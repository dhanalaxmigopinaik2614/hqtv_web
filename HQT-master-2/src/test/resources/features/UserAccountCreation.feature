@Register
Feature: Register user

  Scenario Outline: Validate user account creation functionality
    Given User opens URL
    And User clicks on "SignIn"
    When User enters user details
      | TC_ID         | <TC_ID>         |
      | email address | <email address> |
    And User validates and clicks "CreateAccount"
    Then User is navigated to "Create An Account" page
    When User enters "personal information"
      | Title       | <Title>         |
      | FirstName   | <FirstName>     |
      | LastName    | <LastName>      |
      | Email       | <email address> |
      | Password    | <Password>      |
      | DateOfBirth | <DateOfBirth>   |
    And User enters "address"
      | Company        | <Company>        |
      | Address        | <Address>        |
      | City           | <City>           |
      | State          | <State>          |
      | PostalCode     | <PostalCode>     |
      | Country        | <Country>        |
      | AdditionalInfo | <AdditionalInfo> |
      | MobilePh       | <MobilePh>       |
      | AliasAddress   | <AliasAddress>   |
    And User clicks on Register
    Then Validate user account is created
    Examples:
      | TC_ID | email address | Title | FirstName     | LastName      | Password    | DateOfBirth     | Company     | Address                   | City      | State  | PostalCode | Country       | AdditionalInfo | MobilePh | AliasAddress |
      | 01    | AUTO          | Mr    | testdleightFN | testdleightLN | password123 | 11/January/1980 | testCompany | pasir ris grove,143,dnest | Singapore | Alaska | 00000      | United States | testing        | 99991234 | dnest        |