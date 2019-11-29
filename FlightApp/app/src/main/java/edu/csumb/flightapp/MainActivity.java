package edu.csumb.flightapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.User;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public static String username = null;   // username if logged in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // check database
        FlightRoom.getFlightRoom(MainActivity.this).loadData(this);

        Button create_account_button = findViewById(R.id.create_account);

        create_account_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the ShowUser Activity
                Log.d("MainActivity", "onClick for create account called");
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);

            }
        });

        Button reserve_seat_button = findViewById(R.id.reserve_seat);
        reserve_seat_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the ShowUser Activity
                Log.d("MainActivity", "onClick for reserve seat called");
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });

        Button cancel_reservation_button = findViewById(R.id.cancel_reservation);
        cancel_reservation_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the ShowUser Activity
                Log.d("MainActivity", "onClick for cancel reservation called");
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);

            }
        });

        Button manage_system_button = findViewById(R.id.manage_system);
        manage_system_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the ShowUser Activity
                Log.d("MainActivity", "onClick for manage system is called");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        /*Button login_button = findViewById(R.id.login);

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the ShowUser Activity
                Log.d("MainActivity", "onClick for login called");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });*/

        /*Button user_button = findViewById(R.id.show_users);

        user_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the ShowUser Activity
                Log.d("MainActivity", "onClick for show users called");
                Intent intent = new Intent(MainActivity.this, ShowUserActivity.class);
                startActivity(intent);

            }
        });

        Button flight_button = findViewById(R.id.show_flights);

        flight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call the ShowFlight activity
                Log.d("MainActivity", "onClick for show flights called");
                Intent intent = new Intent(MainActivity.this, ShowFlightActivity.class);
                startActivity(intent);
            }
        });


        Button sarch_flight_button = findViewById(R.id.search_flights);

        sarch_flight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call the ShowFlight activity
                Log.d("MainActivity", "onClick for search flights called");
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });*/

        //TODO add addition main buttons and controller methods for
        //  Cancel Reservation,
        //  Add Flight (administrator only),
        //  View Log (administrator only)
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
