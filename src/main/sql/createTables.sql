use test01;

CREATE TABLE characters (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    ts_creation BIGINT NOT NULL
);

CREATE TABLE character_properties (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    character_id VARCHAR(36) NOT NULL,
    property VARCHAR(255) NOT NULL,
    content VARCHAR(255),
    ts_creation BIGINT NOT NULL
);

CREATE INDEX character_properties_property
    ON character_properties (property);
CREATE INDEX character_properties_content
    ON character_properties (content);