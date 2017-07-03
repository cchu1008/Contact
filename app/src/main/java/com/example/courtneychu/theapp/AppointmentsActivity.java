package com.example.courtneychu.theapp;

import android.content.Context;
import android.content.Intent;
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

    /**
     * @param savedInstanceState: Given parameter
     *
     * Creates the links for the Buttons and sets up the reference date.
     */
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
               //TODO: See if calendar can be edited
               goToSetDate(fromDate);
           }
        });
    }

    /**
     * Moves to the ContactsActivity page (home page)
     */
    private void goToContactsActivity(){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    /**
     * Moves to the MainActivity page (home page)
     */
    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * @param fromDate : Start date
     *
     * Adds appointment date to the list of Calendars. Also, deploys a toast stating this.
     */
    private void goToSetDate(SetDate fromDate){
        String dateToString = fromDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "Your date " + dateToString + " has been saved";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        addAndSort(appointmentDates, fromDate.getCalendar());
    }

    /**
     * @param appointmentDates : List<Calendar> of appointment dates
     * @param nextDate : Calendar date to be added to the list of appointment dates
     *
     * Adds nextDate to appointmentDates and deploys Google Calendars
     */
    private void addAndSort(List<Calendar> appointmentDates, Calendar nextDate){
        for(int i = 0; i < appointmentDates.size(); i++){
            Calendar currentDate = appointmentDates.get(i);

            if(currentDate == null){
                appointmentDates.add(i, nextDate);
            }

            else if(currentDate.get(Calendar.YEAR) == nextDate.get(Calendar.YEAR)){
                if(currentDate.get(Calendar.MONTH) == nextDate.get(Calendar.MONTH)){
                    if(currentDate.get(Calendar.DAY_OF_MONTH) >= nextDate.get(Calendar.DAY_OF_MONTH)){
                        appointmentDates.add(i, nextDate);
                    }
                }
                else if(currentDate.get(Calendar.MONTH) > nextDate.get(Calendar.MONTH)){
                    appointmentDates.add(i, nextDate);
                }
            }
            else if(currentDate.get(Calendar.YEAR) > nextDate.get(Calendar.YEAR)){
                appointmentDates.add(i, nextDate);
            }
        }

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, fromDate.getCalendar().getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, fromDate.getCalendar().getTimeInMillis() + 60*60*1000)
                .putExtra(CalendarContract.Events.TITLE, "Appointment Alert")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Your appointment approaches!")
                .putExtra(CalendarContract.Reminders.EVENT_ID, nextDate.getTimeInMillis())
                .putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
                .putExtra(CalendarContract.Reminders.MINUTES, 60*24)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_TENTATIVE);
        startActivity(intent);
    }

}
