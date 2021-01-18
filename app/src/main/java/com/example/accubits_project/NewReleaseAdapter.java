package com.example.accubits_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewReleaseAdapter extends RecyclerView.Adapter<NewReleaseAdapter.ViewHolder>  {

    ArrayList<Result> resultnewRelease;
    Context context;

    public NewReleaseAdapter(ArrayList<Result> resultnewRelease, Context context) {
        this.resultnewRelease = resultnewRelease;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.new_release_view, parent, false);
       ViewHolder viewHolder = new ViewHolder(listItem);
       return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        String Value = resultnewRelease.get(position).getBackdropPath();
        String movie_name = resultnewRelease.get(position).getOriginalTitle();
        String movie_release = resultnewRelease.get(position).getReleaseDate();
        Log.d("Value", "onBindViewHolder: " + resultnewRelease.get(position));

        //Picasso.get().load(Value).into(holder.imageView);
        Picasso.get().load(String.valueOf(resultnewRelease.get(position))).into(holder.imageView);

        //Glide.with(context).load(resultnewRelease.get(position)).into(holder.imageView);

        holder.movie_name.setText(movie_name);
        holder.movie_release.setText(movie_release);
    }

    @Override
    public int getItemCount() {
        return resultnewRelease.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView movie_name,movie_release;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.image_view);
            this.movie_name = (TextView) itemView.findViewById(R.id.movie_name);
            this.movie_release = (TextView) itemView.findViewById(R.id.movie_release);
        }
    }
}
