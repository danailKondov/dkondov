CREATE TABLE IF NOT EXISTS items (
	item_id character varying (50) PRIMARY KEY,
	item_name character varying (50),
	item_desc character varying (2000),
	item_created bigint
);

CREATE TABLE IF NOT EXISTS items_comments (
	id serial PRIMARY KEY,
	item_comment text,
	item_id character varying (50) REFERENCES items(item_id)
);