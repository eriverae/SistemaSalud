
//$(window).load(function () {
//  // Una vez se cargue al completo la página desaparecerá el div "cargando"
//  $('#cargando').hide();
//});

/**
 * calendarDemoApp - 0.9.0
 */



var app = angular.module('sisc_web');

app.filter("getEdad", function () {  //fa y fb dos fechas
    return function (input) {
        var fa = new Date(input);
        var fb = new Date();
        var totdias = fa - fb;
        totdias /= 3600000;
        totdias /= 24;
        totdias = Math.floor(totdias);
        totdias = Math.abs(totdias);

        var ans, meses, dias, m2, m1, d3, d2, d1;
        var f2 = new Date();
        var f1 = new Date();

        if (fa > fb) {
            f2 = fa;
            f1 = fb;
        } else {
            var f2 = fb;
            f1 = fa;
        }  //Siempre f2 > f1
        ans = f2.getFullYear() - f1.getFullYear(); // dif de años inicial
        m2 = f2.getMonth();
        m1 = f1.getMonth();
        meses = m2 - m1;
        if (meses < 0) {
            meses += 12;
            --ans;
        }

        d2 = f2.getDate();
        d1 = f1.getDate();
        dias = d2 - d1;

        var f3 = new Date(f2.getFullYear(), m2, 1);
        f3.setDate(f3.getDate() - 1);
        d3 = f3.getDate();

        if (d1 > d2) {
            dias += d3;
            --meses;
            if (meses < 0) {
                meses += 12;
                --ans;
            }
            if (fa > fb) {  //corrección por febrero y meses de 30 días
                f3 = new Date(f1.getFullYear(), m1 + 1, 1);
                f3.setDate(f3.getDate() - 1);
                d3 = f3.getDate();
                if (d3 == 30)
                    dias -= 1;
                if (d3 == 29)
                    dias -= 2;
                if (d3 == 28)
                    dias -= 3;
            }
        }
//        var res=ans +'años con '+meses+' meses y '+ dias  ;
        return ans + ' años con ' + meses + ' meses y ' + dias + ' dias';
    }
});

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

//app.controller('agendaMedicoContoller',
//        function ($scope, $compile, $timeout, uiCalendarConfig, $http, $stateParams, $state) {

app.controller('agendaMedicoContoller',
        function ($scope, $compile, $timeout, uiCalendarConfig, $http,$state) {

            var idMedicoSesion = 0;
            var medicoPro = null;

            /*Validacion de objeto personaNatural en localStorage*/
            if (localStorage.getItem('personaNatural') !== null) {
                medicoPro = JSON.parse(localStorage.getItem('personaNatural'));
                console.log('personaNatural localStorage: ');
                console.log(medicoPro);
                if (medicoPro.idPersona === null) {
                    medicoPro = JSON.parse('{"idPersona":9,"tipoIdentificacion":"CC","numeroIdentificacion":3456765434,"version":0,"correoElectronico":"medico@prueba.com","nombres":"Ã‰rika","apellidos":"Villa","genero":"F","fechaNacimiento":511074000000,"telefonoCelular":4567655676,"telefonoFijo":45654456,"direccion":"gygbnhb","fotografia":null,"huella":null,"rh":"+","grupoSanguineo":"A","tarjetaProfesional":null,"rolPersonaNatural":"MEDICO"}');
                }                
            }else{
                medicoPro = JSON.parse('{"idPersona":9,"tipoIdentificacion":"CC","numeroIdentificacion":3456765434,"version":0,"correoElectronico":"medico@prueba.com","nombres":"Ã‰rika","apellidos":"Villa","genero":"F","fechaNacimiento":511074000000,"telefonoCelular":4567655676,"telefonoFijo":45654456,"direccion":"gygbnhb","fotografia":null,"huella":null,"rh":"+","grupoSanguineo":"A","tarjetaProfesional":null,"rolPersonaNatural":"MEDICO"}');
            }

              idMedicoSesion = medicoPro.idPersona;

//           idMedicoSesion=$stateParams.idMedico;

            console.log('idMedico: ' + idMedicoSesion);

            $scope.objErrorNuevaAgenda;
            $scope.generalResponse;
            $scope.infoConsultaCita;

            $scope.nuevaAgenda = {
                fechaInicio: '',
                fechaFinal: '',
                semana: {
                    listaDias: [
                        {numeroDia: 1, dia: 'Lunes', incluir: false},
                        {numeroDia: 2, dia: 'Martes', incluir: false},
                        {numeroDia: 3, dia: 'Miercoles', incluir: false},
                        {numeroDia: 4, dia: 'Jueves', incluir: false},
                        {numeroDia: 5, dia: 'Viernes', incluir: false},
                        {numeroDia: 6, dia: 'Sabado', incluir: false},
                        {numeroDia: 0, dia: 'Domingo', incluir: false}
                    ],
                    numeroDiasSelecionado: 0
                },
                horaInicio: '08:00:00',
                horaFinal: '12:00:00',
                cantidadMinutosXCita: null,
                idPersonaEps: 0,
                idMedico: idMedicoSesion,
                ciudad: 'Bogotá',
                localidad: '',
                direccion: '',
                consultorio: null

            };

            $scope.irMenuHC = function (cita) {
                $state.go('menuhc', {'cita': cita});
            };


            /* funcion para validar si se incluye el dia seleccionado en la agenda */
            $scope.colocarDiasAgenda = function (dia, choice, index) {
                if (choice.checked) {
                    dia.incluir = true;
                    $scope.nuevaAgenda.semana.listaDias[index] = dia;
                    $scope.nuevaAgenda.semana.numeroDiasSelecionado = $scope.nuevaAgenda.semana.numeroDiasSelecionado + 1;

                } else {
                    dia.incluir = false;
                    $scope.nuevaAgenda.semana.listaDias[index] = dia;
                    $scope.nuevaAgenda.semana.numeroDiasSelecionado = $scope.nuevaAgenda.semana.numeroDiasSelecionado - 1;

                }

            };

            var date = new Date();

            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();



            $scope.listEpsMedico = {};
            /**
             * Traer lista eps medico
             */
            var data_eps = $http.get('/SiscAgenda/api/medico/agenda/' + idMedicoSesion + '/listaEps');
            data_eps.then(function (result) {
                $scope.listEpsMedico = result.data;
            });


            $scope.terminarCreacionNuevaAgenda = function () {
                $('#message-box-success').hide();
            };

            var configServicePost = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };

            $scope.agregarAgenda = function () {
                /**
                 * Validar datos del formulario.
                 */
                var validacion = true;
                if ($scope.nuevaAgenda.semana.numeroDiasSelecionado <= 0) {
                    alert('Selecciona al menos un día para agendar.');
                    validacion = false;
                }
                if (validacion) {

                    /**
                     * Llamar a servicio post par insertar agenda.
                     */
                    $http.post('/SiscAgenda/api/medico/agenda/nuevaAgenda', $scope.nuevaAgenda, configServicePost)
                            .success(function (data, status, headers, config) {
                                /**
                                 * Insertar en arreglo de citas
                                 */
                                if (data.codigoRespuesta === "SUCCESS") {
                                    /**
                                     * Mensaje de confirmacion de agenda insertada correctamente.
                                     */

                                    $scope.generalResponse = data.objectResponse;
                                    $('#message-box-success').show();

                                    $scope.calEventsExt.events = [];
                                    var utilRest1 = $http.get('/SiscAgenda/api/medico/agenda/' + idMedicoSesion);
                                    utilRest1.then(function (result) {
                                        var obj = result.data;
                                        if (obj.existeAgenda) {
                                            $.each(obj.events, function (k, v) {
                                                $scope.calEventsExt.events.push(v);
                                            });
                                        }
                                    });



                                } else {
                                    if (data.codigoRespuesta === "ERROR") {
                                        $scope.objErrorNuevaAgenda = data.error;
                                        $('#message-box-sound-2').show();


                                    }
                                }

                            })
                            .error(function (data, status, header, config) {

                            });

                }

            };


            $scope.changeTo = 'Hungarian';
            /* event source that pulls from google.com */
            $scope.eventSource = {
                url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
                className: 'gcal-event', // an option!
                currentTimezone: 'America/Chicago' // an option!
            };
            /* arreglo con las agendas a programar */
            $scope.events = [
//      {title: 'All Day Event',start: new Date(y, m, 1)},

            ];
            /* event source that calls a function on every view switch */
            $scope.eventsF = function (start, end, timezone, callback) {
                var s = new Date(start).getTime() / 1000;
                var e = new Date(end).getTime() / 1000;
                var m = new Date(start).getMonth();


                var events = [{title: 'Feed Me ' + m, start: s + (50000), end: s + (100000), allDay: false, className: ['customFeed']}];
                callback(events);
            };

//            var utilRest = $http.get('/SiscAgenda/api/medico/agenda/' + $stateParams.idMedico);
//
//            utilRest.then(function (result) {
//
//                var obj = result.data;
//                if (obj.existeAgenda) {
//                    $.each(obj.events, function (k, v) {
////                        alert('aca?');
//                        $scope.calEventsExt.events.push(v);
//                    });
//                    alert('sali?');
//                    $scope.loading = false;
//                }
//
//
//            });
            $http.get('/SiscAgenda/api/medico/agenda/' + idMedicoSesion).
                    success(function (data) {

                        var obj = data;
                        if (obj.existeAgenda) {
                            $.each(obj.events, function (k, v) {
//                        alert('aca?');
                                $scope.calEventsExt.events.push(v);
                            });
//                           $('#pleaseWaitDialog').hide();
//                            $scope.loading = false;
                        }
                    });


            $scope.calEventsExt = {
                textColor: '#f1111',
                events: []
            };


            /* alert on eventClick */
            $scope.alertOnEventClick = function (date, jsEvent, view) {


//                console.log(jsEvent);
//                console.log(view);
//                var eventAux=$scope.calEventsExt.events[0];
//                console.log('eventAux: '+eventAux);
//                eventAux.className = ['highPriority'];                
//                $scope.calEventsExt.events.splice(0,1);
//                 $scope.calEventsExt.events.push(eventAux);


//                $scope.calEventsExt.events.push( $scope.calEventsExt.events[0]);

//                 jsEvent.attr({className : ['highPriority']});
//                $compile(jsEvent)($scope);

//                
//                 $compile($scope.calEventsExt.events[0])($scope);
//                callback($scope.calEventsExt);

                var utilRest = $http.get('/SiscAgenda/api/paciente/' + date.idCita + "/consultarCita");
                utilRest.then(function (result) {
                    var obj = result.data;

                    $scope.infoConsultaCita = result.data;

//                    $('#tab222').show();
//                    $('#tab232').hide();
//                    $('#tab242').hide();


                    $("#info_init_cita").click();


                    $('#myModal').modal();




                });

            };
            /* alert on Drop */
            $scope.alertOnDrop = function (event, delta, revertFunc, jsEvent, ui, view) {
                $scope.alertMessage = ('Event Dropped to make dayDelta ' + delta);
            };
            /* alert on Resize */
            $scope.alertOnResize = function (event, delta, revertFunc, jsEvent, ui, view) {
                $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
            };
            /* add and removes an event source of choice */
            $scope.addRemoveEventSource = function (sources, source) {
                var canAdd = 0;
                angular.forEach(sources, function (value, key) {
                    if (sources[key] === source) {
                        sources.splice(key, 1);
                        canAdd = 1;
                    }
                });
                if (canAdd === 0) {
                    sources.push(source);
                }
            };


            /* add custom event*/
            $scope.addEvent = function () {
                $scope.events.push({
                    title: 'Open Sesame',
                    start: new Date(y, m, d),
                    end: new Date(y, m, d),
                    className: ['openSesame']
                });
            };
            /* remove event */
            $scope.remove = function (index) {
                $scope.events.splice(index, 1);
            };
            /* Change View */
            $scope.changeView = function (view, calendar) {
                uiCalendarConfig.calendars[calendar].fullCalendar('changeView', view);
            };
            /* Change View */
            $scope.renderCalender = function (calendar) {

                $timeout(function () {
                    if (uiCalendarConfig.calendars[calendar]) {
                        uiCalendarConfig.calendars[calendar].fullCalendar('render');
                    }
                });
            };
            /* Render Tooltip */
            $scope.eventRender = function (event, element, view) {
                element.attr({'tooltip': event.title,
                    'tooltip-append-to-body': true});
                $compile(element)($scope);
            };
            /* config object */
            $scope.uiConfig = {
                calendar: {
                    height: 450,
                    editable: true,
                    header: {
                        left: 'title',
                        center: '',
                        right: 'Hoy prev,next'
                    },
                    eventClick: $scope.alertOnEventClick,
                    eventDrop: $scope.alertOnDrop,
                    eventResize: $scope.alertOnResize,
                    eventRender: $scope.eventRender
                }
            };
            $scope.uiConfig.calendar.dayNames = ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"];
            $scope.uiConfig.calendar.dayNamesShort = ["Dom", "Lun", "Mar", "Mi", "Jue", "Vi", "Sa"];

            /* event sources array*/
            $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];
            $scope.eventSources2 = [$scope.calEventsExt, $scope.eventsF, $scope.events];

            $scope.opcionesCita = function (idCitaSelec, accionSelec) {

                var request = {
                    idCita: idCitaSelec,
                    accion: accionSelec
                };
                console.log('Request: /SiscAgenda/api/medico/agenda/opcionesCita');
                console.log(request);

                $http.post('/SiscAgenda/api/medico/agenda/opcionesCita', request, configServicePost)
                        .success(function (data, status, headers, config) {
                            /**
                             * Insertar en arreglo de citas
                             */
                            console.log('Response: /SiscAgenda/api/medico/agenda/opcionesCita');
                            console.log(data);

                            if (data.codigoRespuesta === "SUCCESS") {
                                /**
                                 * Mensaje de confirmacion de agenda insertada correctamente.
                                 */

                                $scope.calEventsExt.events = [];
                                var utilRest1 = $http.get('/SiscAgenda/api/medico/agenda/' + idMedicoSesion);
                                utilRest1.then(function (result) {
                                    var obj = result.data;
                                    if (obj.existeAgenda) {
                                        $.each(obj.events, function (k, v) {
                                            $scope.calEventsExt.events.push(v);
                                        });
                                    }
                                    $scope.generalResponse = data.objectResponse;
                                    $('#myModal').modal('hide');
                                    $('#message-box-success-2').modal();
                                });

                            } else {
                                if (data.codigoRespuesta === "ERROR") {
                                    $('#myModal').modal('hide');
                                    $('#message-box-sound-3').modal();
                                    $scope.generalResponse = data.error.mensajeError;
                                }
                            }

                        })
                        .error(function (data, status, header, config) {
                            $('#myModal').modal('hide');
                            $('#message-box-sound-3').modal();
                            $scope.generalResponse = data.error.mensajeError;
                        });

            };

        });

/* EOF */
