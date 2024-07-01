create table public.clubs
(
    id        bigint  not null
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
    id                     bigint  not null
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
    id       bigint  not null
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
    id            bigint  not null
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


--sequences
create sequence  club_footballers_seq
    increment  by 1
    no minvalue
    no maxvalue
    start with  1;

alter table club_footballers
    alter column id set default  nextval('club_footballers_seq');

create sequence  club_seq
    increment  by 1
    no minvalue
    no maxvalue
    start with  1;

alter table clubs
    alter column id set default  nextval('club_seq');


create sequence  match_seq
    increment  by 1
    no minvalue
    no maxvalue
    start with  1;

alter table matches
    alter column id set default  nextval('match_seq');

create sequence  player_seq
    increment  by 1
    no minvalue
    no maxvalue
    start with  1;

alter table players
    alter column id set default  nextval('player_seq');



-- footballers
INSERT INTO footballers (id, name, surname, rating, value) VALUES (1, 'Ahmed', 'Abdullah', 25, 22000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (2, 'Elena', 'Gomez', 6, 4500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (3, 'Yuki', 'Tanaka', 38, 42000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (4, 'Hassan', 'Ali', 15, 11000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (5, 'Javier', 'Rodriguez', 45, 62000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (6, 'Sven', 'Jensen', 3, 3000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (7, 'Ling', 'Chen', 20, 16000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (8, 'Alessandro', 'Martinez', 50, 78000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (9, 'Nadia', 'Khan', 12, 8000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (10, 'Olga', 'Ivanova', 8, 5200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (11, 'Tariq', 'Ahmad', 32, 34000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (12, 'Liam', 'Murphy', 2, 2000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (13, 'Isabella', 'Silva', 18, 14000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (14, 'Cheng', 'Wang', 42, 46000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (15, 'Rakesh', 'Patel', 22, 26000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (16, 'Lucas', 'Santos', 7, 5200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (17, 'Anna', 'Lopez', 13, 9000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (18, 'Abdul', 'Khan', 28, 27000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (19, 'Hiroshi', 'Yamamoto', 40, 47000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (20, 'Leila', 'Mohammed', 9, 5600);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (21, 'Piotr', 'Kowalski', 16, 12000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (22, 'Tomas', 'Novak', 34, 38000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (23, 'Youssef', 'Amin', 47, 54000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (24, 'Marta', 'Garcia', 11, 7500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (25, 'Kenji', 'Sato', 26, 24000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (26, 'Anastasia', 'Petrova', 4, 3700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (27, 'Carlos', 'Fernandez', 19, 13000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (29, 'Sofia', 'Iglesias', 36, 41000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (30, 'Fumio', 'Nakamura', 14, 9800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (31, 'Omar', 'Hassan', 48, 58000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (32, 'Julia', 'Novakova', 23, 20000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (33, 'Mohamed', 'El Said', 5, 3900);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (34, 'Eva', 'Müller', 17, 12500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (35, 'Rajesh', 'Singh', 29, 29000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (36, 'Anna', 'Kuznetsova', 43, 48000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (37, 'Sebastian', 'Gomez', 21, 17000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (38, 'Mei', 'Li', 37, 43000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (39, 'Pavel', 'Kováč', 10, 6800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (40, 'Lina', 'Gonzalez', 24, 23000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (41, 'Akio', 'Yamada', 39, 45000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (42, 'Mikhail', 'Ivanov', 8, 5500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (44, 'Antonio', 'Moreno', 44, 50000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (45, 'Erika', 'Kato', 19, 13800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (46, 'Hassan', 'Chaudhry', 32, 31000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (47, 'Tatiana', 'Petrovna', 47, 56000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (48, 'Rahul', 'Sharma', 13, 9200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (49, 'María', 'López', 28, 28000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (50, 'Hiroki', 'Tanaka', 41, 46000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (51, 'Cristina', 'Santos', 15, 10500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (53, 'Yuki', 'Yamamoto', 7, 4800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (54, 'Alexandre', 'Silva', 22, 19000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (55, 'Fatima', 'Mohammed', 37, 40000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (56, 'Arjun', 'Patel', 49, 58000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (57, 'Isabel', 'García', 16, 11500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (58, 'Sergei', 'Pavlov', 33, 35000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (59, 'Yusuf', 'Khan', 9, 6300);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (60, 'Mia', 'Johnson', 42, 49000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (61, 'Tariq', 'Ahmad', 11, 7200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (62, 'Sara', 'Ali', 25, 24000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (63, 'Luca', 'Rossi', 38, 42000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (64, 'Hiroshi', 'Nakamura', 14, 9700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (65, 'Anya', 'Ivanova', 29, 29000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (66, 'Amir', 'Khan', 46, 55000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (67, 'Yuna', 'Kim', 17, 12500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (68, 'Luis', 'Gonzalez', 31, 31000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (69, 'Elena', 'Petrova', 43, 47000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (70, 'Mohammed', 'Abdul', 18, 13500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (71, 'Anna', 'Kuznetsova', 23, 21000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (72, 'Ravi', 'Patel', 34, 36000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (73, 'Xiao', 'Chen', 19, 14000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (75, 'Javier', 'Lopez', 22, 18000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (76, 'Leila', 'Mohammed', 9, 6400);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (77, 'Igor', 'Popov', 36, 40000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (78, 'Sara', 'Almeida', 47, 53000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (80, 'Lena', 'Kovač', 13, 8800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (81, 'Abdullah', 'Khan', 30, 30000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (82, 'Yasmin', 'Ahmed', 44, 51000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (83, 'Juan', 'García', 21, 16000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (84, 'Aya', 'Nakamura', 39, 44000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (85, 'Maxim', 'Ivanov', 7, 5200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (87, 'Satoshi', 'Yamada', 24, 22000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (88, 'Maria', 'Silva', 40, 46000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (90, 'Elena', 'Ivanova', 35, 39000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (91, 'Raj', 'Singh', 49, 59000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (92, 'Olga', 'Petrova', 15, 10800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (93, 'Ali', 'Mohammad', 28, 27000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (94, 'Hiroshi', 'Tanaka', 41, 47000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (95, 'Yan', 'Wang', 18, 13000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (96, 'Anya', 'Kuznetsova', 33, 33000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (97, 'Amir', 'Ali', 50, 80000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (98, 'Luis', 'Martinez', 20, 15000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (99, 'Maria', 'Gomez', 6, 4800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (100, 'Mateusz', 'Nowak', 10, 6000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (101, 'Ahmed', 'Abdullah', 25, 22000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (102, 'Elena', 'Gomez', 6, 4500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (103, 'Yuki', 'Tanaka', 38, 42000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (104, 'Hassan', 'Ali', 15, 11000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (105, 'Javier', 'Rodriguez', 45, 62000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (106, 'Sven', 'Jensen', 3, 3000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (107, 'Ling', 'Chen', 20, 16000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (109, 'Nadia', 'Khan', 12, 8000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (110, 'Olga', 'Ivanova', 8, 5200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (111, 'Tariq', 'Ahmad', 32, 34000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (112, 'Liam', 'Murphy', 2, 2000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (113, 'Isabella', 'Silva', 18, 14000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (114, 'Cheng', 'Wang', 42, 46000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (115, 'Rakesh', 'Patel', 22, 26000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (116, 'Lucas', 'Santos', 7, 5200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (117, 'Anna', 'Lopez', 13, 9000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (118, 'Abdul', 'Khan', 28, 27000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (119, 'Hiroshi', 'Yamamoto', 40, 47000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (120, 'Alicja', 'Nowak', 3, 3000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (121, 'Mohamed', 'Abdullah', 7, 5500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (123, 'Kenji', 'Tanaka', 8, 7000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (124, 'Hassan', 'Ali', 2, 2500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (125, 'Javier', 'Rodriguez', 9, 9000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (126, 'Sven', 'Jensen', 1, 2000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (127, 'Ling', 'Chen', 6, 5000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (129, 'Nadia', 'Khan', 5, 3800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (130, 'Olga', 'Ivanova', 3, 3200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (131, 'Tariq', 'Ahmad', 8, 6900);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (132, 'Liam', 'Murphy', 2, 2400);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (133, 'Isabella', 'Silva', 7, 8500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (134, 'Cheng', 'Wang', 9, 9700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (74, 'Aisha', 'Farsi', 48, 57000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (28, 'Abdullah', 'Farsi', 31, 32000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (86, 'Fatima', 'Hassan', 45, 54000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (108, 'Alessandr', 'Martinez', 50, 78000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (128, 'Alex', 'Martinez', 10, 10000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (135, 'Rakesh', 'Patel', 6, 5300);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (136, 'Lucas', 'Santos', 4, 4700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (137, 'Anna', 'Lopez', 5, 6000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (138, 'Abdul', 'Khan', 8, 7800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (139, 'Hiroshi', 'Yamamoto', 10, 11000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (140, 'Elena', 'Gomez', 15, 12000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (141, 'Mateo', 'Hernandez', 12, 9500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (142, 'Satoshi', 'Sato', 18, 17000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (143, 'Sofia', 'Abdullah', 11, 7800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (144, 'Hassan', 'Ahmed', 19, 19000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (145, 'Yuka', 'Yamamoto', 13, 10500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (146, 'Juan', 'Martinez', 14, 11500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (147, 'Lila', 'Garcia', 17, 14500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (148, 'Tarek', 'Mohammed', 16, 13000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (149, 'Aisha', 'Ali', 20, 20000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (150, 'Hiro', 'Nakamura', 11, 7500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (151, 'Eva', 'Fernandez', 19, 18000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (152, 'Luka', 'Ivanov', 13, 11000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (153, 'Marie', 'Dupont', 15, 12500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (154, 'Yassin', 'Khaled', 17, 15500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (155, 'Aiko', 'Takahashi', 12, 9000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (156, 'Luca', 'Ricci', 16, 13500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (157, 'Nina', 'Kuznetsova', 14, 11800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (158, 'Raj', 'Patel', 20, 19500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (159, 'Amina', 'Omar', 18, 16500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (160, 'Ahmed', 'Hassan', 16, 13000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (161, 'Mia', 'Moreno', 12, 9200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (162, 'Yoshi', 'Tanaka', 19, 18500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (163, 'Fatima', 'Abdullah', 13, 10200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (164, 'Takashi', 'Suzuki', 18, 16000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (165, 'Emma', 'Garcia', 15, 12500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (166, 'Alessio', 'Romano', 17, 14000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (167, 'Leila', 'Ahmad', 14, 11000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (168, 'Sara', 'Khan', 20, 20000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (170, 'Lila', 'Ibrahim', 16, 13500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (171, 'Luis', 'Gonzalez', 13, 9800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (172, 'Marta', 'Perez', 19, 18000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (173, 'Tarek', 'Mohamed', 15, 12000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (174, 'Anna', 'Kowalczyk', 17, 14500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (175, 'Yusuf', 'Ali', 12, 9000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (176, 'Chihiro', 'Yamamoto', 18, 15500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (177, 'Amina', 'Said', 14, 11500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (178, 'Elena', 'Gomez', 20, 19500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (179, 'Raj', 'Singh', 11, 7200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (180, 'Hassan', 'Mohammed', 2, 2700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (181, 'Lina', 'Ahmed', 5, 4300);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (182, 'Takashi', 'Suzuki', 8, 6900);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (183, 'Eva', 'Garcia', 4, 3800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (184, 'Juan', 'Martinez', 7, 5500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (185, 'Lila', 'Perez', 3, 3200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (186, 'Alessio', 'Romano', 6, 5100);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (187, 'Leila', 'Ahmad', 1, 2000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (188, 'Sara', 'Khan', 9, 8100);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (190, 'Lila', 'Ibrahim', 1, 2300);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (191, 'Luis', 'Gonzalez', 5, 4700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (192, 'Marta', 'Perez', 7, 5800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (193, 'Tarek', 'Mohamed', 4, 3500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (194, 'Anna', 'Kowalczyk', 6, 5300);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (195, 'Yusuf', 'Ali', 2, 2500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (196, 'Chihiro', 'Yamamoto', 8, 6700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (197, 'Amina', 'Said', 3, 3100);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (198, 'Elena', 'Gomez', 9, 7900);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (199, 'Raj', 'Singh', 10, 9700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (200, 'Aisha', 'Mohammed', 3, 3100);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (201, 'Mikhail', 'Ivanov', 7, 5500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (202, 'Lina', 'Gomez', 4, 4200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (203, 'Kenji', 'Tanaka', 8, 7000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (204, 'Hassan', 'Ali', 2, 2500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (205, 'Javier', 'Rodriguez', 9, 9000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (206, 'Sven', 'Jensen', 1, 2000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (207, 'Ling', 'Chen', 6, 5000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (209, 'Nadia', 'Khan', 5, 3800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (210, 'Olga', 'Ivanova', 3, 3200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (211, 'Tariq', 'Ahmad', 8, 6900);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (212, 'Liam', 'Murphy', 2, 2400);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (213, 'Isabella', 'Silva', 7, 8500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (214, 'Cheng', 'Wang', 9, 9700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (215, 'Rakesh', 'Patel', 6, 5300);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (216, 'Lucas', 'Santos', 4, 4700);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (217, 'Anna', 'Lopez', 5, 6000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (218, 'Abdul', 'Khan', 8, 7800);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (219, 'Hiroshi', 'Yamamoto', 10, 11000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (122, 'Lina', 'Gomez', 4, 4200);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (89, 'Ahmed', 'Mansouri', 10, 7000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (189, 'Ahmad', 'Mansour', 10, 9900);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (52, 'Khaled', 'Mansoori', 26, 25000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (169, 'Ahmad', 'Mansour', 11, 7500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (79, 'Ahmed', 'Saud', 26, 26000);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (43, 'Aisha', 'Hassan', 12, 8500);
INSERT INTO footballers (id, name, surname, rating, value) VALUES (208, 'Alan', 'Martinez', 10, 10000);

-- positions
INSERT INTO positions (id, name_of_position, shortcut) VALUES (1, 'LEWY NAPASTNIK', 'LN');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (2, 'PRAWY NAPASTNIK', 'PN');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (3, 'LEWY POMOCNIK', 'LP');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (4, 'LEWY ŚRODKOWY POMOCNIK', 'LŚP');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (5, 'PRAWY ŚRODKOWY POMOCNIK', 'PŚP');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (6, 'PRAWY POMOCNIK', 'PP');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (7, 'LEWY OBROŃCA', 'LO');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (8, 'LEWY ŚRODKOWY OBROŃCA', 'LŚO');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (9, 'PRAWY ŚRODKOWY OBROŃCA', 'PŚO');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (10, 'PRAWY OBROŃCA', 'PO');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (11, 'BRAMKARZ', 'BR');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (12, 'LEWY SKRZYDŁOWY', 'LS');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (13, 'PRAWY SKRZYDŁOWY', 'PS');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (14, 'ŚRODKOWY POMOCNIK', 'ŚP');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (15, 'ŚRODKOWY OBROŃCA', 'ŚO');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (16, 'NAPASTNIK', 'N');
INSERT INTO positions (id, name_of_position, shortcut) VALUES (17, 'REZERWOWY', 'R');


--footballer_positions
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (1, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (1, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (2, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (2, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (3, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (3, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (4, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (4, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (5, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (5, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (6, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (6, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (7, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (7, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (8, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (8, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (9, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (9, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (10, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (10, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (11, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (11, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (12, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (12, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (13, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (13, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (14, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (14, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (14, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (15, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (15, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (15, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (16, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (17, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (17, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (18, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (18, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (19, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (19, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (20, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (21, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (22, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (22, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (23, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (23, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (24, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (24, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (25, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (25, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (26, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (26, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (27, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (27, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (28, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (28, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (29, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (29, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (30, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (30, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (31, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (31, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (32, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (32, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (33, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (33, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (34, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (34, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (35, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (36, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (36, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (36, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (37, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (37, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (38, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (38, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (39, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (40, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (40, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (40, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (41, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (41, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (42, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (42, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (43, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (43, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (43, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (44, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (44, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (44, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (45, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (45, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (46, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (46, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (47, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (48, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (48, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (49, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (49, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (49, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (50, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (50, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (50, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (51, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (52, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (51, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (52, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (53, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (53, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (54, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (54, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (55, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (56, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (57, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (57, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (58, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (58, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (59, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (59, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (59, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (60, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (60, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (61, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (61, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (61, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (66, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (66, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (66, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (67, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (67, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (67, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (68, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (68, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (68, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (69, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (69, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (69, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (70, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (70, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (70, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (71, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (71, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (71, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (72, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (72, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (72, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (73, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (73, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (74, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (74, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (74, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (75, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (75, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (76, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (76, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (77, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (77, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (77, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (78, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (78, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (79, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (79, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (80, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (80, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (80, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (81, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (81, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (81, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (82, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (83, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (83, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (83, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (84, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (84, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (84, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (85, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (86, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (86, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (86, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (87, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (87, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (87, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (88, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (88, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (88, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (89, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (89, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (89, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (90, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (90, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (90, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (91, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (91, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (91, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (92, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (92, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (92, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (93, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (93, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (93, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (94, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (94, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (95, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (95, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (96, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (96, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (96, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (97, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (97, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (98, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (98, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (99, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (100, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (100, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (101, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (101, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (101, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (102, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (103, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (103, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (103, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (104, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (104, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (104, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (105, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (106, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (106, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (106, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (107, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (107, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (107, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (108, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (108, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (108, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (109, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (109, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (109, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (110, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (110, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (110, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (111, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (111, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (111, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (112, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (112, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (112, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (113, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (113, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (113, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (114, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (114, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (115, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (115, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (116, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (116, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (116, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (117, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (117, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (118, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (118, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (119, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (120, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (120, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (121, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (121, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (121, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (122, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (123, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (123, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (123, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (124, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (124, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (124, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (125, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (126, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (126, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (126, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (127, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (127, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (127, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (128, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (128, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (128, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (129, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (129, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (129, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (130, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (130, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (130, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (131, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (131, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (131, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (132, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (132, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (132, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (133, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (133, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (133, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (134, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (134, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (135, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (135, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (136, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (136, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (136, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (137, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (137, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (138, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (138, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (139, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (140, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (140, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (141, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (141, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (141, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (142, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (143, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (143, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (143, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (144, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (144, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (144, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (145, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (146, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (146, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (146, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (147, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (147, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (147, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (148, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (148, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (148, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (149, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (149, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (149, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (150, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (150, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (150, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (151, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (151, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (151, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (152, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (152, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (152, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (153, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (153, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (153, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (154, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (154, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (155, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (155, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (156, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (156, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (156, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (157, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (157, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (158, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (158, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (159, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (160, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (160, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (161, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (161, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (161, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (162, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (163, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (163, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (163, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (164, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (164, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (164, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (165, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (166, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (166, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (166, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (167, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (167, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (167, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (168, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (168, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (168, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (169, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (169, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (169, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (170, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (170, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (170, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (171, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (171, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (171, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (172, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (172, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (172, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (173, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (173, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (173, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (174, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (174, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (175, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (175, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (176, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (176, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (176, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (177, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (177, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (178, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (178, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (179, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (180, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (180, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (181, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (181, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (181, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (182, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (183, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (183, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (183, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (184, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (184, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (184, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (185, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (186, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (186, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (186, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (187, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (187, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (187, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (188, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (188, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (188, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (189, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (189, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (189, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (190, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (190, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (190, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (191, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (191, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (191, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (192, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (192, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (192, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (193, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (193, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (193, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (194, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (194, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (195, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (195, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (196, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (196, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (196, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (197, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (197, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (198, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (198, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (199, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (200, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (200, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (201, 2);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (201, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (201, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (202, 11);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (203, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (203, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (203, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (204, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (204, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (204, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (205, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (206, 8);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (206, 9);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (206, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (207, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (207, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (207, 4);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (208, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (208, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (208, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (209, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (209, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (209, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (210, 14);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (210, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (210, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (211, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (211, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (211, 5);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (212, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (212, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (212, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (213, 16);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (213, 1);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (213, 12);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (214, 13);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (214, 15);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (215, 7);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (215, 3);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (216, 10);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (216, 6);
INSERT INTO footballer_positions (footballer_id, position_id) VALUES (216, 13);
