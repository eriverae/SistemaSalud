/**
 * calendarDemoApp - 0.9.0
 */
var app = angular.module('siscseguridad');

app.controller('agendaMedicoContoller',
        function ($scope, $compile, $timeout, uiCalendarConfig, $http) {

            $scope.listaDiasApor = [
                
            ];
            $scope.listaDias = [
                {   numeroDia: '1',
                    dia: 'Lunes',
                    incluir:false
                },
                {   numeroDia: '2',
                    dia: 'Martes',
                    incluir:false
                },
                {   numeroDia: '3',
                    dia: 'Miercoles',
                    incluir:false
                },
                {   numeroDia: '4',
                    dia: 'Jueves',
                    incluir:false
                },
                {   numeroDia: '5',
                    dia: 'Viernes',
                    incluir:false
                },
                {   numeroDia: '6',
                    dia: 'Sabado',
                    incluir:false                    
                },
                {   numeroDia: '7',
                    dia: 'Domingo',
                    incluir:false
                }
            ];
            /* add custom event*/
            $scope.colocarDiasAgenda = function (dia, choice,index) {
                alert('>> ' + dia.numeroDia + ' ' + choice.checked+ index);
                if (choice.checked) {
                   
                    alert($scope.listaDiasApor[index]);
//                    $scope.listaDiasApor.objects[index] = dia;
                }else{
                  
                    $scope.listaDiasApor.objects[index] = dia;
                }
            };




            var date = new Date();
//    alert('coloue algo');
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();

            $scope.listEpsMedico = {};

            var data_eps = $http.get('http://localhost:8080/SiscAgenda/api/medico/agenda/listaEps?idMedico=4');

            data_eps.then(function (result) {
               $scope.listEpsMedico = result.data;

            });
//    


//      agendaService.consultarListaEps({oper: "listaEps",idMedico:'4'}).$promise.then(
//      function (result) {
//         console.log(result);
//        $scope.listEpsMedico = result;
//      },
//      function () {
//        // Broadcast the event for a server error.
//        
//      });


//     agendaService.consultarListaEps({idMedico: "4"}).$promise.then(
//      function (data) {
//         alert('>> '+data);
//        $scope.listEpsMedico = data;
//        
//        //A partir de Angular 1.3, ng-model requiere un objeto de tipo Date valido, no acepta un String
//       
//      },
//      function () {
//        // Broadcast the event for a server error.
//        alert('nada..');
//      });
//    
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
