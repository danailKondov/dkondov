--—оздать SQL скрипт заполн€ющий начальные данные дл€ системы за€вок.

INSERT INTO order_system (user_of_db, order_of_user, order_condition, order_category)
VALUES ('User1', 'dumbbells 5 kg', 'no condition', 'BUY');

INSERT INTO comment_for_order (comment, user_id)
VALUES ('urgent order', 1);

INSERT INTO attached_file (filepath, user_id)
VALUES ('I:\attached.file', 1);

INSERT INTO db_users (user_name, user_role, user_id)
VALUES ('Vasili', 'simple manager', 1);

INSERT INTO user_rights (user_right, user_id) 
VALUES ('select', 1);

INSERT INTO user_rights (user_right, user_id) 
VALUES ('update', 1);

INSERT INTO user_rights (user_right, user_id) 
VALUES ('insert', 1);