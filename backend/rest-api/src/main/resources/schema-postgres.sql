DROP TABLE IF EXISTS species CASCADE;
DROP TABLE IF EXISTS breed CASCADE;
DROP TABLE IF EXISTS country CASCADE;
DROP TABLE IF EXISTS classification CASCADE;
DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence START 11;

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