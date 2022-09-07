<?php
	include "connect.php";
	$query = "SELECT * FROM sanpham ORDER BY Id DESC LIMIT 8";
	$data = mysqli_query($conn,$query);
	$mangsanphammoinhat = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangsanphammoinhat,new Sanphammoinhat(
			$row['Id'],
			$row['TenSP'],
			$row['GiaSP'],
			$row['HinhAnhSP'],
			$row['MoTa'],
			$row['IdSP']));
	}
	echo json_encode($mangsanphammoinhat);
	
	class Sanphammoinhat{
		function Sanphammoinhat($Id,$TenSP,$GiaSP,$HinhAnhSP,$MoTa,$IdSP){
			$this->Id = $Id;
			$this->TenSP = $TenSP;
			$this->GiaSP = $GiaSP;
			$this->HinhAnhSP = $HinhAnhSP;
			$this->MoTa = $MoTa;
			$this->IdSP = $IdSP;
		}
	}
?>
