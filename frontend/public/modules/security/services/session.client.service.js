'use strict';

angular.module('security').factory('SessionService', ['StorageService', 'Configuration',
	function(StorageService, Configuration) {
        
        var userCached = null;

		var saveUser = function(user) {
			StorageService.user = JSON.stringify(user);
            userCached = user;
//            Configuration.setEntityId(user.entidadId);
		};

		var loadUser = function() {
            
            if (userCached !== null) {
                return userCached;
            }
            
			var user = StorageService.user;
			if (typeof user === 'string') {
				user = JSON.parse(user);
			}
            userCached = user;
			return user;
		};

		var getToken = function() {
			var user = loadUser();
			return (user) ? user.token : null;
		};

		var invalidate = function() {
			StorageService.removeItem('user');
            userCached = null;
		};

		var updateToken = function(newToken) {
			var user = loadUser();
			user.token = newToken;
			saveUser(user);
		};
        
        var isLogged = function() {
            return (typeof window.sessionStorage.user !== 'undefined');
        };

		return {
			saveUser: saveUser,
			loadUser: loadUser,
			getToken: getToken,
			invalidate: invalidate,
			updateToken : updateToken,
            isLogged: isLogged
		};
	}
]);
