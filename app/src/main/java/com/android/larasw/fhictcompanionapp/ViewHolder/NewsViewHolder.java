package com.android.larasw.fhictcompanionapp.ViewHolder;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.larasw.fhictcompanionapp.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Res Non Verba on 11/10/2017.
 */

public class NewsViewHolder extends ChildViewHolder {

    private ImageView mIcon;
    private TextView mTitle;
    public LinearLayout mLayout;

    public NewsViewHolder(View itemView) {
        super(itemView);
        mIcon = (ImageView) itemView.findViewById(R.id.newsListPicture);
        mTitle = (TextView) itemView.findViewById(R.id.newsListTitle);
        mLayout = (LinearLayout) itemView.findViewById(R.id.newsChildLayout);
    }

    public void setIcon(Bitmap icon) {
        mIcon.setImageBitmap(icon);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }
}
