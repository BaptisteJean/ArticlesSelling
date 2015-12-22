 'use strict';

angular.module('articleSellingApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-articleSellingApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-articleSellingApp-params')});
                }
                return response;
            }
        };
    });
