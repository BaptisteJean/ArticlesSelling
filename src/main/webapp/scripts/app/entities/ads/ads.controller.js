'use strict';

angular.module('articleSellingApp')
    .controller('AdsController', function ($http,$scope, Ads, ParseLinks) {
        $scope.adss = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Ads.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.adss = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Ads.get({id: id}, function(result) {
                $scope.ads = result;
                $('#deleteAdsConfirmation').modal('show');
            });
        };

        $scope.blockerOrDeblocker = function(index){
            $http.put('api/ads/blockedOrDeblocked/' + $scope.adss[index].id)
                .success(function(response){
                    $scope.adss[index].blocked = response.blocked;
                    $scope.loadAll();
                })
                .error(function(reason){
                    console.log(reason);
                });
        };

        $scope.confirmDelete = function (id) {
            Ads.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteAdsConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.ads = {
                nameAds: null,
                identif: null,
                dateAjout: null,
                pays: null,
                ville: null,
                price: null,
                etat: null,
                description: null,
                nbreImage: null,
                nbreVue: null,
                mainImage: null,
                negociable: null,
                id: null
            };
        };
    });
