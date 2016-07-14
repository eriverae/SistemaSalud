'use strict';
var app = angular.module('sisc_web');

app.controller('epsController', function ($scope, $rootScope, $stateParams, $state, epsService, modalService) {

    $scope.eps = {};
    $scope.bizMessage = "";

    if (angular.isDefined($stateParams.idPersona)) {
        console.log('EPS a modificar, ID = ' + $stateParams.idPersona);
        epsService.get({id: $stateParams.idPersona}).$promise.then(
                function (data) {
                    console.log('Datos de EPS encontrados');
                    $scope.eps = data;
                    //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
                    $scope.eps.fechaConstitucion = new Date($scope.eps.fechaConstitucion);
                },
                function () {
                    console.log('Datos paila :(');
                    // Broadcast the event for a server error.
                    $rootScope.$broadcast('error');
                });
    }

    //TODO Reemplazar por consulta de items asociados a la enumeracion Java
    $scope.listaTiposIdentificacion = [
        {id: 'NIT', name: 'NIT'}
    ];

    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        console.log("Entro clearForm");
        $scope.eps = null;
        // Resets the form validation state.
        $scope.epsForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a Medico.
    $scope.updateEps = function () {
        epsService.save($scope.eps).$promise.then(
                function (response) {
                    if (response.status == 0) {
                        // Broadcast the event to display a save message.
                        $rootScope.$broadcast('epsSaved');
                    } else {
                        $scope.bizMessage = response.message;
                        $rootScope.$broadcast('error');
                    }
                },
                function () {
                    // Broadcast the event for a server error.
                    $rootScope.$broadcast('error');
                });
    };

    // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
    // the appropiate rest service.
    $scope.$on('epsSelected', function (event, id) {
        console.log('EPS seleccionado, ID = ' + id);
        $scope.eps = epsService.get({id: id});
    });


    $scope.$on('epsSaved', function () {
        $('#message-box-success').show();
        $scope.clearForm();
        $state.go('registroEps');
    });

    $scope.$on('error', function () {
        $('#message-box-warning').show();
    });
    
    $scope.closepopup = function(){
 	 $('#message-box-success').hide();
         $('#message-box-warning').hide();
    };

    $scope.cancelar = function () {
        $state.go('registroEPS');
    };
});
