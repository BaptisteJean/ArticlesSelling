'use strict';

angular.module('articleSellingApp')
    .controller('VilleController', function ($scope, Ville, ParseLinks) {
        $scope.villes = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Ville.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.villes = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Ville.get({id: id}, function(result) {
                $scope.ville = result;
                $('#deleteVilleConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Ville.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteVilleConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.ville = {
                nameVille: null,
                id: null
            };
        };
    });
