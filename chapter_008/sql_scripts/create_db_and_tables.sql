--������� �������:

CREATE DATABASE orders_db;

--������� SQL ������ ��������� ������� ��� �������� ��������� ������� ������.

CREATE TABLE order_system (
	id serial primary key, 
	user_of_db character varying (10), 
	order_of_user character varying (20), 
	order_condition character varying (2000), 
	order_category character varying (2000)
);

--��������� ������� � ������� � �� �������:

CREATE TABLE db_users (
	id serial primary key,
	user_name character varying (20),
	user_role character varying (20),
	user_id integer references order_system(id)
);

CREATE TABLE user_rights (
	id serial primary key,
	user_right character varying (20),
	user_id integer references db_users(id)
);


--��������� ������� ��� �������� ���������� � ������������ � ���������� �� � ��������:

CREATE TABLE comment_for_order (
	id serial primary key,
	comment text,
	user_id integer references order_system(id)
);

CREATE TABLE attached_file (
	id serial primary key,
	filepath text,
	user_id integer references order_system(id)
);