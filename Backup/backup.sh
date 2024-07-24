#!/bin/sh

# Dump the database from Main_DB
docker exec Main_DB /usr/bin/mysqldump -u root -pglobalm rpa_databases > /backup/dump.sql

# Restore the dump to BackUp_DB
cat /backup/dump.sql | docker exec -i BackUp_DB /usr/bin/mysql -u root -pglobalb Back_rpa_databases
