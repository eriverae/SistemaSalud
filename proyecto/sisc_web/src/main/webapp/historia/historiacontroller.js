'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('historiaController', function ($scope, $rootScope, $stateParams, $state, $timeout, historiaService,filtroService,historiaServiceFiltro, modalService) {
  	
  	$scope.historias = [];


  	filtroService.get({idcita:localStorage.getItem('idCita')}).$promise.then(
      function (data) {
		console.log("get medicos");
		$timeout(function() {
			//$scope.historias = data.data;
			$scope.listaMedicos = data.data;
			//$scope.$apply();
		}, 300);
      },
      function () {
		console.log("get FAIL");
      });



 	

	historiaService.get().$promise.then(
      function (data) {
		console.log("get historiaService");
		$timeout(function() {
			$scope.historias = data.data;
			console.log($scope.historias);
			$scope.$apply();
		}, 300);
      },
      function () {
		console.log("get FAIL");
      });


	$scope.buscarCitas = function(){
            if ($scope.epsForm.medico.$viewValue == null)
            $scope.epsForm.medico.$viewValue = "";
            if ($scope.epsForm.fechaInicio.$viewValue == null)
            $scope.epsForm.fechaInicio.$viewValue = "";
            if($scope.epsForm.fechaFin.$viewValue == null)
            $scope.epsForm.fechaFin.$viewValue = "";
    historiaServiceFiltro.get({idcita:localStorage.getItem('idCita'),medico:$scope.epsForm.medico.$viewValue , fechainicio: $scope.epsForm.fechaInicio.$viewValue, fechafin: $scope.epsForm.fechaFin.$viewValue }).$promise.then(
      function (data) {
		console.log("get historiaService 2");
		$timeout(function() {
			$scope.historias = data.data;
			console.log($scope.historias);
			$scope.$apply();
		}, 300);
      },
      function () {
		console.log("get FAIL");
      });
		
		
	};

  
});

