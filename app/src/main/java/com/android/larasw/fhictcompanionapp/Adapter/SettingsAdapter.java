package com.android.larasw.fhictcompanionapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.larasw.fhictcompanionapp.CalendarActivity;
import com.android.larasw.fhictcompanionapp.Model.Settings;
import com.android.larasw.fhictcompanionapp.ProfileActivity;
import com.android.larasw.fhictcompanionapp.R;
import com.android.larasw.fhictcompanionapp.StartActivity;
import com.android.larasw.fhictcompanionapp.TokenFragment;

import java.util.List;

/**
 * Created by Res Non Verba on 14/09/2017.
 */

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.MyViewHolder>{

    private List<Settings> settingList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mIcon;
        public TextView mSettingsName;
        public LinearLayout mLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.list_setting_icon);
            mSettingsName = (TextView) itemView.findViewById(R.id.list_item_setting_name);
            mLayout = (LinearLayout) itemView.findViewById(R.id.settingsLayout);
        }
    }

    public SettingsAdapter(List<Settings> settingList) {
        this.settingList = settingList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_settings, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Settings settings = settingList.get(position);
        Drawable drawable = holder.itemView.getResources().getDrawable(settings.getIcon());

        holder.mIcon.setImageDrawable(drawable);
        holder.mSettingsName.setText(settings.getName());
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch (position){
                    case 0:
                        v.getContext().startActivity(new Intent(v.getContext(), ProfileActivity.class));
                        break;
                    case 1:
                        v.getContext().startActivity(new Intent(v.getContext(), CalendarActivity.class));
                        break;
                    case 2:
                        System.out.println("Aku 2");
                        break;
                    case 3:
                        System.out.println("Aku 3");
                        break;
                    case 4:
                        android.webkit.CookieManager.getInstance().removeSessionCookies(new ValueCallback<Boolean>() {
                            @Override
                            public void onReceiveValue(Boolean value) {
                                TokenFragment.authComplete = false;
                                ((Activity)v.getContext()).finish();
                                v.getContext().startActivity(new Intent(v.getContext(), StartActivity.class));
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingList.size();
    }
}
