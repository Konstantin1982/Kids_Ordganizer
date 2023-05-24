package com.goodapps.kids.kids_organizer.helpers.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

public class OrganizerDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "organizer.db";
    private static final int DATABASE_VERSION = 1;

    public OrganizerDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        CreateTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // nothing to do
    }

    private void CreateTables(@NonNull SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TASK_TABLE =
                "CREATE TABLE " +
                        OrganizerContract.TaskEntry.TABLE_NAME + "(" +
                        OrganizerContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OrganizerContract.TaskEntry.NAME + " TEXT, " +
                        OrganizerContract.TaskEntry.FROMDATE + " TEXT, " +
                        OrganizerContract.TaskEntry.TODATE + " TEXT, " +
                        OrganizerContract.TaskEntry.FROMTIME + " TEXT, " +
                        OrganizerContract.TaskEntry.TOTIME + " TEXT, " +
                        OrganizerContract.TaskEntry.REPORTER + " INTEGER, " +
                        OrganizerContract.TaskEntry.ASSIGNEE + " INTEGER, " +
                        OrganizerContract.TaskEntry.STATUS + " INTEGER, " +
                        OrganizerContract.TaskEntry.SCORE + " INTEGER, " +
                        OrganizerContract.TaskEntry.PICTURE + " INTEGER " +
                        ")";
        final String SQL_CREATE_TASK_STEP_TABLE =
                "CREATE TABLE " +
                        OrganizerContract.TaskSteps.TABLE_NAME + "(" +
                        OrganizerContract.TaskSteps._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OrganizerContract.TaskSteps.TASK_ID + " INTEGER, " +
                        OrganizerContract.TaskSteps.STEP_DESCRIPTION + " TEXT, " +
                        OrganizerContract.TaskSteps.STEP_STATUS + " INTEGER, " +
                        OrganizerContract.TaskSteps.STEP_PICTURE + " INTEGER, " +
                        OrganizerContract.TaskSteps.STEP_ORDER + " INTEGER, " +
                        OrganizerContract.TaskSteps.STEP_RESULT_IMAGE + " VARCHAR(255) " +
                        ")";

        final String SQL_CREATE_SCHEDULE_TABLE =
                "CREATE TABLE " +
                        OrganizerContract.Schedule.TABLE_NAME + "(" +
                        OrganizerContract.Schedule._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OrganizerContract.Schedule.TASK_NAME + " VARCHAR(255), " +
                        OrganizerContract.Schedule.TASK_DESCRIPTION + " TEXT, " +
                        OrganizerContract.Schedule.TASK_SCHEDULE + " VARCHAR(255), " +
                        OrganizerContract.Schedule.TASK_TODATE + " TEXT, " +
                        OrganizerContract.Schedule.TASK_TOTIME + " TEXT, " +
                        OrganizerContract.Schedule.TASK_REPORTER + " INTEGER, " +
                        OrganizerContract.Schedule.TASK_ASSIGNEE + " INTEGER, " +
                        OrganizerContract.Schedule.TASK_PICTURE + " INTEGER " +
                        ")";

        final String SQL_CREATE_COMMENTS_TABLE =
                "CREATE TABLE " +
                        OrganizerContract.Comments.TABLE_NAME + "(" +
                        OrganizerContract.Comments._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OrganizerContract.Comments.COMMENT_TYPE + " TEXT, " +
                        OrganizerContract.Comments.TASK_ID + " INTEGER, " +
                        OrganizerContract.Comments.COMMENT_ID + " INTEGER, " +
                        OrganizerContract.Comments.DATETIME + " TEXT, " +
                        OrganizerContract.Comments.REPORTER + " INTEGER, " +
                        OrganizerContract.Comments.ASSIGNEE + " INTEGER, " +
                        OrganizerContract.Comments.IS_PUSHED + " INTEGER, " +
                        OrganizerContract.Comments.IS_READ + " INTEGER " +
                        ")";

        final String SQL_CREATE_REACTIONS_TABLE =
                "CREATE TABLE " +
                        OrganizerContract.Reactions.TABLE_NAME + "(" +
                        OrganizerContract.Reactions._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OrganizerContract.Reactions.TYPE + " TEXT, " +
                        OrganizerContract.Reactions.TASK_ID + " INTEGER, " +
                        OrganizerContract.Reactions.REPORTER + " INTEGER, " +
                        OrganizerContract.Reactions.DATETIME + " TEXT " +
                        ")";

        final String SQL_CREATE_PAYMENTS_TABLE =
                "CREATE TABLE " +
                        OrganizerContract.Payments.TABLE_NAME + "(" +
                        OrganizerContract.Payments._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OrganizerContract.Payments.CURRENCY + " INTEGER, " +
                        OrganizerContract.Payments.TASK_ID + " INTEGER, " +
                        OrganizerContract.Payments.AMOUNT + " REAL, " +
                        OrganizerContract.Payments.STATUS + " TEXT " +
                        ")";

        final String SQL_CREATE_CURRENCIES_TABLE =
                "CREATE TABLE " +
                        OrganizerContract.Currencies.TABLE_NAME + "(" +
                        OrganizerContract.Currencies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OrganizerContract.Currencies.NAME + " TEXT, " +
                        OrganizerContract.Currencies.PICTURE + " INTEGER " +
                        ")";

        final String SQL_CREATE_PROFILES_TABLE =
                "CREATE TABLE " +
                        OrganizerContract.Profiles.TABLE_NAME + "(" +
                        OrganizerContract.Profiles._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OrganizerContract.Profiles.NAME + " TEXT, " +
                        OrganizerContract.Profiles.OWNER + " INTEGER, " +
                        OrganizerContract.Profiles.TYPE + " TEXT, " +
                        OrganizerContract.Profiles.EMAIL + " TEXT, " +
                        OrganizerContract.Profiles.EMAIL_CONFIRMED + " INTEGER, " +
                        OrganizerContract.Profiles.MY_GUID + " VARCHAR(255) " +
                        ")";

        sqLiteDatabase.execSQL(SQL_CREATE_TASK_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TASK_STEP_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SCHEDULE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_COMMENTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_REACTIONS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PAYMENTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CURRENCIES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PROFILES_TABLE);
        Log.e("DB_HELPER", "Database created");
    }
}