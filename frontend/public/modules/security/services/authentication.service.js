'use strict';

angular.module('security').factory('AuthenticationService', [
    '$http','SessionService', 'Configuration',
    function($http, SessionService, Configuration) {

        var login = function(credentials, sucess, error) {

            var url = Configuration.serviceContext + 'auth/login';
            
            $http.post(url, credentials).success(function(response) {

                SessionService.saveUser(response);

                if (sucess) {
                    sucess(response);
                }

            }).error(function(response) {
                if (error) {
                    error(response);
                }
            });

        };
        
        var logout = function(success, error) {            
            
            var url = Configuration.serviceContext + 'auth/logout';
            
            $http.post(url).success(function(response) {
                
                SessionService.invalidate();
                
                if (success) {
                    success(response);
                }
                
            }).error(function(response) {
                if (error) {
                    error(response);
                }
            });
        };

        var isLogged = function() {
            return SessionService.isLogged();
        };

        var getUser = function() {
            return SessionService.loadUser();
        };
        
        var hasPermission = function(permission) {
            
            var user = SessionService.loadUser();

            var found = false;
            if (user && user.roles) {
                user.roles.every(function(rol) {
                    if (rol.permissions) {
                        rol.permissions.every(function(itemPermission) {
                            if (itemPermission.name === permission) {
                                found = true;
                            }
                            return !found;
                        });
                    }
                    return !found;
                });
            }
            return found;
        };
        
        return {
            login: login,
            logout: logout,
            isLogged: isLogged,
            getUser: getUser,
            hasPermission: hasPermission
        };
    }
]);
