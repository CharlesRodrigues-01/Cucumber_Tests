Feature: Operations with user

  Scenario: Should find a user by ID
    Given Exists a registered user
    When I search this user for id
    And I perform a GET request to "usuarios/"
    Then Status code should be 200
    And I should see a response with the pairs
    |nome         |Authorized User     |
    |email        |authorized@qa.com.br|
    |password     |12test              |
    |administrador|true                |

  Scenario: Should verify one user by parameter nome
    When I search this user through the parameter "nome"
    And The value of parameter is "Authorized User"
    And I send a GET request to "usuarios/"
    Then Status code should be 200
    And the size of list is equal to "quantidade"

  Scenario: Should create a user
    Given I build a user
      |nome         |Another User     |
      |email        |another@qa.com.br|
      |password     |12test              |
      |administrador|true                |
    When I perform a POST request with the built user
    Then Status code should be 201
    And I should see a response with the message "Cadastro realizado com sucesso"