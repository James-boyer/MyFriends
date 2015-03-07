package com.example.jum.myfriends;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.listView);
    // Database handler
        MyDbHandler dbHandle = new MyDbHandler(this);
        dbHandle.loadData(this);


        Cursor c = dbHandle.query("Select * from "+ dbHandle.getTableName());
        c.moveToFirst();
        List<Friend> friends = new ArrayList<>();
        Friend tmp = new Friend();
        // iterate through cursor, create object, add to array
        while(c.moveToNext()) {
            tmp.setName(c.getString(1));
            tmp.setPhone(c.getString(2));
            tmp.setEmail(c.getString(3));
            friends.add(tmp);
            tmp = new Friend();
        }
        //convert to array and make an adapter
        Friend[] friendsarr = new Friend[friends.size()];
        friends.toArray(friendsarr);
        ArrayAdapter<Friend> ada= new ArrayAdapter<>(this,R.layout.activity_listview,friendsarr);

        lv.setAdapter(ada);

        // On click display phone number and email
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend tmp = (Friend)parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),"Phone: " + tmp.getPhone() + "\nEmail: " + tmp.getEmail(),Toast.LENGTH_LONG).show();

            }
        });

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
