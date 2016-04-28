'use strict';
var app = angular.module('sisc_web');

app.controller('beneficiariosController', function ($scope, $rootScope, $stateParams, $state, personaService, modalService) {

    $scope.beneficiario = {};

    if (angular.isDefined($stateParams.idPersona)) {
        console.log('Beneficiario a..., ID = ' + $stateParams.idPersona);
        personaService.get({id: $stateParams.idPersona}).$promise.then(
                function (data) {
                    console.log('Datos de beneficiarios encontrados');
                    $scope.beneficiario = data;
                    //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
                    $scope.beneficiario.fechaNacimiento = new Date($scope.beneficiario.fechaNacimiento);
                },
                function () {
                    console.log('Datos paila :(');
                    // Broadcast the event for a server error.
                    $rootScope.$broadcast('error');
                });
    }

    $scope.listaTiposParentezco = [
        {id: 'MAMA', name: 'Mamá'},
        {id: 'PAPA', name: 'Papá'},
        {id: 'HERMANO', name: 'Hermano'},
        {id: 'HIJO', name: 'Hijo'},
        {id: 'OTRO', name: 'Otro'}
        
    ];



    $scope.updateBeneficiario = function () {
        personaService.save($scope.beneficiario).$promise.then(
                function () {
                    // Broadcast the event to refresh the grid.
                    $rootScope.$broadcast('refreshGrid');
                    // Broadcast the event to display a save message.
                    $rootScope.$broadcast('beneficiarioSaved');

                },
                function () {
                    // Broadcast the event for a server error.
                    $rootScope.$broadcast('error');
                });
    };

});
