package com.goodapps.kids.kids_organizer.helpers.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.NonNull;

import com.goodapps.kids.kids_organizer.helpers.utils.GeneralHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class OrganizerDBManager {

    private OrganizerDBHelper mDBHelper;

    public OrganizerDBManager(Context context) {
        mDBHelper = new OrganizerDBHelper(context);
    }

    private long insertOrUpdateRecord(int id, String table_name, ContentValues values) {
        long result = 0;
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        if (id == 0) {
            result = db.insert(table_name, null, values);
        } else {
            result = db.update(table_name, values, BaseColumns._ID + " = ? ", new String[]{String.valueOf(id)});
        }
        return result;
    }

    private Cursor getAnyData(String query) {
        return mDBHelper.getReadableDatabase().rawQuery(query, null);
    }

    private void insertAnyData(String query, String[] strings) {
        mDBHelper.getWritableDatabase().execSQL(query, strings);
    }


    public void generateTestData() {
        // check if data exists
        String rawQuery = "select * from " + OrganizerContract.Profiles.TABLE_NAME;
        Cursor result = getAnyData(rawQuery);
        Log.e("DB_MANAGER", "Count in profiles = " + result.getCount());
        if (result.getCount() <= 0) {
            String sql = "";

            sql = "INSERT INTO profiles (is_owner, name, type, email, is_confirmed, unique_id) " +
                    "VALUES (?, ?, ?, ?, 1, ?) ";
            insertAnyData(sql, new String[]{"1", "Папа", "1", "k@k.k", UUID.randomUUID().toString()});
            insertAnyData(sql, new String[]{"0", "Дарик", "0", "", UUID.randomUUID().toString()});
            insertAnyData(sql, new String[]{"0", "Ланя", "0", "", UUID.randomUUID().toString()});
            insertAnyData(sql, new String[]{"0", "Златка", "0", "", UUID.randomUUID().toString()});
        }

        rawQuery = "select * from " + OrganizerContract.TaskEntry.TABLE_NAME;
        result = getAnyData(rawQuery);
        Log.e("DB_MANAGER", "Count in tasks = " + result.getCount());
        if (result.getCount() <= 0) {
            String sql = "INSERT INTO tasks (name, description, from_date, to_date, from_time, to_time, reporter, assignee, status, score, picture) " +
                    "VALUES (?, ?, date('NOW'), ?, time('NOW'), ?, 0, 1, 0, 0, 0) ";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:MM:SS");

            insertAnyData(sql, new String[]{
                    "Помыть посуду",
                    "1. Вымыть посуду. 2. Вытереть стол 3. Убрать за собой",
                    dateFormat.format(new Date()),
                    timeFormat.format(new Date())
                }
            );
            insertAnyData(sql, new String[]{"Погулять с собакой", "1. Выгулять собаку. 2.Помыть ей лапы 3. Покормить собаку", dateFormat.format(new Date()), timeFormat.format(new Date())});
        }
    }

    @NonNull
    private ArrayList<HashMap<String, String>> getMapsFromCursor(Cursor data) {
        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        if (data != null) {
            String[] columns = data.getColumnNames();
            for (int i = 0; i < data.getCount(); i++) {
                HashMap<String, String> item = new HashMap<String, String>();
                data.moveToPosition(i);
                for (String column : columns) {
                    int index = data.getColumnIndex(column);
                    item.put(column, data.getString(index));
                }
                result.add(item);
            }
        }
        return result;
    }


    public HashMap<String, String> getCurrentUser() {
        HashMap<String, String> result = new HashMap<String, String>();
        Cursor dbUser = getAnyData("SELECT * FROM " + OrganizerContract.Profiles.TABLE_NAME +
                " WHERE " + OrganizerContract.Profiles.OWNER + " = 1 LIMIT 1;");
        return getMapsFromCursor(dbUser).get(0);
    }


    public ArrayList<HashMap<String, String>> getTasks(Integer showCompleted, String dateCompleted) throws ParseException {
        return getTasks(showCompleted, dateCompleted, -1);
    }

    private ArrayList<HashMap<String, String>> getTasks(Integer showCompleted, String dateCompleted, Integer taskId) throws ParseException {
        Cursor tasksFromDb;
        String completedFilter = "";
        String dateFilter = "";
        String idFilter = "";
        if (taskId < 0) {
            if (showCompleted == 0)
                completedFilter = " AND t." + OrganizerContract.TaskEntry.STATUS + " < 2";
            if (dateCompleted != "") {
                dateFilter = " AND t." + OrganizerContract.TaskEntry.TODATE + " LIKE '" + dateCompleted + "'";
            } else {
                dateFilter = " AND t." + OrganizerContract.TaskEntry.TODATE + " LIKE '" + GeneralHelper.getCurrentDateAsString() + "'";
            }
        } else {
            idFilter = " AND t." + OrganizerContract.TaskEntry._ID  + " =  " + taskId;
        }
        String sql = "Select "
                + " t." + OrganizerContract.TaskEntry._ID + ", "
                + " t." + OrganizerContract.TaskEntry.NAME + ", "
                + " t." + OrganizerContract.TaskEntry.DESCRIPTION + ", "
                + " t." + OrganizerContract.TaskEntry.FROMDATE + ", "
                + " t." + OrganizerContract.TaskEntry.TODATE + ", "
                + " t." + OrganizerContract.TaskEntry.FROMTIME + ", "
                + " t." + OrganizerContract.TaskEntry.TOTIME + ", "
                + " p1." + OrganizerContract.Profiles.NAME + " as " + OrganizerContract.TaskEntry.REPORTER + ", "
                + " p2." + OrganizerContract.Profiles.NAME + " as " + OrganizerContract.TaskEntry.ASSIGNEE + ", "
                + " t." + OrganizerContract.TaskEntry.STATUS + ", "
                + " t." + OrganizerContract.TaskEntry.SCORE + ", "
                + " t." + OrganizerContract.TaskEntry.PICTURE +
                " from " + OrganizerContract.TaskEntry.TABLE_NAME + " t " +
                " LEFT OUTER  JOIN " + OrganizerContract.Profiles.TABLE_NAME +
                " p1 ON t." + OrganizerContract.TaskEntry.REPORTER + " = p1." + OrganizerContract.Profiles.NAME +
                " LEFT OUTER  JOIN " + OrganizerContract.Profiles.TABLE_NAME +
                " p2 ON t." + OrganizerContract.TaskEntry.ASSIGNEE + " = p2." + OrganizerContract.Profiles.NAME +
                " WHERE 1 = 1 " + completedFilter  + dateFilter + idFilter + " ;"
                ;
        Log.e("Get list of task", sql);
        tasksFromDb = getAnyData(sql);

        return getMapsFromCursor(tasksFromDb);
    }

    public HashMap<String, String> getTaskById(Integer taskId) throws ParseException {
        ArrayList<HashMap<String, String>> tasks = getTasks(0, "", taskId);
        if (tasks.size() > 0)
            return tasks.get(0);
        else
            return null;
    }

    public ArrayList<HashMap<String, String>> getAllAssignee(){
        Cursor usersFromDB;
        String sql = "SELECT " + OrganizerContract.Profiles._ID + ", "
                     + OrganizerContract.Profiles.NAME + " FROM "
                     + OrganizerContract.Profiles.TABLE_NAME + " WHERE "
                     + OrganizerContract.Profiles.TYPE + " = 0 "
        ;
        usersFromDB = getAnyData(sql);
        return getMapsFromCursor(usersFromDB);
    }

    public ArrayList<HashMap<String, String>> getAllReporters(){
        Cursor usersFromDB;
        String sql = "SELECT " + OrganizerContract.Profiles._ID + ", "
                     + OrganizerContract.Profiles.NAME + " FROM "
                     + OrganizerContract.Profiles.TABLE_NAME + " WHERE "
                     + OrganizerContract.Profiles.TYPE + " = 0 "
        ;
        usersFromDB = getAnyData(sql);
        return getMapsFromCursor(usersFromDB);
    }
}
