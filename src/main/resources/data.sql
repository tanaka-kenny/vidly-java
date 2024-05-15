insert into customer (uuid, is_gold, name) values ('41913f47-a87f-4cf7-a03b-0f7b9ff1d583', TRUE, 'Sheldon Cooper');
insert into customer (uuid, is_gold, name) values ('c951a27d-71fb-49c1-b6ec-5023227dce08', TRUE, 'Jane Doe');
insert into customer (uuid, is_gold, name) values ('c97f7e4f-cf0e-40a6-bc83-953df70a8c9f', FALSE, 'Mary Jane');

insert into genre (uuid, name) values ('b40c8406-af65-4f79-912c-dc587bac9ed5', 'Comedy');
insert into genre (uuid, name) values ('172e1213-9e12-4a7f-9c76-283161fb3f2c', 'Fiction');
insert into genre (uuid, name) values ('b40c8406-af65-4f79-912c-dc587bac9ed5', 'Documentary');
insert into genre (uuid, name) values ('9a641836-1648-4ae7-a2c7-8558cc315510', 'Animation');

insert into movie (uuid, title, number_in_stock, daily_rental_rate, genre_id) values ('c24237cd-fd1b-4275-8d5a-f16bc0697819', 'Shrek', 20, 5, 4);
insert into movie (uuid, title, number_in_stock, daily_rental_rate, genre_id) values ('5a01ef04-5621-4307-ad4b-dc7ce614a535', 'Tekken', 30, 3, 2);
insert into movie (uuid, title, number_in_stock, daily_rental_rate, genre_id) values ('7c831e68-8e2d-4bd8-b115-f95d9208e36', 'Grown ups', 15, 3, 1);




