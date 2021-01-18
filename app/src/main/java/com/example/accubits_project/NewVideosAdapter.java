package com.example.accubits_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewVideosAdapter extends RecyclerView.Adapter<NewVideosAdapter.ViewHolder> {


    ArrayList<Result> resultnewVideos;

    public NewVideosAdapter(ArrayList<Result> resultnewVideos) {
        this.resultnewVideos = resultnewVideos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.new_movies_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String Value = resultnewVideos.get(position).getBackdropPath();
        String movie_name = resultnewVideos.get(position).getOriginalTitle();
        String movie_release = resultnewVideos.get(position).getReleaseDate();
        Log.d("Value", "onBindViewHolder: " + Value);
        Picasso.get().load(Value).into(holder.imageView);

        holder.movie_name.setText(movie_name);
        holder.movie_release.setText(movie_release);

    }

    @Override
    public int getItemCount() {
        return resultnewVideos.size();
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
