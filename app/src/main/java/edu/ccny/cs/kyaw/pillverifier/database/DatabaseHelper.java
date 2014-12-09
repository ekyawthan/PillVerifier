package edu.ccny.cs.kyaw.pillverifier.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.ccny.cs.kyaw.pillverifier.adapters.ConstantHelp;

/**
 * Created by kyawthan on 12/6/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private String TAG = getClass().getSimpleName();


    private static String sqlForDrugInfo = "CREATE TABLE "+ ConstantHelp.DRUG_INFO_TABLE + " ( "+
            ConstantHelp.PRIMIARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            ConstantHelp.KEY_IMAGE_ID + " TEXT, " + ConstantHelp.KEY_RXSTRING + " TEXT "+ ")";



    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, name, cursorFactory, version);

    }

    public DatabaseHelper(Context context){
        super(context, ConstantHelp.DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, sqlForDrugInfo);
        db.execSQL(sqlForDrugInfo);

    }

    public void tmpUpgrade(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ ConstantHelp.DRUG_INFO_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ConstantHelp.DRUG_INFO_TABLE);
        onCreate(db);

    }
}
