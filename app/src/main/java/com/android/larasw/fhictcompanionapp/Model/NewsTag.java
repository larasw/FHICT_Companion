package com.android.larasw.fhictcompanionapp.Model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Res Non Verba on 11/10/2017.
 */

public class NewsTag extends ExpandableGroup{
    public NewsTag(String title, List items) {
        super(title, items);
    }
}
