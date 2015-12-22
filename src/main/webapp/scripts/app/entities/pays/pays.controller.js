'use strict';

angular.module('articleSellingApp')
    .controller('PaysController', function ($scope, Pays, ParseLinks) {
        $scope.payss = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Pays.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.payss = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Pays.get({id: id}, function(result) {
                $scope.pays = result;
                $('#deletePaysConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Pays.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePaysConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.pays = {
                namePays: null,
                id: null
            };
        };
    });
