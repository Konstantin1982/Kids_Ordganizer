package com.goodapps.kids.kids_organizer.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.goodapps.kids.kids_organizer.R;
import com.goodapps.kids.kids_organizer.adapters.ListTaskParentAdapter;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;

public class ListTaskParentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private ListTaskParentAdapter mAdapter;
    private ActivityResultLauncher<Intent> mEditTaskActivityResultLauncher;
    int mShowCompleted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task_parent);
        // Init recycler
        RecyclerView listTaskRecyclerView = findViewById(R.id.taskListParentRecyclerView);
        try {
            mAdapter = new ListTaskParentAdapter(this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listTaskRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layoutManager.setMeasurementCacheEnabled(false);
        listTaskRecyclerView.setLayoutManager(layoutManager);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("HELLO, WORLD2!");

        // setup main menu (left)
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mEditTaskActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            // doSomeOperations();
                        }
                    }
                });
    }

    public void onNewTaskCreateButtonClick(View view){
        clickOnTask(-1, -1);
    }

    public void clickOnTask(int taskId, int position){
        Intent intent = new Intent(this, EditTaskParentActivity.class);
        intent.putExtra("TASK_ID", taskId);
        intent.putExtra("POSITION", position);
        mEditTaskActivityResultLauncher.launch(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}