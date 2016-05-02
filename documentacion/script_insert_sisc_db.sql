

select * from persona;
select * from persona_natural;
select * from persona_juridica;
select * from persona_eps;
select * from agenda;
select * from cita;

desc agenda;

/**
	insertar medico
*/
INSERT INTO persona (TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES ('MEDICO', '0001111111', 1);

INSERT INTO persona_natural (apellidos,     correo_electronico, direccion,        fecha_nacimiento, fotografia,       grupo_sanguineo,      nombres,         rol_persona_natural, tarjeta_profesional, telefono_celular, telefono_fijo, id_persona, genero, rh) 
VALUES                      ('Don Medicco', 'medico@sisc.com', 'Calle falsa 123', now(),            'fotoDonMedico',   'gruposang',       'Persona Natural', 'MEDICO',             '3432432432',        31320982,          32432432,       1, 'm', 'r');
/**
	insertar paciente
*/
INSERT INTO persona (TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES ('PACIENTE', '1013608894', 1);

INSERT INTO persona_natural (apellidos,     correo_electronico, direccion,        fecha_nacimiento, fotografia,       grupo_sanguineo,      nombres,         rol_persona_natural, tarjeta_profesional, telefono_celular, telefono_fijo, id_persona, genero, rh) 
VALUES                      ('PACIENTE ANDRES', 'pandres@sisc.com', 'Calle falsa 123', now(),            'fotopaciente',   'gruposang',       'Persona Natural', 'PACIENTE',             '3432432432',        31320982,          32432432,       3, 'm', 'r');


/**
	insertar eps
*/
INSERT INTO persona (TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES ('EPS',    '0001111112', 2);
INSERT INTO persona_juridica (fecha_constitucion, razon_social, representante_legal, id_persona) VALUES (now(), 'MI EPS', 'UN TIPO', 2);

INSERT INTO persona (TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES ('EPS',    '0001111113', 3);
INSERT INTO persona_juridica (fecha_constitucion, razon_social, representante_legal, id_persona) VALUES (now(), 'COLSANITAS', 'UN TIPO', 3);

---------------------------------------
/**
	insertar peersona eps - medico
*/
INSERT INTO persona_eps (fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (now(), now(), 2, 1);
/**
	insertar peersona eps - paciente
*/
INSERT INTO persona_eps ( fecha_inicio, id_eps, id_persona) VALUES (now(), 2, 3);
---------------------------------------
/**
	insertar agenda
 */
 
INSERT INTO agenda (ciudad, direccion, estado_diponible, hora_bloque_inicio, hora_bloque_fin, localidad, numero_consultorio, tiempo_minutos_cita, id_medico_eps) VALUES ('Bogotá', 'Calle 123', 'BLOQUE_DISPONIBLE', '2016-03-12 08:00:00', '2016-03-12 16:00:00', 'LOCALIDAD', '301', 15, 1);
INSERT INTO agenda (ciudad, direccion, estado_diponible, hora_bloque_inicio, hora_bloque_fin, localidad, numero_consultorio, tiempo_minutos_cita, id_medico_eps) VALUES ('Bogotá', 'Calle 123', 'BLOQUE_DISPONIBLE', '2016-04-12 08:00:00', '2016-04-12 16:00:00', 'LOCALIDAD', '301', 15, 1);
INSERT INTO agenda (ciudad, direccion, estado_diponible, hora_bloque_inicio, hora_bloque_fin, localidad, numero_consultorio, tiempo_minutos_cita, id_medico_eps) VALUES ('Bogotá', 'Calle 123', 'BLOQUE_DISPONIBLE', '2016-04-11 08:00:00', '2016-04-11 16:00:00', 'LOCALIDAD', '301', 15, 1);
INSERT INTO agenda (ciudad, direccion, estado_diponible, hora_bloque_inicio, hora_bloque_fin, localidad, numero_consultorio, tiempo_minutos_cita, id_medico_eps) VALUES ('Bogotá', 'Calle 123', 'BLOQUE_DISPONIBLE', '2016-04-10 08:00:00', '2016-04-10 16:00:00', 'LOCALIDAD', '301', 15, 1);


------------------------------------------------------------
/**
	insertar cita
*/
--https://saforas.wordpress.com/2009/11/12/postgresql-fechas-y-horas/
--http://stackoverflow.com/questions/16837280/get-interval-in-milliseconds
--ALTER TABLE cita ADD COLUMN estado_cita varchar(20);
ALTER TABLE cita ADD COLUMN observaciones text;
--DELETE FROM cita;

select * from cita;


select id_cita, hora_fin from cita
	order by hora_fin desc
LIMIT 5 OFFSET 1
 

--////
INSERT INTO cita (estado_paciente_atendido, hora_fin, hora_inicio, valor, id_agenda, id_paciente_eps, estado_cita)
VALUES		 (FALSE, (now() + interval '15 minutes 15 milliseconds'), now(), 2500, 2, 4, 'APARTADA');


		--id_cita serial
INSERT INTO cita (id_cita, estado_paciente_atendido, hora_fin, hora_inicio, valor, id_agenda, id_paciente_eps, estado_cita)
VALUES		 (1, FALSE, (now() + interval '15 minutes 15 milliseconds'), now(), 2500, 2, 4, 'APARTADA');
--////


SELECT ('c-' || id_cita) AS citaconca, id_cita FROM cita



UPDATE cita SET estado_cita = 'APARTADA' WHERE estado_cita = 'CANCELADA';














