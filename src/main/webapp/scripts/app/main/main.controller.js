'use strict';

angular.module('articleSellingApp')

 .controller('MainController', function ($scope, Image, ParseLinks, Principal, $http){


        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
        
        
        
        $scope.top10 = [];
        $scope.loadTop10 = function(){
        	$http.get("/api/top10Images") 
        	.success(function(response){  
        		$scope.top10 = response;
        	})
        	.error(function(reason){
        		
        	});
        };
        
        $scope.loadTop10();
        
        
        	
        

        ////////////Automobiles///////////
        $scope.Automobiles=function(){
        $http.get("/api/categories/parentVu/Automobiles").success(function(response){
        	$scope.Automobiles=response;
		}).error(function(reason){
			console.log(reason);
		});
        };$scope.Automobiles();

        ///////////////////Services////////////////////////

        $scope.Services=function(){
            $http.get("/api/categories/parentVu/Services").success(function(response){

            	$scope.Services=response;
    		}).error(function(reason){
    			console.log(reason);
    		});
            };$scope.Services();

        ///////////////////  ForSale //////////////
          $scope.ForSale =function(){
              $http.get("/api/categories/parentVu/For Sale ").success(function(response){

                	$scope.ForSale =response;
        	  }).error(function(reason){
        			console.log(reason);
        	  });
              };$scope.ForSale();
       /////////////////// Property //////////////
           $scope.Property=function(){
                $http.get("/api/categories/parentVu/Property ").success(function(response){

                    	$scope.Property=response;
            		}).error(function(reason){
            			console.log(reason);
            		});
                    };$scope.Property();
         ///////////////////         Learning /////////////////
           $scope.Learning=function(){
                $http.get("/api/categories/parentVu/Learning").success(function(response){

                            	$scope.Learning=response;

                    		}).error(function(reason){
                    			console.log(reason);
                    		});
                            };$scope.Learning();
         //////////////////////////jobs ////////////////
            $scope.Jobs=function(){
                 $http.get("/api/categories/parentVu/Jobs").success(function(response){

                                	$scope.Jobs =response;

                        		}).error(function(reason){
                        			console.log(reason);
                        		});
                                }; $scope.Jobs();

               /////////////////////////Pets /////////////////
            $scope.Pets=function(){
                 $http.get("/api/categories/parentVu/Pets").success(function(response){

                                    	$scope.Pets=response;

                            		}).error(function(reason){
                            			console.log(reason);
                            		});
                                    };$scope.Pets();
             //////////////////////////////  Community       /////////////////
              $scope.Community=function(){
                 $http.get("/api/categories/parentVu/Community").success(function(response){

                        $scope.Community=response;
                    }).error(function(reason){
                        console.log(reason);
                    });
                    };$scope.Community();

    });
