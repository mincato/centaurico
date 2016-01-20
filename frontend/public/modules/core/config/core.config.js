'use strict';

angular.module('core').service('Configuration', [ '$location', function($location) {

	var serviceContext = 'http://' + $location.host() + ':8080/centaurico-archetype/services/';
    
    return {
        serviceContext : serviceContext
    };

}]);


