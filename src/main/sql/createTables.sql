use lenasrpg01;

CREATE TABLE characters (
    id INT NOT NULL AUTO_INCREMENT,
    creation TIMESTAMP NOT NULL,
    CONSTRAINT pk_characters PRIMARY KEY (id)
);