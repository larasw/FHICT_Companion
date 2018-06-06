package com.android.larasw.fhictcompanionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.larasw.fhictcompanionapp.Adapter.NewsTagAdapter;

/**
 * Created by Res Non Verba on 11/10/2017.
 */

public class NewsActivity extends AppCompatActivity {

    private TextView mTitle, mDate, mContent, mLink;
    private ImageView mPicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mTitle = (TextView) findViewById(R.id.newsTitle);
        mDate = (TextView) findViewById(R.id.newsTime);
        mContent = (TextView) findViewById(R.id.newsContent);
        mLink = (TextView) findViewById(R.id.newsLink);
        mPicture = (ImageView) findViewById(R.id.newsPicture);

        Intent intent = getIntent();
        mTitle.setText(intent.getStringExtra("title"));
        mDate.setText(intent.getStringExtra("date"));
        mContent.setText(Html.fromHtml(intent.getStringExtra("content")));

        mPicture.setImageBitmap(NewsTagAdapter.image);

        final String link = intent.getStringExtra("link");

        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLink = new Intent(Intent.ACTION_VIEW);
                intentLink.setData(Uri.parse(link));
                startActivity(intentLink);
            }
        });
    }
}
