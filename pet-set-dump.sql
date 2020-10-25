CREATE TABLE country
(
  countryName VARCHAR(50) NOT NULL,
  PRIMARY KEY (countryName)
);

CREATE TABLE classification
(
  className VARCHAR(50) NOT NULL,
  PRIMARY KEY (className)
);

CREATE TABLE dogBreed
(
  breedName VARCHAR(50) NOT NULL,
  lifeExpectancy INT NOT NULL,
  weight FLOAT NOT NULL,
  height FLOAT NOT NULL,
  temperament VARCHAR(50) NOT NULL,
  colours VARCHAR(50) NOT NULL,
  coat VARCHAR(50) NOT NULL,
  wiki VARCHAR(200) NOT NULL,
  description VARCHAR(500) NOT NULL,
  countryOfOrigin VARCHAR(50),
  classification VARCHAR(50) NOT NULL,
  descendantOf_breed VARCHAR(50),
  PRIMARY KEY (breedName),
  FOREIGN KEY (countryOfOrigin) REFERENCES country(countryName),
  FOREIGN KEY (classification) REFERENCES classification(className),
  FOREIGN KEY (descendantOf_breed) REFERENCES dogBreed(breedName)
);
