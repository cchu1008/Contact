package com.example.courtneychu.theapp;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AppointmentsActivity extends AppCompatActivity {
    private SetDate fromDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        Button contactsButton = (Button) findViewById(R.id.contacts_button);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToContactsActivity();
            }
        });

        Button homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToMainActivity();
            }
        });

        EditText enterDate = (EditText) findViewById(R.id.appDate);
        fromDate = new SetDate(enterDate, this);

        Button setDateButton = (Button) findViewById(R.id.set_date_button);
        setDateButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               //TODO: Save appointment date
               //TODO: See if calendar can be edited
               goToSetDate(fromDate);
           }
        });
    }

    private void goToContactsActivity(){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToSetDate(SetDate fromDate){
        Context context = getApplicationContext();
        CharSequence text = "Your date " + fromDate.toString() + " has been saved";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }

}
