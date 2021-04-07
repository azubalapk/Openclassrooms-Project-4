package com.example.projet_4_oc_maru.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_4_oc_maru.R;
import com.example.projet_4_oc_maru.di.DI;
import com.example.projet_4_oc_maru.models.Meeting;
import com.example.projet_4_oc_maru.service.MeetingApiService;
import com.example.projet_4_oc_maru.ui.AdapterMeetings;

import java.util.List;

public class MainFragment extends Fragment {
    MeetingApiService apiService;
    RecyclerView recyclerView;
    AdapterMeetings adapter;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        initList(apiService.getMeetings());
        return view;
    }

    public void initList(List<Meeting> meetings) {
        adapter = new AdapterMeetings(meetings);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}