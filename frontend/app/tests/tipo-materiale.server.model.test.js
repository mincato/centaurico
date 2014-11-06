'use strict';

/**
 * Module dependencies.
 */
var should = require('should'),
	mongoose = require('mongoose'),
	User = mongoose.model('User'),
	TipoMateriale = mongoose.model('TipoMateriale');

/**
 * Globals
 */
var user, tipoMateriale;

/**
 * Unit tests
 */
describe('Tipo materiale Model Unit Tests:', function() {
	beforeEach(function(done) {
		user = new User({
			firstName: 'Full',
			lastName: 'Name',
			displayName: 'Full Name',
			email: 'test@test.com',
			username: 'username',
			password: 'password'
		});

		user.save(function() { 
			tipoMateriale = new TipoMateriale({
				name: 'Tipo materiale Name',
				user: user
			});

			done();
		});
	});

	describe('Method Save', function() {
		it('should be able to save without problems', function(done) {
			return tipoMateriale.save(function(err) {
				should.not.exist(err);
				done();
			});
		});

		it('should be able to show an error when try to save without name', function(done) { 
			tipoMateriale.name = '';

			return tipoMateriale.save(function(err) {
				should.exist(err);
				done();
			});
		});
	});

	afterEach(function(done) { 
		TipoMateriale.remove().exec();
		User.remove().exec();

		done();
	});
});