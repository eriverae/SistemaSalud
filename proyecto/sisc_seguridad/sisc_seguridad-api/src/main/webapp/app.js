'use strict';
// Declare app level module which depends on views, and components
var app = angular.module('siscseguridad', ['ui.router','ngResource', 'ui.bootstrap','ngResource','ngGrid','app.utils']);

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
