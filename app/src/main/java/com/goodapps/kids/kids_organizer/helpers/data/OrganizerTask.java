package com.goodapps.kids.kids_organizer.helpers.data;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class OrganizerTask {
    public Integer id;
    public String name;
    public String fromDate;
    public String toDate;
    public String fromTime;
    public String toTime;
    public String reporter;
    public String assignee;
    public String status;
    public Integer score;
    public Integer picture;
    public Bitmap resultImage;
    public ArrayList<OrganizerTaskStep> steps;

    public OrganizerTask() {
        return;
    }

}

