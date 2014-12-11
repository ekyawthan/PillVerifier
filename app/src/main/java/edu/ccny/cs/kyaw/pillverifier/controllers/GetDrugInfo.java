package edu.ccny.cs.kyaw.pillverifier.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


import java.io.Serializable;


import edu.ccny.cs.kyaw.pillverifier.R;
import edu.ccny.cs.kyaw.pillverifier.adapters.AdapterRESTClient;
import edu.ccny.cs.kyaw.pillverifier.adapters.ConstantHelp;
import edu.ccny.cs.kyaw.pillverifier.interfaces.InterfaceGetDrugInfo;
import edu.ccny.cs.kyaw.pillverifier.scanner.IntentIntegrator;


/**
 * Created by kyawthan on 11/17/14.
 */
public class GetDrugInfo implements Serializable{

    private Context context;
    private InterfaceGetDrugInfo interfaceGetDrugInfo;
    private String TAG = "GetDrugInfo";

    private ProgressDialog progressDialog;


    // manual entry
    /*
    @param context is the context of application call from
    @param intefaceGetDrugInfo is callback to main thread
     */
    public GetDrugInfo(Context context, InterfaceGetDrugInfo interfaceGetDrugInfo,
                       String imprint, String color, String shape){
        this.context = context;
        this.interfaceGetDrugInfo = interfaceGetDrugInfo;

        showProgressDialog();
        shouldSerializedData(ConstantHelp.API_KEY, imprint);

    }

    // scan constructor
    public GetDrugInfo(Context context, InterfaceGetDrugInfo interfaceGetDrugInfo, String ndc){

        this.context = context;
        this.interfaceGetDrugInfo = interfaceGetDrugInfo;
        shouldSerializedDataForScan(ConstantHelp.API_KEY, ndc);

    }

    private void shouldSerializedDataForScan(String apiKey, String ndc) {
        RequestParams params = new RequestParams();
        params.add("prodcode", ndc);
        params.add("key", apiKey);
        shouldRequestInfo(params);
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
                //Log.e(TAG, new String(bytes));
                interfaceGetDrugInfo.didSucceed(new String(bytes));
                dismissProgressDialog();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                interfaceGetDrugInfo.didFailed(new String(bytes));
                dismissProgressDialog();

            }
        });
    }


    public void showProgressDialog(){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Submitting Query ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIcon(R.drawable.customprogressbar);

        progressDialog.show();
    }
    public void dismissProgressDialog(){
        progressDialog.dismiss();
    }

}

