'use strict';

angular.module('articleSellingApp')
    .controller('CategorieController', function ($http,$stateParams,$scope, Categorie, Ads, ParseLinks) {
        $scope.categories = [];
        $scope.allAds = [];
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

        $scope.adsByCategories = function(){
            $http.get("/api/getAllAdsByCategories/"+$stateParams.id).success(function(response){
                $scope.allAds = response;
            }).error(function(reason){
                console.log(reason);
            })
        };
        $scope.adsByCategories();



        $scope.loadAlls = function() {
            Ads.query({page: $scope.page, size: 10}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.allAds = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAlls();
        };
        $scope.loadAlls();



        $scope.numbreVue=function(){
    		$http.put("/api/categories/addvue/"+$stateParams.id).success(function(response){
    		}).error(function(reason){
    			console.log(reason);
    		});
    	};
        $scope.numbreVue();

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
            }
        };
        });
