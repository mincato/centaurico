'use strict';

// Tipo materiales controller
angular.module('tipo-materiales').controller('TipoMaterialController', ['$scope', '$stateParams', '$location', 'TipoMaterialService',
	function($scope, $stateParams, $location, TipoMaterialService) {

		// Create new Tipo materiale
		$scope.create = function() {
			// Create new Tipo materiale object
			var tipoMateriale = new TipoMaterialService ({
				codigo: this.codigo,
				valor: this.valor
			});

			// Redirect after save
			tipoMateriale.$save(function(response) {
				$location.path('tipo-materiales/' + response._id);

				// Clear form fields
				$scope.codigo = '';
				$scope.valor = '';
			}, function(errorResponse) {
				$scope.error = errorResponse.data.message;
			});
		};

		// Remove existing Tipo materiale
		$scope.remove = function(tipoMateriale) {
			if ( tipoMateriale ) { 
				tipoMateriale.$remove();

				for (var i in $scope.tipoMateriales) {
					if ($scope.tipoMateriales [i] === tipoMateriale) {
						$scope.tipoMateriales.splice(i, 1);
					}
				}
			} else {
				$scope.tipoMateriale.$remove(function() {
					$location.path('tipo-materiales');
				});
			}
		};

		// Update existing Tipo materiale
		$scope.update = function() {
			var tipoMateriale = $scope.tipoMateriale;

			tipoMateriale.$update(function() {
				$location.path('tipo-materiales/' + tipoMateriale._id);
			}, function(errorResponse) {
				$scope.error = errorResponse.data.message;
			});
		};

		// Find a list of Tipo materiales
		$scope.find = function() {
			$scope.tipoMateriales = TipoMaterialService.query();
		};

		// Find existing Tipo materiale
		$scope.findOne = function() {
			$scope.tipoMateriale = TipoMaterialService.get({
				tipoMaterialeId: $stateParams.tipoMaterialeId
			});
		};
	}
]);