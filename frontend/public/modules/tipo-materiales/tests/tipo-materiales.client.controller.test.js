'use strict';

(function() {
	// Tipo materiales Controller Spec
	describe('Tipo materiales Controller Tests', function() {
		// Initialize global variables
		var TipoMaterialesController,
		scope,
		$httpBackend,
		$stateParams,
		$location;

		// The $resource service augments the response object with methods for updating and deleting the resource.
		// If we were to use the standard toEqual matcher, our tests would fail because the test values would not match
		// the responses exactly. To solve the problem, we define a new toEqualData Jasmine matcher.
		// When the toEqualData matcher compares two objects, it takes only object properties into
		// account and ignores methods.
		beforeEach(function() {
			jasmine.addMatchers({
				toEqualData: function(util, customEqualityTesters) {
					return {
						compare: function(actual, expected) {
							return {
								pass: angular.equals(actual, expected)
							};
						}
					};
				}
			});
		});

		// Then we can start by loading the main application module
		beforeEach(module(ApplicationConfiguration.applicationModuleName));

		// The injector ignores leading and trailing underscores here (i.e. _$httpBackend_).
		// This allows us to inject a service but then attach it to a variable
		// with the same name as the service.
		beforeEach(inject(function($controller, $rootScope, _$location_, _$stateParams_, _$httpBackend_) {
			// Set a new global scope
			scope = $rootScope.$new();

			// Point global variables to injected services
			$stateParams = _$stateParams_;
			$httpBackend = _$httpBackend_;
			$location = _$location_;

			// Initialize the Tipo materiales controller.
			TipoMaterialesController = $controller('TipoMaterialesController', {
				$scope: scope
			});
		}));

		it('$scope.find() should create an array with at least one Tipo materiale object fetched from XHR', inject(function(TipoMateriales) {
			// Create sample Tipo materiale using the Tipo materiales service
			var sampleTipoMateriale = new TipoMateriales({
				name: 'New Tipo materiale'
			});

			// Create a sample Tipo materiales array that includes the new Tipo materiale
			var sampleTipoMateriales = [sampleTipoMateriale];

			// Set GET response
			$httpBackend.expectGET('tipo-materiales').respond(sampleTipoMateriales);

			// Run controller functionality
			scope.find();
			$httpBackend.flush();

			// Test scope value
			expect(scope.tipoMateriales).toEqualData(sampleTipoMateriales);
		}));

		it('$scope.findOne() should create an array with one Tipo materiale object fetched from XHR using a tipoMaterialeId URL parameter', inject(function(TipoMateriales) {
			// Define a sample Tipo materiale object
			var sampleTipoMateriale = new TipoMateriales({
				name: 'New Tipo materiale'
			});

			// Set the URL parameter
			$stateParams.tipoMaterialeId = '525a8422f6d0f87f0e407a33';

			// Set GET response
			$httpBackend.expectGET(/tipo-materiales\/([0-9a-fA-F]{24})$/).respond(sampleTipoMateriale);

			// Run controller functionality
			scope.findOne();
			$httpBackend.flush();

			// Test scope value
			expect(scope.tipoMateriale).toEqualData(sampleTipoMateriale);
		}));

		it('$scope.create() with valid form data should send a POST request with the form input values and then locate to new object URL', inject(function(TipoMateriales) {
			// Create a sample Tipo materiale object
			var sampleTipoMaterialePostData = new TipoMateriales({
				name: 'New Tipo materiale'
			});

			// Create a sample Tipo materiale response
			var sampleTipoMaterialeResponse = new TipoMateriales({
				_id: '525cf20451979dea2c000001',
				name: 'New Tipo materiale'
			});

			// Fixture mock form input values
			scope.name = 'New Tipo materiale';

			// Set POST response
			$httpBackend.expectPOST('tipo-materiales', sampleTipoMaterialePostData).respond(sampleTipoMaterialeResponse);

			// Run controller functionality
			scope.create();
			$httpBackend.flush();

			// Test form inputs are reset
			expect(scope.name).toEqual('');

			// Test URL redirection after the Tipo materiale was created
			expect($location.path()).toBe('/tipo-materiales/' + sampleTipoMaterialeResponse._id);
		}));

		it('$scope.update() should update a valid Tipo materiale', inject(function(TipoMateriales) {
			// Define a sample Tipo materiale put data
			var sampleTipoMaterialePutData = new TipoMateriales({
				_id: '525cf20451979dea2c000001',
				name: 'New Tipo materiale'
			});

			// Mock Tipo materiale in scope
			scope.tipoMateriale = sampleTipoMaterialePutData;

			// Set PUT response
			$httpBackend.expectPUT(/tipo-materiales\/([0-9a-fA-F]{24})$/).respond();

			// Run controller functionality
			scope.update();
			$httpBackend.flush();

			// Test URL location to new object
			expect($location.path()).toBe('/tipo-materiales/' + sampleTipoMaterialePutData._id);
		}));

		it('$scope.remove() should send a DELETE request with a valid tipoMaterialeId and remove the Tipo materiale from the scope', inject(function(TipoMateriales) {
			// Create new Tipo materiale object
			var sampleTipoMateriale = new TipoMateriales({
				_id: '525a8422f6d0f87f0e407a33'
			});

			// Create new Tipo materiales array and include the Tipo materiale
			scope.tipoMateriales = [sampleTipoMateriale];

			// Set expected DELETE response
			$httpBackend.expectDELETE(/tipo-materiales\/([0-9a-fA-F]{24})$/).respond(204);

			// Run controller functionality
			scope.remove(sampleTipoMateriale);
			$httpBackend.flush();

			// Test array after successful delete
			expect(scope.tipoMateriales.length).toBe(0);
		}));
	});
}());