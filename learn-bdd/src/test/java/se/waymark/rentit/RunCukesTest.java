package se.waymark.rentit;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;
import cucumber.junit.Cucumber.Options;

@RunWith(Cucumber.class)
@Options(format = { "json:target/cucumber.json" }, features = {
		"classpath:se/waymark/rentit/Rent.feature",
		"classpath:se/waymark/rentit/AdvancedRent.feature" })
public class RunCukesTest {
}