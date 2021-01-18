package com.example.accubits_project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RatedAdapter extends RecyclerView.Adapter<RatedAdapter.Viewholder> {

    ArrayList<Result> ratedmovie;

    public RatedAdapter(ArrayList<Result> ratedmovie) {
        this.ratedmovie = ratedmovie;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.new_movies_view, parent, false);
        Viewholder viewHolder = new Viewholder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String Value = ratedmovie.get(position).getBackdropPath();
        String movie_name = ratedmovie.get(position).getOriginalTitle();
        String movie_release = ratedmovie.get(position).getReleaseDate();
        Log.d("Value", "onBindViewHolder: " + Value);
        Picasso.get().load(Value).into(holder.imageView);

        holder.movie_name.setText(movie_name);
        holder.movie_release.setText(movie_release);
    }

    @Override
    public int getItemCount() {
        return ratedmovie.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView movie_name,movie_release;
        public Viewholder(@NonNull View itemView) {
            super(itemView);


            this.imageView = (ImageView) itemView.findViewById(R.id.image_view);
            this.movie_name = (TextView) itemView.findViewById(R.id.movie_name);
            this.movie_release = (TextView) itemView.findViewById(R.id.movie_release);
        }
    }
}
