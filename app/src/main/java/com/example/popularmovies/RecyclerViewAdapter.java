package com.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Movies> itemData;
    RequestOptions option;

    public RecyclerViewAdapter(Context context, List<Movies> itemData) {
        this.context = context;
        this.itemData = itemData;

        //Request Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.img_loading).error(R.drawable.img_loading);
    }


    //MyViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_movies,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movie_title.setText(itemData.get(position).getMovie_title());
        holder.overview.setText(itemData.get(position).getMovie_description());

//        Informação para nova tela
//        holder.rate.setText(itemData.get(position).getRating());
//        holder.categories.setText(itemData.get(position).getCategories());
//        holder.date.setText(itemData.get(position).getDate());

        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(context).load(itemData.get(position).getImage_url()).apply(option).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }


    //Class - ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movie_title;
        TextView overview;
        ImageView thumbnail; //poster_path
        LinearLayout container; //LinearLayout containing content

//        TextView rate;
//        TextView date;
//        TextView categories;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            movie_title = itemView.findViewById(R.id.movie_name);
            overview = itemView.findViewById(R.id.Description);
            thumbnail = itemView.findViewById(R.id.thumbnail);

//            rate = itemView.findViewById(R.id.rating);
//            date = itemView.findViewById(R.id.release_date);
//            categories = itemView.findViewById(R.id.categories);

        }
    }
}
