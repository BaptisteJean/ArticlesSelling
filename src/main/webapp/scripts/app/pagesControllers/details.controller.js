'use strict';

angular.module('articleSellingApp').controller('DetailController',
		['$state','$scope', '$http', '$stateParams',
	        function($state, $scope, $http, $stateParams) {


			$scope.numbreVue=function(){
	    		$http.put("/api/nbreVue/ads/"+$stateParams.id).success(function(response){
	    			console.log("succes count"+ $stateParams.id);
	    		}).error(function(reason){
	    			console.log(reason);
	    		});

	    	}
	        $scope.numbreVue();

        $('.bxslider').bxSlider({
            pagerCustom: '#bx-pager'
        });

	}]);
