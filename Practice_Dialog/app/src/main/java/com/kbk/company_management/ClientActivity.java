package com.kbk.company_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClientActivity extends AppCompatActivity {

    String AdduserName, AdduserJob, AdduserMobile; //회원가입창에서 DB정보 받아오는 역할의 변수.
    String bringuserName, bringuserJob, bringuserMobile; //Add변수를 cursor를 통해 받아오는 변수.
    String[] userName = new String[15];
    String[] userJob = new String[15];
    String[] userMobile = new String[15];

    static int i = 0;

    Button btn_back;
    Button btn_add;
    Button btnTest;

    View dialogView;

    EditText dlgEdt_addname;
    EditText dlgEdt_addjob;
    EditText dlgEdt_addphone;

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    ClientAdapter adapter = new ClientAdapter(); //함수에서도 쓸 수 있게 전역선언.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        FindID(); // widget id 지정.
        Back(); //뒤로가기 버튼.
        Add_Client(); //직원 추가.

        myHelper = new myDBHelper(ClientActivity.this);
        sqlDB = myHelper.getReadableDatabase(); //DB읽어오기.

/*==========================================================================================TEST======================================================================================*/
/*

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                //cursor = sqlDB.rawQuery("SELECT * FROM userinfoTBL WHERE userName LIKE '김상무' AND userJob LIKE'상무';", null);
                cursor = sqlDB.rawQuery("SELECT * FROM userinfoTBL;", null);

                String name = "";
                String job = "";
                String mobile = "";

                while (cursor.moveToNext()) {
                    name += cursor.getString(0) + " ";
                    job += cursor.getString(1) + " ";
                    mobile += cursor.getString(2) + " ";
                }

                userName = name.split(" ");
                userJob = job.split(" ");
                userMobile = mobile.split(" ");


                for (i=0; i< userJob.length; i++) {
                    adapter.addItem(new Client(userName[i], userJob[i], userMobile[i]));
                }
                adapter.notifyDataSetChanged();
                sqlDB.close();
                Toast.makeText(getApplicationContext(), userName[0] + "\n" + userJob[0] + "\n" + userMobile[0], Toast.LENGTH_SHORT).show();
                //Toast.makeText(ClientActivity.this, name + "\n===\n" +job, Toast.LENGTH_SHORT).show();


            }
        });
*/
/*==========================================================================================TEST======================================================================================*/

        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        //cursor = sqlDB.rawQuery("SELECT * FROM userinfoTBL WHERE userName LIKE '임채은' AND userJob LIKE'노비';", null);
        cursor = sqlDB.rawQuery("SELECT * FROM userinfoTBL;", null);

        String name = "";
        String job = "";
        String mobile = "";

        while (cursor.moveToNext()) {
            name += cursor.getString(0) + " ";
            job += cursor.getString(1) + " ";
            mobile += cursor.getString(2) + " ";
        }

        userName = name.split(" ");
        userJob = job.split(" ");
        userMobile = mobile.split(" ");


        for (i=0; i< userJob.length; i++) {
            adapter.addItem(new Client(userName[i], userJob[i], userMobile[i]));
        }
        adapter.notifyDataSetChanged();
        sqlDB.close();



        //recyclerView 이용한 직원 구성도 제작.
        RecyclerView recyclerView = findViewById(R.id.list_clients);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false); //RecyclerView의 스크린 목록을 구현.
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
        sqlDB.close();


    }


    //직원 추가 기능.
    private void Add_Client() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //추가 버튼 누르면 생성되는 dialog.
                dialogView = (View) View.inflate(ClientActivity.this, R.layout.addclient_dialog, null);
                AlertDialog.Builder addClientDlg = new AlertDialog.Builder(ClientActivity.this);
                addClientDlg.setTitle("                 직원 추가");
                addClientDlg.setIcon(R.drawable.itemicon);
                addClientDlg.setView(dialogView);
                //dialog안에서 추가 버튼 눌렀을 경우.
                addClientDlg.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //사용자가 입력창에 입력하는 데이터
                        dlgEdt_addname = (EditText) dialogView.findViewById(R.id.edit_adClntdlg_name);
                        dlgEdt_addjob = (EditText) dialogView.findViewById(R.id.edit_adClntdlg_job);
                        dlgEdt_addphone = (EditText) dialogView.findViewById(R.id.edit_adClntdlg_phone);
                        //사용자가 입력한 칸이 비어있지않을 경우에만 해당 변수에 저장
                        if(dlgEdt_addname.length() != 0 && dlgEdt_addjob.length() != 0 && dlgEdt_addphone.length() != 0) {
                            AdduserName = dlgEdt_addname.getText().toString();
                            AdduserJob = dlgEdt_addjob.getText().toString();
                            AdduserMobile = dlgEdt_addphone.getText().toString();
                        }
                        //사용자가 추가로 넣은 직원의 정보 DB에 저장.
                        sqlDB = myHelper.getWritableDatabase();
                        sqlDB.execSQL("INSERT INTO userinfoTBL VALUES ( '"+ AdduserName +"', '"+ AdduserJob +"', '"+ AdduserMobile +"');");
                        sqlDB.close();

                        adapter.addItem(new Client(dlgEdt_addname.getText().toString(), dlgEdt_addjob.getText().toString(), dlgEdt_addphone.getText().toString()));
                        adapter.notifyDataSetChanged();
                    }
                });
                //dialog안에서 취소 버튼 눌렀을 경우.
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
    //나가기 버튼 기능.
    private void Back() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientActivity.super.onBackPressed();
            }
        });
    }
    //아이디 설정.
    private void FindID() {
        btn_add = (Button) findViewById(R.id.btn_clt_add);
        btn_back = (Button) findViewById(R.id.btn_clt_back);
        btnTest = (Button) findViewById(R.id.btntestCnt);
    }
/*==========================================================================================DB======================================================================================*/

    //회원정보DB
    public static class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "userinfoDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE userinfoTBL (userName CHAR(20), userJob CHAR(20), userMobile CHAR(30) PRIMARY KEY);");
            db.execSQL("INSERT INTO userinfoTBL VALUES ( '김보근', '사장', '010-9785-5695');");
            db.execSQL("INSERT INTO userinfoTBL VALUES ( '서대리', '과장', '010-3432-5456');");
            db.execSQL("INSERT INTO userinfoTBL VALUES ( '김사원', '사원', '010-5645-8764');");
            db.execSQL("INSERT INTO userinfoTBL VALUES ( '최사원', '사원', '010-1234-4321');");
            db.execSQL("INSERT INTO userinfoTBL VALUES ( '임이삭', '인턴', '010-0186-5483');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS userinfoTBL");
            onCreate(db);
        }
    }
/*==========================================================================================DB======================================================================================*/

    //직원정보 클래스화
    class member{
        String userName;
        String userJob;
        String userMobile;
        public member(String userName, String userJob, String userMobile){
            this.userName = userName;
            this.userJob = userJob;
            this.userMobile = userMobile;
        }
    }
}