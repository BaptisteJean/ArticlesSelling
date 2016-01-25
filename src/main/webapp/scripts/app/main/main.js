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
            .state('categorie1', {
                url: '/categorie1',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                       // controller: 'MainController'
                    }
                },
            })
                .state('categorie2', {
                url: '/categorie2',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                      //  controller: 'MainController'
                    }
                },
            })
                .state('categorie3', {
                url: '/categorie3',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                      //  controller: 'MainController'
                    }
                },
            })
                .state('categorie4', {
                url: '/categorie4',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                      //  controller: 'MainController'
                    }
                },
            })
                .state('categorie5', {
                url: '/categorie5',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                      //  controller: 'MainController'
                    }
                },
            })
                .state('categorie6', {
                url: '/categorie6',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                      //  controller: 'MainController'
                    }
                },
            })
                .state('categorie7', {
                url: '/categorie7',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                      //  controller: 'MainController'
                    }
                },
            })
                .state('categorie8', {
                url: '/categorie8',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                      //  controller: 'MainController'
                    }
                },
            })
                .state('categorie9', {
                url: '/categorie9',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                       // controller: 'MainController'
                    }
                },
            })
                .state('categorie10', {
                url: '/categorie10',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/menu_link.html',
                       // controller: 'MainController'
                    }
                },
            });
    });
