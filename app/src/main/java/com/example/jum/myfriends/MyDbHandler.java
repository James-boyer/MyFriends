package com.example.jum.myfriends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jum on 23/02/15.
 */
public class MyDbHandler extends SQLiteOpenHelper {
    public static final String tableName = "friendstable";
    public static final String dbName = "friends.db";

    public MyDbHandler(Context context){
      super(context,dbName,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      String CreateTable = "CREATE TABLE "+tableName+" ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                           "name TEXT," +
                           "phone TEXT," +
                           "email TEXT )";
      db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      db.execSQL("DROP TABLE IF EXISTS "+ tableName);

      this.onCreate(db);
    }
    public void loadData(Context context){
        try {
            Cursor c = query("select * from "+ tableName);
            // if values have not been loaded, clear and reload values
            if(c.getCount() < 99) {
                getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + tableName);
                this.onCreate(getWritableDatabase());
                Toast.makeText(context, "(Re)load table", Toast.LENGTH_LONG).show();
                InputStream is = context.getResources().openRawResource(R.raw.data);


                BufferedReader file = new BufferedReader(new InputStreamReader(is));
                String a = "";
                while ((a = file.readLine()) != "") {
                    String[] b = a.split(",");
                    ContentValues values = new ContentValues();
                    values.put("name", b[0]);
                    values.put("phone", b[1]);
                    values.put("email", b[2]);
                    getWritableDatabase().insert(tableName, "null", values);
                }
                is.close();
                file.close();
            }
            else{
                Toast.makeText(context, "table already loaded", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){}

    }

    public String getTableName(){
        return tableName;
    }
    public Cursor query(String sql) {
      return getWritableDatabase().rawQuery(sql,null);
    }

}
