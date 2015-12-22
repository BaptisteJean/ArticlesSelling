'use strict';

angular.module('articleSellingApp')
    .factory('Ads', function ($resource, DateUtils) {
        return $resource('api/adss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dateAjout = DateUtils.convertLocaleDateFromServer(data.dateAjout);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.dateAjout = DateUtils.convertLocaleDateToServer(data.dateAjout);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.dateAjout = DateUtils.convertLocaleDateToServer(data.dateAjout);
                    return angular.toJson(data);
                }
            }
        });
    });
