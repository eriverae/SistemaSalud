select * from persona;
select * from persona_natural;
select * from persona_juridica;
select * from persona_eps;
select * from agenda;
select * from cita;
select * from persona_natural_especialidad;
select * from especialidad;
commit ;


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

 

--////
INSERT INTO cita (estado_paciente_atendido, hora_fin, hora_inicio, valor, id_agenda, id_paciente_eps, estado_cita)
VALUES		 (FALSE, (now() + interval '15 minutes 15 milliseconds'), now(), 2500, 2, 4, 'APARTADA');


		--id_cita serial
INSERT INTO cita (id_cita, estado_paciente_atendido, hora_fin, hora_inicio, valor, id_agenda, id_paciente_eps, estado_cita)
VALUES		 (1, FALSE, (now() + interval '15 minutes 15 milliseconds'), now(), 2500, 2, 4, 'APARTADA');
--////



--UPDATE cita SET observaciones = 'observacion paciente by medico';\
UPDATE cita SET estado_cita = 'CANCELADA';
UPDATE cita SET estado_cita = 'APARTADA' WHERE estado_cita = 'CANCELADA';
UPDATE cita SET hora_fin = '2016-05-05 23:55:59.757948', estado_cita = 'APARTADA' WHERE id_cita = 21;



select count(*) from cita;


------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
-- **************************CONSULTAS *************************

SELECT * FROM especialidad;

select DISTINCT ON (descripcion) descripcion from especialidad;
select distinct on (descripcion) * from especialidad;
-- **************************FIN-CONSULTAS *************************


--5 = medicos 
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (11,'MEDICO', 	'0001011011', 1);
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (12,'MEDICO', 	'0001212122', 1);
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (13,'MEDICO', 	'0001313133', 1);
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (14,'MEDICO', 	'0001414144', 1);
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (15,'MEDICO', 	'0001515155', 1);

INSERT INTO persona_natural (apellidos,correo_electronico,direccion,fecha_nacimiento, fotografia,grupo_sanguineo,nombres,rol_persona_natural,tarjeta_profesional,telefono_celular,telefono_fijo,id_persona, genero, rh) VALUES 
                            ('DOC11', 'medico11@sisc.com', 'Calle falsa 111', now(),'fotoDonMedico11','gruposang11', 'Don Medicco11', 'MEDICO','3432432432',31320982,32432432,11, 'm', 'r');
INSERT INTO persona_natural (apellidos,correo_electronico,direccion,fecha_nacimiento, fotografia,grupo_sanguineo,nombres,rol_persona_natural,tarjeta_profesional,telefono_celular,telefono_fijo,id_persona, genero, rh) VALUES 
                            ('DOC12', 'medico12@sisc.com', 'Calle falsa 222', now(),'fotoDonMedico12','gruposang12', 'Don Medicco12', 'MEDICO','3432432432',31320982,32432432,12, 'm', 'r');
INSERT INTO persona_natural (apellidos,correo_electronico,direccion,fecha_nacimiento, fotografia,grupo_sanguineo,nombres,rol_persona_natural,tarjeta_profesional,telefono_celular,telefono_fijo,id_persona, genero, rh) VALUES 
                            ('DOC13', 'medico13@sisc.com', 'Calle falsa 333', now(),'fotoDonMedico13','gruposang13', 'Don Medicco13', 'MEDICO','3432432432',31320982,32432432,13, 'm', 'r');
INSERT INTO persona_natural (apellidos,correo_electronico,direccion,fecha_nacimiento, fotografia,grupo_sanguineo,nombres,rol_persona_natural,tarjeta_profesional,telefono_celular,telefono_fijo,id_persona, genero, rh) VALUES 
                            ('DOC14', 'medico14@sisc.com', 'Calle falsa 444', now(),'fotoDonMedico14','gruposang14', 'Don Medicco14', 'MEDICO','3432432432',31320982,32432432,14, 'm', 'r');
INSERT INTO persona_natural (apellidos,correo_electronico,direccion,fecha_nacimiento, fotografia,grupo_sanguineo,nombres,rol_persona_natural,tarjeta_profesional,telefono_celular,telefono_fijo,id_persona, genero, rh) VALUES 
                            ('DOC15', 'medico15@sisc.com', 'Calle falsa 555', now(),'fotoDonMedico15','gruposang15', 'Don Medicco15', 'MEDICO','3432432432',31320982,32432432,15, 'm', 'r');


-- 1 = eps
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (555,'EPS', 		'0001155551', 555);
INSERT INTO persona_juridica (fecha_constitucion, razon_social, representante_legal, id_persona) VALUES (now(), 'CRUZ BLANCA', 'EL DUEÑO DE LA EPS Cruz-Blanca', 555);


-- ligar los (5 = medicos) a la EPS
INSERT INTO persona_eps (id_persona_eps,fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (111,now(), now(), 555, 11);
INSERT INTO persona_eps (id_persona_eps,fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (112,now(), now(), 555, 12);
INSERT INTO persona_eps (id_persona_eps,fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (113,now(), now(), 555, 13);
INSERT INTO persona_eps (id_persona_eps,fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (114,now(), now(), 555, 14);
INSERT INTO persona_eps (id_persona_eps,fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (115,now(), now(), 555, 15);


-- definimos las especialidades de los medicos en nuestra eps.
INSERT INTO persona_natural_especialidad (id_persona_especialidad, id_especialidad, id_medico) VALUES (200, 1, 11);
INSERT INTO persona_natural_especialidad (id_persona_especialidad, id_especialidad, id_medico) VALUES (201, 2, 12);
INSERT INTO persona_natural_especialidad (id_persona_especialidad, id_especialidad, id_medico) VALUES (202, 2, 13);
INSERT INTO persona_natural_especialidad (id_persona_especialidad, id_especialidad, id_medico) VALUES (203, 3, 14);
INSERT INTO persona_natural_especialidad (id_persona_especialidad, id_especialidad, id_medico) VALUES (204, 1, 15);
INSERT INTO persona_natural_especialidad (id_persona_especialidad, id_especialidad, id_medico) VALUES (205, 2, 15);
INSERT INTO persona_natural_especialidad (id_persona_especialidad, id_especialidad, id_medico) VALUES (206, 3, 15);
INSERT INTO persona_natural_especialidad (id_persona_especialidad, id_especialidad, id_medico) VALUES (207, 4, 15);

-- 4 = ESPECIALIDADES que hay en la eps, segun los medicos registrados
INSERT INTO especialidad (id_especialidad, descripcion) VALUES (1, 'CARDIOLOGO');
INSERT INTO especialidad (id_especialidad, descripcion) VALUES (2, 'ODONTOLOGO');
INSERT INTO especialidad (id_especialidad, descripcion) VALUES (3, 'OFTALMOLOGO');
INSERT INTO especialidad (id_especialidad, descripcion) VALUES (4, 'OPTOMETRA');




------------------------------------------------------------------------------------
------------------------------------------------------------------------------------









	-- datos de prueba entre cita y agenda 
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (1,'MEDICO', 	'0001111111', 1);
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (2,'PACIENTE', '0001111112', 1);
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (3,'EPS', 		'0001111113', 2);
INSERT INTO persona (id_persona,TIPO_PERSONA, numero_identificacion, tipo_identificacion) VALUES (4,'EPS', 		'0001111114', 2);



INSERT INTO persona_natural (apellidos,correo_electronico,direccion,fecha_nacimiento, fotografia,grupo_sanguineo,nombres,rol_persona_natural,tarjeta_profesional,telefono_celular,telefono_fijo,id_persona, genero, rh) VALUES ('Don Medicco', 'medico@sisc.com', 'Calle falsa 123', now(),'fotoDonMedico','gruposang', 'Persona Natural', 'MEDICO','3432432432',31320982,32432432,1, 'm', 'r');
INSERT INTO persona_natural (apellidos,correo_electronico,direccion,fecha_nacimiento, fotografia,grupo_sanguineo,nombres,rol_persona_natural,tarjeta_profesional,telefono_celular,telefono_fijo,id_persona, genero, rh) VALUES ('PACIENTE ANDRES', 'pandres@sisc.com', 'Calle falsa 123', now(),'fotopaciente','gruposang','Persona Natural', 'PACIENTE','3432432432',31320982,32432432,2, 'm', 'r');

INSERT INTO persona_juridica (fecha_constitucion, razon_social, representante_legal, id_persona) VALUES (now(), 'FAMISANAR', 'EL DUEÑO DE LA EPS', 3);
INSERT INTO persona_juridica (fecha_constitucion, razon_social, representante_legal, id_persona) VALUES (now(), 'COLSANITAS', 'EL DUEÑO DE LA EPS', 4);


INSERT INTO persona_eps (id_persona_eps,fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (1,now(), now(), 3, 1);
INSERT INTO persona_eps (id_persona_eps,fecha_fin, fecha_inicio, id_eps, id_persona) VALUES (2,now(), now(), 4, 1);