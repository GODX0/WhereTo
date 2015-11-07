package com.hardsign.whereto;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private TextView myTextView;
    private ImageView imgView;
    public static String LOG_TAG = "my_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listplaces);

        imgView = (ImageView) findViewById(R.id.imageView);

        ImageManager IM=new ImageManager();
        IM.fetchImage("http://dip-games.3dn.ru/hackathon/4mini.jpg",imgView);

        new ParseTask().execute();


    }
    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL("http://kudavl.ru/adapter.php");

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
                   //json_data = jArray.getJSONObject(i);

                   // String name = json_data.getString("Name");
                    //String description = json_data.getString("Description");

                  //  Log.d(LOG_TAG, "name: " + name);
                  //  Log.d(LOG_TAG, "description: " + description);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
