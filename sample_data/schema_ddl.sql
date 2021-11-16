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

DROP TABLE IF EXISTS public.spider CASCADE;
CREATE TABLE public.spider(
    id serial NOT NULL PRIMARY KEY,
    save_id integer NOT NULL,
    spider_hp integer NOT NULL,
    spider_attack integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL
);

DROP TABLE IF EXISTS public.cyclops CASCADE;
CREATE TABLE public.cyclops(
    id serial NOT NULL PRIMARY KEY,
    save_id integer NOT NULL,
    cyclops_hp integer NOT NULL,
    cyclops_attack integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL
);


DROP TABLE IF EXISTS public.skeleton CASCADE;
CREATE TABLE public.skeleton(
    id serial NOT NULL PRIMARY KEY,
    save_id integer NOT NULL,
    skeleton_hp integer NOT NULL,
    skeleton_attack integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL
);

DROP TABLE IF EXISTS public.remowable_walls CASCADE;
CREATE TABLE public.remowable_walls(
    id serial NOT NULL PRIMARY KEY,
    save_id integer NOT NULL,
    positions text NULL
);

ALTER TABLE ONLY public.player
    ADD CONSTRAINT fk_player_id FOREIGN KEY (save_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.spider
    ADD CONSTRAINT fk_spider_save_id FOREIGN KEY (save_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.cyclops
    ADD CONSTRAINT fk_cyclops_save_id FOREIGN KEY (save_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.skeleton
    ADD CONSTRAINT fk_skeleton_save_id FOREIGN KEY (save_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.remowable_walls
    ADD CONSTRAINT fk_remowable_walls_save_id FOREIGN KEY (save_id) REFERENCES public.game_state(id);