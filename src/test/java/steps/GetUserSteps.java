package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utilities.RestAssuredExtension.*;
import static utilities.UserBuilder.buildUser;

public class GetUserSteps {

    private static String userId;
    private static String param;
    private static ResponseOptions<Response> response;

    @Given("Exists a registered  user")
    public void existsRegisteredUser() {
        userId = createUser(buildUser()).thenReturn()
                                        .then()
                                        .extract()
                                        .path("_id");
    }

    @When("I search this user through the {string} parameter")
    public void searchUserForId(String id) {
        param = id;
    }

    @When("I send a GET request to {string}")
    public void I_send_a_GET_request_to(String url) {
        response = getUserById(url, param, userId);
    }

    @Then("status code is {int}")
    public void status_code_is(Integer status) {
        assertEquals(status, response.statusCode());
        deleteUserById(userId);
    }

//    @Then("the size of list is equal to")
//    public void the_size_of_list_is_equal_to() {
//        Integer amount = response.getBody().jsonPath().get("quantidade");
//        Integer sizeList = response.body().jsonPath().getList("usuarios").size();
//        assertEquals(amount, sizeList);
//    }
}
