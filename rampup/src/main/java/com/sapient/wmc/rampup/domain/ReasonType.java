package com.sapient.wmc.rampup.domain;

public enum ReasonType {
	//@formatter:off
	AccountRestructure("Account Restructure"),
	BetterRate("Better Rate"),
	CounterpartyRisk("Counterparty Risk"),
	ExistingBusiness("Existing Business"),
	Expenses("Expenses"),
	Incentive("Incentive"),
	MarginConstraints("Margin Constraints"),
	NewOrLostBusiness("New/Lost Business"),
	SidePocketRebalancing("Side Pocket Rebalancing")
	//@formatter:on
	;

	private String description;

	private ReasonType(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return description;
	}
}