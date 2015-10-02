package com.example.yalin.criminalintent;

import java.util.UUID;

/**
 * Created by yalin on 10/2/2015.
 */
public class Crime {

    private UUID mId;
    private String mTitle;

    public Crime() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
