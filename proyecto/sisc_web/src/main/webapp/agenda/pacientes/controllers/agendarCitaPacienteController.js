/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var app = angular.module('sisc_web');

app.controller('agendarCitaPacienteController',
        function ($scope, $http, $stateParams, $state, $compile, $timeout) {

            //window.setTimeout(function(){location.reload()},4000);

            ////////////////////////////////////////////////////////////////////
            $scope.listaespecialidadesEPS = [];
            /**
             *  Traer la lista especialidades que hay en la eps
             */
            var especialidadesEPS = $http.get('/SiscAgenda/api/paciente/especialidadesMedicosEPS');
            especialidadesEPS.then(function (result) {
                $scope.listaespecialidadesEPS = result.data;
            });
            ////////////////////////////////////////////////////////////////////
            //http://localhost:8080/SiscAgenda/api/paciente/especialidadesMedicosEPS
            

            ////////////////////////////////////////////////////////////////////
//            $scope.especialidad = $('especialidad-'+ item.descripcion);
//            console.log();
            
            $scope.listamedicosEspecialidadEPS = [];
            /**
             *  Traer la lista de medicos segun la especicalidad seleccionada
             *  http://localhost:8080/sisc_web/pages-address-book.html#
             */
            var medicosEspecialidadEPS = $http.get('/SiscAgenda/api/paciente/' + $stateParams.descripcion + '/listaMedicosEspecialidad');
            medicosEspecialidadEPS.then(function (result) {
                $scope.listamedicosEspecialidadEPS = result.data;
            });
            ////////////////////////////////////////////////////////////////////
            //http://localhost:8080/SiscAgenda/api/paciente/CARDIOLOGO/listaMedicosEspecialidad



        });
//fin <pacienteAgendaCitaController>




/******************************************************************************
 * inicial plugin de select buscador
 */
// init jquery functions and plugins
//$(document).ready(function () {
//    $.getScript('//cdnjs.cloudflare.com/ajax/libs/select2/3.4.8/select2.min.js', function () {
//        
//        $("#mySel").select2({
//        });
//        
//    }); //script select2.min.js
//});