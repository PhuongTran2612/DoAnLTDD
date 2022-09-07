package com.example.shopquanao.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopquanao.R;
import com.example.shopquanao.adapter.QuanaoAdapter;
import com.example.shopquanao.model.Sanpham;
import com.example.shopquanao.ultil.CheckConnection;
import com.example.shopquanao.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuanAoActivity extends AppCompatActivity {
    Toolbar toolbarqa;
    ListView lvqa;
    QuanaoAdapter quanaoAdapter;
    ArrayList<Sanpham> mangqa;
    int idqa=0;
    int page=1;
    View footerview;
    boolean isLoading =false;
    boolean limitdata=false;
    mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ao);
        Anhxa();
        if(CheckConnection.haveNetwordConnection(getApplicationContext()))
        {
            GetIdloaisp();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }
        else
        {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Không có kết nối...Bạn hãy ktra lại internet");
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

    private void LoadMoreData() {
        lvqa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangqa.get(i));
                startActivity(intent);

            }
        });
        lvqa.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem+VisibleItem==TotalItem && TotalItem !=0 &&isLoading==false &&limitdata==false)
                {
                    isLoading=true;
                    ThreadData threadData=new ThreadData();
                    threadData.start();

                }

            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.Duongdanquanao + String.valueOf(Page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String Tenquanao="";
                int Giaqa = 0;
                String Hinhanhqa = "";
                String Mota = "";
                int Idspqa = 0;
                if(response!=null && response.length()!=2)
                {
                    lvqa.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("Id");
                            Tenquanao = jsonObject.getString("TenSP");
                            Giaqa = jsonObject.getInt("GiaSP");
                            Hinhanhqa = jsonObject.getString("HinhAnhSP");
                            Mota = jsonObject.getString("MoTa");
                            Idspqa = jsonObject.getInt("IdSP");
                            mangqa.add(new Sanpham(id,Tenquanao,Giaqa,Hinhanhqa,Mota,Idspqa));
                            quanaoAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitdata = true;
                    lvqa.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Đã hết sản phẩm quần áo rùi");

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String,String>();//truyền dl vào là string và đẩy lên cx là string
                param.put("IdSP",String.valueOf(idqa));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void ActionToolbar() {
        setSupportActionBar(toolbarqa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarqa.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIdloaisp() {
        idqa=getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("giatriloaisanpham",idqa+"");
    }

    private void Anhxa() {
        toolbarqa = findViewById(R.id.toolbarquanao);
        lvqa = findViewById(R.id.listviewquanao);
        mangqa=new ArrayList<>();
        quanaoAdapter =new QuanaoAdapter(getApplicationContext(),mangqa);
        lvqa.setAdapter(quanaoAdapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview= inflater.inflate(R.layout.progressbar,null);
        mHandler=new mHandler();
    }
    public class mHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what)
            {
                case 0:
                    lvqa.addFooterView(footerview);break;
                case 1:
                    GetData(++page);
                    isLoading=false;
                    break;

            }
            super.handleMessage(msg);
        }


    }
    public class ThreadData extends Thread
    {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=mHandler.obtainMessage(1);//phương thức liên kết thread với handler
            mHandler.sendMessage(message);
            super.run();
        }
    }
}