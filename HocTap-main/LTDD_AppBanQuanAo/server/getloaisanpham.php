<?php
	include "connect.php";
	$query = "SELECT * FROM loaisanpham";
	$data = mysqli_query($conn,$query);
	$mangloaisanpham = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangloaisanpham,new Loaisp(
			$row['Id'],
			$row['TenLoaiSP'],
			$row['HinhAnhLSP']));
	}
	echo json_encode($mangloaisanpham);
	class Loaisp{
		function Loaisp($Id,$TenLoaiSP,$HinhAnhLSP){
			$this->Id = $Id;
			$this->TenLoaiSP =  $TenLoaiSP;
			$this->HinhAnhLSP =  $HinhAnhLSP;
		}
	}
?>