Ext.application({
	name : 'WMC',
	appFolder : 'app',
	controllers : [ 'SearchTransactions' ],

	launch : function() {
		Ext.create('Ext.container.Viewport', {
			items : [ {
				xtype : 'searchtransaction'
			} ],
		});
	}
});
