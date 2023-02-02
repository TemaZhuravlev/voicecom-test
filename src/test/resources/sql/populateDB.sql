DELETE FROM client;
DELETE FROM manager;

INSERT INTO manager (id, full_name, phone_number, alternate_id)
VALUES (1, 'Ivan Ivanov', '79211111111', null),
       (2, 'Petr Petrov', '79212222222', 3),
       (3, 'Sveta Svetikova', '79213333333', 1);

alter sequence manager_id_seq restart with 4;

INSERT INTO client (id, uuid, full_name, legal_address, enabled, manager_id)
VALUES (1, 'f9cb823d-8a7c-4ccd-8581-e810d42af737', 'LLC Google', 'USA', true, 1),
       (2, 'f9cb823d-8a7c-4ccd-8584-e810d42af842', 'LLC Airbus', 'Europa', true, 2),
       (3, 'f9cb823d-8a7c-4ccd-8387-e810d42af658', 'LLC Toyota', 'Japan', true, 3);

alter sequence client_id_seq restart with 4;