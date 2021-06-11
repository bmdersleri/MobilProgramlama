package com.mustafa.seyahatajandasi.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mustafa.seyahatajandasi.R;

import java.util.ArrayList;


public class ImageAdapter extends ArrayAdapter<Bitmap> {

    ArrayList<Bitmap> bitmaps;
    Activity activity;

    public ImageAdapter(Activity activity, ArrayList<Bitmap> bitmaps){
        super(activity, R.layout.image_row,bitmaps);
        this.activity = activity;
        this.bitmaps = bitmaps;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_row,parent,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmaps.get(position));

        return view;
    }
}
