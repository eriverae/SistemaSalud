'use strict';
var app = angular.module('sisc_web');

app.controller('medicosController', function ($scope, $rootScope, $stateParams, $state, personaService, modalService) {
    $scope.bizMessage = "";
    $scope.medico = {};
    $scope.epsDisponibles = [];
    $scope.epsSeleccionadas = [];
    personaService.listaEPS().$promise.then(
        function (data) {
            $scope.epsDisponibles = data;
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
    };

    $scope.moveAll = function (from, to) {
        // Add all elements from 'from' list to 'to' list
        angular.forEach(from, function (item) {
            to.push(item);
        });
        from.length = 0; // clean the list
    };

    if (angular.isDefined($stateParams.idPersona)) {
        console.log('Médico a modificar, ID = ' + $stateParams.idPersona);
        personaService.get({id: $stateParams.idPersona}).$promise.then(
            function (data) {
                console.log('Datos de médico encontrados');
                $scope.medico = data;
                //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
                $scope.medico.fechaNacimiento = new Date($scope.medico.fechaNacimiento);
                
                console.log('Consultando EPSs asociadas al médico ' + $scope.medico.idPersona);
                personaService.getMedicoEPS({medico: $scope.medico.idPersona}).$promise.then(
                    function (dataEPS) {
                        console.log('Datos de asociación medico-eps encontrados');
                        $scope.nuevoArray = angular.fromJson(dataEPS);
                        $scope.otroArray = [];
                        angular.forEach($scope.epsDisponibles, function (item) {
                            if ($scope.nuevoArray.filter(function(e) { return e.razonSocial == item.razonSocial; }).length > 0) {
                                $scope.epsSeleccionadas.push(item);
                                $scope.otroArray.push(item);
                            }
                        });
                        angular.forEach($scope.otroArray, function (x) {
                            $scope.epsDisponibles.splice(x, 1);
                        });
                    },
                    function (data) {
                        console.log(data);
                        console.log('Datos paila');
                        // Broadcast the event for a server error.
                        $rootScope.$broadcast('error');
                    }
                );
            },
            function () {
                console.log('Datos paila :(');
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
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
        console.log("Entro clearForm");
        $scope.medico = null;
        $scope.medicoForm.$setPristine();
        personaService.listaEPS().$promise.then(
            function (data) {
                $scope.epsDisponibles = data;
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            }
        );
        $scope.epsSeleccionadas=[];
    };

    // Calls the rest method to save a Medico.
    $scope.updateMedico = function () {
        $scope.medico.rolPersonaNatural = "MEDICO";
        personaService.save($scope.medico).$promise.then(
            function (response) {
                if (response.status == 0) {
                    var epsList = [];
                    angular.forEach($scope.epsSeleccionadas, function(eps) {
                        epsList.push(eps.idPersona);
                    });

                    var args = {
                        medico: response.idPersona,
                        eps: angular.toJson(epsList)
                    };
                    personaService.asociarMedicoEPS(args).$promise.then(
                        function () {

                        },
                        function () {
                            // Broadcast the event for a server error.
                            $rootScope.$broadcast('error');
                        }
                    );
                    // Broadcast the event to display a save message.
                    $rootScope.$broadcast('medicoSaved');
                } else {                    
                    $scope.bizMessage = response.message;
                    $rootScope.$broadcast('error');
                }                
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            }
        );
    };

    // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
    // the appropiate rest service.
    $scope.$on('medicoSelected', function (event, id) {
        console.log('Médico seleccionado, ID = ' + id);
        $scope.medico = personaService.get({id: id});
    });

    $scope.$on('medicoSaved', function () {
        $('#message-box-success').show();
        $scope.clearForm();
        $state.go('home.registroMedicos');
    });

    $scope.$on('error', function () {
        $('#message-box-warning').show();
    });
    
    $scope.closepopup = function(){
 	 $('#message-box-success').hide();
         $('#message-box-warning').hide();
    };

    $scope.cancelar = function () {
        $state.go('home.registroMedicos');
    };

});
