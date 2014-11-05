package com.lp.pierrerubier.synctask;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.iem.asynctask.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ParseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);

        TextView output = (TextView) findViewById(R.id.printJSON);
        String input = getIntent().getStringExtra("stringJSON");

        JSONObject json = null;
        String getTitle = "";
        try {
            // On associe à notre JSON Object la String du JSON récupérer
            json = new JSONObject(input);

            JSONArray jArray = json.getJSONArray("features");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jsonProperties = jArray.getJSONObject(i).getJSONObject("properties");
                getTitle = jsonProperties.getString("title");
            }
            Log.d("json", json+"");
            Log.d("jArray", jArray+"");
            Log.d("getTitle", getTitle+"");
            output.setText(getTitle);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
