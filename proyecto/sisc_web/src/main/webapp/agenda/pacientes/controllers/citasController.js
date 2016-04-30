/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//modulo
var app = angular.module('sisc_web');

app.filter("getFormatofecha", function () {
    return function (input) {
        var res = new Date(input);
        return res.getDate() + '-' + (res.getMonth() + 1) + '-' + res.getFullYear();

    }
});
app.filter("getFormatoHora", function () {
    return function (input) {
        var res = new Date(input);
        return res.getHours() + ':' + (res.getMinutes()) + ':' + res.getSeconds();

    }
});


app.controller('citasController',
        function ($scope, $http, $stateParams) {





            ////////////////////////////////////////////////////////////////////
            $scope.listaCitasPaciente = {};
            /**
             *  Traer la lista de citas de un paciente
             */
            var data_citasPaciente = $http.get('/SiscAgenda/api/paciente/' + $stateParams.idPaciente + '/listaCitas');
            data_citasPaciente.then(function (result) {
                $scope.listaCitasPaciente = result.data;
            });
            ////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////////////////////////
            //provicional mientras tanto <esconderMensajeCitaSeleccionada>
            $scope.esconderMensajeCitaSeleccionada = true;
            $scope.mostrarMensajeCitaSeleccionada = function () {
                $scope.esconderMensajeCitaSeleccionada = !$scope.esconderMensajeCitaSeleccionada;
            }
            ////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////////////////////////
            $scope.informacionCita = null;
            $scope.mensajesCita = {};
            /**
             * Mostrar una cita detallada, que fue seleccionada por el paciente
             */
            $scope.mostrarUnaCitaDetallada = function (informacionCita) {
                //alert("entro a mostrar una cita mediante un click");
                $scope.informacionCita = informacionCita;

                $('#message-box-sound-2').show();
                $('#audio-fail').get(0).play();

                $scope.mensajesCita =
                        {
                            msn_citaSeleccionada1: 'MUY BIEN!!! ',
                            msn_citaSeleccionada2: 'Has seleccionado una cita correctamente'
                        };
            }
            ////////////////////////////////////////////////////////////////////

            $scope.cancelarCita = function (cita) {
                /**
                 * Cancelar la cita seleccionada por un paciente.
                 */
                $('#message-box-sound-2').hide();
                $('.noty_message').show();
                
                var configServicePost = {
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8;'
                    }
                }

                $http.post('/SiscAgenda/api/paciente/cancelarCita', cita, configServicePost)
                        .success(function (data, status, headers, config) {
                            alert("PERFECTO: cita cancelada..");

                        })
                        .error(function (data, status, header, config) {
                            //alert("ERROR: no se pudo cancelar la cita");
                            $('#message-box-warning').show();

                        });
            };


        });

