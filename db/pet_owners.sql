CREATE TABLE master(
id SERIAL PRIMARY KEY,
name VARCHAR(15),
pets_number INT	
);

CREATE TABLE pets(
id SERIAL PRIMARY KEY,
	nickname VARCHAR(15)
);

CREATE TABLE pets_owners(
id SERIAL PRIMARY KEY,
	master_id INT references master(id),
	pet_id INT references pets(id),
	master_name varchar references master(name),
	pet_nickname varchar references pets(nickname)
	);

INSERT INTO master(name) VALUES('Ivan');
INSERT INTO master(name) VALUES('Petr');
INSERT INTO master(name) VALUES('Olga');

INSERT INTO pets(nickname) VALUES('Zhuchka');
INSERT INTO pets(nickname) VALUES('Pushok');
INSERT INTO pets(nickname) VALUES('Murzik');
INSERT INTO pets(nickname) VALUES('Polkan');


INSERT INTO pets_owners(master_id, pet_id) VALUES(1,1);
INSERT INTO pets_owners(master_id, pet_id) VALUES(1,2);
INSERT INTO pets_owners(master_id, pet_id) VALUES(2,3);
INSERT INTO pets_owners(master_id, pet_id) VALUES(3,4);

SELECT * FROM pets_owners;