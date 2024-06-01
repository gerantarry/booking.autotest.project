package utils;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class ResponseUtils {

    public static <T> T extractBodyTo(Response response, T variable){
        return response.getBody().as(new TypeRef<T>() {});
    }

}
