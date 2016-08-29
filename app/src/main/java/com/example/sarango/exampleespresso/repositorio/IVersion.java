package com.example.sarango.exampleespresso.repositorio;

import com.example.sarango.exampleespresso.model.VersionModel;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sarango on 29/08/2016.
 */
public interface IVersion {

    @GET("v3/version")
    Call<VersionModel> getVersionNumber();
}
