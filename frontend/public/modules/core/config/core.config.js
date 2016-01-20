'use strict';

angular.module('core').service('Configuration', [ '$location', function($location) {

	var serviceContext = 'http://' + $location.host() + ':8080/mate-search-backoffice/services/';
    
    return {
        serviceContext : serviceContext
    };

}]);
