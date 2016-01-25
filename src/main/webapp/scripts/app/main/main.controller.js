'use strict';

angular.module('articleSellingApp')
    .controller('MainController', function ($http, $scope, Principal) {
    	
    	$http.get("api/categories/parentVu/{parent}").success(function(response){ 
    		
			console.log("succes count parent"+ $stateParams.id);
		}).error(function(reason){
			console.log(reason);
		});
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
                  
        });
    });
