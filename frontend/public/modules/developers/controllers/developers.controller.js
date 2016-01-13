'use strict';

angular.module('developers').controller('DeveloperController', ['$scope', '$stateParams', '$location', 'DeveloperService', 'Messages',
	function($scope, $stateParams, $location, DeveloperService, Messages) {
        
        $scope.developer = new DeveloperService();

		$scope.create = function() {
            
            console.log($scope.developer);

			$scope.developer.$save(function(response) {
				$location.path('developers/' + response._id);

				$scope.name = '';
				$scope.lastName = '';
                $scope.seniority = '';
                
			}, function(errorResponse) {
                var error = Messages.getErrorMessage(errorResponse.data);
                $scope.$emit('showError', error);
			});
		};

		$scope.remove = function(developer) {
			if (developer) { 
				developer.$remove();

				for (var i in $scope.developers) {
					if ($scope.developers[i] === developer) {
						$scope.developers.splice(i, 1);
					}
				}
			} else {
				$scope.developer.$remove(function() {
					$location.path('developers');
				});
			}
		};

		$scope.update = function() {
			$scope.developer.$update(function() {
				$location.path('developers/' + $scope.developer._id);
			}, function(errorResponse) {
                var error = Messages.getErrorMessage(errorResponse.data);
                $scope.$emit('showError', error);
			});
		};

		$scope.find = function() {
			$scope.developers = DeveloperService.query();
		};

		$scope.findOne = function() {
			$scope.developer = DeveloperService.get({
				developerId: $stateParams.developerId
			});
		};
	}
]);