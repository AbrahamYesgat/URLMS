package ca.mcgill.ecse321.appurlms.persistence;

import ca.mcgill.ecse321.urlms.model.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SQLController extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "URLMSDB";
    private String test = "";

    public SQLController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE URLMS (data BLOB);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public URLMS loadURLMSFromDatabase(SQLiteDatabase db) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT data FROM URLMS;", null);

        URLMS urlms = null;

        if (cursor != null) {
            cursor.moveToFirst();

            byte[] blob = cursor.getBlob(0);
            String json = new String(blob);
            Gson gson = new Gson();

            urlms = gson.fromJson(json, new TypeToken<URLMS>() {
            }.getType());
        }

        else {
            urlms = new URLMS();
            saveURLMSToDatabase(db, urlms);
        }

        return urlms;
    }

    public void saveURLMSToDatabase(SQLiteDatabase db, URLMS urlms) {
        Gson gson = new Gson();
        ContentValues values = new ContentValues();
        values.put("data", gson.toJson(urlms).getBytes());
        db.insert("URLMS", null, values);
    }
}
