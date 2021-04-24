package com.kbk.practice_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;

    public String userid = "vczxvreaqvc", userpw = "vcvaeqafcvd";

    Button btn_login, btn_register;
    EditText main_id,main_pw,dlgEdt_id,dlgEdt_pw;
    View dialogView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FindID(); //id 설정 모아둔 함수
        //textView = findViewById(R.id.test);

        //로그인 버튼 이벤트
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = main_id.getText().toString();
                String pw = main_pw.getText().toString();
                try {
                    if(main_id.length() == 0) {
                        Toast.makeText(MainActivity.this, "ID를 입력하십시오.", Toast.LENGTH_SHORT).show();
                    }
                    else if(main_pw.length() == 0) {
                        Toast.makeText(MainActivity.this, "PW를 입력하십시오.", Toast.LENGTH_SHORT).show();
                    }
                    else if (userid.equals(id) || userid.equals("admin")) {
                        if (userpw.equals(pw) || userpw.equals("admin")) {
                            Toast.makeText(MainActivity.this, "성공적으로 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                            startActivity(intent);
                        } else if (pw.isEmpty())
                            Toast.makeText(MainActivity.this, "PW를 입력하십시오.", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "PW가 다릅니다.", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "등록되지 않은 ID입니다.", Toast.LENGTH_SHORT).show();
                }catch (Exception e) {System.out.println("오류");}
            }
        });

        //회원가입 버튼 이벤트
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDialog(v);
            }
        });
    }

    //회원가입 창 Dialog funtion
    private void registerDialog(View v) {
        dialogView = (View) View.inflate(MainActivity.this, R.layout.register_dialog, null);
        AlertDialog.Builder rgistDlg = new AlertDialog.Builder(MainActivity.this);
        rgistDlg.setTitle("         사용자 정보 입력");
        rgistDlg.setIcon(R.drawable.people);
        rgistDlg.setView(dialogView);
        rgistDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dlgEdt_id = (EditText) dialogView.findViewById(R.id.dlgEdt_id); //dialogView.findViewById 해줘야 dialog 화면 안에서 선언해야 안에서 쓸 수 있음.
                dlgEdt_pw = (EditText) dialogView.findViewById(R.id.dlgEdt_pw);

                if(dlgEdt_id.length() != 0 && dlgEdt_pw.length() != 0) {
                    userid = dlgEdt_id.getText().toString();
                    userpw = dlgEdt_pw.getText().toString();
                    Toast.makeText(MainActivity.this, "가입되셨습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder inputErrordlg = new AlertDialog.Builder(v.getContext());
                    inputErrordlg.setTitle("입력오류");
                    inputErrordlg.setMessage("ID와 PW 모두 입력해주세요.");
                    inputErrordlg.setPositiveButton("확인",null);
                    inputErrordlg.show();

                }

            }
        });
        rgistDlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"취소하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        rgistDlg.show();
    }
    //ID 설정.
    private void FindID() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);

        main_id = (EditText) findViewById(R.id.main_id);
        main_pw = (EditText) findViewById(R.id.main_pw);

    }
}