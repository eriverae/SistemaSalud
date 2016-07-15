/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var app = angular.module('sisc_web');
/**
 *Definicio de filtro para html de implementacion del controlador 
 */
app.filter("getFormatoEstado", function () {
    return function (input) {
        if (input === 'DISPONIBLE') {
            return 'Disponible';
        } else {
            if (input === 'CANCELADA') {
                return 'Cancelada';
            } else {
                if (input === 'APARTADA') {
                    return 'Agendada';
                } else {
                    if (input === 'PACIENTE_ASISTIO_A_CITA') {
                        return 'Atendido';
                    } else {
                        if (input === 'PACIENTE_NO_ASISTIO_A_CITA') {
                            return 'No asistio';
                        } else {
                            return input;
                        }
                    }
                }
            }
        }

    };
});
/**
 * Filtro formato de fecha
 */
app.filter("getFormatofecha", function () {
    return function (input) {
        var res = new Date(input);
        return res.getDate() + '-' + (res.getMonth() + 1) + '-' + res.getFullYear();

    };
});
/**
 * Filtro formato de hora
 */
app.filter("getFormatoHora", function () {
    return function (input) {
        var res = new Date(input);
        return res.getHours() + ':' + (res.getMinutes()) + ':' + res.getSeconds();

    };
});

app.controller('citasController',
        function ($scope, $http, $stateParams, $rootScope, DTOptionsBuilder, DTColumnDefBuilder) {

            var idPacienteSesion = 0;
            var medicoPro = null;
            var configServicePost = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            /*Validacion de objeto personaNatural en localStorage*/
            if (localStorage.getItem('personaNatural') !== null) {
                medicoPro = JSON.parse(localStorage.getItem('personaNatural'));
                console.log('personaNatural localStorage: ');
                console.log(medicoPro);
                if (medicoPro.idPersona === null) {
                    medicoPro = JSON.parse('{"idPersona":23,"tipoIdentificacion":"CC","numeroIdentificacion":151515154,"version":0,"correoElectronico":"paciente@prueba.com","nombres":"jhvj","apellidos":"Rojas Rojas","genero":"M","fechaNacimiento":-2242062000000,"telefonoCelular":71717171717,"telefonoFijo":61616161,"direccion":"kkks","fotografia":null,"huella":null,"rh":"+","grupoSanguineo":"A","tarjetaProfesional":null,"rolPersonaNatural":"PACIENTE"}');
                }

            } else {
                medicoPro = JSON.parse('{"idPersona":23,"tipoIdentificacion":"CC","numeroIdentificacion":151515154,"version":0,"correoElectronico":"paciente@prueba.com","nombres":"jhvj","apellidos":"Rojas Rojas","genero":"M","fechaNacimiento":-2242062000000,"telefonoCelular":71717171717,"telefonoFijo":61616161,"direccion":"kkks","fotografia":null,"huella":null,"rh":"+","grupoSanguineo":"A","tarjetaProfesional":null,"rolPersonaNatural":"PACIENTE"}');
            }
            idPacienteSesion = medicoPro.idPersona;
            console.log('idPacienteSesion: ' + idPacienteSesion);

            $scope.objErrorCancelarCita;
            $scope.generalResponse;
            $scope.listaCitasPaciente = [];
            /**
             *  Traer la lista de citas pendientes de un paciente
             */
            var data_citasPaciente = $http.get('/SiscAgenda/api/paciente/' + idPacienteSesion + '/listaCitas');
            data_citasPaciente.then(function (result) {
                $scope.listaCitasPaciente = result.data;
            });

            $scope.listaCitasPacienteHistorialEPS = [];
            /**
             *  Traer la lista de historial de itas en EPS de un paciente
             */
            var data_citasPacienteHistorialEPS = $http.get('/SiscAgenda/api/paciente/' + idPacienteSesion + '/listaCitasHistorialEPS');
            data_citasPacienteHistorialEPS.then(function (result) {
                $scope.listaCitasPacienteHistorialEPS = result.data;
            });

            $scope.esconderMensajeCitaSeleccionada = true;
            /**
             * provicional mientras tanto <esconderMensajeCitaSeleccionada>
             * @returns {undefined}
             */
            $scope.mostrarMensajeCitaSeleccionada = function () {
                $scope.esconderMensajeCitaSeleccionada = !$scope.esconderMensajeCitaSeleccionada;
            };

            $scope.informacionCita = null;
            $scope.mensajesCita = {};

            /**
             * Mostrar una cita detallada, que fue seleccionada por el paciente
             * @param {type} informacionCita
             * @returns {undefined}
             */
            $scope.mostrarUnaCitaDetallada = function (informacionCita) {

                console.log("mostrarUnaCitaDetallada(informacionCita)");
                console.log("idCita" + informacionCita.idCita);

                $scope.informacionCita = informacionCita;
                $('#tab222').show();
                $('#message-box-sound-1').modal();
                $('#audio-fail').get(0).play();

                $scope.mensajesCita =
                        {
                            msn_citaSeleccionada1: 'MUY BIEN!!! ',
                            msn_citaSeleccionada2: 'Has seleccionado una cita correctamente'
                        };
            };

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
                    console.log("idCita-->" + JSON.stringify($scope.listaCitasPaciente[$scope.posicionFila].idCita));
                    
                    var idCita = $scope.listaCitasPaciente[$scope.posicionFila].idCita + "";
                    
                    $http.post('/SiscAgenda/api/paciente/' + idCita + '/cancelarCita', configServicePost)
                            .success(function (data, status, headers, config) {
                                if (data.codigoRespuesta === "SUCCESS") {
                                    console.log("codigo respuesta === SUCCES");
                                    $('#mb-signout').hide();

                                    $scope.generalResponse = data.objectResponse;
                                    $('#cita-' + idCita).remove();
                                    $('#message-box-success').show();


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
                                alert("ERROR: Noo se puede cancelar la cita...");


                            });
                } else {
                    console.log("problemas ... ELSE");
                }
            };

            $scope.cerrarCancelarCita = function () {
                $('#message-box-success').hide();
            };

            $(document).ready(function () {
                $('#example').DataTable({
                    "pagingType": "full_numbers"
                });
            });

        });
