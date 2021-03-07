package com.example.projet_3_oc_maru.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_3_oc_maru.R;

import java.util.List;


public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.ViewHolder> {

    // Store a member variable for the contacts
    private final List<String> mParticipants;

    // Pass in the contact array into the constructor
    public AdapterDetail(List<String> items) {
        mParticipants = items;
    }

    @Override
    public AdapterDetail.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_participant, parent, false);
        return new ViewHolder(view);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String participant = mParticipants.get(position);
        holder.textView.setText(participant);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mParticipants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewParticipantRecyclerView20);

        }
    }
}
