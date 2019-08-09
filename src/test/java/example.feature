Feature: Test login page

  Scenario: Test positive login page
    Given I am on the main application page
    When I login as correct user
    Then I see error message

  Scenario: failed example
    Given I am on the main application page
    Then I fail

  Scenario: passed example
    Given I am on the main application page
    Then I pass

  Scenario: pending example
    Given I am on the main application page
    Then I am not implemented