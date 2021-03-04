package com.example.projet_3_oc_maru.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.di.DI;
import com.example.projet_3_oc_maru.fragments.MainFragment;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.service.MeetingApiService;
import com.example.projet_3_oc_maru.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;
    MainFragment mainFragment;
    MeetingApiService mApiService;
    int mMonth,mYear,mDay;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        userClickOnButtonForOpenAddMeetingActivity();
        mApiService = DI.getMeetingApiService();


    }

    public void setUpViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.MainFragment);

    }

    public void userClickOnButtonForOpenAddMeetingActivity(){
        fab.setOnClickListener(v -> {

            Intent AddMeetingActivity = new Intent(MainActivity.this, AddMeetingActivity.class);
            startActivity(AddMeetingActivity);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.allSalles:
                mainFragment.initList(mApiService.getMeetings());
                return true;

            case R.id.selection_salle:

                return true;

            case R.id.selection_date:

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        (view, year, monthOfYear, dayOfMonth) -> {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                filterItemDate(LocalDate.of(year,monthOfYear+1,dayOfMonth));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                return true;

            case R.id.Peach:
                filterItemRoom("Peach");
                toolbar.setTitle("Ma réunion - Salle Peach");
                return true;

            case R.id.Mario:
                filterItemRoom("Mario");
                toolbar.setTitle("Ma réunion - Salle Mario");
                return true;

            case R.id.Luigi:
                filterItemRoom("Luigi");
                toolbar.setTitle("Ma réunion - Salle Luigi");
                return true;

            case R.id.Toad:
                filterItemRoom("Toad");
                toolbar.setTitle("Ma réunion - Salle Toad");
                return true;

            case R.id.Bowser:
                filterItemRoom("Bowser");
                toolbar.setTitle("Ma réunion - Salle Bowser");
                return true;

            case R.id.Yoshi:
                filterItemRoom("Yoshi");
                toolbar.setTitle("Ma réunion - Salle Yoshi");
                return true;

            case R.id.Wario:
                filterItemRoom("Wario");
                toolbar.setTitle("Ma réunion - Salle Wario");
                return true;

            case R.id.Daisy:
                filterItemRoom("Daisy");
                toolbar.setTitle("Ma réunion - Salle Daisy");
                return true;

            case R.id.Harmonie:
                filterItemRoom("Harmonie");
                toolbar.setTitle("Ma réunion - Salle Harmonie");
                return true;

            case R.id.Pokey:
                filterItemRoom("Pokey");
                toolbar.setTitle("Ma réunion - Salle Pokey");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filterItemRoom(String salle) {

        /* Filtre par salle */
        boolean nothing = true;
        for (Meeting m : mApiService.getMeetings()) {
            if (m.getMeetingRoom().getmNameRoomMeeting().equals(salle)) {
                nothing = false;
                break;
            }
        }
        if (!nothing) {
            mainFragment.initList(mApiService.getMeetingsFilterRoom(salle));
        } else {
            ToastUtil.DisplayToastLong("Aucune réunion de prévue dans cette salle", getApplicationContext());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void filterItemDate(LocalDate date) {

        /* Filtre par salle */
        boolean nothing = true;
        for (Meeting m : mApiService.getMeetings()) {
            if (m.getDateTimeBegin().toLocalDate().equals(date)) {
                nothing = false;
                break;
            }
        }
        if (!nothing) {
            mainFragment.initList(mApiService.getMeetingsFilterDate(date));
        } else {
            ToastUtil.DisplayToastLong("Aucune réunion de prévue dans cette date", getApplicationContext());
        }
    }





}