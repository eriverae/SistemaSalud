var app = angular.module('sisc_web');
// Create a controller with name clientesListController to bind to the grid section.
app.controller('cirugiaController', function ($scope, $rootScope,$state ,cirugiaService, modalService) {
    // Initialize required information: sorting, the first page to show and the grid options.


    $scope.myData = [
            {
            cita: "ffff",
            cirugia: "male",
            detalles:"",
            observaciones:""         
            }
    ];

    $scope.gridOptions = {

        data: 'myData',
        columnDefs: [
            { field: 'cita', displayName: 'Cita' ,  enableCellEdit: true},
            
                { field: 'cirugia', 
                    displayField : 'idcirugia',
                    valueField: 'nombrecirugia',
                    enableCellEdit: true,
                    editableCellTemplate: 'wgCirugias.html',
                    

                 },
                 {field: 'detalles', displayName: 'Detalles' ,  enableCellEdit: true},
                 {field: 'observaciones', displayName: 'observaciones' ,  enableCellEdit: true}
        ],

        canSelectRows : false,
        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('modificarUsuario', $scope.gridOptions.selectedItems[0].usuaUsua);
                //console.log('Se emitio evento <modificarUsuario> ');
                //console.log($scope.gridOptions.selectedItems[0].usuaUsua);
            }
        }
    };


 




    rowCount = 0;
    var newRow = null;

    $scope.onAddRow = function(){
        $scope.myData.push ( {
            cita: "",
            cirugia: "",
            detalles:"",
            observaciones:""         
            });
    };

    $scope.guardar = function(){
        cirugiaService.save($scope.myData).$promise.then(
        function () {
          // Broadcast the event to refresh the grid.
          $rootScope.$broadcast('refreshGrid');
          // Broadcast the event to display a save message.
          $rootScope.$broadcast('usuarioSaved');
          
        },
        function () {
          // Broadcast the event for a server error.
          $rootScope.$broadcast('error');
        });
    };


    $scope.searchTextChanged = function(){
      console.log('Ingreso a funcion searchTextChanged');
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listUsuariosArgs = {
            
        };

       /* medicamentoService.get(listUsuariosArgs, function (data) {
            $scope.usuarios = data;
        });*/
    };

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
      var usarName = row.entity.usuaEmail;
      var modalOptions = {
          closeButtonText: 'Cancelar',
          actionButtonText: 'Eliminar Usuario',
          headerText: 'Eliminar ' + usarName,
          bodyText: '¿Esta seguro de eliminar este usuario?'
      };

      modalService.showModal({}, modalOptions).then(function (result) {
        $rootScope.$broadcast('deleteUsuario', row.entity.usuaUsua);
      });
      
    };
    
    $scope.updateRow = function(row){
      var usuaUsua = row.entity.usuaUsua;
      $state.go("modificarUsuario", {'usuaUsua':usuaUsua});
    };

    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo', function () {
        $scope.usuarios = {currentPage: 1};
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
    
    // Picks us the event broadcasted when the person is deleted from the grid and perform the actual person delete by
    // calling the appropiate rest service.
    $scope.$on('deleteUsuario', function (event, id) {
      console.log('Evento eliminar usuario :' + id);
      cirugiaService.delete({usuaUsua: id}).$promise.then(
          function () {
              // Broadcast the event to refresh the grid.
              $rootScope.$broadcast('refreshGrid');
              // Broadcast the event to display a delete message.
              $rootScope.$broadcast('usuarioDeleted');
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
    $scope.$on('usuarioSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('usuarioDeleted', function () {
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
