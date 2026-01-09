-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into garden (id, address) values(1, 'Flurstraße 17, 4470 Enns');

insert into plot (id, width, height, garden_id) values(1, 12, 3, 1);

insert into vegetable (id, name, nutritionalvalue) values(1, 'Carrot', 23);

insert into plot_vegetable (plot_id, vegetable_id) values(1, 1);

alter sequence garden_seq restart with 10;
alter sequence plot_seq restart with 10;
alter sequence vegetable_seq restart with 10;