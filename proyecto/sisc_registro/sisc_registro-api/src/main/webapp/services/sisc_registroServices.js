'use strict';

var myModule = angular.module('sisc_registro');
// Service that provides persons operations
myModule.factory('medicoService', function ($resource) {
    return $resource('/sisc/registro/api/medicos/:id');
});



