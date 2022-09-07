package com.example.shopquanao.model;

public class LoaiSP {
    public  int Id;
    public  String TenLoaiSP;
    public  String HinhAnhLSP;

    public LoaiSP(int id, String tenLoaiSP, String hinhAnhLSP) {
        Id = id;
        TenLoaiSP = tenLoaiSP;
        HinhAnhLSP = hinhAnhLSP;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenLoaiSP() {
        return TenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        TenLoaiSP = tenLoaiSP;
    }

    public String getHinhAnhLSP() {
        return HinhAnhLSP;
    }

    public void setHinhAnhLSP(String hinhAnhLSP) {
        HinhAnhLSP = hinhAnhLSP;
    }



}
