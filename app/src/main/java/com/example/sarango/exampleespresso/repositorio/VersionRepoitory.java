package com.example.sarango.exampleespresso.repositorio;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

import com.example.sarango.exampleespresso.model.VersionModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sarango on 29/08/2016.
 */
public class VersionRepoitory extends Base<IVersion> {

    private IVersion iVersion;
    String versionCode;


    public VersionRepoitory() {
        setServiceAdapter(new ServiceAdapter());
        iVersion = getServiceAdapter().getInstance(IVersion.class);
    }

    public String getNumberVersionFromService() {
        Call<VersionModel> modelCall = iVersion.getVersionNumber();
        modelCall.enqueue(new Callback<VersionModel>() {
            @Override
            public void onResponse(Call<VersionModel> call, Response<VersionModel> response) {
                if (response.isSuccessful()) {
                    versionCode = response.body().getVersion();
                }
            }

            @Override
            public void onFailure(Call<VersionModel> call, Throwable t) {
                Log.e(VersionRepoitory.class.getName(), "getNumberVersionFromService: " + call);
            }
        });

        return versionCode;


/*        Response<VersionModel> version = null;
        try {
            version = iVersion.getVersionNumber().execute().body();
            Log.d(VersionRepoitory.class.getName(), "getNumberVersionFromService: " + version);
        } catch (NetworkOnMainThreadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return version.toString();*/

    }


}
