'use strict';

// Configuring the Articles module
angular.module('tipo-materiales').run(['Menus',
	function(Menus) {
		// Set top bar menu items
		Menus.addMenuItem('topbar', 'Tipo materiales', 'tipo-materiales', 'dropdown', 'tipo-materiales(/create)?');
		Menus.addSubMenuItem('topbar', 'tipo-materiales', 'List Tipo materiales', 'tipo-materiales');
		Menus.addSubMenuItem('topbar', 'tipo-materiales', 'New Tipo materiale', 'tipo-materiales/create');
	}
]);