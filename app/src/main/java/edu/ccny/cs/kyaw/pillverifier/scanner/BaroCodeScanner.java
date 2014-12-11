package edu.ccny.cs.kyaw.pillverifier.scanner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.loopj.android.image.SmartImageView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import edu.ccny.cs.kyaw.pillverifier.R;
import edu.ccny.cs.kyaw.pillverifier.adapters.ConstantHelp;
import edu.ccny.cs.kyaw.pillverifier.controllers.GetDrugInfo;
import edu.ccny.cs.kyaw.pillverifier.interfaces.InterfaceGetDrugInfo;
import edu.ccny.cs.kyaw.pillverifier.model.ModelDrug;
import edu.ccny.cs.kyaw.pillverifier.parser.XMLParser;

public class BaroCodeScanner extends ActionBarActivity implements InterfaceGetDrugInfo {

    private String TAG = getClass().getSimpleName().toUpperCase();
    private  String idcNumber;

    private Toolbar toolbar;
    private Document documentElement;
    private XMLParser xmLparser;
    private ArrayList<ModelDrug> listOfDrugs;

    //view
    private SmartImageView smartImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baro_code_scanner);
        toolbar = (Toolbar) findViewById(R.id.toolbarfromScanner);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Scan NDC Number");
        }
        xmLparser = new XMLParser();
        willSetView();
        willInitScanner();
        if (listOfDrugs !=null){
            willSetResultView();
        }

    }

    private void willSetView() {
        smartImageView = (SmartImageView) findViewById(R.id.smartImageViewBarCode);

    }

    private void willSetResultView() {
        if(listOfDrugs.size()>0){
            smartImageView.setImageUrl(ConstantHelp.LargeImageBaseUrl +listOfDrugs.get(0).imageId + ".jpg");

        }


    }

    private void willInitScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

    }

    public void didSClickOnScanAgain(View view){
        willInitScanner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_baro_code_scanner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                Log.e(TAG, " returning home");
                finish();
                break;

        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent intent)
    {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(intentResult!=null)
        {
            System.out.println(intentResult.getContents());
            idcNumber = intentResult.getContents();
            new GetDrugInfo(this, this, idcNumber);

        }
    }

    @Override
    public void didSucceed(String response) {
        Log.e(TAG, response);
        if(response == null){
            Log.e(TAG, "response is null");
            return;
        }
        documentElement = xmLparser.getDomElement(response);
        listOfDrugs = new ArrayList<ModelDrug>();
        if (documentElement != null) {


            NodeList nodeList = documentElement.getElementsByTagName(ConstantHelp.KEY_PILL);

            if (nodeList == null) {
                Log.e(TAG, "Hello I am not null");
            }

            for (int i = 0; i < nodeList.getLength(); i++) {
                ModelDrug modelDrug = new ModelDrug();

                Element element = (Element) nodeList.item(i);
                if (xmLparser.getValue(element, ConstantHelp.KEY_IMAGE_ID) != "") {
                    modelDrug.splId         = xmLparser.getValue(element, ConstantHelp.KEY_SPL);
                    modelDrug.productCode   = xmLparser.getValue(element, ConstantHelp.KEY_PRODUCT_CODE);
                    modelDrug.ndc9          = xmLparser.getValue(element, ConstantHelp.KEY_NDC9);
                    modelDrug.splColor      = xmLparser.getValue(element, ConstantHelp.KEY_SPLCOLOR);
                    modelDrug.sqlImprint    = xmLparser.getValue(element, ConstantHelp.KEY_SPLIMPRINT);
                    modelDrug.splShape      = xmLparser.getValue(element, ConstantHelp.KEY_SPLSHAPE);
                    modelDrug.splSize       = xmLparser.getValue(element, ConstantHelp.KEY_SPLSIZE);
                    modelDrug.rxCui         = xmLparser.getValue(element, ConstantHelp.KEY_RXCUI);
                    modelDrug.rxTty         = xmLparser.getValue(element, ConstantHelp.KEY_RXTTY);
                    modelDrug.rxString      = xmLparser.getValue(element, ConstantHelp.KEY_RXSTRING);
                    modelDrug.hasImage      = xmLparser.getValue(element, ConstantHelp.KEY_HAS_IMAGE);
                    modelDrug.setId         = xmLparser.getValue(element, ConstantHelp.KEY_SETID);
                    modelDrug.author        = xmLparser.getValue(element, ConstantHelp.KEY_AUTHOR);
                    modelDrug.sqlScore      = xmLparser.getValue(element, ConstantHelp.KEY_SPLSCORE);
                    modelDrug.imageId       = xmLparser.getValue(element, ConstantHelp.KEY_IMAGE_ID);

                    listOfDrugs.add(modelDrug);

                }

            }
        }
        if(listOfDrugs != null){
            willSetResultView();

        }

    }

    @Override
    public void didFailed(String response) {

    }
}
