'use strict';

angular.module('core').controller('HeaderController', ['$scope', 'Menus', '$location', 'AuthenticationService',
	function($scope, Menus, $location, AuthenticationService) {
		
		$scope.isCollapsed = false;
		$scope.menu = Menus.getMenu('topbar');
        $scope.loggedUser = function() {
            var user = AuthenticationService.getUser();
            return user.firstName + ' ' + user.lastName;
        };

        $scope.isLogged = function() {
            return AuthenticationService.isLogged();
        };
        
        $scope.logout = function() {
            AuthenticationService.logout(function(response) {
                $location.path('login');
            }, function(errorResponse) {
                console.error('Error al cerrar sesión.');
            });
        };
        
		$scope.toggleCollapsibleMenu = function() {
			$scope.isCollapsed = !$scope.isCollapsed;
		};

		// Collapsing the menu after navigation
		$scope.$on('$stateChangeSuccess', function() {
			$scope.isCollapsed = false;
		});
	}
]);