package com.example.shopquanao.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopquanao.R;
import com.example.shopquanao.ultil.CheckConnection;
import com.example.shopquanao.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKH extends AppCompatActivity {
    EditText edttenkhachhang,edtsdt,edtemail;
    Button  btnxacnhan,btntrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_kh);
        Anhxa();
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (CheckConnection.haveNetwordConnection(getApplicationContext()))
        {
            EventButton();
        }
        else
        {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void EventButton() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten=edttenkhachhang.getText().toString().trim();//.trim : bỏ đi khoảng trắng
                String sdt=edtsdt.getText().toString().trim();
                String email=edtemail.getText().toString().trim();
                if(ten.length()>0 &&sdt.length()>0 && email.length()>0)
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String Madonhang) {
                            Log.d("Madonhang",Madonhang);
                            if(Integer.parseInt(Madonhang)>0)
                            {
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request= new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã thêm dữ liệu giỏ hàng thành công");
                                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");

                                        }
                                        else
                                        {
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Dữ liệu giỏ hàng của bạn bị lỗi");

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
                                        JSONArray jsonArray=new JSONArray();
                                        for(int i=0;i<MainActivity.manggiohang.size();i++)
                                        {
                                            JSONObject jsonObject=new JSONObject();
                                            try {
                                                jsonObject.put("Madonhang",Madonhang);
                                                jsonObject.put("MaSP",MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("TenSP",MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("GiaSP",MainActivity.manggiohang.get(i).getGiasp());
                                                jsonObject.put("SoluongSP",MainActivity.manggiohang.get(i).getSoluongsp());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap=new HashMap<String,String>();//truyền dl vào là string và đẩy lên cx là string
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
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
                            HashMap<String,String> hashMap=new HashMap<String,String>();//truyền dl vào là string và đẩy lên cx là string
                            hashMap.put("TenKH",ten);
                            hashMap.put("Sdt",sdt);
                            hashMap.put("Email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                }else
                {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại dữ liệu");
                }
            }
        });
    }

    private void Anhxa() {
        edttenkhachhang=findViewById(R.id.edittexttenkhachhang);
        edtsdt=findViewById(R.id.edittextsodienthoai);
        edtemail=findViewById(R.id.edittextemail);
        btnxacnhan=findViewById(R.id.buttonxacnhan);
        btntrove=findViewById(R.id.buttontrove);

    }
}