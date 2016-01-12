'use strict';

angular.module('core').factory('ConfirmationModalService', [
    '$modal',
	function($modal) {
        
        var open = function(title, body) {

            var modalInstance = $modal.open({
                templateUrl: 'modules/core/views/confirmation-modal.client.view.html',
                controller: 'ConfirmationModalController',
                size: 'sm',
                resolve: {
                    modalData: function() {
                        return {
                            title : title,
                            body: body
                        };
                    }
                }
            });
            
            return modalInstance;
        };        

	    return {
	    	open : open
	    };
	}
]);
