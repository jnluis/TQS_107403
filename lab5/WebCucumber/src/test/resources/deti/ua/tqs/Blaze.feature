@blaze_demo
Feature: Buy a Flight

  Scenario: Search books by publication year
    Given I am on https://www.blazedemo.com/ home page
    When I write/select "Paris" on the "fromPort" input
    And I write/select "Rome" on the "toPort" input
    And I click "Find Flights"
    Then I should be redirected to a page with the title "BlazeDemo - reserve"
    When I click Choose This Flight on flight 234
    Then I should be redirected to a page with the title "BlazeDemo Purchase"
    When I write/select "Marcolino" on the "inputName" input
    And I write/select "Rua da Guerra" on the "address" input
    And I write/select "Esgueira" on the "city" input
    And I write/select "Aveiro" on the "state" input
    And I write/select "3800-587" on the "zipCode" input
    And I write/select "American Express" on the "cardType" input
    And I write/select "123456789" on the "creditCardNumber" input
    And I write/select "10" on the "creditCardMonth" input
    And I write/select "2030" on the "creditCardYear" input
    And I write/select "Marcolino" on the "nameOnCard" input
    And I click "Purchase Flight"
    Then I should be redirected to a page with the title "BlazeDemo Confirmation"