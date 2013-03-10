Ext.define('WMC.view.SearchTransactionForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.searchtransactionform',
	title : 'Search Transaction',

	// The fields
	defaultType : 'textfield',
	items : [ {
		fieldLabel : 'Set Id',
		name : 'id',
	}, {
		fieldLabel : 'Business Area',
		name : 'businessArea',
		xtype : 'combobox',
	}, {
		fieldLabel : 'Reason Type',
		name : 'reasonType',
		xtype : 'combobox',
	}, {
		fieldLabel : 'Entered By',
		name : 'enteredBy',
		xtype : 'combobox',
	}, {
		fieldLabel : 'Approved By',
		name : 'approvedBy',
		xtype : 'combobox',
	}, {
		fieldLabel : 'Date Entered',
		name : 'dateEntered',
		xtype : 'datefield',
	}, {
		fieldLabel : 'Keywords',
		name : 'keywords',
	}, ],

	// Reset and Submit buttons
	buttons : [ {
		text : 'Clear',
		handler : function() {
			this.up('form').getForm().reset();
		}
	}, {
		text : 'Search',
		action : 'search'
	} ],
});
