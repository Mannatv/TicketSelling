INSERT INTO tickets (name, price, seatNumber, date, time)
VALUES
    ('Ram',200.00,'A120','2023-07-10', '01:00:00'),
    ('Rohan',200.00, 'A201','2023-07-10','01:00:00'),
    ('Ajay',210.00, 'A050','2023-07-10','01:00:00'),
    ('Mohammad',250.00, 'B340','2023-07-10','01:00:00'),
    ('Sahil',230.00, 'B290','2023-07-10','01:00:00'),
    ('Gurinder',300.00, 'A023','2023-07-11','03:00:00'),
    ('Naresh',200.00, 'D123','2023-07-11','03:00:00'),
    ('Kaptan',300.00, 'A009','2023-07-11','03:00:00'),
    ('Megha',280.00, 'C200','2023-07-11','03:00:00'),
    ('Jatin',200.00, 'D234','2023-07-11','03:00:00');

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$RI8v7P08RlXU68x2DWMJYOK0yT9wraJ5yDm5zm5bgdSLprP0RBLJ6', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('ajay', '$2a$10$RI8v7P08RlXU68x2DWMJYOK0yT9wraJ5yDm5zm5bgdSLprP0RBLJ6', 1),
       ('jerry', '$2a$10$RI8v7P08RlXU68x2DWMJYOK0yT9wraJ5yDm5zm5bgdSLprP0RBLJ6', 1),
       ('tom', '$2a$10$RI8v7P08RlXU68x2DWMJYOK0yT9wraJ5yDm5zm5bgdSLprP0RBLJ6', 1),
       ('alex', '$2a$10$RI8v7P08RlXU68x2DWMJYOK0yT9wraJ5yDm5zm5bgdSLprP0RBLJ6', 1),
       ('ram', '$2a$10$RI8v7P08RlXU68x2DWMJYOK0yT9wraJ5yDm5zm5bgdSLprP0RBLJ6', 1);


insert into sec_role (roleName)
values ('VENDOR');

insert into sec_role (roleName)
values ('GUEST');

insert into user_role (userId, roleId)
values (1, 1);


insert into user_role (userId, roleId)
values (2, 2);

insert into user_role (userId, roleId)
values (3, 2);

insert into user_role (userId, roleId)
values (4, 2);

insert into user_role (userId, roleId)
values (5, 2);

insert into user_role (userId, roleId)
values (6, 2);





