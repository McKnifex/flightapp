package edu.csumb.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Date;

import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightDao;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.LogRecord;
import edu.csumb.flightapp.model.Reservation;
import edu.csumb.flightapp.model.User;

public class CreateReservationActivity extends AppCompatActivity {

    private static Flight selectedFlight = SearchActivity.selectedFlight;

    public static Reservation res = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CreateReservatiActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);

                if (username.getText().toString().equals("!admiM2") &&
                        password.getText().toString().equals("!admiM2")) {

                    finish();
                }

                String name = username.getText().toString();
                String pw = password.getText().toString();

                FlightDao dao = FlightRoom.getFlightRoom(CreateReservationActivity.this).dao();
                User user = dao.login(name, pw);
                if (user == null) {
                    // unsuccessful login
                    TextView msg = findViewById(R.id.message);
                    msg.setText("User name or password is invalid.");

                } else {
                    // successful login
                    MainActivity.username = username.getText().toString();
                    CreateReservationActivity.res = new Reservation(MainActivity.username, selectedFlight, SearchActivity.amountTickets);
                    dao.addReservation(res);
                    Intent intent = new Intent(CreateReservationActivity.this, ConfirmReservationActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}

