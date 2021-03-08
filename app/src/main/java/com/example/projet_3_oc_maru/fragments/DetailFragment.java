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
import com.example.projet_3_oc_maru.activities.DetailMeetingActivity;
import com.example.projet_3_oc_maru.ui.AdapterDetail;

import java.util.List;


public class DetailFragment extends Fragment {

    AdapterDetail adapterDetail;
    RecyclerView recyclerView;
    List<String> participants;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        DetailMeetingActivity activity = (DetailMeetingActivity) getActivity();
        participants = activity.getListParticipants();
        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapterDetail =new AdapterDetail(participants);
        recyclerView.setAdapter(adapterDetail);
        // Inflate the layout for this fragment
        return view;
    }
}