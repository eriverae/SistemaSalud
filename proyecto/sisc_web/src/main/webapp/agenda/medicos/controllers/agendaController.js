/**
 * calendarDemoApp - 0.9.0
 */
var app = angular.module('sisc_web');

app.controller('agendaMedicoContoller',
        function ($scope, $compile, $timeout, uiCalendarConfig, $http, $stateParams) {
            
            $scope.objErrorNuevaAgenda;
            
            $scope.nuevaAgenda = {
                fechaInicio: '18-04-2016',
                fechaFinal: '22-04-2016',
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
                cantidadMinutosXCita: 15,
                idPersonaEps: 0,
                idMedico:$stateParams.idMedico,
                especialidadCita: 'MEDICINA GENERAL'

            };


            $scope.semana = {
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
            };

            /* funcion para validar si se incluye el dia seleccionado en la agenda */
            $scope.colocarDiasAgenda = function (dia, choice, index) {
                if (choice.checked) {
                    dia.incluir = true;
                    $scope.nuevaAgenda.semana.listaDias[index] = dia;
                    $scope.nuevaAgenda.semana.numeroDiasSelecionado = $scope.semana.numeroDiasSelecionado + 1;
                } else {
                    dia.incluir = false;
                    $scope.nuevaAgenda.semana.listaDias[index] = dia;
                    $scope.nuevaAgenda.semana.numeroDiasSelecionado = $scope.semana.numeroDiasSelecionado - 1;
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
            var data_eps = $http.get('/SiscAgenda/api/medico/agenda/' + $stateParams.idMedico + '/listaEps');
            data_eps.then(function (result) {
                $scope.listEpsMedico = result.data;
            });


            $scope.agregarAgenda = function () {
                /**
                 * Validar datos del formulario.
                 */
                var validacion = true;

                var configServicePost = {
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8;'
                    }
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
                                   
                                    alert('PARECE QUE INSERTO: ');
                                    /**
                                     * Mensaje de confirmacion de agenda insertada correctamente.
                                     */

                                }else{
                                    if (data.codigoRespuesta === "ERROR") {
                                         alert('Eror: '+data.error.mensajeError);
                                       $scope.objErrorNuevaAgenda= data.error;
                                        
                                    }
                                }

                            })
                            .error(function (data, status, header, config) {
                                alert('Error.');
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

                alert('>> ' + s + ' ' + ' ' + e + ' ' + m);

                var events = [{title: 'Feed Me ' + m, start: s + (50000), end: s + (100000), allDay: false, className: ['customFeed']}];
                callback(events);
            };

            $scope.calEventsExt = {
                color: '#f00',
                textColor: 'yellow',
                events: [
                    {type: 'party', title: 'Lunch', start: new Date(y, m, d, 12, 0), end: new Date(y, m, d, 14, 15), allDay: false},
                    {type: 'party', title: 'Lunch 2', start: new Date(y, m, d, 12, 0), end: new Date(y, m, d, 14, 0), allDay: false},
                    {type: 'party', title: 'Click for Google', start: new Date(y, m, 28), end: new Date(y, m, 29), url: 'http://google.com/'}
                ]
            };
            /* alert on eventClick */
            $scope.alertOnEventClick = function (date, jsEvent, view) {
                $scope.alertMessage = (date.title + ' was clicked ');
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
                        right: 'today prev,next'
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
        });

/* EOF */