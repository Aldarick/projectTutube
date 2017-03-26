package com.projects.aldarick.projecttutube;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity{

    private static final String VIDEOTITLE = "TITLE";
    private static final String VIDEOURL = "URL";
    private static final String VIDEODESC = "DESC";
    private static final String VIDEODATE = "DATE";
    private static final String VIDEOCHANNEL = "CHANNEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView txtView = (TextView) findViewById(R.id.videoTitle);
        txtView.setText(getIntent().getStringExtra(VIDEOTITLE));

        txtView = (TextView) findViewById(R.id.videoDate);
        txtView.setText(getIntent().getStringExtra(VIDEODATE));

        txtView = (TextView) findViewById(R.id.videoDescription);
        txtView.setText(getIntent().getStringExtra(VIDEODESC));

        txtView = (TextView) findViewById(R.id.videoChannel);
        txtView.setText(getIntent().getStringExtra(VIDEOCHANNEL));

    }




    public static void start(Context context, String title, String url, String description, String date, String channel) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(VIDEOTITLE, title);
        intent.putExtra(VIDEODATE, date);
        intent.putExtra(VIDEOURL, url);
        intent.putExtra(VIDEODESC, description);
        intent.putExtra(VIDEOCHANNEL, channel);
        context.startActivity(intent);
    }


}

