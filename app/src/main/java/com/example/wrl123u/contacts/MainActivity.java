package com.example.wrl123u.contacts;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editname, editphone;
    SQLiteDatabase db;

    ListView list;
    String[] itemname = {
            "contact name",
            "contact name",
            "contact name",
            "contact name",
            "contact name",
            "contact name",
            "contact name",
            "contact name"
    };

    String[] phone = {
            "222-222-2222",
            "222-222-2222",
            "222-222-2222",
            "222-222-2222",
            "222-222-2222",
            "222-222-2222",
            "222-222-2222",
            "222-222-2222"
    };

    Integer[] imgid = {
            R.drawable.ic_contact_icon,
            R.drawable.ic_contact_icon,
            R.drawable.ic_contact_icon,
            R.drawable.ic_contact_icon,
            R.drawable.ic_contact_icon,
            R.drawable.ic_contact_icon,
            R.drawable.ic_contact_icon,
            R.drawable.ic_contact_icon
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editname = findViewById(R.id.nameBox);
        editphone = findViewById(R.id.phoneBox);

        db = openOrCreateDatabase("PersonDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS PersonTBL(Name VARCHAR(30), Phone VARCHAR(25));");

        ViewAllF();

        CustomListAdapter adapter = new CustomListAdapter(this, itemname, phone, imgid);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Contacts.class));
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

//    public void showMessage(String title, String message) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//    }

//    public void ViewAll(View v) {
//
//        Cursor c = db.rawQuery("SELECT * FROM StudentTBL;", null);
//        if(c.getCount() == 0) {
//            showMessage("Error", "No records found");
//            return;
//        }
//
//        StringBuffer buffer = new StringBuffer();
//
//        while(c.moveToNext()) {
//            buffer.append("Name: " + c.getString(0) + "\n");
//            buffer.append("Phone: " + c.getString(1) + "\n\n");
//
//        }
//
//        showMessage("Contacts", buffer.toString());
//
//    }

    public void ViewAllF() {

        int x = 0;
        Cursor c = db.rawQuery("SELECT * FROM PersonTBL;", null);

        if(c.getCount() == 0) {
            itemname[0] = "No Contacts";
            imgid[0] = R.drawable.ic_contact_icon;
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()) {
            itemname[x] = c.getString(0);
            phone[x] = c.getString(1);
            imgid[x] = R.drawable.ic_contact_icon;
            x++;

        }

//        showMessage("Contact Details", buffer.toString());

    }


}
