--Создать структур данных в базе. Таблицы. Трансмиссия. Двигатель, Коробка передач.

CREATE DATABASE cars;

CREATE TABLE engine (
	id serial primary key,
	engine_name character varying (100)
);

CREATE TABLE transmission (
	id serial primary key,
	transmission_name character varying (100)
);

CREATE TABLE gearbox (
	id serial primary key,
	gearbox_name character varying (100)
);

--Создать структуру Машина.

CREATE TABLE cars_list (
	id serial PRIMARY KEY,
	car_name character varying (20) NOT NULL,
	engine_id integer REFERENCES engine(id),
	transmission_id integer REFERENCES transmission (id),
	gearbox_id integer REFERENCES gearbox (id)
);


INSERT INTO engine (engine_name) VALUES ('МАЗ-642550-2120');
INSERT INTO engine (engine_name) VALUES ('МАЗ-642505-028');
INSERT INTO engine (engine_name) VALUES ('МАЗ-5440А9');
INSERT INTO engine (engine_name) VALUES ('Daimler OM501 LA.III');

INSERT INTO transmission (transmission_name) VALUES ('АКПП');
INSERT INTO transmission (transmission_name) VALUES ('МКПП');
INSERT INTO transmission (transmission_name) VALUES ('АКПП-2');
INSERT INTO transmission (transmission_name) VALUES ('АКПП-3');

INSERT INTO gearbox (gearbox_name) VALUES ('gearbox-1');
INSERT INTO gearbox (gearbox_name) VALUES ('gearbox-2');
INSERT INTO gearbox (gearbox_name) VALUES ('gearbox-3');
INSERT INTO gearbox (gearbox_name) VALUES ('gearbox-4');

INSERT INTO cars_list (car_name, engine_id, transmission_id, gearbox_id) VALUES ('Kamaz', 1, 1, 1);
INSERT INTO cars_list (car_name, engine_id, transmission_id, gearbox_id) VALUES ('TATRA', 2, 2, 2);
INSERT INTO cars_list (car_name, engine_id, transmission_id, gearbox_id) VALUES ('Mercedes', 3, 3, 3);

--Вывести все машины.

SELECT c.car_name, e.engine_name, t.transmission_name, g.gearbox_name
FROM cars_list AS c
LEFT OUTER JOIN engine AS e ON c.engine_id = e.id
LEFT OUTER JOIN transmission AS t ON c.transmission_id = t.id
LEFT OUTER JOIN gearbox AS g ON c.gearbox_id = g.id;

--Вывести все неиспользуемые детали.

SELECT e.engine_name 
FROM engine AS e
LEFT OUTER JOIN cars_list AS c
ON c.engine_id = e.id
WHERE c.car_name IS NULL;

SELECT t.transmission_name 
FROM transmission AS t
LEFT OUTER JOIN cars_list AS c
ON c.transmission_id = t.id
WHERE c.car_name IS NULL;

SELECT g.gearbox_name 
FROM gearbox AS g
LEFT OUTER JOIN cars_list AS c
ON c.gearbox_id = g.id
WHERE c.car_name IS NULL;

--Или как вариант с RIGHT OUTER JOIN:

SELECT e.engine_name
FROM cars_list AS c
RIGHT OUTER JOIN engine AS e ON c.engine_id = e.id
WHERE c.car_name IS NULL;

SELECT t.transmission_name
FROM cars_list AS c
RIGHT OUTER JOIN transmission AS t ON c.transmission_id = t.id
WHERE c.car_name IS NULL;

SELECT g.gearbox_name 
FROM cars_list AS c
RIGHT OUTER JOIN gearbox AS g ON c.gearbox_id = g.id
WHERE c.car_name IS NULL;
