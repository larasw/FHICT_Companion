package com.android.larasw.fhictcompanionapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.larasw.fhictcompanionapp.Adapter.NewsTagAdapter;
import com.android.larasw.fhictcompanionapp.Model.News;
import com.android.larasw.fhictcompanionapp.Model.NewsTag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Res Non Verba on 11/10/2017.
 */

public class NewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NewsTagAdapter mAdapter;
    private List<NewsTag> mNewsTagList;
    private ProgressDialog mDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewsTagList = new ArrayList<>();
        mDialog = new ProgressDialog(getActivity());

        new JSONTask().execute();
    }

    public class JSONTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try{
                List<News> newsFhict = new ArrayList<>();
                URL url = new URL("https://api.fhict.nl/newsfeeds/Fhict/posts");
                String response = urlResponse(url);
                getData(response, newsFhict, 0);

                List<News> newsFhictM = new ArrayList<>();
                URL urlFM = new URL("https://api.fhict.nl/newsfeeds/FhictMededelingen/posts");
                String responseFM = urlResponse(urlFM);
                getData(responseFM, newsFhictM, 1);

                List<News> newsBronN = new ArrayList<>();
                URL urlBN = new URL("https://api.fhict.nl/newsfeeds/BronNieuws/posts");
                String responseBN = urlResponse(urlBN);
                getData(responseBN, newsBronN, 2);

                List<News> newsBronO = new ArrayList<>();
                URL urlBO = new URL("https://api.fhict.nl/newsfeeds/BronOpinie/posts");
                String responseBO = urlResponse(urlBO);
                getData(responseBO, newsBronO, 2);

                List<News> newsBronA = new ArrayList<>();
                URL urlBA = new URL("https://api.fhict.nl/newsfeeds/BronAchtergrond/posts");
                String responseBA = urlResponse(urlBA);
                getData(responseBA, newsBronA, 2);

                mNewsTagList.add(new NewsTag("FHICT", newsFhict));
                mNewsTagList.add(new NewsTag("FHICT Mededelingen", newsFhictM));
                mNewsTagList.add(new NewsTag("Bron Nieuws", newsBronN));
                mNewsTagList.add(new NewsTag("Bron Opinie", newsBronO));
                mNewsTagList.add(new NewsTag("Bron Achtergrond", newsBronA));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog.setTitle("Loading");
            mDialog.setMessage("Please Wait...");
            mDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mDialog.dismiss();

            mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerViewExpandable);
            mAdapter = new NewsTagAdapter(mNewsTagList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mAdapter);

        }

        public String urlResponse(URL url){
            try{
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Authorization", "Bearer " + StartActivity.token);
                connection.connect();

                InputStream is = new BufferedInputStream(connection.getInputStream());
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                String response = sb.toString();

                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
        }

        public void getData(String response, List<News> newsList, int format){
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    URL urlThumbnail = new URL(jsonObject.optString("thumbnail"));
                    Bitmap thumbnail = BitmapFactory.decodeStream(getPicture(urlThumbnail));

                    Bitmap image;
                    if(format == 1){
                        image = null;
                    } else {
                        URL urlImage = new URL(jsonObject.optString("image"));
                        image = BitmapFactory.decodeStream(getPicture(urlImage));
                    }

                    String title = jsonObject.optString("title");
                    String content = jsonObject.optString("content");
                    String link = jsonObject.optString("link");

                    String oldDate;
                    if(format < 2){
                        oldDate = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                    } else {
                        oldDate = "yyyy-MM-dd'T'HH:mm:ss";
                    }

                    String newDate = "dd/MM/yyyy HH:mm";
                    SimpleDateFormat sdf = new SimpleDateFormat(oldDate);
                    Date pubDate = sdf.parse(jsonObject.optString("pubDate"));
                    sdf.applyPattern(newDate);
                    String newsDate = sdf.format(pubDate);

                    newsList.add(new News(thumbnail, image, title, content, link, newsDate));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        public InputStream getPicture(URL url){
            try{
                HttpURLConnection connectionPhoto = (HttpURLConnection) url.openConnection();
                connectionPhoto.connect();

                return connectionPhoto.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
