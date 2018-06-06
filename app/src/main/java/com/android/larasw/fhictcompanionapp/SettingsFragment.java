package com.android.larasw.fhictcompanionapp;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.larasw.fhictcompanionapp.Adapter.SettingsAdapter;
import com.android.larasw.fhictcompanionapp.Model.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Settings> settings = new ArrayList<>();
    private SettingsAdapter mAdapter;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_settings);
        mAdapter = new SettingsAdapter(settings);
        getSettingsMenu();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getSettingsMenu(){
        settings.add(new Settings(R.drawable.ic_person, "Profile"));
        settings.add(new Settings(R.drawable.ic_event, "Calendar"));
        settings.add(new Settings(R.drawable.ic_notifications, "Announcements"));
        settings.add(new Settings(R.drawable.ic_info, "About FHICT Companion App"));
        settings.add(new Settings(R.drawable.ic_power, "Log Out"));

        mAdapter.notifyDataSetChanged();
    }

}
