CREATE TABLE tasks_categories
(
    id          serial PRIMARY KEY,
    task_id     int not null REFERENCES tasks (id),
    category_id int not null REFERENCES todo_category (id),
    UNIQUE (task_id, category_id)
);