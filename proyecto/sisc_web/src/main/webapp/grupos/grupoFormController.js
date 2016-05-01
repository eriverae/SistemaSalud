'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('grupoFormController', function ($scope, $rootScope, $stateParams, $state, 
          grupoService,modalService) {
  
  $scope.grupo={};
  
  if (angular.isDefined($stateParams.grupGrup)){
    console.log('Grupo a modificar, ID = '+ $stateParams.grupGrup);
    grupoService.get({grupGrup: $stateParams.grupGrup}).$promise.then(
      function (data) {
        $scope.grupo = data;
        //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
      },
      function () {
        // Broadcast the event for a server error.
        $rootScope.$broadcast('error');
      });
  }
  
  // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
  $scope.clearForm = function () {
    $scope.grupo = null;
    // Resets the form validation state.
    $scope.grupoForm.$setPristine();
    // Broadcast the event to also clear the grid selection.
    $rootScope.$broadcast('clear');
  };

  // Calls the rest method to save a Grupo.
  $scope.updateGrupo = function () {
    grupoService.save($scope.grupo).$promise.then(
    function () {
      // Broadcast the event to refresh the grid.
      $rootScope.$broadcast('refreshGrid');
      // Broadcast the event to display a save message.
      $rootScope.$broadcast('grupoSaved');
      
    },
    function () {
      // Broadcast the event for a server error.
      $rootScope.$broadcast('error');
    });
  };

  // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
  // the appropiate rest service.
  $scope.$on('grupoSelected', function (event, id) {
    console.log('Grupo seleccionado, ID = '+ id);
    $scope.grupo = grupoService.get({id: id});
  });
  
  $scope.$on('grupoSaved', function(){
    var modalOptions = {
          //closeButtonText: 'Cancelar',
          actionButtonText: 'Continuar',
          headerText: 'Resultado de operación',
          bodyText: 'Operación existosa!'
      };

      modalService.showModal({}, modalOptions).then(function () {
        $scope.clearForm();
        $state.go('grupos');
      });
  });
  
  $scope.cancelar = function(){
    $state.go('grupos');
  };
  
});