package httpmanager;

import dto.requests.CreateTokenRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static constants.UriConstants.AUTH;
import static io.restassured.RestAssured.*;


public class HttpManager implements IHttpManager {

    public HttpManager(final String baseUri) {
        baseURI = baseUri;
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setSessionId("Autotest-" + UUID.randomUUID())
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    public void setCookies(String cookieName, String cookieValue){
        requestSpecification = requestSpecification.cookie(new Cookie.Builder(cookieName, cookieValue).build());
    }

    public void setHeaders(Map<String, Object> headers){
        requestSpecification = requestSpecification.headers(headers);
    }

    public void setHeaders(String key, String value){
        HashMap<String, String> headers = new HashMap<>();
        headers.put(key, value);
        requestSpecification = requestSpecification.headers(headers);
    }

    @Override
    public Response get(final String uri) {
        return get(uri, new HashMap<>(), new HashMap<>());
    }

    public Response get(final String uri,
                         Map<String, Object> queries,
                         Map<String, Object> pathParams) {

        if (queries == null){
            queries = new HashMap<>();
        }
        if (pathParams == null){
            pathParams = new HashMap<>();
        }
        return given()
                .queryParams(queries)
                .pathParams(pathParams)
                .get(uri)
                .thenReturn();
    }

    public Response authorize(){
        CreateTokenRequest createTokenBody = CreateTokenRequest.builder().username("admin").password("password123").build();
        return this.post(AUTH, createTokenBody);
    }

    public Response get(final String uri, Object paramValue) {
        return given()
                .pathParam("id", paramValue)
                .when()
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
    public Response delete(final String uri, Object paramValue) {
        return given()
                .pathParam("id", paramValue)
                .when()
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
