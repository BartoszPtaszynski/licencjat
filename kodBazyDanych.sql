create sequence public.player_seq;
create sequence public.club_seq;
create sequence public.position_seq;
create sequence public.footballers_seq;
create sequence public.club_footballers_seq;
create sequence public.match_seq;


create table public.clubs
(
    id        bigint      default nextval('club_seq'::regclass) not null
        constraint "CLUBS_pkey"
            primary key,
    name      varchar(30)                                       not null,
    value     integer                                           not null,
    funds     integer                                           not null,
    player_id bigint                                            not null
        constraint "CLUBS_player_id_key"
            unique,
    formation varchar(15) default '1-4-3-3'::character varying  not null,
    league    integer
);


create table public.footballers
(
    id      bigint      not null
        constraint "FOOTBALLERS_pkey"
            primary key,
    name    varchar(30) not null,
    surname varchar(30) not null,
    rating  integer     not null,
    value   integer     not null
);


create table public.matches
(
    id                     bigint default nextval('match_seq'::regclass) not null
        constraint "MATCHES_pkey"
            primary key,
    host_team_score        integer                                       not null,
    guest_team_score       integer                                       not null,
    host_club_id           bigint                                        not null
        constraint "MATCHES_hostclub_id_fkey"
            references public.clubs
        constraint match_club_fk
            references public.clubs,
    guest_club_id          bigint                                        not null
        constraint "MATCHES_guestclub_id_fkey"
            references public.clubs
        constraint match_club_fkv1
            references public.clubs,
    league                 integer                                       not null,
    host_collected_money   integer                                       not null,
    guest_collected_money  integer                                       not null,
    host_club_rating       integer                                       not null,
    guest_club_rating      integer                                       not null,
    host_collected_points  integer                                       not null,
    guest_collected_points integer                                       not null
);



create table public.players
(
    id       bigint default nextval('player_seq'::regclass) not null
        constraint "PLAYERS_pkey"
            primary key,
    email    varchar(50)                                    not null,
    password varchar(200)                                   not null,
    username varchar(25)                                    not null,
    club_id  bigint
        constraint "PLAYERS_club_id_fkey"
            references public.clubs
        constraint club_fk
            references public.clubs
);


alter table public.clubs
    add constraint club_player_fk
        foreign key (player_id) references public.players;

create table public.player_friends
(
    player_id_1 bigint not null
        constraint "PLAYER_FRIENDS_player_id_1_fkey"
            references public.players
        constraint player_friend_player_fk
            references public.players,
    player_id_2 bigint not null
        constraint "PLAYER_FRIENDS_player_id_2_fkey"
            references public.players
        constraint player_friend_player_fkv2
            references public.players,
    constraint "PLAYER_FRIENDS_pkey"
        primary key (player_id_1, player_id_2)
);


create table public.positions
(
    id               bigint      not null
        constraint "POSITIONS_pkey"
            primary key,
    name_of_position varchar(30) not null,
    shortcut         varchar(3)  not null
);


create table public.club_footballers
(
    id            bigint default nextval('club_footballers_seq'::regclass) not null
        constraint "CLUB_FOOTBALLERS_pkey"
            primary key,
    club_id       bigint                                                   not null
        constraint "Club-Footballer_Club_FK"
            references public.clubs,
    footballer_id bigint                                                   not null
        constraint "Club-Footballer_Footballer_FK"
            references public.footballers,
    position_id   bigint                                                   not null
        constraint club_footballers__position_fk
            references public.positions
);




create table public.footballer_positions
(
    footballer_id bigint not null
        constraint "Footballer_FK"
            references public.footballers,
    position_id   bigint not null
        constraint "Position_FK"
            references public.positions,
    constraint "FOOTBALLER_POSITIONS_pkey"
        primary key (footballer_id, position_id)
);



