package com.example.projet_3_oc_maru.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projet_3_oc_maru.activities.DetailMeetingActivity;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.R;
import java.util.List;

public class MyMeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingsRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    OnCallbackAdapterToMainFragment mCallback;

    public MyMeetingsRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    public void setOnCallbackAdapterToMainFragment(OnCallbackAdapterToMainFragment mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnCallbackAdapterToMainFragment {
         void shareCallbackAdapterToMainFragment(Meeting meeting);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_meeting, parent, false);
        return new ViewHolder(view);
    }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);

        holder.imageViewMeet.setColorFilter(meeting.getMeetingRoom().getmRoomMeetingColor());

        holder.textViewIdMeet.setText("Réunion "+meeting.getId()+"-");

        if(meeting.getDateTimeBegin().getMonthValue()<10){
            holder.textViewDateMeet.setText(meeting.getDateTimeBegin().getDayOfMonth()+"/0"+meeting.getDateTimeBegin().getMonthValue()+"/"+meeting.getDateTimeBegin().getYear()+"-");
        }else{
            holder.textViewDateMeet.setText(meeting.getDateTimeBegin().getDayOfMonth()+"/"+meeting.getDateTimeBegin().getMonthValue()+"/"+meeting.getDateTimeBegin().getYear()+"-");
        }

        holder.textViewRoomMeet.setText("Salle "+ meeting.getMeetingRoom().getmNameRoomMeeting());

        if (meeting.getDateTimeBegin().getMinute()==0 ){
            holder.textViewHoursMeet.setText(meeting.getDateTimeBegin().getHour()+"h"+meeting.getDateTimeBegin().getMinute()+"0/"+meeting.getDateTimeEnd().getHour()+"h"+meeting.getDateTimeEnd().getMinute()+"0");
        }else{
            holder.textViewHoursMeet.setText(meeting.getDateTimeBegin().getHour()+"h"+meeting.getDateTimeBegin().getMinute()+"0/"+meeting.getDateTimeEnd().getHour()+"h"+meeting.getDateTimeEnd().getMinute()+"0");
        }

        holder.textViewParticipantsMeet.setText(meeting.getParticipants());

        holder.imageButtonDeleteMeet.setOnClickListener(v -> { mCallback.shareCallbackAdapterToMainFragment(meeting);});

        holder.itemView.setOnClickListener(v -> {
            final Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, DetailMeetingActivity.class);
            intent.putExtra("detailMeeting", meeting);
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewMeet;
        public TextView textViewIdMeet;
        public TextView textViewDateMeet;
        public TextView textViewHoursMeet;
        public TextView textViewRoomMeet;
        public TextView textViewParticipantsMeet;
        public ImageButton imageButtonDeleteMeet;

        public ViewHolder(View view) {
            super(view);
            imageViewMeet= view.findViewById(R.id.imageViewMeet);
            textViewIdMeet=view.findViewById(R.id.textViewIdMeet);
            textViewDateMeet=view.findViewById(R.id.textViewDateMeet);
            textViewHoursMeet=view.findViewById(R.id.textViewHoursMeet);
            textViewRoomMeet=view.findViewById(R.id.textViewRoomMeet);
            textViewParticipantsMeet= view.findViewById(R.id.textViewParticipantsMeet);
            imageButtonDeleteMeet=view.findViewById(R.id.imageButtonDeleteMeet);
        }
    }

}
