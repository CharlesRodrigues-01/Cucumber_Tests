package utilities;

import Model.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class RestAssuredExtension {

    public static RequestSpecification request;

    public RestAssuredExtension() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("https://serverest.dev/");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        request = given().spec(requestSpec);
    }

    public static ResponseOptions<Response> getUserById(String url, String path, String param){
        request.param(path, param);
        try {
            return request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ResponseOptions<Response> findUserById(String url, String id) {
        try {
            return request.get(new URI(url + id));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ResponseOptions<Response> createUser(User body)  {
        request.body(body);
        return request.post("usuarios");
    }
    public static void deleteUserById(String userId)  {
        request.delete("usuarios/"+userId);
    }
}
