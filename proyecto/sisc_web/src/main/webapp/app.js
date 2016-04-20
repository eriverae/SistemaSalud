'use strict';
// Declare app level module which depends on views, and components
var app = angular.module('siscseguridad', ['ui.router','ngResource','ui.calendar', 'ui.bootstrap','ngGrid','app.utils']);

app.config(['$stateProvider','$urlRouterProvider', function($stateProvider,$urlRouterProvider) {
  $urlRouterProvider.otherwise("/home");
  
  $stateProvider.
    state('home',{
      url: '/home',
      templateUrl: 'home.html'
    })
      
    .state('usuarios',{
      url:'/usuarios',
      templateUrl: 'usuarios/usuarios.html',
      controller: 'usuariosListController'
    })
  
    .state('crearUsuario',{
      url: '/crearUsuario',
      templateUrl: 'usuarios/formularioUsuario.html',
      controller: 'usuarioFormController'
    })
    
    .state('modificarUsuario',{
      url: '/modificarUsuario',
      templateUrl: 'usuarios/formularioUsuario.html',
      controller: 'usuarioFormController',
      params : {'usuaUsua':null}
    })
    
     .state('agenda',{
      url: '/medico/agenda',
      templateUrl: 'agenda/medicos/agendaMedico.html',
      controller:'agendaMedicoContoller',
      params : {'idMedico':'5'}
      
      
    })
     .state('citasPaciente',{
      url: '/paciente/citas',
      templateUrl: 'agenda/pacientes/consultarCitas.html',
      controller: 'citasController'
    })    
        
     ;
    
}]);



