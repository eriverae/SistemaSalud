/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('sisc_web');

app.controller('pacienteConsultaMedicoEspecializado',
        function ($scope, $http, $state, $compile, $timeout) {

            $scope.especialidadSelected = "";
            $scope.fechaSeleccionada = "";
            $scope.listaespecialidadesEPS = [];
            $scope.listamedicosEspecialidadEPS = [];
            $scope.medicosDisponible1 = null;
            $scope.fechaAc = new Date().getTime();
            $scope.generalResponse = null;
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
            /**
             * Metodo para agendar cita de un paciente con un medico
             * @param {type} cita
             * @returns {undefined}
             */
            $scope.agendarCitaPaciente = function (cita) {
                console.log(cita.idCita);
                console.log('rest.. ' + '/SiscAgenda/api/paciente/agendar/cita/' + cita.idCita + '/paciente/' + idPacienteSesion)
                $http.post('/SiscAgenda/api/paciente/agendar/cita/' + cita.idCita + '/paciente/' + idPacienteSesion, null, configServicePost)
                        .success(function (data, status, headers, config) {
                            if (data.codigoRespuesta === "SUCCESS") {
                                cita.estadoCita = 'APARTADA';
                                $('#vistaHorariosMedicos').modal('hide');
                                $('#message-box-success').modal();
                            } else {
                                $('#vistaHorariosMedicos').modal('hide');
                                $('#message-box-sound-3').modal();
                                $scope.generalResponse = data.error.mensajeError;
                            }
                        })
                        .error(function (data, status, header, config) {

                        });
            };
            /**
             *  Traer la lista especialidades que hay en la eps
             */
            var especialidadesEPS = $http.get('/SiscAgenda/api/paciente/especialidadesMedicosEPS');
            especialidadesEPS.then(function (result) {
                $scope.listaespecialidadesEPS = result.data;
            });

            /**
             * Muestra los horarios disponibles en la agenda de un medico.
             * @param {type} medicoDisponible
             * @returns {undefined}
             */
            $scope.mostrarHoriosDisponible = function (medicoDisponible) {
                $scope.medicosDisponible1 = medicoDisponible;
                $('#vistaHorariosMedicos').modal();
            };

            /**
             * Metodo para realizar busqueda segun especialidad y/o fecha seleccionada
             * @returns {undefined}
             */
            $scope.buscarEspecialidades = function () {

                var urlServiceRest = "";

                if ($scope.especialidadSelected !== "" && $scope.fechaSeleccionada !== "") {
                    urlServiceRest = "/SiscAgenda/api/paciente/citasDisponibles?idEspecialidad=" + $scope.especialidadSelected +
                            "&idPaciente=" + idPacienteSesion + "&fechaBusqueda=" + $scope.fechaSeleccionada;
                } else {
                    urlServiceRest = "/SiscAgenda/api/paciente/citasDisponibles?idEspecialidad=" + $scope.especialidadSelected +
                            "&idPaciente=" + idPacienteSesion;
                }

                console.log(urlServiceRest);

                var medicosEspecialidadEPS = $http.get(urlServiceRest);
                medicosEspecialidadEPS.then(function (result) {
                    $scope.listamedicosEspecialidadEPS = result.data;
                });

            };
        });
