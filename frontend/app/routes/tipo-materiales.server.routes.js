'use strict';

module.exports = function(app) {
	var users = require('../../app/controllers/users.server.controller');
	var tipoMateriales = require('../../app/controllers/tipo-materiales.server.controller');

	// Tipo materiales Routes
	app.route('/tipo-materiales')
		.get(tipoMateriales.list)
		.post(users.requiresLogin, tipoMateriales.create);

	app.route('/tipo-materiales/:tipoMaterialeId')
		.get(tipoMateriales.read)
		.put(users.requiresLogin, tipoMateriales.hasAuthorization, tipoMateriales.update)
		.delete(users.requiresLogin, tipoMateriales.hasAuthorization, tipoMateriales.delete);

	// Finish by binding the Tipo materiale middleware
	app.param('tipoMaterialeId', tipoMateriales.tipoMaterialeByID);
};
