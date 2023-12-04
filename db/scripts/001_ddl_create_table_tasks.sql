CREATE TABLE tasks (
   id SERIAL PRIMARY KEY,
   title varchar(200),
   description TEXT,
   created TIMESTAMP,
   done BOOLEAN
);