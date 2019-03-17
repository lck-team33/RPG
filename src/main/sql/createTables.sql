use lenasrpg01;

CREATE TABLE characters (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ts_creation BIGINT NOT NULL
);

CREATE TABLE character_properties (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    character_id INT NOT NULL FOREIGN KEY REFERENCES characters(id),
    property VARCHAR(255) NOT NULL,
    content VARCHAR(255),
    ts_creation BIGINT NOT NULL
);

CREATE INDEX character_properties_property
    ON character_properties (property);
CREATE INDEX character_properties_content
    ON character_properties (content);