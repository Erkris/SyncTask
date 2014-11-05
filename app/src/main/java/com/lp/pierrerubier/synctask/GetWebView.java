package com.lp.pierrerubier.synctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * Created by pierrerubier on 17/10/2014.
 */
public class GetWebView extends AsyncTask<Object, Void, String> {

    WebView myWebView;
    ProgressDialog waiting;

    Context myContext;
    int buttonClicked;

    public GetWebView(Context c) {
        myContext = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        waiting = new ProgressDialog(myContext);
        waiting.setMessage("Waiting...");
        waiting.show();
    }

    @Override
    protected String doInBackground(Object... tab) {
        String adress = "";
        HttpURLConnection urlConnection;
        BufferedReader in;
        String PHtml = "";
        String urlString;

        myWebView = (WebView)tab[0];
        buttonClicked = (Integer)tab[1];

        switch (buttonClicked) {
            case 1:
                adress = "http://www.google.fr";
                break;
            case 2:
                adress = "file:///android_asset/test.html";
                break;
            case 3:
                adress = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.atom";
                break;
            case 4:

                urlString = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_hour.geojson";
                try {
                    URL url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        in = new BufferedReader(new
                                InputStreamReader(urlConnection.getInputStream()));
                        while ((PHtml = in.readLine()) != null) {
                            adress += PHtml;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 5:
                urlString = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson";

                // récupération du JSON sous forme de String
                try {
                    URL url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        in = new BufferedReader(new
                                InputStreamReader(urlConnection.getInputStream()));
                        while ((PHtml = in.readLine()) != null) {
                            adress += PHtml;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }

        return adress;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (buttonClicked == 4) {
            myWebView.loadData(s, "text/html; charset = utf-8", null);
            waiting.dismiss();
        } else if (buttonClicked == 5) {
            Intent i = new Intent(myContext, ParseActivity.class);
            i.putExtra("stringJSON", s);
            myContext.startActivity(i);
        } else {
            myWebView.loadUrl(s);
            waiting.dismiss();
        }
    }
}
