package base;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import utils.ResponseUtils;

public class CommonChecks {
    public static void statusCodeOK(Response response){
        Assertions.assertEquals(200, response.getStatusCode(), "Wrong status code");
    }

    public static <T> void responseBodyIsEqualTo(Response response, T expectedObject, Class<T> clazz){
        T actualObject = ResponseUtils.extractBodyTo(response, clazz);
        Assertions.assertEquals(expectedObject, actualObject);
    }
}
