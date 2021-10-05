package com.example.memexi;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private   Context context;
    private  static ArrayList<News> newsList;

  public RecyclerViewAdapter( ArrayList<News> newsList,Context context){

      this.context  =  context;
      this.newsList = newsList;
  }

   // Where to get single news as viewholder object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_single,parent,false);

      return new ViewHolder(view);
      //khali card milega (skeleton only )
    }
   // what to dop after we get viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
      News news = newsList.get(position);
      holder.news_heading.setText(news.getHeadline());
      holder.author_resource.setText(news.getAuthor());
      Glide.with(context).load(news.getImage_url()).into(holder.news_image);
        Log.d("NameTheGrat","DisplayesTheMac");



    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView news_heading;
        public ImageView news_image;
        public TextView  author_resource;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            news_heading    = itemView.findViewById(R.id.news_heading);
            news_image      = itemView.findViewById(R.id.news_image);
            author_resource = itemView.findViewById(R.id.source_author);
            news_heading.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position  = this.getAbsoluteAdapterPosition();

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            News news = newsList.get(position);
            customTabsIntent.launchUrl(v.getContext(), Uri.parse(news.getUrl()));
        }
        //<- is sent to binder

        }

    }

