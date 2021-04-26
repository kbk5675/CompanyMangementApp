package com.kbk.practice_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity {

    Button btn_back;
    Button btn_add;

    View dialogView;

    EditText dlgEdt_addname;
    EditText dlgEdt_addjob;
    EditText dlgEdt_addphone;



    ClientAdapter adapter = new ClientAdapter(); //함수에서도 쓸 수 있게 전역선언.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        RecyclerView recyclerView = findViewById(R.id.list_clients);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        adapter.addItem(new Client("김보근", "사장", "010-9785-5695"));
        adapter.addItem(new Client("김종은", "부장", "010-3212-3333"));
        adapter.addItem(new Client("서대리", "과장", "010-3432-5456"));
        adapter.addItem(new Client("이사원", "팀장", "010-4545-1231"));
        adapter.addItem(new Client("양대리", "대리", "010-7756-4353"));
        adapter.addItem(new Client("최사원", "사원", "010-5645-8764"));
        adapter.addItem(new Client("임이삭", "인턴", "010-0186-5483"));

        FindID(); // widget id 지정.
        Back(); //뒤로가기 버튼.
        Add_Client(); //직원 추가.



    }

    private void Add_Client() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogView = (View) View.inflate(ClientActivity.this, R.layout.addclient_dialog, null);
                AlertDialog.Builder addClientDlg = new AlertDialog.Builder(ClientActivity.this);
                addClientDlg.setTitle("                 직원 추가");
                addClientDlg.setIcon(R.drawable.itemicon);
                addClientDlg.setView(dialogView);
                addClientDlg.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dlgEdt_addname = (EditText) dialogView.findViewById(R.id.edit_adClntdlg_name);
                        dlgEdt_addjob = (EditText) dialogView.findViewById(R.id.edit_adClntdlg_job);
                        dlgEdt_addphone = (EditText) dialogView.findViewById(R.id.edit_adClntdlg_phone);

                        adapter.addItem(new Client(dlgEdt_addname.getText().toString(), dlgEdt_addjob.getText().toString(), dlgEdt_addphone.getText().toString()));

                        Toast.makeText(getApplicationContext(), "DB미구현으로 저장 안됨...", Toast.LENGTH_SHORT).show();
                    }
                });
                addClientDlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                addClientDlg.show();
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