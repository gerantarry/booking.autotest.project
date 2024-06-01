import httpmanager.HttpManager;

public abstract class TestBase {
    protected HttpManager httpManager;
//TODO enable logging
    {
        httpManager = new HttpManager(System.getProperty("host.test"));
    }
}
