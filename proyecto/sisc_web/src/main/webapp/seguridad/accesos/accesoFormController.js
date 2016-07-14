'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('accesoFormController', function ($scope, $rootScope, $stateParams, $state, 
          accesoService,modalService) {
  
  $scope.acceso={};
  
  if (angular.isDefined($stateParams.acceAcce)){
    console.log('Acceso a modificar, ID = '+ $stateParams.acceAcce);
    accesoService.get({acceAcce: $stateParams.acceAcce}).$promise.then(
      function (data) {
        $scope.acceso = data;
        //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
      },
      function () {
        // Broadcast the event for a server error.
        $rootScope.$broadcast('error');
      });
  }
  
  // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
  $scope.clearForm = function () {
    $scope.acceso = null;
    // Resets the form validation state.
    $scope.accesoForm.$setPristine();
    // Broadcast the event to also clear the grid selection.
    $rootScope.$broadcast('clear');
  };

  // Calls the rest method to save a Acceso.
  $scope.updateAcceso = function () {
    accesoService.save($scope.acceso).$promise.then(
    function () {
      // Broadcast the event to refresh the grid.
      $rootScope.$broadcast('refreshGrid');
      // Broadcast the event to display a save message.
      $rootScope.$broadcast('accesoSaved');
      
    },
    function () {
      // Broadcast the event for a server error.
      $rootScope.$broadcast('error');
    });
  };

  // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
  // the appropiate rest service.
  $scope.$on('accesoSelected', function (event, id) {
    console.log('Acceso seleccionado, ID = '+ id);
    $scope.acceso = accesoService.get({id: id});
  });
  
//  $scope.$on('accesoSaved', function(){
//    var modalOptions = {
//          //closeButtonText: 'Cancelar',
//          actionButtonText: 'Continuar',
//          headerText: 'Resultado de operación',
//          bodyText: 'Operación existosa!'
//      };
//
//      modalService.showModal({}, modalOptions).then(function () {
//        $scope.clearForm();
//        $state.go('accesos');
//      });
//  });

    $scope.$on('accesoSaved', function () {
        $('#message-box-success').show();
        $scope.clearForm();
    });

    $scope.closepopup = function () {
        $('#message-box-success').hide();
        $state.go('accesos');
    };
  
  $scope.cancelar = function(){
    $state.go('accesos');
  };
  
});