'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('personaNaturalController', function ($scope, $rootScope, $stateParams, $state,
        personaService, modalService) {

    $scope.persona = {};
    $scope.paciente = true;
    if (angular.isDefined($stateParams.idPersona)) {
        console.log('Paciente a modificar, ID = ' + $stateParams.idPersona);
        personaService.get({id: $stateParams.idPersona}).$promise.then(
                function (data) {
                    $scope.persona = data;
                    //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
                    $scope.persona.fechaNacimiento = new Date($scope.persona.fechaNacimiento);
                },
                function () {
                    // Broadcast the event for a server error.
                    $rootScope.$broadcast('error');
                });
    }

    //TODO Reemplazar por consulta de items asociados a la enumeracion Java
    $scope.listaTiposIdentificacion = [
        {id: 'CC', name: 'Cedula'},
        {id: 'TI', name: 'Tarjeta de identidad'},
        {id: 'RC', name: 'Registro civil'}
    ];

    //TODO Reemplazar por consulta de items asociados a la enumeracion en Java
    $scope.listaGeneros = [
        {id: 'M', label: 'Masculino'},
        {id: 'F', label: 'Femenino'}
    ];

    //TODO Reemplazar por consulta de items asociados a la enumeracion en Java
    $scope.listaGrupoSanguineo = [
        {id: 'A', label: 'A'},
        {id: 'B', label: 'B'},
        {id: 'AB', label: 'AB'},
        {id: 'O', label: 'O'}
    ];

    $scope.listaRH = [
        {id: '+', label: 'Positivo'},
        {id: '-', label: 'Negativo'}
    ];

    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.persona = null;
        // Resets the form validation state.
        $scope.personaForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a Paciente.
    $scope.updatePaciente = function () {
        personaService.save($scope.persona).$promise.then(
                function () {
                    // Broadcast the event to refresh the grid.
                    $rootScope.$broadcast('refreshGrid');
                    // Broadcast the event to display a save message.
                    $rootScope.$broadcast('pacienteSaved');
                },
                function () {
                    // Broadcast the event for a server error.
                    $rootScope.$broadcast('error');
                });
    };

    // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
    // the appropiate rest service.
    $scope.$on('pacienteSelected', function (event, id) {
        // Calls the rest method to save a Paciente.
        $scope.updatePaciente = function () {
            if ($scope.paciente) {
                $scope.persona.rolPersonaNatural = "PACIENTE";
                personaService.save($scope.persona).$promise.then(
                        function () {
                            // Broadcast the event to refresh the grid.
                            $rootScope.$broadcast('refreshGrid');
                            // Broadcast the event to display a save message.
                            $rootScope.$broadcast('pacienteSaved');
                        },
                        function () {
                            // Broadcast the event for a server error.
                            $rootScope.$broadcast('error');
                        });
            } else {
                $scope.persona.rolPersonaNatural = "MEDICO";
                personaService.save($scope.persona).$promise.then(
                        function () {
                            // Broadcast the event to refresh the grid.
                            $rootScope.$broadcast('refreshGrid');
                            // Broadcast the event to display a save message.
                            $rootScope.$broadcast('pacienteSaved');
                        },
                        function () {
                            // Broadcast the event for a server error.
                            $rootScope.$broadcast('error');
                        });
            }
        };
        // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
        // the appropiate rest service.
        $scope.$on('pacienteSelected', function (event, id) {
            console.log('Paciente seleccionado, ID = ' + id);
            $scope.persona = personaService.get({id: id});
        });
        $scope.$on('pacienteSaved', function () {
            var modalOptions = {
                //closeButtonText: 'Cancelar',
                actionButtonText: 'Continuar',
                headerText: 'Resultado de operación',
                bodyText: 'Operación existosa!'
            };

            modalService.showModal({}, modalOptions).then(function () {
                $scope.clearForm();
                $state.go('home.registroPersonaNatural');
            });
        });
        $scope.cancelar = function () {
            $state.go('home.registroPersonaNatural');
        };
    });
});
