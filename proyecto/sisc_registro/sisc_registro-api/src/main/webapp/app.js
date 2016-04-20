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
      
    .state('listarPacientes',{
      url:'/listaPacientes',
      templateUrl: 'pacientes/listaPacientes.html',
      controller: 'listaPacientesController'
    })
    
    .state('modificarMedicos',{
      url:'/medicos',
      templateUrl: 'medicos/registroMedicos.html',
      controller: 'medicosController',
      params : {idPersona:null}
    })
    
    .state('modificarPacientes',{
      url:'/pacientes',
      templateUrl: 'pacientes/registroPacientes.html',
      controller: 'pacientesController',
      params : {idPersona:null}
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
