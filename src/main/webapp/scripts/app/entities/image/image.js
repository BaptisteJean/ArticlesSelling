'use strict';

angular.module('articleSellingApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('image', {
                parent: 'entity',
                url: '/images',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'articleSellingApp.image.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/image/images.html',
                        controller: 'ImageController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('image');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('image.detail', {
                parent: 'entity',
                url: '/image/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'articleSellingApp.image.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/image/image-detail.html',
                        controller: 'ImageDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('image');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Image', function($stateParams, Image) {
                        return Image.get({id : $stateParams.id});
                    }]
                }
            })
            .state('image.new', {
                parent: 'image',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/image/image-dialog.html',
                        controller: 'ImageDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    imgThumbnail: null,
                                    imgThumbnailContentType: null,
                                    imgNormal: null,
                                    imgNormalContentType: null,
                                    identif: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('image', null, { reload: true });
                    }, function() {
                        $state.go('image');
                    })
                }]
            })
            .state('image.edit', {
                parent: 'image',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/image/image-dialog.html',
                        controller: 'ImageDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Image', function(Image) {
                                return Image.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('image', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
