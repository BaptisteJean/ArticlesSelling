'use strict';

angular.module('articleSellingApp').controller('DetailController',
		['$state','$scope', '$http', '$stateParams','Ads',
	        function($state, $scope, $http, $stateParams,Ads) {

            $scope.images = [];

            $scope.load = function(){
                Ads.get({id : $stateParams.id}, function(result) {
                    $scope.ads = result;
                });
                 $http.get("/api/allimagesads/"+$stateParams.id).success(function(response){
                      $scope.images = response;
                  }).error(function(reason){
                      console.log(reason);
                  });
                };
            $scope.load();

            $scope.numbreVue=function(){
	    		$http.put("/api/nbreVue/ads/"+$stateParams.id).success(function(response){
	    		}).error(function(reason){
	    			console.log(reason);
	    		});

	    	   };
	        $scope.numbreVue();

        $('.bxslider').bxSlider({
            pagerCustom: '#bx-pager'
        });



	}]);
