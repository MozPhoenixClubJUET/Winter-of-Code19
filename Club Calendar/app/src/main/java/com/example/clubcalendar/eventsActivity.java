package com.example.clubcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clubcalendar.model.Event;
import com.example.clubcalendar.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class eventsActivity extends AppCompatActivity {

    TextView username;
    User user;

    ListView listViewEvents;

    //our database reference object
    DatabaseReference databaseEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        username = findViewById(R.id.username);
        username.setText("Events");

        listViewEvents = findViewById(R.id.list_view);
        Toast.makeText(eventsActivity.this, "We will get it ready in 5 seconds", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart() {
        super.onStart();

        //getting the reference of events (club) node
        databaseEvents = FirebaseDatabase.getInstance().getReference("Events (all)");

        databaseEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Event> events = new ArrayList<>();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting event
                    Event event = postSnapshot.getValue(Event.class);
                    //adding event to the list
                    events.add(event);
                }

                //creating adapter
                EventList eventAdapter = new EventList(eventsActivity.this, events);
                //attaching adapter to the listview
                listViewEvents.setAdapter(eventAdapter);
                listViewEvents.setEmptyView(findViewById(R.id.empty));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
