Ext.define('WMC.view.SearchTransaction', {
	requires : [ 'WMC.view.SearchTransactionPanel', 'WMC.view.SearchTransactionForm'],
	extend : 'Ext.container.Container',
	alias : 'widget.searchtransaction',
	layout : {
		type : 'vbox',
	},
	border : 1,
	defaults : {
		width : '100%'
	},
	style : {
		borderColor : '#000000',
		borderStyle : 'solid',
		borderWidth : '1px'
	},

	items : [ {
		xtype : 'searchtransactionform',
		collapsible : true,
	}, {
		xtype : 'searchtransactionpanel',
		collapsible : true,
	} ],


});
