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
        function ($scope, $http, $stateParams, $rootScope) {

            $scope.objErrorCancelarCita;
            $scope.generalResponse;



            ////////////////////////////////////////////////////////////////////
            $scope.listaCitasPaciente = [];
            /**
             *  Traer la lista de citas pendientes de un paciente
             */
            var data_citasPaciente = $http.get('/SiscAgenda/api/paciente/' + $stateParams.idPaciente + '/listaCitas');
            data_citasPaciente.then(function (result) {
                $scope.listaCitasPaciente = result.data;
            });
            ////////////////////////////////////////////////////////////////////
            
            ////////////////////////////////////////////////////////////////////
            $scope.listaCitasPacienteHistorialEPS = [];
            /**
             *  Traer la lista de historial de itas en EPS de un paciente
             */
            var data_citasPacienteHistorialEPS = $http.get('/SiscAgenda/api/paciente/' + $stateParams.idPaciente + '/listaCitasHistorialEPS');
            data_citasPacienteHistorialEPS.then(function (result) {
                $scope.listaCitasPacienteHistorialEPS = result.data;
            });
            ////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////////////////////////
            $scope.esconderMensajeCitaSeleccionada = true;
            /**
             * provicional mientras tanto <esconderMensajeCitaSeleccionada>
             * @returns {undefined}
             */
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
                console.log("mostrarUnaCitaDetallada(informacionCita)")
                console.log("idCita" + informacionCita.idCita)

                $scope.informacionCita = informacionCita;

                $('#message-box-sound-1').show();
                $('#audio-fail').get(0).play();

                $scope.mensajesCita =
                        {
                            msn_citaSeleccionada1: 'MUY BIEN!!! ',
                            msn_citaSeleccionada2: 'Has seleccionado una cita correctamente'
                        };
            }
            ////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////////////////////////
            $scope.posicionFila = -1;

            /**
             * No cancelar mi cita que fue seleccionada en el boton cancelar
             * @returns {undefined}
             */
            $scope.confirmacionNOCancelarCita = function () {
                $('#mb-signout').hide();
                console.log("*** no cancelo mi cita");
                $scope.posicionFila = -1;
            };

            /**
             * Confirmacion para poder cancelar una cita que fue seleccionada
             * @param {type} index
             * @param {type} cita
             * @returns {undefined}
             */
            $scope.confirmacionCancelarCita = function (index, cita) {
                $('#mb-signout').show();
                console.log("***" + index);
                $scope.posicionFila = index;
                //citaEscogida = cita;
            };
            /**
             * Si la confirmacion de eliminar la cita es TRUE, entonces
             * se cancelara la cita del paciente
             * @returns {undefined}
             */
            $scope.cancelarCita = function () {
                /**
                 * Cancelar la cita seleccionada por un paciente.
                 */
                if ($scope.posicionFila >= 0) {
                    console.log("*** popsicio0nFila = " + $scope.posicionFila + "  ..  ");


                    var configServicePost = {
                        headers: {
                            'Content-Type': 'application/json;charset=utf-8;'
                        }
                    }

                    console.log("idCita-->" + JSON.stringify($scope.listaCitasPaciente[$scope.posicionFila].idCita));
                    console.log("...");
                    var idCita = $scope.listaCitasPaciente[$scope.posicionFila].idCita + "";
                    //$http.post('/SiscAgenda/api/paciente/cancelarCita', idCita, configServicePost)
                    $http.post('/SiscAgenda/api/paciente/' + idCita + '/cancelarCita', configServicePost)
                            .success(function (data, status, headers, config) {
                                if (data.codigoRespuesta === "SUCCESS") {
                                    console.log("codigo respuesta === SUCCES");
                                    $('#mb-signout').hide();

                                    $scope.generalResponse = data.objectResponse;
                                    //$('#message-box-success').show();
                                    
                                    ////////////////////////////////////////////
                                    //RECARGAR PAGINA 
//                                    window.setTimeout(function(){location.reload()},4000);
                                    $('#cita-'+idCita).remove();
                                    $('#message-box-success').show();


//                                    $scope.mensajesCita =
//                                            {
//                                                msn_citaSeleccionada1: 'MUY BIEN!!! ',
//                                                msn_citaSeleccionada2: 'Has cancelado una cita correctamente'
//                                            };
                                } else {
                                    if (data.codigoRespuesta === "ERROR") {
                                        console.log("codigo respuesta === ERROR");
                                        $('#mb-signout').hide();

                                        $scope.objErrorCancelarCita = data.error;
                                        $('#message-box-sound-2').show();
                                    }
                                }

                            })
                            .error(function (data, status, header, config) {
                                $('#mb-signout').hide();
                                console.log(" .error(function (data, status, header, config) ");
                                //$('#message-box-warning').show();
                                alert("ERROR: Noo se puede cancelar la cita...");


                            });
                } else {
                    console.log("problemas ... ELSE");
                }
            };

            $scope.cerrarCancelarCita = function () {
                $('#message-box-success').hide();
            };                       




            /******************************************************************* */
            /******************************************************************* */

            
$(document).ready(function() {
    $('#example').DataTable( {
        "pagingType": "full_numbers"
    } );
} );     
            







            //fin <citasController>
        });

//$(document).ready(function() {
//    $('#example').DataTable( {
//        "pagingType": "full_numbers"
//    } );
//} );

