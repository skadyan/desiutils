Ext.define('WMC.controller.SearchTransactions', {
	extend : 'Ext.app.Controller',

	views : [ 'SearchTransaction' ],
	stores : [ 'SearchResults' ],
	models : [ 'Transaction' ],

	init : function() {
		this.control({
			'viewport > panel' : {
				render : this.onPanelRendered
			},
			'searchtransactionform button[action=search]' : {
				click : this.searchTransaction
			},

		});
	},

	searchTransaction : function(button) {
		console.log('clicked the Search Transaction button');
		var values = button.up('form').getForm().getFieldValues();
		var store = this.getSearchResultsStore();
		store.clearFilter(true);

		var filters = [];
		for (p in values) {
			var value = values[p];
			if (!Ext.isEmpty(value)) {
				filters.push({
					property : p,
					value : value
				});
			}
		}
		
		store.filter(filters);
		
		console.log('fitler values ' + filters + ' need to applied on ' + store);

	},

	onPanelRendered : function() {
		console.log('The panel was rendered');
	}
});
