package edu.ccny.cs.kyaw.pillverifier;


import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;


import com.loopj.android.image.SmartImageView;

import static edu.ccny.cs.kyaw.pillverifier.R.id.toolbar_detailSpecific;


public class DetailDrugActivity extends ActionBarActivity {
    public static final String EXTRA_IMAGE = "DetailActivity:image";


    private SmartImageView imageView;
    private TextView rxStringEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_drug);
        Toolbar toolbar = (Toolbar) findViewById(toolbar_detailSpecific);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        willSetupView();
        willRenderDataIntoView();
    }

    private void willRenderDataIntoView() {
        imageView.setImageUrl(EXTRA_IMAGE);



    }

    private void willSetupView() {

        imageView = (SmartImageView) findViewById(R.id.imageViewSpecific);
        rxStringEditText = (TextView) findViewById(R.id.textviewRxstringSpecific);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_drug, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return  true;
   }

    public static void lunch(ActionBarActivity actionBarActivity, View transitionView, String Url){
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(actionBarActivity,
                        transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(actionBarActivity, DetailDrugActivity.class);
        intent.putExtra(EXTRA_IMAGE, Url);
        Log.e("url", Url);
        ActivityCompat.startActivity(actionBarActivity, intent, optionsCompat.toBundle());
    }

}
