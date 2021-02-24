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
import com.example.projet_3_oc_maru.Activities.DetailMeetingActivity;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.R;
import java.util.List;

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

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mMeetingLineOne.setText("Réunion "+meeting.getId()+"-"+meeting.getDateTimeBegin().getYear()+"/"+meeting.getDateTimeBegin().getMonthValue()+"/"+meeting.getDateTimeBegin().getDayOfMonth()+"-"+meeting.getDateTimeBegin().getHour()+"h"+meeting.getDateTimeBegin().getMinute()+"/"+meeting.getDateTimeEnd().getHour()+"h"+meeting.getDateTimeEnd().getMinute()+"\n"+"Salle "+ meeting.getMeetingRoom().getmNameRoomMeeting());
        holder.mMeetingLineTwo.setText(meeting.getParticipants());
        holder.mMeetingAvatar.setColorFilter(meeting.getMeetingRoom().getmRoomMeetingColor());

        holder.mDeleteButton.setOnClickListener(v -> {
            mCallback.ShareClicked(meeting);
        });
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
