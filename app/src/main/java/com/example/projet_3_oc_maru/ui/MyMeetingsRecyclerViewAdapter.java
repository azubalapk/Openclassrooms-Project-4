package com.example.projet_3_oc_maru.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_3_oc_maru.Activities.MainActivity;
import com.example.projet_3_oc_maru.Fragments.MainFragment;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;
import com.example.projet_3_oc_maru.R;




import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MyMeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingsRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    OnShareClickedListener mCallback;

    public MyMeetingsRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    public void setOnShareClickedListener(OnShareClickedListener mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnShareClickedListener {
         void ShareClicked(Meeting meeting);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_meeting, parent, false);
        return new ViewHolder(view);
    }







        @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mMeetingLineOne.setText("Réunion "+meeting.getId()+",le "+meeting.getDate().toString()+", \nde "+meeting.getTimeBegin().toString()+" à "+meeting.getTimeEnd().toString()+","+"\nSalle "+ meeting.getMeetingRoom().getmNameRoomMeeting()+", à propos de "+ meeting.getSubject());
        holder.mMeetingLineTwo.setText(meeting.getParticipants());
        holder.mMeetingAvatar.setColorFilter(meeting.getMeetingRoom().getmRoomMeetingColor());

        holder.mDeleteButton.setOnClickListener(v -> {
            mCallback.ShareClicked(meeting);

        });
    }
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mMeetingAvatar;
        public TextView mMeetingLineOne;
        public TextView mMeetingLineTwo;

        public ImageButton mDeleteButton;


        public ViewHolder(View view) {
            super(view);
            mMeetingAvatar= view.findViewById(R.id.item_list_avatar);
            mMeetingLineOne=view.findViewById(R.id.item_line_One);
            mMeetingLineTwo=view.findViewById(R.id.item_line_Two);
            mDeleteButton=view.findViewById(R.id.item_list_delete_button);


        }
    }


}
