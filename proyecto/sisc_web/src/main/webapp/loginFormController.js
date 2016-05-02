'use strict';
var app = angular.module('sisc_web');

// Create a controller with name personsFormController to bind to the form section.
app.controller('loginFormController', function ($scope, $rootScope, $stateParams, loginService, $state) {
  $scope.credenciales={};
  
  // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
  $scope.clearForm = function () {
    $scope.credenciales = null;
    // Resets the form validation state.
    $scope.credencialesForm.$setPristine();
    // Broadcast the event to also clear the grid selection.
    $rootScope.$broadcast('clear');
    $state.go('login');
  };
  
  $scope.hacerLogin = function () {
    loginService.save($scope.credenciales).$promise.then(
    function () {
      // Broadcast the event to refresh the grid.
      $rootScope.$broadcast('refreshGrid');
      // Broadcast the event to display a save message.
      $rootScope.$broadcast('usuarioSaved');
    },
    function () {
      // Broadcast the event for a server error.
      $rootScope.$broadcast('error');
    });
  };
  
});