package com.example.myapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EvaluateActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_evaluate, menu);
        return true;
    }
}
