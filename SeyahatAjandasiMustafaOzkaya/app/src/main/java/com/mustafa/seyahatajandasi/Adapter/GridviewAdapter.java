package com.mustafa.seyahatajandasi.Adapter;

import android.app.Application;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mustafa.seyahatajandasi.R;

import java.util.ArrayList;

public class GridviewAdapter extends ArrayAdapter<String> {


    ClickPencil clickPencil;
    public interface ClickPencil{
        public void onPencil(int position,ImageView imageView);
    }

    public void setClickPencil(ClickPencil clickPencil) {
        this.clickPencil = clickPencil;
    }

    ArrayList<String> arrayList;
    Context context;
    public GridviewAdapter(Context context, ArrayList<String> arrayList){
          super(context, R.layout.custom_adapter,arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.custom_adapter,parent,false);

        TextView textView = view.findViewById(R.id.file_adi);

        textView.setText(arrayList.get(position));


        ImageView imageView = view.findViewById(R.id.click_pencil);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if (clickPencil != null){
                         clickPencil.onPencil(position,v.findViewById(R.id.click_pencil));
                     }

            }
        });

        return view;
    }




}
