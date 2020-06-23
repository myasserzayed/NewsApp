package com.example.android.newsapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsModel> {
    private TextView newsTitle;
    private TextView secName;
    private TextView mDate;
    private TextView mTime;
    private TextView mAuthor;
    String date;
    String time;
    private static final String LOCATION_SEPARATOR = "T";


    public NewsAdapter(Context mContext, List<NewsModel> results) {
        super(mContext, 0, results);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        NewsModel currentNews = getItem(position);
        newsTitle = listItemView.findViewById(R.id.news_title_detailed);
        String title = currentNews.getmTitle();
        newsTitle.setText(title);

        secName = listItemView.findViewById(R.id.section_name_detailed);
        String section = currentNews.getmSecName();
        secName.setText(section);

        String dateTotal = currentNews.getmDate();
        if (dateTotal.contains(LOCATION_SEPARATOR)) {
            String[] parts = dateTotal.split(LOCATION_SEPARATOR);
            date = parts[0];
            time = parts[1];
        }
        mDate = listItemView.findViewById(R.id.date_detailed);
        mDate.setText(date);
        mTime = listItemView.findViewById(R.id.time_detailed);
        mTime.setText(time);

        mAuthor = listItemView.findViewById(R.id.author_detailed);
        String author = currentNews.getmAuthor();
        mAuthor.setText(author);

        return listItemView;
    }
}

