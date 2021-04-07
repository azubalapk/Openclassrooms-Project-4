package com.example.projet_4_oc_maru.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_4_oc_maru.R;

import java.util.List;


public class AdapterParticipants extends RecyclerView.Adapter<AdapterParticipants.ViewHolder> {

    private final List<String> participants;

    public AdapterParticipants(List<String> items) {
        participants = items;
    }

    @NonNull
    @Override
    public AdapterParticipants.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_participant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String participant = participants.get(position);
        holder.textView.setText(participant);

    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewParticipantRecyclerView);
        }
    }
}
