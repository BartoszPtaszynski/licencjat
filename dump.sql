create table clubs
(
    id        bigint                                             not null
        constraint "CLUBS_pkey"
            primary key,
    name      varchar(30)                                      not null,
    rating    integer                                          not null,
    value     integer                                          not null,
    funds     integer                                          not null,
    player_id bigint                                             not null
        constraint "CLUBS_player_id_key"
            unique,
    formation varchar(15) default '1-4-3-3'::character varying not null,
    crest     varchar(15)                                      not null
);

alter table clubs
    owner to bartoszptaszynski;

create table footballers
(
    id      bigint        not null
        constraint "FOOTBALLERS_pkey"
            primary key,
    name    varchar(30) not null,
    surname varchar(30) not null,
    rating  integer     not null,
    value   integer     not null
);

alter table footballers
    owner to bartoszptaszynski;

create table club_footballers
(
    id            bigint not null
        constraint "CLUB_FOOTBALLERS_pkey"
            primary key,
    club_id       bigint not null
        constraint "Club-Footballer_Club_FK"
            references clubs,
    footballer_id bigint not null
        constraint "Club-Footballer_Footballer_FK"
            references footballers,
    position varchar(15) not null
);

alter table club_footballers
    owner to postgres;

create table matches
(
    id             bigint    not null
        constraint "MATCHES_pkey"
            primary key,
    host_team_score  integer not null,
    guest_team_score integer not null,
    host_club_id    bigint    not null
        constraint "MATCHES_hostclub_id_fkey"
            references clubs
        constraint match_club_fk
            references clubs,
    guest_club_id   bigint    not null
        constraint "MATCHES_guestclub_id_fkey"
            references clubs
        constraint match_club_fkv1
            references clubs
);

alter table matches
    owner to bartoszptaszynski;

create table players
(
    id       bigint         not null
        constraint "PLAYERS_pkey"
            primary key,
    email    varchar(50)  not null,
    password varchar(200) not null,
    username varchar(25)  not null,
    club_id  bigint
        constraint "PLAYERS_club_id_fkey"
            references clubs
        constraint club_fk
            references clubs
);

alter table players
    owner to bartoszptaszynski;

alter table clubs
    add constraint club_player_fk
        foreign key (player_id) references players;

create table player_friends
(
    player_id_1 bigint not null
        constraint "PLAYER_FRIENDS_player_id_1_fkey"
            references players
        constraint player_friend_player_fk
            references players,
    player_id_2 bigint not null
        constraint "PLAYER_FRIENDS_player_id_2_fkey"
            references players
        constraint player_friend_player_fkv2
            references players,
    constraint "PLAYER_FRIENDS_pkey"
        primary key (player_id_1, player_id_2)
);

alter table player_friends
    owner to bartoszptaszynski;

create table positions
(
    id             bigint        not null
        constraint "POSITIONS_pkey"
            primary key,
    name_of_position varchar(30) not null,
    shortcut       varchar(3)  not null
);

alter table positions
    owner to bartoszptaszynski;

create table footballer_positions
(
    footballer_id bigint not null
        constraint "Footballer_FK"
            references footballers,
    position_id   bigint not null
        constraint "Position_FK"
            references positions,
    constraint "FOOTBALLER_POSITIONS_pkey"
        primary key (footballer_id, position_id)
);

alter table footballer_positions
    owner to bartoszptaszynski;



CREATE SEQUENCE player_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER TABLE players
    alter COLUMN id  set  DEFAULT nextval('player_seq')  ;


GRANT USAGE, SELECT, UPDATE ON SEQUENCE player_seq TO bartoszptaszynski;


CREATE SEQUENCE club_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;



ALTER TABLE clubs
    alter COLUMN id  set  DEFAULT nextval('club_seq')  ;


GRANT USAGE, SELECT, UPDATE ON SEQUENCE club_seq TO bartoszptaszynski;

