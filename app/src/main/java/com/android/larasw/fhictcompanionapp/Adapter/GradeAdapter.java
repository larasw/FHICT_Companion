package com.android.larasw.fhictcompanionapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.larasw.fhictcompanionapp.Model.Grade;
import com.android.larasw.fhictcompanionapp.R;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

/**
 * Created by Res Non Verba on 04/09/2017.
 */

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.GradeViewHolder> {
    private List<Grade> mItems = new ArrayList<>();

    public void setItems(List<Grade> items){
        mItems=items;
        notifyDataSetChanged();
    }

    @Override
    public GradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return GradeViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(GradeViewHolder holder, int position) {
        Grade grade = mItems.get(position);
        holder.SubjectText.setText(grade.getSubject());
        holder.GradeText.setText(grade.getGrade());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    static class GradeViewHolder extends RecyclerView.ViewHolder {
        private TextView SubjectText,GradeText;

        public static GradeViewHolder inflate(ViewGroup parent){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_grade, parent, false);
            return new GradeViewHolder(view);
        }

        public GradeViewHolder(View itemView) {
            super(itemView);

            SubjectText = (TextView) itemView.findViewById(R.id.G_Subject);
            GradeText = (TextView) itemView.findViewById(R.id.G_Grade);
        }

        public void bind() {
//            mTextView.setText(text);
        }
    }
}