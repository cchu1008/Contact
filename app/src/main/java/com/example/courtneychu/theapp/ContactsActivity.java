package com.example.courtneychu.theapp;

import android.content.Context;
import android.provider.CalendarContract;
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
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private SetDate fromDate;
    private ArrayList<Calendar> expirationDates = new ArrayList<>();

    /**
     * @param savedInstanceState: Given parameter
     *
     * Creates the links for the Buttons and sets up the reference date.
     */
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
                goToOneWeek(fromDate.getCalendar());
            }
        });

        Button twoWeekButton = (Button) findViewById(R.id.two_week_button);
        twoWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToTwoWeek(fromDate.getCalendar());
            }
        });

        Button oneMonthButton = (Button) findViewById(R.id.one_month_button);
        oneMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToOneMonth(fromDate.getCalendar());
            }
        });

        Button threeMonthsButton = (Button) findViewById(R.id.three_months_button);
        threeMonthsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToThreeMonths(fromDate.getCalendar());
            }
        });

    }

    /**
     * Moves to the MainActivity page (home page)
     */
    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Moves to the AppointmentActivity page
     */
    private void goToAppointmentsActivity(){
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    /**
     * @param fromDate: Start date
     *
     * Adds date one week from fromDate to the expirationDates list. Also deploys a toast stating this.
     */
    private void goToOneWeek(Calendar fromDate){
        Calendar nextDate = getNewDate(fromDate, 0, 0, 7);

        SetDate finalDate = new SetDate(nextDate);
        String dateToString = finalDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "One-week contact lens registered\n" + dateToString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        addAndSort(expirationDates, nextDate);
    }

    /**
     * @param fromDate: Start date
     *
     * Adds date two weeks from fromDate to the expirationDates list. Also deploys a toast stating this.
     */
    private void goToTwoWeek(Calendar fromDate){
        Calendar nextDate = getNewDate(fromDate, 0, 0, 14);

        SetDate finalDate = new SetDate(nextDate);
        String dateToString = finalDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "Two-week contact lens registered\n" + dateToString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        addAndSort(expirationDates, nextDate);
    }

    /**
     * @param fromDate: Start date
     *
     * Adds date one month from fromDate to the expirationDates list. Also deploys a toast stating this.
     */
    private void goToOneMonth(Calendar fromDate){
        Calendar nextDate = getNewDate(fromDate, 0, 1, 0);

        SetDate finalDate = new SetDate(nextDate);
        String dateToString = finalDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "One-month contact lens registered\n" + dateToString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        addAndSort(expirationDates, nextDate);
    }

    /**
     * @param fromDate: Start date
     *
     * Adds date three months from fromDate to the expirationDates list. Also deploys a toast stating this.
     */
    private void goToThreeMonths(Calendar fromDate){
        Calendar nextDate = getNewDate(fromDate, 0, 3, 0);

        SetDate finalDate = new SetDate(nextDate);
        String dateToString = finalDate.toString();

        Context context = getApplicationContext();
        CharSequence text = "Three-month contact lens registered\n" + dateToString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();

        addAndSort(expirationDates, nextDate);
    }

    /**
     *
     * @param expirationDates : List of contact expiration dates (List<Calendar>)
     * @param nextDate : The date to be added to the list expirationDates (Calendar)
     *
     * Adds nextDate to expirationDates in order. Also brings up Google Calendars to add the event.
     */
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

        //.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true) is the code for setting an all-day event
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, nextDate.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, nextDate.getTimeInMillis() + 60*60*1000)
                .putExtra(CalendarContract.Events.TITLE, "Contacts Alert")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Contacts have expired!")
                .putExtra(CalendarContract.Reminders.EVENT_ID, nextDate.getTimeInMillis())
                .putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
                .putExtra(CalendarContract.Reminders.MINUTES, 10)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_TENTATIVE);
        startActivity(intent);
    }

    /**
     *
     * @param fromDate : Start date (Calendar)
     * @param year : The years from the start date
     * @param month : The months from the start date
     * @param day : The days from the start date
     * @return Calendar: The date from the start day given the delay.
     */
    private Calendar getNewDate(Calendar fromDate, int year, int month, int day){
        Calendar finDate = Calendar.getInstance();
        finDate.set(Calendar.YEAR, fromDate.get(Calendar.YEAR));
        finDate.set(Calendar.MONTH, fromDate.get(Calendar.MONTH));
        finDate.set(Calendar.DAY_OF_MONTH, fromDate.get(Calendar.DAY_OF_MONTH));

        int newYear = fromDate.get(Calendar.YEAR);
        int newMonth = fromDate.get(Calendar.MONTH);
        int newDay = fromDate.get(Calendar.DAY_OF_MONTH);

        newYear += year;

        if(month != 0){
            if(fromDate.get(Calendar.MONTH) + month >= 12){
                newYear += (fromDate.get(Calendar.MONTH) + month)/12;
                newMonth += (fromDate.get(Calendar.MONTH) + month)%12;
            }
            else{
                newMonth += month;
            }
        }

        if(day != 0){
            if((fromDate.get(Calendar.MONTH)) == Calendar.FEBRUARY){
                if(fromDate.get(Calendar.MONTH) + day >= 28){
                    newMonth += ((fromDate.get(Calendar.DAY_OF_MONTH) + day)/28);
                    newDay += ((fromDate.get(Calendar.DAY_OF_MONTH) + day)%28);
                }
                else{
                    newDay += day;
                }
            }
            else if(fromDate.get(Calendar.MONTH) < Calendar.AUGUST){
                if(fromDate.get(Calendar.MONTH)%2 == 0) {
                    if (fromDate.get(Calendar.MONTH) + day >= 31) {
                        newMonth += ((fromDate.get(Calendar.DAY_OF_MONTH) + day) / 31);
                        newDay += ((fromDate.get(Calendar.DAY_OF_MONTH) + day) % 31);
                    } else {
                        newDay += day;
                    }
                }
                if(fromDate.get(Calendar.MONTH)%2 == 1) {
                    if (fromDate.get(Calendar.MONTH) + day >= 30) {
                        newMonth += ((fromDate.get(Calendar.DAY_OF_MONTH) + day) / 30);
                        newDay += ((fromDate.get(Calendar.DAY_OF_MONTH) + day) % 30);
                    } else {
                        newDay += day;
                    }
                }
            }
            else{
                if(fromDate.get(Calendar.MONTH)%2 == 1) {
                    if (fromDate.get(Calendar.MONTH) + day >= 31) {
                        newMonth += ((fromDate.get(Calendar.DAY_OF_MONTH) + day) / 31);
                        newDay += ((fromDate.get(Calendar.DAY_OF_MONTH) + day) % 31);
                    } else {
                        newDay += day;
                    }
                }
                if(fromDate.get(Calendar.MONTH)%2 == 2) {
                    if (fromDate.get(Calendar.MONTH) + day >= 30) {
                        newMonth += ((fromDate.get(Calendar.DAY_OF_MONTH) + day) / 30);
                        newDay += ((fromDate.get(Calendar.DAY_OF_MONTH) + day) % 30);
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
