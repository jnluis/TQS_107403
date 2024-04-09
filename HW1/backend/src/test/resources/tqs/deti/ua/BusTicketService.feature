Feature: Bus Ticket Service
  To allow a client to find a bus trip between cities, the service has to offer ways to search, list and book trips

    Scenario:Buy a ticket for a trip

      Given the user landed on the Home page
      When the user searches for trips, clicking on the Search button
      And selects the first trip
      And the user inputs his information
      And the user clicks on the Purchase Ticket button
      Then the user should be redirected to a confirmation page