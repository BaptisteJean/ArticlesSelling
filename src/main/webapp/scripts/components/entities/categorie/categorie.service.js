'use strict';

angular.module('articleSellingApp')
    .factory('Categorie', function ($resource, DateUtils) {
        return $resource('api/categories/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
