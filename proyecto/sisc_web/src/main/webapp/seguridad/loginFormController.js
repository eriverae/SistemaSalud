'use strict';
var app = angular.module('sisc_web');

// Create a controller with name personsFormController to bind to the form section.
app.controller('loginFormController', function ($scope, $rootScope, $stateParams, loginService, $state, store, AuthService) {
    $scope.title = 'Login SISC';
    $scope.credenciales = {};

    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.credenciales = null;
        // Resets the form validation state.
        $scope.credencialesForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
        $state.go('login');
    };
    
    $scope.hacerLogin = function () {
        loginService.save($scope.credenciales).$promise.then(
                function (data) {
                    if (data.autheticated) {
                        $rootScope.grupAuth = data.listaGrupos;
                        store.set('autheticated', data.autheticated);
                        store.set('listaGrupos', data.listaGrupos);
                        store.set('personaNatural', data.personaNatural);
                        store.set('token', data.token);
                        store.set('login', $scope.credenciales.usuario);
                        //TODO Consultar la persona por email personaNatural/getByEmail/{email}
                        // Broadcast the event to refresh the grid.
                        $rootScope.$broadcast('refreshGrid');
                        // Broadcast the event to display a save message.
                        $rootScope.$broadcast('usuarioSaved');
                        data.id = data.token;
                        data.role = data.listaGrupos;
                        data.roleCode = data.listaGrupos;
                        data.userMail = $scope.credenciales.usuario;

                        //Session.create(data.token, data.listaGrupos, $scope.credenciales.usuario);
                        AuthService.newSession(data);
                        $state.go('home');
                    } else {
                        $('#message-box-danger').show();
                    }
                },
                function () {
                    // Broadcast the event for a server error.
                    $rootScope.$broadcast('error');
                });
    };
    
    $scope.closepopup_failure = function () {
        $('#message-box-danger').hide();
        $state.go('login');
    };

    function doCleanValuesStorage() {
        delete $rootScope.autheticated;
        localStorage.clear();
    }

    $scope.doCleanAlert = function () {
        doCleanValuesStorage();
    };

    $scope.doLogout = function () {
        //auth.signout();
        doCleanValuesStorage();
        $state.go('login');
    };

    $scope.inSession = function () {
        var aut = store.get('autheticated');
        if (aut !== null) {
            return aut;
        }
        return false;
    };

});