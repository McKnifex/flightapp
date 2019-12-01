package edu.csumb.flightapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddNewFlightActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d("CancelReservatiActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

}
