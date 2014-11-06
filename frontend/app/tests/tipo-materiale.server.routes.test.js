'use strict';

var should = require('should'),
	request = require('supertest'),
	app = require('../../server'),
	mongoose = require('mongoose'),
	User = mongoose.model('User'),
	TipoMateriale = mongoose.model('TipoMateriale'),
	agent = request.agent(app);

/**
 * Globals
 */
var credentials, user, tipoMateriale;

/**
 * Tipo materiale routes tests
 */
describe('Tipo materiale CRUD tests', function() {
	beforeEach(function(done) {
		// Create user credentials
		credentials = {
			username: 'username',
			password: 'password'
		};

		// Create a new user
		user = new User({
			firstName: 'Full',
			lastName: 'Name',
			displayName: 'Full Name',
			email: 'test@test.com',
			username: credentials.username,
			password: credentials.password,
			provider: 'local'
		});

		// Save a user to the test db and create new Tipo materiale
		user.save(function() {
			tipoMateriale = {
				name: 'Tipo materiale Name'
			};

			done();
		});
	});

	it('should be able to save Tipo materiale instance if logged in', function(done) {
		agent.post('/auth/signin')
			.send(credentials)
			.expect(200)
			.end(function(signinErr, signinRes) {
				// Handle signin error
				if (signinErr) done(signinErr);

				// Get the userId
				var userId = user.id;

				// Save a new Tipo materiale
				agent.post('/tipo-materiales')
					.send(tipoMateriale)
					.expect(200)
					.end(function(tipoMaterialeSaveErr, tipoMaterialeSaveRes) {
						// Handle Tipo materiale save error
						if (tipoMaterialeSaveErr) done(tipoMaterialeSaveErr);

						// Get a list of Tipo materiales
						agent.get('/tipo-materiales')
							.end(function(tipoMaterialesGetErr, tipoMaterialesGetRes) {
								// Handle Tipo materiale save error
								if (tipoMaterialesGetErr) done(tipoMaterialesGetErr);

								// Get Tipo materiales list
								var tipoMateriales = tipoMaterialesGetRes.body;

								// Set assertions
								(tipoMateriales[0].user._id).should.equal(userId);
								(tipoMateriales[0].name).should.match('Tipo materiale Name');

								// Call the assertion callback
								done();
							});
					});
			});
	});

	it('should not be able to save Tipo materiale instance if not logged in', function(done) {
		agent.post('/tipo-materiales')
			.send(tipoMateriale)
			.expect(401)
			.end(function(tipoMaterialeSaveErr, tipoMaterialeSaveRes) {
				// Call the assertion callback
				done(tipoMaterialeSaveErr);
			});
	});

	it('should not be able to save Tipo materiale instance if no name is provided', function(done) {
		// Invalidate name field
		tipoMateriale.name = '';

		agent.post('/auth/signin')
			.send(credentials)
			.expect(200)
			.end(function(signinErr, signinRes) {
				// Handle signin error
				if (signinErr) done(signinErr);

				// Get the userId
				var userId = user.id;

				// Save a new Tipo materiale
				agent.post('/tipo-materiales')
					.send(tipoMateriale)
					.expect(400)
					.end(function(tipoMaterialeSaveErr, tipoMaterialeSaveRes) {
						// Set message assertion
						(tipoMaterialeSaveRes.body.message).should.match('Please fill Tipo materiale name');
						
						// Handle Tipo materiale save error
						done(tipoMaterialeSaveErr);
					});
			});
	});

	it('should be able to update Tipo materiale instance if signed in', function(done) {
		agent.post('/auth/signin')
			.send(credentials)
			.expect(200)
			.end(function(signinErr, signinRes) {
				// Handle signin error
				if (signinErr) done(signinErr);

				// Get the userId
				var userId = user.id;

				// Save a new Tipo materiale
				agent.post('/tipo-materiales')
					.send(tipoMateriale)
					.expect(200)
					.end(function(tipoMaterialeSaveErr, tipoMaterialeSaveRes) {
						// Handle Tipo materiale save error
						if (tipoMaterialeSaveErr) done(tipoMaterialeSaveErr);

						// Update Tipo materiale name
						tipoMateriale.name = 'WHY YOU GOTTA BE SO MEAN?';

						// Update existing Tipo materiale
						agent.put('/tipo-materiales/' + tipoMaterialeSaveRes.body._id)
							.send(tipoMateriale)
							.expect(200)
							.end(function(tipoMaterialeUpdateErr, tipoMaterialeUpdateRes) {
								// Handle Tipo materiale update error
								if (tipoMaterialeUpdateErr) done(tipoMaterialeUpdateErr);

								// Set assertions
								(tipoMaterialeUpdateRes.body._id).should.equal(tipoMaterialeSaveRes.body._id);
								(tipoMaterialeUpdateRes.body.name).should.match('WHY YOU GOTTA BE SO MEAN?');

								// Call the assertion callback
								done();
							});
					});
			});
	});

	it('should be able to get a list of Tipo materiales if not signed in', function(done) {
		// Create new Tipo materiale model instance
		var tipoMaterialeObj = new TipoMateriale(tipoMateriale);

		// Save the Tipo materiale
		tipoMaterialeObj.save(function() {
			// Request Tipo materiales
			request(app).get('/tipo-materiales')
				.end(function(req, res) {
					// Set assertion
					res.body.should.be.an.Array.with.lengthOf(1);

					// Call the assertion callback
					done();
				});

		});
	});


	it('should be able to get a single Tipo materiale if not signed in', function(done) {
		// Create new Tipo materiale model instance
		var tipoMaterialeObj = new TipoMateriale(tipoMateriale);

		// Save the Tipo materiale
		tipoMaterialeObj.save(function() {
			request(app).get('/tipo-materiales/' + tipoMaterialeObj._id)
				.end(function(req, res) {
					// Set assertion
					res.body.should.be.an.Object.with.property('name', tipoMateriale.name);

					// Call the assertion callback
					done();
				});
		});
	});

	it('should be able to delete Tipo materiale instance if signed in', function(done) {
		agent.post('/auth/signin')
			.send(credentials)
			.expect(200)
			.end(function(signinErr, signinRes) {
				// Handle signin error
				if (signinErr) done(signinErr);

				// Get the userId
				var userId = user.id;

				// Save a new Tipo materiale
				agent.post('/tipo-materiales')
					.send(tipoMateriale)
					.expect(200)
					.end(function(tipoMaterialeSaveErr, tipoMaterialeSaveRes) {
						// Handle Tipo materiale save error
						if (tipoMaterialeSaveErr) done(tipoMaterialeSaveErr);

						// Delete existing Tipo materiale
						agent.delete('/tipo-materiales/' + tipoMaterialeSaveRes.body._id)
							.send(tipoMateriale)
							.expect(200)
							.end(function(tipoMaterialeDeleteErr, tipoMaterialeDeleteRes) {
								// Handle Tipo materiale error error
								if (tipoMaterialeDeleteErr) done(tipoMaterialeDeleteErr);

								// Set assertions
								(tipoMaterialeDeleteRes.body._id).should.equal(tipoMaterialeSaveRes.body._id);

								// Call the assertion callback
								done();
							});
					});
			});
	});

	it('should not be able to delete Tipo materiale instance if not signed in', function(done) {
		// Set Tipo materiale user 
		tipoMateriale.user = user;

		// Create new Tipo materiale model instance
		var tipoMaterialeObj = new TipoMateriale(tipoMateriale);

		// Save the Tipo materiale
		tipoMaterialeObj.save(function() {
			// Try deleting Tipo materiale
			request(app).delete('/tipo-materiales/' + tipoMaterialeObj._id)
			.expect(401)
			.end(function(tipoMaterialeDeleteErr, tipoMaterialeDeleteRes) {
				// Set message assertion
				(tipoMaterialeDeleteRes.body.message).should.match('User is not logged in');

				// Handle Tipo materiale error error
				done(tipoMaterialeDeleteErr);
			});

		});
	});

	afterEach(function(done) {
		User.remove().exec();
		TipoMateriale.remove().exec();
		done();
	});
});