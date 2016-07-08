'use strict';

var myModule = angular.module('sisc_web');
// Service that provides persons operations
myModule.factory('personaService', function ($resource) {
    return $resource('/sisc_registro/api/personaNatural/:id', {},
            {
                getByNumber: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/getByNumber/:numberId',
                    params: {numberId: '@numberID'}
                },
                beneficiarios: {
                    method: 'POST',
                    url: '/sisc_registro/api/personaNatural/beneficiarios',
                    params: {page: '@page', sortFields: '@sortFields', sortDirections: '@sortDirections', cotizante: '@cotizante'}
                },
                asociarBeneficiario: {
                    method: 'POST',
                    url: '/sisc_registro/api/personaNatural/asociarBeneficiario',
                    params: {cotizante: '@cotizante', beneficiario: '@beneficiario', parentezco: '@parentezco'}
                },
                listaEPS: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/listaEPS/',
                    isArray: true
                },
                listaAlergias: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/listaAlergias/',
                    isArray: true
                },
                listaEnfermedades: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/listaEnfermedades/',
                    isArray: true
                },
                listaOperaciones: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/listaOperaciones/',
                    isArray: true
                },
                asociarPacienteEPS: {
                    method: 'POST',
                    url: '/sisc_registro/api/personaNatural/asociarPacienteEPS',
                    params: {paciente: '@paciente', eps: '@eps'}
                },
                getPacienteEPS: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/getPacienteEPS/:paciente',
                    params: {paciente: '@paciente'}
                },
                getByEmail: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/getByEmail/:email',
                    params: {email: '@email'}
                },
                asociarMedicoEPS: {
                    method: 'POST',
                    url: '/sisc_registro/api/personaNatural/asociarMedicoEPS',
                    params: {medico: '@medico', eps: '@eps'}
                },
                getMedicoEPS: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/getMedicoEPS/:medico',
                    params: {medico: '@medico'},
                    isArray: true
                },
                asociarPacienteAlergias: {
                    method: 'POST',
                    url: '/sisc_registro/api/personaNatural/asociarPacienteAlergias',
                    params: {paciente: '@paciente', alergias: '@alergias'}
                },
                asociarPacienteEnfermedades: {
                    method: 'POST',
                    url: '/sisc_registro/api/personaNatural/asociarPacienteEnfermedades',
                    params: {paciente: '@paciente', enfermedades: '@enfermedades'}
                },
                asociarPacienteOperaciones: {
                    method: 'POST',
                    url: '/sisc_registro/api/personaNatural/asociarPacienteOperaciones',
                    params: {paciente: '@paciente', operaciones: '@operaciones'}
                },
                getAlergiasPaciente: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/getAlergiasPaciente/:paciente',
                    params: {paciente: '@paciente'},
                    isArray: true
                },
                getEnfermedadesPaciente: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/getEnfermedadesPaciente/:paciente',
                    params: {paciente: '@paciente'},
                    isArray: true
                },
                getOperacionesPaciente: {
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/getOperacionesPaciente/:paciente',
                    params: {paciente: '@paciente'},
                    isArray: true
                }
            });
});

myModule.factory('epsService', function ($resource) {
    return $resource('/sisc_registro/api/personaJuridica/:id');
});
