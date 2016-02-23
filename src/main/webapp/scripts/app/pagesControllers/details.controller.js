'use strict';

angular.module('articleSellingApp').controller('DetailController',
		['$state','$scope', '$http', '$stateParams','Ads',
	        function($state, $scope, $http, $stateParams,Ads) {

            $scope.images = {};
            $scope.toto = "TOTO"

            $scope.load = function(){
                Ads.get({id : $stateParams.id}, function(result) {
                    $scope.ads = result;
                });
                 $http.get("/api/allimagesads/"+$stateParams.id).success(function(response){
                      $scope.images = response;
                      console.log($scope.images.mainImg);
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
        
        $scope.byteSize = function (base64String) {
            if (!angular.isString(base64String)) {
                return '';
            }
            function endsWith(suffix, str) {
                return str.indexOf(suffix, str.length - suffix.length) !== -1;
            }
            function paddingSize(base64String) {
                if (endsWith('==', base64String)) {
                    return 2;
                }
                if (endsWith('=', base64String)) {
                    return 1;
                }
                return 0;
            }
            function size(base64String) {
                return base64String.length / 4 * 3 - paddingSize(base64String);
            }
            function formatAsBytes(size) {
                return size.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ") + " bytes";
            }

            return formatAsBytes(size(base64String));
        };



	}]);
