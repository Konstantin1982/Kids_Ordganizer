package com.goodapps.kids.kids_organizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.goodapps.kids.kids_organizer.R;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerTask;
import com.goodapps.kids.kids_organizer.helpers.utils.CurrentUser;
import com.goodapps.kids.kids_organizer.helpers.utils.DBEntitiesHelper;
import com.goodapps.kids.kids_organizer.helpers.utils.GeneralHelper;
import com.goodapps.kids.kids_organizer.helpers.utils.OrganizerTasksHelper;

import java.text.ParseException;
import java.util.Calendar;

public class EditTaskParentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Toolbar mToolbar;
    private Integer mTaskId;
    private Integer mTaskPosition;
    private OrganizerTask mTask;
    private OrganizerTasksHelper tasksHelper;
    private DBEntitiesHelper dbEntitiesHelper;
    private ImageView mTaskImage;
    private CurrentUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_parent);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_clear_24);

        dbEntitiesHelper = new DBEntitiesHelper(this);
        Intent intent = getIntent();
        mTaskId = intent.getIntExtra("TASK_ID", -1);
        mTaskPosition = intent.getIntExtra("POSITION", -1);
        if (mTaskId >= 0) {
            try {
                tasksHelper = new OrganizerTasksHelper(this);
                mTask = tasksHelper.getTaskById(mTaskId);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            mTask = new OrganizerTask();
        }
        mTaskImage = findViewById(R.id.taskPicture);
        mTaskImage.setOnClickListener(view -> {
            showImageSelectionDialog();
        });
        mUser = CurrentUser.getInstance(this);
        loadAssigneeSpinnerData();
        prepareDateTimeDialog();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        GeneralHelper.LooseFocus(this, R.id.edit_task_parent_layout);
        switch (item.getItemId()) {
            case R.id.menu_item_save :
                return true;
            case android.R.id.home:
                setResult(0);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void inflateTaskToView(){
        EditText taskTitle = findViewById(R.id.taskTitleEditText);
        EditText taskDescription = findViewById(R.id.taskDescriptionMultiLine);
        TextView taskEndDate = findViewById(R.id.taskEndDate);
        TextView taskEndTime = findViewById(R.id.taskEndTime);
        Spinner assignee = findViewById(R.id.assignee_spinner);

        taskTitle.setText(mTask.name);
        taskDescription.setText(mTask.description);
        taskEndDate.setText(mTask.toDate);
        taskEndTime.setText(mTask.toTime);
        // assignee
        // TODO : PRIZE + PICTURE + ASSIGNEE
    }

    public void loadAssigneeSpinnerData(){
        ArrayAdapter<String> assigneeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dbEntitiesHelper.getUserNames(0));
        assigneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner assigneeSpinner = findViewById(R.id.assignee_spinner);
        assigneeSpinner.setAdapter(assigneeAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void prepareDateTimeDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        TextView taskEndDate = findViewById(R.id.taskEndDate);
        taskEndDate.setOnClickListener(view -> {
            datePickerDialog.show();
        });

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, hour, minute, true);
        TextView taskEndTime = findViewById(R.id.taskEndTime);
        taskEndTime.setOnClickListener(view -> {
            timePickerDialog.show();
        });
    }

    // Define a listener to retrieve the selected date value
    private DatePickerDialog.OnDateSetListener dateSetListener = (DatePicker view, int year, int month, int dayOfMonth) -> {
        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        Log.e("Chosen Date: ", selectedDate);
        // dateTextView.setText("Selected date: " + selectedDate);
    };

    // Define a listener to retrieve the selected time value
    private TimePickerDialog.OnTimeSetListener timeSetListener = (TimePicker view, int hourOfDay, int minute) -> {
        String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
        Log.e("Chosen Time: ", selectedTime);
    };

    // Show a dialog with a list of images from the app's resources to select
    private void showImageSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select an Image")
                .setItems(new CharSequence[]{"Image 1", "Image 2", "Image 3"}, (dialog, which) -> {
                    // Handle the user's selection
                    switch (which) {
                        case 0:
                            mTaskImage.setImageResource(R.drawable.ic_baseline_clear_24);
                            break;
                        case 1:
                            mTaskImage.setImageResource(R.drawable.ic_baseline_filter_list_24);
                            break;
                        case 2:
                            mTaskImage.setImageResource(R.drawable.ic_baseline_search_24);
                            break;
                    }
                });
        builder.show();
    }

}