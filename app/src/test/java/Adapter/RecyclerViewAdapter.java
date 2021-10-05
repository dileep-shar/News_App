package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memexi.R;

import java.util.List;

import model.News;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<News> newsList;
  public RecyclerViewAdapter(Context context,List<News> news){
      this.context = context;
      this.newsList = news;
  }
   // Where to get single news as viewholder object
    @NonNull
    @Override
    public RecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_single,parent,false);

      return new viewHolder(view);
      //khali card milega (skeleton only )
    }
   // what to dop after we get viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.viewHolder holder, int position) {
      News news = newsList.get(position);


    }
   // how many items
    @Override
    public int getItemCount() {
        return newsList.size();
    }
    public static class viewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public TextView news_heading;
        public ImageView news_image;
        public TextView  author_resource;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            news_heading    = itemView.findViewById(R.id.news_recycler);
            news_image      = itemView.findViewById(R.id.news_image);
            author_resource = itemView.findViewById(R.id.source_author);
        }
        //<- is sent to binder

        @Override
        public void onClick(View v) {

        }
    }
}
