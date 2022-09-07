package com.example.shopquanao.ultil;

public class Server {
    //public static String localhost="192.168.1.30";//wifi nhà
    //public static String localhost="192.168.1.169";//wifi ngoài
    //public static String localhost="192.168.1.19";//wifi ngoài

    public static String localhost="172.20.10.3";//wifi đt
    public static String DuongdanLoaisp= "http://" + localhost + ":84/server/getloaisanpham.php";
    public static String Duongdansanphammoinhat= "http://" + localhost + ":84/server/getsanphammoinhat.php";
    public static String Duongdanquanao= "http://" + localhost + ":84/server/getsanpham.php?page=";
    public static String Duongdanphukien= "http://" + localhost + ":84/server/getphukien.php?page=";
    public static String Duongdandonhang= "http://" + localhost + ":84/server/thongtinkhachhang.php";
    public static String Duongdanchitietdonhang= "http://" + localhost + ":84/server/chitietdonhang.php";

}
