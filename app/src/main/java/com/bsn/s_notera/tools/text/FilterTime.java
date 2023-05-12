package com.bsn.s_notera.tools.text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FilterTime {
    public String actualTime(){
        return new SimpleDateFormat("dd/MM/yy KK:mm", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
    }
    public String setTime(int minutes){
        long minutesTotal = 60000;
        return new SimpleDateFormat("dd/MM/yy KK:mm", Locale.getDefault()).format(new Date(System.currentTimeMillis() + (minutes * minutesTotal)));
    }
    public String onTime(long time){
        return new SimpleDateFormat("dd/MM/yy KK:mm", Locale.getDefault()).format(new Date(time));
    }
}
