'use strict';

//Tipo materiales service used to communicate Tipo materiales REST endpoints
angular.module('tipo-materiales').factory('TipoMaterialService', ['$resource', 'Configuration',
	function($resource, Configuration) {

		var url = Configuration.serviceContext + 'tipo-materiales/:tipoMaterialeId';
		
		return $resource(url, { tipoMaterialeId: '@_id'
		}, {
			update: {
				method: 'PUT'
			}
		});
	}
]);