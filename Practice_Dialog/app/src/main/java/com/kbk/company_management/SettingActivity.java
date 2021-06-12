package com.kbk.company_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    Button btn_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        FindID();
        VersionInfo();


    }

    private void VersionInfo() {
        btn_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Version_1.4", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void FindID() {
        btn_version = findViewById(R.id.btn_version);

    }
}