'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('incapacidadController', function ($scope, $rootScope, $stateParams, $state, 
          incapacidadService,modalService) {
  
  $scope.incapacidad={};
  
  if (angular.isDefined($stateParams.idUsuario)){
    console.log('Usuario a modificar, ID = '+ $stateParams.idUsuario);
    incapacidadService.get({id: $stateParams.idUsuario}).$promise.then(
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
   $scope.insertIncapacidad = function () {
       
    console.log($scope.incapacidad);
    incapacidadService.save($scope.incapacidad).$promise.then(
    function () {
      // Broadcast the event to refresh the grid.
      //   $rootScope.$broadcast('refreshGrid');
      // Broadcast the event to display a save message.
      //   $rootScope.$broadcast('clienteSaved');
      alert("exito");
    },
    function () {
      // Broadcast the event for a server error.
      //$rootScope.$broadcast('error');
      alert("error");
    });
    /*usuarioService.save($scope.usuario).$promise.then(
    function () {
      // Broadcast the event to refresh the grid.
      $rootScope.$broadcast('refreshGrid');
      // Broadcast the event to display a save message.
      $rootScope.$broadcast('usuarioSaved');
      
    },
    function () {
      // Broadcast the event for a server error.
      $rootScope.$broadcast('error');
    });*/
  };
  // Calls the rest method to save a Usuario.
  $scope.updateUsuario = function () {
    incapacidadService.save($scope.usuario).$promise.then(
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
    $scope.usuario = incapacidadService.get({id: id});
  });
  
  $scope.$on('usuarioSaved', function(){
    var modalOptions = {
          //closeButtonText: 'Cancelar',
          actionButtonText: 'Continuar',
          headerText: 'Resultado de operación',
          bodyText: 'Operación existosa!'
      };

      //modalService.showModal({}, modalOptions).then(function () {
        //$scope.clearForm();
        //$state.go('usuarios');
      //});
  });
  
  $scope.cancelar = function(){
    $state.go('usuarios');
  };
  
});
