package edu.csumb.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Dao;

import java.util.Date;

import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightDao;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.LogRecord;

public class ConfirmReservationActivity extends AppCompatActivity {
    FlightDao dao = FlightRoom.getFlightRoom(ConfirmReservationActivity.this).dao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CrateAccountActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String information = CreateReservationActivity.res.toString();

        //Show booking info
        TextView summary = findViewById(R.id.summary);
        summary.setText(information);

        //Confirm booking info
        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //update flight capacity
                Flight updateFlight = SearchActivity.selectedFlight;
                updateFlight.setCapacity(updateFlight.getCapacity()-SearchActivity.amountTickets);
                dao.updateFlight(updateFlight);
                //Log the reservation
                //write a record to Log table with message that reservation is confirmed.
                //  include username (but not password) in the message.
                Date now = new Date();
                LogRecord rec = new LogRecord(now, LogRecord.TYPE_RESERVATION,
                        MainActivity.username, CreateReservationActivity.res.getLog());
                dao.addLogRecord(rec);

                //Go back to main menu
                Intent intent = new Intent(ConfirmReservationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //If user declines the information
        //delete reservation form DB
        Button decline = findViewById(R.id.decline);
        decline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //delete res from db
                dao.deleteReservation(CreateReservationActivity.res);
                //go back to main menu
                Intent intent = new Intent(ConfirmReservationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

