package com.example.clubcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clubcalendar.model.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateActivity extends AppCompatActivity {

    EditText name, date, description, venue, clubName;
    Button update, cancel;

    DatabaseReference databaseTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.event_name);
        name.setText(getIntent().getStringExtra("eName"));
        date = findViewById(R.id.date);
        date.setText(getIntent().getStringExtra("date"));
        venue = findViewById(R.id.venue);
        venue.setText(getIntent().getStringExtra("eVenue"));
        clubName = findViewById(R.id.club_name);
        clubName.setText(getIntent().getStringExtra("cName"));
        description = findViewById(R.id.description);
        description.setText(getIntent().getStringExtra("eDesc"));

        //updating the event
        update = findViewById(R.id.update_event);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName = name.getText().toString().trim();
                String eventDate = date.getText().toString().trim();
                String eventdesc = description.getText().toString().trim();
                String eventVenue = venue.getText().toString().trim();
                String eventClubName = clubName.getText().toString().trim();

                if ((!TextUtils.isEmpty(eventName)) && (!TextUtils.isEmpty(eventDate))) {
                    databaseTracks = FirebaseDatabase.getInstance().getReference("Events (club)").child(getIntent().getStringExtra("usid"));
                    String id  = getIntent().getStringExtra("eId");
                    Event event = new Event(eventName, eventDate, eventdesc, eventVenue, eventClubName);
                    databaseTracks.child(id).setValue(event);

                    databaseTracks = FirebaseDatabase.getInstance().getReference("Events (all)");
                    databaseTracks.child(id).setValue(event);
                    Toast.makeText(updateActivity.this, "Event updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(updateActivity.this, clubHomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(updateActivity.this, "Please enter details", Toast.LENGTH_LONG).show();
                }

            }
        });



        //cancel updating the event
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updateActivity.this, clubHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
