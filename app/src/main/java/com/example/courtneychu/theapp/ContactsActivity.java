package com.example.courtneychu.theapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ContactsActivity extends AppCompatActivity {
    private SetDate fromDate;
    private EditText enterDate;

    private ArrayList<Calendar> expirationDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        enterDate = (EditText) findViewById(R.id.startDateContacts);
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
                goToOneWeek(enterDate, fromDate);
            }
        });

        Button twoWeekButton = (Button) findViewById(R.id.two_week_button);
        twoWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToTwoWeek(enterDate, fromDate);
            }
        });

        Button oneMonthButton = (Button) findViewById(R.id.one_month_button);
        oneMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToOneMonth(enterDate, fromDate);
            }
        });

        Button threeMonthsButton = (Button) findViewById(R.id.three_months_button);
        threeMonthsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToThreeMonths(enterDate, fromDate);
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

    private void goToOneWeek(EditText enterDate, SetDate fromDate){
        Calendar nextDate = getNewDate(0, 0, 7);

        SetDate finalDate = new SetDate(nextDate);
        String dateToString = finalDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "One-week contact lens registered\n" + dateToString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        expirationDates.add(nextDate);
    }

    private void goToTwoWeek(EditText enterDate, SetDate fromDate){
        Calendar nextDate = getNewDate(0, 0, 14);

        SetDate finalDate = new SetDate(nextDate);
        String dateToString = finalDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "Two-week contact lens registered\n" + dateToString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        expirationDates.add(nextDate);
    }

    private void goToOneMonth(EditText enterDate, SetDate fromDate){
        Calendar nextDate = getNewDate(0, 1, 0);

        SetDate finalDate = new SetDate(nextDate);
        String dateToString = finalDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "One-month contact lens registered\n" + dateToString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        expirationDates.add(nextDate);
    }

    private void goToThreeMonths(EditText enterDate, SetDate fromDate){
        Calendar nextDate = getNewDate(0, 3, 0);

        SetDate finalDate = new SetDate(nextDate);
        String dateToString = finalDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "Three-month contact lens registered\n" + dateToString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        expirationDates.add(nextDate);
    }

    private Calendar getNewDate(int year, int month, int day){
        Calendar finDate = Calendar.getInstance();
        int newYear = finDate.get(Calendar.YEAR);
        int newMonth = finDate.get(Calendar.MONTH);
        int newDay = finDate.get(Calendar.DAY_OF_MONTH);

        newYear += year;

        if(month != 0){
            if(newMonth + month >= 12){
                newYear += (newMonth + month)/12;
                newMonth += (newMonth + month)%12;
            }
            else{
                newMonth += month;
            }
        }

        if(day != 0){
            if(newMonth == 1){
                if(newMonth + day >= 28){
                    newMonth += ((newDay + day)/28);
                    newDay += ((newDay + day)%28);
                }
                else{
                    newDay += day;
                }
            }
            else if(newMonth < 7){
                if(newMonth%2 == 0) {
                    if (newMonth + day >= 31) {
                        newMonth += ((newDay + day) / 31);
                        newDay += ((newDay + day) % 31);
                    } else {
                        newDay += day;
                    }
                }
                if(newMonth%2 == 1) {
                    if (newMonth + day >= 30) {
                        newMonth += ((newDay + day) / 30);
                        newDay += ((newDay + day) % 30);
                    } else {
                        newDay += day;
                    }
                }
            }
            else{
                if(newMonth%2 == 1) {
                    if (newMonth + day >= 31) {
                        newMonth += ((newDay + day) / 31);
                        newDay += ((newDay + day) % 31);
                    } else {
                        newDay += day;
                    }
                }
                if(newMonth%2 == 2) {
                    if (newMonth + day >= 30) {
                        newMonth += ((newDay + day) / 30);
                        newDay += ((newDay + day) % 30);
                    } else {
                        newDay += day;
                    }
                }

            }
        }

        finDate.set(Calendar.YEAR, newYear);
        finDate.set(Calendar.MONTH, newMonth);
        finDate.set(Calendar.DAY_OF_MONTH, newDay);

        return finDate;
    }
}
