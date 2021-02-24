package com.example.projet_3_oc_maru.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;
import com.example.projet_3_oc_maru.R;

import java.time.LocalDateTime;
import java.util.Objects;

public class DetailMeetingActivity extends AppCompatActivity {
    ImageView avatarMeeting;
    TextView detailLineOne,detailLineTwo,detailLineThree;
    int idMeeting;
    String subjectMeeting;
    LocalDateTime timeBeginMeeting;
    LocalDateTime timeEndMeeting;
    String participantsMeetings;
    RoomMeeting roomMeeting;
    Meeting meeting;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        prepareApiServiceAndNeighbour();
        meetingNotNull();

    }
    public void prepareApiServiceAndNeighbour(){
        meeting = getIntent().getParcelableExtra("detailMeeting");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void meetingNotNull(){
        if (meeting != null) {
            getMeeting();
            setUpViews();
            setTextAndImage();
        }
    }
    public void getMeeting(){
        idMeeting =meeting.getId();
        subjectMeeting=meeting.getSubject();
        timeBeginMeeting=meeting.getDateTimeBegin();
        timeEndMeeting=meeting.getDateTimeEnd();
        participantsMeetings=meeting.getParticipants();
        roomMeeting=meeting.getMeetingRoom();
    }
    public void setUpViews(){
        avatarMeeting = findViewById(R.id.item_detail_avatar);
        detailLineOne = findViewById(R.id.detailLineOne);
        detailLineTwo =findViewById(R.id.detailLineTwo);
        detailLineThree = findViewById(R.id.detailLineThree);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setTextAndImage(){
        avatarMeeting.setColorFilter(roomMeeting.getmRoomMeetingColor());
        detailLineOne.setText("Réunion "+idMeeting+"-"+timeBeginMeeting.getYear()+"/"+timeBeginMeeting.getMonthValue()+"/"+timeBeginMeeting.getDayOfMonth()+"-"+timeBeginMeeting.getHour()+"h"+timeBeginMeeting.getMinute()+"/"+timeEndMeeting.getHour()+"h"+timeEndMeeting.getMinute());
        detailLineTwo.setText("Sujet"+subjectMeeting+"Salle "+ roomMeeting.getmNameRoomMeeting()+"("+roomMeeting.getId()+")");
        detailLineThree.setText(participantsMeetings);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}