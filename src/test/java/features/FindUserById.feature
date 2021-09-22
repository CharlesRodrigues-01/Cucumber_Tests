Feature: Find User by Id

  Scenario: Verify one user by id
    Given Exists a registered user
    When I search this user for id
    And I perform a GET request to "usuarios/"
    Then status code should be 200
    And I should see a response with the pairs
    |nome         |Authorized User     |
    |email        |authorized@qa.com.br|
    |password     |12test              |
    |administrador|true                |
