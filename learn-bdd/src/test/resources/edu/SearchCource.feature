Feature: Search courses
  In order to ensure better utilization of courses
  Potential students should be able to search for courses

  Scenario: Search by topic
    Given there are 240 courses where neither has the topic "biology" 
    And there are 3 courses A,B,C that each have "biology" as one of the topics
    When I search for "biology" 
    Then I should see a the following courses:
      | title |
      | A     |
      | B     |
      | C     |