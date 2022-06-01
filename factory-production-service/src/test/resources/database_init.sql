
CREATE TABLE IF NOT EXISTS factory (
    id uuid,
    type varchar NOT NULL,
    name varchar NOT NULL,
    capacity integer NOT NULL,
    timezone varchar NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS hourly_production (
    id serial,
    factory_id uuid NOT NULL,
    start_hour timestamp with time zone NOT NULL,
    production integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (factory_id) REFERENCES factory(id)
);


-- factories
INSERT INTO factory (id, type, name, capacity, timezone) VALUES
('1d73004e-cdcf-4982-9974-7e5c74f3c6a1', 'FOOD_FACTORY', 'UK Nestle Food Factory', 20, 'Europe/London');

INSERT INTO factory (id, type, name, capacity, timezone) VALUES
('3a7be5d6-8829-4ec7-9f1e-a2b2a4f3c48f', 'FOOD_FACTORY', 'Spain Tyson Food Factory', 50, 'Europe/Madrid');

INSERT INTO factory (id, type, name, capacity, timezone) VALUES
('a18ba533-13fd-430d-a00a-5bccfab23de2', 'FOOD_FACTORY', 'California Bunge Food Factory', 100, 'America/Los_Angeles');

INSERT INTO factory (id, type, name, capacity, timezone) VALUES
('c9590ef7-ab84-4df9-a493-66d53664e0fb', 'TOY_FACTORY', 'Lego Factory', 580, 'Africa/Casablanca');

-- hourly production
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 00:00:00 America/Los_Angeles',20);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 01:00:00 America/Los_Angeles',67);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 02:00:00 America/Los_Angeles',46);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 03:00:00 America/Los_Angeles',8);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 04:00:00 America/Los_Angeles',27);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 05:00:00 America/Los_Angeles',37);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 06:00:00 America/Los_Angeles',64);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 07:00:00 America/Los_Angeles',11);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 08:00:00 America/Los_Angeles',59);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 09:00:00 America/Los_Angeles',37);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 10:00:00 America/Los_Angeles',35);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 11:00:00 America/Los_Angeles',52);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 12:00:00 America/Los_Angeles',19);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 13:00:00 America/Los_Angeles',56);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 14:00:00 America/Los_Angeles',39);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 15:00:00 America/Los_Angeles',92);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 16:00:00 America/Los_Angeles',12);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 18:00:00 America/Los_Angeles',28);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 19:00:00 America/Los_Angeles',72);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 20:00:00 America/Los_Angeles',19);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 21:00:00 America/Los_Angeles',86);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 22:00:00 America/Los_Angeles',93);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-15 23:00:00 America/Los_Angeles',49);



INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 00:00:00 America/Los_Angeles',18);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 01:00:00 America/Los_Angeles',37);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 02:00:00 America/Los_Angeles',7);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 03:00:00 America/Los_Angeles',59);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 04:00:00 America/Los_Angeles',87);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 05:00:00 America/Los_Angeles',67);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 06:00:00 America/Los_Angeles',71);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 07:00:00 America/Los_Angeles',21);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 08:00:00 America/Los_Angeles',27);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 09:00:00 America/Los_Angeles',23);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 10:00:00 America/Los_Angeles',51);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 11:00:00 America/Los_Angeles',31);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 12:00:00 America/Los_Angeles',25);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 13:00:00 America/Los_Angeles',67);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 14:00:00 America/Los_Angeles',45);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 15:00:00 America/Los_Angeles',77);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 16:00:00 America/Los_Angeles',91);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 17:00:00 America/Los_Angeles',23);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 18:00:00 America/Los_Angeles',98);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 19:00:00 America/Los_Angeles',94);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 20:00:00 America/Los_Angeles',64);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 21:00:00 America/Los_Angeles',66);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 22:00:00 America/Los_Angeles',30);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2022-01-16 23:00:00 America/Los_Angeles',30);







INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 00:00:00 America/Los_Angeles',85);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 01:00:00 America/Los_Angeles',94);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 02:00:00 America/Los_Angeles',63);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 03:00:00 America/Los_Angeles',4);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 04:00:00 America/Los_Angeles',92);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 05:00:00 America/Los_Angeles',76);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 06:00:00 America/Los_Angeles',63);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 07:00:00 America/Los_Angeles',39);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 08:00:00 America/Los_Angeles',9);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 09:00:00 America/Los_Angeles',55);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 10:00:00 America/Los_Angeles',28);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 11:00:00 America/Los_Angeles',47);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 12:00:00 America/Los_Angeles',80);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 13:00:00 America/Los_Angeles',29);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 14:00:00 America/Los_Angeles',73);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 15:00:00 America/Los_Angeles',82);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 16:00:00 America/Los_Angeles',50);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 17:00:00 America/Los_Angeles',47);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 18:00:00 America/Los_Angeles',6);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 19:00:00 America/Los_Angeles',25);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 20:00:00 America/Los_Angeles',96);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 21:00:00 America/Los_Angeles',16);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 22:00:00 America/Los_Angeles',42);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-13 23:00:00 America/Los_Angeles',30);


INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 00:00:00 America/Los_Angeles',51);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 01:00:00 America/Los_Angeles',10);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 03:00:00 America/Los_Angeles',41);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 04:00:00 America/Los_Angeles',9);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 05:00:00 America/Los_Angeles',43);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 06:00:00 America/Los_Angeles',19);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 07:00:00 America/Los_Angeles',56);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 08:00:00 America/Los_Angeles',28);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 09:00:00 America/Los_Angeles',79);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 10:00:00 America/Los_Angeles',98);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 11:00:00 America/Los_Angeles',46);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 12:00:00 America/Los_Angeles',100);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 13:00:00 America/Los_Angeles',30);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 14:00:00 America/Los_Angeles',3);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 15:00:00 America/Los_Angeles',46);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 16:00:00 America/Los_Angeles',67);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 17:00:00 America/Los_Angeles',46);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 18:00:00 America/Los_Angeles',55);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 19:00:00 America/Los_Angeles',9);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 20:00:00 America/Los_Angeles',67);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 21:00:00 America/Los_Angeles',99);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 22:00:00 America/Los_Angeles',17);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-14 23:00:00 America/Los_Angeles',44);


INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 00:00:00 America/Los_Angeles',41);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 01:00:00 America/Los_Angeles',81);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 02:00:00 America/Los_Angeles',97);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 03:00:00 America/Los_Angeles',8);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 04:00:00 America/Los_Angeles',22);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 05:00:00 America/Los_Angeles',63);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 06:00:00 America/Los_Angeles',86);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 07:00:00 America/Los_Angeles',15);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 08:00:00 America/Los_Angeles',30);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 09:00:00 America/Los_Angeles',56);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 10:00:00 America/Los_Angeles',28);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 11:00:00 America/Los_Angeles',100);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 12:00:00 America/Los_Angeles',42);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 13:00:00 America/Los_Angeles',37);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 14:00:00 America/Los_Angeles',92);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 15:00:00 America/Los_Angeles',44);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 16:00:00 America/Los_Angeles',97);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 17:00:00 America/Los_Angeles',28);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 18:00:00 America/Los_Angeles',67);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 19:00:00 America/Los_Angeles',3);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 20:00:00 America/Los_Angeles',77);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 21:00:00 America/Los_Angeles',49);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 22:00:00 America/Los_Angeles',79);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-03-15 23:00:00 America/Los_Angeles',77);


INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 00:00:00 America/Los_Angeles',75);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 01:00:00 America/Los_Angeles',73);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 02:00:00 America/Los_Angeles',0);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 03:00:00 America/Los_Angeles',53);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 04:00:00 America/Los_Angeles',97);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 05:00:00 America/Los_Angeles',20);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 06:00:00 America/Los_Angeles',17);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 07:00:00 America/Los_Angeles',96);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 08:00:00 America/Los_Angeles',30);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 09:00:00 America/Los_Angeles',65);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 10:00:00 America/Los_Angeles',78);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 11:00:00 America/Los_Angeles',77);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 12:00:00 America/Los_Angeles',5);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 13:00:00 America/Los_Angeles',27);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 14:00:00 America/Los_Angeles',47);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 15:00:00 America/Los_Angeles',96);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 16:00:00 America/Los_Angeles',95);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 17:00:00 America/Los_Angeles',20);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 18:00:00 America/Los_Angeles',18);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 19:00:00 America/Los_Angeles',25);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 20:00:00 America/Los_Angeles',44);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 21:00:00 America/Los_Angeles',26);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 22:00:00 America/Los_Angeles',83);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-06 23:00:00 America/Los_Angeles',94);


INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 00:00:00 America/Los_Angeles',35);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 01:00:00 America/Los_Angeles',89);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 01:00:00 America/Los_Angeles',28);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 02:00:00 America/Los_Angeles',4);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 03:00:00 America/Los_Angeles',58);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 04:00:00 America/Los_Angeles',37);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 05:00:00 America/Los_Angeles',62);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 06:00:00 America/Los_Angeles',84);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 07:00:00 America/Los_Angeles',61);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 08:00:00 America/Los_Angeles',46);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 09:00:00 America/Los_Angeles',1);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 10:00:00 America/Los_Angeles',65);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 11:00:00 America/Los_Angeles',3);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 12:00:00 America/Los_Angeles',13);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 13:00:00 America/Los_Angeles',58);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 14:00:00 America/Los_Angeles',86);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 15:00:00 America/Los_Angeles',66);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 16:00:00 America/Los_Angeles',91);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 17:00:00 America/Los_Angeles',82);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 18:00:00 America/Los_Angeles',35);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 19:00:00 America/Los_Angeles',73);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 20:00:00 America/Los_Angeles',45);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 21:00:00 America/Los_Angeles',33);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 22:00:00 America/Los_Angeles',3);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-07 23:00:00 America/Los_Angeles',15);


INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 00:00:00 America/Los_Angeles',50);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 01:00:00 America/Los_Angeles',45);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 02:00:00 America/Los_Angeles',16);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 03:00:00 America/Los_Angeles',42);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 04:00:00 America/Los_Angeles',16);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 05:00:00 America/Los_Angeles',83);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 06:00:00 America/Los_Angeles',33);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 07:00:00 America/Los_Angeles',3);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 08:00:00 America/Los_Angeles',40);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 09:00:00 America/Los_Angeles',91);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 10:00:00 America/Los_Angeles',7);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 11:00:00 America/Los_Angeles',28);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 12:00:00 America/Los_Angeles',93);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 13:00:00 America/Los_Angeles',66);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 14:00:00 America/Los_Angeles',24);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 15:00:00 America/Los_Angeles',2);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 16:00:00 America/Los_Angeles',99);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 17:00:00 America/Los_Angeles',33);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 18:00:00 America/Los_Angeles',84);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 19:00:00 America/Los_Angeles',34);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 20:00:00 America/Los_Angeles',71);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 21:00:00 America/Los_Angeles',4);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 22:00:00 America/Los_Angeles',84);
INSERT INTO hourly_production(factory_id, start_hour, production) VALUES ('a18ba533-13fd-430d-a00a-5bccfab23de2','2021-11-08 23:00:00 America/Los_Angeles',96);
