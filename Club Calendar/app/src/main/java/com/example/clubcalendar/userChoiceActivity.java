package com.example.clubcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userChoiceActivity extends AppCompatActivity {

    ImageButton student;
    ImageButton clubmember;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is null and redirects to main activity
        if(firebaseUser != null){
            Intent intent = new Intent(userChoiceActivity.this, clubHomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);

        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun) {
            startActivity(new Intent(userChoiceActivity.this, MainActivity.class));
            Toast.makeText(userChoiceActivity.this, "Welcome", Toast.LENGTH_LONG)
                    .show();
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

        student = findViewById(R.id.student);
        clubmember = findViewById(R.id.clubmember);

        clubmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userChoiceActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userChoiceActivity.this, eventsActivity.class);
                startActivity(intent);
            }
        });


    }
}
