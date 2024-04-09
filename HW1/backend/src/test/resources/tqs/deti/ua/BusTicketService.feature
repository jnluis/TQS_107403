Feature: Bus Ticket Service
  To allow a client to find a bus trip between cities, the service has to offer ways to search, list and book trips

  Scenario: Search for a bus trip

    Given the client wants to find a bus trip between two cities
    When the client searches for a trip from city A to city B for a given day
    Then the service should return a list of available trips

    Scenario:Buy a ticket for a trip

      Given the user landed on the Home page
      When the user searches for trips
      And selects the first trip
      And the user inputs his information
      And the user clicks on the Purchase Ticket button
      Then the user should be redirected to a confirmation page