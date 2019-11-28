package edu.csumb.flightapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Dao;
import edu.csumb.flightapp.model.FlightDao;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.User;

public class CreateAccountActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CrateAccountActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button create_button = findViewById(R.id.create_account_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);

                // TODO check that username and password meets requirements
                //   one special char, one uppercase and one lowercase letters, one digit

                if (username.getText().toString().equals("!admiM2")) {
                    // username already exists.
                    TextView msg = findViewById(R.id.message);
                    msg.setText("Username not available.");
                    return;
                }

                User user = FlightRoom.getFlightRoom(CreateAccountActivity.this).
                        dao().getUserByName(username.getText().toString());

                if (user == null) {
                    // username does not exist, so add the new account
                    String name = username.getText().toString();
                    String pw = password.getText().toString();

                    User newUser = new User(name, pw);
                    FlightDao dao = FlightRoom.getFlightRoom(CreateAccountActivity.this).dao();
                    dao.addUser(newUser);

                    //TODO  write a record to Log table with message that new Account has been created.
                    //  include username (but not password) in the message.

                    // inform user that new account has been created
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);
                    builder.setTitle("Account successfully created.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    // username already exists.
                    TextView msg = findViewById(R.id.message);
                    msg.setText("Username not available.");
                }

            }
        });
    }
}