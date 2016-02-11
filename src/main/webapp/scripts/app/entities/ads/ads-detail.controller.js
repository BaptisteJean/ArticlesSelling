'use strict';

angular.module('articleSellingApp')
    .controller('AdsDetailController', function ($scope, $rootScope, $stateParams, entity, Ads, Categorie, Image) {
        $scope.ads = entity;
        $scope.load = function (id) {
            Ads.get({id: id}, function(result) {
                $scope.ads = result;
            });
        };
        var unsubscribe = $rootScope.$on('articleSellingApp:adsUpdate', function(event, result) {
            $scope.ads = result;
        });
        $scope.$on('$destroy', unsubscribe);
       
    });
