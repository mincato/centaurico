'use strict';

angular.module('security').controller('LoginController', ['$scope', 'AuthenticationService', '$location', 'Configuration', 'Messages',
    function ($scope, AuthenticationService, $location, Configuration, Messages) {
        
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
            var error = Messages.getErrorMessage(response);            
            $scope.$emit('showError', error);
        };        
        
        $scope.init();
    }
]);
