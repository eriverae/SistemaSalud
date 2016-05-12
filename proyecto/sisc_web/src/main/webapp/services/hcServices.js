'use strict';

var myModule = angular.module('sisc_web');
// Service that provides persons operations

myModule.factory('menuService', function ($resource) {
	return $resource('http://127.0.0.1:8080/hc/api/menu/');
});

myModule.factory('medicamentoService', function ($resource) {
	return $resource('http://127.0.0.1:8080/hc/api/medicamento/');
});

myModule.factory('cirugiaService', function ($resource) {
	return $resource('http://127.0.0.1:8080/hc/api/cirugia/');
});

myModule.factory('tratamientoService', function ($resource) {
	return $resource('http://127.0.0.1:8080/hc/api/tratamiento/');
});

myModule.factory('examenService', function ($resource) {
	return $resource('http://127.0.0.1:8080/hc/api/examen/');
});

myModule.factory('incapacidadService', function ($resource) {
	return $resource('http://127.0.0.1:8080/hc/api/incapacidad/');
});

myModule.factory('historiaService', function ($resource) {
	return $resource('http://127.0.0.1:8080/hc/api/historia/lastcita/');
});

myModule.factory('diagnosticoService', function ($resource) {
	return $resource('http://127.0.0.1:8080/hc/api/diagnostico/');
});