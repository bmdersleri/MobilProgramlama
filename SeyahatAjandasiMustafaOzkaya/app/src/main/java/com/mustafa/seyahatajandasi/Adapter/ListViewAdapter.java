package com.mustafa.seyahatajandasi.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mustafa.seyahatajandasi.Details;
import com.mustafa.seyahatajandasi.MapsActivity.Maps;
import com.mustafa.seyahatajandasi.Model.MapsModel;
import com.mustafa.seyahatajandasi.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<MapsModel> {

    OnClickButton button2;
    public  interface  OnClickButton{
        void listener(int position,Activity activity);
        void imageSettingsClick(int position, ImageView imageView);
    }

    public void setButton2(OnClickButton button2) {
        this.button2 = button2;
    }

    ArrayList<MapsModel> models;
    Activity activity;
    public ListViewAdapter(Activity activity, ArrayList<MapsModel> models){
        super(activity, R.layout.listview_row,models);
        this.activity = activity;
        this.models = models;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.listview_row,null,true);

        ImageView settings = view.findViewById(R.id.settings_listview);
        TextView textView = view.findViewById(R.id.addres_name);
        textView.setText(models.get(position).getAddress());
        Button button = view.findViewById(R.id.goto_maps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (button2 != null){
                     button2.listener(position,activity);
                 }
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (button2 != null){
                        button2.imageSettingsClick(position,settings);
                    }
            }
        });


        return view;
    }
}
