/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var app = angular.module('sisc_web');

app.controller('pacienteConsultaMedicoEspecializado',
        function ($scope, $http, $state, $compile, $timeout) {
            
            
            var idPacienteSesion = 0;
            var medicoPro = null;

            /*Validacion de objeto personaNatural en localStorage*/
            if (localStorage.getItem('personaNatural') !== null) {
                medicoPro = JSON.parse(localStorage.getItem('personaNatural'));
                console.log('personaNatural localStorage: ');
                console.log(medicoPro);
                if (medicoPro.idPersona === null) {
                    medicoPro = JSON.parse('{"idPersona":23,"tipoIdentificacion":"CC","numeroIdentificacion":151515154,"version":0,"correoElectronico":"paciente@prueba.com","nombres":"jhvj","apellidos":"Rojas Rojas","genero":"M","fechaNacimiento":-2242062000000,"telefonoCelular":71717171717,"telefonoFijo":61616161,"direccion":"kkks","fotografia":null,"huella":null,"rh":"+","grupoSanguineo":"A","tarjetaProfesional":null,"rolPersonaNatural":"PACIENTE"}');
                }               
            }else{
               medicoPro = JSON.parse('{"idPersona":23,"tipoIdentificacion":"CC","numeroIdentificacion":151515154,"version":0,"correoElectronico":"paciente@prueba.com","nombres":"jhvj","apellidos":"Rojas Rojas","genero":"M","fechaNacimiento":-2242062000000,"telefonoCelular":71717171717,"telefonoFijo":61616161,"direccion":"kkks","fotografia":null,"huella":null,"rh":"+","grupoSanguineo":"A","tarjetaProfesional":null,"rolPersonaNatural":"PACIENTE"}'); 
            }
             idPacienteSesion = medicoPro.idPersona;
            console.log('idPacienteSesion: ' + idPacienteSesion);
            
            
            $scope.fechaAc=new Date().getTime();
               var configServicePost = {
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8;'
                    }
                };
                
            
            $scope.generalResponse=null;
             $scope.agendarCitaPaciente = function (cita){
               console.log(cita.idCita);
                                console.log('rest.. '+'/SiscAgenda/api/paciente/agendar/cita/'+cita.idCita+'/paciente/'+idPacienteSesion)
                             $http.post('/SiscAgenda/api/paciente/agendar/cita/'+cita.idCita+'/paciente/'+idPacienteSesion,null, configServicePost)
                            .success(function (data, status, headers, config) {
                                 if (data.codigoRespuesta === "SUCCESS") {                                     
                                     cita.estadoCita='APARTADA';
                                     $('#vistaHorariosMedicos').modal('hide');
                                     $('#message-box-success').modal();
                                 }else{
                                       $('#vistaHorariosMedicos').modal('hide');
                                    $('#message-box-sound-3').modal();
                                     $scope.generalResponse=data.error.mensajeError;
                                 }
                            })
                            .error(function (data, status, header, config) {

                            });
             };
            
             
            
            $scope.especialidadSelected = "";
            $scope.fechaSeleccionada="";
            $scope.listaespecialidadesEPS = [];
            $scope.listamedicosEspecialidadEPS = [];
            $scope.medicosDisponible1=null;

            
            /**
             *  Traer la lista especialidades que hay en la eps
             */
            var especialidadesEPS = $http.get('/SiscAgenda/api/paciente/especialidadesMedicosEPS');
            especialidadesEPS.then(function (result) {
                $scope.listaespecialidadesEPS = result.data;
            });
            ////////////////////////////////////////////////////////////////////
            //http://localhost:8080/SiscAgenda/api/paciente/especialidadesMedicosEPS
            
            $scope.mostrarHoriosDisponible =function (medicoDisponible){
                $scope.medicosDisponible1=medicoDisponible;
                $('#vistaHorariosMedicos').modal();
            };
            

            $scope.buscarEspecialidades = function () {
                
                
               
                ////////////////////////////////////////////////////////////////////
                /**
                 *  Traer la lista de medicos segun la especicalidad seleccionada
                 *  http://localhost:8080/sisc_web/pages-address-book.html#
                 */
                var urlServiceRest="";
                
                if($scope.especialidadSelected!=""&&$scope.fechaSeleccionada!=""){
                    urlServiceRest="/SiscAgenda/api/paciente/citasDisponibles?idEspecialidad="+$scope.especialidadSelected+
                            "&idPaciente="+idPacienteSesion+"&fechaBusqueda="+$scope.fechaSeleccionada;
                }else{
                    urlServiceRest="/SiscAgenda/api/paciente/citasDisponibles?idEspecialidad="+$scope.especialidadSelected+
                            "&idPaciente="+idPacienteSesion;
                }
                
                console.log(urlServiceRest);
               
                var medicosEspecialidadEPS = $http.get(urlServiceRest);
                medicosEspecialidadEPS.then(function (result) {
                             $scope.listamedicosEspecialidadEPS = result.data;
                });
                
            };


           




        });
//fin <pacienteAgendaCitaController>




/******************************************************************************
 * inicial plugin de select buscador
 */
// init jquery functions and plugins
//$(document).ready(function () {
//    $.getScript('//cdnjs.cloudflare.com/ajax/libs/select2/3.4.8/select2.min.js', function () {
//        
//        $("#mySel").select2({
//        });
//        
//    }); //script select2.min.js
//});