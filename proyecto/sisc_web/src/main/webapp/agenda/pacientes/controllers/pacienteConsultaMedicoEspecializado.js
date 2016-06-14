/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var app = angular.module('sisc_web');

app.controller('pacienteConsultaMedicoEspecializado',
        function ($scope, $http, $stateParams, $state, $compile, $timeout) {

           
            
            alert('>> '+$stateParams.paciente.idPersona);
            alert('>> '+$stateParams.paciente.idEps);
            
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
                            "&idEps="+$stateParams.paciente.idEps+"&fechaBusqueda="+$scope.fechaSeleccionada;
                }else{
                    urlServiceRest="/SiscAgenda/api/paciente/citasDisponibles?idEspecialidad="+$scope.especialidadSelected+
                            "&idEps="+$stateParams.paciente.idEps;
                }
                
               
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