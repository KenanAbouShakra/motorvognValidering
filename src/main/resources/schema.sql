CREATE TABLE Bil
(
    id INTEGER AUTO_INCREMENT NOT NULL ,
    personnr Long NOT NULL,
    fornavn VARCHAR (255) NOT NULL,
    etternavn VARCHAR (255) NOT NULL,
    kjennetegn VARCHAR (255) NOT NULL,
    bilmerke VARCHAR (255) NOT NULL,
    bilmodell VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE Motorvogn
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    merke VARCHAR(255) NOT NULL,
    modell VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);
 CREATE TABLE Bruker
(
    id INTEGER AUTO_INCREMENT NOT NULL ,
    brukernavn VARCHAR (255) NOT NULL,
    passord VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
 )