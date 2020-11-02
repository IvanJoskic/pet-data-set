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
