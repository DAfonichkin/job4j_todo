create TABLE todo_user (
   id SERIAL PRIMARY KEY,
   name varchar(200),
   login varchar not null,
   password varchar not null
);