package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.List;

public class ResponseUtils {

    public static <T> T extractBodyTo(Response response, Class<T> variable){
        return response.getBody().as(variable);
    }

    public static <T> List<T> extractBodyAsList(Response response, Class<T> clazz){
        Gson gson = new Gson();
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(response.getBody().asString(), listType);
    }

}
