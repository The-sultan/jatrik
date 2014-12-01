-- CREA Secuencias
	
-- Sequence: hibernate_sequence

CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE hibernate_sequence OWNER TO jatrik;

-- Sequence: seq_comentarios

CREATE SEQUENCE seq_comentarios
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_comentarios OWNER TO jatrik;

-- Sequence: seq_correos

CREATE SEQUENCE seq_correos
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_correos OWNER TO jatrik;

-- Sequence: seq_datos_jugador

CREATE SEQUENCE seq_datos_jugador
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_datos_jugador OWNER TO jatrik;

-- Sequence: seq_ejercicios

CREATE SEQUENCE seq_ejercicios
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_ejercicios OWNER TO jatrik;

-- Sequence: seq_equipos

CREATE SEQUENCE seq_equipos
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 4
  CACHE 1;
ALTER TABLE seq_equipos OWNER TO jatrik;

-- Sequence: seq_eventos

CREATE SEQUENCE seq_eventos
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_eventos OWNER TO jatrik;

-- Sequence: seq_formaciones

CREATE SEQUENCE seq_formaciones
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE seq_formaciones OWNER TO jatrik;

-- Sequence: seq_jugador_formacion

CREATE SEQUENCE seq_jugador_formacion
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 38
  CACHE 1;
ALTER TABLE seq_jugador_formacion OWNER TO jatrik;

-- Sequence: seq_jugadores

CREATE SEQUENCE seq_jugadores
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_jugadores OWNER TO jatrik;


-- Sequence: seq_liga

CREATE SEQUENCE seq_liga
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_liga OWNER TO jatrik;

-- Sequence: seq_partidos

CREATE SEQUENCE seq_partidos
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 12
  CACHE 1;
ALTER TABLE seq_partidos OWNER TO jatrik;

-- Sequence: seq_rel_partido_evento

CREATE SEQUENCE seq_rel_partido_evento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_rel_partido_evento OWNER TO jatrik;

-- Sequence: seq_transferencias

CREATE SEQUENCE seq_transferencias
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_transferencias OWNER TO jatrik;

-- Sequence: seq_usuarios

CREATE SEQUENCE seq_usuarios
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE seq_usuarios OWNER TO jatrik;

-- CREAR TABLAS

CREATE TABLE comentarios (
    id bigint NOT NULL,
    descripcion character varying(255),
    nivel bigint,
    evento_id bigint
);
ALTER TABLE comentarios OWNER TO jatrik;


CREATE TABLE correos (
    id bigint NOT NULL,
    asunto character varying(255),
    fecha timestamp without time zone,
    leido boolean,
    mensaje character varying(255),
    from_id bigint,
    to_id bigint
);
ALTER TABLE correos OWNER TO jatrik;

CREATE TABLE datos_jugador (
    id bigint NOT NULL,
    nombre character varying(255)
);
ALTER TABLE datos_jugador OWNER TO jatrik;

CREATE TABLE ejercicios (
    id bigint NOT NULL,
    descripcion character varying(255),
    ataque integer,
    defensa integer,
    porteria integer,
    tecnica integer,
    velocidad integer
);
ALTER TABLE ejercicios OWNER TO jatrik;

CREATE TABLE equipos (
    id bigint NOT NULL,
    nombre character varying(255),
    altura integer,
    estadio character varying(255),
    fondos double precision,
    latitud double precision,
    longitud double precision,
    ultimo_entrenamiento timestamp without time zone,
    formacion_id bigint,
    usuario_id bigint
);
ALTER TABLE equipos OWNER TO jatrik;

CREATE TABLE eventos (
    id bigint NOT NULL,
    comentario character varying(255),
    nombre character varying(255)
);
ALTER TABLE eventos OWNER TO jatrik;

CREATE TABLE eventos_partido (
    tipo_evento integer NOT NULL,
    id bigint NOT NULL,
    minuto integer,
    gollocal bigint,
    golvisitante bigint,
    nivel bigint,
    evento_id bigint,
    partido_id bigint,
    equipo_id bigint,
    jugador_id bigint,
    comentario_id bigint
);
ALTER TABLE eventos_partido OWNER TO jatrik;

CREATE TABLE fixture (
    liga_id bigint NOT NULL,
    partido_id bigint NOT NULL
);
ALTER TABLE fixture OWNER TO jatrik;

CREATE TABLE formaciones (
    id bigint NOT NULL,
    descriptor character varying(255)
);
ALTER TABLE formaciones OWNER TO jatrik;

CREATE TABLE habilidades (
    id bigint NOT NULL,
    descripcion character varying(255),
    tipo integer,
    valor integer
);
ALTER TABLE habilidades OWNER TO jatrik;

CREATE TABLE jugador_formacion (
    id bigint NOT NULL,
    indice integer,
    puestoformacion integer,
    formacion_id bigint,
    jugador_id bigint
);
ALTER TABLE jugador_formacion OWNER TO jatrik;


CREATE TABLE jugadores (
    id bigint NOT NULL,
    altura double precision,
    edad integer,
    en_venta boolean,
    fecha_entrenamiento timestamp without time zone,
    fechafinlesion timestamp without time zone,
    fechafinsancion timestamp without time zone,
    nombre character varying(255),
    nro_camiseta integer,
    peso double precision,
    puesto integer,
    ejercicio_id bigint,
    equipo_id bigint
);
ALTER TABLE jugadores OWNER TO jatrik;


CREATE TABLE jugadores_habilidades (
    jugador_id bigint NOT NULL,
    habilidad_id bigint NOT NULL
);
ALTER TABLE jugadores_habilidades OWNER TO jatrik;


CREATE TABLE jugadores_partido_amarilla (
    partido_id bigint NOT NULL,
    jugador_id bigint NOT NULL
);
ALTER TABLE jugadores_partido_amarilla OWNER TO jatrik;


CREATE TABLE jugadores_partido_cambiados (
    partido_id bigint NOT NULL,
    jugador_id bigint NOT NULL
);
ALTER TABLE jugadores_partido_cambiados OWNER TO jatrik;

CREATE TABLE jugadores_partido_lesionados (
    partido_id bigint NOT NULL,
    jugador_id bigint NOT NULL
);
ALTER TABLE jugadores_partido_lesionados OWNER TO jatrik;

CREATE TABLE jugadores_partido_roja (
    partido_id bigint NOT NULL,
    jugador_id bigint NOT NULL
);
ALTER TABLE jugadores_partido_roja OWNER TO jatrik;

CREATE TABLE ligas (
    id bigint NOT NULL,
    descripcion character varying(255),
    fecha_fin timestamp without time zone,
    fecha_inicio timestamp without time zone
);
ALTER TABLE ligas OWNER TO jatrik;

CREATE TABLE partidos (
    id bigint NOT NULL,
    estado integer,
    etapa integer,
    fecha_inicio timestamp without time zone,
    goles_local integer,
    goles_visitante integer,
    minuto integer,
    formacion_local_id bigint,
    formacion_visitante_id bigint,
    local_id bigint,
    visitante_id bigint
);
ALTER TABLE partidos OWNER TO jatrik;


CREATE TABLE tabla_posiciones (
    equipo_id bigint NOT NULL,
    liga_id bigint NOT NULL,
    diferencia integer,
    gf integer,
    gc integer,
    partidos_empatados integer,
    partidos_ganados integer,
    partidos_jugados integer,
    partidos_perdidos integer,
    puntos integer
);
ALTER TABLE tabla_posiciones OWNER TO jatrik;


CREATE TABLE transferencias (
    id bigint NOT NULL,
    precio double precision,
    equipo_comprador_id bigint,
    jugador_id bigint,
    equipo_vendedor_id bigint
);
ALTER TABLE transferencias OWNER TO jatrik;

CREATE TABLE usuarios (
    id bigint NOT NULL,
    email character varying(255),
    nick character varying(255),
    nombre character varying(255),
    password character varying(255),
    registrationid character varying(255),
    equipo_id bigint
);
ALTER TABLE usuarios OWNER TO jatrik;


ALTER TABLE ONLY comentarios
    ADD CONSTRAINT comentarios_pkey PRIMARY KEY (id);

ALTER TABLE ONLY correos
    ADD CONSTRAINT correos_pkey PRIMARY KEY (id);

ALTER TABLE ONLY datos_jugador
    ADD CONSTRAINT datos_jugador_pkey PRIMARY KEY (id);

ALTER TABLE ONLY ejercicios
    ADD CONSTRAINT ejercicios_pkey PRIMARY KEY (id);

ALTER TABLE ONLY equipos
    ADD CONSTRAINT equipos_pkey PRIMARY KEY (id);

ALTER TABLE ONLY eventos_partido
    ADD CONSTRAINT eventos_partido_pkey PRIMARY KEY (id);

ALTER TABLE ONLY eventos
    ADD CONSTRAINT eventos_pkey PRIMARY KEY (id);

ALTER TABLE ONLY fixture
    ADD CONSTRAINT fixture_pkey PRIMARY KEY (liga_id, partido_id);

ALTER TABLE ONLY formaciones
    ADD CONSTRAINT formaciones_pkey PRIMARY KEY (id);

ALTER TABLE ONLY habilidades
    ADD CONSTRAINT habilidades_pkey PRIMARY KEY (id);

ALTER TABLE ONLY jugador_formacion
    ADD CONSTRAINT jugador_formacion_pkey PRIMARY KEY (id);

ALTER TABLE ONLY jugadores_partido_amarilla
    ADD CONSTRAINT jugadores_partido_amarilla_pkey PRIMARY KEY (partido_id, jugador_id);

ALTER TABLE ONLY jugadores_partido_cambiados
    ADD CONSTRAINT jugadores_partido_cambiados_pkey PRIMARY KEY (partido_id, jugador_id);

ALTER TABLE ONLY jugadores_partido_lesionados
    ADD CONSTRAINT jugadores_partido_lesionados_pkey PRIMARY KEY (partido_id, jugador_id);

ALTER TABLE ONLY jugadores_partido_roja
    ADD CONSTRAINT jugadores_partido_roja_pkey PRIMARY KEY (partido_id, jugador_id);


ALTER TABLE ONLY jugadores
    ADD CONSTRAINT jugadores_pkey PRIMARY KEY (id);

ALTER TABLE ONLY ligas
    ADD CONSTRAINT ligas_pkey PRIMARY KEY (id);

ALTER TABLE ONLY partidos
    ADD CONSTRAINT partidos_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tabla_posiciones
    ADD CONSTRAINT tabla_posiciones_pkey PRIMARY KEY (equipo_id, liga_id);

ALTER TABLE ONLY transferencias
    ADD CONSTRAINT transferencias_pkey PRIMARY KEY (id);

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT uk_4yu2bn9iiadugj0p6rovphn62 UNIQUE (nick);

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);

ALTER TABLE ONLY jugadores_partido_amarilla
    ADD CONSTRAINT fk_10dkfa1bbyrt9tg3s6myp7qb3 FOREIGN KEY (jugador_id) REFERENCES jugadores(id);

ALTER TABLE ONLY equipos
    ADD CONSTRAINT fk_1o0x5sr80h00wbkrekceftc3g FOREIGN KEY (formacion_id) REFERENCES formaciones(id);

ALTER TABLE ONLY fixture
    ADD CONSTRAINT fk_26xmdcrdewkvtp42qtpa30uhs FOREIGN KEY (liga_id) REFERENCES ligas(id);

ALTER TABLE ONLY eventos_partido
    ADD CONSTRAINT fk_2dcavlsprv0bnwolsi1tplb7n FOREIGN KEY (jugador_id) REFERENCES jugadores(id);

ALTER TABLE ONLY transferencias
    ADD CONSTRAINT fk_2l153qu8skehhefvxgmvelcco FOREIGN KEY (equipo_vendedor_id) REFERENCES equipos(id);

ALTER TABLE ONLY jugadores_partido_roja
    ADD CONSTRAINT fk_2pi3ffvmr8bei6thv21keornw FOREIGN KEY (partido_id) REFERENCES partidos(id);

ALTER TABLE ONLY jugadores_partido_amarilla
    ADD CONSTRAINT fk_469c0rmo9sd1ohj8g8lnccb4l FOREIGN KEY (partido_id) REFERENCES partidos(id);

ALTER TABLE ONLY partidos
    ADD CONSTRAINT fk_5ahlug9oropc0gg16uys1kytu FOREIGN KEY (formacion_visitante_id) REFERENCES formaciones(id);

ALTER TABLE ONLY comentarios
    ADD CONSTRAINT fk_5eeab0oy1m3ukxlxka0jnhjwy FOREIGN KEY (evento_id) REFERENCES eventos(id);

ALTER TABLE ONLY jugadores_habilidades
    ADD CONSTRAINT fk_5l60btcnjcksr1dvv78v2wi0i FOREIGN KEY (jugador_id) REFERENCES jugadores(id);

ALTER TABLE ONLY partidos
    ADD CONSTRAINT fk_5rpx0ej9q8b4jngr3fmshnv8m FOREIGN KEY (visitante_id) REFERENCES equipos(id);

ALTER TABLE ONLY jugadores
    ADD CONSTRAINT fk_5x0p1aby3fwdaqx3fw65wsojt FOREIGN KEY (ejercicio_id) REFERENCES ejercicios(id);

ALTER TABLE ONLY eventos_partido
    ADD CONSTRAINT fk_6qcmhhnx5bo3n16kh8reoatn5 FOREIGN KEY (partido_id) REFERENCES partidos(id);

ALTER TABLE ONLY partidos
    ADD CONSTRAINT fk_8415496x2w781ylqqde8nh4kd FOREIGN KEY (local_id) REFERENCES equipos(id);

ALTER TABLE ONLY correos
    ADD CONSTRAINT fk_8y9m41nj1lkij0bra7bu8e8pg FOREIGN KEY (from_id) REFERENCES usuarios(id);

ALTER TABLE ONLY jugadores_partido_roja
    ADD CONSTRAINT fk_97jcos9tc27whm86vroecalm2 FOREIGN KEY (jugador_id) REFERENCES jugadores(id);

ALTER TABLE ONLY jugadores_partido_lesionados
    ADD CONSTRAINT fk_a3qggvl9s0xp4qx5o34ta4j7i FOREIGN KEY (jugador_id) REFERENCES jugadores(id);

ALTER TABLE ONLY jugador_formacion
    ADD CONSTRAINT fk_aqccdoie3yxj844pfejw88gdt FOREIGN KEY (formacion_id) REFERENCES formaciones(id);

ALTER TABLE ONLY jugador_formacion
    ADD CONSTRAINT fk_cx8tqacuc82v08brnhd3442tw FOREIGN KEY (jugador_id) REFERENCES jugadores(id);

ALTER TABLE ONLY jugadores_partido_lesionados
    ADD CONSTRAINT fk_d6p4sadrbi3cstgcr3murif84 FOREIGN KEY (partido_id) REFERENCES partidos(id);

ALTER TABLE ONLY tabla_posiciones
    ADD CONSTRAINT fk_ej7t2jvgj4il6m81rerfevwlv FOREIGN KEY (liga_id) REFERENCES ligas(id);

ALTER TABLE ONLY jugadores
    ADD CONSTRAINT fk_eumohi7yihddwfoavo5o4bj7d FOREIGN KEY (equipo_id) REFERENCES equipos(id);

ALTER TABLE ONLY fixture
    ADD CONSTRAINT fk_ffe4yody59vraaytrk4x3hau0 FOREIGN KEY (partido_id) REFERENCES partidos(id);

ALTER TABLE ONLY jugadores_partido_cambiados
    ADD CONSTRAINT fk_fn7s4f3imftr0er5vmgals3l9 FOREIGN KEY (partido_id) REFERENCES partidos(id);

ALTER TABLE ONLY jugadores_partido_cambiados
    ADD CONSTRAINT fk_gt0bps0grda98hialon44s4l FOREIGN KEY (jugador_id) REFERENCES jugadores(id);

ALTER TABLE ONLY correos
    ADD CONSTRAINT fk_gyrnadsbhkio82xc0lknem7nf FOREIGN KEY (to_id) REFERENCES usuarios(id);

ALTER TABLE ONLY transferencias
    ADD CONSTRAINT fk_hj72emvipfwqab1cfgbq6a1n FOREIGN KEY (jugador_id) REFERENCES jugadores(id);

ALTER TABLE ONLY eventos_partido
    ADD CONSTRAINT fk_hwnuv9yiq8tpsy7n8b90hgj8v FOREIGN KEY (equipo_id) REFERENCES equipos(id);

ALTER TABLE ONLY transferencias
    ADD CONSTRAINT fk_iqcj7tkcwjpprmkfrsnht9oqh FOREIGN KEY (equipo_comprador_id) REFERENCES equipos(id);

ALTER TABLE ONLY tabla_posiciones
    ADD CONSTRAINT fk_j64xlf4m43rt01wfjgch292qg FOREIGN KEY (equipo_id) REFERENCES equipos(id);

ALTER TABLE ONLY equipos
    ADD CONSTRAINT fk_kelf1tr3aafqv9jfebhun2oxh FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT fk_pbh5csow87ehqj911ey81wcny FOREIGN KEY (equipo_id) REFERENCES equipos(id);

ALTER TABLE ONLY eventos_partido
    ADD CONSTRAINT fk_rb796waxrntrnvew0ysgn6elg FOREIGN KEY (comentario_id) REFERENCES comentarios(id);

ALTER TABLE ONLY jugadores_habilidades
    ADD CONSTRAINT fk_rh1w9y33a49cstx89v8q9ja5n FOREIGN KEY (habilidad_id) REFERENCES habilidades(id);

ALTER TABLE ONLY partidos
    ADD CONSTRAINT fk_tkno0tbkpyypk34e418vyhbhu FOREIGN KEY (formacion_local_id) REFERENCES formaciones(id);

ALTER TABLE ONLY eventos_partido
    ADD CONSTRAINT fk_xosko8vspp1e6ny3pcc7quvf FOREIGN KEY (evento_id) REFERENCES eventos(id);