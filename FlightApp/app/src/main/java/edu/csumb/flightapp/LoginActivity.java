package edu.csumb.flightapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.csumb.flightapp.model.FlightDao;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LoginActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                    // special admin userid
                    MainActivity.username = username.getText().toString();
                    Intent intent = new Intent(LoginActivity.this, ViewLogActivity.class);
                    startActivity(intent);
                    finish();
                }

                String name = username.getText().toString();
                String pw = password.getText().toString();

                FlightDao dao = FlightRoom.getFlightRoom(LoginActivity.this).dao();
                User user = dao.login(name, pw);
                if (user == null) {
                    // unsuccessful login
                    TextView msg = findViewById(R.id.message);
                    msg.setText("User name or password is invalid.");

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("You are not the administrator!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

            }
        });

    }
}
