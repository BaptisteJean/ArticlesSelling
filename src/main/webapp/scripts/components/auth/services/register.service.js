'use strict';

angular.module('articleSellingApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


