
'use strict';

var calendarDemoApp = angular.module('sisc_web');
// Service that provides persons operations
calendarDemoApp.factory('agendaService', function ($resource) {
    alert('>> ');
    return $resource('http://localhost:8080/SiscAgenda/api/medico/agenda/:idMedico/:oper',{oper:'@oper',idMedico:'@idMedico'}
      
    );
});