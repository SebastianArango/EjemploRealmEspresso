package com.example.sarango.exampleespresso.view.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sarango.exampleespresso.R;
import com.example.sarango.exampleespresso.model.Person;
import com.example.sarango.exampleespresso.presenter.MainActivityPresenter;
import com.example.sarango.exampleespresso.view.adapter.RVAdapterPerson;
import com.example.sarango.exampleespresso.view.interface_view.DataBaseView;
import com.example.sarango.exampleespresso.view.interface_view.MainActivityView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements MainActivityView, DataBaseView {

    private Realm realm;
    private RealmConfiguration realmConfig;
    private MainActivityPresenter presenter;
    private AlertDialog.Builder alertDialog;
    private RecyclerView mRecyclerView;
    private RVAdapterPerson rvAdapterPerson;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Person> personList;
    private Toolbar toolbar;
    private FloatingActionButton btnFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        castComponents();
        loadToolbar();
        initListeners();


        realmConfig = new RealmConfiguration
                .Builder(MainActivity.this)
                .deleteRealmIfMigrationNeeded()
                .build();

        // realmConfig = new RealmConfiguration.Builder(MainActivity.this).build();
        realm = Realm.getInstance(realmConfig);

        alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setCancelable(false);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter = new MainActivityPresenter(MainActivity.this, realm);
        presenter.validateDateInDB();
    }

    private void initListeners() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    btnFab.hide();
                else if (dy < 0)
                    btnFab.show();
            }
        });

        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Esto es una prueba", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void castComponents() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list_person);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        btnFab = (FloatingActionButton) findViewById(R.id.btnFab);
    }

    private void loadToolbar() {
        toolbar.setTitle("Personas");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWithe));
        setSupportActionBar(toolbar);

    }


    private void callCustomAlertDialog() {

        LayoutInflater inflater = this.getLayoutInflater();
        // inflate the custom dialog view
        final View mDialogView = inflater.inflate(R.layout.custom_item_alert, null);
        // set the View for the AlertDialog
        alertDialog.setView(mDialogView);

        Button dialogButton = (Button) mDialogView.findViewById(R.id.btn_add_person);
        final EditText edtIdPerson = (EditText) mDialogView.findViewById(R.id.edt_user_id);
        final EditText edtNamePerson = (EditText) mDialogView.findViewById(R.id.edt_user_name);
        final EditText edtLastNamePerson = (EditText) mDialogView.findViewById(R.id.edt_user_last_name);
        final EditText edtAgePerson = (EditText) mDialogView.findViewById(R.id.edt_user_age);

        final AlertDialog dialog = alertDialog.create();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validateFields = presenter.validateFields(edtIdPerson.getText().toString(), edtNamePerson.getText().toString(),
                        edtLastNamePerson.getText().toString(), edtAgePerson.getText().toString(), mDialogView);

                if (!validateFields) {
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }

    @Override
    public void showAlertDialog(String title, String message) {
        alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });

        alertDialog.show();

    }

    @Override
    public void loadRecyclerView() {
        rvAdapterPerson = new RVAdapterPerson(personList, MainActivity.this);
        mRecyclerView.setAdapter(rvAdapterPerson);
    }

    @Override
    public void setListPerson(List<Person> personListfromDataBase) {
        personList = personListfromDataBase;
        loadRecyclerView();
    }

    @Override
    public void addPersonToListAdapter(Person person) {
        personList.add(person);
        rvAdapterPerson.notifyDataSetChanged();
    }

    @Override
    public void showSnackBar(View mDialogView) {
        Snackbar snackbar = Snackbar
                .make(mDialogView, "Campos vacios", Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorWithe));

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorRed));
        snackbar.show();
    }


    @Override
    public void deleteForId(String id) {
        presenter.deletePerson(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_person, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_person_menu:
                callCustomAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
