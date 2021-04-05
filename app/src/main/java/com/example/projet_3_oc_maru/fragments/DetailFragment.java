package com.example.projet_3_oc_maru.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.activities.DetailActivity;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.ui.AdapterMeetings;
import com.example.projet_3_oc_maru.ui.AdapterParticipants;

import java.util.List;


public class DetailFragment extends Fragment {

    AdapterParticipants adapterParticipants;
    RecyclerView recyclerView;
    DetailActivity activity;

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        getDetailActivity();
        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        initList(activity.getListParticipants());
        return view;
    }

    public void getDetailActivity() {
        activity = (DetailActivity) getActivity();
    }

    public void initList(List<String> participants) {
        adapterParticipants = new AdapterParticipants(participants);
        recyclerView.setAdapter(adapterParticipants);
    }
}