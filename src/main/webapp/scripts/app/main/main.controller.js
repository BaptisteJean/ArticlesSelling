'use strict';

angular.module('articleSellingApp')

 .controller('MainController', function ($scope, Image, ParseLinks, Principal, $http){

 //   .controller('MainController', function ( $scope, Principal) {
    	  	

  //  .controller('MainController', function ( $http, $scope, Principal) {
    	
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;       
        });

//        $scope.Mid = null;
//        $scope.recuperer = function(adsId){
//        	$scope.Mid = adsId;
//        	$http.put('/api/nbreVue/ads/'+adsId)
//        	.success(function(response){
//        		console.log(response);
//        		$scope.nbreVue = response;
//        	})
//        	.error(function(response){
//        		console.log(response);
//        	})
//        }
//        
//        $scope.recuperer();
        
       
        $scope.images = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Image.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.images = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Image.get({id: id}, function(result) {
                $scope.image = result;
                $('#deleteImageConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Image.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteImageConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.image = {
                imgThumbnail: null,
                imgThumbnailContentType: null,
                imgNormal: null,
                imgNormalContentType: null,
                identif: null,
                id: null
            };
        };

        $scope.abbreviate = function (text) {
            if (!angular.isString(text)) {
                return '';
            }
            if (text.length < 30) {
                return text;
            }
            return text ? (text.substring(0, 15) + '...' + text.slice(-10)) : '';
        };

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
