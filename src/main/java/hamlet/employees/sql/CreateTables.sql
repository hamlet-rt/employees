
create table `employees`
(
    `id`    bigint unsigned auto_increment,
    `first_name`  varchar (50) not null,
    `last_name`   varchar (50) not null,
    `middle_name`   varchar (50),
    `phone_number`  varchar (50),
    `address`   varchar (100),
    primary key (`id`)
);

insert into `employees` (first_name, last_name, middle_name, phone_number, address)
values ('Артём', 'Вершило', 'Сергеевич', '+7(701)569-96-77', 'Майлина 23'),
       ('Артём', 'Кузнецов', 'Игоревич', '+7(700)777-00-77', 'Пушкина 21'),
       ('Максим', 'Вершило', 'Артёмович', '+7(800)656-11-88', 'Вавилова 1');

create table `countries`
(
    `id`    bigint unsigned auto_increment,
    `code`  varchar(50) not null,
    `name` varchar(50) not null,
    primary key (`id`)
);

insert into `countries` (code, name)
values ('KZ', 'Казахстан'),
       ('RU', 'Россия'),
       ('US', 'США'),
       ('UK', 'Великобритания'),
       ('UA', 'Украина');


create table `cities`
(
    `id`    bigint unsigned auto_increment,
    `country_id` bigint unsigned,
    `name`  varchar (50) not null,
    primary key (`id`),
    foreign key (country_id) references countries(id)
);

insert into `cities` (county_id, name)
values ('2', 'Москва'),
       ('2', 'Омск'),
       ('2', 'Ленинград'),
       ('5', 'Одесса'),
       ('5', 'Киев'),
       ('5', 'Львов');
