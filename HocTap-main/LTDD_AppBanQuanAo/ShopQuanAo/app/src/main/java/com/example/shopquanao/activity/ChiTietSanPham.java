package com.example.shopquanao.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.shopquanao.R;
import com.example.shopquanao.model.Giohang;
import com.example.shopquanao.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btndatmua;
    int id=0;
    String tenChiTiet="";
    int giaChiTiet=0;
    String hinhAnhChiTiet="";
    String moTaChiTiet="";
    int Idsanpham=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEvenSpinner();
        EventButton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menugiohang:
                Intent intent=new Intent(getApplicationContext(), com.example.shopquanao.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size()>0)
                {
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists=false;
                    for(int i=0;i<MainActivity.manggiohang.size();i++)
                    {
                        if(MainActivity.manggiohang.get(i).getIdsp()==id)
                        {
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp()+sl);
                            if(MainActivity.manggiohang.get(i).getSoluongsp()>=10)
                            {
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(giaChiTiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exists =true;
                        }
                    }
                    if(exists==false)
                    {
                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi= soluong* giaChiTiet;
                        MainActivity.manggiohang.add(new Giohang(id,tenChiTiet,Giamoi,hinhAnhChiTiet,soluong));
                    }

                }
                else
                {
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi= soluong* giaChiTiet;
                    MainActivity.manggiohang.add(new Giohang(id,tenChiTiet,Giamoi,hinhAnhChiTiet,soluong));
                }
                Intent intent =new Intent(ChiTietSanPham.this, com.example.shopquanao.activity.Giohang.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEvenSpinner() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayadapter);
    }

    private void GetInformation() {

        Sanpham sanpham= (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id=sanpham.getID();
        tenChiTiet=sanpham.getTensanpham();
        giaChiTiet=sanpham.getGiasanpham();
        hinhAnhChiTiet=sanpham.getHinhanhsanpham();
        moTaChiTiet=sanpham.getMotasanpham();
        Idsanpham=sanpham.getIDsanpham();

        txtten.setText(tenChiTiet);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");//3 con số chuyển về 1 dấu phẩy
        txtgia.setText("Giá:"+decimalFormat.format(giaChiTiet) +"Đ");
        txtmota.setText(moTaChiTiet);
        Picasso.get().load(hinhAnhChiTiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgChitiet);

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);//xét toolbar như 1 action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//kết thúc màn hình

            }
        });

    }

    private void Anhxa() {
        toolbarChitiet=findViewById(R.id.toolbarchitietsanpham);
        imgChitiet=findViewById(R.id.imageviewchitietsanpham);
        txtten=findViewById(R.id.textviewtenchitietsanpham);
        txtgia=findViewById(R.id.textviewgiachitietsanpham);
        txtmota=findViewById(R.id.textviewmotachitietsanpham);
        spinner=findViewById(R.id.spinner);
        btndatmua=findViewById(R.id.buttondatmua);

    }
}