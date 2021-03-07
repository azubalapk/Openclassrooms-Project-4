package com.example.projet_3_oc_maru.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_3_oc_maru.R;
import java.util.List;

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.ViewHolder>{
     final List<String> mMeetings;

    public AdapterDetail(List<String> items) {
        mMeetings = items;
    }

    @Override
    public AdapterDetail.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_participant, parent, false);
        return new AdapterDetail.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final AdapterDetail.ViewHolder holder, int position  ) {




        holder.textViewParticipantsMeet.setText(mMeetings.toString());

    }
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewParticipantsMeet;


        public ViewHolder(View view) {
            super(view);

            textViewParticipantsMeet= view.findViewById(R.id.textViewParticipantRecyclerView20);

        }
    }

}
