'use strict';
var app = angular.module('sisc_web');

app.controller('listaEpsController', function ($scope, $rootScope, $stateParams, $state, epsService, modalService) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.eps = {currentPage: 1};
    $scope.searchText = null;

    $scope.gridOptions = {
        data: 'eps.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'idPersona', displayName: 'Id'},
            { field: 'razonSocial', displayName: 'Razon Social'},
            { field: 'fechaConstitucion', displayName: 'Fecha Constitucion' },
            { field: 'representanteLegal', displayName: 'Representante Legal'},
            { field: '', width: 80, 
                cellTemplate: '<span class="glyphicon glyphicon-trash remove" ng-click="deleteRow(row)"></span>'+
                '<span class="glyphicon glyphicon-edit modify" ng-click="updateRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('epsSelected', $scope.gridOptions.selectedItems[0].idPersona);
                console.log('Se emitio evento <epsSelected>');
            }
        }
    };
    
    $scope.searchTextChanged = function(){
      console.log('Ingreso a funcion searchTextChanged');
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listEpsArgs = {
            page: $scope.eps.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        epsService.get(listEpsArgs, function (data) {
            $scope.eps = data;
        });
    };

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
      var m = row.entity.razonSocial + " " + row.entity.fechaConstitucion;
      var modalOptions = {
          closeButtonText: 'Cancelar',
          actionButtonText: 'Eliminar EPS',
          headerText: 'Eliminar ' + m,
          bodyText: '¿Esta seguro de eliminar esta EPS?'
      };

      modalService.showModal({}, modalOptions).then(function (result) {
        $rootScope.$broadcast('deleteEps', row.entity.idPersona);
      });
    };
    
    $scope.updateRow = function(row){
      var idP = row.entity.idPersona;
      console.log('Modificar EPS: ' & idP);
      $state.go("modificarEps", {'idPersona': idP});
    };
    
    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo', function () {
        $scope.eps = {currentPage: 1};
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
    
    $scope.$on('epsSelected', function (event, id) {
        console.log("Ejecuta epselected " & id);
        $scope.eps = epsService.get({id: id});
    });
    
    // Picks us the event broadcasted when the person is deleted from the grid and perform the actual person delete by
    // calling the appropiate rest service.
    $scope.$on('deleteEps', function (event, id) {
      console.log('Evento eliminar EPS:' + id);
      epsService.delete({id: id}).$promise.then(
          function () {
              // Broadcast the event to refresh the grid.
              $rootScope.$broadcast('refreshGrid');
              // Broadcast the event to display a delete message.
              $rootScope.$broadcast('epsDeleted');
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
    $scope.$on('epsSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('epsDeleted', function () {
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
