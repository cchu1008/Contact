package com.example.courtneychu.theapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppointmentsActivity extends AppCompatActivity {

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
    }

    private void goToContactsActivity(){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
