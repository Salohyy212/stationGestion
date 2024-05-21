create table station (
    id SERIAL primary key ,
    localisation VARCHAR(150),
    FOREIGN KEY (idProduct) REFERENCES product(id),
    FOREIGN KEY (idOperationProduct) REFERENCES operationProduct(id)
)