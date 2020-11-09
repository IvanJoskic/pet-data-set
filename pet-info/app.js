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
        title: 'Home',
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
        exportAsCsv: exportAsCsv,
    });
    
    function exportAsJson() {
        console.log("Exporting as JSON...");
    }

    function exportAsCsv() {

        //const response = await fetch();
        console.log('Exporting as CSV...');
    }

});

app.get('/about', (req, res) => {
    res.render('about', {
        title: 'About',
        showExport: false,
    });
});

app.post('/datatable', async (req, res) => {
    let petData;
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
    } else {
        petData = await db.any('SELECT * FROM breed WHERE $1~=$2;', [req.body.sfield, req.body.krijec])
        .then((data) => {
            console.log('DATA: ', data);
            return data;
        })
        .catch((error) => {
            console.log('ERROR: ', error)
        });
    }
    console.log(req.body)
    res.render('datatable', {
        title: 'Datatable',
        data:petData,
        showExport:true,
    });

    function isNumeric(str) {
        if (typeof str != "string") return false;
        return !isNaN(str) && !isNaN(parseFloat(str));
    }
});

app.listen(port, () => {
    console.log(`Listening on port: ${port}`);
});