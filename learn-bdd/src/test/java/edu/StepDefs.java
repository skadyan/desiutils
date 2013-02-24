package edu;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import cucumber.table.DataTable;

public class StepDefs {

	@Given("^there are (\\d+) courses where neither has the topic \"([^\"]*)\"$")
	public void there_are_courses_where_neither_has_the_topic(int arg1, String arg2) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@Given("^there are (\\d+) courses A,B,C that each have \"([^\"]*)\" as one of the topics$")
	public void there_are_courses_A_B_C_that_each_have_as_one_of_the_topics(int arg1, String arg2) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@When("^I search for \"([^\"]*)\"$")
	public void I_search_for(String arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@Then("^I should see a the following courses:$")
	public void I_should_see_a_the_following_courses(DataTable arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		// For automatic conversion, change DataTable to List<YourType>
		throw new PendingException();
	}

}
