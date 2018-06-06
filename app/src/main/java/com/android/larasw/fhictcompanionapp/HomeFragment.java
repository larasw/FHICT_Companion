package com.android.larasw.fhictcompanionapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.larasw.fhictcompanionapp.Adapter.PagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ImageButton mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMap = (ImageButton) view.findViewById(R.id.fontysMap);

        mMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri geoIntengUri = Uri.parse("geo:51.452095,5.481963?q=" + Uri.encode("Fontys - Campus Rachelsmolen"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoIntengUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}
