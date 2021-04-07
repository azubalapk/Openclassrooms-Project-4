package com.example.projet_4_oc_maru.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_4_oc_maru.R;
import com.example.projet_4_oc_maru.models.Meeting;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    ImageView imageViewDetailMeet;
    TextView textViewDetailHourMeetBegin, textViewDetailHourMeetEnd, textViewDetailIdMeet, textViewDetailDateMeet, textViewDetailSubjectMeet, textViewDetailRoomMeet;
    Meeting meeting;
    List<String> participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getMeetingParcelable();
        meetingNotNull();
        participants = meeting.getParticipants();
    }

    public void getMeetingParcelable() {
        meeting = getIntent().getParcelableExtra("detailMeeting");
    }

    public void meetingNotNull() {
        if (meeting != null) {
            setUpViews();
            setTextAndImage();
        }
    }

    public void setUpViews() {
        imageViewDetailMeet = findViewById(R.id.imageViewDetailMeet);
        textViewDetailIdMeet = findViewById(R.id.textViewDetailIdMeet);
        textViewDetailSubjectMeet = findViewById(R.id.textViewDetailSubjectMeet);
        textViewDetailHourMeetBegin = findViewById(R.id.textViewDetailHourMeetBegin);
        textViewDetailHourMeetEnd = findViewById(R.id.textViewDetailHourMeetEnd);
        textViewDetailDateMeet = findViewById(R.id.textViewDetailDateMeet);
        textViewDetailRoomMeet = findViewById(R.id.textViewDetailRoomMeet);
    }

    public void setTextAndImage() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
        imageViewDetailMeet.setColorFilter(meeting.getMeetingRoom().getRoomMeetingColor());
        textViewDetailIdMeet.setText("Numéro : " + meeting.getId());
        textViewDetailSubjectMeet.setText("A propos de : " + meeting.getSubject());
        textViewDetailDateMeet.setText("Date : " + meeting.getDateTimeBegin().toLocalDate().toString());
        textViewDetailRoomMeet.setText("Salle : " + meeting.getMeetingRoom().getNameRoomMeeting());
        textViewDetailHourMeetBegin.setText("Début :" + meeting.getDateTimeBegin().toLocalTime().toString(fmt));
        textViewDetailHourMeetEnd.setText("Fin :" + meeting.getDateTimeEnd().toLocalTime().toString(fmt));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<String> getListParticipants() {
        return participants;
    }

}