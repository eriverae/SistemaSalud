'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('menuController', function ($scope, $rootScope, $stateParams, $state, 
          menuService,modalService) {
             
        localStorage.setItem("idCita",parseInt($stateParams.cita.idCita));
        localStorage.setItem("idPaciente",$stateParams.cita.pacienteEps.persona.idPersona);
  	$scope.menu={};


	$scope.imprimir = function(){

	}

	$("#icono1").click(function(){
	            $(".page-container").addClass("page-navigation-toggled");
	            x_navigation_minimize("close");            
	});
	  
});