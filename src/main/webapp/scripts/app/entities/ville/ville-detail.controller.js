'use strict';

angular.module('articleSellingApp')
    .controller('VilleDetailController', function ($scope, $rootScope, $stateParams, entity, Ville, Pays) {
        $scope.ville = entity;
        $scope.load = function (id) {
            Ville.get({id: id}, function(result) {
                $scope.ville = result;
            });
        };
        var unsubscribe = $rootScope.$on('articleSellingApp:villeUpdate', function(event, result) {
            $scope.ville = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
