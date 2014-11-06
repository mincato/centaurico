'use strict';

//Tipo materiales service used to communicate Tipo materiales REST endpoints
angular.module('tipo-materiales').factory('TipoMateriales', ['$resource',
	function($resource) {
		return $resource('http://localhost:8080/cnea-trazabilidad-archetype/services/tipo-materiales/:tipoMaterialeId', { tipoMaterialeId: '@_id'
		}, {
			update: {
				method: 'PUT'
			}
		});
	}
]);