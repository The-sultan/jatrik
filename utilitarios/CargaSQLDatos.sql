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