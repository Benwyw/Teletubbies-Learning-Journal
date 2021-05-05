CREATE TABLE history(
    order_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    item_id INTEGER DEFAULT NULL,
    quantity INTEGER NOT NULL,
    datetime CHAR(17) NOT NULL,
    PRIMARY KEY(order_id),
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (item_id) REFERENCES item(id)
);

INSERT INTO history(username,item_id,quantity,datetime) VALUES ('ben', 1,1, '05-MAY-2021 19:01');