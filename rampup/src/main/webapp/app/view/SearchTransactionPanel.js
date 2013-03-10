Ext.define('WMC.view.SearchTransactionPanel', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.searchtransactionpanel',
	title : 'Transaction Results',
	store : 'SearchResults',
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'SearchResults', // same store GridPanel is using
		dock : 'bottom',
		displayInfo : true
	} ],

	initComponent : function() {
		this.columns = [ {
			header : 'Status',
			dataIndex : 'status',
			flex : 1
		}, {
			header : 'Entered By',
			dataIndex : 'enteredBy',
			flex : 1
		}, {
			header : 'Approved By',
			dataIndex : 'approvedBy',
			flex : 1
		}, {
			header : 'Date Entered',
			dataIndex : 'enteredDate',
			flex : 1
		}, {
			header : 'Keywords',
			dataIndex : 'keywords',
			flex : 1
		}, {
			header : 'Set ID',
			dataIndex : 'id',
			flex : 1
		}, {
			header : 'Business Area',
			dataIndex : 'businessArea',
			flex : 1
		}, {
			header : 'Reason Type',
			dataIndex : 'reasonType',
			flex : 1
		} ];

		this.callParent(arguments);
	}
});
