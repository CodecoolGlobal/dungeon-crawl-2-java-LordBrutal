DROP TABLE IF EXISTS public.cyclops CASCADE;
DROP TABLE IF EXISTS public.spider CASCADE;
DROP TABLE IF EXISTS public.skeleton CASCADE;
DROP TABLE IF EXISTS public.game_state CASCADE;
CREATE TABLE public.game_state (
    id serial NOT NULL PRIMARY KEY,
    current_map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.player CASCADE;
CREATE TABLE public.player (
    id serial NOT NULL PRIMARY KEY,
    save_id integer NOT NULL,
    player_tile_name  text NOT NULL,
    saved_name text NOT NULL,
    hp integer NOT NULL,
    def integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    items text NOT NULL
);

DROP TABLE IF EXISTS public.enemy CASCADE;
CREATE TABLE public.enemy(
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    save_id integer NOT NULL,
    hp integer NOT NULL,
    attack integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL
);


DROP TABLE IF EXISTS public.remowable_walls CASCADE;
CREATE TABLE public.remowable_walls(
    id serial NOT NULL PRIMARY KEY,
    save_id integer NOT NULL,
    positions text NULL
);

INSERT INTO public.game_state (id, current_map, saved_at) VALUES (1, '1', '2021-11-17 13:57:00.168000');
INSERT INTO public.game_state (id, current_map, saved_at) VALUES (2, '2', '2021-11-17 14:26:14.502000');

INSERT INTO public.player (id, save_id, player_tile_name, saved_name, hp, def, x, y, items) VALUES (1, 1, 'player', 'tz', 10, 0, 17, 11, '');
INSERT INTO public.player (id, save_id, player_tile_name, saved_name, hp, def, x, y, items) VALUES (2, 2, 'player', 'tz', 10, 0, 26, 27, '');

INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (1, 'spider', 1, 10, 5, 18, 2);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (2, 'spider', 1, 10, 5, 2, 8);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (3, 'cyclops', 1, 10, 10, 30, 11);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (4, 'spider', 1, 10, 5, 41, 11);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (5, 'skeleton', 1, 10, 2, 6, 17);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (6, 'spider', 1, 10, 5, 26, 20);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (7, 'skeleton', 1, 10, 2, 39, 20);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (8, 'spider', 2, 10, 5, 15, 1);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (9, 'spider', 2, 10, 5, 30, 5);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (10, 'spider', 2, 10, 5, 39, 9);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (11, 'spider', 2, 10, 5, 16, 10);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (12, 'spider', 2, 10, 5, 6, 11);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (13, 'spider', 2, 10, 5, 28, 12);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (14, 'spider', 2, 10, 5, 29, 12);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (15, 'skeleton', 2, 10, 2, 39, 12);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (16, 'spider', 2, 10, 5, 16, 15);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (17, 'cyclops', 2, 10, 10, 24, 15);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (18, 'spider', 2, 10, 5, 39, 15);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (19, 'spider', 2, 10, 5, 8, 22);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (20, 'spider', 2, 10, 5, 15, 22);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (21, 'spider', 2, 10, 5, 36, 22);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (22, 'spider', 2, 10, 5, 39, 22);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (23, 'spider', 2, 10, 5, 9, 23);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (24, 'spider', 2, 10, 5, 13, 23);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (25, 'spider', 2, 10, 5, 12, 24);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (26, 'spider', 2, 10, 5, 29, 24);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (27, 'skeleton', 2, 10, 2, 37, 24);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (28, 'spider', 2, 10, 5, 13, 26);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (29, 'skeleton', 2, 10, 2, 5, 28);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (30, 'skeleton', 2, 10, 2, 36, 29);
INSERT INTO public.enemy (id, name, save_id, hp, attack, x, y) VALUES (31, 'spider', 2, 10, 5, 38, 29);

UPDATE public.remowable_walls SET save_id = 1, positions = '23,2 24,2 25,2 26,2 27,2 28,2 29,2 30,2 31,2 32,2 33,2 34,2 35,2 36,2 37,2 38,2 39,2 39,3 39,4 39,5 5,6 6,6 7,6 8,6 9,6 10,6 11,6 12,6 13,6 14,6 15,6 16,6 39,6 39,7 39,8 39,9 39,10 39,12 39,13 39,14 39,15 39,16 39,17 39,18 7,19' WHERE id = 1;
UPDATE public.remowable_walls SET save_id = 2, positions = '2,2 11,2 38,12 40,19 37,23 35,28' WHERE id = 2;

ALTER TABLE ONLY public.player
    ADD CONSTRAINT fk_player_id FOREIGN KEY (save_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.enemy
    ADD CONSTRAINT fk_enemy_save_id FOREIGN KEY (save_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.remowable_walls
    ADD CONSTRAINT fk_remowable_walls_save_id FOREIGN KEY (save_id) REFERENCES public.game_state(id);

ALTER SEQUENCE game_state_id_seq RESTART WITH 3;
ALTER SEQUENCE player_id_seq RESTART WITH 3;
ALTER SEQUENCE enemy_id_seq RESTART WITH 32;
ALTER SEQUENCE remowable_walls_id_seq RESTART WITH 3;