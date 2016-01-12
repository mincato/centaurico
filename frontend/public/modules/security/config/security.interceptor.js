'use strict';

angular.module('security').config(['$httpProvider',
	function($httpProvider) {
        
//        $httpProvider.defaults.withCredentials = true;
        
		$httpProvider.interceptors.push(['$q', '$location', 'SessionService', '$injector',
			function($q, $location, SessionService, $injector) {

				var redirectToLogin = function() {
					SessionService.invalidate();
					$location.path('login');
				};

				return {
					request: function(config) {
						config.headers = config.headers || {};
						var token = SessionService.getToken();
						if (token) {
							config.headers.Authorization = 'Bearer ' + token;                            
						}
						return config;
					},
					responseError: function(rejection) {
						switch (rejection.status) {
							case 401:
								redirectToLogin();
								break;
							case 403:
								// Add unauthorized behaviour 
								break;
						}

						return $q.reject(rejection);
					}
				};
			}
		]);
	}
]);
