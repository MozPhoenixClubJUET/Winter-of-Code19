package com.example.clubcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clubcalendar.model.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class addEventActivity extends AppCompatActivity {

    EditText name, date, description, venue, clubName;
    Button add, cancel;

    DatabaseReference databaseTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        name = findViewById(R.id.event_name);
        date = findViewById(R.id.date);
        venue = findViewById(R.id.venue);
        clubName = findViewById(R.id.club_name);
        description = findViewById(R.id.description);
        add = findViewById(R.id.add_event);

        //adding event
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName = name.getText().toString().trim();
                String eventDate = date.getText().toString().trim();
                String eventdesc = description.getText().toString().trim();
                String eventVenue = venue.getText().toString().trim();
                String eventClubName = clubName.getText().toString().trim();

                if ((!TextUtils.isEmpty(eventName)) && (!TextUtils.isEmpty(eventDate))) {
                    databaseTracks = FirebaseDatabase.getInstance().getReference("Events (club)").child(getIntent().getStringExtra("usid"));
                    String id  = databaseTracks.push().getKey();
                    Event event = new Event(eventName, eventDate, eventdesc, eventVenue, eventClubName);
                    databaseTracks.child(id).setValue(event);

                    databaseTracks = FirebaseDatabase.getInstance().getReference("Events (all)");
                    databaseTracks.child(id).setValue(event);
                    Toast.makeText(addEventActivity.this, "Event saved", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(addEventActivity.this, clubHomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(addEventActivity.this, "Please enter details", Toast.LENGTH_LONG).show();
                }
            }
        });

        //cancel adding the event
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addEventActivity.this, clubHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
