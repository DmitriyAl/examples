drop table if exists addresses;
drop table if exists users;

create table users
(
    id   bigserial primary key,
    name varchar not null,
    age  int     not null
);

create table addresses
(
    id          bigserial primary key,
    country     varchar not null,
    city        varchar not null,
    street      varchar not null,
    home_number int     not null,
    flat_number int     not null,
    user_id     bigint references users (id)
);