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
                        templateUrl: 'scripts/app/pages/landing.html',
                        controller: 'MainController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        return $translate.refresh();
                    }]
                }
            }) .state('category', {
                parent: 'site',
                url: '/category/{id}',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/category.html',
                        controller: 'CategorieController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', function ($translate) {
                        return $translate.refresh();
                    }]
                }
            })
            .state('ads-details', {
            	parent: 'site',
                url: '/ads-details/{id}',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/ads-details.html',
                        controller: 'DetailController'
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
                        //controller: 'SubCategoryController'
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
                        //controller: 'AboutUsController'
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
                        //controller: 'ContactController'
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
                        //controller: 'FagController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate) {
                        return $translate.refresh();
                    }]
                }
            })
            .state('authenticated', {
                parent: 'site',
                url: '/authenticated',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/login.html',
                        controller: 'LoginController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('login');
                        return $translate.refresh();

                    }]

                }
            })
            .state('forgot-password', {
                parent: 'site',
                url: '/authenticated/forgot-password',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/forgot-password.html',
                        controller: 'RequestResetController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        return $translate.refresh();
                    }]
                }
            })
            .state('account-home', {
                parent: 'site',
                url: '/account-home',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/account-home.html',
                        controller: 'SettingsController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        return $translate.refresh();
                    }]
                }
            })
            .state('account-myads', {
                parent: 'site',
                url: '/account-myads',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/account-myads.html',
                        controller: 'AccountMyadsController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        return $translate.refresh();
                    }]
                }
            })
            .state('account-favourite-ads', {
                parent: 'site',
                url: '/account-favourite-ads',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/account-favourite-ads.html'
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
            .state('account-saved-search', {
                parent: 'site',
                url: '/account-saved-search',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/account-saved-search.html'
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
            .state('account-archived-ads', {
                parent: 'site',
                url: '/account-archived-ads',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/account-archived-ads.html'
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
            .state('account-pending-approval-ads', {
                parent: 'site',
                url: '/account-pending-approval-ads',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/account-pending-approval-ads.html'
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
            .state('account-close', {
                parent: 'site',
                url: '/account-close',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/account-close.html'
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
            .state('signup', {
                parent: 'site',
                url: '/signup',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/signup.html'
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
            .state('post-ads', {
                parent: 'site',
                url: '/post-ads',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/post-ads.html',
                        controller: 'PostAdsController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        return $translate.refresh();
                    }]
                }
            })
            .state('posting-success', {
                parent: 'site',
                url: '/post-ads/posting-success',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/pages/posting-success.html'
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
