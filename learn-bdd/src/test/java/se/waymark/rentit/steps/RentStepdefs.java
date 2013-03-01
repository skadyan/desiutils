package se.waymark.rentit.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class RentStepdefs {
	private RentACarSupport rentACarSupport = new RentACarSupport();

	public RentStepdefs() {
		System.out.println("RentStepdefs.RentStepdefs()");
	}
	
	@cucumber.annotation.Before
	public void before() {
		System.out.println("RentStepdefs.before() on " + System.identityHashCode(this));
	}

	@cucumber.annotation.After
	public void after() {
		System.out.println("RentStepdefs.after() on " + System.identityHashCode(this));
	}

	@Given("^there are (\\d+) cars available for rental$")
	public void thereAreCarsAvailableForRental(int availableCars) throws Throwable {
		System.out.println("RentStepdefs.thereAreCarsAvailableForRental()");
		rentACarSupport.createCars(availableCars);
	}

	@When("^I rent one$")
	public void rent_one_car() throws Throwable {
		System.out.println("RentStepdefs.rent_one_car()");
		rentACarSupport.rentACar();
	}

	@Then("^there will only be (\\d+) cars available for rental$")
	public void there_will_be_less_cars_available_for_rental(int expectedAvailableCars) throws Throwable {
		int actualAvailableCars = rentACarSupport.getAvailableNumberOfCars();
		assertThat(actualAvailableCars, is(expectedAvailableCars));
	}
}