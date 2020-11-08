const express = require('express');
const app = express();
const path = require('path');
const port = 5000;

const pgp = require('pg-promise')(/* options */);
const db = pgp('postgres://postgres:bazepodataka@localhost:5432/pets-information');



app.use(express.static(path.join(__dirname, 'public')));

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.get('/', (req, res) => {
    res.render('index', {
        title: 'Home',
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
    });
});

app.get('/about', (req, res) => {
    res.render('about', {
        title: 'About'
    });
});

app.listen(port, () => {
    console.log(`Listening on port: ${port}`);
});