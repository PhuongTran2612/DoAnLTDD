package com.example.shopquanao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopquanao.R;
import com.example.shopquanao.model.LoaiSP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSPAdapter extends BaseAdapter {
    ArrayList<LoaiSP> arrayListloaisp;
    Context context;

    public LoaiSPAdapter(ArrayList<LoaiSP> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();//đưa ra tất cả giá trị trong mảng
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public  class ViewHolder
    {
        TextView txttenloaisp;
        ImageView imgloaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txttenloaisp=view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp=view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag ();
        }
        LoaiSP loaisp= (LoaiSP) getItem(i);
        viewHolder.txttenloaisp.setText(loaisp.getTenLoaiSP());
        Picasso.get().load(loaisp.getHinhAnhLSP())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgloaisp);
        return view;
    }
}
