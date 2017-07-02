package com.example.courtneychu.theapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
/**
 * Created by Courtney Chu on 6/23/2017.
 */

public class SetDate implements View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {
    private EditText editText;
    private Calendar myCalendar;
    private Context ctx;

    public SetDate(EditText editText, Context ctx) {
        this.editText = editText;
        this.editText.setOnFocusChangeListener(this);
        this.ctx = ctx;
        myCalendar = Calendar.getInstance();
    }

    public SetDate(int year, int monthOfYear, int dayOfMonth){
        this.myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    public SetDate(Calendar newCalendar){
        this.myCalendar = newCalendar;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.US);

        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        editText.setText(sdformat.format(myCalendar.getTime()));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus){
        if(hasFocus){
            new DatePickerDialog(this.ctx, this, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    public Calendar getDate(){
        return this.myCalendar;
    }

    @Override
    public String toString(){
        return getMonth(myCalendar.get(Calendar.MONTH)) + " " + myCalendar.get(Calendar.DAY_OF_MONTH) + ", " + myCalendar.get(Calendar.YEAR);
    }

    private String getMonth(int month){
        switch(month){
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "January";
        }
    }
}
