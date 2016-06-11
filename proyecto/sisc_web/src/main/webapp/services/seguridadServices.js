'use strict';

var myModule = angular.module('sisc_web');
// Service that provides persons operations
myModule.factory('usuarioService', function ($resource) {
    return $resource('/sisc_seguridad/api/usuarios/:usuaUsua');
});

myModule.factory('seguridadService', function ($resource) {
    return $resource('/sisc_seguridad/api/seguridad/:seguridad');
});

myModule.factory('accesoService', function ($resource) {
    return $resource('/sisc_seguridad/api/accesos/:acceAcce');
});

myModule.factory('grupoService', function ($resource) {
    return $resource('/sisc_seguridad/api/grupos/:grupGrup');
});

myModule.factory('loginService', function ($resource) {
    return $resource('/sisc_seguridad/api/seguridad/');
});

myModule.factory('accesoGrupoService', function ($resource) {
    return $resource('/sisc_seguridad/api/accesoGrupo/:idAccgrup');
});

myModule.factory('grupoUsuarioService', function ($resource) {
    return $resource('/sisc_seguridad/api/grupoUsuario/:idGrupusu');
});

myModule.factory('grupoUsuarioSelection', function ($resource) {
    return $resource('/sisc_seguridad/api/grupoUsuario/actUsGr/');
});

myModule.factory('accesoGrupoSelection', function ($resource) {
    return $resource('/sisc_seguridad/api/accesoGrupo/actAccGr/:usuaUsua');
});

myModule.factory('contrasenaService', function ($resource) {
    return $resource('/sisc_seguridad/api/usuarios/actCon/:usuaUsua');
});

myModule.factory('auditoriaService', function ($resource) {
    return $resource('/sisc_seguridad/api/auditorias/');
});