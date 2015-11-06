package com.hardsign.whereto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 06.11.2015.
 */
public class ListPlacesActivity extends AppCompatActivity {
    private TextView myTextView;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listplaces);

        imgView = (ImageView) findViewById(R.id.imageView);

        ImageManager IM=new ImageManager();
        IM.fetchImage("http://dip-games.3dn.ru/hackathon/4mini.jpg",imgView);
    }
}
