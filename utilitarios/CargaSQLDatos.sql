-- Codiguera de jugadores
insert into datos_jugador values (1,'C.Mendoza ');
insert into datos_jugador values (2,'A.González ');
insert into datos_jugador values (3,'D.Araújo ');
insert into datos_jugador values (4,'P.Mares ');
insert into datos_jugador values (5,'A.Geijer ');
insert into datos_jugador values (6,'R.Costa ');
insert into datos_jugador values (7,'E.Gil ');
insert into datos_jugador values (8,'J.Ferér ');
insert into datos_jugador values (9,'V.Alvarenga ');
insert into datos_jugador values (10,'A.Åsberg ');
insert into datos_jugador values (11,'M.Key ');
insert into datos_jugador values (12,'R.Gjersell ');
insert into datos_jugador values (13,'T.Rebari ');
insert into datos_jugador values (14,'N.Donato ');
insert into datos_jugador values (15,'R.Dalmonte ');
insert into datos_jugador values (16,'D.Furacão ');
insert into datos_jugador values (17,'G.Edu ');
insert into datos_jugador values (18,'M.Holetz ');
insert into datos_jugador values (19,'Z.Andrade ');
insert into datos_jugador values (20,'A.Marinho ');
insert into datos_jugador values (21,'D.Cardozo  ');
insert into datos_jugador values (22,'T.Irrureta  ');
insert into datos_jugador values (23,'O.Meilton  ');
insert into datos_jugador values (24,'M.Martínez  ');
insert into datos_jugador values (25,'J.Scheinhardt  ');
insert into datos_jugador values (26,'T.Cadete  ');
insert into datos_jugador values (27,'R.Brindisi  ');
insert into datos_jugador values (28,'J.Artoni  ');
insert into datos_jugador values (29,'M.Cufré  ');
insert into datos_jugador values (30,'F.Perez  ');
insert into datos_jugador values (31,'S.Batistuta  ');
insert into datos_jugador values (32,'R.Oliveria  ');
insert into datos_jugador values (33,'D.Cardozo  ');
insert into datos_jugador values (34,'E.Losaldo  ');
insert into datos_jugador values (35,'G.Melfi  ');
insert into datos_jugador values (36,'P.Franco  ');
insert into datos_jugador values (37,'L.Krauss  ');
insert into datos_jugador values (38,'R.Recoba  ');
insert into datos_jugador values (39,'J.Guardiola  ');
insert into datos_jugador values (40,'G.Giusti  ');
insert into datos_jugador values (41,'S.Galdames   ');
insert into datos_jugador values (42,'V.Junior  ');
insert into datos_jugador values (43,'A,Sabas  ');
insert into datos_jugador values (44,'M.Manjarin  ');
insert into datos_jugador values (45,'E.Enrique  ');
insert into datos_jugador values (46,'C.Norris  ');
insert into datos_jugador values (47,'O.Ato  ');
insert into datos_jugador values (48,'O.Lembowski  ');
insert into datos_jugador values (49,'G.Torre  ');
insert into datos_jugador values (50,'E.Dunga  ');
insert into datos_jugador values (51,'C.Aizpurua  ');
insert into datos_jugador values (52,'A.Castro  ');
insert into datos_jugador values (53,'J.Ribera  ');
insert into datos_jugador values (54,'H.Ilhan  ');
insert into datos_jugador values (55,'M.Tinoco  ');
insert into datos_jugador values (56,'I.Virgílio  ');
insert into datos_jugador values (57,'A.Boroja  ');
insert into datos_jugador values (58,'P.Bengoechea  ');
insert into datos_jugador values (59,'F.Beckenbauer  ');
insert into datos_jugador values (60,'M.Veloso  ');
insert into datos_jugador values (61,'A.Maldonado   ');
insert into datos_jugador values (62,'S.Amessi  ');
insert into datos_jugador values (63,'M.Losaldo  ');
insert into datos_jugador values (64,'D.Curle  ');
insert into datos_jugador values (65,'W.Pandiani  ');
insert into datos_jugador values (66,'I.Braco  ');
insert into datos_jugador values (67,'H.Schelin  ');
insert into datos_jugador values (68,'L.Alessandro  ');
insert into datos_jugador values (69,'R.Dorronsoro  ');
insert into datos_jugador values (70,'R.Cesaretti  ');
insert into datos_jugador values (71,'L.Misco  ');
insert into datos_jugador values (72,'F.Sorondo  ');
insert into datos_jugador values (73,'J.Poutiainen  ');
insert into datos_jugador values (74,'D.Carini  ');
insert into datos_jugador values (75,'I.Diogo  ');
insert into datos_jugador values (76,'F.Rosa  ');
insert into datos_jugador values (77,'M.Fúriga  ');
insert into datos_jugador values (78,'H.Mortensen  ');
insert into datos_jugador values (79,'R.Biriotti ');
insert into datos_jugador values (80,'S.Kralj   ');
insert into datos_jugador values (81,'M.Memic  ');
insert into datos_jugador values (82,'V.Radosevic  ');
insert into datos_jugador values (83,'D.Foric  ');
insert into datos_jugador values (84,'M.Wietono  ');
insert into datos_jugador values (85,'C.Dudic  ');
insert into datos_jugador values (86,'N.Cirkovic  ');
insert into datos_jugador values (87,'R.Horacio  ');
insert into datos_jugador values (88,'E.Hermansson  ');
insert into datos_jugador values (89,'Z.Talic  ');
insert into datos_jugador values (90,'S.Björkman  ');
insert into datos_jugador values (91,'J.Artigas  ');
insert into datos_jugador values (92,'L.Garcia  ');
insert into datos_jugador values (93,'H.Troche  ');
insert into datos_jugador values (94,'S.Alejnikov  ');
insert into datos_jugador values (95,'P.Filgueira  ');
insert into datos_jugador values (96,'M.Echeverria  ');
insert into datos_jugador values (97,'Z.Hadzibegic  ');
insert into datos_jugador values (98,'I.Villasboas ');
insert into datos_jugador values (99,'P.Carvalho  ');
insert into datos_jugador values (100,'R.Gaucho ');





--Usuario

INSERT INTO usuarios(id, email, nick, nombre, "password") 
	VALUES (1, 'zegunadi@hotmail.com', 'zegunadi', 'Diego Rodriguez', 'clave');

--Equipo
INSERT INTO equipos(id, nombre, altura, estadio, fondos, latitud, longitud, usuario_id)
    VALUES (1,'Peñarol',2000,'El Paladino',0,-95.3245,-121.22133131, null);

INSERT INTO equipos(id, nombre, altura, estadio, fondos, latitud, longitud, usuario_id)
    VALUES (2,'Nacional',2000,'El Parque Central',0,-95.3245,-121.22133131, null);
	
--Juagadores
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
	VALUES (1, 170, 21, false,'J.Gomez', 1, 85, 1,1);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (2,170, 21, false,'C.Norris',1, 85, 1,1);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (3,170, 21, false,'M.Mar',1, 85, 1,1);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (4,170, 21, false, 'P.Mujica',1, 85, 1,1);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (5,170, 21, false, 'D.Astori',1, 85, 1,1);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (6,170, 21, false, 'J.Rambo',1, 85, 1,1);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (7,170, 21, false, 'C.Good',1, 85, 1,1);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (8,170, 21, false, 'B.Price',1, 85, 1,2);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (9,170, 21, false, 'O.Ato',1, 85, 1,2);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (10,170, 21, false, 'Paco',1, 85, 1,2);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (11,170, 21, false, 'F.Carini',1, 85, 1,2);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (12,170, 21, false, 'E.Peña',1, 85, 1,2);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (13,170, 21, false, 'A.Perez',1, 85, 1,2);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (14,170, 21, false, 'R.Robinson',1, 85, 1,2);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (15,170, 21, false, 'V.Junior',1, 85, 1,2);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (16,170, 21, false, 'C.Soldado',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (17,170, 21, false, 'B.Diaz',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (18,170, 21, false, 'T.Henry',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (19,170, 21, false, 'J.Franco',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (20,170, 21, false, 'J.Carrasco',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (21,170, 21, false, 'S.Hall',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (22,170, 21, false, 'J.Simon',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (23,170, 21, false, 'P.Perez',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (24,170, 21, false, 'T.Gonzales',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (25,170, 21, false, 'R.Newman',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (26,170, 21, false, 'V.Junior',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (27,170, 21, false, 'C.Garcia',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (28,170, 21, false, 'B.Diaz',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (29,170, 21, false, 'T.Henry',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (30,170, 21, false, 'J.Franco',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (31,170, 21, false, 'J.Carrasco',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (32,170, 21, false, 'S.Hall',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (33,170, 21, false, 'J.Simon',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (34,170, 21, false, 'P.Perez',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (35,170, 21, false, 'T.Gonzales',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (36,170, 21, false, 'R.Fernandez',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (37,170, 21, false, 'A.Rosano',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (38,170, 21, false, 'F.Vasquez',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (39,170, 21, false, 'G.Estevez',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (40,170, 21, false, 'H.Puente',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (41,170, 21, false, 'L.Rios',1, 85, 1,null);
INSERT INTO juagadores(id, altura, edad, en_venta, nombre, nro_camiseta, peso, puesto,equipo_id) 
    VALUES (42,170, 21, false, 'N.Tapia',1, 85, 1,null);
	
	
	
