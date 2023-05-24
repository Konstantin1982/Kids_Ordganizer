package com.goodapps.kids.kids_organizer.helpers.utils;
import android.content.Context;

import com.goodapps.kids.kids_organizer.helpers.data.OrganizerContract;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerDBManager;
import java.util.ArrayList;
import java.util.HashMap;


public class DBEntitiesHelper {
    private OrganizerDBManager dbManager;

    public DBEntitiesHelper(Context context) {
        dbManager = new OrganizerDBManager(context);
    }

    public ArrayList<String> getUserNames(Integer type) {
        switch (type){
            case 0: // assignee
                return mapsNames(dbManager.getAllAssignee());
            case 1: // reporters
                return mapsNames(dbManager.getAllReporters());
            default:
                return null;
        }
    }

    private  ArrayList<String> mapsNames(ArrayList<HashMap<String, String>> usersFromDB) {
        ArrayList<String> names = new ArrayList<>();
        for (HashMap<String, String> userTask : usersFromDB){
            names.add(userTask.get(OrganizerContract.Profiles.NAME));
        }
        return names;
    }
}
