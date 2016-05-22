'use strict';
var app = angular.module('sisc_web');
app.controller('diagnosticoController', function ($scope, $rootScope, $stateParams, $state, $timeout, diagnosticoService,cabeceraService, modalService) {
  	
  	$scope.myData = [];

  	$scope.diagnostico = "";
  	$scope.idcita = 1;




	diagnosticoService.get({idcita:1}).$promise.then(
      function (data) {
		console.log("get historiaService");
		$timeout(function() {
			$scope.diagnostico = data.data[0].diagnostico;

			$scope.$apply();
		}, 300);
      },

      function () {
		console.log("get FAIL");
      });

  cabeceraService.get({idcita:1}).$promise.then(
      function (data) {
    console.log("get cabeceraService");
    $timeout(function() {
     
      $scope.nombre = data.data[0].nombre;
      $scope.fechanac = data.data[0].fechanac;
      $scope.identificacion = data.data[0].identificacion;
      $scope.correo = data.data[0].correo;
      $scope.$apply();
    }, 300);
      },

      function () {
    console.log("get FAIL cabeceraService");
      });




 
 $scope.guardar = function(){

 	console.log($scope.diagnostico);

 		$scope.myData =  ({
 			id_cita : 1,        
        	diagnostico : $scope.diagnostico
        });
        
        diagnosticoService.save($scope.myData).$promise.then(
        function () {
          // Broadcast the event to refresh the grid.
          $('#message-box-success').show();
          
        },
        function () {
          // Broadcast the event for a server error.
           alert("error");
        });
    };

 $scope.closepopup = function(){
 	 $('#message-box-success').hide();
 };
  
});

