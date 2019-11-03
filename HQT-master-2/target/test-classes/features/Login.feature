@Login
Feature: Register user

  Scenario Outline: Validate login functionality
    Given user hits URL
    And clicks on "SignIn"
    When user enters "login credentials"
      | Email    | <email address> |
      | Password | <Password>      |
    Then user account is created
    Examples:
      | TC_ID | email address          | Password    |
      | 01    | testdl99seven@test.com | password123 |
      | 02    | testdl99eight@test.com | password123 |
