package edu.ccny.cs.kyaw.pillverifier;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import java.util.ArrayList;


import edu.ccny.cs.kyaw.pillverifier.adapters.ConstantHelp;
import edu.ccny.cs.kyaw.pillverifier.adapters.GridViewAdapter;
import edu.ccny.cs.kyaw.pillverifier.database.DatabaseHelper;
import edu.ccny.cs.kyaw.pillverifier.model.ModelDrugGridView;


public class PillGridViewActivity extends ActionBarActivity {

    private final String TAG = getClass().getSimpleName();

    private Toolbar toolbar;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private ArrayList<ModelDrugGridView> modelDrugGridViews;
    private GridViewAdapter gridViewAdapter;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_grid_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        if(toolbar !=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        modelDrugGridViews = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getReadableDatabase();
        willSetupView();

        willGetDataFromDatabase();
        willRenderImageIntoGridView();

    }

    private void willSetupView() {
        gridView = (GridView) findViewById(R.id.gridView);

    }

    private void willRenderImageIntoGridView() {

        gridViewAdapter = new GridViewAdapter(this);
        Log.e(TAG, String.valueOf(modelDrugGridViews.size()));
        gridViewAdapter.setViewArrayList(modelDrugGridViews);
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, String.valueOf(position));
            }
        });
    }

    private void willGetDataFromDatabase() {

        String sql = "SELECT * FROM "+ ConstantHelp.DRUG_INFO_TABLE ;
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                ModelDrugGridView modelDrugGridView = new ModelDrugGridView();
                modelDrugGridView.rxString = cursor.getString(cursor.getColumnIndex(ConstantHelp.KEY_RXSTRING));

                modelDrugGridView.imageUrl = ConstantHelp.LargeImageBaseUrl +
                        cursor.getString(cursor.getColumnIndex(ConstantHelp.KEY_IMAGE_ID)) +".jpg";
                Log.e(TAG, modelDrugGridView.imageUrl);
                modelDrugGridViews.add(modelDrugGridView);


            }while (cursor.moveToNext());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_pill_grid_view, menu);
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
               finish();
               return true;
       }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
