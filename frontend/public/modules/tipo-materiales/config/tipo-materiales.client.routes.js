'use strict';

//Setting up route
angular.module('tipo-materiales').config(['$stateProvider',
	function($stateProvider) {
		// Tipo materiales state routing
		$stateProvider.
		state('listTipoMateriales', {
			url: '/tipo-materiales',
			templateUrl: 'modules/tipo-materiales/views/list-tipo-materiales.client.view.html'
		}).
		state('createTipoMateriale', {
			url: '/tipo-materiales/create',
			templateUrl: 'modules/tipo-materiales/views/create-tipo-materiale.client.view.html'
		}).
		state('viewTipoMateriale', {
			url: '/tipo-materiales/:tipoMaterialeId',
			templateUrl: 'modules/tipo-materiales/views/view-tipo-materiale.client.view.html'
		}).
		state('editTipoMateriale', {
			url: '/tipo-materiales/:tipoMaterialeId/edit',
			templateUrl: 'modules/tipo-materiales/views/edit-tipo-materiale.client.view.html'
		});
	}
]);