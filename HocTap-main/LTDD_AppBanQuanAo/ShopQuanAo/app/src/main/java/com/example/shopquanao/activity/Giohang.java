package com.example.shopquanao.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.shopquanao.R;
import com.example.shopquanao.adapter.GiohangAdapter;
import com.example.shopquanao.adapter.QuanaoAdapter;
import com.example.shopquanao.ultil.CheckConnection;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Giohang extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan,btntieptucmua;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();
        CheckData();
        EvenUltil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size()>0)
                {
                    Intent intent=new Intent(getApplicationContext(),ThongTinKH.class);
                    startActivity(intent);
                }
                else
                {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Giohang.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này hông ??");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MainActivity.manggiohang.size()<=0)
                        {
                            txtthongbao.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();//cập nhật lại giỏ hàng
                            EvenUltil();
                            if(MainActivity.manggiohang.size()<=0)
                            {
                                txtthongbao.setVisibility(View.VISIBLE);
                            }
                            else{
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();//cập nhật lại giỏ hàng
                                EvenUltil();
                            }
                        }

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();//cập nhật lại giỏ hàng
                        EvenUltil();

                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EvenUltil() {
        long tongtien=0;
        for(int i=0;i<MainActivity.manggiohang.size();i++)
        {
            tongtien+=MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");//3 con số chuyển về 1 dấu phẩy
        txttongtien.setText(decimalFormat.format(tongtien) +"Đ");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size() <=0)
        {
            giohangAdapter.notifyDataSetChanged();//cập nhật adapter
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }
        else
        {
            giohangAdapter.notifyDataSetChanged();//cập nhật adapter
            txtthongbao.setVisibility(View.INVISIBLE);//ẩn
            lvgiohang.setVisibility(View.VISIBLE);//hiện
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void Anhxa() {
        lvgiohang = findViewById(R.id.listviewgiohang);
        txtthongbao=findViewById(R.id.textviewthongbao);
        txttongtien=findViewById(R.id.textviewtongtien);
        btnthanhtoan=findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmua=findViewById(R.id.buttontieptucmuahang);

        toolbargiohang = findViewById(R.id.toolbargiohang);
        giohangAdapter =new GiohangAdapter(Giohang.this,MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }
}