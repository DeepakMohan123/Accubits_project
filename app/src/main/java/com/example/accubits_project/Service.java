package com.example.accubits_project;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Service {
    @POST("upcoming")
    Call<Response> response(@Body JsonObject request);
}
