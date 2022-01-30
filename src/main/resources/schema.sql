create table if not exists PHONE
(
    phone_id     binary not null,
    city_code    varchar(255),
    country_code varchar(255),
    number       varchar(255),
    user_id      binary,
    primary key (phone_id)
);
create table if not exists USER
(
    user_id    binary  not null,
    active     boolean not null,
    created    timestamp,
    email      varchar(255),
    last_login timestamp,
    modified   timestamp,
    name       varchar(255),
    password   varchar(255),
    token      varchar(255),
    primary key (user_id)
);
alter table USER
    add constraint if not exists UK_email unique (email);
alter table PHONE
    add constraint if not exists FK_user_phone foreign key (user_id) references user;
