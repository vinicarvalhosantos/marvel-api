-- SCRIPT PARA ADICIONAR OS HEROIS NA TABELA
INSERT INTO marvel_characters (name, description, modified)
VALUES ('Savage Puncher', 'Caótico e Mal', NOW()),
       ('Noxious Shadow', 'Leal', NOW()),
       ('The Gaunt Ocelot', 'Caótico e Bom', NOW()),
       ('Glorious Illusion', 'Neutro', NOW()),
       ('Spiky Spider Prince', 'Caótico e Neutro', NOW());

-- SCRIPT PARA ADICIONAR COMICS NA TABELA
INSERT INTO marvel_comics (description, digital_id, issue_number, title, variant_description, modified)
VALUES ('Teste de descriçao de quadrinhos para o quadrinhos The Beseeched Ghoul',1054,8,'The Beseeched Ghoul','', NOW()),
       ('Teste de descriçao de quadrinhos para o quadrinhos Toxic Pistol',8845,6,'Toxic Pistol','', NOW()),
       ('Teste de descriçao de quadrinhos para o quadrinhos Indigo Siren',578,200,'Indigo Siren','', NOW()),
       ('Teste de descriçao de quadrinhos para o quadrinhos Swift Embalmer',7854,50,'Swift Embalmer','', NOW());

-- SCRIPT PARA LINKAR O HEROI COM A COMIC
INSERT INTO marvel_characters_comics (marvel_characters_id,comics_id)
VALUES (1548,10547),
       (1548,10548),
       (1548,10549),
       (1548,10550),
       (1549,10548),
       (1549,10550),
       (1550,10547),
       (1550,10548),
       (1550,10550),
       (1551,10547),
       (1551,10550);

-- SCRIPT PARA ADICIONAR EVENTOS
INSERT INTO marvel_events (description,title)
VALUES ('Descricao teste para o evento Invasao','Invasao'),
       ('Descricao teste para o evento Cachorro quente na praca','Cachorro quente na praca');

-- SCRIPT PARA LINKAR O HEROI COM O EVENTO
INSERT INTO marvel_characters_events (marvel_characters_id,events_id)
VALUES (1548,254),
       (1549,254),
       (1550,254),
       (1552,254),
       (1551,253),
       (1549,253),
       (1550,253);

INSERT INTO marvel_series (description,title)
VALUES ('General series','General');

INSERT INTO marvel_stories (description,title,`type`)
VALUES ('General Story, almost every heroes are here','Our History','cover');

INSERT INTO marvel_characters_series (marvel_characters_id,series_id)
VALUES (1548,154),
       (1549,154),
       (1550,154),
       (1552,154);

INSERT INTO marvel_characters_stories (marvel_characters_id,stories_id)
VALUES (1548,173),
       (1552,173);

