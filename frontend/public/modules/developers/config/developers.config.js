'use strict';

angular.module('developers').run(['Menus',
	function(Menus) {
		Menus.addMenuItem('topbar', 'Developers', 'developers', 'dropdown', 'developers(/create)?');
		Menus.addSubMenuItem('topbar', 'developers', 'List Developers', 'developers');
		Menus.addSubMenuItem('topbar', 'developers', 'New Developer', 'developers/create');
	}
]);