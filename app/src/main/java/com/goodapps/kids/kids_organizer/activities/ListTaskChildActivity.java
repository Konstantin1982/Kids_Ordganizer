package com.goodapps.kids.kids_organizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;


import com.goodapps.kids.kids_organizer.R;
import com.goodapps.kids.kids_organizer.adapters.ListTaskChildAdapter;
import com.goodapps.kids.kids_organizer.helpers.utils.GeneralHelper;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.util.Calendar;

public class ListTaskChildActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {
    final Calendar myCalendar= Calendar.getInstance();
    int mShowCompleted = 0;
    private Toolbar mToolbar;
    private ListTaskChildAdapter mTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task_child);

        // Init recycler
        RecyclerView listTaskRecyclerView = findViewById(R.id.taskListChildRecyclerView);
        try {
            mTaskAdapter = new ListTaskChildAdapter(this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listTaskRecyclerView.setAdapter(mTaskAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layoutManager.setMeasurementCacheEnabled(false);
        listTaskRecyclerView.setLayoutManager(layoutManager);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("HELLO, WORLD!");

        // setup main menu (left)
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MenuItem item = navigationView.getMenu().findItem(R.id.main_menu_point_1);
        if (item != null) {
            item.setVisible(false);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_task_child, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_task_list_child_filter:
                new DatePickerDialog(this,this,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH,month);
        myCalendar.set(Calendar.DAY_OF_MONTH,day);
        getSupportActionBar().setTitle(GeneralHelper.GetTitleForListOfTasks(1, myCalendar));
        try {
            mTaskAdapter.updateListValues(mShowCompleted, GeneralHelper.GetAnyDateInString(myCalendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}