Ext.define('WMC.view.SystemUserCombobox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.systemuserlist',
	displayField : 'displayName',
	valueField : 'username',
	queryMode : 'local',
	typeAhead : true,
	store : 'SystemUsers',
});