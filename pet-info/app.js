const express = require('express');
const app = express();
const path = require('path');
const bodyParser = require('body-parser');
const port = 3000;

const pgp = require('pg-promise')(/* options */);
const db = pgp('postgres://postgres:bazepodataka@localhost:5432/pets-information');

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.use(express.static(path.join(__dirname, 'public')));

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.get('/', (req, res) => {
    res.render('index', {
        title: 'Pet data set',
        showExport: false,
    });
});

app.get('/datatable', async (req, res) => {
    const petData = await db.any('SELECT * FROM breed;')
    .then((data) => {
        console.log('DATA: ', data);
        return data;
    })
    .catch((error) => {
        console.log('ERROR: ', error)
    });

    res.render('datatable', {
        title: 'Datatable',
        data: petData,
        showExport: true,
        query: '',
    });
});

app.get('/about', (req, res) => {
    res.render('about', {
        title: 'About',
        showExport: false,
    });
});

app.post('/datatable', async (req, res) => {
    let petData, query;
    const krijec = req.body.krijec;
    const srchField = req.body.sfield;
    var values = {
        delimiter: ',',
        path: `${__dirname}\\public\\static\\pet-set-filter.csv`,
        query: ''
    };
    if (req.body.sfield === 'all') {
        if (isNumeric(req.body.krijec)) {
            petData = await db.any('SELECT * FROM breed WHERE breedname=$1 or lifeexpectancy=$1 or weight=$1 or height=$1 or $1=ANY(temperament) or $1=ANY(colours) or coat=$1 or wiki=$1 or description=$1 or gender=$1 or countryoforigin=$1 or classification=$1 or species=$1 or descendantof_breed=$1;'
            , req.body.krijec)
            .then((data) => {
                console.log('DATA: ', data);
                return data;
            })
            .catch((error) => {
                console.log('ERROR: ', error)
            });
            values.query = 'SELECT * FROM breed WHERE lifeexpectancy=' + krijec + ' or weight=' + krijec + ' or height=' + krijec + ' or descendantof_breed=' + krijec + '';
            console.log(values);
            db.none('COPY (SELECT * FROM breed WHERE lifeexpectancy=' + krijec + ' or weight=' + krijec + ' or height=' + krijec + ' or descendantof_breed=' + krijec + ') TO \'' + `${__dirname}\\public\\static\\pet-set-filter` + '\' WITH CSV DELIMITER \',\'');
        } else {
            petData = await db.any('SELECT * FROM breed WHERE breedname=$1 or $1=ANY(temperament) or $1=ANY(colours) or coat=$1 or wiki=$1 or description=$1 or gender=$1 or countryoforigin=$1 or classification=$1 or species=$1;'
            , req.body.krijec)
            .then((data) => {
                console.log('DATA: ', data);
                return data;
            })
            .catch((error) => {
                console.log('ERROR: ', error)
            });
            query = 'SELECT * FROM breed WHERE breedname=\'' + krijec + '\' or \'' + krijec + '\'=ANY(temperament) or \'' + krijec + '\'=ANY(colours) or coat=\'' + krijec + '\' or wiki=\'' + krijec + '\' or description=\'' + krijec + '\' or gender=\'' + krijec + '\' or countryoforigin=\'' + krijec + '\' or classification=\'' + krijec + '\' or species=\'' + krijec + '\';'
        }
    } else if (req.body.sfield === 'temperament' || req.body.sfield === 'colours') {
        petData = await db.any('SELECT * FROM breed WHERE $2=ANY($1~);', [req.body.sfield, req.body.krijec])
        .then((data) => {
            console.log('DATA: ', data);
            return data;
        })
        .catch((error) => {
            console.log('ERROR: ', error)
        });
        query = 'SELECT * FROM breed WHERE \'' + krijec + '\'=ANY(' + srchField + ');'
    } else {
        petData = await db.any('SELECT * FROM breed WHERE $1~=$2;', [req.body.sfield, req.body.krijec])
        .then((data) => {
            console.log('DATA: ', data);
            return data;
        })
        .catch((error) => {
            console.log('ERROR: ', error)
        });
        query = 'SELECT * FROM breed WHERE ' + srchField + '=\'' + krijec + '\';'
    }

    res.render('datatable', {
        title: 'Datatable',
        data:petData,
        showExport:true,
        query: query,
    });

    function isNumeric(str) {
        if (typeof str != "string") return false;
        return !isNaN(str) && !isNaN(parseFloat(str));
    }
});

app.get('/csv', function(req, res){
    const file = `${__dirname}/public/static/pet-set-filter.csv`;
    res.download(file); // Set disposition and send it.
});

app.listen(port, () => {
    console.log(`Listening on port: ${port}`);
});