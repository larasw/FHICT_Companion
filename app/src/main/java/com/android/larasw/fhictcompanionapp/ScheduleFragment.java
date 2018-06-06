package com.android.larasw.fhictcompanionapp;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.larasw.fhictcompanionapp.Adapter.ScheduleAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private MaterialCalendarView mCalendarView;
    private String mDate, mDay, mMonth, mYear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCalendarView = (MaterialCalendarView) view.findViewById(R.id.datePicker);
        mCalendarView.setSelectedDate(CalendarDay.today());

        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDay = String.valueOf(date.getDay());
                mMonth = String.valueOf(date.getMonth()+1);
                mYear = String.valueOf(date.getYear());

                validateDate(mDay, mMonth);
                mDate = mYear+ "-" +mMonth+ "-" +mDay;

                Intent intent = new Intent(getActivity(), ScheduleListActivity.class);
                intent.putExtra("date", mDate);
                startActivity(intent);
            }
        });
    }

    public void validateDate(String date, String month){
        if(date.length() == 1){
            mDay = "0" +mDay;
        }

        if(month.length() == 1){
            mMonth = "0" +mMonth;
        }
    }
}
