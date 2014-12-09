package edu.ccny.cs.kyaw.pillverifier;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import java.util.ArrayList;

import edu.ccny.cs.kyaw.pillverifier.adapters.ConstantHelp;
import edu.ccny.cs.kyaw.pillverifier.controllers.GetDrugInfo;
import edu.ccny.cs.kyaw.pillverifier.database.DatabaseHelper;
import edu.ccny.cs.kyaw.pillverifier.interfaces.InterfaceGetDrugInfo;
import edu.ccny.cs.kyaw.pillverifier.model.ModelDrug;
import edu.ccny.cs.kyaw.pillverifier.parser.XMLParser;


public class ActivityManual extends ActionBarActivity implements InterfaceGetDrugInfo
{

    private String TAG = getClass().getSimpleName();
    private Document documentElement;
    private XMLParser xmLparser;
    private ArrayList<ModelDrug> listOfDrugs;

    private EditText imprintEditText;
    private Spinner shapeSpinner, colorSpinner;

    private String imprint ;

    //database
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_manual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();
        setTitle("ID by Manual");
        xmLparser = new XMLParser();
        willSetupView();
        willSetupSpinner();

    }


    public void didClickOnFindItFromManual(View view){
        imprint = imprintEditText.getText().toString();
        new GetDrugInfo(this, this, imprint);


    }
    private void willSetupSpinner() {
        // color adapter
        ArrayAdapter<CharSequence> adapterColor = ArrayAdapter.createFromResource(this, R.array.pill_color,
                android.R.layout.simple_spinner_dropdown_item);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapterColor);
        // shape adapter
        ArrayAdapter<CharSequence> adapterShape = ArrayAdapter.createFromResource(this, R.array.pill_shape,
                android.R.layout.simple_spinner_dropdown_item);
        adapterShape.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(adapterShape);

    }

    private void willSetupView() {

        imprintEditText = (EditText) findViewById(R.id.edit_textImprint);
        shapeSpinner = (Spinner) findViewById(R.id.spinnerShape);
        colorSpinner = (Spinner) findViewById(R.id.spinnerColor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_activity_manual, menu);
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

                Log.e(TAG, "Home");
                finish();
                return true;

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void didSucceed(String response) {
        documentElement                         = xmLparser.getDomElement(response);
        listOfDrugs                             = new ArrayList<ModelDrug>();
        if(documentElement != null){


            NodeList nodeList                   = documentElement.getElementsByTagName(ConstantHelp.KEY_PILL);
            if(nodeList == null){
                Log.e(TAG, "Hello I am not null");
            }

            for (int i = 0; i<nodeList.getLength(); i++){
                ModelDrug modelDrug = new ModelDrug();

                Element element                 = (Element) nodeList.item(i);
                if (xmLparser.getValue(element, ConstantHelp.KEY_IMAGE_ID) != ""){
                    modelDrug.splId                 = xmLparser.getValue(element, ConstantHelp.KEY_SPL);
                    modelDrug.productCode           = xmLparser.getValue(element, ConstantHelp.KEY_PRODUCT_CODE);
                    modelDrug.ndc9                  = xmLparser.getValue(element, ConstantHelp.KEY_NDC9);
                    modelDrug.splColor              = xmLparser.getValue(element, ConstantHelp.KEY_SPLCOLOR);
                    modelDrug.sqlImprint            = xmLparser.getValue(element, ConstantHelp.KEY_SPLIMPRINT);
                    modelDrug.splShape              = xmLparser.getValue(element, ConstantHelp.KEY_SPLSHAPE);
                    modelDrug.splSize               = xmLparser.getValue(element, ConstantHelp.KEY_SPLSIZE);
                    modelDrug.rxCui                 = xmLparser.getValue(element, ConstantHelp.KEY_RXCUI);
                    modelDrug.rxTty                 = xmLparser.getValue(element, ConstantHelp.KEY_RXTTY);
                    modelDrug.rxString              = xmLparser.getValue(element, ConstantHelp.KEY_RXSTRING);
                    modelDrug.hasImage              = xmLparser.getValue(element, ConstantHelp.KEY_HAS_IMAGE);
                    modelDrug.setId                 = xmLparser.getValue(element, ConstantHelp.KEY_SETID);
                    modelDrug.author                = xmLparser.getValue(element, ConstantHelp.KEY_AUTHOR);
                    modelDrug.sqlScore              = xmLparser.getValue(element, ConstantHelp.KEY_SPLSCORE);
                    modelDrug.imageId               = xmLparser.getValue(element, ConstantHelp.KEY_IMAGE_ID);

                    listOfDrugs.add(modelDrug);

                }

            }

            databaseHelper.tmpUpgrade(database);

            if(listOfDrugs != null){
                ContentValues values = new ContentValues();

                for (int i = 0; i<listOfDrugs.size(); i++){
                    values.put(ConstantHelp.KEY_IMAGE_ID , listOfDrugs.get(i).imageId);
                    values.put(ConstantHelp.KEY_RXSTRING, listOfDrugs.get(i).rxString);
                    Log.e("Drug Name: ", listOfDrugs.get(i).imageId);
                    database.insert(ConstantHelp.DRUG_INFO_TABLE, null, values);
                }
            }

            startActivity(new Intent(this, PillGridViewActivity.class));
            finish();

        }





    }

    @Override
    public void didFailed(String response) {

    }
}
