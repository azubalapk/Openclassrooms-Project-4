package com.example.projet_3_oc_maru.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_3_oc_maru.activities.MainActivity;
import com.example.projet_3_oc_maru.di.DI;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.service.MeetingApiService;
import com.example.projet_3_oc_maru.ui.AdapterMain;
import java.util.List;

public class MainFragment extends Fragment implements AdapterMain.OnCallbackAdapterToMainFragment {
    MeetingApiService mApiService;
    RecyclerView mRecyclerView;
    AdapterMain mAdapter;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        initList(mApiService.getMeetings());

        return view;
    }

    public void initList(List<Meeting> meetings) {
        mAdapter =new AdapterMain(meetings);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnCallbackAdapterToMainFragment(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void shareCallbackAdapterToMainFragment() {
        MainActivity activity = (MainActivity) getActivity();
        activity.resetToolbarTitle();
    }

}