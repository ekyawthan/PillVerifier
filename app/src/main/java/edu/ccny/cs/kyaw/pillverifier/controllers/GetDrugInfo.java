package edu.ccny.cs.kyaw.pillverifier.controllers;

import android.content.Context;
import android.util.Log;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


import java.io.Serializable;


import edu.ccny.cs.kyaw.pillverifier.adapters.AdapterRESTClient;
import edu.ccny.cs.kyaw.pillverifier.adapters.ConstantHelp;
import edu.ccny.cs.kyaw.pillverifier.interfaces.InterfaceGetDrugInfo;


/**
 * Created by kyawthan on 11/17/14.
 */
public class GetDrugInfo implements Serializable{

    private Context context;
    private InterfaceGetDrugInfo interfaceGetDrugInfo;
    private String TAG = "GetDrugInfo";

    public GetDrugInfo(Context context, InterfaceGetDrugInfo interfaceGetDrugInfo,
                       String imprint){
        this.context = context;
        this.interfaceGetDrugInfo = interfaceGetDrugInfo;

        shouldSerializedData(ConstantHelp.API_KEY, imprint);
    }

    private void shouldSerializedData(String apiKey, String imprint) {
        RequestParams params = new RequestParams();
        params.add("key", apiKey);
        params.add("imprint", imprint);
        Log.e("params", params.toString());

        shouldRequestInfo(params);
    }

    private void shouldRequestInfo(RequestParams params) {

        AdapterRESTClient.getRequestParams("?", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.e(TAG, new String(bytes));
                interfaceGetDrugInfo.didSucceed(new String(bytes));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                interfaceGetDrugInfo.didFailed(new String(bytes));

            }
        });
    }

}

