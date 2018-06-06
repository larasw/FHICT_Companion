package com.android.larasw.fhictcompanionapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.android.larasw.fhictcompanionapp.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by Res Non Verba on 11/10/2017.
 */

public class NewsTagViewHolder extends GroupViewHolder {

    private TextView mTitleTag;

    public NewsTagViewHolder(View itemView) {
        super(itemView);
        mTitleTag = (TextView) itemView.findViewById(R.id.titleTag);
    }

    public void setTitleTag(String titleTag) {
        mTitleTag.setText(titleTag);
    }
}
