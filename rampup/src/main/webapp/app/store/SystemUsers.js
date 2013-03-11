Ext.define('WMC.store.SystemUsers', {
	extend : 'Ext.data.Store',
	fields : [ 'username', 'displayName' ],
	autoLoad : true,
	remoteFilter : false,
	proxy : {
		type : 'ajax',
		url : 'data/reteriveSystemUsers.json',
		reader : {
			type : 'json',
			root : 'users',
			successProperty : 'success',
			totalProperty : 'total',
		}
	},
});