package com.android.larasw.fhictcompanionapp.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.larasw.fhictcompanionapp.Model.News;
import com.android.larasw.fhictcompanionapp.NewsActivity;
import com.android.larasw.fhictcompanionapp.R;
import com.android.larasw.fhictcompanionapp.ViewHolder.NewsTagViewHolder;
import com.android.larasw.fhictcompanionapp.ViewHolder.NewsViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.util.List;

/**
 * Created by Res Non Verba on 11/10/2017.
 */

public class NewsTagAdapter extends ExpandableRecyclerViewAdapter<NewsTagViewHolder, NewsViewHolder> {

    public static Bitmap image;

    public NewsTagAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public NewsTagViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news_tag, parent,false);
        return new NewsTagViewHolder(view);
    }

    @Override
    public NewsViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(NewsViewHolder holder, int flatPosition, ExpandableGroup group, final int childIndex) {
        final News news = (News) group.getItems().get(childIndex);

        holder.setIcon(news.getIcon());
        holder.setTitle(news.getNewsTitle());
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image = news.getNewsImage();

                Intent intent = new Intent(v.getContext(), NewsActivity.class);
                intent.putExtra("title", news.getNewsTitle());
                intent.putExtra("date", news.getNewsDate());
                intent.putExtra("content", news.getNewsContent());
                intent.putExtra("link", news.getNewsLink());

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(NewsTagViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitleTag(group.getTitle());
    }
}
