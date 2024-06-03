package base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import httpmanager.HttpManager;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;

import java.lang.reflect.Type;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

public abstract class TestBase {
    protected HttpManager httpManager;

    //TODO enable logging
    {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(objectMapperConfig().gsonObjectMapperFactory(
                new GsonObjectMapperFactory() {
                    @Override
                    public Gson create(Type type, String s) {
                        return new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
                    }
                }
        ));

        httpManager = new HttpManager(System.getProperty("host.test"));
    }
}
