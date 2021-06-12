package com.kbk.company_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ItemsActivity extends AppCompatActivity {

    Button btn_modify;
    Button btn_exit;

    Spinner spinner;

    LinearLayout container;

    View dialogView2;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        getSupportFragmentManager().beginTransaction().isEmpty();

        FindButton();
        ItemSelect();
        Back();

        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modifing(v);
            }
        });



    }



    //버튼 ID 설정
    private void FindButton() {
        btn_modify = (Button) findViewById(R.id.btn_modify);
        btn_exit = (Button) findViewById(R.id.btn_exit);
    }

    //spinner 아이템 선택 시 이벤트 발생 설정.
    private void ItemSelect() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear_items, fragment1).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear_items, fragment2).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear_items, fragment3).commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear_items, fragment4).commit();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Modifing(View v) {
            dialogView2 = (View) View.inflate(ItemsActivity.this, R.layout.modfing_dialog, null);
            AlertDialog.Builder ModifyDlg = new AlertDialog.Builder(ItemsActivity.this);
            ModifyDlg.setTitle("                 상품 수정");
            ModifyDlg.setIcon(R.drawable.itemicon);
            ModifyDlg.setView(dialogView2);
            ModifyDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "편집 기능 준비중...", Toast.LENGTH_SHORT).show();
                }
            });
            ModifyDlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            ModifyDlg.show();
            }


    //뒤로가기 기능
    private void Back() {
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ItemsActivity.super.onBackPressed(); }
        });
    }
}