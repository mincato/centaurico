'use strict';

/**
 * Module dependencies.
 */
var mongoose = require('mongoose'),
	Schema = mongoose.Schema;

/**
 * Tipo materiale Schema
 */
var TipoMaterialeSchema = new Schema({
	name: {
		type: String,
		default: '',
		required: 'Please fill Tipo materiale name',
		trim: true
	},
	created: {
		type: Date,
		default: Date.now
	},
	user: {
		type: Schema.ObjectId,
		ref: 'User'
	}
});

mongoose.model('TipoMateriale', TipoMaterialeSchema);