'use strict';
var app = angular.module('sisc_web');

app.controller('beneficiariosController', function ($scope, $rootScope, $stateParams, $state, personaService, modalService) {
    $scope.datosGenerales = {};
    $scope.cotizante = {};
    $scope.beneficiario = {};
    $scope.numeroIdBeneficiario;

    if (angular.isDefined($stateParams.idPersona)) {
        console.log('Cotizante a..., ID = ' + $stateParams.idPersona);
        personaService.get({id: $stateParams.idPersona}).$promise.then(
                function (data) {
                    console.log('Datos de cotizante encontrados');
                    $scope.datosGenerales.cotizante = data;
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

    $scope.buscarBeneficiario = function() {
        personaService.get({numeroIdentificacion: $stateParams.numeroIdBeneficiario}).$promise.then(
        function (data) {
            console.log('Datos de beneficiario encontrados');
            $scope.datosGenerales.beneficiario = data;
        },
        function () {
            console.log('Datos paila :(');
            // Broadcast the event for a server error.
            $rootScope.$broadcast('error');
        });
    };

    $scope.asociarBeneficiario = function() {
        
    };
});
