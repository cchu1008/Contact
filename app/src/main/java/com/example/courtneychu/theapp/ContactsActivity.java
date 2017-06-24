package com.example.courtneychu.theapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class ContactsActivity extends AppCompatActivity {
    private SetDate fromDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText enterDate = (EditText) findViewById(R.id.startDateContacts);
        fromDate = new SetDate(enterDate, this);

        Button homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToMainActivity();
            }
        });

        Button appointmentButton = (Button) findViewById(R.id.appointment_button);
        appointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToAppointmentsActivity();
            }
        });

        Button oneWeekButton = (Button) findViewById(R.id.one_week_button);
        oneWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToOneWeek();
            }
        });

        Button twoWeekButton = (Button) findViewById(R.id.two_week_button);
        twoWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToTwoWeek();
            }
        });

        Button twoMonthsButton = (Button) findViewById(R.id.two_months_button);
        twoMonthsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToTwoMonths();
            }
        });

        Button threeMonthsButton = (Button) findViewById(R.id.three_months_button);
        threeMonthsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToThreeMonths();
            }
        });

    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToAppointmentsActivity(){
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    private void goToOneWeek(){
        Context context = getApplicationContext();
        CharSequence text = "One-week contact lens registered";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void goToTwoWeek(){
        Context context = getApplicationContext();
        CharSequence text = "Two-week contact lens registered";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void goToTwoMonths(){
        Context context = getApplicationContext();
        CharSequence text = "Two-month contact lens registered";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void goToThreeMonths(){
        Context context = getApplicationContext();
        CharSequence text = "Three-month contact lens registered";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }
}
