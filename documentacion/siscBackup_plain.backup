--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

-- Started on 2016-07-29 11:11:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 245 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2507 (class 0 OID 0)
-- Dependencies: 245
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 180 (class 1259 OID 70175)
-- Name: acceso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE acceso (
    acce_acce bigint NOT NULL,
    acce_desc character varying(255) NOT NULL,
    acce_esta character varying(255) NOT NULL,
    acce_nombre character varying(255) NOT NULL,
    acce_url character varying(255) NOT NULL,
    version bigint
);


ALTER TABLE acceso OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 70181)
-- Name: acceso_acce_acce_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE acceso_acce_acce_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE acceso_acce_acce_seq OWNER TO postgres;

--
-- TOC entry 2508 (class 0 OID 0)
-- Dependencies: 181
-- Name: acceso_acce_acce_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE acceso_acce_acce_seq OWNED BY acceso.acce_acce;


--
-- TOC entry 182 (class 1259 OID 70183)
-- Name: accgrup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE accgrup (
    id_accgrup bigint NOT NULL,
    version bigint,
    acce_acce bigint,
    grup_grup bigint
);


ALTER TABLE accgrup OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 70186)
-- Name: accgrup_id_accgrup_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE accgrup_id_accgrup_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE accgrup_id_accgrup_seq OWNER TO postgres;

--
-- TOC entry 2509 (class 0 OID 0)
-- Dependencies: 183
-- Name: accgrup_id_accgrup_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE accgrup_id_accgrup_seq OWNED BY accgrup.id_accgrup;


--
-- TOC entry 184 (class 1259 OID 70188)
-- Name: agenda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE agenda (
    id_agenda bigint NOT NULL,
    ciudad character varying(255) NOT NULL,
    direccion character varying(255) NOT NULL,
    estado_diponible character varying(255),
    hora_bloque_fin timestamp without time zone,
    hora_bloque_inicio timestamp without time zone,
    localidad character varying(255) NOT NULL,
    numero_consultorio integer,
    tiempo_minutos_cita integer NOT NULL,
    version bigint,
    id_medico_eps bigint NOT NULL
);


ALTER TABLE agenda OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 70194)
-- Name: agenda_id_agenda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE agenda_id_agenda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE agenda_id_agenda_seq OWNER TO postgres;

--
-- TOC entry 2510 (class 0 OID 0)
-- Dependencies: 185
-- Name: agenda_id_agenda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE agenda_id_agenda_seq OWNED BY agenda.id_agenda;


--
-- TOC entry 186 (class 1259 OID 70196)
-- Name: alergia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE alergia (
    id_alergia bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    version bigint
);


ALTER TABLE alergia OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 70199)
-- Name: alergia_id_alergia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE alergia_id_alergia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE alergia_id_alergia_seq OWNER TO postgres;

--
-- TOC entry 2511 (class 0 OID 0)
-- Dependencies: 187
-- Name: alergia_id_alergia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE alergia_id_alergia_seq OWNED BY alergia.id_alergia;


--
-- TOC entry 188 (class 1259 OID 70201)
-- Name: alergia_medicamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE alergia_medicamento (
    id bigint NOT NULL,
    fecha_genracion date NOT NULL,
    version bigint,
    id_alergia bigint NOT NULL,
    id_medicamento bigint NOT NULL
);


ALTER TABLE alergia_medicamento OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 70204)
-- Name: alergia_medicamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE alergia_medicamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE alergia_medicamento_id_seq OWNER TO postgres;

--
-- TOC entry 2512 (class 0 OID 0)
-- Dependencies: 189
-- Name: alergia_medicamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE alergia_medicamento_id_seq OWNED BY alergia_medicamento.id;


--
-- TOC entry 190 (class 1259 OID 70206)
-- Name: auditusu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auditusu (
    audi_audi bigint NOT NULL,
    audi_drip character varying(255),
    audi_fech timestamp without time zone,
    audi_hostn character varying(255),
    audi_obser character varying(255),
    audi_usua bigint,
    version bigint,
    usua_usua bigint NOT NULL
);


ALTER TABLE auditusu OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 70212)
-- Name: auditusu_audi_audi_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auditusu_audi_audi_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auditusu_audi_audi_seq OWNER TO postgres;

--
-- TOC entry 2513 (class 0 OID 0)
-- Dependencies: 191
-- Name: auditusu_audi_audi_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE auditusu_audi_audi_seq OWNED BY auditusu.audi_audi;


--
-- TOC entry 192 (class 1259 OID 70214)
-- Name: cirugia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cirugia (
    id_cirugia bigint NOT NULL,
    nombre_cirugia character varying(255),
    version bigint
);


ALTER TABLE cirugia OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 70217)
-- Name: cirugia_id_cirugia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cirugia_id_cirugia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cirugia_id_cirugia_seq OWNER TO postgres;

--
-- TOC entry 2514 (class 0 OID 0)
-- Dependencies: 193
-- Name: cirugia_id_cirugia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cirugia_id_cirugia_seq OWNED BY cirugia.id_cirugia;


--
-- TOC entry 194 (class 1259 OID 70219)
-- Name: cita; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cita (
    id_cita bigint NOT NULL,
    estado_cita character varying(255) NOT NULL,
    estado_paciente_atendido boolean NOT NULL,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    observaciones character varying(255),
    valor double precision NOT NULL,
    version bigint,
    id_agenda bigint NOT NULL,
    id_paciente_eps bigint
);


ALTER TABLE cita OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 70225)
-- Name: cita_cirugia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cita_cirugia (
    id bigint NOT NULL,
    detalles character varying(255),
    fecha_generacion date NOT NULL,
    observaciones character varying(255),
    version bigint,
    id_cirugia bigint NOT NULL,
    id_cita bigint NOT NULL
);


ALTER TABLE cita_cirugia OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 70231)
-- Name: cita_cirugia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cita_cirugia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cita_cirugia_id_seq OWNER TO postgres;

--
-- TOC entry 2515 (class 0 OID 0)
-- Dependencies: 196
-- Name: cita_cirugia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cita_cirugia_id_seq OWNED BY cita_cirugia.id;


--
-- TOC entry 197 (class 1259 OID 70233)
-- Name: cita_examen; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cita_examen (
    id bigint NOT NULL,
    detalles character varying(255),
    fecha_generacion date NOT NULL,
    observaciones character varying(255),
    version bigint,
    id_cita bigint NOT NULL,
    id_examen bigint NOT NULL
);


ALTER TABLE cita_examen OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 70239)
-- Name: cita_examen_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cita_examen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cita_examen_id_seq OWNER TO postgres;

--
-- TOC entry 2516 (class 0 OID 0)
-- Dependencies: 198
-- Name: cita_examen_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cita_examen_id_seq OWNED BY cita_examen.id;


--
-- TOC entry 199 (class 1259 OID 70241)
-- Name: cita_id_cita_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cita_id_cita_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cita_id_cita_seq OWNER TO postgres;

--
-- TOC entry 2517 (class 0 OID 0)
-- Dependencies: 199
-- Name: cita_id_cita_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cita_id_cita_seq OWNED BY cita.id_cita;


--
-- TOC entry 200 (class 1259 OID 70243)
-- Name: cita_medicamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cita_medicamento (
    id bigint NOT NULL,
    fecha_genracion date NOT NULL,
    formula character varying(255) NOT NULL,
    version bigint,
    id_cita bigint NOT NULL,
    id_medicamento bigint NOT NULL
);


ALTER TABLE cita_medicamento OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 70246)
-- Name: cita_medicamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cita_medicamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cita_medicamento_id_seq OWNER TO postgres;

--
-- TOC entry 2518 (class 0 OID 0)
-- Dependencies: 201
-- Name: cita_medicamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cita_medicamento_id_seq OWNED BY cita_medicamento.id;


--
-- TOC entry 202 (class 1259 OID 70248)
-- Name: cita_tratamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cita_tratamiento (
    id bigint NOT NULL,
    fecha_generacion date NOT NULL,
    observaciones character varying(255),
    id_cita bigint NOT NULL,
    id_tratamiento bigint NOT NULL
);


ALTER TABLE cita_tratamiento OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 70251)
-- Name: cita_tratamiento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cita_tratamiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cita_tratamiento_id_seq OWNER TO postgres;

--
-- TOC entry 2519 (class 0 OID 0)
-- Dependencies: 203
-- Name: cita_tratamiento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cita_tratamiento_id_seq OWNED BY cita_tratamiento.id;


--
-- TOC entry 204 (class 1259 OID 70253)
-- Name: enfermedad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE enfermedad (
    id_enfermedad bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    version bigint
);


ALTER TABLE enfermedad OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 70256)
-- Name: enfermedad_id_enfermedad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE enfermedad_id_enfermedad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE enfermedad_id_enfermedad_seq OWNER TO postgres;

--
-- TOC entry 2520 (class 0 OID 0)
-- Dependencies: 205
-- Name: enfermedad_id_enfermedad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE enfermedad_id_enfermedad_seq OWNED BY enfermedad.id_enfermedad;


--
-- TOC entry 206 (class 1259 OID 70258)
-- Name: especialidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE especialidad (
    id_especialidad bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    version bigint
);


ALTER TABLE especialidad OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 70261)
-- Name: especialidad_id_especialidad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE especialidad_id_especialidad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE especialidad_id_especialidad_seq OWNER TO postgres;

--
-- TOC entry 2521 (class 0 OID 0)
-- Dependencies: 207
-- Name: especialidad_id_especialidad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE especialidad_id_especialidad_seq OWNED BY especialidad.id_especialidad;


--
-- TOC entry 208 (class 1259 OID 70263)
-- Name: examen; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE examen (
    id_examen bigint NOT NULL,
    nombre_examen character varying(255),
    version bigint
);


ALTER TABLE examen OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 70266)
-- Name: examen_id_examen_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE examen_id_examen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE examen_id_examen_seq OWNER TO postgres;

--
-- TOC entry 2522 (class 0 OID 0)
-- Dependencies: 209
-- Name: examen_id_examen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE examen_id_examen_seq OWNED BY examen.id_examen;


--
-- TOC entry 210 (class 1259 OID 70268)
-- Name: grupo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE grupo (
    grup_grup bigint NOT NULL,
    grup_descri character varying(255) NOT NULL,
    grup_esta character varying(255) NOT NULL,
    grup_nombr character varying(255) NOT NULL,
    version bigint
);


ALTER TABLE grupo OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 70274)
-- Name: grupo_grup_grup_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grupo_grup_grup_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE grupo_grup_grup_seq OWNER TO postgres;

--
-- TOC entry 2523 (class 0 OID 0)
-- Dependencies: 211
-- Name: grupo_grup_grup_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grupo_grup_grup_seq OWNED BY grupo.grup_grup;


--
-- TOC entry 212 (class 1259 OID 70276)
-- Name: grupusu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE grupusu (
    id_grupusu bigint NOT NULL,
    version bigint,
    grup_grup bigint,
    usua_usua bigint
);


ALTER TABLE grupusu OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 70279)
-- Name: grupusu_id_grupusu_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grupusu_id_grupusu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE grupusu_id_grupusu_seq OWNER TO postgres;

--
-- TOC entry 2524 (class 0 OID 0)
-- Dependencies: 213
-- Name: grupusu_id_grupusu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grupusu_id_grupusu_seq OWNED BY grupusu.id_grupusu;


--
-- TOC entry 214 (class 1259 OID 70281)
-- Name: incapacidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE incapacidad (
    id_incapacidad bigint NOT NULL,
    fecha_generacion date NOT NULL,
    motivo character varying(255) NOT NULL,
    periodo character varying(255) NOT NULL,
    version bigint,
    id_cita bigint NOT NULL
);


ALTER TABLE incapacidad OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 70287)
-- Name: incapacidad_id_incapacidad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE incapacidad_id_incapacidad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE incapacidad_id_incapacidad_seq OWNER TO postgres;

--
-- TOC entry 2525 (class 0 OID 0)
-- Dependencies: 215
-- Name: incapacidad_id_incapacidad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE incapacidad_id_incapacidad_seq OWNED BY incapacidad.id_incapacidad;


--
-- TOC entry 216 (class 1259 OID 70289)
-- Name: log_notifica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE log_notifica (
    lgno_lgno bigint NOT NULL,
    lgno_asunto character varying(255) NOT NULL,
    lgno_cuerpo character varying(255) NOT NULL,
    lgno_dest character varying(255) NOT NULL,
    lgno_pers bigint NOT NULL,
    lgno_siste character varying(255),
    version bigint,
    id_persona bigint NOT NULL
);


ALTER TABLE log_notifica OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 70295)
-- Name: log_notifica_lgno_lgno_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE log_notifica_lgno_lgno_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE log_notifica_lgno_lgno_seq OWNER TO postgres;

--
-- TOC entry 2526 (class 0 OID 0)
-- Dependencies: 217
-- Name: log_notifica_lgno_lgno_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE log_notifica_lgno_lgno_seq OWNED BY log_notifica.lgno_lgno;


--
-- TOC entry 218 (class 1259 OID 70297)
-- Name: medicamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE medicamento (
    id_medicamento bigint NOT NULL,
    nombre_medicamento character varying(255) NOT NULL,
    version bigint
);


ALTER TABLE medicamento OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 70300)
-- Name: medicamento_id_medicamento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE medicamento_id_medicamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE medicamento_id_medicamento_seq OWNER TO postgres;

--
-- TOC entry 2527 (class 0 OID 0)
-- Dependencies: 219
-- Name: medicamento_id_medicamento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE medicamento_id_medicamento_seq OWNED BY medicamento.id_medicamento;


--
-- TOC entry 220 (class 1259 OID 70302)
-- Name: operacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE operacion (
    id_operacion bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    version bigint
);


ALTER TABLE operacion OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 70305)
-- Name: operacion_id_operacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE operacion_id_operacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE operacion_id_operacion_seq OWNER TO postgres;

--
-- TOC entry 2528 (class 0 OID 0)
-- Dependencies: 221
-- Name: operacion_id_operacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE operacion_id_operacion_seq OWNED BY operacion.id_operacion;


--
-- TOC entry 222 (class 1259 OID 70307)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona (
    tipo_persona character varying(31) NOT NULL,
    id_persona bigint NOT NULL,
    numero_identificacion bigint NOT NULL,
    tipo_identificacion integer NOT NULL,
    version bigint
);


ALTER TABLE persona OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 70310)
-- Name: persona_eps; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona_eps (
    id_persona_eps bigint NOT NULL,
    fecha_fin date,
    fecha_inicio date,
    version bigint,
    id_eps bigint,
    id_persona bigint
);


ALTER TABLE persona_eps OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 70313)
-- Name: persona_eps_id_persona_eps_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_eps_id_persona_eps_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_eps_id_persona_eps_seq OWNER TO postgres;

--
-- TOC entry 2529 (class 0 OID 0)
-- Dependencies: 224
-- Name: persona_eps_id_persona_eps_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE persona_eps_id_persona_eps_seq OWNED BY persona_eps.id_persona_eps;


--
-- TOC entry 225 (class 1259 OID 70315)
-- Name: persona_id_persona_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_id_persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_id_persona_seq OWNER TO postgres;

--
-- TOC entry 2530 (class 0 OID 0)
-- Dependencies: 225
-- Name: persona_id_persona_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE persona_id_persona_seq OWNED BY persona.id_persona;


--
-- TOC entry 226 (class 1259 OID 70317)
-- Name: persona_juridica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona_juridica (
    fecha_constitucion timestamp without time zone,
    razon_social character varying(255),
    representante_legal character varying(255),
    id_persona bigint NOT NULL
);


ALTER TABLE persona_juridica OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 70323)
-- Name: persona_natural; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona_natural (
    apellidos character varying(255) NOT NULL,
    correo_electronico character varying(255) NOT NULL,
    direccion character varying(255) NOT NULL,
    fecha_nacimiento timestamp without time zone NOT NULL,
    fotografia character varying(255),
    genero character(1) NOT NULL,
    grupo_sanguineo character varying(255) NOT NULL,
    huella oid,
    nombres character varying(255) NOT NULL,
    rh character(1) NOT NULL,
    rol_persona_natural character varying(255),
    tarjeta_profesional character varying(255),
    telefono_celular bigint NOT NULL,
    telefono_fijo bigint NOT NULL,
    id_persona bigint NOT NULL
);


ALTER TABLE persona_natural OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 70329)
-- Name: persona_natural_alergia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona_natural_alergia (
    id_persona_alergia bigint NOT NULL,
    version bigint,
    id_alergia bigint,
    id_paciente bigint
);


ALTER TABLE persona_natural_alergia OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 70332)
-- Name: persona_natural_alergia_id_persona_alergia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_natural_alergia_id_persona_alergia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_natural_alergia_id_persona_alergia_seq OWNER TO postgres;

--
-- TOC entry 2531 (class 0 OID 0)
-- Dependencies: 229
-- Name: persona_natural_alergia_id_persona_alergia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE persona_natural_alergia_id_persona_alergia_seq OWNED BY persona_natural_alergia.id_persona_alergia;


--
-- TOC entry 230 (class 1259 OID 70334)
-- Name: persona_natural_beneficiario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona_natural_beneficiario (
    id_persona_natural_beneficiario bigint NOT NULL,
    parentezco integer,
    version bigint,
    id_beneficiario bigint,
    id_cotizante bigint
);


ALTER TABLE persona_natural_beneficiario OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 70337)
-- Name: persona_natural_beneficiario_id_persona_natural_beneficiari_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_natural_beneficiario_id_persona_natural_beneficiari_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_natural_beneficiario_id_persona_natural_beneficiari_seq OWNER TO postgres;

--
-- TOC entry 2532 (class 0 OID 0)
-- Dependencies: 231
-- Name: persona_natural_beneficiario_id_persona_natural_beneficiari_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE persona_natural_beneficiario_id_persona_natural_beneficiari_seq OWNED BY persona_natural_beneficiario.id_persona_natural_beneficiario;


--
-- TOC entry 232 (class 1259 OID 70339)
-- Name: persona_natural_enfermedad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona_natural_enfermedad (
    id_persona_enfermedad bigint NOT NULL,
    version bigint,
    id_enfermedad bigint,
    id_paciente bigint
);


ALTER TABLE persona_natural_enfermedad OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 70342)
-- Name: persona_natural_enfermedad_id_persona_enfermedad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_natural_enfermedad_id_persona_enfermedad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_natural_enfermedad_id_persona_enfermedad_seq OWNER TO postgres;

--
-- TOC entry 2533 (class 0 OID 0)
-- Dependencies: 233
-- Name: persona_natural_enfermedad_id_persona_enfermedad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE persona_natural_enfermedad_id_persona_enfermedad_seq OWNED BY persona_natural_enfermedad.id_persona_enfermedad;


--
-- TOC entry 234 (class 1259 OID 70344)
-- Name: persona_natural_especialidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona_natural_especialidad (
    id_persona_especialidad bigint NOT NULL,
    version bigint,
    id_especialidad bigint,
    id_medico bigint
);


ALTER TABLE persona_natural_especialidad OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 70347)
-- Name: persona_natural_especialidad_id_persona_especialidad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_natural_especialidad_id_persona_especialidad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_natural_especialidad_id_persona_especialidad_seq OWNER TO postgres;

--
-- TOC entry 2534 (class 0 OID 0)
-- Dependencies: 235
-- Name: persona_natural_especialidad_id_persona_especialidad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE persona_natural_especialidad_id_persona_especialidad_seq OWNED BY persona_natural_especialidad.id_persona_especialidad;


--
-- TOC entry 236 (class 1259 OID 70349)
-- Name: persona_natural_operacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona_natural_operacion (
    id_persona_operacion bigint NOT NULL,
    version bigint,
    id_operacion bigint,
    id_paciente bigint
);


ALTER TABLE persona_natural_operacion OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 70352)
-- Name: persona_natural_operacion_id_persona_operacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_natural_operacion_id_persona_operacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_natural_operacion_id_persona_operacion_seq OWNER TO postgres;

--
-- TOC entry 2535 (class 0 OID 0)
-- Dependencies: 237
-- Name: persona_natural_operacion_id_persona_operacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE persona_natural_operacion_id_persona_operacion_seq OWNED BY persona_natural_operacion.id_persona_operacion;


--
-- TOC entry 238 (class 1259 OID 70354)
-- Name: tipo_persona_natural; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipo_persona_natural (
    id_tipo_persona_natural smallint NOT NULL,
    descripcion character varying(255) NOT NULL
);


ALTER TABLE tipo_persona_natural OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 70357)
-- Name: tratamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tratamiento (
    id_tratamiento bigint NOT NULL,
    nombre_tratamiento character varying(255),
    version bigint
);


ALTER TABLE tratamiento OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 70360)
-- Name: tratamiento_id_tratamiento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tratamiento_id_tratamiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tratamiento_id_tratamiento_seq OWNER TO postgres;

--
-- TOC entry 2536 (class 0 OID 0)
-- Dependencies: 240
-- Name: tratamiento_id_tratamiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tratamiento_id_tratamiento_seq OWNED BY tratamiento.id_tratamiento;


--
-- TOC entry 241 (class 1259 OID 70362)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuario (
    usua_usua bigint NOT NULL,
    usua_block boolean,
    usua_conta bigint NOT NULL,
    usua_email character varying(255) NOT NULL,
    usua_esta character varying(255) NOT NULL,
    usua_pass character varying(255) NOT NULL,
    usua_usucd timestamp without time zone NOT NULL,
    usua_usucs character varying(255) NOT NULL,
    usua_usumd timestamp without time zone NOT NULL,
    usua_usums character varying(255) NOT NULL,
    version bigint
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 70368)
-- Name: usuario_usua_usua_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_usua_usua_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_usua_usua_seq OWNER TO postgres;

--
-- TOC entry 2537 (class 0 OID 0)
-- Dependencies: 242
-- Name: usuario_usua_usua_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_usua_usua_seq OWNED BY usuario.usua_usua;


--
-- TOC entry 243 (class 1259 OID 70370)
-- Name: usuempr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuempr (
    id_usuempr bigint NOT NULL,
    version bigint,
    id_persona bigint,
    usua_usua bigint
);


ALTER TABLE usuempr OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 70373)
-- Name: usuempr_id_usuempr_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuempr_id_usuempr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuempr_id_usuempr_seq OWNER TO postgres;

--
-- TOC entry 2538 (class 0 OID 0)
-- Dependencies: 244
-- Name: usuempr_id_usuempr_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuempr_id_usuempr_seq OWNED BY usuempr.id_usuempr;


--
-- TOC entry 2185 (class 2604 OID 70375)
-- Name: acce_acce; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acceso ALTER COLUMN acce_acce SET DEFAULT nextval('acceso_acce_acce_seq'::regclass);


--
-- TOC entry 2186 (class 2604 OID 70376)
-- Name: id_accgrup; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accgrup ALTER COLUMN id_accgrup SET DEFAULT nextval('accgrup_id_accgrup_seq'::regclass);


--
-- TOC entry 2187 (class 2604 OID 70377)
-- Name: id_agenda; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY agenda ALTER COLUMN id_agenda SET DEFAULT nextval('agenda_id_agenda_seq'::regclass);


--
-- TOC entry 2188 (class 2604 OID 70378)
-- Name: id_alergia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alergia ALTER COLUMN id_alergia SET DEFAULT nextval('alergia_id_alergia_seq'::regclass);


--
-- TOC entry 2189 (class 2604 OID 70379)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alergia_medicamento ALTER COLUMN id SET DEFAULT nextval('alergia_medicamento_id_seq'::regclass);


--
-- TOC entry 2190 (class 2604 OID 70380)
-- Name: audi_audi; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auditusu ALTER COLUMN audi_audi SET DEFAULT nextval('auditusu_audi_audi_seq'::regclass);


--
-- TOC entry 2191 (class 2604 OID 70381)
-- Name: id_cirugia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cirugia ALTER COLUMN id_cirugia SET DEFAULT nextval('cirugia_id_cirugia_seq'::regclass);


--
-- TOC entry 2192 (class 2604 OID 70382)
-- Name: id_cita; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita ALTER COLUMN id_cita SET DEFAULT nextval('cita_id_cita_seq'::regclass);


--
-- TOC entry 2193 (class 2604 OID 70383)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_cirugia ALTER COLUMN id SET DEFAULT nextval('cita_cirugia_id_seq'::regclass);


--
-- TOC entry 2194 (class 2604 OID 70384)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_examen ALTER COLUMN id SET DEFAULT nextval('cita_examen_id_seq'::regclass);


--
-- TOC entry 2195 (class 2604 OID 70385)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_medicamento ALTER COLUMN id SET DEFAULT nextval('cita_medicamento_id_seq'::regclass);


--
-- TOC entry 2196 (class 2604 OID 70386)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_tratamiento ALTER COLUMN id SET DEFAULT nextval('cita_tratamiento_id_seq'::regclass);


--
-- TOC entry 2197 (class 2604 OID 70387)
-- Name: id_enfermedad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY enfermedad ALTER COLUMN id_enfermedad SET DEFAULT nextval('enfermedad_id_enfermedad_seq'::regclass);


--
-- TOC entry 2198 (class 2604 OID 70388)
-- Name: id_especialidad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY especialidad ALTER COLUMN id_especialidad SET DEFAULT nextval('especialidad_id_especialidad_seq'::regclass);


--
-- TOC entry 2199 (class 2604 OID 70389)
-- Name: id_examen; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY examen ALTER COLUMN id_examen SET DEFAULT nextval('examen_id_examen_seq'::regclass);


--
-- TOC entry 2200 (class 2604 OID 70390)
-- Name: grup_grup; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupo ALTER COLUMN grup_grup SET DEFAULT nextval('grupo_grup_grup_seq'::regclass);


--
-- TOC entry 2201 (class 2604 OID 70391)
-- Name: id_grupusu; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupusu ALTER COLUMN id_grupusu SET DEFAULT nextval('grupusu_id_grupusu_seq'::regclass);


--
-- TOC entry 2202 (class 2604 OID 70392)
-- Name: id_incapacidad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY incapacidad ALTER COLUMN id_incapacidad SET DEFAULT nextval('incapacidad_id_incapacidad_seq'::regclass);


--
-- TOC entry 2203 (class 2604 OID 70393)
-- Name: lgno_lgno; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY log_notifica ALTER COLUMN lgno_lgno SET DEFAULT nextval('log_notifica_lgno_lgno_seq'::regclass);


--
-- TOC entry 2204 (class 2604 OID 70394)
-- Name: id_medicamento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicamento ALTER COLUMN id_medicamento SET DEFAULT nextval('medicamento_id_medicamento_seq'::regclass);


--
-- TOC entry 2205 (class 2604 OID 70395)
-- Name: id_operacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY operacion ALTER COLUMN id_operacion SET DEFAULT nextval('operacion_id_operacion_seq'::regclass);


--
-- TOC entry 2206 (class 2604 OID 70396)
-- Name: id_persona; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona ALTER COLUMN id_persona SET DEFAULT nextval('persona_id_persona_seq'::regclass);


--
-- TOC entry 2207 (class 2604 OID 70397)
-- Name: id_persona_eps; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_eps ALTER COLUMN id_persona_eps SET DEFAULT nextval('persona_eps_id_persona_eps_seq'::regclass);


--
-- TOC entry 2208 (class 2604 OID 70398)
-- Name: id_persona_alergia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_alergia ALTER COLUMN id_persona_alergia SET DEFAULT nextval('persona_natural_alergia_id_persona_alergia_seq'::regclass);


--
-- TOC entry 2209 (class 2604 OID 70399)
-- Name: id_persona_natural_beneficiario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_beneficiario ALTER COLUMN id_persona_natural_beneficiario SET DEFAULT nextval('persona_natural_beneficiario_id_persona_natural_beneficiari_seq'::regclass);


--
-- TOC entry 2210 (class 2604 OID 70400)
-- Name: id_persona_enfermedad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_enfermedad ALTER COLUMN id_persona_enfermedad SET DEFAULT nextval('persona_natural_enfermedad_id_persona_enfermedad_seq'::regclass);


--
-- TOC entry 2211 (class 2604 OID 70401)
-- Name: id_persona_especialidad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_especialidad ALTER COLUMN id_persona_especialidad SET DEFAULT nextval('persona_natural_especialidad_id_persona_especialidad_seq'::regclass);


--
-- TOC entry 2212 (class 2604 OID 70402)
-- Name: id_persona_operacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_operacion ALTER COLUMN id_persona_operacion SET DEFAULT nextval('persona_natural_operacion_id_persona_operacion_seq'::regclass);


--
-- TOC entry 2213 (class 2604 OID 70403)
-- Name: id_tratamiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tratamiento ALTER COLUMN id_tratamiento SET DEFAULT nextval('tratamiento_id_tratamiento_seq'::regclass);


--
-- TOC entry 2214 (class 2604 OID 70404)
-- Name: usua_usua; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN usua_usua SET DEFAULT nextval('usuario_usua_usua_seq'::regclass);


--
-- TOC entry 2215 (class 2604 OID 70405)
-- Name: id_usuempr; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuempr ALTER COLUMN id_usuempr SET DEFAULT nextval('usuempr_id_usuempr_seq'::regclass);


--
-- TOC entry 2435 (class 0 OID 70175)
-- Dependencies: 180
-- Data for Name: acceso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY acceso (acce_acce, acce_desc, acce_esta, acce_nombre, acce_url, version) FROM stdin;
\.


--
-- TOC entry 2539 (class 0 OID 0)
-- Dependencies: 181
-- Name: acceso_acce_acce_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('acceso_acce_acce_seq', 1, false);


--
-- TOC entry 2437 (class 0 OID 70183)
-- Dependencies: 182
-- Data for Name: accgrup; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY accgrup (id_accgrup, version, acce_acce, grup_grup) FROM stdin;
\.


--
-- TOC entry 2540 (class 0 OID 0)
-- Dependencies: 183
-- Name: accgrup_id_accgrup_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('accgrup_id_accgrup_seq', 1, false);


--
-- TOC entry 2439 (class 0 OID 70188)
-- Dependencies: 184
-- Data for Name: agenda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY agenda (id_agenda, ciudad, direccion, estado_diponible, hora_bloque_fin, hora_bloque_inicio, localidad, numero_consultorio, tiempo_minutos_cita, version, id_medico_eps) FROM stdin;
1	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-29 12:00:00	2016-07-15 08:00:00	Kennedy	111	30	0	7
2	Bogotá	Calle 72	AGENDA_DISPONIBLE	2016-07-29 12:00:00	2016-07-15 08:00:00	Kennedy	201	60	0	12
3	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-20 09:00:00	2016-07-15 15:00:00	Kennedy	111	60	0	12
4	Bogotá	sds	AGENDA_DISPONIBLE	2016-07-21 09:00:00	2016-07-21 08:00:00	Kennedy	111	60	0	12
5	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-21 12:00:00	2016-07-21 08:00:00	Kennedy	111	60	0	12
6	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-17 12:00:00	2016-07-15 17:00:00	Kennedy	103	60	0	12
7	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-19 21:00:00	2016-07-15 19:00:00	Kennedy	103	60	0	12
8	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-19 21:00:00	2016-07-15 19:00:00	Kennedy	103	60	0	12
9	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-19 12:00:00	2016-07-18 08:00:00	Kennedy	103	60	0	12
10	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-15 19:00:00	2016-07-15 18:00:00	Kennedy	103	60	0	12
11	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-15 19:00:00	2016-07-15 18:00:00	Kennedy	103	60	0	12
12	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-16 12:00:00	2016-07-16 08:00:00	Kennedy	103	90	0	12
13	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-16 13:00:00	2016-07-16 12:00:00	Kennedy	103	90	0	12
14	Bogotá	Calle falsa 123	AGENDA_DISPONIBLE	2016-07-22 12:00:00	2016-07-17 08:00:00	Kennedy	103	60	0	11
15	Bogotá	callle 123	AGENDA_DISPONIBLE	2016-07-22 12:00:00	2016-07-18 08:00:00	Bogotá	123	60	0	12
\.


--
-- TOC entry 2541 (class 0 OID 0)
-- Dependencies: 185
-- Name: agenda_id_agenda_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('agenda_id_agenda_seq', 15, true);


--
-- TOC entry 2441 (class 0 OID 70196)
-- Dependencies: 186
-- Data for Name: alergia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY alergia (id_alergia, descripcion, version) FROM stdin;
\.


--
-- TOC entry 2542 (class 0 OID 0)
-- Dependencies: 187
-- Name: alergia_id_alergia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('alergia_id_alergia_seq', 1, false);


--
-- TOC entry 2443 (class 0 OID 70201)
-- Dependencies: 188
-- Data for Name: alergia_medicamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY alergia_medicamento (id, fecha_genracion, version, id_alergia, id_medicamento) FROM stdin;
\.


--
-- TOC entry 2543 (class 0 OID 0)
-- Dependencies: 189
-- Name: alergia_medicamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('alergia_medicamento_id_seq', 1, false);


--
-- TOC entry 2445 (class 0 OID 70206)
-- Dependencies: 190
-- Data for Name: auditusu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auditusu (audi_audi, audi_drip, audi_fech, audi_hostn, audi_obser, audi_usua, version, usua_usua) FROM stdin;
1	181.49.252.121	2016-07-15 11:36:54.539	localhost	Crear usuario	1	0	1
2	181.49.252.121	2016-07-15 11:37:13.391	localhost	Crear usuario	1	0	1
3	181.49.252.121	2016-07-15 11:37:37.018	localhost	Crear usuario	1	0	1
4	181.49.252.121	2016-07-15 11:56:13.584	localhost	Crear usuario	1	0	1
5	181.49.252.121	2016-07-15 11:56:49.984	localhost	Crear usuario	1	0	1
6	181.49.252.121	2016-07-15 11:57:12.532	localhost	Crear usuario	1	0	1
7	181.49.252.121	2016-07-15 11:57:46.691	localhost	Crear usuario	1	0	1
\.


--
-- TOC entry 2544 (class 0 OID 0)
-- Dependencies: 191
-- Name: auditusu_audi_audi_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auditusu_audi_audi_seq', 7, true);


--
-- TOC entry 2447 (class 0 OID 70214)
-- Dependencies: 192
-- Data for Name: cirugia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cirugia (id_cirugia, nombre_cirugia, version) FROM stdin;
\.


--
-- TOC entry 2545 (class 0 OID 0)
-- Dependencies: 193
-- Name: cirugia_id_cirugia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cirugia_id_cirugia_seq', 1, false);


--
-- TOC entry 2449 (class 0 OID 70219)
-- Dependencies: 194
-- Data for Name: cita; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cita (id_cita, estado_cita, estado_paciente_atendido, hora_fin, hora_inicio, observaciones, valor, version, id_agenda, id_paciente_eps) FROM stdin;
84	DISPONIBLE	f	2016-07-16 09:30:00	2016-07-16 08:00:00	\N	0	0	12	\N
85	DISPONIBLE	f	2016-07-16 11:00:00	2016-07-16 09:30:00	\N	0	0	12	\N
86	DISPONIBLE	f	2016-07-16 12:30:00	2016-07-16 11:00:00	\N	0	0	12	\N
87	DISPONIBLE	f	2016-07-16 13:30:00	2016-07-16 12:00:00	\N	0	0	13	\N
88	DISPONIBLE	f	2016-07-18 09:00:00	2016-07-18 08:00:00	\N	0	0	14	\N
91	DISPONIBLE	f	2016-07-18 12:00:00	2016-07-18 11:00:00	\N	0	0	14	\N
92	DISPONIBLE	f	2016-07-19 09:00:00	2016-07-19 08:00:00	\N	0	0	14	\N
93	DISPONIBLE	f	2016-07-19 10:00:00	2016-07-19 09:00:00	\N	0	0	14	\N
94	DISPONIBLE	f	2016-07-19 11:00:00	2016-07-19 10:00:00	\N	0	0	14	\N
95	DISPONIBLE	f	2016-07-19 12:00:00	2016-07-19 11:00:00	\N	0	0	14	\N
96	DISPONIBLE	f	2016-07-20 09:00:00	2016-07-20 08:00:00	\N	0	0	14	\N
97	DISPONIBLE	f	2016-07-20 10:00:00	2016-07-20 09:00:00	\N	0	0	14	\N
98	DISPONIBLE	f	2016-07-20 11:00:00	2016-07-20 10:00:00	\N	0	0	14	\N
99	DISPONIBLE	f	2016-07-20 12:00:00	2016-07-20 11:00:00	\N	0	0	14	\N
100	DISPONIBLE	f	2016-07-21 09:00:00	2016-07-21 08:00:00	\N	0	0	14	\N
101	DISPONIBLE	f	2016-07-21 10:00:00	2016-07-21 09:00:00	\N	0	0	14	\N
102	DISPONIBLE	f	2016-07-21 11:00:00	2016-07-21 10:00:00	\N	0	0	14	\N
103	DISPONIBLE	f	2016-07-21 12:00:00	2016-07-21 11:00:00	\N	0	0	14	\N
104	DISPONIBLE	f	2016-07-18 09:00:00	2016-07-18 08:00:00	\N	0	0	15	\N
106	DISPONIBLE	f	2016-07-18 11:00:00	2016-07-18 10:00:00	\N	0	0	15	\N
107	DISPONIBLE	f	2016-07-18 12:00:00	2016-07-18 11:00:00	\N	0	0	15	\N
108	DISPONIBLE	f	2016-07-19 09:00:00	2016-07-19 08:00:00	\N	0	0	15	\N
109	DISPONIBLE	f	2016-07-19 10:00:00	2016-07-19 09:00:00	\N	0	0	15	\N
110	DISPONIBLE	f	2016-07-19 11:00:00	2016-07-19 10:00:00	\N	0	0	15	\N
111	DISPONIBLE	f	2016-07-19 12:00:00	2016-07-19 11:00:00	\N	0	0	15	\N
105	APARTADA	f	2016-07-18 10:00:00	2016-07-18 09:00:00	\N	0	1	15	3
89	PACIENTE_NO_ASISTIO_A_CITA	f	2016-07-18 10:00:00	2016-07-18 09:00:00	\N	0	3	14	3
90	APARTADA	f	2016-07-18 11:00:00	2016-07-18 10:00:00	\N	0	1	14	3
\.


--
-- TOC entry 2450 (class 0 OID 70225)
-- Dependencies: 195
-- Data for Name: cita_cirugia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cita_cirugia (id, detalles, fecha_generacion, observaciones, version, id_cirugia, id_cita) FROM stdin;
\.


--
-- TOC entry 2546 (class 0 OID 0)
-- Dependencies: 196
-- Name: cita_cirugia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cita_cirugia_id_seq', 1, false);


--
-- TOC entry 2452 (class 0 OID 70233)
-- Dependencies: 197
-- Data for Name: cita_examen; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cita_examen (id, detalles, fecha_generacion, observaciones, version, id_cita, id_examen) FROM stdin;
\.


--
-- TOC entry 2547 (class 0 OID 0)
-- Dependencies: 198
-- Name: cita_examen_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cita_examen_id_seq', 1, false);


--
-- TOC entry 2548 (class 0 OID 0)
-- Dependencies: 199
-- Name: cita_id_cita_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cita_id_cita_seq', 111, true);


--
-- TOC entry 2455 (class 0 OID 70243)
-- Dependencies: 200
-- Data for Name: cita_medicamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cita_medicamento (id, fecha_genracion, formula, version, id_cita, id_medicamento) FROM stdin;
\.


--
-- TOC entry 2549 (class 0 OID 0)
-- Dependencies: 201
-- Name: cita_medicamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cita_medicamento_id_seq', 1, false);


--
-- TOC entry 2457 (class 0 OID 70248)
-- Dependencies: 202
-- Data for Name: cita_tratamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cita_tratamiento (id, fecha_generacion, observaciones, id_cita, id_tratamiento) FROM stdin;
\.


--
-- TOC entry 2550 (class 0 OID 0)
-- Dependencies: 203
-- Name: cita_tratamiento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cita_tratamiento_id_seq', 1, false);


--
-- TOC entry 2459 (class 0 OID 70253)
-- Dependencies: 204
-- Data for Name: enfermedad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY enfermedad (id_enfermedad, descripcion, version) FROM stdin;
\.


--
-- TOC entry 2551 (class 0 OID 0)
-- Dependencies: 205
-- Name: enfermedad_id_enfermedad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('enfermedad_id_enfermedad_seq', 1, false);


--
-- TOC entry 2461 (class 0 OID 70258)
-- Dependencies: 206
-- Data for Name: especialidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY especialidad (id_especialidad, descripcion, version) FROM stdin;
1	Medicina General	\N
2	Cardiologia	\N
3	Odontlogia	\N
4	Pediatra	\N
\.


--
-- TOC entry 2552 (class 0 OID 0)
-- Dependencies: 207
-- Name: especialidad_id_especialidad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('especialidad_id_especialidad_seq', 4, true);


--
-- TOC entry 2463 (class 0 OID 70263)
-- Dependencies: 208
-- Data for Name: examen; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY examen (id_examen, nombre_examen, version) FROM stdin;
\.


--
-- TOC entry 2553 (class 0 OID 0)
-- Dependencies: 209
-- Name: examen_id_examen_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('examen_id_examen_seq', 1, false);


--
-- TOC entry 2465 (class 0 OID 70268)
-- Dependencies: 210
-- Data for Name: grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grupo (grup_grup, grup_descri, grup_esta, grup_nombr, version) FROM stdin;
1	Administrador	Activo	Administrador	0
2	Medico	Activo	Medico	0
3	Paciente	Activo	Paciente	0
\.


--
-- TOC entry 2554 (class 0 OID 0)
-- Dependencies: 211
-- Name: grupo_grup_grup_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grupo_grup_grup_seq', 3, true);


--
-- TOC entry 2467 (class 0 OID 70276)
-- Dependencies: 212
-- Data for Name: grupusu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grupusu (id_grupusu, version, grup_grup, usua_usua) FROM stdin;
1	0	1	1
2	0	2	2
3	0	2	3
4	0	2	4
6	0	3	6
7	0	3	7
8	0	3	8
13	0	2	5
16	0	2	5
17	0	3	5
\.


--
-- TOC entry 2555 (class 0 OID 0)
-- Dependencies: 213
-- Name: grupusu_id_grupusu_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grupusu_id_grupusu_seq', 17, true);


--
-- TOC entry 2469 (class 0 OID 70281)
-- Dependencies: 214
-- Data for Name: incapacidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY incapacidad (id_incapacidad, fecha_generacion, motivo, periodo, version, id_cita) FROM stdin;
\.


--
-- TOC entry 2556 (class 0 OID 0)
-- Dependencies: 215
-- Name: incapacidad_id_incapacidad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('incapacidad_id_incapacidad_seq', 1, false);


--
-- TOC entry 2471 (class 0 OID 70289)
-- Dependencies: 216
-- Data for Name: log_notifica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY log_notifica (lgno_lgno, lgno_asunto, lgno_cuerpo, lgno_dest, lgno_pers, lgno_siste, version, id_persona) FROM stdin;
1	Creacion Usuario	Hola, les informamos la creacion del usuario	medico1@medico.com	0	Modulo Seguridad	0	3
2	Creacion Usuario	Hola, les informamos la creacion del usuario	medico2@medico.com	0	Modulo Seguridad	0	4
3	Creacion Usuario	Hola, les informamos la creacion del usuario	medico3@medico.com	0	Modulo Seguridad	0	5
4	Creacion Usuario	Hola, les informamos la creacion del usuario	paciente1@paciente.com	0	Modulo Seguridad	0	11
5	Creacion Usuario	Hola, les informamos la creacion del usuario	paciente2@paciente.com	0	Modulo Seguridad	0	12
6	Creacion Usuario	Hola, les informamos la creacion del usuario	paciente3@paciente.com	0	Modulo Seguridad	0	13
\.


--
-- TOC entry 2557 (class 0 OID 0)
-- Dependencies: 217
-- Name: log_notifica_lgno_lgno_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('log_notifica_lgno_lgno_seq', 6, true);


--
-- TOC entry 2473 (class 0 OID 70297)
-- Dependencies: 218
-- Data for Name: medicamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY medicamento (id_medicamento, nombre_medicamento, version) FROM stdin;
\.


--
-- TOC entry 2558 (class 0 OID 0)
-- Dependencies: 219
-- Name: medicamento_id_medicamento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('medicamento_id_medicamento_seq', 1, false);


--
-- TOC entry 2475 (class 0 OID 70302)
-- Dependencies: 220
-- Data for Name: operacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY operacion (id_operacion, descripcion, version) FROM stdin;
\.


--
-- TOC entry 2559 (class 0 OID 0)
-- Dependencies: 221
-- Name: operacion_id_operacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('operacion_id_operacion_seq', 1, false);


--
-- TOC entry 2477 (class 0 OID 70307)
-- Dependencies: 222
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona (tipo_persona, id_persona, numero_identificacion, tipo_identificacion, version) FROM stdin;
JURIDICA	1	800024075	4	0
NATURAL	2	1030626305	0	0
JURIDICA	9	800024079	4	0
JURIDICA	6	800024076	4	0
JURIDICA	7	800024077	4	0
JURIDICA	8	800024078	4	0
NATURAL	3	103654895	0	1
NATURAL	4	10365321	0	1
NATURAL	5	10365333	0	0
NATURAL	12	10306433333	0	0
NATURAL	13	1245454545	0	0
NATURAL	14	124542554	0	0
NATURAL	11	10306458646	0	1
\.


--
-- TOC entry 2478 (class 0 OID 70310)
-- Dependencies: 223
-- Data for Name: persona_eps; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona_eps (id_persona_eps, fecha_fin, fecha_inicio, version, id_eps, id_persona) FROM stdin;
1	\N	2011-01-10	\N	7	5
2	\N	2011-01-10	\N	1	11
3	\N	2011-01-10	\N	6	12
4	\N	2011-01-10	\N	7	13
5	\N	2011-01-10	\N	8	14
6	\N	2011-01-10	\N	1	5
7	\N	2011-01-10	\N	6	5
8	\N	2011-01-10	\N	8	5
9	\N	2011-01-10	\N	9	5
10	\N	2011-01-10	\N	1	3
11	\N	2011-01-10	\N	6	3
12	\N	2011-01-10	\N	6	4
\.


--
-- TOC entry 2560 (class 0 OID 0)
-- Dependencies: 224
-- Name: persona_eps_id_persona_eps_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_eps_id_persona_eps_seq', 12, true);


--
-- TOC entry 2561 (class 0 OID 0)
-- Dependencies: 225
-- Name: persona_id_persona_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_id_persona_seq', 14, true);


--
-- TOC entry 2481 (class 0 OID 70317)
-- Dependencies: 226
-- Data for Name: persona_juridica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona_juridica (fecha_constitucion, razon_social, representante_legal, id_persona) FROM stdin;
1990-01-10 00:00:00	Famisanar	Famisanar	6
1990-01-10 00:00:00	CafeSalud	CafeSalud	8
1990-01-10 00:00:00	Colsanitas	Colsanitas	1
1990-01-10 00:00:00	Nueva EPS	Nueva EPS	7
1990-01-10 00:00:00	MI EPS	MI EPS	9
\.


--
-- TOC entry 2482 (class 0 OID 70323)
-- Dependencies: 227
-- Data for Name: persona_natural; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona_natural (apellidos, correo_electronico, direccion, fecha_nacimiento, fotografia, genero, grupo_sanguineo, huella, nombres, rh, rol_persona_natural, tarjeta_profesional, telefono_celular, telefono_fijo, id_persona) FROM stdin;
Sanchez	admin@admin.com	Calle 56A sur	1993-06-20 00:00:00		M	O	\N	Julio	+	PACIENTE		3134225022	7753854	2
Garcia Garcia	medico1@medico.com	Calle falsa 123	1990-01-08 00:00:00	\N	M	A	\N	Pedro Pablo	+	MEDICO	123574564646	3136987852	4587952	3
Perez Perez	medico2@medico.com	Calle falsa 123	1990-01-08 00:00:00	\N	M	B	\N	Juan Carlos	+	MEDICO	123574564646	3136987852	4587952	4
Ramos	medico3@medico.com	Calle falsa 123	1990-01-08 00:00:00	\N	F	AB	\N	Marcela	+	MEDICO	123574564646	3136987852	4587952	5
Perez Perez	paciente2@paciente.com	Calle falsa 123	1988-01-12 00:00:00	\N	F	B	\N	Juliana	+	PACIENTE	\N	3214554557	452122	12
Acosta Acosta	paciente3@paciente.com	Calle falsa 123	1991-06-11 00:00:00	\N	M	B	\N	Carlos	-	PACIENTE	\N	3132131312	1212121	13
Acosta Acosta	paciente4@paciente.com	Calle falsa 123	1991-06-11 00:00:00	\N	M	O	\N	Luis	-	PACIENTE	\N	3132131312	1212121	14
Duarte Duarte1	paciente1@paciente.com	Calle falsa 123	1988-01-12 00:00:00	\N	M	A	\N	Diana Marcela	+	PACIENTE	\N	3214556411	452122	11
\.


--
-- TOC entry 2483 (class 0 OID 70329)
-- Dependencies: 228
-- Data for Name: persona_natural_alergia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona_natural_alergia (id_persona_alergia, version, id_alergia, id_paciente) FROM stdin;
\.


--
-- TOC entry 2562 (class 0 OID 0)
-- Dependencies: 229
-- Name: persona_natural_alergia_id_persona_alergia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_natural_alergia_id_persona_alergia_seq', 1, false);


--
-- TOC entry 2485 (class 0 OID 70334)
-- Dependencies: 230
-- Data for Name: persona_natural_beneficiario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona_natural_beneficiario (id_persona_natural_beneficiario, parentezco, version, id_beneficiario, id_cotizante) FROM stdin;
\.


--
-- TOC entry 2563 (class 0 OID 0)
-- Dependencies: 231
-- Name: persona_natural_beneficiario_id_persona_natural_beneficiari_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_natural_beneficiario_id_persona_natural_beneficiari_seq', 1, false);


--
-- TOC entry 2487 (class 0 OID 70339)
-- Dependencies: 232
-- Data for Name: persona_natural_enfermedad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona_natural_enfermedad (id_persona_enfermedad, version, id_enfermedad, id_paciente) FROM stdin;
\.


--
-- TOC entry 2564 (class 0 OID 0)
-- Dependencies: 233
-- Name: persona_natural_enfermedad_id_persona_enfermedad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_natural_enfermedad_id_persona_enfermedad_seq', 1, false);


--
-- TOC entry 2489 (class 0 OID 70344)
-- Dependencies: 234
-- Data for Name: persona_natural_especialidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona_natural_especialidad (id_persona_especialidad, version, id_especialidad, id_medico) FROM stdin;
1	\N	1	2
2	\N	2	2
3	\N	3	2
4	\N	4	2
5	\N	1	3
6	\N	2	3
7	\N	3	3
8	\N	4	4
9	\N	3	4
10	\N	2	4
11	\N	1	4
\.


--
-- TOC entry 2565 (class 0 OID 0)
-- Dependencies: 235
-- Name: persona_natural_especialidad_id_persona_especialidad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_natural_especialidad_id_persona_especialidad_seq', 11, true);


--
-- TOC entry 2491 (class 0 OID 70349)
-- Dependencies: 236
-- Data for Name: persona_natural_operacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona_natural_operacion (id_persona_operacion, version, id_operacion, id_paciente) FROM stdin;
\.


--
-- TOC entry 2566 (class 0 OID 0)
-- Dependencies: 237
-- Name: persona_natural_operacion_id_persona_operacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_natural_operacion_id_persona_operacion_seq', 1, false);


--
-- TOC entry 2493 (class 0 OID 70354)
-- Dependencies: 238
-- Data for Name: tipo_persona_natural; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipo_persona_natural (id_tipo_persona_natural, descripcion) FROM stdin;
\.


--
-- TOC entry 2494 (class 0 OID 70357)
-- Dependencies: 239
-- Data for Name: tratamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tratamiento (id_tratamiento, nombre_tratamiento, version) FROM stdin;
\.


--
-- TOC entry 2567 (class 0 OID 0)
-- Dependencies: 240
-- Name: tratamiento_id_tratamiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tratamiento_id_tratamiento_seq', 1, false);


--
-- TOC entry 2496 (class 0 OID 70362)
-- Dependencies: 241
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (usua_usua, usua_block, usua_conta, usua_email, usua_esta, usua_pass, usua_usucd, usua_usucs, usua_usumd, usua_usums, version) FROM stdin;
1	f	0	admin@admin.com	Activo	21232f297a57a5a743894a0e4a801fc3	2016-05-22 10:54:37.46	SYS	2016-05-22 10:54:37.46	SYS	0
2	f	0	medico1@medico.com	Activo	21232f297a57a5a743894a0e4a801fc3	2016-07-15 11:36:54.034	Administrador	2016-07-15 11:36:54.034	El usuario mismo	0
3	f	0	medico2@medico.com	Activo	21232f297a57a5a743894a0e4a801fc3	2016-07-15 11:37:13.293	Administrador	2016-07-15 11:37:13.293	El usuario mismo	0
4	f	0	medico3@medico.com	Activo	21232f297a57a5a743894a0e4a801fc3	2016-07-15 11:37:36.862	Administrador	2016-07-15 11:37:36.862	El usuario mismo	0
6	f	0	paciente2@paciente.com	Activo	21232f297a57a5a743894a0e4a801fc3	2016-07-15 11:56:49.881	Administrador	2016-07-15 11:56:49.881	El usuario mismo	0
7	f	0	paciente3@paciente.com	Activo	21232f297a57a5a743894a0e4a801fc3	2016-07-15 11:57:12.365	Administrador	2016-07-15 11:57:12.365	El usuario mismo	0
8	f	0	paciente4@paciente.com	Activo	21232f297a57a5a743894a0e4a801fc3	2016-07-15 11:57:46.605	Administrador	2016-07-15 11:57:46.605	El usuario mismo	0
5	f	0	paciente1@paciente.com	Activo	21232f297a57a5a743894a0e4a801fc3	2016-07-15 11:56:13.493	Administrador	2016-07-15 11:56:13.493	El usuario mismo	1
\.


--
-- TOC entry 2568 (class 0 OID 0)
-- Dependencies: 242
-- Name: usuario_usua_usua_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_usua_usua_seq', 8, true);


--
-- TOC entry 2498 (class 0 OID 70370)
-- Dependencies: 243
-- Data for Name: usuempr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuempr (id_usuempr, version, id_persona, usua_usua) FROM stdin;
\.


--
-- TOC entry 2569 (class 0 OID 0)
-- Dependencies: 244
-- Name: usuempr_id_usuempr_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuempr_id_usuempr_seq', 1, false);


--
-- TOC entry 2217 (class 2606 OID 70407)
-- Name: acceso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acceso
    ADD CONSTRAINT acceso_pkey PRIMARY KEY (acce_acce);


--
-- TOC entry 2219 (class 2606 OID 70409)
-- Name: accgrup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accgrup
    ADD CONSTRAINT accgrup_pkey PRIMARY KEY (id_accgrup);


--
-- TOC entry 2221 (class 2606 OID 70411)
-- Name: agenda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY agenda
    ADD CONSTRAINT agenda_pkey PRIMARY KEY (id_agenda);


--
-- TOC entry 2225 (class 2606 OID 70413)
-- Name: alergia_medicamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alergia_medicamento
    ADD CONSTRAINT alergia_medicamento_pkey PRIMARY KEY (id);


--
-- TOC entry 2223 (class 2606 OID 70415)
-- Name: alergia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alergia
    ADD CONSTRAINT alergia_pkey PRIMARY KEY (id_alergia);


--
-- TOC entry 2227 (class 2606 OID 70417)
-- Name: auditusu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auditusu
    ADD CONSTRAINT auditusu_pkey PRIMARY KEY (audi_audi);


--
-- TOC entry 2229 (class 2606 OID 70419)
-- Name: cirugia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cirugia
    ADD CONSTRAINT cirugia_pkey PRIMARY KEY (id_cirugia);


--
-- TOC entry 2233 (class 2606 OID 70421)
-- Name: cita_cirugia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_cirugia
    ADD CONSTRAINT cita_cirugia_pkey PRIMARY KEY (id);


--
-- TOC entry 2235 (class 2606 OID 70423)
-- Name: cita_examen_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_examen
    ADD CONSTRAINT cita_examen_pkey PRIMARY KEY (id);


--
-- TOC entry 2237 (class 2606 OID 70425)
-- Name: cita_medicamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_medicamento
    ADD CONSTRAINT cita_medicamento_pkey PRIMARY KEY (id);


--
-- TOC entry 2231 (class 2606 OID 70427)
-- Name: cita_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT cita_pkey PRIMARY KEY (id_cita);


--
-- TOC entry 2239 (class 2606 OID 70429)
-- Name: cita_tratamiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_tratamiento
    ADD CONSTRAINT cita_tratamiento_pkey PRIMARY KEY (id);


--
-- TOC entry 2241 (class 2606 OID 70431)
-- Name: enfermedad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY enfermedad
    ADD CONSTRAINT enfermedad_pkey PRIMARY KEY (id_enfermedad);


--
-- TOC entry 2243 (class 2606 OID 70433)
-- Name: especialidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY especialidad
    ADD CONSTRAINT especialidad_pkey PRIMARY KEY (id_especialidad);


--
-- TOC entry 2245 (class 2606 OID 70435)
-- Name: examen_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY examen
    ADD CONSTRAINT examen_pkey PRIMARY KEY (id_examen);


--
-- TOC entry 2247 (class 2606 OID 70437)
-- Name: grupo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupo
    ADD CONSTRAINT grupo_pkey PRIMARY KEY (grup_grup);


--
-- TOC entry 2249 (class 2606 OID 70439)
-- Name: grupusu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupusu
    ADD CONSTRAINT grupusu_pkey PRIMARY KEY (id_grupusu);


--
-- TOC entry 2251 (class 2606 OID 70441)
-- Name: incapacidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY incapacidad
    ADD CONSTRAINT incapacidad_pkey PRIMARY KEY (id_incapacidad);


--
-- TOC entry 2253 (class 2606 OID 70443)
-- Name: log_notifica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY log_notifica
    ADD CONSTRAINT log_notifica_pkey PRIMARY KEY (lgno_lgno);


--
-- TOC entry 2255 (class 2606 OID 70445)
-- Name: medicamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicamento
    ADD CONSTRAINT medicamento_pkey PRIMARY KEY (id_medicamento);


--
-- TOC entry 2257 (class 2606 OID 70447)
-- Name: operacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY operacion
    ADD CONSTRAINT operacion_pkey PRIMARY KEY (id_operacion);


--
-- TOC entry 2261 (class 2606 OID 70449)
-- Name: persona_eps_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_eps
    ADD CONSTRAINT persona_eps_pkey PRIMARY KEY (id_persona_eps);


--
-- TOC entry 2263 (class 2606 OID 70451)
-- Name: persona_juridica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_juridica
    ADD CONSTRAINT persona_juridica_pkey PRIMARY KEY (id_persona);


--
-- TOC entry 2267 (class 2606 OID 70453)
-- Name: persona_natural_alergia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_alergia
    ADD CONSTRAINT persona_natural_alergia_pkey PRIMARY KEY (id_persona_alergia);


--
-- TOC entry 2269 (class 2606 OID 70455)
-- Name: persona_natural_beneficiario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_beneficiario
    ADD CONSTRAINT persona_natural_beneficiario_pkey PRIMARY KEY (id_persona_natural_beneficiario);


--
-- TOC entry 2271 (class 2606 OID 70457)
-- Name: persona_natural_enfermedad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_enfermedad
    ADD CONSTRAINT persona_natural_enfermedad_pkey PRIMARY KEY (id_persona_enfermedad);


--
-- TOC entry 2273 (class 2606 OID 70459)
-- Name: persona_natural_especialidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_especialidad
    ADD CONSTRAINT persona_natural_especialidad_pkey PRIMARY KEY (id_persona_especialidad);


--
-- TOC entry 2275 (class 2606 OID 70461)
-- Name: persona_natural_operacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_operacion
    ADD CONSTRAINT persona_natural_operacion_pkey PRIMARY KEY (id_persona_operacion);


--
-- TOC entry 2265 (class 2606 OID 70463)
-- Name: persona_natural_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural
    ADD CONSTRAINT persona_natural_pkey PRIMARY KEY (id_persona);


--
-- TOC entry 2259 (class 2606 OID 70465)
-- Name: persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);


--
-- TOC entry 2277 (class 2606 OID 70467)
-- Name: tipo_persona_natural_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_persona_natural
    ADD CONSTRAINT tipo_persona_natural_pkey PRIMARY KEY (id_tipo_persona_natural);


--
-- TOC entry 2279 (class 2606 OID 70469)
-- Name: tratamiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tratamiento
    ADD CONSTRAINT tratamiento_pkey PRIMARY KEY (id_tratamiento);


--
-- TOC entry 2281 (class 2606 OID 70471)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usua_usua);


--
-- TOC entry 2283 (class 2606 OID 70473)
-- Name: usuempr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuempr
    ADD CONSTRAINT usuempr_pkey PRIMARY KEY (id_usuempr);


--
-- TOC entry 2286 (class 2606 OID 70474)
-- Name: fk_2ep30xu7rpipsnjn60yj4suaj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY agenda
    ADD CONSTRAINT fk_2ep30xu7rpipsnjn60yj4suaj FOREIGN KEY (id_medico_eps) REFERENCES persona_eps(id_persona_eps);


--
-- TOC entry 2293 (class 2606 OID 70479)
-- Name: fk_2tl87f012ttx3b05r8pghv148; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_cirugia
    ADD CONSTRAINT fk_2tl87f012ttx3b05r8pghv148 FOREIGN KEY (id_cirugia) REFERENCES cirugia(id_cirugia);


--
-- TOC entry 2304 (class 2606 OID 70484)
-- Name: fk_3e35vwcgyno55lmyel4jx4jf5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY log_notifica
    ADD CONSTRAINT fk_3e35vwcgyno55lmyel4jx4jf5 FOREIGN KEY (id_persona) REFERENCES persona(id_persona);


--
-- TOC entry 2291 (class 2606 OID 70489)
-- Name: fk_4ugcdbb6htjonkr1c8a7v18l0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk_4ugcdbb6htjonkr1c8a7v18l0 FOREIGN KEY (id_paciente_eps) REFERENCES persona_eps(id_persona_eps);


--
-- TOC entry 2309 (class 2606 OID 70494)
-- Name: fk_6qbfc05o3nukeg4qvo9aoukdj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_alergia
    ADD CONSTRAINT fk_6qbfc05o3nukeg4qvo9aoukdj FOREIGN KEY (id_alergia) REFERENCES alergia(id_alergia);


--
-- TOC entry 2305 (class 2606 OID 70499)
-- Name: fk_6qc0r2i6sw8ji938loyaj3tuo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_eps
    ADD CONSTRAINT fk_6qc0r2i6sw8ji938loyaj3tuo FOREIGN KEY (id_eps) REFERENCES persona_juridica(id_persona);


--
-- TOC entry 2313 (class 2606 OID 70504)
-- Name: fk_8ibo9rlkq9f0lvmtmbc8n2f2r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_enfermedad
    ADD CONSTRAINT fk_8ibo9rlkq9f0lvmtmbc8n2f2r FOREIGN KEY (id_enfermedad) REFERENCES enfermedad(id_enfermedad);


--
-- TOC entry 2310 (class 2606 OID 70509)
-- Name: fk_8xivrxe0frol5k6ppytvvtc2l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_alergia
    ADD CONSTRAINT fk_8xivrxe0frol5k6ppytvvtc2l FOREIGN KEY (id_paciente) REFERENCES persona_natural(id_persona);


--
-- TOC entry 2301 (class 2606 OID 70514)
-- Name: fk_93jo884ohgw3dd052tpqs9lhn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupusu
    ADD CONSTRAINT fk_93jo884ohgw3dd052tpqs9lhn FOREIGN KEY (usua_usua) REFERENCES usuario(usua_usua);


--
-- TOC entry 2306 (class 2606 OID 70519)
-- Name: fk_9slmy6a81dg5sldtml5mdda9g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_eps
    ADD CONSTRAINT fk_9slmy6a81dg5sldtml5mdda9g FOREIGN KEY (id_persona) REFERENCES persona_natural(id_persona);


--
-- TOC entry 2314 (class 2606 OID 70524)
-- Name: fk_a0sdon6a7e7ntgmg6el2el2h8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_enfermedad
    ADD CONSTRAINT fk_a0sdon6a7e7ntgmg6el2el2h8 FOREIGN KEY (id_paciente) REFERENCES persona_natural(id_persona);


--
-- TOC entry 2294 (class 2606 OID 70529)
-- Name: fk_am72k0jvcnquybpkhpxy50ex8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_cirugia
    ADD CONSTRAINT fk_am72k0jvcnquybpkhpxy50ex8 FOREIGN KEY (id_cita) REFERENCES cita(id_cita);


--
-- TOC entry 2287 (class 2606 OID 70534)
-- Name: fk_axn1vetxi8y7o45i44k5c16d2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alergia_medicamento
    ADD CONSTRAINT fk_axn1vetxi8y7o45i44k5c16d2 FOREIGN KEY (id_medicamento) REFERENCES medicamento(id_medicamento);


--
-- TOC entry 2319 (class 2606 OID 70539)
-- Name: fk_bj7dxwudim8t8it50iq63v30g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuempr
    ADD CONSTRAINT fk_bj7dxwudim8t8it50iq63v30g FOREIGN KEY (id_persona) REFERENCES persona_juridica(id_persona);


--
-- TOC entry 2292 (class 2606 OID 70544)
-- Name: fk_druqtxdftvlw9tl1j2q9ddw3i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk_druqtxdftvlw9tl1j2q9ddw3i FOREIGN KEY (id_agenda) REFERENCES agenda(id_agenda);


--
-- TOC entry 2315 (class 2606 OID 70549)
-- Name: fk_estfh1bk1yhvlbhpmf4l0n90j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_especialidad
    ADD CONSTRAINT fk_estfh1bk1yhvlbhpmf4l0n90j FOREIGN KEY (id_especialidad) REFERENCES especialidad(id_especialidad);


--
-- TOC entry 2284 (class 2606 OID 70554)
-- Name: fk_i7s1kx4dsvdrqu254e1euq666; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accgrup
    ADD CONSTRAINT fk_i7s1kx4dsvdrqu254e1euq666 FOREIGN KEY (acce_acce) REFERENCES acceso(acce_acce);


--
-- TOC entry 2289 (class 2606 OID 70559)
-- Name: fk_k43wp04fn58rjpeomlbht9w26; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auditusu
    ADD CONSTRAINT fk_k43wp04fn58rjpeomlbht9w26 FOREIGN KEY (usua_usua) REFERENCES usuario(usua_usua);


--
-- TOC entry 2290 (class 2606 OID 70564)
-- Name: fk_kjgnfh3sk0fasfpwxstvv1127; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auditusu
    ADD CONSTRAINT fk_kjgnfh3sk0fasfpwxstvv1127 FOREIGN KEY (audi_audi) REFERENCES usuario(usua_usua);


--
-- TOC entry 2307 (class 2606 OID 70569)
-- Name: fk_l32s8gmtr1oniiwyj632jro73; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_juridica
    ADD CONSTRAINT fk_l32s8gmtr1oniiwyj632jro73 FOREIGN KEY (id_persona) REFERENCES persona(id_persona);


--
-- TOC entry 2311 (class 2606 OID 70574)
-- Name: fk_lvaxrferm1yyghp4u53knmw3l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_beneficiario
    ADD CONSTRAINT fk_lvaxrferm1yyghp4u53knmw3l FOREIGN KEY (id_beneficiario) REFERENCES persona_natural(id_persona);


--
-- TOC entry 2312 (class 2606 OID 70579)
-- Name: fk_mwl2q9kfjwcayd9me36ni2umy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_beneficiario
    ADD CONSTRAINT fk_mwl2q9kfjwcayd9me36ni2umy FOREIGN KEY (id_cotizante) REFERENCES persona_natural(id_persona);


--
-- TOC entry 2320 (class 2606 OID 70584)
-- Name: fk_mwr038gw4hmuqj264bnwgl9kh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuempr
    ADD CONSTRAINT fk_mwr038gw4hmuqj264bnwgl9kh FOREIGN KEY (usua_usua) REFERENCES usuario(usua_usua);


--
-- TOC entry 2316 (class 2606 OID 70589)
-- Name: fk_njvam3pxl89n8ra5a6b4rwl5i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_especialidad
    ADD CONSTRAINT fk_njvam3pxl89n8ra5a6b4rwl5i FOREIGN KEY (id_medico) REFERENCES persona_natural(id_persona);


--
-- TOC entry 2288 (class 2606 OID 70594)
-- Name: fk_noa78ra5ffbagcpw8t5tggukr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alergia_medicamento
    ADD CONSTRAINT fk_noa78ra5ffbagcpw8t5tggukr FOREIGN KEY (id_alergia) REFERENCES alergia(id_alergia);


--
-- TOC entry 2297 (class 2606 OID 70599)
-- Name: fk_ns892s76n52dms0pwxmx8ipix; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_medicamento
    ADD CONSTRAINT fk_ns892s76n52dms0pwxmx8ipix FOREIGN KEY (id_medicamento) REFERENCES medicamento(id_medicamento);


--
-- TOC entry 2299 (class 2606 OID 70604)
-- Name: fk_nun6cjdpi73sb7970uvpw92ml; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_tratamiento
    ADD CONSTRAINT fk_nun6cjdpi73sb7970uvpw92ml FOREIGN KEY (id_cita) REFERENCES cita(id_cita);


--
-- TOC entry 2317 (class 2606 OID 70609)
-- Name: fk_o844jicy2qugl3ubq6wy480w8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_operacion
    ADD CONSTRAINT fk_o844jicy2qugl3ubq6wy480w8 FOREIGN KEY (id_operacion) REFERENCES operacion(id_operacion);


--
-- TOC entry 2295 (class 2606 OID 70614)
-- Name: fk_q2hbflhih9769gvyadkovxeem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_examen
    ADD CONSTRAINT fk_q2hbflhih9769gvyadkovxeem FOREIGN KEY (id_cita) REFERENCES cita(id_cita);


--
-- TOC entry 2300 (class 2606 OID 70619)
-- Name: fk_qh399542hx11oebp2br4vtvc7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_tratamiento
    ADD CONSTRAINT fk_qh399542hx11oebp2br4vtvc7 FOREIGN KEY (id_tratamiento) REFERENCES tratamiento(id_tratamiento);


--
-- TOC entry 2302 (class 2606 OID 70624)
-- Name: fk_r5nn4hlqfr2f8hxoyw37bt6s8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupusu
    ADD CONSTRAINT fk_r5nn4hlqfr2f8hxoyw37bt6s8 FOREIGN KEY (grup_grup) REFERENCES grupo(grup_grup);


--
-- TOC entry 2285 (class 2606 OID 70629)
-- Name: fk_rbx2oxskfm2woy3x8o2yypxs0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accgrup
    ADD CONSTRAINT fk_rbx2oxskfm2woy3x8o2yypxs0 FOREIGN KEY (grup_grup) REFERENCES grupo(grup_grup);


--
-- TOC entry 2303 (class 2606 OID 70634)
-- Name: fk_rmvjvoejq81295nl328gj1edc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY incapacidad
    ADD CONSTRAINT fk_rmvjvoejq81295nl328gj1edc FOREIGN KEY (id_cita) REFERENCES cita(id_cita);


--
-- TOC entry 2298 (class 2606 OID 70639)
-- Name: fk_s8sxi4mk7t9d9pls7gg76o33e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_medicamento
    ADD CONSTRAINT fk_s8sxi4mk7t9d9pls7gg76o33e FOREIGN KEY (id_cita) REFERENCES cita(id_cita);


--
-- TOC entry 2318 (class 2606 OID 70644)
-- Name: fk_sv13m2ln3sndsh44d5f4wsdmd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural_operacion
    ADD CONSTRAINT fk_sv13m2ln3sndsh44d5f4wsdmd FOREIGN KEY (id_paciente) REFERENCES persona_natural(id_persona);


--
-- TOC entry 2308 (class 2606 OID 70649)
-- Name: fk_tq2cwm1aoij2kbwypyxsmnrnn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona_natural
    ADD CONSTRAINT fk_tq2cwm1aoij2kbwypyxsmnrnn FOREIGN KEY (id_persona) REFERENCES persona(id_persona);


--
-- TOC entry 2296 (class 2606 OID 70654)
-- Name: fk_vxqhj5h4dlo9yfrwek0qfthh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita_examen
    ADD CONSTRAINT fk_vxqhj5h4dlo9yfrwek0qfthh FOREIGN KEY (id_examen) REFERENCES examen(id_examen);


--
-- TOC entry 2506 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-07-29 11:11:25

--
-- PostgreSQL database dump complete
--

