package com.example.projet_3_oc_maru.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.projet_3_oc_maru.DI.DI;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;
import com.example.projet_3_oc_maru.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        UserClickOnButtonForOpenAddMeetingActivity();
    }

    public void setUpViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
    }

    public void UserClickOnButtonForOpenAddMeetingActivity(){
        fab.setOnClickListener(v -> {
            Intent AddMeetingActivity = new Intent(MainActivity.this, AddMeetingActivity.class);
            startActivity(AddMeetingActivity);
        });
    }

}