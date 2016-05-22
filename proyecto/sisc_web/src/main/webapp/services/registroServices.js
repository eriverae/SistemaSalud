'use strict';

var myModule = angular.module('sisc_web');
// Service that provides persons operations
myModule.factory('personaService', function ($resource) {
    return $resource('/sisc_registro/api/personaNatural/:id',{},
    {
        getByNumber: $resource({
            method: 'GET',
            url: '/sisc_registro/api/personaNatural/getByNumber/:numberId',
            params:{numberId:'@numberID'}
        }),
        beneficiarios: $resource({
            method: 'POST',
            url: '/sisc_registro/api/personaNatural/beneficiarios/'
        }),
        asociarBeneficiario: $resource({
            method: 'POST',
            url: '/sisc_registro/api/personaNatural/asociarBeneficiario/'
        }),
        personasPorRol: $resource({
            method: 'GET',
            url: '/sisc_registro/api/personaNatural/personasPorRol/:page/:sortFields/:sortDirections/:rol',
            params:{page:'page', sortFields:'sortFields', sortDirections:'sortDirections', rol:'rol'}
        })
    });
});

myModule.factory('epsService', function ($resource) {
    return $resource('/sisc_registro/api/personaJuridica/:id');
});