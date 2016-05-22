'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('historiaController', function ($scope, $rootScope, $stateParams, $state, $timeout, historiaService,filtroService,historiaServiceFiltro, modalService) {
  	
  	$scope.historias = [];


  	filtroService.get({idcita:1}).$promise.then(
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

    historiaServiceFiltro.get({idcita:1,medico:$scope.epsForm.medico.$viewValue , fechainicio: $scope.epsForm.fechaInicio.$viewValue, fechafin: $scope.epsForm.fechaFin.$viewValue }).$promise.then(
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

