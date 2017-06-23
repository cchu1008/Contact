package com.example.courtneychu.theapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button contactsButton = (Button) findViewById(R.id.contacts_button);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToContactsActivity();
            }
        });

        Button appointmentButton = (Button) findViewById(R.id.appointment_button);
        appointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToAppointmentsActivity();
            }
        });

    }

    private void goToContactsActivity(){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    private void goToAppointmentsActivity(){
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }
}
