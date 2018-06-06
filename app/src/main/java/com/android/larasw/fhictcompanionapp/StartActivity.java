package com.android.larasw.fhictcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Res Non Verba on 21/09/2017.
 */

public class StartActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener {

    public static String token;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    @Override
    public void onFragmentInteraction(String token) {
        this.token=token;
        Intent intent = new Intent(StartActivity.this, MenuActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
        finish();
    }
}
