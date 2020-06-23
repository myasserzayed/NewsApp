package com.example.android.newsapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsModel>> {

    private String mUrl;

    public NewsLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<NewsModel> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<NewsModel> newsList = QueryUtils.fetchNewsData(mUrl);
        return newsList;
    }
}
