'use strict';

angular.module('articleSellingApp').controller('AccountMyadsController',
	        function($state, $scope, $http, $stateParams) {

	        $scope.myads = [];

	        $scope.loadAll = function() {
                $http.get("/api/myads").success(function(response){
                    $scope.myads = response;
                }).error(function(reason){
                    console.log(reason);
                })
	        };
	        $scope.loadAll();


                $scope.delete = function (myadsId) {
                        $http.post('/api/delete/'+myadsId).success(function(response){
                            $scope.loadAll();
                        }).error(function(reason){
                            console.log(reason);
                        });
                };
                $scope.vider = function () {
                    for(var i=0;i< $scope.myads.length;i++){
                        $http.post('/api/delete/'+$scope.myads[i].id).success(function(response){
                            console.log("vider Ok");
                        }).error(function(reason){
                            console.log(reason);
                        });
                    }
                    $scope.loadAll();
                };
            });
