create type productName AS enum (diesel, petroleum, gasoline);


create table productTemplate (
    id SERIAL PRIMARY KEY,
    productName productName NOT NULL,
    price DOUBLE PRECISION NOT NULL
);