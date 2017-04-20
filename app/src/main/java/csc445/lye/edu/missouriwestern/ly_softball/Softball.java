package csc445.lye.edu.missouriwestern.ly_softball;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Lisa on 4/18/2017.
 */

public class Softball {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mHere;


    public Softball(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId(){
        return mId;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isHere(){
        return mHere;
    }

    public void setHere(boolean here){
        mHere = here;
    }
}
