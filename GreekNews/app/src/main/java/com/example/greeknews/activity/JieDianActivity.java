package com.example.greeknews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.greeknews.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JieDianActivity extends AppCompatActivity {

    private static final String TAG = "JieDianActivity";
    private String url = "https://www.v2ex.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_dian);
        initData();
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements items = doc.select("div.cell");
                    for (Element element : items) {
                        Elements tv = element.select("table tbody tr td > span.fade");
                        if (tv != null) {
                            String title = tv.text();
                            Log.d(TAG, "initData: " + title);
                        }
                        Elements wenben = element.select("table tbody tr td > a");
                        if (wenben != null) {
                            String attr = wenben.attr("href");
                            String text = wenben.text();
                            Log.d(TAG, "run: " + text);
                            Log.d(TAG, "attr: " + attr);
                        }
                    }

                    //


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
