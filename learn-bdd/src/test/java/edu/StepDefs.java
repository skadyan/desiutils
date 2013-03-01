package edu;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import cucumber.runtime.Runtime;
import cucumber.table.DataTable;

public class StepDefs {

	@Given("^A user is on contact detail page$")
	public void A_user_is_on_contact_detail_page() throws Throwable {
			
	}

	@Given("^fill following information$")
	public void fill_following_information(DataTable arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		// For automatic conversion, change DataTable to List<YourType>
		throw new PendingException();
	}

	@When("^Submit the page$")
	public void Submit_the_page() throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@Then("^System register the request and generate the reference number.$")
	public void System_register_the_request_and_generate_the_reference_number()
			throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

}
