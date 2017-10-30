CREATE ROLE simple_manager;
GRANT SELECT, UPDATE, INSERT ON order_system TO simple_manager;
GRANT SELECT, UPDATE, INSERT ON db_users TO simple_manager;
GRANT SELECT, UPDATE, INSERT ON user_rights TO simple_manager;
GRANT SELECT, UPDATE, INSERT ON comment_for_order TO simple_manager;
GRANT SELECT, UPDATE, INSERT ON attached_file TO simple_manager;
CREATE USER simple_user;
GRANT simple_manager to simple_user;