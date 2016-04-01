'use strict';
// Declare app level module which depends on views, and components
var app = angular.module('sisc_registro', ['ui.router','ngResource', 'ui.bootstrap','ngResource','ngGrid','app.utils']);

app.config(['$stateProvider','$urlRouterProvider', function($stateProvider,$urlRouterProvider) {
  $urlRouterProvider.otherwise("/home");
  
  $stateProvider.
    state('home',{
      url: '/home',
      templateUrl: 'home.html'
    })
      
    .state('registroMedicos',{
      url:'/personaNatural',
      templateUrl: 'medicos/registroMedicos.html',
      controller: 'medicosController'
    })
  
    .state('registroPacientes',{
      url: '/personaNatural',
      templateUrl: 'pacientes/registroPacientes.html',
      controller: 'pacientesController'
    });
    /*
    .state('modificarCliente',{
      url: '/modificarCliente',
      templateUrl: 'clientes/formularioCliente.html',
      controller: 'clienteFormController',
      params : {'idCliente':null}
    });
    
    $routeProvider
    .when('/clientes', {
        templateUrl : 'clientes/clientes.html',
        controller  : 'clientesListController'
    })
    
    .when('/crearModificarCliente', {
        templateUrl : 'clientes/formularioCliente.html',
        controller  : 'clienteFormController'
    })
                
    .otherwise({redirectTo: '/view1'});
    */
}]);
