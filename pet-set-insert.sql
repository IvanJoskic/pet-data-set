INSERT INTO species (colloquialname, speciesname)
VALUES
	('Cat', 'Felis catus'),
	('Dog', 'Canis familiaris');


INSERT INTO classification (classname)
VALUES
	('Sheepdogs and Cattledogs'),
	('Pinscher and Schnauzer - Molossoid and Swiss Mountain and Cattledogs'),
	('Terriers'),
	('Dachshunds'),
	('Spitz and primitive types'),
	('Scent hounds and related breeds'),
	('Pointing Dogs'),
	('Retrievers - Flushing Dogs - Water Dogs'),
	('Companion and Toy Dogs'),
	('Sighthounds');

INSERT INTO country (countryname)
VALUES
	('Germany'),
	('Soviet Union'),
	('France'),
	('Croatia'),
	('Argentina'),
	('America'),
	('Yugoslavia');

INSERT INTO breed 
(breedname, lifeexpectancy, weight, height,
 temperament, colours, coat, wiki, description, gender,
 countryoforigin, classification, species,
 descendantof_breed)
VALUES
	('German Shepherd', 13, 40, 65,'Confident, Courageous, Smart',
	'Tan with black saddle, sable, solid black',
	'Double coat', 'https://en.wikipedia.org/wiki/German_Shepherd',
	'', 'male', 'Germany', 'Sheepdogs and Cattledogs',
	'Dog', null)
RETURNING *;
