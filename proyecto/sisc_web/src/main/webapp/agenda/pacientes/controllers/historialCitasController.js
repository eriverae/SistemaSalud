'use strict';
var app = angular.module('sisc_web');

app.controller('historialCitasController', function ($scope, $rootScope, $stateParams, $state, citaService, modalService) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.citas = {currentPage: 1};
    $scope.searchText = null;

    $scope.gridOptions = {
        data: 'citas.list', //historialCitas.list    //citas.list ???  ¿?
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'idCita', displayName: 'Id'},
            { field: 'valor', displayName: 'Valor'},
            { field: 'horaFin', displayName: 'hora cita' },
            { field: 'estadoCita', displayName: 'Estado cita'},
            { field: '', width: 80, 
                cellTemplate: '<span class="glyphicon glyphicon-trash remove" ng-click="deleteRow(row)"></span>'+
                '<span class="glyphicon glyphicon-edit modify" ng-click="updateRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('citasSelected', $scope.gridOptions.selectedItems[0].idCita);
                console.log('Se emitio evento <citasSelected>');
            }
        }
    };
    
    $scope.searchTextChanged = function(){
      console.log('Ingreso a funcion searchTextChanged');
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listCitasArgs = {
            page: $scope.citas.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        citaService.get(listCitasArgs, function (data) {
            $scope.citas = data;
        });
    };

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
      var m = row.entity.nombres + " " + row.entity.apellidos;
      var modalOptions = {
          closeButtonText: 'Cancelar',
          actionButtonText: 'Eliminar Cita',
          headerText: 'Eliminar ' + m,
          bodyText: '¿Esta seguro de eliminar esta cita?'
      };

      modalService.showModal({}, modalOptions).then(function (result) {
        $rootScope.$broadcast('deletePaciente', row.entity.idCita);
      });
      
    };
    
    $scope.updateRow = function(row){
      var idP = row.entity.idCita;
      console.log('Modificar cita: ' & idP);
      $state.go("modificarCitas", {idCita: idP});
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
        $scope.citas = {currentPage: 1};
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
    
    $scope.$on('citasSelected', function (event, id) {
        console.log("Ejecuta citasSelected " & id);
        $scope.citas = citaService.get({id: id});
    });
    
    // Picks us the event broadcasted when the person is deleted from the grid and perform the actual person delete by
    // calling the appropiate rest service.
    $scope.$on('deleteCita', function (event, id) {
      console.log('Evento eliminar cita:' + id);
      citaService.delete({id: id}).$promise.then(
          function () {
              // Broadcast the event to refresh the grid.
              $rootScope.$broadcast('refreshGrid');
              // Broadcast the event to display a delete message.
              $rootScope.$broadcast('citaDeleted');
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
    $scope.$on('citaSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('citaDeleted', function () {
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