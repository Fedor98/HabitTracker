package com.example.fedor.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by fedor on 29.06.2017.
 */

public class HabitContract {

    private HabitContract() {}

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_TYPE = "type";
        public static final String COLUMN_HABIT_FREQUENCY = "frequency";
        public static final String COLUMN_HABIT_GROUP = "group activity";

        public static final int FREQUENCY_DAILY = 0;
        public static final int FREQUENCY_WEEKLY = 1;
        public static final int FREQUENCY_MONTHLY = 2;

        public static final int GROUP_NO = 0;
        public static final int GROUP_YES = 1;

    }
}
