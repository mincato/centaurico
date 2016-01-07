'use strict';

angular.module('security').controller('LoginController', ['$scope', 'AuthenticationService', '$location', 'Configuration',
    function ($scope, AuthenticationService, $location, Configuration) {
        
        $scope.init = function() {            
            $scope.credentials = {
                username: '',
                password: ''
            };
        };
        
        $scope.login = function() {
            AuthenticationService.login($scope.credentials, loginSuccess, loginError);
        };
        
        var loginSuccess = function(response) {
            $location.path('home');
        };
        
        var loginError = function(response) {
            var error = null;
            if (response) {
                error = response.message;
            } else {
                error = 'Error Inesperado.';
            }
            $scope.$emit('showError', error);
        };        
        
        $scope.init();
    }
]);
