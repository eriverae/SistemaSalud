'use strict';
var app = angular.module('sisc_web');

// Create a controller with name personsFormController to bind to the form section.
app.controller('loginFormController', function ($scope, $rootScope, $stateParams, loginService, $state, store, AuthService) {
  $scope.credenciales={};
  
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
      console.log(data);
      if (data.autheticated){
        store.set('autheticated', data.autheticated);
        store.set('listaGrupos', data.listaGrupos);
        store.set('personaNatural', data.personaNatural);
        store.set('token', data.token);
        store.set('login', $scope.credenciales.usuario);
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
      }else{
        $state.go('login');
      }
    },
    function () {
      // Broadcast the event for a server error.
      $rootScope.$broadcast('error');
    });
  };
  
  $scope.doLogout = function(){
      //auth.signout();
      store.remove('autheticated');
      store.remove('listaGrupos');
      store.remove('personaNatural');
      store.remove('token');
      $state.go('login');
  };
  
  $scope.inSession = function (){
      var aut = store.get('autheticated');
      if (aut !== null){
          return aut;
      }
      return false;
  };
  
});