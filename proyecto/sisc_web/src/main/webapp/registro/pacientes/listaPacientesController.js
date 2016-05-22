'use strict';
var app = angular.module('sisc_web');

app.controller('listaPacientesController', function ($scope, $rootScope, $stateParams, $state, personaService, modalService) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.pacientes = {currentPage: 1};
    $scope.searchText = null;

    $scope.gridOptions = {
        data: 'pacientes.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'idPersona', displayName: 'Id'},
            { field: 'nombres', displayName: 'Nombre'},
            { field: 'apellidos', displayName: 'Apellidos' },
            { field: 'numeroIdentificacion', displayName: 'Numero Identificacion'},
            { field: '', width: 80, 
                cellTemplate: '<span class="glyphicon glyphicon-trash remove" ng-click="deleteRow(row)"></span>'+
                '<span class="glyphicon glyphicon-edit modify" ng-click="updateRow(row)"></span>' +
                '&nbsp;&nbsp;&nbsp;<span class="fa fa-sitemap" ng-click="beneficiarios(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('pacienteSelected', $scope.gridOptions.selectedItems[0].idPersona);
                console.log('Se emitio evento <pacienteSelected>');
            }
        }
    };
    
    $scope.searchTextChanged = function(){
      console.log('Ingreso a funcion searchTextChanged');
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listPacientesArgs = {
            page: $scope.pacientes.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0],
            rol: 'PACIENTE'
        };

        personaService.get(listPacientesArgs, function (data) {
            $scope.pacientes = data;
        });
    };

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
      var m = row.entity.nombres + " " + row.entity.apellidos;
      var modalOptions = {
          closeButtonText: 'Cancelar',
          actionButtonText: 'Eliminar Paciente',
          headerText: 'Eliminar ' + m,
          bodyText: '¿Esta seguro de eliminar este paciente?'
      };

      modalService.showModal({}, modalOptions).then(function (result) {
        $rootScope.$broadcast('deletePaciente', row.entity.idPersona);
      });
      
    };
    
    $scope.updateRow = function(row){
      var idP = row.entity.idPersona;
      console.log('Modificar persona: ' & idP);
      $state.go("modificarPersonaNatural", {idPersona: idP});
    };
    
    $scope.beneficiarios = function(row){
      var idP = row.entity.idPersona;
      $state.go("registroBeneficiarios", {idPersona: idP});
    };
    
    $scope.autenticar = function () {
    authService.save('ljygkvy', 'jygyukg').$promise.then(
        function () {
          var modalOptions = {
            closeButtonText: 'jhfy',
            actionButtonText: 'jysgdluwdgjeyglwew',
            headerText: 'asjdk ',
            bodyText: 'Ingreso exitoso'
        };
        },
        function () {
          // Broadcast the event for a server error.
          $rootScope.$broadcast('error');
        });
  };

    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo', function () {
        $scope.pacientes = {currentPage: 1};
        $scope.refreshGrid();
    }, true);

    // Do something when the grid is sorted.
    // The grid throws the ngGridEventSorted that gets picked up here and assigns the sortInfo to the scope.
    // This will allow to watch the sortInfo in the scope for changed and refresh the grid.
    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
      $scope.sortInfo = sortInfo;
    });

    // Picks the event broadcasted when a person is saved or deleted to refresh the grid elements with the most
    // updated information.
    $scope.$on('refreshGrid', function () {
      $scope.refreshGrid();
    });
    
    // Picks the event broadcasted when the form is cleared to also clear the grid selection.
    $scope.$on('clear', function () {
        $scope.gridOptions.selectAll(false);
    });
    
    $scope.$on('pacienteSelected', function (event, id) {
        console.log("Ejecuta pacienteSelected " & id);
        $scope.paciente = personaService.get({id: id});
    });
    
    // Picks us the event broadcasted when the person is deleted from the grid and perform the actual person delete by
    // calling the appropiate rest service.
    $scope.$on('deletePaciente', function (event, id) {
      console.log('Evento eliminar paciente:' + id);
      personaService.delete({id: id}).$promise.then(
          function () {
              // Broadcast the event to refresh the grid.
              $rootScope.$broadcast('refreshGrid');
              // Broadcast the event to display a delete message.
              $rootScope.$broadcast('pacienteDeleted');
              //$scope.clearForm();
          },
          function () {
              // Broadcast the event for a server error.
              $rootScope.$broadcast('error');
          });
    });
});

// Create a controller with name alertMessagesController to bind to the feedback messages section.
app.controller('alertMessagesController', function ($scope) {
    // Picks up the event to display a saved message.
    $scope.$on('pacienteSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('pacienteDeleted', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record deleted successfully!' }
        ];
    });

    // Picks up the event to display a server error message.
    $scope.$on('error', function () {
        $scope.alerts = [
            { type: 'danger', msg: 'There was a problem in the server!' }
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
});
