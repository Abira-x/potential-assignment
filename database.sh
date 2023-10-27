bash
#!/bin/bash

# Start the MySQL service
service mysql start

# Wait for the MySQL service to be ready
while ! mysqladmin ping -h127.0.0.1 -uroot -pdummy12345 --silent; do
    sleep 1
done

# Create the database
mysql -h127.0.0.1 -uroot -pdummy12345 -e "CREATE DATABASE order_data;"

# Create tables and insert initial data
mysql -h127.0.0.1 -uroot -pdummy12345 order_data < /path/to/your/sql/script.sql

# Stop the MySQL service
service mysql stop