package edu.ccny.cs.kyaw.pillverifier;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.ccny.cs.kyaw.pillverifier.database.DatabaseHelper;
import edu.ccny.cs.kyaw.pillverifier.ocr.OcrActivity;
import edu.ccny.cs.kyaw.pillverifier.scanner.BaroCodeScanner;
import lt.lemonlabs.android.expandablebuttonmenu.ExpandableButtonMenu;
import lt.lemonlabs.android.expandablebuttonmenu.ExpandableMenuOverlay;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout drawer;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private ExpandableMenuOverlay expandableMenuOverlay ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
            setTitle("ID A PILL");

        }

        databaseHelper = new DatabaseHelper(this);


        willSetUpView();
        willSetOnClickListenerForExpandableMenu();

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);


    }

    private void willSetOnClickListenerForExpandableMenu() {
        expandableMenuOverlay.setOnMenuButtonClickListener( new ExpandableButtonMenu.OnMenuButtonClick() {
            @Override
            public void onClick(ExpandableButtonMenu.MenuButton menuButton) {
                switch (menuButton){
                    case MID:
                        didClickOnScanNdc();
                        break;
                    case LEFT:
                        didClickOnManual();
                        break;
                    case RIGHT:
                        didClickOnOCR();
                        break;
                }
            }
        });
    }

    private void willSetUpView() {

        expandableMenuOverlay = (ExpandableMenuOverlay) findViewById(R.id.button_menu);

    }
    public void didClickOnScanNdc(){
        startActivity(new Intent(getApplicationContext(), BaroCodeScanner.class));
    }

    public void didClickOnManual(){
        startActivity(new Intent(this, ActivityManual.class));
    }
    public void didClickOnOCR(){
        startActivity(new Intent(this, OcrActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.home:
                drawer.openDrawer(Gravity.START);
                return true;
            case R.id.action_settings:
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
