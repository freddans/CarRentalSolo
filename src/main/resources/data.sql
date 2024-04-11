-- Lägg till 5 adresser
INSERT INTO customers (username, name, address, email, phone) VALUES
                                                    ('Freddan', 'Fredrik', 'Prästgårdsängen 8', 'flundell89@gmail.com', '+46708388404'),
                                                    ('Anders55', 'Anders', 'Andersgatan 55', 'anders@hotmail.com', '+46701112233'),
                                                    ('Pia132', 'Pia', 'Sommarvädersgatan 12', 'piapia@gmail.com', '+46702223344'),
                                                    ('tokstolle', 'Johannes', 'Avenyen 33', 'tokstollenjohannes@gmail.com', '+46703334455'),
                                                    ('crazycatlady', 'Gert', 'Kattvägen 93', 'mjau-mjau@gmail.com', '+46704445566');



-- Lägg till 5 medlemmar
INSERT INTO cars (dailyprice, manifacturer, model, regnr, available) VALUES
                                                                                         (150, 'Tesla', 'Model 3', 'ABC123', true),
                                                                                         (150, 'Volvo', 'S90', 'JHT534', true),
                                                                                         (150, 'Toyota', 'Yaris', 'TAM967', false),
                                                                                         (150, 'Audi', 'A7', 'VRR332', false),
                                                                                         (150, 'Audi', 'TT', 'FEL312', false);

