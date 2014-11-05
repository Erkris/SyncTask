package com.lp.pierrerubier.synctask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.iem.asynctask.R;


public class MainActivity extends Activity {

    Button bGoogle, bHTML, bURL, bData, bParser ;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);

        bGoogle = (Button)findViewById(R.id.googleButton);
        bHTML = (Button)findViewById(R.id.htmlButton);
        bURL = (Button)findViewById(R.id.loadURL);
        bData = (Button)findViewById(R.id.loadData);
        bParser = (Button)findViewById(R.id.parseJson);

        bGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetWebView(MainActivity.this).execute(webView, 1);
                //webView.loadUrl("http://www.google.fr");
                //webView.setWebViewClient(new WebViewClient());
            }
        });

        bHTML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetWebView(MainActivity.this).execute(webView, 2);

            }
        });

        bURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetWebView(MainActivity.this).execute(webView, 3);

            }
        });

        bData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetWebView(MainActivity.this).execute(webView, 4);
            }
        });

        bParser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetWebView(MainActivity.this).execute(webView, 5);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
