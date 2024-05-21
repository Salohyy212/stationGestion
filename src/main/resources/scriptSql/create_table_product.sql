create table product (
    id SERIAL primary key,
    stockQuantity int NOT NULL,
    FOREIGN KEY (idTemplate) REFERENCES productTemplate(id)
)