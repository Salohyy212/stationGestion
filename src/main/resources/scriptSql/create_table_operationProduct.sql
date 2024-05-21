create type operationType AS enum (Entry, exit);

create table operationProduct (
    id SERIAL primary key ,
    dateTime instant NOT NULL ,
    operationType operationType NOT NULL ,
    quantity int NOT NULL ,
    FOREIGN KEY (idProduct) REFERENCES product(id)
)