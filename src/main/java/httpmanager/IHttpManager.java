package httpmanager;

import io.restassured.response.Response;

public interface IHttpManager {

    Response get(String uri);

    Response post(String uri, Object body);

    Response delete(String uri, Object paramValue);

    Response put(String uri);

    Response patch(String uri, Object partialBody);


}
