'use strict';

angular.module('articleSellingApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('ads', {
                parent: 'entity',
                url: '/adss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'articleSellingApp.ads.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ads/adss.html',
                        controller: 'AdsController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ads');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('ads.detail', {
                parent: 'entity',
                url: '/ads/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'articleSellingApp.ads.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ads/ads-detail.html',
                        controller: 'AdsDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ads');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Ads', function($stateParams, Ads) {
                        return Ads.get({id : $stateParams.id});
                    }]
                }
            })
            .state('ads.new', {
                parent: 'ads',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/ads/ads-dialog.html',
                        controller: 'AdsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
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
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('ads', null, { reload: true });
                    }, function() {
                        $state.go('ads');
                    })
                }]
            })
            .state('ads.edit', {
                parent: 'ads',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/ads/ads-dialog.html',
                        controller: 'AdsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Ads', function(Ads) {
                                return Ads.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('ads', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
