Feature: New Address

  Scenario Outline: Successfully adding new address to the existing user
    Given An browser with opened https://prod-kurs.coderslab.pl/index.php page
    And User is logged in
    When User clicked an "Addresses" button
    And User clicked an "Create new address" button
    And Form is filled with with data: <alias>, <address>, <city>, <postcode>, <country>, <phone> and submitted
    Then "Address successfully added!" message is shown
    And Close browser
    Examples:
    |alias|address|city|postcode|country|phone|
    |Freeman|65 Bootham Terrace|RAVENSTONE|LE67 1PS|United Kingdom|07915568473|