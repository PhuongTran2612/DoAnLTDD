package com.example.shopquanao.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopquanao.R;
import com.example.shopquanao.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PhukienAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayphukien;

    public PhukienAdapter(Context context, ArrayList<Sanpham> arrayphukien) {
        this.context = context;
        this.arrayphukien = arrayphukien;
    }

    @Override
    public int getCount() {
        return arrayphukien.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayphukien.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder
    {
        public TextView txttenphukien,txtgiaphukien,txtmotaphukien;
        public ImageView imgphukien;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//khởi tạo gắn layout vào
            view =inflater.inflate(R.layout.dong_phukien,null);
            viewHolder.txttenphukien=view.findViewById(R.id.textviewtenphukien);
            viewHolder.txtgiaphukien=view.findViewById(R.id.textviewgiaphukien);
            viewHolder.txtmotaphukien=view.findViewById(R.id.textviewmotaphukien);
            viewHolder.txtgiaphukien=view.findViewById(R.id.textviewgiaphukien);
            viewHolder.imgphukien=view.findViewById(R.id.imageviewphukien);

            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag ();
        }
        Sanpham sanpham= (Sanpham) getItem(i);
        viewHolder.txttenphukien.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");//3 con số chuyển về 1 dấu phẩy
        viewHolder.txtgiaphukien.setText("Giá:"+decimalFormat.format(sanpham.getGiasanpham()) +"Đ");
        viewHolder.txtmotaphukien.setMaxLines(2);//set mô tả quần áo thành 2 dòng
        viewHolder.txtmotaphukien.setEllipsize(TextUtils.TruncateAt.END);//nếu mô tả quá dài thì hiện dấu 3 chấm
        viewHolder.txtmotaphukien.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgphukien);
        return view;
    }
}
