DROP TABLE IF EXISTS species CASCADE;
DROP TABLE IF EXISTS breed CASCADE;
DROP TABLE IF EXISTS country CASCADE;
DROP TABLE IF EXISTS classification CASCADE;

CREATE TABLE country
(
  countryName VARCHAR(100) NOT NULL,
  countryAbbrev VARCHAR(100),
  PRIMARY KEY (countryName)
);

CREATE TABLE classification
(
  className VARCHAR(100) NOT NULL,
  description VARCHAR(500),
  PRIMARY KEY (className)
);

CREATE TABLE species
(
  colloquialName VARCHAR(100) NOT NULL,
  speciesName VARCHAR(100) NOT NULL,
  PRIMARY KEY (colloquialName),
  UNIQUE (speciesName)
);

CREATE TABLE breed
(
  id INT NOT NULL,
  breedName VARCHAR(200) NOT NULL,
  lifeExpectancy INT NOT NULL,
  weight FLOAT NOT NULL,
  height FLOAT NOT NULL,
  temperament text[] NOT NULL,
  colours text[] NOT NULL,
  coat VARCHAR(200) NOT NULL,
  wiki VARCHAR(200) NOT NULL,
  description VARCHAR(500),
  gender VARCHAR(10) NOT NULL,
  countryOfOrigin VARCHAR(100),
  classification VARCHAR(100),
  species VARCHAR(100) NOT NULL,
  descendantOf_breed INT,
  PRIMARY KEY (id),
  FOREIGN KEY (countryOfOrigin) REFERENCES country(countryName),
  FOREIGN KEY (classification) REFERENCES classification(className),
  FOREIGN KEY (species) REFERENCES species(colloquialName),
  FOREIGN KEY (descendantOf_breed) REFERENCES breed(id)
);

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

INSERT INTO country (countryname, countryAbbrev)
VALUES
	('Germany', 'DE'),
	('Soviet Union', null),
	('France', 'FR'),
	('Croatia', 'HR'),
	('Argentina', 'AR'),
	('America', 'US'),
	('Yugoslavia', null),
	('Burma', 'MM'),
	('Afghanistan', 'AF'),
	('United Kingdom', 'UK');

INSERT INTO breed 
(id, breedname, lifeexpectancy, weight, height,
 temperament, colours, coat, wiki, description, gender,
 countryoforigin, classification, species,
 descendantof_breed)
VALUES
	(1, 'German Shepherd', 11, 35, 62.5,'{Confident, Courageous, Smart}',
	'{Tan with black saddle, sable, solid black}',
	'Double coat', 'German_Shepherd',
	null, 'male', 'Germany', 'Sheepdogs and Cattledogs',
	'Dog', null),
	(2, 'East European Shepherd', 11, 41, 69.5, '{Confident, Courageous, Smart, Cold resistant}',
	'{Black and tan, solid black, sable}',
	'Medium-length dense coat', 'East-European_Shepherd',
	null, 'male', 'Soviet Union', 'Sheepdogs and Cattledogs',
	'Dog', 1),
	(3, 'German Shepherd', 11, 27, 57.5,'{Confident, Courageous, Smart}',
	'{Tan with black saddle, sable, solid black}',
	'Double coat', 'German_Shepherd',
	null, 'female', 'Germany', 'Sheepdogs and Cattledogs',
	'Dog', null),
	(4, 'Croatian Sheepdog', 13, 16.5, 47.5, '{Loyal, Active, Alert, Intelligent}',
	'{Black}', 'Medium-length dense coat', 'Croatian_Sheepdog',
	null, 'm/f', 'Croatia', 'Sheepdogs and Cattledogs',
	'Dog', null),
	(5, 'Burmese', 14, 5, 21.5, '{Playful, Puppy-like, Vocal}',
	'{Rich Dark Brown, Blue, Red, Cream, Tortoiseshell}', 'Very short, fine and glossy', 'Burmese_cat',
	'The dogs of cats', 'm/f', 'Burma', null,
	'Cat', null),
	(6, 'Affenpinscher', 12, 3.7, 26.5, '{Confident, Funny, Fearless}',
	'{Black, Grey, Silver}', 'Wire', 'Affenpinscher',
	'The stand-up comedian of the pedigrees', 'm/f', 'Germany', 'Pinscher and Schnauzer - Molossoid and Swiss Mountain and Cattledogs', 'Dog', null),
	(7, 'Afghan Hound', 12, 24, 67, '{Dignified, Profoundly Loyal, Aristocratic}',
	'{Fawn, Gold, Brindle, White}', 'Long and fine', 'Afghan_Hound',
	'90% curtain, 10% dog', 'm/f', 'Afghanistan', 'Sighthounds', 'Dog', null),
	(8, 'Basset Hound', 11, 30, 34, '{Charming, Patient, Low-key}',
	'{Black, White, Tan}', 'Smooth and short', 'Basset_Hound',
	'Flappy sausage', 'male', 'United Kingdom', 'Scent hounds and related breeds', 'Dog', null),
	(9, 'Basset Hound', 11, 24.5, 32, '{Charming, Patient, Low-key}',
	'{Black, White, Tan}', 'Smooth and short', 'Basset_Hound',
	'Flappy sausage', 'female', 'United Kingdom', 'Scent hounds and related breeds', 'Dog', null),
	(10, 'French Bulldog', 11, 12, 30.5, '{Adaptable, Playful, Smart}',
	'{White, Cream, Fawn, Brindle}', 'Brilliant, short and smooth', 'French_Bulldog',
	'Wrinkly-faced pups', 'm/f', 'France', 'Companion and Toy Dogs', 'Dog', null)
RETURNING *;