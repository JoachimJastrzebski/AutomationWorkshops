Feature: Making Purchase

  Scenario Outline: Making successful purchase
    Given Opened https://prod-kurs.coderslab.pl/index.php page
    And User is signed in
    When <item> is searched
    And Clicked <item> to move to its page
    And size <size> and <number> pieces are chosen
    And Products added to basket
    And Proceeded to checkout
    And Confirmed address
    And "Presta shop" option is chosen
    And Payment by check is chosen, agreed to terms of service
    Then Items were successfully ordered, "YOUR ORDER IS CONFIRMED" message has popped up
    And Taken screenshot
    And closed browser

    Examples:
    |item|size|number|
    |Hummingbird printed sweater|L|5|