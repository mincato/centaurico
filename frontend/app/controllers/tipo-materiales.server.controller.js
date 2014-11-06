'use strict';

/**
 * Module dependencies.
 */
var mongoose = require('mongoose'),
	errorHandler = require('./errors.server.controller'),
	TipoMateriale = mongoose.model('TipoMateriale'),
	_ = require('lodash');

/**
 * Create a Tipo materiale
 */
exports.create = function(req, res) {
	var tipoMateriale = new TipoMateriale(req.body);
	tipoMateriale.user = req.user;

	tipoMateriale.save(function(err) {
		if (err) {
			return res.status(400).send({
				message: errorHandler.getErrorMessage(err)
			});
		} else {
			res.jsonp(tipoMateriale);
		}
	});
};

/**
 * Show the current Tipo materiale
 */
exports.read = function(req, res) {
	res.jsonp(req.tipoMateriale);
};

/**
 * Update a Tipo materiale
 */
exports.update = function(req, res) {
	var tipoMateriale = req.tipoMateriale ;

	tipoMateriale = _.extend(tipoMateriale , req.body);

	tipoMateriale.save(function(err) {
		if (err) {
			return res.status(400).send({
				message: errorHandler.getErrorMessage(err)
			});
		} else {
			res.jsonp(tipoMateriale);
		}
	});
};

/**
 * Delete an Tipo materiale
 */
exports.delete = function(req, res) {
	var tipoMateriale = req.tipoMateriale ;

	tipoMateriale.remove(function(err) {
		if (err) {
			return res.status(400).send({
				message: errorHandler.getErrorMessage(err)
			});
		} else {
			res.jsonp(tipoMateriale);
		}
	});
};

/**
 * List of Tipo materiales
 */
exports.list = function(req, res) { 
	TipoMateriale.find().sort('-created').populate('user', 'displayName').exec(function(err, tipoMateriales) {
		if (err) {
			return res.status(400).send({
				message: errorHandler.getErrorMessage(err)
			});
		} else {
			res.jsonp(tipoMateriales);
		}
	});
};

/**
 * Tipo materiale middleware
 */
exports.tipoMaterialeByID = function(req, res, next, id) { 
	TipoMateriale.findById(id).populate('user', 'displayName').exec(function(err, tipoMateriale) {
		if (err) return next(err);
		if (! tipoMateriale) return next(new Error('Failed to load Tipo materiale ' + id));
		req.tipoMateriale = tipoMateriale ;
		next();
	});
};

/**
 * Tipo materiale authorization middleware
 */
exports.hasAuthorization = function(req, res, next) {
	if (req.tipoMateriale.user.id !== req.user.id) {
		return res.status(403).send('User is not authorized');
	}
	next();
};
