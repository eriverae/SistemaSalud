'use strict';
// Declare app level module which depends on views, and components
var app = angular.module('sisc_web', ['ngResource','ui.router','ui.calendar', 'ui.bootstrap','ngGrid','app.utils']);

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
      controller: 'citasController',
      params : {'idPaciente':'3'}
    })    
        
    .state('registroMedicos',{
      url:'/medicos',
      templateUrl: 'registro/medicos/registroMedicos.html',
      controller: 'medicosController'
    })
     
    .state('listarMedicos',{
      url:'/listaMedicos',
      templateUrl: 'registro/medicos/listaMedicos.html',
      controller: 'listaMedicosController'
    })
  
    .state('registroPacientes',{
      url: '/pacientes',
      templateUrl: 'registro/pacientes/registroPacientes.html',
      controller: 'pacientesController'
    })
      
    .state('listarPacientes',{
      url:'/listaPacientes',
      templateUrl: 'registro/pacientes/listaPacientes.html',
      controller: 'listaPacientesController'
    })
    
    .state('modificarMedicos',{
      url:'/medicos',
      templateUrl: 'registro/medicos/registroMedicos.html',
      controller: 'medicosController',
      params : {idPersona:null}
    })
    
    .state('modificarPacientes',{
      url:'/pacientes',
      templateUrl: 'registro/pacientes/registroPacientes.html',
      controller: 'pacientesController',
      params : {idPersona:null}
    })
     ;
    
}]);



