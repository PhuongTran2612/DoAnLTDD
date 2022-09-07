package com.example.shopquanao.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopquanao.R;
import com.example.shopquanao.adapter.LoaiSPAdapter;
import com.example.shopquanao.adapter.SanphamAdapter;
import com.example.shopquanao.model.Giohang;
import com.example.shopquanao.model.LoaiSP;
import com.example.shopquanao.model.Sanpham;
import com.example.shopquanao.ultil.CheckConnection;
import com.example.shopquanao.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<LoaiSP> mangloaisp;
    LoaiSPAdapter loaiSPAdapter;
    int Id = 0;
    String TenLoaiSP = "";
    String HinhAnhLSP = "";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;

    public static ArrayList<Giohang>manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        if (CheckConnection.haveNetwordConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaisp();
            GetDuLieuSPMoiNhat();
            CatchOnItemListView();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }
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

    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        if(CheckConnection.haveNetwordConnection(getApplicationContext()))
                        {
                            Intent intent=new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetwordConnection(getApplicationContext()))
                        {
                            Intent intent=new Intent(MainActivity.this,QuanAoActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetwordConnection(getApplicationContext()))
                        {
                            Intent intent=new Intent(MainActivity.this,PhuKienActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetwordConnection(getApplicationContext()))
                        {
                            Intent intent=new Intent(MainActivity.this,LienHeActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetwordConnection(getApplicationContext()))
                        {
                            Intent intent=new Intent(MainActivity.this,ThongTinActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
            }
        });
    }

    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int Id = 0;
                    String TenSP = "";
                    Integer GiaSP = 0;
                    String HinhAnhSP = "";
                    String MoTa = "";
                    int IdSP = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Id = jsonObject.getInt("Id");
                            TenSP = jsonObject.getString("TenSP");
                            GiaSP = jsonObject.getInt("GiaSP");
                            HinhAnhSP = jsonObject.getString("HinhAnhSP");
                            MoTa = jsonObject.getString("MoTa");
                            IdSP = jsonObject.getInt("IdSP");
                            mangsanpham.add(new Sanpham(Id, TenSP, GiaSP, HinhAnhSP, MoTa, IdSP));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanLoaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Id = jsonObject.getInt("Id");
                            TenLoaiSP = jsonObject.getString("TenLoaiSP");
                            HinhAnhLSP = jsonObject.getString("HinhAnhLSP");
                            mangloaisp.add(new LoaiSP(Id, TenLoaiSP, HinhAnhLSP));
                            loaiSPAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(3, new LoaiSP(0, "Liên hệ", "https://cdn1.iconfinder.com/data/icons/mix-color-3/502/Untitled-12-512.png"));
                    mangloaisp.add(4, new LoaiSP(0, "Thông tin", "https://cdn2.iconfinder.com/data/icons/perfect-flat-icons-2/512/User_info_man_male_profile_information.png"));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://oldniceshop.com/wp-content/uploads/2020/07/KSopr5-1536x466.jpg");
        mangquangcao.add("https://intphcm.com/data/upload/banner-thoi-trang%20-nhan-manh.jpg");
        mangquangcao.add("https://thegioidohoa.com/wp-content/uploads/2015/10/thiet-ke-banner-an-tuong-cho-web-thoi-trang.jpeg");
        mangquangcao.add("https://img3.thuthuatphanmem.vn/uploads/2019/10/14/banner-thoi-trang-dep-nhat-hien-nay_113856288.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//imgview canh vừa với viewlipper mà k bi cắt mất ảnh
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);//chạy trong vòng 5s
        viewFlipper.setAutoStart(true);//cho view lipper tự chạy
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);//chạy hiệu ứng đẹp hơn..khi ảnh xuất hiện
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);//khi ảnh mất đi
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh = findViewById(R.id.recyclerview);
        navigationView = findViewById(R.id.navigationview);
        listViewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0, new LoaiSP(0, "Trang Chính", "http://icons.iconarchive.com/icons/fps.hu/free-christmas-flat-circle/512/home-icon.png"));
        loaiSPAdapter = new LoaiSPAdapter(mangloaisp, getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaiSPAdapter);
        mangsanpham = new ArrayList<>();

        sanphamAdapter = new SanphamAdapter(getApplicationContext(), mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));//chia thành 2 cột
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);

        if(manggiohang !=null)
        {

        }
        else
        {
            manggiohang=new ArrayList<>();
        }
    }
}