package base.checks;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import utils.ResponseUtils;

public class CommonChecks {
    public static final String WRONG_SC = "Wrong status code";

    public static void statusCodeOK(Response response){
        Assertions.assertEquals(200, response.getStatusCode(), WRONG_SC);
    }

    public static void statusCodeCreate(Response response){
        Assertions.assertEquals(201, response.getStatusCode(), WRONG_SC);
    }

    public static void statusCodeNotFound(Response response){
        Assertions.assertEquals(404, response.getStatusCode(), WRONG_SC);
    }

    public static <T> void responseBodyIsEqualTo(Response response, T expectedObject, Class<T> clazz){
        T actualObject = ResponseUtils.extractBodyTo(response, clazz);
        Assertions.assertEquals(expectedObject, actualObject);
    }
}
