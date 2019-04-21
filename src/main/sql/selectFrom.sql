SELECT * FROM characters;
SELECT * FROM character_properties;

SELECT * FROM
	characters AS CH,
	(SELECT character_id, content FROM character_properties WHERE property = 'haarfarbe') AS haarfarbe
WHERE haarfarbe.character_id = CH.id ;