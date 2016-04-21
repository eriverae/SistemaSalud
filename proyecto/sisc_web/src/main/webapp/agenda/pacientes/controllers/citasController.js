/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//modulo
var app = angular.module('sisc_web');

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



            //provicional mientras tanto <esconderMensajeCitaSeleccionada>
            $scope.esconderMensajeCitaSeleccionada = true;
            $scope.mostrarMensajeCitaSeleccionada = function() {
                    $scope.esconderMensajeCitaSeleccionada = !$scope.esconderMensajeCitaSeleccionada;
            }
            
            
            
            $scope.informacionCita = null;
            //$scope.mensajesCita = {};
            /**
             * Mostrar una cita detallada, que fue seleccionada por el paciente
             */
            $scope.mostrarUnaCitaDetallada = function(informacionCita) {
                //alert("entro a mostrar una cita mediante un click");
                $scope.informacionCita = informacionCita;
                
//                $scope.mensajesCita = [
//                    {
//                        msn_citaSeleccionada1: 'MUY BIEN!!! ',
//                        msn_citaSeleccionada2 : 'Has seleccionado una cita correctamente'
//                    }
//                ];
                  
            }



});

