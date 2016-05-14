'use strict';
var app = angular.module('sisc_web');
// Create a controller with name personsFormController to bind to the form section.
app.controller('grupoFormController', function ($scope, $rootScope, $stateParams, $state,
        grupoService, modalService, accesoService, accesoGrupoService) {

    $scope.grupo = {};
    $scope.items = [];

    accesoService.get(null, function (data) {
        $scope.listaAcceso = data.list;
    });


    if (angular.isDefined($stateParams.grupGrup)) {
        console.log('Grupo a modificar, ID = ' + $stateParams.grupGrup);
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

    $scope.listaTiposGrupo = [
        {id: 'Medico', name: 'Médico'},
        {id: 'Paciente', name: 'Paciente'},
        {id: 'EPS', name: 'EPS'},
        {id: 'Administrador', name: 'Administrador'},
        {id: 'Auditor', name: 'Auditor'}
    ];

    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.grupo = null;
        // Resets the form validation state.
        $scope.grupoForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    $scope.toggleSelection = function (idRol) {
        var index = $scope.items.indexOf(idRol);
        if (index > -1) { //Ya existe en los items seleccionados, >> se debe eliminar
            $scope.items.splice(index, 1); //-- Se elimina el item del arreglo
        } else { //Si no existen en el arreglo se adiciona
            $scope.items.push(idRol);
        }
    }

    // Calls the rest method to save a Grupo.
    $scope.updateGrupo = function () {
        grupoService.save($scope.grupo).$promise.then(
                function (data) {
                    // Broadcast the event to refresh the grid.
                    $rootScope.$broadcast('refreshGrid');
                    // Broadcast the event to display a save message.
                    $rootScope.$broadcast('grupoSaved');
                    
                    if ($scope.items.length == 0) {
                        console.log('No hay items seleccionados ...');
                    } else {
                        var index = 0;
                        var cadenaRoles = "";
                        for (; index < $scope.items.length; index++) {
                            var accesoGrupo = {};
                            console.log($scope.items[index].acceAcce + ' -- ' + $scope.items[index].acceNombre);
                            //accesoGrupo = {acceso:{acceAcce:$scope.items[index].acceAcce}, grupo:{data.grupGrup}};
                            accesoGrupo.acceso = $scope.items[index];
                            accesoGrupo.grupo = data;
                            accesoGrupoService.save(accesoGrupo);
                            //cadenaRoles = cadenaRoles + $scope.items[index].acceNombre + ";"
                        }
                        //alert("Seleccionó: " + cadenaRoles);
                    }
                },
                function () {
                    // Broadcast the event for a server error.
                    $rootScope.$broadcast('error');
                });
    };

    // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
    // the appropiate rest service.
    $scope.$on('grupoSelected', function (event, id) {
        console.log('Grupo seleccionado, ID = ' + id);
        $scope.grupo = grupoService.get({id: id});
    });

    $scope.$on('grupoSaved', function () {
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

    $scope.cancelar = function () {
        $state.go('grupos');
    };

});