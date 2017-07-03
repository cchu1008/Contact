package com.example.courtneychu.theapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SetDate implements View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {
    private EditText editText;
    private Calendar myCalendar;
    private Context ctx;

    /**
     * @param editText : Text form for the date
     * @param ctx : Context
     */
    public SetDate(EditText editText, Context ctx) {
        this.editText = editText;
        this.editText.setOnFocusChangeListener(this);
        this.ctx = ctx;
        myCalendar = Calendar.getInstance();
    }

    /**
     * @param year : Year to be set
     * @param monthOfYear : Month to be set
     * @param dayOfMonth : Day of month to be set
     */
    public SetDate(int year, int monthOfYear, int dayOfMonth){
        this.myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    /**
     * @param newCalendar : Calendar w/ date to be set
     */
    public SetDate(Calendar newCalendar){
        this.myCalendar = newCalendar;
    }

    /**
     * @param view : Given DatePicker
     * @param year : Year to be set from the DatePicker
     * @param monthOfYear : Month to be set from the DatePicker
     * @param dayOfMonth : Day of month to be set from the DatePicker
     *
     * Brings up calendar to pick the date.
     */
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.US);

        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        editText.setText(sdformat.format(myCalendar.getTime()));
    }

    /**
     * @param v : Given view
     * @param hasFocus : Boolean value of whether the calendar is visible
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus){
        if(hasFocus){
            new DatePickerDialog(this.ctx, this, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    /**
     * @return Calendar : This date (Calendar)
     */
    public Calendar getCalendar(){
        return this.myCalendar;
    }

    /**
     * @return String : String version of the date
     */
    @Override
    public String toString(){
        return getMonth(myCalendar.get(Calendar.MONTH)) + " " + myCalendar.get(Calendar.DAY_OF_MONTH) + ", " + myCalendar.get(Calendar.YEAR);
    }

    /**
     * @param month : Given month
     * @return String : String representation of the month
     */
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
