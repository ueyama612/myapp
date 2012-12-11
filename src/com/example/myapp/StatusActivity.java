package com.example.myapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;

public class StatusActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        
        ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        // 水平プログレスバーの最大値を設定します
        progressBar1.setMax(100);
        // 水平プログレスバーの値を設定します
        progressBar1.setProgress(70);
        
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        // 水平プログレスバーの最大値を設定します
        progressBar2.setMax(100);
        // 水平プログレスバーの値を設定します
        progressBar2.setProgress(30);
        
        ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        // 水平プログレスバーの最大値を設定します
        progressBar3.setMax(100);
        // 水平プログレスバーの値を設定します
        progressBar3.setProgress(80);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_status, menu);
        return true;
    }
}
