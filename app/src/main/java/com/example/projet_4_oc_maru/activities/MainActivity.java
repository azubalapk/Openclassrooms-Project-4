package com.example.projet_4_oc_maru.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.projet_4_oc_maru.R;
import com.example.projet_4_oc_maru.di.DI;
import com.example.projet_4_oc_maru.fragments.MainFragment;
import com.example.projet_4_oc_maru.models.Meeting;
import com.example.projet_4_oc_maru.service.MeetingApiService;
import com.example.projet_4_oc_maru.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.joda.time.LocalDate;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;
    MainFragment mainFragment;
    MeetingApiService apiService;
    int month, year, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        userClickOnButtonForOpenAddMeetingActivity();
        apiService = DI.getMeetingApiService();
    }

    public void setUpViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.outline_filter_list_white_24));
        fab = findViewById(R.id.fab);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.MainFragment);
    }

    public void userClickOnButtonForOpenAddMeetingActivity() {
        fab.setOnClickListener(v -> {
            Intent AddMeetingActivity = new Intent(MainActivity.this, AddActivity.class);
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
                toolbar.setTitle(R.string.app_name);
                mainFragment.initList(apiService.getMeetings());
                return true;

            case R.id.selection_salle:
                return true;

            case R.id.selection_date:

                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        (view, year, monthOfYear, dayOfMonth) -> {

                            filterItemDate(new LocalDate(year, monthOfYear + 1, dayOfMonth));

                        }, year, month, day);
                datePickerDialog.show();

                return true;

            case R.id.Peach:
                filterItemRoom(getString(R.string.name_ofRoomPeach));
                return true;

            case R.id.Mario:
                filterItemRoom(getString(R.string.name_ofRoomMario));
                return true;

            case R.id.Luigi:
                filterItemRoom(getString(R.string.name_ofRoomLuigi));
                return true;

            case R.id.Toad:
                filterItemRoom(getString(R.string.name_ofRoomToad));
                return true;

            case R.id.Bowser:
                filterItemRoom(getString(R.string.name_ofRoomBowser));
                return true;

            case R.id.Yoshi:
                filterItemRoom(getString(R.string.name_ofRoomYoshi));
                return true;

            case R.id.Wario:
                filterItemRoom(getString(R.string.name_ofRoomWario));
                return true;

            case R.id.Daisy:
                filterItemRoom(getString(R.string.name_ofRoomDaisy));
                return true;

            case R.id.Harmonie:
                filterItemRoom(getString(R.string.name_ofRoomHarmonie));
                return true;

            case R.id.Pokey:
                filterItemRoom(getString(R.string.name_ofRoomPokey));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filterItemRoom(String salle) {
        /* Filtre par salle */
        boolean nothing = true;
        for (Meeting m : apiService.getMeetings()) {
            if (m.getMeetingRoom().getNameRoomMeeting().equals(salle)) {
                nothing = false;
                break;
            }
        }
        if (!nothing) {
            toolbar.setTitle(getString(R.string.app_name_filter) + salle);
            mainFragment.initList(apiService.getMeetingsFilterRoom(salle));
        } else {
            toolbar.setTitle(getString(R.string.app_name));
            ToastUtil.displayToastLong(getString(R.string.toast_noMeetingScheduledInThisRoom), getApplicationContext());

        }
    }

    private void filterItemDate(LocalDate date) {
        /* Filtre par salle */
        boolean nothing = true;
        for (Meeting m : apiService.getMeetings()) {
            if (m.getDateTimeBegin().toLocalDate().equals(date)) {
                nothing = false;
                break;
            }
        }
        if (!nothing) {
            toolbar.setTitle(getString(R.string.app_name_filter) + date.toString());
            mainFragment.initList(apiService.getMeetingsFilterDate(date));
        } else {
            toolbar.setTitle(getString(R.string.app_name));
            ToastUtil.displayToastLong(getString(R.string.toast_noMeetingScheduledForThisDate), getApplicationContext());

        }
    }


}