-- Create databases for microservices
CREATE DATABASE user_db;
CREATE DATABASE product_db;
CREATE DATABASE order_db;
CREATE DATABASE notification_db;

-- Create users for each service
CREATE USER user_db_user WITH PASSWORD 'user_db_password';
CREATE USER product_db_user WITH PASSWORD 'product_db_password';
CREATE USER order_db_user WITH PASSWORD 'order_db_password';
CREATE USER notification_db_user WITH PASSWORD 'notification_db_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE user_db TO user_db_user;
GRANT ALL PRIVILEGES ON DATABASE product_db TO product_db_user;
GRANT ALL PRIVILEGES ON DATABASE order_db TO order_db_user;
GRANT ALL PRIVILEGES ON DATABASE notification_db TO notification_db_user;
