package com.kbk.company_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button btn_clients;
    Button btn_sales;
    Button btn_items;
    Button btn_setting;
    Button btn_backtologin;

    MainActivity.myDBHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        FindID();

        ManageClient();
        ManageSales();
        ManageItems();
        Setting();

        btn_backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.super.onBackPressed();
            }
        });

    }

    private void ManageClient() {
        btn_clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ClientActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ManageSales() {
        btn_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SalesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ManageItems() {
        btn_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ItemsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Setting() {
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void FindID() {
        btn_clients = (Button) findViewById(R.id.btn_client);
        btn_sales = (Button) findViewById(R.id.btn_sales);
        btn_items = (Button) findViewById(R.id.btn_items);
        btn_setting = (Button) findViewById(R.id.btn_setting);
        btn_backtologin = (Button) findViewById(R.id.btn_backtologin);
    }
}