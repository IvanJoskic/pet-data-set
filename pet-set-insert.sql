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
	('Yugoslavia'),
	('Burma'),
	('Afghanistan'),
	('United Kingdom');

INSERT INTO breed 
(id, breedname, lifeexpectancy, weight, height,
 temperament, colours, coat, wiki, description, gender,
 countryoforigin, classification, species,
 descendantof_breed)
VALUES
	(1, 'German Shepherd', 11, 35, 62.5,'Confident, Courageous, Smart',
	'Tan with black saddle, sable, solid black',
	'Double coat', 'https://en.wikipedia.org/wiki/German_Shepherd',
	'', 'male', 'Germany', 'Sheepdogs and Cattledogs',
	'Dog', null),
	(2, 'East European Shepherd', 11, 41, 69.5, 'Confident, Courageous, Smart, Cold resistant',
	'Black and tan, solid black, sable',
	'Medium-length dense coat', 'https://en.wikipedia.org/wiki/East-European_Shepherd#Characteristics',
	'', 'male', 'Soviet Union', 'Sheepdogs and Cattledogs',
	'Dog', 1),
	(3, 'German Shepherd', 11, 27, 57.5,'Confident, Courageous, Smart',
	'Tan with black saddle, sable, solid black',
	'Double coat', 'https://en.wikipedia.org/wiki/German_Shepherd',
	'', 'female', 'Germany', 'Sheepdogs and Cattledogs',
	'Dog', null),
	(4, 'Croatian Sheepdog', 13, 16.5, 47.5, 'Loyal, Active, Alert, Intelligent',
	'Black', 'Medium-length dense coat', 'https://en.wikipedia.org/wiki/Croatian_Sheepdog',
	'', 'm/f', 'Croatia', 'Sheepdogs and Cattledogs',
	'Dog', null),
	(5, 'Burmese', 14, 5, 21.5, 'Playful, Puppy-like, Vocal',
	'Rich Dark Brown, Blue, Red, Cream, Tortoiseshell', 'Very short, fine, glossy', 'https://en.wikipedia.org/wiki/Burmese_cat',
	'The dogs of cats', 'm/f', 'Burma', null,
	'Cat', null),
	(6, 'Affenpinscher', 12, 3.7, 26.5, 'Confident, Funny, Fearless',
	'Black, Grey, Silver', 'Wire', 'https://en.wikipedia.org/wiki/Affenpinscher',
	'The stand-up comedian of the pedigrees', 'm/f', 'Germany', 'Pinscher and Schnauzer - Molossoid and Swiss Mountain and Cattledogs', 'Dog', null),
	(7, 'Afghan Hound', 12, 24, 67, 'Dignified, Profoundly Loyal, Aristocratic',
	'Fawn, Gold, Brindle, White', 'Long and fine', 'https://en.wikipedia.org/wiki/Afghan_Hound',
	'90% curtain, 10% dog', 'm/f', 'Afghanistan', 'Sighthounds', 'Dog', null),
	(8, 'Basset Hound', 11, 30, 34, 'Charming, Patient, Low-key',
	'Black, White and Tan', 'Smooth, short', 'https://en.wikipedia.org/wiki/Basset_Hound',
	'Flappy sausage', 'male', 'United Kingdom', 'Scent hounds and related breeds', 'Dog', null),
	(9, 'Basset Hound', 11, 24.5, 32, 'Charming, Patient, Low-key',
	'Black, White and Tan', 'Smooth, short', 'https://en.wikipedia.org/wiki/Basset_Hound',
	'Flappy sausage', 'female', 'United Kingdom', 'Scent hounds and related breeds', 'Dog', null),
	(10, 'French Bulldog', 11, 12, 30.5, 'Adaptable, Playful, Smart',
	'White, Cream, Fawn, Brindle', 'Brilliant, short, smooth', 'https://en.wikipedia.org/wiki/French_Bulldog',
	'Wrinkly-faced pups', 'm/f', 'France', 'Companion and Toy Dogs', 'Dog', null)
RETURNING *;
