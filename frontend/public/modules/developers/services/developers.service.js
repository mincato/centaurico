'use strict';

angular.module('developers').factory('DeveloperService', ['$resource', 'Configuration',
	function($resource, Configuration) {

		var url = Configuration.serviceContext + 'developers/:developerId';
		
		return $resource(url, {
            developerId: '@_id'
		}, {
			update: {
				method: 'PUT'
			}
		});
	}
]);