--Tabelle anlegen
create table politicians (
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    name       text not null,
    surname    text not null,
    party      text not null,
    image_path text
);

--Die ersten 4 Politiker zum testen anlegen
INSERT INTO politicians (name, surname, party, image_path) VALUES
                                                               ('Angela', 'Merkel', 'CDU', 'angela-merkel.jpg'),
                                                               ('Robert', 'Habeck', 'Bündnis 90/Die Grünen', 'robert-habeck.jpg'),
                                                               ('Olaf', 'Scholz', 'SPD', 'olaf-scholz.jpg'),
                                                               ('Christian', 'Lindner', 'FDP', 'christian-lindner.jpg');

--Tabelle erweitern mit weiteren bekannten Politikern
INSERT INTO politicians (name, surname, party, image_path) VALUES
                                                               ('Friedrich', 'Merz', 'CDU', 'friedrich-merz.jpg'),
                                                               ('Armin', 'Laschet', 'CDU', 'armin-laschet.jpg'),
                                                               ('Markus', 'Söder', 'CSU', 'markus-soeder.jpg'),
                                                               ('Paul', 'Ziemiak', 'CDU', 'paul-ziemiak.jpg'),
                                                               ('Annegret', 'Kramp-Karrenbauer', 'CDU', 'annegret-kramp-karrenbauer.jpg'),
                                                               ('Julia', 'Klöckner', 'CDU', 'julia-kloeckner.jpg'),
                                                               ('Jens', 'Spahn', 'CDU', 'jens-spahn.jpg'),
                                                               ('Herbert', 'Reul', 'CDU', 'herbert-reul.jpg'),
                                                               ('Kevin', 'Kühnert', 'SPD', 'kevin-kuehnert.jpg'),
                                                               ('Lars', 'Klingbeil', 'SPD', 'lars-klingbeil.jpg'),
                                                               ('Saskia', 'Esken', 'SPD', 'saskia-esken.jpg'),
                                                               ('Hubertus', 'Heil', 'SPD', 'hubertus-heil.jpg'),
                                                               ('Nancy', 'Faeser', 'SPD', 'nancy-faeser.jpg'),
                                                               ('Karl', 'Lauterbach', 'SPD', 'karl-lauterbach.jpg'),
                                                               ('Rolf', 'Mützenich', 'SPD', 'rolf-mützenich.jpg'),
                                                               ('Annalena', 'Baerbock', 'Bündnis 90/Die Grünen', 'annalena-baerbock.jpg'),
                                                               ('Cem', 'Özdemir', 'Bündnis 90/Die Grünen', 'cem-oezdemir.jpg'),
                                                               ('Ricarda', 'Lang', 'Bündnis 90/Die Grünen', 'ricarda-lang.jpg'),
                                                               ('Omid', 'Nouripour', 'Bündnis 90/Die Grünen', 'omid-nouripour.jpg'),
                                                               ('Anton', 'Hofreiter', 'Bündnis 90/Die Grünen', 'anton-hofreiter.jpg'),
                                                               ('Katrin', 'Göring-Eckardt', 'Bündnis 90/Die Grünen', 'katrin-goering-eckardt.jpg'),
                                                               ('Volker', 'Wissing', 'FDP', 'volker-wissing.jpg'),
                                                               ('Marie-Agnes', 'Strack-Zimmermann', 'FDP', 'marie-agnes-strack-zimmermann.jpg'),
                                                               ('Wolfgang', 'Kubicki', 'FDP', 'wolfgang-kubicki.jpg'),
                                                               ('Bettina', 'Stark-Watzinger', 'FDP', 'bettina-stark-watzinger.jpg'),
                                                               ('Alice', 'Weidel', 'AfD', 'alice-weidel.jpg'),
                                                               ('Tino', 'Chrupalla', 'AfD', 'tino-chrupalla.jpg'),
                                                               ('Alexander', 'Gauland', 'AfD', 'alexander-gauland.jpg'),
                                                               ('Sahra', 'Wagenknecht', 'BSW', 'sahra-wagenknecht.jpg'),
                                                               ('Gregor', 'Gysi', 'Die Linke', 'gregor-gysi.jpg'),
                                                               ('Dietmar', 'Bartsch', 'Die Linke', 'dietmar-bartsch.jpg'),
                                                               ('Janine', 'Wissler', 'Die Linke', 'janine-wissler.jpg'),
                                                               ('Gerhard', 'Schröder', 'SPD', 'gerhard-schroeder.jpg'),
                                                               ('Sigmar', 'Gabriel', 'SPD', 'sigmar-gabriel.jpg'),
                                                               ('Frank-Walter', 'Steinmeier', 'SPD', 'frank-walter-steinmeier.jpg');