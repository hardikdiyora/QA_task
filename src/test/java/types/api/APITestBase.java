package types.api;

import io.restassured.RestAssured;
import types.TestBase;
import org.testng.annotations.BeforeClass;

/***
 * Base class for API tests.
 */
public class APITestBase extends TestBase {
    String baseURI;

    @BeforeClass
    public void setUp() {
        Log.info("Setting up the Base URI for API testing");
        baseURI = "https://www.metaweather.com/api";
        RestAssured.baseURI = baseURI;
    }
}
