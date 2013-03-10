package com.sapient.wmc.rampup.domain;

public enum BusinessArea {
	//@formatter:off
	BenchmarkTeam("Benchmark Team"),
	BankLoans("Bank Loans"),
	ClientIntegrationAndAdministration("Client Integration & Administration"),
	CorpActions("Corporate Actions"),
	FutureAndOptions("Futures & Options"),
	GenevaBusinessTeam("Geneva Business Team"), GTISupport("GTI Support"),
	HedgeFundAdminGroup("Hedge Fund Admin group"),
	InvestmentSupportAndLiaisonGrp("Investment Support & Liaison Grp"),
	OpServices("Op Services"),
	SLARecon("SLA Recon");
	//@formatter:on

	private String description;

	private BusinessArea(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return description;
	}
}
