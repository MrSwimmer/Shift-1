create TABLE PERSONS (
    id serial primary key,
    name text,
    surname text,
    age integer,
    occupation text DEFAULT NULL
)