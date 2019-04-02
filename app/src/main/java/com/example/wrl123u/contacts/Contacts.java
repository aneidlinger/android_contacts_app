package com.example.wrl123u.contacts;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Contacts extends AppCompatActivity {

    EditText personName;
    EditText personPhone;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        personName = findViewById(R.id.nameBox);
        personPhone = findViewById(R.id.phoneBox);

        db = openOrCreateDatabase("PersonDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS PersonTBL(Name varchar(30), Phone varchar(25));");

    }

    public void ClearText() {

        personName.setText("");
        personPhone.setText("");
        personName.requestFocus();

    }

    public void showMessage(String title, String message) {

        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void save(View v) {
        if(personName.getText().toString().trim().length() == 0 || personPhone.getText().toString().trim().length() == 0) {
            showMessage("Error", "Please enter all values");
            return;
        }

        db.execSQL("INSERT INTO PersonTBL VALUES('" + personName.getText() + "', '" + personPhone.getText() + "');");
        showMessage("Success!", "You just added a record");
        ClearText();
        startActivity(new Intent(Contacts.this, MainActivity.class));
    }

    public void cancel(View v) {

        ClearText();
        personName.requestFocus();

    }


}
