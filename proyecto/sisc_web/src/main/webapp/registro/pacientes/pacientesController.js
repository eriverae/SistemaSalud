'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('pacientesController', function ($scope, $rootScope, $stateParams, $state,
        personaService, modalService) {

    $scope.paciente = {};
    $scope.eps = {};
    $scope.listaEPS;
    
    personaService.listaEPS().$promise.then(
        function (data) {
            $scope.listaEPS = data;
        },
        function () {
            // Broadcast the event for a server error.
            $rootScope.$broadcast('error');
        }
    );
    
    $scope.alergiasDisponibles = [];
    $scope.alergiasSeleccionadas = [];
    personaService.listaAlergias().$promise.then(
        function (data) {
            $scope.alergiasDisponibles = data;
        },
        function () {
            // Broadcast the event for a server error.
            $rootScope.$broadcast('error');
        }
    );
    
    $scope.enfermedadesDisponibles = [];
    $scope.enfermedadesSeleccionadas = [];
    personaService.listaEnfermedades().$promise.then(
        function (data) {
            $scope.enfermedadesDisponibles = data;
        },
        function () {
            // Broadcast the event for a server error.
            $rootScope.$broadcast('error');
        }
    );
    
    $scope.operacionesDisponibles = [];
    $scope.operacionesSeleccionadas = [];
    personaService.listaOperaciones().$promise.then(
        function (data) {
            $scope.operacionesDisponibles = data;
        },
        function () {
            // Broadcast the event for a server error.
            $rootScope.$broadcast('error');
        }
    );

    $scope.moveItem = function (item, from, to) {
        var idx = from.indexOf(item);
        if (idx != -1) {
            from.splice(idx, 1); // Remove the selected item from 'from' list
            to.push(item); // Add the selected item from 'to' list
        }
    }

    $scope.moveAll = function (from, to) {
        // Add all elements from 'from' list to 'to' list
        angular.forEach(from, function (item) {
            to.push(item);
        });
        from.length = 0; // clean the list
    }

    if (angular.isDefined($stateParams.idPersona)) {
        console.log('Paciente a modificar, ID = ' + $stateParams.idPersona);
        personaService.get({id: $stateParams.idPersona}).$promise.then(
            function (data) {
                console.log('Datos de paciente encontrados');
                $scope.paciente = data;
                //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
                $scope.paciente.fechaNacimiento = new Date($scope.paciente.fechaNacimiento);
                
                console.log('Consultando EPS asociada al paciente ' + $scope.paciente.idPersona);
                personaService.getPacienteEPS({paciente: $scope.paciente.idPersona}).$promise.then(
                    function (dataEPS) {
                        console.log('Datos de asociación paciente-eps encontrados');
                        $scope.eps = dataEPS.idPersona;
                    },
                    function () {
                        console.log('Datos paila');
                        // Broadcast the event for a server error.
                        $rootScope.$broadcast('error');
                    }
                );
            },
            function () {
                console.log('Datos paila');
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            }
        );
    }

    //TODO Reemplazar por consulta de items asociados a la enumeracion Java
    $scope.listaTiposIdentificacion = [
        {id: 'CC', name: 'Cedula'},
        {id: 'TI', name: 'T. Identidad'},
        {id: 'RC', name: 'Registro Civil'}
    ];

    //TODO Reemplazar por consulta de items asociados a la enumeracion en Java
    $scope.listaGeneros = [
        {id: 'M', label: 'Masculino'},
        {id: 'F', label: 'Femenino'}
    ];

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
        $scope.paciente = null;
        // Resets the form validation state.
        $scope.pacienteForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a Paciente.
    $scope.updatePaciente = function () {
        console.log("EPS seleccionada: " + $scope.eps);
        $scope.paciente.rolPersonaNatural = "PACIENTE";
        personaService.save($scope.paciente).$promise.then(
            function (response) {
                console.log("Paciente almacenado");
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('pacienteSaved');
                // Broadcast the event to display a save message.
                //$rootScope.$broadcast('pacienteSaved');
                
                if(!(angular.isUndefined($scope.eps.razonSocial) || $scope.eps.razonSocial === null)){
                    var args = {
                        paciente: response.idPersona,
                        eps: $scope.eps
                    };
                    personaService.asociarPacienteEPS(args).$promise.then(
                        function () {

                        },
                        function () {
                            // Broadcast the event for a server error.
                            $rootScope.$broadcast('error');
                        }
                    );
                };
                
                var alergiasList = [];
                angular.forEach($scope.alergiasSeleccionadas, function(a) {
                    alergiasList.push(a.idAlergia);
		});
                
                var argsA = {
                    paciente: angular.toJson(response.idPersona),
                    alergias: angular.toJson(alergiasList)
                };
                personaService.asociarPacienteAlergias(argsA).$promise.then(
                    function () {

                    },
                    function () {
                        // Broadcast the event for a server error.
                        $rootScope.$broadcast('error');
                    }
                );
                
                var medicamentosList = [];
                angular.forEach($scope.medicamentosSeleccionadas, function(m) {
                    medicamentosList.push(m.idMedicamento);
		});
                
                var argsM = {
                    paciente: angular.toJson(response.idPersona),
                    medicamentos: angular.toJson(medicamentosList)
                };
                personaService.asociarPacienteMedicamentos(argsM).$promise.then(
                    function () {

                    },
                    function () {
                        // Broadcast the event for a server error.
                        $rootScope.$broadcast('error');
                    }
                );
                
                var operacionesList = [];
                angular.forEach($scope.operacionesSeleccionadas, function(m) {
                    operacionesList.push(m.idOperacion);
		});
                var argsO = {
                    paciente: angular.toJson(response.idPersona),
                    operaciones: angular.toJson(operacionesList)
                };
                personaService.asociarPacienteOperaciones(argsO).$promise.then(
                    function () {

                    },
                    function () {
                        // Broadcast the event for a server error.
                        $rootScope.$broadcast('error');
                    }
                );
            },
            function (response) {
                // Broadcast the event for a server error.
                console.log('Mensaje error:'+ response.data.message);
                console.log('Codigo error :' + response.data.code);
                console.log('Status:'+ response.status );

                $rootScope.$broadcast('error', response.data.message);
            }
        );

        
    };

    $scope.$on('pacienteSaved', function () {
        $('#message-box-success').show().then(function () {
            $scope.clearForm();
            $state.go('registroPacientes');
        });
    });

    $scope.cancelar = function () {
        $state.go('registroPacientes');
    };
    
    $scope.closepopup = function(){
 	 $('#message-box-success').hide();
    };
});