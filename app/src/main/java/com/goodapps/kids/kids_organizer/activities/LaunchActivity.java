package com.goodapps.kids.kids_organizer.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.goodapps.kids.kids_organizer.R;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerDBManager;
import com.goodapps.kids.kids_organizer.helpers.utils.CurrentUser;

public class LaunchActivity extends AppCompatActivity {
    private OrganizerDBManager mDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mDbManager = new OrganizerDBManager(this);
    }

    public void onGenerateTestData(View view){
        mDbManager.generateTestData();
        CurrentUser user = CurrentUser.getInstance(this);
        Log.e("MAIN_ACTIVITY", " Current User  = " + user.name);
    }

    public void onOpenListTaskChild(View view){
        Intent intent = new Intent(this, ListTaskChildActivity.class);
        startActivity(intent);
    }

    public void onOpenListTaskParent(View view){
        Intent intent = new Intent(this, ListTaskParentActivity.class);
        startActivity(intent);
    }
}

/*
TASKS LINK:

DONE 1: Create activity for child with task list
DONE 2: Create activity for parent with task list
TODO 3: Create activity to create a task:
TODO 4: Create activity to work with task on behalf of children:
TODO 5: Create Profile Activity
TODO 6: Create Profile Activity for parent
TODO 6: Create Schedule Activity for parent
TODO 6: Create Statistics
TODO 6: Create Settings
TODO 7: create server-side scripts to get/set entities
TODO 8: Implement server-side changes
TODO 9: Implement initialize for child / parent
 */