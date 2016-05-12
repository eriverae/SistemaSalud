'use strict';
var app = angular.module('sisc_web');
app.controller('diagnosticoController', function ($scope, $rootScope, $stateParams, $state, $timeout, diagnosticoService, modalService) {
  	
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

