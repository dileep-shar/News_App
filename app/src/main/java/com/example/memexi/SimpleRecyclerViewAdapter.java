package com.example.memexi;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {

  ArrayList<String> newsList;
    public SimpleRecyclerViewAdapter( ArrayList<String> news){

        this.newsList = news;
    }
    @NonNull
    @Override
    public SimpleRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_single,parent,false);

        return new ViewHolder(view);
        //suspedct REcucdsfadf is req?
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleRecyclerViewAdapter.ViewHolder holder, int position) {
          String st = newsList.get(position);
          //suspect<-
          holder.news_heading.setText(st);
          holder.author_resource.setText("resource");

    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView news_heading;
        public ImageView news_image;
        public TextView  author_resource;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_heading = (TextView) itemView.findViewById(R.id.news_heading);
            author_resource = (TextView) itemView.findViewById(R.id.source_author);

        }

        @Override
        public void onClick(View v) {


        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
