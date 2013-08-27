package com.example.antistalker;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fanton
 * Date: 8/27/13
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewBlockedActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blockedview);

        ListView listView = (ListView) findViewById(R.id.listView);

        List<Person> blockedPersons = (List<Person>) getIntent().getExtras().get("blockedPersons");

        Log.v("TAG", "Super Persons: " + blockedPersons);
        ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.textview, blockedPersons);

        listView.setAdapter(adapter);
    }
}
