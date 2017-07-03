package com.example.courtneychu.theapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {
    private SetDate fromDate;
    private ArrayList<Calendar> appointmentDates = new ArrayList<>();

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
        String dateToString = fromDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "Your date " + dateToString + " has been saved";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        addAndSort(appointmentDates, fromDate.getDate());
    }

    private void addAndSort(List<Calendar> expirationDates, Calendar nextDate){
        for(int i = 0; i < expirationDates.size(); i++){
            Calendar currentDate = expirationDates.get(i);

            if(currentDate == null){
                expirationDates.add(i, nextDate);
            }

            else if(currentDate.get(Calendar.YEAR) == nextDate.get(Calendar.YEAR)){
                if(currentDate.get(Calendar.MONTH) == nextDate.get(Calendar.MONTH)){
                    if(currentDate.get(Calendar.DAY_OF_MONTH) >= nextDate.get(Calendar.DAY_OF_MONTH)){
                        expirationDates.add(i, nextDate);
                    }
                }
                else if(currentDate.get(Calendar.MONTH) > nextDate.get(Calendar.MONTH)){
                    expirationDates.add(i, nextDate);
                }
            }
            else if(currentDate.get(Calendar.YEAR) > nextDate.get(Calendar.YEAR)){
                expirationDates.add(i, nextDate);
            }
        }

        if (Build.VERSION.SDK_INT >= 14) {
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, fromDate.getDate().getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, fromDate.getDate().getTimeInMillis() + 60*60*1000)
                    .putExtra(CalendarContract.Events.TITLE, "Appointment Alert")
                    .putExtra(CalendarContract.Events.DESCRIPTION, "Your appointment approaches!")
                    .putExtra(CalendarContract.Reminders.EVENT_ID, nextDate.getTimeInMillis())
                    .putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
                    .putExtra(CalendarContract.Reminders.MINUTES, 60*24)
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_TENTATIVE);
            startActivity(intent);
        }

        else {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("beginTime", nextDate.getTimeInMillis());
            intent.putExtra("allDay", true);
            intent.putExtra("rrule", "FREQ=YEARLY");
            intent.putExtra("endTime", nextDate.getTimeInMillis()+60*60*1000);
            intent.putExtra("title", "Appointment Alert");
            startActivity(intent);
        }
    }

}
