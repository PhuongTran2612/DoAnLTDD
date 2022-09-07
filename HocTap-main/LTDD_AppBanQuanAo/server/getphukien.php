<?php
	include "connect.php";
	$page = $_GET['page'];
	//$idsp=$_POST['IdSP'];
	$idsp=2;
	$space=5;
	$limit=($page - 1) * $space;

	$mangsanpham = array();
	$query = "SELECT * FROM sanpham WHERE IdSP=$idsp LIMIT $limit,$space";
	$data = mysqli_query($conn,$query);
	
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangsanpham,new Sanpham(
			$row['Id'],
			$row['TenSP'],
			$row['GiaSP'],
			$row['HinhAnhSP'],
			$row['MoTa'],
			$row['IdSP']));
	}
	echo json_encode($mangsanpham);
	
	class Sanpham{
		function Sanpham($Id,$TenSP,$GiaSP,$HinhAnhSP,$MoTa,$IdSP){
			$this->Id = $Id;
			$this->TenSP = $TenSP;
			$this->GiaSP = $GiaSP;
			$this->HinhAnhSP = $HinhAnhSP;
			$this->MoTa = $MoTa;
			$this->IdSP = $IdSP;
		}
	}
?>