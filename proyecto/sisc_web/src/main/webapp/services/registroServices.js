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
                    method: 'GET',
                    url: '/sisc_registro/api/personaNatural/beneficiarios/'
                },
                asociarBeneficiario: {
                    method: 'POST',
                    url: '/sisc_registro/api/personaNatural/asociarBeneficiario/'
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
                    params: {paciente: '@medico', eps: '@eps'}
                }
            });
});

myModule.factory('epsService', function ($resource) {
    return $resource('/sisc_registro/api/personaJuridica/:id');
});
