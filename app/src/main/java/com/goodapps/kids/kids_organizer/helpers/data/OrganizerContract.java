package com.goodapps.kids.kids_organizer.helpers.data;

import android.provider.BaseColumns;

public class OrganizerContract {

    public static final class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String FROMDATE = "from_date";
        public static final String TODATE = "to_date";
        public static final String FROMTIME = "from_time";
        public static final String TOTIME = "to_time";
        public static final String REPORTER = "reporter";
        public static final String ASSIGNEE = "assignee";
        public static final String STATUS = "status";
        public static final String SCORE = "score";
        public static final String PICTURE = "picture";
    }

    public static final class Schedule implements BaseColumns {
        public static final String TABLE_NAME = "schedule";
        public static final String TASK_NAME = "task_name";
        public static final String TASK_DESCRIPTION = "task_description";
        public static final String TASK_SCHEDULE = "task_schedule";
        public static final String TASK_TODATE = "task_todate";
        public static final String TASK_TOTIME = "task_totime";
        public static final String TASK_REPORTER = "task_reporter";
        public static final String TASK_ASSIGNEE = "task_assignee";
        public static final String TASK_PICTURE = "picture";
    }


    public static final class Comments implements BaseColumns {
        public static final String TABLE_NAME = "comments";
        public static final String COMMENT_TYPE = "type";
        public static final String TASK_ID = "task_id";
        public static final String COMMENT_ID = "comment_id";
        public static final String DATETIME = "comment_datetime";
        public static final String REPORTER = "reporter";
        public static final String ASSIGNEE = "assignee";
        public static final String IS_PUSHED = "is_pushed";
        public static final String IS_READ = "is_read";
    }

    public static final class Reactions implements BaseColumns {
        public static final String TABLE_NAME = "reactions";
        public static final String TYPE = "type";
        public static final String TASK_ID = "task_id";
        public static final String REPORTER = "reporter";
        public static final String DATETIME = "reaction_datetime";
    }

    public static final class Payments implements BaseColumns {
        public static final String TABLE_NAME = "payments";
        public static final String CURRENCY = "currency_id";
        public static final String TASK_ID = "task_id";
        public static final String AMOUNT = "amount";
        public static final String STATUS = "status";
    }

    public static final class Currencies implements BaseColumns {
        public static final String TABLE_NAME = "currencies";
        public static final String NAME = "name";
        public static final String PICTURE = "picture";
    }

    public static final class Profiles implements BaseColumns {
        public static final String TABLE_NAME = "profiles";
        public static final String OWNER = "is_owner"; // owner of device
        public static final String NAME = "name";
        public static final String TYPE = "type"; // child, parent
        public static final String EMAIL = "email";
        public static final String EMAIL_CONFIRMED = "is_confirmed";
        public static final String MY_GUID = "unique_id";
    }


}
