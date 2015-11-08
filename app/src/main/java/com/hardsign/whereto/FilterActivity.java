package com.hardsign.whereto;

/**
 * Created by User on 08.11.2015.
 */
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    private TextView myTextView;
    private ListView mainMenuList;
    String[] names = { "Попова","Лазурная","Остров Шкота","Золотой Рог", "Холодильник"};

    private ArrayList<ListData> catalog;
    int[] cost={30000,40000,300,5000,3000};
    int[] img={R.drawable.pic01,R.drawable.pic02,
            R.drawable.pic03,R.drawable.pic04,R.drawable.pic05};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String message=intent.getStringExtra("message");
        myTextView = (TextView)findViewById(R.id.textView);
        myTextView.setText(message);

        mainMenuList = (ListView) findViewById(R.id.listView);


        catalog=new ArrayList<ListData>();
        for(int i=1; i<=5; i++){
            catalog.add(new ListData(names[i-1], cost[i-1], img[i-1]));
        }

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        CatalogAdapter catAdapter;
        catAdapter=new CatalogAdapter(this, catalog);
        //setListAdapter(catAdapter);

        mainMenuList.setAdapter(catAdapter);


        mainMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                myTextView.setText("Выброна строка иномер " + position);
            }
        });

    }
}
