select * from persona;
select * from persona_natural;
select * from persona_juridica;
select * from persona_eps;
select * from agenda;
select * from cita;
commit ;

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