package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utilities.RestAssuredExtension.*;
import static utilities.UserBuilder.buildUser;


public class FindUserByIdSteps{

    private static ResponseOptions<Response> userCreated;
    private static String userId;
    private static ResponseOptions<Response> response;

    @Given("Exists a registered user")
    public void existsRegisteredUser() {
       userCreated = createUser(buildUser());
    }

    @When("I search this user for id")
    public void searchUserForId() {
       userId = userCreated.thenReturn()
               .then()
               .extract()
               .path("_id");;
    }

    @When("I perform a GET request to {string}")
    public void performGetRequestTo(String url) {
        response = findUserById(url, userId);
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(Integer statusCode) {
        assertEquals(statusCode ,response.statusCode());
    }

    @Then("I should see a response with the pairs")
    public void i_should_see_a_response_with_the_pairs(DataTable dataTable) {
        Map<String, String> rows = dataTable.asMap(String.class, String.class);

        for(Map.Entry<String, String> columns : rows.entrySet()) {
            assertEquals(columns.getValue(), response.body().jsonPath().get(columns.getKey()));
        }
        deleteUserById(userId);
    }

}
