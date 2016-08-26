package com.example.sarango.exampleespresso.helpers;

import android.app.Activity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sarango on 24/08/2016.
 */
public class AccesForDataBase<T> {

    private Realm realm;
    private RealmConfiguration realmConfig;
    private Activity activity;

    public AccesForDataBase(Activity activity) {
        this.activity = activity;
    }

    private void openDataBase() {
        realmConfig = new RealmConfiguration.Builder(activity).build();
        realm = Realm.getInstance(realmConfig);
    }

    public void insert(Class<T> c) {

    }


}
