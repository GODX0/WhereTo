package com.hardsign.whereto;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 06.11.2015.
 */
public class ListPlacesActivity extends AppCompatActivity {
    private ImageView imgView;
    Button btn1;
    public static String LOG_TAG = "my_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        imgView = (ImageView) findViewById(R.id.imageView);

        ImageManager IM=new ImageManager();
        IM.fetchImage("http://dip-games.3dn.ru/hackathon/4mini.jpg", imgView);

        new ParseTask().execute();

        btn1 = (Button) findViewById(R.id.Button1);
        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListPlacesActivity.this, FilterActivity.class));
            }
        };
        btn1.setOnClickListener(oclBtnOk);
    }
    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                int per=0;
                boolean extreme=false,culture=false,withGirlfriend=false,withGroup=false,withFamily=false,withChild=false,sport=false,other=false,ball=false,
                        sportsGround=false,bicycle=false,extremeSportGround=false,extremeBicycle=false,fortress=false,nature=false,monuments=false,
                        cinema=false,theatres=false,isFree=false,bar=false,bowling=false,billiard=false,childGround=false;
                ball=true;
                withFamily=true;

                String strurl ="http://kudavl.ru/adapter.php";
                if (extreme) {if (per==0) strurl+="?extreme=1"; else strurl+="&extreme=1";per++;}
                if (culture) {if (per==0) strurl+="?clture=1"; else strurl+="&culture=1";per++;}
                if (withGirlfriend) {if (per==0) strurl+="?withGirlfriend=1"; else strurl+="&withGirlfriend=1";per++;}
                if (withFamily) {if (per==0) strurl+="?withFamily=1"; else strurl+="&withFamily=1";per++;}
                if (withGroup) {if (per==0) strurl+="?withGroup=1"; else strurl+="&withGroup=1";per++;}
                if (withChild) {if (per==0) strurl+="?withChild=1"; else strurl+="&withChild=1";per++;}
                if (sport) {if (per==0) strurl+="?sport=1"; else strurl+="&sport=1";per++;}
                if (other) {if (per==0) strurl+="?other=1"; else strurl+="&other=1";per++;}
                if (ball) {if (per==0) strurl+="?ball=1"; else strurl+="&ball=1";per++;}
                if (sportsGround) {if (per==0) strurl+="?sportsGround=1"; else strurl+="&sportsGround=1";per++;}
                if (bicycle) {if (per==0) strurl+="?bicycle=1"; else strurl+="&bicycle=1";per++;}
                if (extremeSportGround) {if (per==0) strurl+="?extremeSportGround=1"; else strurl+="&extremeSportGround=1";per++;}


                Log.d(LOG_TAG, strurl);

                URL url = new URL(strurl);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            // выводим целиком полученную json-строку
            Log.d(LOG_TAG, strJson);

            JSONObject dataJsonObj = null;
            String eventName = "";

            try {
                JSONArray jArray = new JSONArray(strJson);

                JSONObject json_data=null;
                for(int i=0;i<jArray.length();i++)
                {
                   //Log.d(LOG_TAG, jArray.getJSONObject(i));
                   json_data = jArray.getJSONObject(i);

                    String name = json_data.getString("name");
                    //String description = json_data.getString("Description");

                   Log.d(LOG_TAG, "name: " + name);
                  //  Log.d(LOG_TAG, "description: " + description);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
