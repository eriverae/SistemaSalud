
'use strict';

var calendarDemoApp = angular.module('siscseguridad');
// Service that provides persons operations
calendarDemoApp.factory('agendaService', function ($resource) {
    return $resource('http://localhost:8080/SiscAgenda/api/medico/agenda/:oper',{oper:'@oper',value:'@value'},
      {
        consultarListaEps:{
            method: 'GET',
            params:{idMedico:'@value'}
        }
      }
    );
});