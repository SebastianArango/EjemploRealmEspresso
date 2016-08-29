package com.example.sarango.exampleespresso.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sarango on 29/08/2016.
 */
public class ServiceAdapter<T> {

    private static String URL_BASE = "http://apiv3.iucnredlist.org/api/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private Retrofit retrofit;


    public ServiceAdapter() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public T getInstance(Class<T> service) {
        return retrofit.create(service);
    }
}
