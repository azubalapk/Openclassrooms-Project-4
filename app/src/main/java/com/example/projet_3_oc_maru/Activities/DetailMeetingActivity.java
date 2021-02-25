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

    Meeting meeting;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getMeetingParcelable();
        meetingNotNull();

    }
    public void getMeetingParcelable(){
        meeting = getIntent().getParcelableExtra("detailMeeting");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void meetingNotNull(){
        if (meeting != null) {
            setUpViews();
            setTextAndImage();
        }
    }

    public void setUpViews(){
        avatarMeeting = findViewById(R.id.item_detail_avatar);
        detailLineOne = findViewById(R.id.detailLineOne);
        detailLineTwo =findViewById(R.id.detailLineTwo);
        detailLineThree = findViewById(R.id.detailLineThree);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setTextAndImage(){
        avatarMeeting.setColorFilter(meeting.getMeetingRoom().getmRoomMeetingColor());
        detailLineOne.setText("  Réunion "+meeting.getId()+"-"+meeting.getDateTimeBegin().getYear()+"/"+meeting.getDateTimeBegin().getMonthValue()+"/"+meeting.getDateTimeBegin().getDayOfMonth()+
                "-"+meeting.getDateTimeBegin().getHour()+"h"+meeting.getDateTimeBegin().getMinute()+"/"+meeting.getDateTimeEnd().getHour()+"h"+meeting.getDateTimeEnd().getMinute());

        detailLineTwo.setText("  Sujet :"+meeting.getSubject()+", Salle :"+meeting.getMeetingRoom().getmNameRoomMeeting()+"("+meeting.getMeetingRoom().getId()+")");
        detailLineThree.setText("  Particpants :"+meeting.getParticipants());
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