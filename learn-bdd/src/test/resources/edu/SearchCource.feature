Feature: Take Driver Contact Details

  In order to have the maximum pool of driver, we need to collect the driver 
  information online. 

 Scenario: Successful Submission
    Given A user is on contact detail page 
	And fill following information
     | Field	  | Value		|
     | First Name | Hony 		| 
     | Last Name  | Singh 		|
     | DL Number  | HR-06-100001| 
    When Submit the page
    Then System register the request and generate the reference number.

      