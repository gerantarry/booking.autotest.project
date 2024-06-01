package httpmanager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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

    //TODO rework POST, DELETE, PUT + Add PATCH
    @Override
    public Response post(final String uri, Object body) {
        return given()
                .body(body).
                when()
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
}
