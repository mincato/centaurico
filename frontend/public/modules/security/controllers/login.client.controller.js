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
                switch (response.code) {
                    case 4: //ACCESS_DENIED
                        error = 'Nombre de usuario o contraseña inválidos.';
                        break;
                    case 9:
                        error = 'Usuario bloqueado, consulte con su administrador.';
                        break;
                    default:
                        error = 'Error Inesperado.';
                }
            } else {
                error = 'Error Inesperado.';
            }
            $scope.$emit('showError', error);
        };        
        
        $scope.init();
    }
]);
