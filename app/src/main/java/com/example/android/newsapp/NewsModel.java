package com.example.android.newsapp;

public class NewsModel {

    private String mTitle;
    private String mSecName;
    private String mDate;
    private String mUrl;
    private String mAuthor;

    public NewsModel(String mTitle, String mSecName, String mDate, String mUrl, String mAuthor) {
        this.mTitle = mTitle;
        this.mSecName = mSecName;
        this.mDate = mDate;
        this.mUrl = mUrl;
        this.mAuthor = mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSecName() {
        return mSecName;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmAuthor() {
        return mAuthor;
    }
}
