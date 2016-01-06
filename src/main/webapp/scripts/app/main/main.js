'use strict';

angular.module('articleSellingApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/landing.html'
                        //controller: 'MainController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        return $translate.refresh();
                    }]
                }
            })
            .state('category', {
                url: '/category',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/category.html'
                        //controller: 'CategoryController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', function ($translate) {
                        return $translate.refresh();
                    }]
                }
            })
            .state('ads-details', {
                url: '/ads-details',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/ads-details.html'
                        //controller: 'AdsDetailsController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', function ($translate) {
                        return $translate.refresh();
                    }]
                }
            })
            .state('sub-category-sub-location', {
                url: '/sub-category-sub-location',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/sub-category-sub-location.html'
                        //controller: 'AdsDetailsController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', function ($translate) {
                        return $translate.refresh();
                    }]
                }
            }).state('about-us', {
                url: '/about-us',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/about-us.html'
                        //controller: 'CategoryController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate) {
                        return $translate.refresh();
                    }]
                }
            }).state('contact', {
                url: '/contact',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/contact.html'
                        //controller: 'CategoryController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate) {
                        return $translate.refresh();
                    }]
                }
            }).state('faq', {
                url: '/faq',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/faq.html'
                        //controller: 'CategoryController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate) {
                        return $translate.refresh();
                    }]
                }
            });
    });
