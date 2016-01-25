'use strict';

angular.module('articleSellingApp')
    .controller('CategorieController', function ($scope, Categorie, ParseLinks) {
        $scope.categories = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Categorie.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.categories = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Categorie.get({id: id}, function(result) {
                $scope.categorie = result;
                $('#deleteCategorieConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Categorie.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteCategorieConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.selectRegion = function(){
            $('#selectRegion').modal('show');
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.categorie = {
                nameCategorie: null,
                parent: null,
                description: null,
                nbreAds: null,
                id: null
            };
        };
    });
