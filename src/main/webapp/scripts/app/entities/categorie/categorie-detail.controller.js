'use strict';

angular.module('articleSellingApp')
    .controller('CategorieDetailController', function ($scope, $rootScope, $stateParams, entity, Categorie, Ads) {
        $scope.categorie = entity;
        $scope.load = function (id) {
            Categorie.get({id: id}, function(result) {
                $scope.categorie = result;
            });
        };
        var unsubscribe = $rootScope.$on('articleSellingApp:categorieUpdate', function(event, result) {
            $scope.categorie = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
