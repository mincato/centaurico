'use strict';

angular.module('developers').config(['$stateProvider',
	function($stateProvider) {
		$stateProvider.
		state('listDevelopers', {
			url: '/developers',
			templateUrl: 'modules/developers/views/list-developers.view.html'
		}).
		state('createDeveloper', {
			url: '/developers/create',
			templateUrl: 'modules/developers/views/create-developer.view.html'
		}).
		state('viewDeveloper', {
			url: '/developers/:developerId',
			templateUrl: 'modules/developers/views/view-developer.view.html'
		}).
		state('editDeveloper', {
			url: '/developers/:developerId/edit',
			templateUrl: 'modules/developers/views/edit-developer.view.html'
		});
	}
]);