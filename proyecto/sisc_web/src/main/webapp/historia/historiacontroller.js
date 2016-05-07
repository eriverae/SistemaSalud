'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('historiaController', function ($scope, $rootScope, $stateParams, $state, $timeout, historiaService, modalService) {
  	
  	$scope.historias = [];

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

  
});

