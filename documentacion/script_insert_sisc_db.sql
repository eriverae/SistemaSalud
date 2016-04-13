

select * from persona;
select * from persona_natural;
select * from persona_juridica;
select * from persona_eps;
select * from agenda;

desc agenda;

/**
	insertar medico
*/
INSERT INTO persona (TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES ('MEDICO', '0001111111', 'C.C');
INSERT INTO persona_natural (apellidos, correo_electronico, direccion, fecha_nacimiento, fotografia, genero, grupo_sanguineo, nombres, rh, rol_persona_natural, tarjeta_profesional, telefono_celular, telefono_fijo, id_persona) VALUES ('Don Medicco', 'medico@sisc.com', 'Calle falsa 123', now(), 'dsa', 'M', '+', 'Persona Natural', 'AB', 'MEDICO', '3432432432', '31320982', '32432432', '1');


/**
	insertar eps
*/
INSERT INTO persona (TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES ('EPS',    '0001111112', 'NIT');
INSERT INTO persona_juridica (fecha_constitucion, razon_social, representante_legal, id_persona) VALUES (now(), 'MI EPS', 'UN TIPO', 2);
/**
	insertar peersona eps
*/
INSERT INTO persona_eps (fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (now(), now(), 2, 1);
/**
	insertar agenda
 */
 
INSERT INTO agenda (ciudad, direccion, estado_diponible, hora_bloque_inicio, hora_bloque_fin, localidad, numero_consultorio, tiempo_minutos_cita, id_medico_eps) VALUES ('Bogotá', 'Calle 123', 'BLOQUE_DISPONIBLE', '2016-03-12 08:00:00', '2016-03-12 16:00:00', 'LOCALIDAD', '301', 15, 1);
INSERT INTO agenda (ciudad, direccion, estado_diponible, hora_bloque_inicio, hora_bloque_fin, localidad, numero_consultorio, tiempo_minutos_cita, id_medico_eps) VALUES ('Bogotá', 'Calle 123', 'BLOQUE_DISPONIBLE', '2016-04-12 08:00:00', '2016-04-12 16:00:00', 'LOCALIDAD', '301', 15, 1);
INSERT INTO agenda (ciudad, direccion, estado_diponible, hora_bloque_inicio, hora_bloque_fin, localidad, numero_consultorio, tiempo_minutos_cita, id_medico_eps) VALUES ('Bogotá', 'Calle 123', 'BLOQUE_DISPONIBLE', '2016-04-11 08:00:00', '2016-04-11 16:00:00', 'LOCALIDAD', '301', 15, 1);
INSERT INTO agenda (ciudad, direccion, estado_diponible, hora_bloque_inicio, hora_bloque_fin, localidad, numero_consultorio, tiempo_minutos_cita, id_medico_eps) VALUES ('Bogotá', 'Calle 123', 'BLOQUE_DISPONIBLE', '2016-04-10 08:00:00', '2016-04-10 16:00:00', 'LOCALIDAD', '301', 15, 1);


