package com.kbk.company_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String userid, userpw; //사용자 입력 데이터 변수
    public String strUserId, strUserPw; //DB 데이터 불러올때 변수
    public String Ruserid, Ruserpw; //회원가입에 쓰는 변수

    Button btn_login, btn_register;
    EditText main_id,main_pw,dlgEdt_id,dlgEdt_pw;
    View dialogView;

    Button btn_test;

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new myDBHelper(this);

        FindID(); //id 설정 모아둔 함수
        LogIn(); //로그인 함수
        Test(); // DB에서 data 가져오는지 확인하는 test버튼
        Register();

    }

    //ID 설정.
    private void FindID() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_test = findViewById(R.id.btn_test);

        main_id = (EditText) findViewById(R.id.main_id);
        main_pw = (EditText) findViewById(R.id.main_pw);
    }

    //회원가입 버튼 이벤트, DB 생성
    private void Register() {

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDialog(v);
            }
        });
    }

/*==========================================================================================TEST======================================================================================*/
    private void Test() {
        //DB에서 가져오는 데이터확인용 테스트 버튼.
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                userid = main_id.getText().toString(); //id,pw입력창에 입력한 데이터를 userid, userpw로 설정
                userpw = main_pw.getText().toString();
                Cursor cursor; //id커서
                //cursor = sqlDB.rawQuery("SELECT * FROM userDB",null);
                cursor = sqlDB.rawQuery("SELECT * FROM userDB WHERE userID LIKE '" + userid + "' AND userPW LIKE'" + userpw + "';", null);

                strUserId = "userID";
                strUserPw = "userPW";
                while (cursor.moveToNext()) {
                    strUserId = cursor.getString(0);
                    strUserPw = cursor.getString(1);
                }


                Toast.makeText(MainActivity.this, strUserId + "\n=====\n" + userid + "\n" + strUserPw + "\n======\n" + userpw, Toast.LENGTH_SHORT).show();

            }
        });
    }
/*=========================================================================================TEST========================================================================================*/

    private void LogIn() {
        //로그인 버튼 이벤트
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sqlDB = myHelper.getReadableDatabase(); //DB읽어오기.
                //id,pw입력창에 입력한 데이터를 userid, userpw로 설정.
                userid = main_id.getText().toString();
                userpw = main_pw.getText().toString();
                Cursor cursor; //데이터 받아올 커서 생성.
                cursor = sqlDB.rawQuery("SELECT * FROM userDB WHERE userID LIKE '"+userid+"' AND userPW LIKE'"+userpw+"';", null);
                //strUserId의 값 넣기
                strUserId = "userID";
                strUserPw = "userPW";

                //커서에 DB데이터 반영.
                while (cursor.moveToNext()) {
                    strUserId = cursor.getString(0);
                    strUserPw = cursor.getString(1);
                }

                try {
                    //ID입력을 안했을 시,
                    if(main_id.length() == 0) {
                        Toast.makeText(MainActivity.this, "ID를 입력하십시오.", Toast.LENGTH_SHORT).show();
                    }
                    //PW입력을 안했을 시,
                    else if(main_pw.length() == 0) {
                        Toast.makeText(MainActivity.this, "PW를 입력하십시오.", Toast.LENGTH_SHORT).show();
                    }
                    else if (userid.equals(strUserId)) {
                        if (userpw.equals(strUserPw)) {
                            //Toast.makeText(MainActivity.this, "userid :   " + strUserId + "\nuserpw : " + strUserPw, Toast.LENGTH_SHORT).show(); //DB에서 불러온 데이터 확인용.
                            Toast.makeText(MainActivity.this, "성공적으로 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                            startActivity(intent);
                        } else if (main_pw.length() == 0)
                            Toast.makeText(MainActivity.this, "PW를 입력하십시오.", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "PW가 다릅니다.", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "등록되지 않은 ID입니다.", Toast.LENGTH_SHORT).show();
                }catch (Exception e) {System.out.println("오류");}
                sqlDB.close();
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
                    Ruserid = dlgEdt_id.getText().toString();
                    Ruserpw = dlgEdt_pw.getText().toString();

                    sqlDB = myHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT INTO userDB VALUES ( '"+ Ruserid + "', '"+ Ruserpw +"');");
                    sqlDB.close();

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
/*==========================================================================================DB======================================================================================*/

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "userDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE userDB ( userID CHAR(20) PRIMARY KEY, userPW CHAR(20));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS userDB");
            onCreate(db);
        }
    }

/*==========================================================================================DB======================================================================================*/


}