/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Ronaldtsai
 * Created: 2021年5月5日
 */

CREATE TABLE comment(
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    username VARCHAR(255) NOT NULL,
    comment VARCHAR(255) NOT NULL,
    item_id INTEGER DEFAULT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
);