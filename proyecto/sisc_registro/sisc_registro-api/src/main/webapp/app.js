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
      url:'/medicos',
      templateUrl: 'medicos/registroMedicos.html',
      controller: 'medicosController'
    })
      
    .state('listarMedicos',{
      url:'/listaMedicos',
      templateUrl: 'medicos/listaMedicos.html',
      controller: 'listaMedicosController'
    })
  
    .state('registroPacientes',{
      url: '/pacientes',
      templateUrl: 'pacientes/registroPacientes.html',
      controller: 'pacientesController'
    })
    
    .state('modificarMedicos',{
      url:'/medicos',
      templateUrl: 'medicos/registroMedicos.html',
      controller: 'medicosController',
      params : {idMedico:null}
    });
    /*
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
