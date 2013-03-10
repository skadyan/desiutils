Ext.define('WMC.store.SearchResults', {
	extend : 'Ext.data.Store',
	model : 'WMC.model.Transaction',
	autoLoad : false,
	remoteFilter : true,
	proxy : {
		type : 'ajax',
		url : 'data/searchTransaction.json',
		reader : {
			type : 'json',
			root : 'transactions',
			successProperty : 'success',
			totalProperty : 'total',
		}
	},
});
