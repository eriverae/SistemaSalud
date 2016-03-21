DROP TABLE log_notifica CASCADE
;
DROP TABLE usuempr CASCADE
;
DROP TABLE auditusu CASCADE
;
DROP TABLE usuario CASCADE
;
DROP TABLE grupusu CASCADE
;
DROP TABLE accusua CASCADE
;
DROP TABLE grupo CASCADE
;
DROP TABLE accgrup CASCADE
;
DROP TABLE acceso CASCADE
;
DROP TABLE Cita CASCADE
;
DROP TABLE Agenda CASCADE
;
DROP TABLE persona_eps CASCADE
;
DROP TABLE persona_natural_especialidad CASCADE
;
DROP TABLE persona_natural_operacion CASCADE
;
DROP TABLE persona_natural_alergia CASCADE
;
DROP TABLE persona_natural_enfermedad CASCADE
;
DROP TABLE tipo_persona_natural CASCADE
;
DROP TABLE especialidad CASCADE
;
DROP TABLE operacion CASCADE
;
DROP TABLE alergia CASCADE
;
DROP TABLE enfermedad CASCADE
;
DROP TABLE persona_natural_beneficiario CASCADE
;
DROP TABLE persona_juridica CASCADE
;
DROP TABLE persona_natural CASCADE
;
DROP TABLE persona CASCADE
;
DROP TABLE persona_cirugia CASCADE
;
DROP TABLE incapacidad CASCADE
;
DROP TABLE persona_examen CASCADE
;
DROP TABLE persona_tratamiento CASCADE
;
DROP TABLE persona_medicamento CASCADE
;
DROP TABLE cirugia CASCADE
;
DROP TABLE Examen CASCADE
;
DROP TABLE Tratamiento CASCADE
;
DROP TABLE medicamento CASCADE
;

CREATE TABLE log_notifica ( 
	lgno_lgno bigint NOT NULL,
	lgno_siste varchar(100),
	lgno_dest varchar(100) NOT NULL,
	lgno_asunto varchar(255) NOT NULL,
	lgno_cuerpo varchar(255) NOT NULL,
	lgno_pers bigint NOT NULL
)
;

CREATE TABLE usuempr ( 
	usem_usem bigint NOT NULL,
	usem_prju bigint NOT NULL
)
;

CREATE TABLE auditusu ( 
	audi_audi bigint NOT NULL,
	audi_fech timestamp,
	audi_usua bigint,
	audi_obser varchar(500),
	audi_drip varchar(100),
	audi_hostn varchar(100)
)
;

CREATE TABLE usuario ( 
	usua_usua bigint NOT NULL,
	usua_email varchar(45) NOT NULL,
	usua_pass varchar(45) NOT NULL,
	usua_esta varchar(1) NOT NULL,
	usua_usucs varchar(45) NOT NULL,
	usua_usucd timestamp NOT NULL,
	usua_usums varchar(45) NOT NULL,
	usua_usumd timestamp NOT NULL,
	usua_block varchar(1),
	usua_persn bigint NOT NULL,
	usua_conta bigint NOT NULL
)
;

CREATE TABLE grupusu ( 
	grus_grup bigint NOT NULL,
	grus_usua bigint NOT NULL
)
;

CREATE TABLE accusua ( 
	acus_acce bigint NOT NULL,
	acus_usua bigint NOT NULL
)
;

CREATE TABLE grupo ( 
	grup_grup bigint NOT NULL,
	grup_nombr varchar(45) NOT NULL,
	grup_descri varchar(500) NOT NULL,
	grup_esta varchar(1) NOT NULL
)
;

CREATE TABLE accgrup ( 
	acgr_acce bigint NOT NULL,
	acgr_grup bigint NOT NULL
)
;

CREATE TABLE acceso ( 
	acce_acce bigint NOT NULL,
	acce_nombre varchar(45) NOT NULL,
	acce_desc varchar(500) NOT NULL,
	acce_esta varchar(1) NOT NULL
)
;

CREATE TABLE Cita ( 
	id bigint NOT NULL,
	valor double precision NOT NULL,
	estado_paciente_atendido boolean NOT NULL,
	fecha_paciente date NOT NULL,
	hora_inicio time,
	id_agenda bigint NOT NULL,
	id_persona_eps integer NOT NULL,
	id_persona_natural numeric(10) NOT NULL
)
;

CREATE TABLE Agenda ( 
	id bigint NOT NULL,
	ciudad varchar(50) NOT NULL,
	localidad varchar(50) NOT NULL,
	direccion varchar(50) NOT NULL,
	numero_consultorio integer,
	fecha_bloque date NOT NULL,
	hora_bloqueInicio time,
	hora_bloque_fin time,
	estado_diponible varchar(50),
	id_persona numeric(10) NOT NULL
)
;

CREATE TABLE persona_eps ( 
	id_persona_eps numeric(10) NOT NULL,
	id_persona_natural numeric(10),
	id_eps numeric(10),
	fecha_inicio date,
	fecha_fin date
)
;

CREATE TABLE persona_natural_especialidad ( 
	id_persona_natural numeric(10) NOT NULL,
	id_especialidad numeric(10) NOT NULL
)
;

CREATE TABLE persona_natural_operacion ( 
	id_persona_natural numeric(10) NOT NULL,
	id_operacion numeric(10) NOT NULL
)
;

CREATE TABLE persona_natural_alergia ( 
	id_persona_natural numeric(10) NOT NULL,
	id_alergia numeric(10) NOT NULL
)
;

CREATE TABLE persona_natural_enfermedad ( 
	id_persona numeric(10) NOT NULL,
	id_enfermedad numeric(10) NOT NULL
)
;

CREATE TABLE tipo_persona_natural ( 
	id_tipo_persona_natural numeric(1) NOT NULL,
	descripcion varchar(255) NOT NULL
)
;

CREATE TABLE especialidad ( 
	id_especialidad numeric(10) NOT NULL,
	descripcion varchar(255) NOT NULL
)
;

CREATE TABLE operacion ( 
	id_operacion numeric(10) NOT NULL,
	descripcion varchar(255) NOT NULL
)
;

CREATE TABLE alergia ( 
	id_alergia decimal(10) NOT NULL,
	descripcion varchar(255) NOT NULL
)
;

CREATE TABLE enfermedad ( 
	id_enfermedad numeric(10) NOT NULL,
	descripcion varchar(255) NOT NULL
)
;

CREATE TABLE persona_natural_beneficiario ( 
	id_persona_natural_beneficiario numeric(10) NOT NULL,
	id_beneficiario numeric(1) NOT NULL,
	id_cotizante numeric(10),
	parentezco varchar(5)
)
;

CREATE TABLE persona_juridica ( 
	id_persona_juridica numeric(10) NOT NULL,
	razon_social varchar(200),
	fecha_constitucion numeric(10),
	representante_legal numeric(10),
	id_persona numeric(10) NOT NULL
)
;

CREATE TABLE persona_natural ( 
	id_persona_natural numeric(10) NOT NULL,
	tipo_persona numeric(1),
	correo_electronico varchar(100) NOT NULL,
	nombres varchar(50) NOT NULL,
	apellidos varchar(50) NOT NULL,
	genero char(1) NOT NULL,
	fecha_nacimiento timestamp NOT NULL,
	telefono_celular numeric(10) NOT NULL,
	telefono_fijo numeric(10) NOT NULL,
	direccion varchar(150) NOT NULL,
	fotografia varchar(100),
	huella bytea,
	RH char(1) NOT NULL,
	grupo_sanguineo char(2) NOT NULL,
	tarjeta_profesional varchar(20),
	id_persona numeric(10) NOT NULL
)
;

CREATE TABLE persona ( 
	id_persona numeric(10) NOT NULL,
	tipo_identificacion varchar(2) NOT NULL,
	numero_identificacion numeric(10) NOT NULL
)
;

CREATE TABLE persona_cirugia ( 
	id_persona numeric(10) NOT NULL,
	id_cirugia bigint NOT NULL,
	id_cita bigint NOT NULL,
	observaciones text,
	detalles varchar(200)
)
;

CREATE TABLE incapacidad ( 
	idincapacidad bigint NOT NULL,
	idpersona numeric(10) NOT NULL,
	periodo varchar(150) NOT NULL,
	motivo text NOT NULL,
	id_persona numeric(10) NOT NULL
)
;

CREATE TABLE persona_examen ( 
	id_persona numeric(10) NOT NULL,
	id_examen bigint NOT NULL,
	id_cita bigint NOT NULL,
	fecha_examen date NOT NULL,
	detalles varchar(200),
	observaciones text
)
;

CREATE TABLE persona_tratamiento ( 
	id_persona numeric(10) NOT NULL,
	id_tratamiento bigint NOT NULL,
	id_cita bigint NOT NULL,
	observaciones text
)
;

CREATE TABLE persona_medicamento ( 
	id_medicamento bigint NOT NULL,
	id_persona numeric(10) NOT NULL,
	id_cita bigint NOT NULL,
	fecha_formula date NOT NULL,
	formula varchar(150) NOT NULL
)
;

CREATE TABLE cirugia ( 
	id_cirugia bigint NOT NULL,
	nombre_cirugia varchar(150)
)
;

CREATE TABLE Examen ( 
	id_examen bigint NOT NULL,
	nombre_examen varchar(150)
)
;

CREATE TABLE Tratamiento ( 
	id_tratamiento bigint NOT NULL,
	nombre_tratamiento varchar(150)
)
;

CREATE TABLE medicamento ( 
	id_medicamento bigint NOT NULL,
	nombre_medicamento varchar(150) NOT NULL
)
;


ALTER TABLE log_notifica ADD CONSTRAINT PK_log_norifica 
	PRIMARY KEY (lgno_lgno)
;


ALTER TABLE usuempr ADD CONSTRAINT PK_usuempr 
	PRIMARY KEY (usem_usem, usem_prju)
;


ALTER TABLE auditusu ADD CONSTRAINT PK_Auditor 
	PRIMARY KEY (audi_audi)
;


ALTER TABLE usuario ADD CONSTRAINT PK_usuario 
	PRIMARY KEY (usua_usua)
;


ALTER TABLE grupusu ADD CONSTRAINT PK_grupusu 
	PRIMARY KEY (grus_grup, grus_usua)
;


ALTER TABLE accusua ADD CONSTRAINT PK_accusua 
	PRIMARY KEY (acus_acce, acus_usua)
;


ALTER TABLE grupo ADD CONSTRAINT PK_grupo 
	PRIMARY KEY (grup_grup)
;


ALTER TABLE accgrup ADD CONSTRAINT PK_accgrup 
	PRIMARY KEY (acgr_acce, acgr_grup)
;


ALTER TABLE acceso ADD CONSTRAINT PK_acceso 
	PRIMARY KEY (acce_acce)
;


ALTER TABLE Cita ADD CONSTRAINT PK_Cita 
	PRIMARY KEY (id)
;


ALTER TABLE Agenda ADD CONSTRAINT PK_Agenda 
	PRIMARY KEY (id)
;


ALTER TABLE persona_eps ADD CONSTRAINT PK_Eps 
	PRIMARY KEY (id_persona_eps)
;


ALTER TABLE persona_natural_especialidad ADD CONSTRAINT PK_persona_natural_especialidad 
	PRIMARY KEY (id_persona_natural, id_especialidad)
;


ALTER TABLE persona_natural_operacion ADD CONSTRAINT PK_persona_natural_operacion 
	PRIMARY KEY (id_persona_natural, id_operacion)
;


ALTER TABLE persona_natural_alergia ADD CONSTRAINT PK_persona_natural_alergia 
	PRIMARY KEY (id_persona_natural, id_alergia)
;


ALTER TABLE persona_natural_enfermedad ADD CONSTRAINT PK_PersonaNatural_Enfermedad 
	PRIMARY KEY (id_persona, id_enfermedad)
;


ALTER TABLE tipo_persona_natural ADD CONSTRAINT PK_TipoPersona 
	PRIMARY KEY (id_tipo_persona_natural)
;


ALTER TABLE especialidad ADD CONSTRAINT PK_Especialidad 
	PRIMARY KEY (id_especialidad)
;


ALTER TABLE operacion ADD CONSTRAINT PK_Operacion 
	PRIMARY KEY (id_operacion)
;


ALTER TABLE alergia ADD CONSTRAINT PK_Alergia 
	PRIMARY KEY (id_alergia)
;


ALTER TABLE enfermedad ADD CONSTRAINT PK_Enfermedad 
	PRIMARY KEY (id_enfermedad)
;


ALTER TABLE persona_natural_beneficiario ADD CONSTRAINT PK_Beneficiarios 
	PRIMARY KEY (id_persona_natural_beneficiario)
;


ALTER TABLE persona_juridica ADD CONSTRAINT PK_PersonaJuridica 
	PRIMARY KEY (id_persona_juridica)
;


ALTER TABLE persona_natural ADD CONSTRAINT PK_PersonaNatural 
	PRIMARY KEY (id_persona_natural)
;


ALTER TABLE persona ADD CONSTRAINT PK_Persona 
	PRIMARY KEY (id_persona)
;


ALTER TABLE incapacidad ADD CONSTRAINT PK_incapacidad 
	PRIMARY KEY (idincapacidad)
;


ALTER TABLE cirugia ADD CONSTRAINT PK_cirugia 
	PRIMARY KEY (id_cirugia)
;


ALTER TABLE Examen ADD CONSTRAINT PK_Examen 
	PRIMARY KEY (id_examen)
;


ALTER TABLE Tratamiento ADD CONSTRAINT PK_Tratamiento 
	PRIMARY KEY (id_tratamiento)
;


ALTER TABLE medicamento ADD CONSTRAINT PK_medicamento 
	PRIMARY KEY (id_medicamento)
;



ALTER TABLE log_notifica
	ADD CONSTRAINT UQ_log_norifica_lgno_lgno UNIQUE (lgno_lgno)
;
ALTER TABLE usuempr
	ADD CONSTRAINT UQ_usuempr_usem_prju UNIQUE (usem_prju)
;
ALTER TABLE usuempr
	ADD CONSTRAINT UQ_usuempr_usem_usem UNIQUE (usem_usem)
;
ALTER TABLE auditusu
	ADD CONSTRAINT UQ_Auditor_audi_audi UNIQUE (audi_audi)
;
ALTER TABLE usuario
	ADD CONSTRAINT UQ_usuario_usua_email UNIQUE (usua_email)
;
ALTER TABLE usuario
	ADD CONSTRAINT UQ_usuario_usua_usua UNIQUE (usua_usua)
;
ALTER TABLE grupusu
	ADD CONSTRAINT UQ_grupusu_grus_grup UNIQUE (grus_grup)
;
ALTER TABLE grupusu
	ADD CONSTRAINT UQ_grupusu_grus_usua UNIQUE (grus_usua)
;
ALTER TABLE accusua
	ADD CONSTRAINT UQ_accusua_acus_acce UNIQUE (acus_acce)
;
ALTER TABLE accusua
	ADD CONSTRAINT UQ_accusua_acus_usua UNIQUE (acus_usua)
;
ALTER TABLE grupo
	ADD CONSTRAINT UQ_grupo_grup_grup UNIQUE (grup_grup)
;
ALTER TABLE accgrup
	ADD CONSTRAINT UQ_accgrup_acgr_acce UNIQUE (acgr_acce)
;
ALTER TABLE accgrup
	ADD CONSTRAINT UQ_accgrup_acgr_grup UNIQUE (acgr_grup)
;
ALTER TABLE acceso
	ADD CONSTRAINT UQ_acceso_acce_acce UNIQUE (acce_acce)
;
ALTER TABLE persona_natural_enfermedad
	ADD CONSTRAINT UQ_persona_natural_enfermedad_id_enfermedad UNIQUE (id_enfermedad)
;
ALTER TABLE persona_natural_enfermedad
	ADD CONSTRAINT UQ_persona_natural_enfermedad_id_persona UNIQUE (id_persona)
;
ALTER TABLE tipo_persona_natural
	ADD CONSTRAINT UQ_tipo_persona_natural_id_tipo_persona_natural UNIQUE (id_tipo_persona_natural)
;
ALTER TABLE especialidad
	ADD CONSTRAINT UQ_especialidad_id_especialidad UNIQUE (id_especialidad)
;
ALTER TABLE operacion
	ADD CONSTRAINT UQ_operacion_id_operacion UNIQUE (id_operacion)
;
ALTER TABLE alergia
	ADD CONSTRAINT UQ_alergia_id_alergia UNIQUE (id_alergia)
;
ALTER TABLE enfermedad
	ADD CONSTRAINT UQ_enfermedad_id_enfermedad UNIQUE (id_enfermedad)
;
ALTER TABLE persona_natural_beneficiario
	ADD CONSTRAINT UQ_persona_natural_beneficiario_id_persona_natural_beneficiario UNIQUE (id_persona_natural_beneficiario)
;
ALTER TABLE persona_juridica
	ADD CONSTRAINT UQ_persona_juridica_id_persona UNIQUE (id_persona)
;
ALTER TABLE persona_juridica
	ADD CONSTRAINT UQ_PersonaJuridica_id_persona_juridica UNIQUE (id_persona_juridica)
;
ALTER TABLE persona_natural
	ADD CONSTRAINT UQ_persona_natural_id_persona UNIQUE (id_persona)
;
ALTER TABLE persona_natural
	ADD CONSTRAINT UQ_persona_natural_id_persona_natural UNIQUE (id_persona_natural)
;
ALTER TABLE persona
	ADD CONSTRAINT UQ_persona_id_persona UNIQUE (id_persona)
;
ALTER TABLE persona
	ADD CONSTRAINT UQ_persona_numero_identificacion UNIQUE (numero_identificacion)
;
ALTER TABLE persona_cirugia
	ADD CONSTRAINT UQ_persona_cirugia_id_cirugia UNIQUE (id_cirugia)
;
ALTER TABLE persona_cirugia
	ADD CONSTRAINT UQ_persona_cirugia_id_cita UNIQUE (id_cita)
;
ALTER TABLE persona_cirugia
	ADD CONSTRAINT UQ_persona_cirugia_id_persona UNIQUE (id_persona)
;
ALTER TABLE persona_examen
	ADD CONSTRAINT UQ_persona_examen_id_cita UNIQUE (id_cita)
;
ALTER TABLE persona_examen
	ADD CONSTRAINT UQ_persona_examen_id_examen UNIQUE (id_examen)
;
ALTER TABLE persona_examen
	ADD CONSTRAINT UQ_persona_examen_id_persona UNIQUE (id_persona)
;
ALTER TABLE persona_tratamiento
	ADD CONSTRAINT UQ_persona_tratamiento_id_cita UNIQUE (id_cita)
;
ALTER TABLE persona_tratamiento
	ADD CONSTRAINT UQ_persona_tratamiento_id_persona UNIQUE (id_persona)
;
ALTER TABLE persona_tratamiento
	ADD CONSTRAINT UQ_persona_tratamiento_id_tratamiento UNIQUE (id_tratamiento)
;
ALTER TABLE persona_medicamento
	ADD CONSTRAINT UQ_persona_medicamento_id_cita UNIQUE (id_cita)
;
ALTER TABLE persona_medicamento
	ADD CONSTRAINT UQ_persona_medicamento_id_medicamento UNIQUE (id_medicamento)
;
ALTER TABLE persona_medicamento
	ADD CONSTRAINT UQ_persona_medicamento_id_persona UNIQUE (id_persona)
;
ALTER TABLE cirugia
	ADD CONSTRAINT UQ_cirugia_id_cirugia UNIQUE (id_cirugia)
;
ALTER TABLE cirugia
	ADD CONSTRAINT UQ_cirugia_nombre_cirugia UNIQUE (nombre_cirugia)
;
ALTER TABLE Examen
	ADD CONSTRAINT UQ_Examen_id_examen UNIQUE (id_examen)
;
ALTER TABLE Examen
	ADD CONSTRAINT UQ_Examen_nombre_examen UNIQUE (nombre_examen)
;
ALTER TABLE Tratamiento
	ADD CONSTRAINT UQ_Tratamiento_id_tratamiento UNIQUE (id_tratamiento)
;
ALTER TABLE Tratamiento
	ADD CONSTRAINT UQ_Tratamiento_nombre_tratamiento UNIQUE (nombre_tratamiento)
;
ALTER TABLE medicamento
	ADD CONSTRAINT UQ_medicamento_id_medicamento UNIQUE (id_medicamento)
;
ALTER TABLE medicamento
	ADD CONSTRAINT UQ_medicamento_nombre_medicamento UNIQUE (nombre_medicamento)
;

ALTER TABLE Cita ADD CONSTRAINT FK_Cita_persona_eps 
	FOREIGN KEY (id_persona_eps) REFERENCES persona_eps (id_persona_eps)
;

ALTER TABLE Cita ADD CONSTRAINT FK_Cita_persona_natural 
	FOREIGN KEY (id_persona_natural) REFERENCES persona_natural (id_persona_natural)
;

ALTER TABLE persona_eps ADD CONSTRAINT FK_persona_eps_persona_juridica 
	FOREIGN KEY (id_persona_natural) REFERENCES persona_juridica (id_persona_juridica)
;

ALTER TABLE persona_eps ADD CONSTRAINT FK_persona_eps_persona_natural 
	FOREIGN KEY (id_persona_natural) REFERENCES persona_natural (id_persona_natural)
;

ALTER TABLE persona_juridica ADD CONSTRAINT FK_persona_juridica_persona 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona)
;

ALTER TABLE persona_natural ADD CONSTRAINT FK_persona_natural_persona 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona)
;

ALTER TABLE persona_cirugia ADD CONSTRAINT FK_persona_cirugia_persona 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona)
;

ALTER TABLE incapacidad ADD CONSTRAINT FK_incapacidad_persona 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona)
;

ALTER TABLE persona_examen ADD CONSTRAINT FK_persona_examen_persona 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona)
;

ALTER TABLE persona_tratamiento ADD CONSTRAINT FK_persona_tratamiento_persona 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona)
;

ALTER TABLE persona_medicamento ADD CONSTRAINT FK_persona_medicamento_persona 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona)
;

ALTER TABLE medicamento ADD CONSTRAINT FK_medicamento_persona_medicamento 
	FOREIGN KEY (id_medicamento) REFERENCES persona_medicamento (id_medicamento)
;
