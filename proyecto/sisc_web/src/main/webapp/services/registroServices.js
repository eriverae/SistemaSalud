'use strict';

var myModule = angular.module('sisc_web');
// Service that provides persons operations
myModule.factory('personaService', function ($resource) {
    return $resource('/sisc_registro/api/personaNatural/:id');
});

