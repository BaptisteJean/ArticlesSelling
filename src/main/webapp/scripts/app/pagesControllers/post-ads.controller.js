'use strict';

angular.module('articleSellingApp').controller('PostAdsController',
		['$state','$scope', '$http', '$stateParams', 'Ads', 'Categorie', 'Pays', 'Image', 'Principal', 'ParseLinks',
	        function($state, $scope, $http, $stateParams, Ads, Categorie, Pays, Image, Principal, ParseLinks) {

	        $scope.ads = {
	        		categorieId: null,
	        		nameCategorie: null,
                    nameAds: null,
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
	        $scope.image = {
	        	    adsId: null,
	        	    nameAds: null,
	        	    id: null,
	        	    imgNormalContentType: null,
	        	    imgThumbnailContentType: null
	        	  };
	        $scope.adss = [];
	        $scope.categories = [];
	        $scope.payss = [];
	        $scope.page = 0;

	        $scope.loadAll = function() {
//	            Ads.query({page: $scope.page}, function(result, headers) {
//	                $scope.links = ParseLinks.parse(headers('link'));
//	                $scope.adss = result;
//	            });
	        	$http.get("/api/adId")
	        	.success(function(response){
	        		console.log(response);
	        	})
	        	.error(function(reason){
	        		
	        	});
	        	
	        	$http.get("/api/adId")
	        	.success(function(response){
	        		$scope.ads.id = response.id;
		            $scope.image.adsId = response.id;
	        	})
	        	.error(function(reason){
	        			
	        	});
	            
	            Categorie.query({page: $scope.page, size: 63}, function(result, headers) {
	                $scope.links = ParseLinks.parse(headers('link'));
	                $scope.categories = result;
	            });
	            
	            Pays.query({page: $scope.page, size: 243}, function(result, headers) {
	                $scope.links = ParseLinks.parse(headers('link'));
	                $scope.payss = result;
	            });
	        };
	        $scope.loadAll();

	        //$scope.categories = Categorie.query();
	        //$scope.images = Image.query();
	        
	        $scope.load = function(id) {
	            Ads.get({id : id}, function(result) {
	                $scope.ads = result;
	            });
	            Image.get({id : id}, function(result) {
	                $scope.image = result;
	            });
	        };

	        Principal.identity(true).then(function(account) {
	            $scope.settingsAccount = account;
	        });

	        var onSaveAdFinished = function (result) {
	            $scope.$emit('articleSellingApp:adsUpdate', result);
	            //$modalInstance.close(result);
	        };

	        var onSaveImgFinished = function (result) {
	            $scope.$emit('articleSellingApp:imageUpdate', result);
	            //$modalInstance.close(result);
	        };

	        $scope.save = function () {

	        	if ($scope.ads.id != null && $scope.image.id != null) {
	                Ads.update($scope.ads, onSaveAdFinished);
	                Image.update($scope.image, onSaveImgFinished);
	            } else {
	            	
	            	$scope.ads.nameCategorie = $scope.categories[$scope.ads.categorieId].nameCategorie;
	            	$scope.ads.pays = $scope.payss[$scope.ads.pays - 1].namePays;
	            	$scope.image.nameAds = $scope.ads.nameAds;
	            	Ads.save($scope.ads, onSaveAdFinished);
	            	Image.save($scope.image, onSaveImgFinished);
	                $state.go("posting-success");
	            }

	        };
	        
//	        $scope.searcCity = function(){
//	        	$scope.ads.pays = $scope.payss[$scope.ads.pays - 1].namePays;
//	        	console.log($scope.ads.pays);
//	        }
	        

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

	        $scope.setImgThumbnail = function ($file, image) {
	            if ($file && $file.$error == 'pattern') {
	                return;
	            }
	            if ($file) {
	                var fileReader = new FileReader();
	                fileReader.readAsDataURL($file);
	                fileReader.onload = function (e) {
	                    var base64Data = e.target.result.substr(e.target.result.indexOf('base64,') + 'base64,'.length);
	                    $scope.$apply(function() {
	                        image.imgThumbnail = base64Data;
	                        image.imgThumbnailContentType = $file.type;
	                    });
	                };
	            }
	        };

	        $scope.setImgNormal = function ($file, image) {
	            if ($file && $file.$error == 'pattern') {
	                return;
	            }
	            if ($file) {
	                var fileReader = new FileReader();
	                fileReader.readAsDataURL($file);
	                fileReader.onload = function (e) {
	                    var base64Data = e.target.result.substr(e.target.result.indexOf('base64,') + 'base64,'.length);
	                    $scope.$apply(function() {
	                        image.imgNormal = base64Data;
	                        image.imgNormalContentType = $file.type;
	                    });
	                };
	            }
	        };
	}]);
