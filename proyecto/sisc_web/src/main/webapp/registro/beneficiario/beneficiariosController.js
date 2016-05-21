'use strict';
var app = angular.module('sisc_web');

app.controller('beneficiariosController', function ($scope, $rootScope, $stateParams, $state, personaService, modalService) {
    $scope.cotizante = {};
    $scope.beneficiario = {};
    $scope.parentezco;
    $scope.beneficiarioSelected = {};
    $scope.numeroIdBeneficiario;

    if (angular.isDefined($stateParams.idPersona)) {
        console.log('Cotizante a..., ID = ' + $stateParams.idPersona);
        personaService.get({id: $stateParams.idPersona}).$promise.then(
                function (data) {
                    console.log('Datos de cotizante encontrados');
                    $scope.cotizante = data;
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
        //personaService.get({id: $scope.numeroIdBeneficiario}).$promise.then(
        personaService.getByNumber({numberId: $scope.numeroIdBeneficiario}).$promise.then(
        function (data) {
            console.log('Datos de beneficiario encontrados');
            $scope.beneficiario = data;
        },
        function () {
            console.log('Datos paila :(');
            // Broadcast the event for a server error.
            $rootScope.$broadcast('error');
        });
    };

    $scope.asociarBeneficiario = function() {
        var args = {
            cotizante: $scope.cotizante,
            beneficiario: $scope.beneficiario,
            parentezco: $scope.parentezco
        };
        personaService.asociarBeneficiario(args).$promise.then(
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
    
    ////////////////////////////////////////////////////////////////////////////
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.beneficiarios = {currentPage: 1};
    $scope.searchText = null;

    $scope.gridOptions = {
        data: 'beneficiarios.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'idPersonaNaturalBeneficiario', displayName: 'Id'},
            { field: 'cotizante.nombres', displayName: 'Cotizante Nombre'},
            { field: 'cotizante.apellidos', displayName: 'Cotizante Apellidos' },
            { field: 'beneficiario.nombres', displayName: 'Beneficiario Nombre'},
            { field: 'beneficiario.apellidos', displayName: 'Beneficiario Apellidos' },
            { field: '', width: 80, 
                cellTemplate: '<span class="glyphicon glyphicon-trash remove" ng-click="deleteBeneficiario(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('beneficiarioSelected', $scope.gridOptions.selectedItems[0].idPersona);
                console.log('Se emitio evento <beneficiarioSelected>');
            }
        }
    };
    
    $scope.searchTextChanged = function(){
      console.log('Ingreso a funcion searchTextChanged');
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listBeneficiariosArgs = {
            page: $scope.beneficiarios.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0],
            cotizante: $scope.cotizante.idPersona
        };

        personaService.beneficiarios(listBeneficiariosArgs, function (data) {
            $scope.beneficiarios = data;
        });
    };

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteBeneficiario = function (row) {
      var m = row.entity.nombres + " " + row.entity.apellidos;
      var modalOptions = {
          closeButtonText: 'Cancelar',
          actionButtonText: 'Eliminar Beneficiario',
          headerText: 'Eliminar ' + m,
          bodyText: '¿Esta seguro de eliminar esta persona como beneficiario?'
      };

      modalService.showModal({}, modalOptions).then(function (result) {
        $rootScope.$broadcast('deleteBeneficiario', row.entity.idPersona);
      });
      
    };
        
    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo', function () {
        $scope.beneficiarios = {currentPage: 1};
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
    
    $scope.$on('beneficiarioSelected', function (event, id) {
        console.log("Ejecuta beneficiarioselected " & id);
        $scope.beneficiarioSelected = personaService.get({id: id});
    });

    $scope.$on('deleteBeneficiario', function (event, id) {
      console.log('Evento eliminar beneficiario:' + id);
      //personaService.delete({id: id}).$promise.then(
      //    function () {
      //        $rootScope.$broadcast('refreshGrid');
      //        $rootScope.$broadcast('medicoDeleted');
      //    },
      //    function () {
      //        $rootScope.$broadcast('error');
      //    });
    });
});
