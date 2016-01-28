'use strict';

angular.module('articleSellingApp')
    .controller('MainController', function ( $http, $scope, Principal) {
    	
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
                  
        });
        ////////////Automobiles///////////
        $scope.Automobiles=function(){    
        $http.get("/api/categories/parentVu/Automobiles").success(function(response){ 
        	//$scope.parentVuProperty=response.Automobiles;
        	$scope.Automobiles=response;
			console.log("succes count");
		}).error(function(reason){
			console.log(reason);
		});
        };$scope.Automobiles();
        
        ///////////////////Services////////////////////////
        
        $scope.Services=function(){    
            $http.get("/api/categories/parentVu/Services").success(function(response){ 
            	
            	$scope.Services=response;
    			console.log("succes count");
    		}).error(function(reason){
    			console.log(reason);
    		});
            };$scope.Services();
            
        ///////////////////  ForSale //////////////
          $scope.ForSale =function(){    
              $http.get("/api/categories/parentVu/For Sale ").success(function(response){ 
                	
                	$scope.ForSale =response;
        			console.log("succes count");
        	  }).error(function(reason){
        			console.log(reason);
        	  });
              };$scope.ForSale();
       /////////////////// Property //////////////
           $scope.Property=function(){    
                $http.get("/api/categories/parentVu/Property ").success(function(response){ 
                    	//$scope.parentVuProperty=response.Automobiles;
                    	$scope.Property=response;
            			console.log("succes count");
            		}).error(function(reason){
            			console.log(reason);
            		});
                    };$scope.Property();
         ///////////////////         Learning ///////////////// 
           $scope.Learning=function(){    
                $http.get("/api/categories/parentVu/Learning").success(function(response){ 
                            	
                            	$scope.Learning=response;
                    			console.log("succes count");
                    		}).error(function(reason){
                    			console.log(reason);
                    		});
                            };$scope.Learning();
         //////////////////////////jobs ////////////////
            $scope.Jobs=function(){    
                 $http.get("/api/categories/parentVu/Jobs").success(function(response){ 
                                	//$scope.parentVuProperty=response.Automobiles;
                                	$scope.Jobs =response;
                        			console.log("succes count");
                        		}).error(function(reason){
                        			console.log(reason);
                        		});
                                }; $scope.Jobs();
                           
               /////////////////////////Pets /////////////////
            $scope.Pets=function(){    
                 $http.get("/api/categories/parentVu/Pets").success(function(response){ 
                                    	//$scope.parentVuProperty=response.Automobiles;
                                    	$scope.Pets=response;
                            			console.log("succes count");
                            		}).error(function(reason){
                            			console.log(reason);
                            		});
                                    };$scope.Pets();
             //////////////////////////////  Community       /////////////////       
              $scope.Community=function(){    
                 $http.get("/api/categories/parentVu/Community").success(function(response){ 
                                        	//$scope.parentVuProperty=response.Automobiles;
                                        	$scope.Community=response;
                                			console.log("succes count");
                                		}).error(function(reason){
                                			console.log(reason);
                                		});
                                        };$scope.Community();
    });
