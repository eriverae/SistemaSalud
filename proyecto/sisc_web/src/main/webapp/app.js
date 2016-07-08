'use strict';
// Declare app level module which depends on views, and components
var app = angular.module('sisc_web', ['ui.router', 'ngResource', 'ui.calendar', 'ui.bootstrap', 'ngGrid', 'app.utils', 'ngRoute', 'angular-storage']);

app.config(['$stateProvider', '$urlRouterProvider', 'USER_ROLES', function ($stateProvider, $urlRouterProvider, USER_ROLES) {

        $urlRouterProvider.otherwise("/login");

        $stateProvider
                .state('login', {
                    url: '/login',
                    templateUrl: 'login.html',
                    controller: 'loginFormController'
                })

                .state('home', {
                    url: '/home',
                    templateUrl: 'home.html',
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.Medico, USER_ROLES.Paciente, USER_ROLES.EPS, USER_ROLES.Auditor]}
                })

                .state('accesos', {
                    url: '/accesos',
                    templateUrl: 'seguridad/accesos/accesos.html',
                    controller: 'accesosListController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('crearAcceso', {
                    url: '/crearAcceso',
                    templateUrl: 'seguridad/accesos/formularioAcceso.html',
                    controller: 'accesoFormController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('modificarAcceso', {
                    url: '/modificarAcceso',
                    templateUrl: 'seguridad/accesos/formularioAcceso.html',
                    controller: 'accesoFormController',
                    params: {'acceAcce': null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('grupos', {
                    url: '/grupos',
                    templateUrl: 'seguridad/grupos/grupos.html',
                    controller: 'gruposListController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('crearGrupo', {
                    url: '/crearGrupo',
                    templateUrl: 'seguridad/grupos/formularioGrupo.html',
                    controller: 'grupoFormController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('modificarGrupo', {
                    url: '/modificarGrupo',
                    templateUrl: 'seguridad/grupos/formularioGrupo.html',
                    controller: 'grupoFormController',
                    params: {'grupGrup': null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('usuarios', {
                    url: '/usuarios',
                    templateUrl: 'seguridad/usuarios/usuarios.html',
                    controller: 'usuariosListController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('crearUsuario', {
                    url: '/crearUsuario',
                    templateUrl: 'seguridad/usuarios/formularioUsuario.html',
                    controller: 'usuarioFormController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                
                .state('modificarUsuario', {
                    url: '/modificarUsuario',
                    templateUrl: 'seguridad/usuarios/formularioUsuario.html',
                    controller: 'usuarioFormController',
                    params: {'usuaUsua': null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                
                .state('cambiarContrasena', {
                    url: '/cambiarContrasena',
                    templateUrl: 'seguridad/usuarios/formularioContrasena_Admin.html',
                    controller: 'contrasenaFormController',
                    params: {'usuaUsua': null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                
                .state('cambiarContrasenaUsuario', {
                    url: '/cambiarContrasenaUsuario',
                    templateUrl: 'seguridad/usuarios/formularioContrasena.html',
                    controller: 'contrasenaFormController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.Medico, USER_ROLES.Paciente, USER_ROLES.EPS, USER_ROLES.Auditor]}
                })

                .state('agenda', {
                    url: '/medico/agenda',
                    templateUrl: 'agenda/medicos/agendaMedico.html',
                    controller: 'agendaMedicoContoller',
                    params: {'idMedico': '9'},
//                    roles: {authorizedRoles: [USER_ROLES.Administrador]}

                })
                .state('citasPaciente', {
                    url: '/paciente/lcitas',
                    templateUrl: 'agenda/pacientes/consultarCitas.html',
                    controller: 'citasController',
                    //params : {'idPaciente':'2'}
                    params: {'idPaciente': '23'},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                .state('citasHistorialPaciente', {
                    url: '/paciente/historialCitas',
                    templateUrl: 'agenda/pacientes/historialCitas.html',
                    controller: 'historialCitasController',
                    params : {'idPaciente':'23'},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                    
                })
                .state('historialPacienteEPS', {
                    url: '/paciente/historialPacienteEPS',
                    templateUrl: 'agenda/pacientes/historialPacienteEPS.html',
                    controller: 'citasController',
                    params: {'idPaciente': '23'},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                .state('pacienteConsultaMedicoEspecializado', {
                    url: '/paciente/ConsultaMedicoEspecializado',
                    templateUrl: 'agenda/pacientes/pacienteConsultaMedicoEspecializado.html',
                    controller: 'pacienteConsultaMedicoEspecializado',
                    params: {paciente: {
                            idPersona: "23",
                            idEps: "3",
                        }},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                .state('agendarCalendarioDelMedico', {
                    url: '/paciente/agendarCalendarioDelMedico',
                    templateUrl: 'agenda/pacientes/agendarCalendarioDelMedico.html',
                    controller: 'agendarCalendarioDelMedico',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })




                // Registro    
                .state('registroPersonaNatural', {
                    url: '/personaNatural',
                    templateUrl: 'registro/registroPersonaNatural.html',
                    controller: 'personaNaturalController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('modificarPersonaNatural', {
                    url: '/mPersonaNatural',
                    templateUrl: 'registro/registroPersonaNatural.html',
                    controller: 'personaNaturalController',
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('registroMedicos', {
                    url: '/medicos',
                    templateUrl: 'registro/medicos/registroMedicos.html',
                    controller: 'medicosController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('listarMedicos', {
                    url: '/listaMedicos',
                    templateUrl: 'registro/medicos/listaMedicos.html',
                    controller: 'listaMedicosController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('modificarMedicos', {
                    url: '/modificarMedicos',
                    templateUrl: 'registro/medicos/registroMedicos.html',
                    controller: 'medicosController',
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('registroPacientes', {
                    url: '/pacientes',
                    templateUrl: 'registro/pacientes/registroPacientes.html',
                    controller: 'pacientesController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('listarPacientes', {
                    url: '/listaPacientes',
                    templateUrl: 'registro/pacientes/listaPacientes.html',
                    controller: 'listaPacientesController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('modificarPacientes', {
                    url: '/modificarPacientes',
                    templateUrl: 'registro/pacientes/registroPacientes.html',
                    controller: 'pacientesController',
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('registroBeneficiarios', {
                    url: '/beneficiario',
                    templateUrl: 'registro/beneficiario/registroBeneficiarios.html',
                    controller: 'beneficiariosController',
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('registroEps', {
                    url: '/RegistroEPS',
                    templateUrl: 'registro/eps/registroEps.html',
                    controller: 'epsController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('listarEps', {
                    url: '/listaEPS',
                    templateUrl: 'registro/eps/listaEps.html',
                    controller: 'listaEpsController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('modificarEps', {
                    url: '/ModificarEPS',
                    templateUrl: 'registro/eps/registroEps.html',
                    controller: 'epsController',
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })


                // hc
                .state('diagnostico', {
                    url: '/historia/diagnostico',
                    templateUrl: 'historia/diagnostico.html',
                    controller: 'diagnosticoController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                .state('menuhc', {
                    url: '/menuhc',
                    templateUrl: 'historia/menuhc.html',
                    controller: 'menuController',
                    params: {cita: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}

                })
                .state('historia', {
                    url: '/historia/historiaclinica',
                    templateUrl: 'historia/historiaclinica.html',
                    controller: 'historiaController'
                    ,
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                .state('asignarmedicamento', {
                    url: '/historia/asignar-medicamento',
                    templateUrl: 'historia/asignarmedicamento.html',
                    controller: 'medicamentoController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })
                .state('asignarTratamiento', {
                    url: '/historia/asignar-tratamiento',
                    templateUrl: 'historia/asignarTratamiento.html',
                    controller: 'tratamientoController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('asignarincapacidad', {
                    url: '/historia/asignar-incapacidad',
                    templateUrl: 'historia/asignarincapacidad.html',
                    controller: 'incapacidadController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('asignarexamen', {
                    url: '/historia/asignar-examen',
                    templateUrl: 'historia/asignarexamen.html',
                    controller: 'examenController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('asignarCirugia', {
                    url: '/historia/asignar-cirugia',
                    templateUrl: 'historia/asignarCirugia.html',
                    controller: 'cirugiaController',
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                ;

    }]);

app.run(function ($rootScope, $state, $http, AUTH_EVENTS, AuthService) {

    $rootScope.$on('$stateChangeStart', function (event, next) {

        if (next.name !== 'login') {
            console.log(next);
            var authorizedRoles = next.roles.authorizedRoles;

            if (!AuthService.isAuthorized(authorizedRoles)) {

                event.preventDefault();

                if (AuthService.isAuthenticated()){
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                    $state.go('home');
                }
                else{
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
                    $state.go('login');
                }
                
                alert('No tiene acceso para esta acci\u00F3n');

            }
        } else if (next.name == 'Administrador' && AuthService.isAdmin()) {
            $state.go('/home');
        }


    });

});