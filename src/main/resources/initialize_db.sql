CREATE TABLE users(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    fullname VARCHAR(50) NOT NULL,
    phone VARCHAR(8) NOT NULL,
    address VARCHAR(100) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE user_roles(
    user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE item (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    description VARCHAR(255) NOT NULL,
    itemName VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    isabailability BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE attachment (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    item_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE history(
    order_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    item_id INTEGER DEFAULT NULL,
    quantity INTEGER NOT NULL,
    datetime CHAR(30) NOT NULL,
    PRIMARY KEY(order_id),
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE comment(
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    username VARCHAR(255) NOT NULL,
    comment VARCHAR(255) NOT NULL,
    item_id INTEGER DEFAULT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
);

INSERT INTO users VALUES ('admin1', '{noop}admin1pw','admin Yip','23382338','OU');
INSERT INTO user_roles(username, role) VALUES ('admin1', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('admin1', 'ROLE_ADMIN');
INSERT INTO users VALUES ('admin2', '{noop}admin2pw','admin Wong','24482448','OU');
INSERT INTO user_roles(username, role) VALUES ('admin2', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('admin2', 'ROLE_ADMIN');
INSERT INTO users VALUES ('user1', '{noop}user1pw','Ken Wong','88888888','OU');
INSERT INTO user_roles(username, role) VALUES ('user1', 'ROLE_USER');
INSERT INTO users VALUES ('user2', '{noop}user2pw','Andy Kwok','95951313','OU');
INSERT INTO user_roles(username, role) VALUES ('user2', 'ROLE_USER');

INSERT INTO item(itemName,description, price, isabailability) VALUES ('Burger','This is Burger', 25, TRUE);
INSERT INTO item(itemName,description, price, isabailability) VALUES ('Hotdog', 'This is Hotdog',30, TRUE);
INSERT INTO item(itemName,description, price, isabailability) VALUES ('Coke', 'This is Coke',10, TRUE);

INSERT INTO history(username,item_id,quantity,datetime) VALUES ('user1', 1,3, '2021-05-05T22:57:12.504');
INSERT INTO history(username,item_id,quantity,datetime) VALUES ('user1', 2,1, '2021-05-06T22:59:10.504');
INSERT INTO history(username,item_id,quantity,datetime) VALUES ('user2', 3,1, '2021-05-06T23:58:11.504');

INSERT INTO comment(username,comment,item_id) VALUES ('user1', 'Delicious!', 1);
INSERT INTO comment(username,comment,item_id) VALUES ('user2', 'Delicious +1', 1);
INSERT INTO comment(username,comment,item_id) VALUES ('user2', 'Bad', 2);