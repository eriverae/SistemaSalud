'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('menuController', function ($scope, $rootScope, $stateParams, $state, 
          menuService,modalService) {
   
     //    alert('LLEGUE DESDE AGENDA MEDICO ID_CITA: '+$stateParams.cita.idCita+ '\n OBJ_CITA: '+JSON.stringify($stateParams.cita));
	localStorage.setItem("idCita",integer.parseInt("1"));  

  	$scope.menu={};


	$scope.imprimir = function(){

	}

	$("#icono1").click(function(){
	            $(".page-container").addClass("page-navigation-toggled");
	            x_navigation_minimize("close");            
	});
	  
});