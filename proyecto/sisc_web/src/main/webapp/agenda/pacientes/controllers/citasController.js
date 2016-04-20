/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//modulo
var app = angular.module('siscseguridad');

app.controller('citasController',
        function ($scope, $http, $stateParams) {


            $scope.listaCitasPaciente = {};
            /**
             * Traer lista de las citas del paciente
             */
            var data_citasPaciente = $http.get('/SiscAgenda/api/paciente/' + $stateParams.idPaciente + '/listaCitas');
            data_citasPaciente.then(function (result) {
                $scope.listaCitasPaciente = result.data;
            });


            $scope.informacionCita = null;
            /**
             * Mostrar una cita detallada, que fue seleccionada por el paciente
             */
            $scope.mostrarUnaCitaDetallada = function(informacionCita) {
                //alert("entro a mostrar una cita mediante un click");
                $scope.informacionCita = informacionCita;
            }



        });

