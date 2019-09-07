SELECT * FROM characters;
SELECT * FROM character_properties;

SELECT character_id FROM character_properties WHERE property = 'name' AND content = 'andi';

SELECT property, content FROM character_properties WHERE character_id = 'aeae11ed-0ed3-4669-8d85-5e7d4742ac35';

SELECT * FROM
	characters AS CH,
	(SELECT character_id, content FROM character_properties WHERE property = 'haarfarbe') AS haarfarbe
WHERE haarfarbe.character_id = CH.id ;