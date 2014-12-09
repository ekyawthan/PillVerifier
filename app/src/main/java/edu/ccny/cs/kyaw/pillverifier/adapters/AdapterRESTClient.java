package edu.ccny.cs.kyaw.pillverifier.adapters;

/**
 * Created by kyawthan on 12/6/14.
 */
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;

/**
 * Created by kyawthan on 11/16/14.
 */
public class AdapterRESTClient {
    private static final int DEFAULT_TIMEOUT = 30 * 1000;
    private static String BASE_URL = "http://pillbox.nlm.nih.gov/PHP/pillboxAPIService.php";

    public static AsyncHttpClient client = new AsyncHttpClient();


    public static void getRequestParams(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteUrl(url), params, handler);
    }

    public static void postWithRequestParams(String url, final RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postWithStringEntity(String url, StringEntity entity, AsyncHttpResponseHandler responseHandler) {
        client.post(null, url, entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String url) {
        return BASE_URL + url;
    }
}