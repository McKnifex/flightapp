package edu.csumb.flightapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.LogRecord;

public class ViewLogActivity  extends AppCompatActivity {

    // private ViewLogAdapter adapter;

    private List<LogRecord> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ViewLogActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button return_main_button = findViewById(R.id.return_button);
        return_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ViewLogActivity", "onClick return called");
                finish();
            }
        });

        //TODO  use a RecylerView to display LogRecords
        //   define adapter and item_holder classes

        // retrieve all log records from database
        // records = FlightRoom.getFlightRoom(this).dao().getLogRecords();
    }
}
