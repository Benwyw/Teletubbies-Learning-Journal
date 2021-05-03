CREATE TABLE users(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE user_roles(
    user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO users VALUES ('admin', '{noop}adminpw');
INSERT INTO user_roles(username, role) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO users VALUES ('user1', '{noop}user1pw');
INSERT INTO users VALUES ('user2', '{noop}user2pw');
INSERT INTO user_roles(username, role) VALUES ('user1', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('user2', 'ROLE_USER');