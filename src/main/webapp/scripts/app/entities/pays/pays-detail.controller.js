'use strict';

angular.module('articleSellingApp')
    .controller('PaysDetailController', function ($scope, $rootScope, $stateParams, entity, Pays, Ville) {
        $scope.pays = entity;
        $scope.load = function (id) {
            Pays.get({id: id}, function(result) {
                $scope.pays = result;
            });
        };
        var unsubscribe = $rootScope.$on('articleSellingApp:paysUpdate', function(event, result) {
            $scope.pays = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
