package com.example.android.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsModel>> {
    private NewsAdapter mNewsAdapter;
    private static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    private static final int NEWS_LOADER_ID = 1;
    private TextView mEmptyStateTV;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView newsListView = findViewById(R.id.list);
        mEmptyStateTV = findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTV);
        mNewsAdapter = new NewsAdapter(this, new ArrayList<NewsModel>());
        newsListView.setAdapter(mNewsAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsModel currentNews = mNewsAdapter.getItem(i);
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(webIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            mProgress = findViewById(R.id.progress_bar);
            mProgress.setVisibility(View.GONE);
            mEmptyStateTV.setText(R.string.no_internet_connection);
        }
    }

    @NonNull
    @Override
    public Loader<List<NewsModel>> onCreateLoader(int id, @Nullable Bundle args) {
        Uri baseUri = Uri.parse(NEWS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsModel>> loader, List<NewsModel> data) {
        mProgress = findViewById(R.id.progress_bar);
        mProgress.setVisibility(View.GONE);
        mEmptyStateTV.setText(R.string.no_news);
        mNewsAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mNewsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsModel>> loader) {
        mNewsAdapter.clear();
    }

}
