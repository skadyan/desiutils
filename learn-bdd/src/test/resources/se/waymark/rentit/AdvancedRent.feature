Feature: Rental cars should be possible to rent to gain revenue to the rental company in longer term.

  As an owner of a car rental company
  I want to make cars available for renting
  So I can make money

  Scenario: Find and rent a car
    Given there are 10 cars available for rental
    When I rent one
    Then there will only be 9 cars available for rental

  Scenario: Find and rent a car
	Given there are 5 cars available for rental
	When I rent one
	Then there will only be 4 cars available for rental
			    