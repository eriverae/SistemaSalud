'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('usuarioFormController', function ($scope, $rootScope, $stateParams, $state, 
          usuarioService,modalService) {
  
  $scope.usuario={};
  
  if (angular.isDefined($stateParams.usuaUsua)){
    console.log('Usuario a modificar, ID = '+ $stateParams.usuaUsua);
    usuarioService.get({usuaUsua: $stateParams.usuaUsua}).$promise.then(
      function (data) {
        $scope.usuario = data;
        //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
      },
      function () {
        // Broadcast the event for a server error.
        $rootScope.$broadcast('error');
      });
  }
  
  // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
  $scope.clearForm = function () {
    $scope.usuario = null;
    // Resets the form validation state.
    $scope.usuarioForm.$setPristine();
    // Broadcast the event to also clear the grid selection.
    $rootScope.$broadcast('clear');
  };

  // Calls the rest method to save a Usuario.
  $scope.updateUsuario = function () {
    usuarioService.save($scope.usuario).$promise.then(
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

  // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
  // the appropiate rest service.
  $scope.$on('usuarioSelected', function (event, id) {
    console.log('Usuario seleccionado, ID = '+ id);
    $scope.usuario = usuarioService.get({id: id});
  });
  
  $scope.$on('usuarioSaved', function(){
    var modalOptions = {
          //closeButtonText: 'Cancelar',
          actionButtonText: 'Continuar',
          headerText: 'Resultado de operación',
          bodyText: 'Operación existosa!'
      };

      modalService.showModal({}, modalOptions).then(function () {
        $scope.clearForm();
        $state.go('usuarios');
      });
  });
  
  $scope.cancelar = function(){
    $state.go('usuarios');
  };
  
  $scope.validarContrasena = function(){
    console.log("contraseña 1: "+$scope.usuario.usuaPass+" contraseña 2: "+$scope.usuario.usuaPass1);
    
    return($scope.usuario.usuaPass !== $scope.usuario.usuaPass1);

  };
  
});

app.directive('wjValidationError', function () {
  return {
    require: 'ngModel',
    link: function (scope, elm, attrs, ctl) {
      scope.$watch(attrs['wjValidationError'], function (errorMsg) {
        elm[0].setCustomValidity(errorMsg);
        ctl.$setValidity('wjValidationError', errorMsg ? false : true);
      });
    }
  };
});