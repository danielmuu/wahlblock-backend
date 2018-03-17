
INSERT INTO voter(id, name, id_card_number, voter_key, vote_session_started) VALUES (1, 'Max Mustermann', 'X1234567', '892374', FALSE);
INSERT INTO voter(id, name, id_card_number, voter_key, vote_session_started) VALUES (2, 'Maxi Musterfrau', 'F123G568', '238472', FALSE);

INSERT INTO election(id, title, description, begin_date, end_date) VALUES (1, 'Wahl von Lorem ipsum dolor sit amet', 'Cat ipsum dolor sit amet, run in circles sit in box but cough hairball on conveniently placed pants or then cats take over the world. My slave human didn''t give me any food so i pooped on the floor show belly or eat all the power cords ears back wide eyed. Prance along on top of the garden fence, annoy the neighbor''s dog and make it bark sniff sniff.', '2018-08-01 10:00:00.000', '2019-08-20 23:59:59.999');

CREATE SEQUENCE voter_seq_generator;
ALTER SEQUENCE voter_seq_generator RESTART WITH 3;

