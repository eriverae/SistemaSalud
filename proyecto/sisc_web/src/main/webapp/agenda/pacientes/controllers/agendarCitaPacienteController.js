/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var app = angular.module('sisc_web');

app.controller('agendarCitaPacienteController',
        function ($scope, $http, $stateParams, $state, $compile, $timeout, uiCalendarConfig) {
            
            
            ////////////////////////////////////////////////////////////////////
            $scope.listaespecialidadesEPS = [];
            /**
             *  Traer la lista especialidades de medicos eps
             */
            var especialidadesEPS = $http.get('/SiscAgenda/api/paciente/especialidadesMedicosEPS');
            especialidadesEPS.then(function (result) {
                $scope.listaespecialidadesEPS = result.data;
            });
            ////////////////////////////////////////////////////////////////////
            
            
            


            
            

/*******************************************************************************
 * inicial pluggin de calendar
 */
            $scope.changeTo = 'Hungarian';
            /* event source that pulls from google.com */
            $scope.eventSource = {
                url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
                className: 'gcal-event', // an option!
                currentTimezone: 'America/Chicago' // an option!
            };
            
            
//            /* arreglo con las agendas a programar */
//            $scope.events = [
////      {title: 'All Day Event',start: new Date(y, m, 1)},
//
//            ];
            /* event source that calls a function on every view switch */
            $scope.eventsF = function (start, end, timezone, callback) {
                var s = new Date(start).getTime() / 1000;
                var e = new Date(end).getTime() / 1000;
                var m = new Date(start).getMonth();


                var events = [{title: 'Feed Me ' + m, start: s + (50000), end: s + (100000), allDay: false, className: ['customFeed']}];
                callback(events);
            };
//
//            var utilRest = $http.get('/SiscAgenda/api/medico/agenda/' + $stateParams.idMedico);
//
//            utilRest.then(function (result) {
//
//                var obj = result.data;
//                if (obj.existeAgenda) {
//                    $.each(obj.events, function (k, v) {
//                        $scope.calEventsExt.events.push(v);
//                    });
//                }
//
//            });
//
//
//
            $scope.calEventsExt = {
                textColor: '#f1111',
                events: []
            };
//
//
//            /* alert on eventClick */
//            $scope.alertOnEventClick = function (date, jsEvent, view) {
//
//
//                var utilRest = $http.get('/SiscAgenda/api/paciente/' + date.idCita + "/consultarCita");
//                utilRest.then(function (result) {
//                    var obj = result.data;
//                    alert('ID CITA: DESDE REST:  ' + obj.idCita);
//                    $scope.infoConsultaCita = result.data;
//                    $('#myModal').modal();
//                    $('#tab222').show();
//
//
//                });
//
//            };
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
//fin <pacienteAgendaCitaController>






/******************************************************************************
 * inicial plugin de select buscador
 */            
// init jquery functions and plugins
$(document).ready(function(){

  $.getScript('//cdnjs.cloudflare.com/ajax/libs/select2/3.4.8/select2.min.js',function(){
  $.getScript('//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js',function(){
  $.getScript('//cdnjs.cloudflare.com/ajax/libs/moment.js/2.6.0/moment.min.js',function(){
  $.getScript('//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/js/bootstrap-datetimepicker.min.js',function(){
  $.getScript('//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js',function(){
  
    $("#mySel").select2({
    	
    });
    
    $("#mySel2").select2({
    	closeOnSelect:false
    });
    
    $('#datepicker').datepicker({
      autoclose:true,
    }).on("changeDate", function(e){
      console.log(e.date);
    });
    
    $('.input-daterange').datepicker({
      autoclose:true
    }).on("changeDate", function(e){
      console.log(e.date);
    });
    
    $('#timepicker').datetimepicker({
      pickDate: false
    });
    
    $("input").bind('keyup change',function() {
		var $t = $(this);
        var $par = $t.parent();
        var min = $t.attr("data-valid-min");
        var match = $t.attr("data-valid-match");
      	var pattern = $t.attr("pattern");
               
        if (typeof match!="undefined"){
            if ($t.val()!=$('#'+match).val()) {
                $par.removeClass('has-success').addClass('has-error');
            }
            else {
                $par.removeClass('has-error').addClass('has-success');
            }
        }
      	else if (!this.checkValidity()) {
      		$par.removeClass('has-success').addClass('has-error');
        }
        else {
            $par.removeClass('has-error').addClass('has-success');
        }
      	
      	if ($par.hasClass("has-success")) {
        	$par.find('.form-control-feedback').removeClass('fade');
      	}
 		else {
        	$par.find('.form-control-feedback').addClass('fade');
      	}
      
	});
    
  });//script
  });//script
  });//script
  });//script
  });//script
  
  });