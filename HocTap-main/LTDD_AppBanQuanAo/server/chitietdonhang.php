<?php
 include "connect.php";
 $json=$_POST['json'];
 $data=json_decode($json,true);
 foreach ($data as $value) 
 {
    $Madonhang=$value['Madonhang'];
    $MaSP=$value['MaSP'];
    $TenSP=$value['TenSP'];
    $GiaSP=$value['GiaSP'];
    $SoluongSP=$value['SoluongSP'];
    $query="INSERT INTO chitietdonhang(Id,Madonhang,MaSP,TenSP,GiaSP,SoluongSP) VALUES (null,'$Madonhang','$MaSP','$TenSP','$GiaSP','$SoluongSP')";
    $Dta=mysqli_query($conn,$query);
 }
    if($Dta)
    {
      echo "1";
    }
    else
    {
       echo "0";
    }
 
?>