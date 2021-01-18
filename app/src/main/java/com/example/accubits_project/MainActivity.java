package com.example.accubits_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView new_movie,new_release,new_rated;

    ArrayList<com.example.accubits_project.Result> movieArray_release = new ArrayList<Result>();
    ArrayList<com.example.accubits_project.Result> movieArray_new = new ArrayList<Result>();
    ArrayList<com.example.accubits_project.Result> movieArray_rated = new ArrayList<Result>();

    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    LinearLayoutManager HorizontalLayout_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volley();
    }

    private void volley() {
        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=9c0523bff54071c4fb4b716a950231b9&language=en-US&page=1&region=IN|US&with_release_type=2|3";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("check", "onResponse: " + response);
                        try {
                            JSONArray array = response.getJSONArray("results");
                            String url = "http://image.tmdb.org/t/p/w780";
                            String url_poster = "http://image.tmdb.org/t/p/w185";

                            if (array.length() > 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    Result result_new_release = new Result();
                                    Result result_new_movies = new Result();
                                    Result result_rated = new Result();
                                    String value = array.getJSONObject(i).getString("backdrop_path");
                                    //String value_poster = array.getJSONObject(i).getString("poster_path");
                                    int rate = array.getJSONObject(i).getInt("vote_average");
                                    result_new_release.setOriginalTitle(array.getJSONObject(i).getString("original_title"));
                                    result_new_release.setReleaseDate(array.getJSONObject(i).getString("release_date"));



                                    if (value.equals("null")) {
                                        result_new_release.setBackdropPath(url_poster + array.getJSONObject(i).getString("poster_path"));
                                    } else {
                                        result_new_release.setBackdropPath(url + array.getJSONObject(i).getString("backdrop_path"));
                                        result_new_movies.setBackdropPath(url + array.getJSONObject(i).getString("backdrop_path"));
                                        result_new_movies.setOriginalTitle(array.getJSONObject(i).getString("original_title"));
                                        result_new_movies.setReleaseDate(array.getJSONObject(i).getString("release_date"));
                                        movieArray_new.add(result_new_movies);
                                        Log.d("TAG", "onResponse: else");
                                    }

                                    if (rate > 5) {
                                        result_rated.setBackdropPath(url + array.getJSONObject(i).getString("backdrop_path"));
                                        result_rated.setOriginalTitle(array.getJSONObject(i).getString("original_title"));
                                        result_rated.setReleaseDate(array.getJSONObject(i).getString("release_date"));
                                        movieArray_rated.add(result_rated);
                                    }
                                    movieArray_release.add(result_new_release);

                                    new_movie = (RecyclerView) findViewById(R.id.recyclerViewNewVideos);
                                    RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
                                    new_movie.setLayoutManager(RecyclerViewLayoutManager);
                                    NewVideosAdapter adapter = new NewVideosAdapter(movieArray_new);
                                    new_movie.setHasFixedSize(true);

                                    new_movie.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    HorizontalLayout_banner = new LinearLayoutManager(MainActivity.this,
                                            LinearLayoutManager.HORIZONTAL,
                                            false);
//
                                    new_movie.setLayoutManager(HorizontalLayout_banner);
                                    new_movie.setAdapter(adapter);

                                    //
                                    new_release = (RecyclerView) findViewById(R.id.recyclerViewNewReleases);
                                    RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
                                    new_release.setLayoutManager(RecyclerViewLayoutManager);
                                    NewReleaseAdapter adapter_new_release = new NewReleaseAdapter(movieArray_release,MainActivity.this);
                                    new_release.setHasFixedSize(true);

                                    new_release.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    HorizontalLayout_banner = new LinearLayoutManager(MainActivity.this,
                                            LinearLayoutManager.HORIZONTAL,
                                            false);
//
                                    new_release.setLayoutManager(HorizontalLayout_banner);
                                    new_release.setAdapter(adapter_new_release);


                                    //
                                    new_rated = (RecyclerView) findViewById(R.id.recyclerViewNewRated);
                                    RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
                                    new_rated.setLayoutManager(RecyclerViewLayoutManager);
                                    RatedAdapter adapter_new_rated = new RatedAdapter(movieArray_rated);
                                    new_rated.setHasFixedSize(true);

                                    new_rated.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    HorizontalLayout_banner = new LinearLayoutManager(MainActivity.this,
                                            LinearLayoutManager.HORIZONTAL,
                                            false);
//
                                    new_rated.setLayoutManager(HorizontalLayout_banner);
                                    new_rated.setAdapter(adapter_new_rated);

                                }
                            }
                            Log.d("Array Value", "onResponse: " + array.getJSONObject(1).getString("backdrop_path"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("check", "onErrorResponse: ");
                    }
                }

        );
        requestQueue.add(jsonObjectRequest);
    }

}