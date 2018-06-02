package xyz.elcomercio.software.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

    public static String now() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return df.format(Calendar.getInstance().getTime());
    }

}
