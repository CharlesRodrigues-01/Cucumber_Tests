Feature: Get users by parameters

Scenario: Verify one user by parameter id
  Given Exists a registered  user
  When I search this user through the "_id" parameter
  And I send a GET request to "usuarios/"
  Then status code is 200
#  And the size of list is equal to