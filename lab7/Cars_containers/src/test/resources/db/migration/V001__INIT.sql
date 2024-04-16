CREATE TABLE cars (
                      id BIGSERIAL PRIMARY KEY,
                      maker VARCHAR(255),
                      model VARCHAR(255)
);

insert into cars (maker, model) values ('Ford', 'F150');
insert into cars (maker, model) values ('Ford', 'Lightning');
insert into cars (maker, model) values ('Polestar', '2');
insert into cars (maker, model) values ('Ferrari', 'La Ferrari');
insert into cars (maker, model) values ('Renault', 'Zoe');