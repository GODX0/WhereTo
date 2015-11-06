package com.hardsign.whereto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView mainMenuList;
    String[] names = { "Куда пойти c... ?", "Где отпразновать?", "Предложить событие"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMenuList=(ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        mainMenuList.setAdapter(adapter);

        mainMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (id == 0) {
                    startActivity(new Intent(MainActivity.this, ListPlacesActivity.class));
                }

                if (id == 1) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "В разработке!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (id == 2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Важное сообщение!")
                            .setMessage("Тут будут правила для предложения события")
                            .setCancelable(false)
                            .setNegativeButton("Принимаю",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }
}
