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
        // �����v���O���X�o�[�̍ő�l��ݒ肵�܂�
        progressBar1.setMax(100);
        // �����v���O���X�o�[�̒l��ݒ肵�܂�
        progressBar1.setProgress(70);
        
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        // �����v���O���X�o�[�̍ő�l��ݒ肵�܂�
        progressBar2.setMax(100);
        // �����v���O���X�o�[�̒l��ݒ肵�܂�
        progressBar2.setProgress(30);
        
        ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        // �����v���O���X�o�[�̍ő�l��ݒ肵�܂�
        progressBar3.setMax(100);
        // �����v���O���X�o�[�̒l��ݒ肵�܂�
        progressBar3.setProgress(80);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_status, menu);
        return true;
    }
}
