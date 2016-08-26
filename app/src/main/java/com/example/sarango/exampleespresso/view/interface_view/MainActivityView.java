package com.example.sarango.exampleespresso.view.interface_view;

import android.view.View;

import com.example.sarango.exampleespresso.model.Person;

import java.util.List;

/**
 * Created by sarango on 24/08/2016.
 */
public interface MainActivityView {

    void showAlertDialog(String title, String message);

    void loadRecyclerView();

    void setListPerson(List<Person> personList);

    void addPersonToListAdapter(Person person);

    void showSnackBar(View mDialogView);


}
