package types.api.search;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import types.api.APITestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetSearchLocationTest extends APITestBase {
    private String route_api;
    private static Response response;

    @BeforeMethod
    void setup_Route() {
        route_api = "/location/search/";
    }

    @Test(dataProvider = "validQueryParam")
    void verifySuccessResponse(String name) {
        Log.info("Verify /search api GET SUCCESS with query = " + name);
        response = given().param("query", name)
                .when().get(route_api)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].title", equalTo("Berlin")
                        , "[0].location_type", equalTo("City")
                        , "[0].woeid", equalTo(638242)
                        , "[0].latt_long", equalTo("52.516071,13.376980"))
                .extract().response();
    }

    @Test(dataProvider = "inValidQueryParam")
    void verifyEmptyBodyResponse(Object name) {
        Log.info("Verify /search api GET SUCCESS with Empty body with query = " + name);
        response = given().param("query", name)
                .when().get(route_api)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", Matchers.hasSize(0))
                .extract().response();
    }

    @Test
    void verifyForbiddenResponse() {
        Log.info("Verify /search api GET FORBIDDEN without query param");
        response = given()
                .when().get(route_api)
                .then()
                .statusCode(403)
                .extract().response();
    }

    @DataProvider(name = "validQueryParam")
    public Object[][] getValidQueryParam() {
        return new Object[][] {{"Berlin"},{"bErLiN"}};
    }

    @DataProvider(name = "inValidQueryParam")
    public Object[][] getInValidQueryParam() {
        return new Object[][] {{"1234"},{"!@#"},{"Ber lin"}, {true}};
    }
}
