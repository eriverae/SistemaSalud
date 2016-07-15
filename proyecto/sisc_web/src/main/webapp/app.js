'use strict';
// Declare app level module which depends on views, and components
var app = angular.module('sisc_web', ['ui.router', 'ngResource', 'ui.calendar', 'ui.bootstrap', 'ngGrid', 'app.utils', 'ngRoute', 'angular-storage', 'datatables']);

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
                    templateUrl: 'template.html',
                    controller: function ($scope) {
                        $scope.title = 'SISC WEB';
                    },
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.Medico, USER_ROLES.Paciente, USER_ROLES.EPS, USER_ROLES.Auditor]}
                })

                .state('home.#', {
                    views: {
                        "@": {
                            url: '/home',
                            templateUrl: 'template.html',
                            controller: function ($scope) {
                                $scope.title = 'SISC WEB';
                            }
                        }
                    },
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.Medico, USER_ROLES.Paciente, USER_ROLES.EPS, USER_ROLES.Auditor]}
                })

                .state('home.accesos', {
                    views: {
                        'contenidoOculto': {
                            url: '/accesos',
                            templateUrl: 'seguridad/accesos/accesos.html',
                            controller: 'accesosListController'
                        }
                    },
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.crearAcceso', {
                    views: {
                        'contenidoOculto': {
                            url: '/crearAcceso',
                            templateUrl: 'seguridad/accesos/formularioAcceso.html',
                            controller: 'accesoFormController'
                        }
                    },
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.modificarAcceso', {
                    views: {
                        'contenidoOculto': {
                            url: '/modificarAcceso',
                            templateUrl: 'seguridad/accesos/formularioAcceso.html',
                            controller: 'accesoFormController'

                        }
                    },
                    params: {'acceAcce': null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.grupos', {
                    views: {
                        'contenidoOculto': {
                            url: '/grupos',
                            templateUrl: 'seguridad/grupos/grupos.html',
                            controller: 'gruposListController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.crearGrupo', {
                    views: {
                        'contenidoOculto': {
                            url: '/crearGrupo',
                            templateUrl: 'seguridad/grupos/formularioGrupo.html',
                            controller: 'grupoFormController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.modificarGrupo', {
                    views: {
                        'contenidoOculto': {
                            url: '/modificarGrupo',
                            templateUrl: 'seguridad/grupos/formularioGrupo.html',
                            controller: 'grupoFormController'
                        }},
                    params: {'grupGrup': null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.usuarios', {
                    views: {
                        'contenidoOculto': {
                            url: '/usuarios',
                            templateUrl: 'seguridad/usuarios/usuarios.html',
                            controller: 'usuariosListController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.crearUsuario', {
                    views: {
                        'contenidoOculto': {
                            url: '/crearUsuario',
                            templateUrl: 'seguridad/usuarios/formularioUsuario.html',
                            controller: 'usuarioFormController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.modificarUsuario', {
                    views: {
                        'contenidoOculto': {
                            url: '/modificarUsuario',
                            templateUrl: 'seguridad/usuarios/formularioUsuario.html',
                            controller: 'usuarioFormController'
                        }},
                    params: {'usuaUsua': null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.cambiarContrasena', {
                    views: {
                        'contenidoOculto': {
                            url: '/cambiarContrasena',
                            templateUrl: 'seguridad/usuarios/formularioContrasena_Admin.html',
                            controller: 'contrasenaFormController'
                        }},
                    params: {'usuaUsua': null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.cambiarContrasenaUsuario', {
                    views: {
                        'contenidoOculto': {
                            url: '/cambiarContrasenaUsuario',
                            templateUrl: 'seguridad/usuarios/formularioContrasena.html',
                            controller: 'contrasenaFormController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.Medico, USER_ROLES.Paciente, USER_ROLES.EPS, USER_ROLES.Auditor]}
                })

                .state('home.agenda', {
                    views: {
                        'contenidoOculto': {
                            url: '/medico/agenda',
                            templateUrl: 'agenda/medicos/agendaMedico.html',
                            controller: 'agendaMedicoContoller'
//                    params: {'idMedico': '9'},

//                    roles: {authorizedRoles: [USER_ROLES.Administrador]}

                        }}
                    ,roles: {authorizedRoles: [USER_ROLES.Medico]}


                })

                .state('home.citasPaciente', {
                    views: {
                        'contenidoOculto': {
                            url: '/paciente/lcitas',
                            templateUrl: 'agenda/pacientes/consultarCitas.html',
                            controller: 'citasController'
                        }}
//                    , roles: {authorizedRoles: [USER_ROLES.Paciente]}
                })

                .state('home.citasHistorialPaciente', {
                    views: {
                        'contenidoOculto': {
                            url: '/paciente/historialCitas',
                            templateUrl: 'agenda/pacientes/historialCitas.html',
                            controller: 'historialCitasController'
//                    params : {'idPaciente':'23'},
                        }},
                    roles: {authorizedRoles: [USER_ROLES.Paciente]}

                })
                .state('home.historialPacienteEPS', {
                    views: {
                        'contenidoOculto': {
                            url: '/paciente/historialPacienteEPS',
                            templateUrl: 'agenda/pacientes/historialPacienteEPS.html',
                            controller: 'citasController'
                        }}
//                    , roles: {authorizedRoles: [USER_ROLES.Paciente]}
                })
                .state('home.pacienteConsultaMedicoEspecializado', {
                    views: {
                        'contenidoOculto': {
                            url: '/paciente/ConsultaMedicoEspecializado',
                            templateUrl: 'agenda/pacientes/pacienteConsultaMedicoEspecializado.html',
                            controller: 'pacienteConsultaMedicoEspecializado'
//                    params: {paciente: {
//                            idPersona: "23",
//                            idEps: "3",
//                        }},
                        }}
//                    ,roles: {authorizedRoles: [USER_ROLES.Paciente]}
                })
                .state('home.agendarCalendarioDelMedico', {
                    views: {
                        'contenidoOculto': {
                            url: '/paciente/agendarCalendarioDelMedico',
                            templateUrl: 'agenda/pacientes/agendarCalendarioDelMedico.html',
                            controller: 'agendarCalendarioDelMedico'}},
                    roles: {authorizedRoles: [USER_ROLES.Paciente]}
                })




                // Registro    
                .state('home.registroPersonaNatural', {
                    views: {
                        'contenidoOculto': {
                            url: '/personaNatural',
                            templateUrl: 'registro/registroPersonaNatural.html',
                            controller: 'personaNaturalController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.modificarPersonaNatural', {
                    views: {
                        'contenidoOculto': {
                            url: '/mPersonaNatural',
                            templateUrl: 'registro/registroPersonaNatural.html',
                            controller: 'personaNaturalController'
                        }},
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.registroMedicos', {
                    views: {
                        'contenidoOculto': {
                            url: '/medicos',
                            templateUrl: 'registro/medicos/registroMedicos.html',
                            controller: 'medicosController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.listarMedicos', {
                    views: {
                        'contenidoOculto': {
                            url: '/listaMedicos',
                            templateUrl: 'registro/medicos/listaMedicos.html',
                            controller: 'listaMedicosController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.modificarMedicos', {
                    views: {
                        'contenidoOculto': {
                            url: '/modificarMedicos',
                            templateUrl: 'registro/medicos/registroMedicos.html',
                            controller: 'medicosController'
                        }},
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.registroPacientes', {
                    views: {
                        'contenidoOculto': {
                            url: '/pacientes',
                            templateUrl: 'registro/pacientes/registroPacientes.html',
                            controller: 'pacientesController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.listarPacientes', {
                    views: {
                        'contenidoOculto': {
                            url: '/listaPacientes',
                            templateUrl: 'registro/pacientes/listaPacientes.html',
                            controller: 'listaPacientesController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.modificarPacientes', {
                    views: {
                        'contenidoOculto': {
                            url: '/modificarPacientes',
                            templateUrl: 'registro/pacientes/registroPacientes.html',
                            controller: 'pacientesController'
                        }},
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.registroBeneficiarios', {
                    views: {
                        'contenidoOculto': {
                            url: '/beneficiario',
                            templateUrl: 'registro/beneficiario/registroBeneficiarios.html',
                            controller: 'beneficiariosController'
                        }},
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador, USER_ROLES.EPS]}
                })

                .state('home.registroEps', {
                    views: {
                        'contenidoOculto': {
                            url: '/RegistroEPS',
                            templateUrl: 'registro/eps/registroEps.html',
                            controller: 'epsController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.listarEps', {
                    views: {
                        'contenidoOculto': {
                            url: '/listaEPS',
                            templateUrl: 'registro/eps/listaEps.html',
                            controller: 'listaEpsController'}},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })

                .state('home.modificarEps', {
                    views: {
                        'contenidoOculto': {
                            url: '/ModificarEPS',
                            templateUrl: 'registro/eps/registroEps.html',
                            controller: 'epsController'
                        }},
                    params: {idPersona: null},
                    roles: {authorizedRoles: [USER_ROLES.Administrador]}
                })


                // hc
                .state('home.diagnostico', {
                    views: {
                        'contenidoOculto': {
                            url: '/historia/diagnostico',
                            templateUrl: 'historia/diagnostico.html',
                            controller: 'diagnosticoController'}},
                    roles: {authorizedRoles: [USER_ROLES.Medico]}
                })
                .state('home.menuhc', {
                    views: {
                        'contenidoOculto': {
                            url: '/menuhc',
                            templateUrl: 'historia/menuhc.html',
                            controller: 'menuController'
                        }},
                    params: {cita: null},
                    roles: {authorizedRoles: [USER_ROLES.Medico, USER_ROLES.Paciente]}

                })
                .state('home.historia', {
                    views: {
                        'contenidoOculto': {
                            url: '/historia/historiaclinica',
                            templateUrl: 'historia/historiaclinica.html',
                            controller: 'historiaController'
                        }},
                    roles: {authorizedRoles: [USER_ROLES.Medico, USER_ROLES.Paciente]}
                })
                .state('home.asignarmedicamento', {
                    views: {
                        'contenidoOculto': {
                            url: '/historia/asignar-medicamento',
                            templateUrl: 'historia/asignarmedicamento.html',
                            controller: 'medicamentoController'}},
                    roles: {authorizedRoles: [USER_ROLES.Medico]}
                })
                .state('home.asignarTratamiento', {
                    views: {
                        'contenidoOculto': {
                            url: '/historia/asignar-tratamiento',
                            templateUrl: 'historia/asignarTratamiento.html',
                            controller: 'tratamientoController'}},
                    roles: {authorizedRoles: [USER_ROLES.Medico]}
                })

                .state('home.asignarincapacidad', {
                    views: {
                        'contenidoOculto': {
                            url: '/historia/asignar-incapacidad',
                            templateUrl: 'historia/asignarincapacidad.html',
                            controller: 'incapacidadController'}},
                    roles: {authorizedRoles: [USER_ROLES.Medico]}
                })

                .state('home.asignarexamen', {
                    views: {
                        'contenidoOculto': {
                            url: '/historia/asignar-examen',
                            templateUrl: 'historia/asignarexamen.html',
                            controller: 'examenController'}},
                    roles: {authorizedRoles: [USER_ROLES.Medico]}
                })

                .state('home.asignarCirugia', {
                    views: {
                        'contenidoOculto': {
                            url: '/historia/asignar-cirugia',
                            templateUrl: 'historia/asignarCirugia.html',
                            controller: 'cirugiaController'}},
                    roles: {authorizedRoles: [USER_ROLES.Medico]}
                })

                ;

    }]);

app.controller('loginController', function ($scope, store) {

    $scope.nombrePersona = store.get('personaNatural').nombres;
    $scope.apellidoPersona = store.get('personaNatural').apellidos;
    $scope.tipoDocPersona = store.get('personaNatural').tipoIdentificacion;
    $scope.docPersona = store.get('personaNatural').numeroIdentificacion;
    $scope.correoPersona = store.get('personaNatural').correoElectronico;

    $.getJSON("https://api.ipify.org?format=jsonp&callback=?", function (json) {
        store.set('ip', json.ip);
    }
    );

    $scope.ipPersona = store.get('ip');


    //{"idPersona":1,"tipoIdentificacion":"CC","numeroIdentificacion":9999999,"version":0,"correoElectronico":"admin@admin.com","nombres":"Admin","apellidos":"admin","genero":"M","fechaNacimiento":315550800000,"telefonoCelular":555555555555,"telefonoFijo":444444444444,"direccion":"Direccion de mi casa","fotografia":null,"huella":null,"rh":"+","grupoSanguineo":"A","tarjetaProfesional":null,"rolPersonaNatural":"PACIENTE"}
    $scope.mostrarMenu = function (value) {
        for (var i = 0; i < store.get('listaGrupos').length; i++) {
            if (value instanceof Array) {
                for (var j = 0; j < value.length; j++) {
                    if (value[j] === store.get('listaGrupos')[i]) {
                        return true;
                    }
                }
            } else {
                if (value === store.get('listaGrupos')[i]) {
                    return true;
                }
            }
        }
        return false;
    };
});

app.run(function ($rootScope, $state, $http, AUTH_EVENTS, AuthService) {

    $rootScope.$on('$stateChangeStart', function (event, next) {

        if (next.name !== 'login') {
            var authorizedRoles = next.roles.authorizedRoles;

            if (!AuthService.isAuthorized(authorizedRoles)) {

                event.preventDefault();

                if (AuthService.isAuthenticated()) {
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                    $state.go('home');
                } else {
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
