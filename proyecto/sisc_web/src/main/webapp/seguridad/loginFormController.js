'use strict';
var app = angular.module('siscseguridad');
// Create a controller with name personsFormController to bind to the form section.
app.controller('loginFormController', function ($scope, $rootScope, $stateParams, $state, 
          seguridadService,modalService) {
  
  $scope.usuario={};
  
  // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
  $scope.clearForm = function () {
    $scope.usuario = null;
    // Resets the form validation state.
    $scope.seguridadForm.$setPristine();
    // Broadcast the event to also clear the grid selection.
    $rootScope.$broadcast('clear');
  };
  
  $scope.updateUsuario = function () {
    seguridadService.save($scope.usuario).$promise.then(
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
  
  $scope.cancelar = function(){
    $state.go('seguridad');
  };
  
});