DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS manager;

CREATE TABLE manager
(
    id           SERIAL PRIMARY KEY,
    full_name    VARCHAR(128) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    alternate_id INTEGER,
    CONSTRAINT manager_phone_number_idx UNIQUE (phone_number)
);

CREATE SEQUENCE manager_id_seq AS INTEGER START WITH 1;

CREATE TABLE client
(
    id            SERIAL PRIMARY KEY,
    uuid          UUID              NOT NULL,
    full_name     VARCHAR(128)      NOT NULL,
    legal_address VARCHAR(128)      NOT NULL,
    enabled       BOOL DEFAULT TRUE NOT NULL,
    manager_id    INTEGER           NOT NULL,
    FOREIGN KEY (manager_id) REFERENCES manager (id)
);

CREATE SEQUENCE client_id_seq AS INTEGER START WITH 1;

