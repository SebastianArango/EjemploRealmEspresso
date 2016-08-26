package com.example.sarango.exampleespresso.presenter;

import android.view.View;

import com.example.sarango.exampleespresso.helpers.ValidatorForFields;
import com.example.sarango.exampleespresso.model.Person;
import com.example.sarango.exampleespresso.view.interface_view.MainActivityView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by sarango on 24/08/2016.
 */
public class MainActivityPresenter {
    private MainActivityView mainActivityView;
    private Realm realm;

    public MainActivityPresenter(MainActivityView mainActivityView, Realm realm) {
        this.mainActivityView = mainActivityView;
        this.realm = realm;
    }

    public boolean validateFields(String edtIdPerson, String edtNamePerson, String edtLastNamePerson, String edtAgePerson, View mDialogView) {
        boolean fieldEmpty = ValidatorForFields.validatorStringsEmpty(edtIdPerson, edtNamePerson, edtLastNamePerson, edtAgePerson);

        if (fieldEmpty) {
            mainActivityView.showSnackBar(mDialogView);

        } else {
            addPersonToDB(edtIdPerson, edtNamePerson, edtLastNamePerson, edtAgePerson);
        }

        return fieldEmpty;

    }

    public void validateDateInDB() {
        RealmQuery query = realm.where(Person.class);
        List<Person> results = query.findAll();

        if (results.size() > 0) {
            mainActivityView.setListPerson(results);

        }
    }

    private void addPersonToDB(String edtIdPerson, String edtNamePerson, String edtLastNamePerson, String edtAgePerson) {
        if (checkIfExists(edtIdPerson)) {
            mainActivityView.showAlertDialog("Hola", "El usuario que desea ingresar ya fue ingresado");
        } else {
            realm.beginTransaction();
            Person person = realm.createObject(Person.class);
            person.setUserID(edtIdPerson);
            person.setUserName(edtNamePerson);
            person.setUserLastName(edtLastNamePerson);
            person.setUserAge(edtAgePerson);
            realm.commitTransaction();
            validateDateInDB();
        }
    }

    public boolean checkIfExists(String id) {

        RealmQuery<Person> query = realm.where(Person.class)
                .equalTo("userID", id);

        return query.count() != 0;
    }

    public void deletePerson(String id) {
        //  Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        Person result = realm.where(Person.class).equalTo("userID", id).findFirst();
        result.deleteFromRealm();
        realm.commitTransaction();
    }
}
