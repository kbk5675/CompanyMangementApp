package com.kbk.practice_dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity {

    Button btn_back;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        RecyclerView recyclerView = findViewById(R.id.list_clients);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ClientAdapter adapter = new ClientAdapter();
        recyclerView.setAdapter(adapter);

        adapter.addItem(new Client("김보근", "사장", "010-9785-5695"));
        adapter.addItem(new Client("김부장", "부장", "010-3212-3333"));
        adapter.addItem(new Client("서대리", "대리", "010-3432-5456"));
        adapter.addItem(new Client("이사원", "사원", "010-4545-1231"));
        adapter.addItem(new Client("임사원", "사원", "010-7756-4353"));
        adapter.addItem(new Client("최사원", "사원", "010-5645-8764"));
        adapter.addItem(new Client("강인턴", "인턴", "010-0186-5483"));

        FindID(); // widget id 지정.
        Back(); //뒤로가기 버튼.
        Add_Client(); //직원 추가.



    }

    private void Add_Client() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "미구현..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Back() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientActivity.super.onBackPressed();
            }
        });
    }

    private void FindID() {
        btn_add = (Button) findViewById(R.id.btn_clt_add);
        btn_back = (Button) findViewById(R.id.btn_clt_back);

    }
}