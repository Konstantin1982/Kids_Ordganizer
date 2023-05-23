package com.goodapps.kids.kids_organizer.helpers.utils;

import android.content.Context;

import com.goodapps.kids.kids_organizer.R;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerContract;
import com.goodapps.kids.kids_organizer.helpers.data.OrganizerDBManager;

import java.util.HashMap;

public class CurrentUser {
    private static CurrentUser currentUser;

    public String name;
    public String type;
    public String email;
    public Integer email_confirmed;
    public String guid;
    public Integer is_premium;


    private CurrentUser(Context context){
        OrganizerDBManager dbManager = new OrganizerDBManager(context);
        HashMap<String, String> dbUser =  dbManager.getCurrentUser();
        if (dbUser != null) {
            name = dbUser.get(OrganizerContract.Profiles.NAME);
            type = dbUser.get(OrganizerContract.Profiles.TYPE);
            email = dbUser.get(OrganizerContract.Profiles.EMAIL);
            email_confirmed = Integer.valueOf(dbUser.get(OrganizerContract.Profiles.EMAIL_CONFIRMED));
            guid = dbUser.get(OrganizerContract.Profiles.MY_GUID);
        } else {
            name = "Not defined";
            type = context.getResources().getStringArray(R.array.user_types)[0];
            email = "not@defined.com";
            email_confirmed = 0;
            guid = java.util.UUID.randomUUID().toString();
        }
        is_premium = 1;
    }

    public static CurrentUser getInstance(Context context) {
        if (currentUser == null) {
            currentUser = new CurrentUser(context);
        }
        return currentUser;
    }
}