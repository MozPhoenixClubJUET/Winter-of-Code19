package com.example.clubcalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clubcalendar.model.Event;
import com.example.clubcalendar.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class clubHomeActivity extends AppCompatActivity {

    TextView username;
    FloatingActionButton add;

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    User user;

    ListView listViewEvents;

    //our database reference object
    DatabaseReference databaseEvents;
    String usernamecurr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        listViewEvents = findViewById(R.id.list_view);
        username = findViewById(R.id.username);
        add = findViewById(R.id.add_event_floating_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clubHomeActivity.this, addEventActivity.class);
                intent.putExtra("usid", firebaseUser.getUid());
                startActivity(intent);
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        Toast.makeText(clubHomeActivity.this, "We will get it ready in 5 seconds", Toast.LENGTH_SHORT).show();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                username.setText(user.getName());
                usernamecurr = user.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();

        //getting the reference of events (club) node
        databaseEvents = FirebaseDatabase.getInstance().getReference("Events (club)").child(firebaseUser.getUid());

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
                EventList eventAdapter = new EventList(clubHomeActivity.this, events);
                //attaching adapter to the listview
                listViewEvents.setAdapter(eventAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(clubHomeActivity.this, userChoiceActivity.class));
                Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();
                finish();
                return true;

            case R.id.resetpassword:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(clubHomeActivity.this, forgot_password.class));
                Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }

        return false;
    }
}
