Ext.define('WMC.model.Transaction', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'int'
	}, {
		name : 'enteredBy',
		type : 'string'
	}, {
		name : 'approvedBy',
		type : 'string'
	}, {
		name : 'enteredDate',
		type : 'string'
	}, {
		name : 'businessArea',
		type : 'string'
	}, {
		name : 'reasonType',
		type : 'string'
	}, {
		name : 'status',
		type : 'string'
	}, {
		name : 'keywords',
		type : 'string'
	} ]
});
