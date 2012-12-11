package com.example.myapp;

import com.example.myapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button login = (Button)findViewById(R.id.login);
        //�{�^���̃N���b�N�C�x���g���X�i�[�@��ݒ�
        login.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    

    
    public void onClick(View v) {
        Log.d("MyInfo", v.getId() + v.toString());
        Button b = (Button)v;
        Log.d("MyInfo", "Button");
        
        try {
            if (b.getId() == R.id.login) {
                //id �́@R.id.btnGotoSub�i����ʂɑJ�ڃ{�^���j�ł���
                
                //Intent �C���X�^���X����
                Intent intent = new Intent(getApplicationContext(), ModeSelectActivity.class);
                //����ʂ��N��
                startActivity(intent);
                Log.i("MyInfo", "Start activity ok");
            }
        } catch (Exception e) {
            Log.d("MyInfo", "Erroraaaa", e);
        }
    }
}
