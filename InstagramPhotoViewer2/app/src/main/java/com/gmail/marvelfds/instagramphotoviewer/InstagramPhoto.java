package com.gmail.marvelfds.instagramphotoviewer;

import android.text.format.DateUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Core i7 on 5/13/2016.
 */
public class InstagramPhoto {
    public String userName;
    public String caption;
    public String imageUrl;
    public int imageHeight;
    public  int likesCount;
    public String userProfile;
    public  long creationDate;

    public InstagramPhoto(String userName, String caption, String imageUrl, int imageHeight, int likesCount, String userProfile, long creationDate) {
        this.userName = userName;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.likesCount = likesCount;
        this.userProfile = userProfile;
        this.creationDate = creationDate;
    }
    public InstagramPhoto(){

    }
// convert the long format in date
    public String getRelativeTimeStam(){

        DateUtils.formatElapsedTime(this.creationDate);
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        long difference = cal.getTimeInMillis()/1000 - this.creationDate;
        String value ="";
        long x = difference;
        long seconds = x % 60;
        x /= 60;
        long minutes = x % 60;
        x /= 60;
        long hours = x % 24;
        x /= 24;
        long days = x%365;
        x/=365;
        long years =x;
        if (years !=0)
            value= value+ years+ "y ";
        if (days !=0 && days%7==0 )
            value= value + days/7+ "w ";
        if (days !=0 && days%7!=0 )
        value= value + days+ "d ";
        if (hours !=0)
            value= value + hours+ "h ";
        if (minutes !=0)
            value= value + minutes+ "mn ";
        if (seconds !=0)
            value= value + seconds+ "s ";
        return  value + "passed";
    }
    public String getLikesCount(){
        return  likesCount+" likes";
    }
}
