'use strict';

angular.module('articleSellingApp')
    .controller('LoginController', function ($rootScope, $scope, $state, $timeout, Auth) {
        $scope.user = {};
        $scope.errors = {};

        $scope.login = function (event) {
            event.preventDefault();
            Auth.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;
                if ($rootScope.previousStateName === 'register') {
                    $state.go('account-home');
                } else {
                    $rootScope.back();
                }
            }).catch(function () {
                $scope.authenticationError = true;
            });
        };
    });
