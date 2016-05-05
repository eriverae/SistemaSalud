'use strict';

var myModule = angular.module('sisc_web');
// Service that provides persons operations

myModule.factory('citaService', function ($resource) {
//    return $resource('/sisc_agenda/api/historialCitas/:id');
    return $resource('/SiscAgenda/api/historialCitas');
})

;

