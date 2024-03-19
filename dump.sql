-- we don't know how to generate root <with-no-name> (class Root) :(

comment on database postgres is 'default administrative connection database';

grant connect, create, temporary on database club_carrier to bartoszptaszynski;

grant connect, create, temporary on database testlogin to bartoszptaszynski;

grant connect, create, temporary on database logindb to bartoszptaszynski;

create table clubs
(
    id        uuid        not null
        constraint "CLUBS_pkey"
            primary key,
    name      varchar(30) not null,
    rating    integer     not null,
    value     integer     not null,
    funds     integer     not null,
    player_id uuid        not null
        constraint "CLUBS_player_id_key"
            unique
);

alter table clubs
    owner to bartoszptaszynski;

create table footballers
(
    id      uuid        not null
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
    id            uuid not null
        constraint "CLUB_FOOTBALLERS_pkey"
            primary key,
    club_id       uuid not null
        constraint "Club-Footballer_Club_FK"
            references clubs,
    footballer_id uuid not null
        constraint "Club-Footballer_Footballer_FK"
            references footballers
);

alter table club_footballers
    owner to bartoszptaszynski;

create table matches
(
    id             uuid    not null
        constraint "MATCHES_pkey"
            primary key,
    hostteamscore  integer not null,
    guestteamscore integer not null,
    hostclub_id    uuid    not null
        constraint "MATCHES_hostclub_id_fkey"
            references clubs
        constraint match_club_fk
            references clubs,
    guestclub_id   uuid    not null
        constraint "MATCHES_guestclub_id_fkey"
            references clubs
        constraint match_club_fkv1
            references clubs
);

alter table matches
    owner to bartoszptaszynski;

create table players
(
    id       uuid         not null
        constraint "PLAYERS_pkey"
            primary key,
    email    varchar(50)  not null,
    password varchar(200) not null,
    username varchar(25)  not null,
    club_id  uuid
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
    player_id_1 uuid not null
        constraint "PLAYER_FRIENDS_player_id_1_fkey"
            references players
        constraint player_friend_player_fk
            references players,
    player_id_2 uuid not null
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
    id             uuid        not null
        constraint "POSITIONS_pkey"
            primary key,
    nameofposition varchar(30) not null,
    shortcut       varchar(3)  not null
);

alter table positions
    owner to bartoszptaszynski;

create table footballer_positions
(
    footballer_id uuid not null
        constraint "Footballer_FK"
            references footballers,
    position_id   uuid not null
        constraint "Position_FK"
            references positions,
    constraint "FOOTBALLER_POSITIONS_pkey"
        primary key (footballer_id, position_id)
);

alter table footballer_positions
    owner to bartoszptaszynski;


