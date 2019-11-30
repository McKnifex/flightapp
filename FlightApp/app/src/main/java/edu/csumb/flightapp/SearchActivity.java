package edu.csumb.flightapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.Reservation;

public class SearchActivity extends AppCompatActivity {

    public static List<Flight> flights = new ArrayList<Flight>();
    public static Flight selectedFlight = null;
    public static int amountTickets = 0;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SearchActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button return_main_button = findViewById(R.id.return_button);
        return_main_button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("SearchActivity", "onClick return called");
                finish();
            }
        });

        Button search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("SearchActivity", "onClick search called");
                EditText from = findViewById(R.id.from_city);
                EditText to = findViewById(R.id.to_city);
                EditText no_tickets = findViewById(R.id.no_tickets);

                /*if(from.getText().toString().isEmpty()|to.getText().toString().isEmpty()
                        ||no_tickets.getText().toString().isEmpty()){
                    Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
                    finish();
                    startActivity(intent);
                }*/

                /*LAB* Check if tickets between 1 and 7 */
                int amountTickets = Integer.parseInt(no_tickets.getText().toString());

                if(amountTickets > 0 && amountTickets <= 7){
                    flights = FlightRoom.getFlightRoom(SearchActivity.this).dao().
                            searchFlight(from.getText().toString(),
                                    to.getText().toString(),amountTickets);
                    if(flights.isEmpty()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                        builder.setTitle("There are no flights available.");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SearchActivity.this,
                                        SearchActivity.class);

                                startActivity(intent);
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                    SearchActivity.amountTickets = amountTickets;
                    // notify recycler view that list of flights has changed
                    adapter.notifyDataSetChanged();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                    builder.setTitle("Due to ticket restriction, you can only buy up to 7 tickets.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(SearchActivity.this,
                                    SearchActivity.class);

                            startActivity(intent);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });

        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager( new LinearLayoutManager(this));
        adapter = new Adapter();
        rv.setAdapter( adapter );

    }

    private class Adapter  extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(SearchActivity.this);
            return new  ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position){
            holder.bind(flights.get(position));
        }

        @Override
        public int getItemCount() { return flights.size(); }

    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Flight f ) {
            TextView item = itemView.findViewById(R.id.item_id);
            item.setText(f.toString());

            // make the item clickable
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedFlight = f;
                    flights.clear();
                    Intent intent = new Intent(SearchActivity.this, CreateReservationActivity.class);
                    finish();
                    startActivity(intent);
                }
            });


        }
    }
}
