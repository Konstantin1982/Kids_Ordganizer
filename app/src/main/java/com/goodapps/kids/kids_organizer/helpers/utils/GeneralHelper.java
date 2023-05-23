package com.goodapps.kids.kids_organizer.helpers.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.drawerlayout.widget.DrawerLayout;

import com.goodapps.kids.kids_organizer.R;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerContract;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerTask;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class GeneralHelper {

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String getCurrentDateAsString() throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }


    public static String GetAnyDateInString (Date date) {
        String result;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        result = dateFormat.format(date);
        return result;
    }

    public static String GetTitleForListOfTasks(int isChild, Calendar filterDate) {
        String result = "Дела на";
        LocalDate filter = LocalDate.of(filterDate.get(Calendar.YEAR),
                                        filterDate.get(Calendar.MONTH) + 1,
                                        filterDate.get(Calendar.DAY_OF_MONTH));
        LocalDate nowDate = LocalDate.now();
        int diff = Period.between(nowDate, filter).getDays();
        String dateValue = "";
        switch (diff){
            case 0:
                dateValue = " Сегодня";
                break;
            case 1:
                dateValue = " Завтра";
                break;
            case 2:
                dateValue = " Послезавтра";
                break;
            case -1:
                dateValue = " Вчера";
                break;
            case -2:
                dateValue = " Позавчера";
                break;
            default:
                dateValue = DateFormat.getDateInstance().format(filterDate.getTime());
                break;
        }
        return result + dateValue;
    }
    public static String[] GetStatuses(Context context){
        return context.getResources().getStringArray(R.array.tasks_statuses);
    }

    public static void ConfigureMainMenu(Context context, NavigationView view, DrawerLayout drawer){

    }

    public static void LooseFocus(Activity activity, Integer layoutResourceId) {
        activity.findViewById(layoutResourceId).requestFocus();
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
