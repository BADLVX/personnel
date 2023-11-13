DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Ligue;


CREATE TABLE Utilisateur(
    User_Id INT,
    Id_Ligue INT,
    Nom VARCHAR(25),
    Prenom VARCHAR(25),
    Mdp VARCHAR (50),
    Date_Arrivee DATE,
    Date_Depart DATE,
    PRIMARY KEY (User_Id),
    /* FOREIGN KEY REFERENCES Ligue (Id_Ligue) */
)ENGINE = INNODB;

CREATE TABLE Ligue(
    Id_Ligue INT,
    Nom_Ligue VARCHAR(40),
    PRIMARY KEY (Id_Ligue)
)ENGINE = INNODB;