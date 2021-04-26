package com.kbk.practice_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    Button btn_version;

    CheckBox chk_En;
    CheckBox chk_Kr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        FindID();
        CheckBox_Language();
    }

    //언어설정 체크박스
    private void CheckBox_Language() {
        btn_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Version_1.0", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 500);
                toast.show();
            }
        });
        //영어체크박스
        chk_En.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk_En.isChecked())
                    Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "한국어", Toast.LENGTH_SHORT).show();
            }
        });
        //한국어체크박스
        chk_Kr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk_En.isChecked())
                    Toast.makeText(getApplicationContext(), "한국어", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void FindID() {
        btn_version = findViewById(R.id.btn_version);
        chk_En = findViewById(R.id.chk_En);
        chk_Kr = findViewById(R.id.chk_Kr);
    }
}