package com.android.larasw.fhictcompanionapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.larasw.fhictcompanionapp.Adapter.GradeAdapter;
import com.android.larasw.fhictcompanionapp.Model.Grade;
import com.android.larasw.fhictcompanionapp.Model.Profile;

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
public class GradeFragment extends Fragment {

    private ProgressDialog mDialog;
    private RecyclerView mRecyclerView;
    private GradeAdapter mAdapter;
    private List<Grade> grade;
    private Profile mProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grade, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDialog = new ProgressDialog(getActivity());



        mRecyclerView = (RecyclerView) view.findViewById(R.id.grade_recycler_view);
        new JSONTask().execute();
    }

    public void getGradeLaras() {
        grade = new ArrayList<>(5);

        grade.add(new Grade("Bahasa Indonesia", "A"));
        grade.add(new Grade("Physics I", "AB"));
        grade.add(new Grade("Physics I Lab Works", "A"));
        grade.add(new Grade("Islamic Religion and Ethics", "AB"));
        grade.add(new Grade("Introduction to Information System", "A"));

        grade.add(new Grade("Customer Relationship Management", "AB"));
        grade.add(new Grade("Calculus I", "A"));
        grade.add(new Grade("Linear Algebra", "AB"));
        grade.add(new Grade("English I", "B"));
        grade.add(new Grade("Physics II", "A"));

        grade.add(new Grade("Physics II Lab Works", "A"));
        grade.add(new Grade("Enviromental Science", "AB"));
        grade.add(new Grade("Concept of Science and Technology Development", "A"));
        grade.add(new Grade("Service Management", "B"));
        grade.add(new Grade("Algorithms and Programming", "A"));
    }

    public void getGradeFeter() {
        grade = new ArrayList<>(31);

        grade.add(new Grade("English", "AB"));
        grade.add(new Grade("Calculus I", "C"));
        grade.add(new Grade("Basic Programming", "A"));
        grade.add(new Grade("Discrite Mathematics", "B"));
        grade.add(new Grade("System & to Inform.Technology", "A"));

        grade.add(new Grade("Digital System", "BC"));
        grade.add(new Grade("Religion", "AB"));
        grade.add(new Grade("National Awareness", "AB"));
        grade.add(new Grade("Linear Algebra", "AB"));
        grade.add(new Grade("Computer Organization", "AB"));

        grade.add(new Grade("Data Structure", "AB"));
        grade.add(new Grade("Informatic Mathematics", "AB"));
        grade.add(new Grade("Object Oriented Programming", "AB"));
        grade.add(new Grade("Design & Analysis Algorithms I", "B"));
        grade.add(new Grade("Probability and Statistics", "BC"));

        grade.add(new Grade("Database System", "AB"));
        grade.add(new Grade("Operating System", "A"));
        grade.add(new Grade("Graph Theory", "A"));
        grade.add(new Grade("Computer Network", "A"));
        grade.add(new Grade("Artificial Intelligence", "AB"));

        grade.add(new Grade("Database Management", "A"));
        grade.add(new Grade("Automata", "A"));
        grade.add(new Grade("Web Programming", "B"));
        grade.add(new Grade("Analysis & Info.System Design", "A"));
        grade.add(new Grade("Computer Graph", "A"));

        grade.add(new Grade("Computation Intelligence", "B"));
        grade.add(new Grade("Numerical Computer", "AB"));
        grade.add(new Grade("Network Programming", "A"));
        grade.add(new Grade("Software Design", "AB"));
        grade.add(new Grade("Technopreneurship", "A"));

        grade.add(new Grade("Net.Based Comp.Special Topics", "AB"));
    }

    public class JSONTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL("https://api.fhict.nl/people/me");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
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

                JSONObject json = new JSONObject(response);

                String fhictId = json.getString("id");
                String studentId = json.getString("employeeId");

                mProfile = new Profile(fhictId, studentId);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog.setTitle("Loading");
            mDialog.setMessage("Please wait...");
            mDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mDialog.dismiss();
            if(mProfile.getStudentID().equals("3339920")){
                getGradeLaras();
            }
            else getGradeFeter();

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mAdapter = new GradeAdapter();

            mAdapter.setItems(grade);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
