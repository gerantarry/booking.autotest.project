package httpmanager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.*;


public class HttpManager implements IHttpManager {

    public HttpManager(final String baseUri) {
        baseURI = baseUri;
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setSessionId("Autotest-" + UUID.randomUUID())
                .build();
    }

    @Override
    public Response get(final String uri) {
        return get(uri, new HashMap<>(), new HashMap<>());
    }

    public Response get(final String uri,
                        final Map<String, Object> queries,
                        final Map<String, Object> pathParams) {
        return given()
                .queryParams(queries)
                .pathParams(pathParams)
                .get(uri)
                .thenReturn();
    }

    @Override
    public Response post(final String uri, final Object body) {
        return given()
                .body(body)
                .when()
                .post(uri)
                .thenReturn();
    }

    @Override
    public Response delete(final String uri) {
        return when()
                .delete(uri)
                .thenReturn();
    }

    @Override
    public Response put(final String uri) {
        return when()
                .put(uri)
                .thenReturn();
    }

    @Override
    public Response patch(final String uri, Object partialBody) {
        return given()
                .body(partialBody)
                .when()
                .post(uri)
                .thenReturn();
    }
}
