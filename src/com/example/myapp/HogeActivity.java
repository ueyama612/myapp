package com.example.myapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HogeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoge);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hoge, menu);
        return true;
    }
}
