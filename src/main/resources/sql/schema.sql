create table politicians (
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    name       text not null,
    surname    text not null,
    party      text not null,
    image_path text
);

INSERT INTO politicians (name, surname, party, image_path) VALUES
                                                               ('Angela', 'Merkel', 'CDU', 'angela-merkel.jpg'),
                                                               ('Robert', 'Habeck', 'Bündnis 90/Die Grünen', 'robert-habeck.jpg'),
                                                               ('Olaf', 'Scholz', 'SPD', 'olaf-scholz.jpg'),
                                                               ('Christian', 'Lindner', 'FDP', 'christian-lindner.jpg');

