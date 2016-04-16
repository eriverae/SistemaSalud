'use strict';

var myModule = angular.module('siscseguridad');
// Service that provides persons operations
myModule.factory('usuarioService', function ($resource) {
    return $resource('/sisc_seguridad/api/usuarios/:usuaUsua');
});



