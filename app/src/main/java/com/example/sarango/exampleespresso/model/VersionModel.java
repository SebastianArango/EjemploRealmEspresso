package com.example.sarango.exampleespresso.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by sarango on 29/08/2016.
 */
public class VersionModel {
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }


}
