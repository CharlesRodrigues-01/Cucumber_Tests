package userSteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import model.User;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utilities.RestAssuredExtension.*;
import static utilities.UserBuilder.buildUser;


public class UserOperationsSteps {

    private static ResponseOptions<Response> userCreated;
    private static String userId;
    private static String param;
    private static String paramValue;
    private static ResponseOptions<Response> response;
    private static Map<String, String> rows;
    private static User user;

    @Given("Exists a registered user")
    public void existsRegisteredUser() {
       userCreated = createUser(buildUser());
    }

    @When("I search this user for id")
    public void searchUserForId() {
       userId = userCreated.thenReturn()
               .then()
               .extract()
               .path("_id");
    }

    @When("I perform a GET request to {string}")
    public void performGetRequestTo(String url) {
        response = findUserById(url, userId);
    }

    @Then("I should see a response with the pairs")
    public void shouldSeeResponseWithThePairs(DataTable dataTable) {
        rows = dataTable.asMap(String.class, String.class);

        for(Map.Entry<String, String> columns : rows.entrySet()) {
            assertEquals(columns.getValue(), response.body().jsonPath().get(columns.getKey()));
        }
    }

    @When("I search this user through the parameter {string}")
    public void searchUserForId(String parameter) {
        param = parameter;
    }

    @When("The value of parameter is {string}")
    public void theValueOfParameterIs(String value) {
        paramValue = value;
    }

    @When("I send a GET request to {string}")
    public void sendGETRequestTo(String url) {
        response = getUserByParam(url, param, paramValue);
    }

    @Then("the size of list is equal to {string}")
    public void theSizeOfListEqualTo(String quantidade) {
        Integer getAmount = response.getBody().jsonPath().get(quantidade);
        Integer sizeList = response.body().jsonPath().getList("usuarios").size();
        assertEquals(getAmount, sizeList);
        deleteUserById(userId);
    }

    @Given("I build a user")
    public void buildAUser(DataTable dataTable) {
        rows = dataTable.asMap(String.class, String.class);
        user = new User(rows.get("nome"), rows.get("email"), rows.get("password"), rows.get("administrador"));
    }

    @When("I perform a POST request with the built user")
    public void performPOSTRequestWithTheBuiltUser() {
        response = createUser(user);
        userId = response.thenReturn()
                .then()
                .extract()
                .path("_id");
        deleteUserById(userId);
    }

    @Then("Status code should be {int}")
    public void statusCodeShouldBe(Integer statusCode) {
        assertEquals(statusCode, response.statusCode());
    }

    @Then("I should see a response with the message {string}")
    public void shouldSeeResponseWithTheMessage(String message) {
       assertEquals(message, response.body().jsonPath().get("message"));
    }
}
