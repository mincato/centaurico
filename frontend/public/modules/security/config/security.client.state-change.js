'use strict';

angular.module('security').run(function($rootScope, $location, AuthenticationService, $window, $log) {
    
	$rootScope.$on('$stateChangeStart', function (event, next) {
        
        // En Chrome esto no funciona!! En Firefox si
        $window.onbeforeunload = function() {
            if (AuthenticationService.isLogged()) {
                AuthenticationService.logout();
            }
        };
        
		if (!AuthenticationService.isLogged()) {
			$location.path('login');
		}
        
		if (next.data && next.data.permission && !AuthenticationService.hasPermission(next.data.permission)) {
			$log.error('Acceso Denegado: Se requiere permiso ' + next.data.permission);
			event.preventDefault();
			return false;
		}
	});
});
