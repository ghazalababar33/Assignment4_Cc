Feature: Weather Shopper Tests

  Scenario: Add two moisturizers to the cart
    Given I am on the Weather Shopper homepage
    When I check the temperature
    And I shop for moisturizers if the temperature is below 19
    Then I should be on the moisturizers page
    When I add the least expensive moisturizer containing Aloe to the cart
    And I add the least expensive moisturizer containing Almond to the cart
    Then the cart should contain two moisturizers
    When I verify the items in the cart
    And I proceed to checkout with Stripe test card numbers
    Then the payment should be successful or fail by design

  Scenario: Add two sunscreens to the cart
    Given I am on the Weather Shopper homepage
    When I check the temperature
    And I shop for sunscreens if the temperature is above 34
    Then I should be on the sunscreens page
    When I add the least expensive sunscreen with SPF 50 to the cart
    And I add the least expensive sunscreen with SPF 30 to the cart
    Then the cart should contain two sunscreens
    When I verify the items in the cart
    And I proceed to checkout with Stripe test card numbers
    Then the payment should be successful or fail by design



