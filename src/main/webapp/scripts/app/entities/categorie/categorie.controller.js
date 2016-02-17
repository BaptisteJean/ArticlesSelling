'use strict';

angular.module('articleSellingApp')
    .controller('CategorieController', function ($http,$stateParams,$scope, Categorie, ParseLinks) {
        $scope.categories = [];
        $scope.category = [];
        $scope.allAds = [];
        $scope.page = 0;
        //$scope.itemsPerPage = 5;
        //$scope.currentPage = 0;
        //$scope.items = [];

    //    for (var i=0; i<50; i++) {
    //        $scope.items.push({
    //           items
    //        });
    //    }
    //
    //    $scope.prevPage = function() {
    //        if ($scope.currentPage > 0) {
    //            $scope.currentPage--;
    //        }
    //    };
    //
    //    $scope.prevPageDisabled = function() {
    //        return $scope.currentPage === 0 ? "disabled" : "";
    //    };
    //
    //    $scope.pageCount = function() {
    //        return Math.ceil($scope.items.length/$scope.itemsPerPage)-1;
    //    };
    //
    //    $scope.nextPage = function() {
    //        if ($scope.currentPage < $scope.pageCount()) {
    //            $scope.currentPage++;
    //        }
    //    };
    //
    //    $scope.nextPageDisabled = function() {
    //        return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
    //    };
    //
    //});

        $scope.loadAll = function() {
            Categorie.query({page: $scope.page, size: 10}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.categories = result;
                $scope.category = result;
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
