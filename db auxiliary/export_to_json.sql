SELECT json_agg(t)
FROM 
(SELECT breedname, lifeexpectancy, weight, height, temperament, colours, coat, wiki
	, description, gender, countryOfOrigin, countryAbbrev, classification
	, species, speciesName, descendantof_breed
FROM breed 
	JOIN country ON countryOfOrigin=countryName
	JOIN species ON species=colloquialName) t;
	
--
-- OTHER WAY FROM TERMINAL
--
\t on
\pset format unaligned
WITH t (
	breedname, lifeExpectancy, weight, height, temperament, colours, coat, wiki
	, description, gender, countryOfOrigin, countryAbbrev, classification
	, species, speciesName, descendantOf_breed
) as (
SELECT breedname, lifeexpectancy, weight, height, temperament, colours, coat, wiki
	, description, gender, countryOfOrigin, countryAbbrev, classification
	, species, speciesName, descendantof_breed
FROM breed 
	JOIN country ON countryOfOrigin=countryName
	JOIN species ON species=colloquialName
)
SELECT json_agg(t) FROM t \g test_file.json

--
-- TEMP
--
SELECT array_to_json(array_agg(row_to_json(t)))
FROM 
(SELECT breedname, lifeexpectancy, weight, height, temperament, colours, coat, wiki
	, description, gender, 
 	(SELECT row_to_json(d)
	 FROM (
		SELECT countryOfOrigin, countryAbbrev FROM breed as b1
		JOIN country ON countryOfOrigin=countryName WHERE b1.id=breed.id) d
	) as countryOfOrigin
 	, classification,
 	(SELECT row_to_json(d)
	 FROM (SELECT colloquialName, speciesName FROM breed as b1
		  JOIN species ON species=colloquialName WHERE b1.id=breed.id) d
	) AS species
 	, (SELECT row_to_json(d)
	  FROM (SELECT breedName, countryOfOrigin FROM breed AS b2
	  WHERE b2.id=breed.descendantOf_breed) d) AS descendantof_breed
FROM breed
	JOIN country ON countryOfOrigin=countryName
	JOIN species ON species=colloquialName) t

--
-- export to csv
-- ako je bitno, pitaj!
SELECT breedname, lifeexpectancy, weight, height, temperament, colours, coat, wiki
	, description, gender, 
 	(SELECT array_agg(row_to_json(d))
	 FROM (
		SELECT countryOfOrigin, countryAbbrev FROM breed as b1
		JOIN country ON countryOfOrigin=countryName WHERE b1.id=breed.id) d
	) as countryOfOrigin
 	, classification,
 	(SELECT array_agg(row_to_json(d))
	 FROM (SELECT colloquialName, speciesName FROM breed as b1
		  JOIN species ON species=colloquialName WHERE b1.id=breed.id) d
	) AS species
 	, (SELECT array_agg(row_to_json(d))
	  FROM (SELECT breedName, countryOfOrigin FROM breed AS b2
	  WHERE b2.id=breed.descendantOf_breed) d) AS descendantof_breed
FROM breed
	JOIN country ON countryOfOrigin=countryName
	JOIN species ON species=colloquialName