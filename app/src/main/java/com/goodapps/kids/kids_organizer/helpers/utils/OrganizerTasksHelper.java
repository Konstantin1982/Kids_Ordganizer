package com.goodapps.kids.kids_organizer.helpers.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.goodapps.kids.kids_organizer.helpers.data.OrganizerContract;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerDBManager;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerTask;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrganizerTasksHelper {
    private Context mContext;
    private OrganizerDBManager mDBManager;
    private List<OrganizerTask> mTasks;
    private Integer mTaskCount;


    public OrganizerTasksHelper(Context context) throws ParseException {
        mContext = context;
        mDBManager = new OrganizerDBManager(mContext);
        mTasks = getTasksListFromDB(0, "");
        mTaskCount = mTasks.size();
    }

    public void UpdateListOfTasks(Integer showComplete, String dateFilter) throws ParseException {
        mTasks = getTasksListFromDB(showComplete, dateFilter);
        mTaskCount = mTasks.size();
    }

    public Integer getCount() {
        return mTasks.size();
    }


    // get list
    private List<OrganizerTask> getTasksListFromDB(Integer showCompleted, String dateFilter) throws ParseException {
        List<OrganizerTask> listTasks = new ArrayList<>();
        ArrayList<HashMap<String, String>> listTasksFromDB = mDBManager.getTasks(showCompleted, dateFilter);
        String[] statuses = GeneralHelper.GetStatuses(mContext);
        for (HashMap<String, String> mapTask : listTasksFromDB){
            OrganizerTask task = new OrganizerTask();
            task.id = Integer.valueOf(mapTask.get(OrganizerContract.TaskEntry._ID));
            task.name = mapTask.get(OrganizerContract.TaskEntry.NAME);
            task.description = mapTask.get(OrganizerContract.TaskEntry.DESCRIPTION);
            task.fromDate = mapTask.get(OrganizerContract.TaskEntry.FROMDATE);
            task.fromTime = mapTask.get(OrganizerContract.TaskEntry.FROMTIME);
            task.toDate = mapTask.get(OrganizerContract.TaskEntry.TODATE);
            task.toTime = mapTask.get(OrganizerContract.TaskEntry.TOTIME);
            task.reporter = mapTask.get(OrganizerContract.TaskEntry.REPORTER);
            task.assignee =  mapTask.get(OrganizerContract.TaskEntry.ASSIGNEE);
            task.status = statuses[Integer.valueOf(mapTask.get(OrganizerContract.TaskEntry.STATUS))];
            task.score = Integer.valueOf(mapTask.get(OrganizerContract.TaskEntry.SCORE));
            // TODO Bitmap from DB
            // task.picture = mapTask.get(OrganizerContract.TaskEntry.PICTURE);
            listTasks.add(task);
        }
        return listTasks;
    }

    // get task
    public OrganizerTask getTaskFromList(int index){
        return mTasks.get(index);
    }

    // get task by id
    public  OrganizerTask getTaskById(int taskId) throws ParseException {
        OrganizerTask task = new OrganizerTask();
        HashMap<String, String> mapTask = mDBManager.getTaskById(taskId);
        String[] statuses = GeneralHelper.GetStatuses(mContext);
        task.id = Integer.valueOf(mapTask.get(OrganizerContract.TaskEntry._ID));
        task.name = mapTask.get(OrganizerContract.TaskEntry.NAME);
        task.description = mapTask.get(OrganizerContract.TaskEntry.DESCRIPTION);
        task.fromDate = mapTask.get(OrganizerContract.TaskEntry.FROMDATE);
        task.fromTime = mapTask.get(OrganizerContract.TaskEntry.FROMTIME);
        task.toDate = mapTask.get(OrganizerContract.TaskEntry.TODATE);
        task.toTime = mapTask.get(OrganizerContract.TaskEntry.TOTIME);
        task.reporter = mapTask.get(OrganizerContract.TaskEntry.REPORTER);
        task.assignee =  mapTask.get(OrganizerContract.TaskEntry.ASSIGNEE);
        task.status = statuses[Integer.valueOf(mapTask.get(OrganizerContract.TaskEntry.STATUS))];
        task.score = Integer.valueOf(mapTask.get(OrganizerContract.TaskEntry.SCORE));
        return task;
    }

    // create task
    // update task
    // comment task
    // react to task
    // pay by task
    // sync ?


}
