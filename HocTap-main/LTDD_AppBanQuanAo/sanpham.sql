-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2022 at 01:43 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanao`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `Id` int(11) NOT NULL,
  `Madonhang` int(11) NOT NULL,
  `MaSP` int(11) NOT NULL,
  `TenSP` varchar(10000) NOT NULL,
  `GiaSP` int(11) NOT NULL,
  `SoluongSP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`Id`, `Madonhang`, `MaSP`, `TenSP`, `GiaSP`, `SoluongSP`) VALUES
(1, 1, 1, 'Lace Floral Bow Knot Decor Hair Clip', 180000, 2),
(2, 2, 2, 'Pearl&Bow Earrings', 120000, 2),
(3, 2, 25, 'Side Tie Denim Skirt', 550000, 1),
(4, 2, 23, 'Solid Tassel Cami Top', 370000, 1),
(5, 2, 1, 'Lace Floral Bow Knot Decor Hair Clip', 180000, 1),
(6, 3, 17, 'Floral Bell Sleeve Corset Mini Dress', 1600000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `Id` int(11) NOT NULL,
  `TenKH` varchar(200) NOT NULL,
  `Sdt` int(11) NOT NULL,
  `Email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`Id`, `TenKH`, `Sdt`, `Email`) VALUES
(1, 'Lê Thùy Na', 362417182, 'nalethuy@gmail.com'),
(2, 'Lê Trung Đô', 963273900, 'dole@gmail.com'),
(3, 'quyền', 1234456789, 'nalethuy@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `Id` int(11) NOT NULL,
  `TenLoaiSP` varchar(200) NOT NULL,
  `HinhAnhLSP` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`Id`, `TenLoaiSP`, `HinhAnhLSP`) VALUES
(1, 'Quần áo', 'https://www.freeiconspng.com/uploads/gothic-black-dress-png-12.png\r\n'),
(2, 'Phụ kiện', 'https://www.freeiconspng.com/uploads/jewelry-necklace-clipart-photo-24.png');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `Id` int(11) NOT NULL,
  `TenSP` varchar(200) NOT NULL,
  `GiaSP` int(15) NOT NULL,
  `HinhAnhSP` varchar(200) NOT NULL,
  `MoTa` varchar(10000) NOT NULL,
  `IdSP` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`Id`, `TenSP`, `GiaSP`, `HinhAnhSP`, `MoTa`, `IdSP`) VALUES
(1, 'Lace Floral Bow Knot Decor Hair Clip', 180000, 'https://img1.shopcider.com/product/1640763386000-dbJYDK.jpg', 'Kẹp tóc hình nơ', 2),
(2, 'Pearl&Bow Earrings', 120000, 'https://img1.shopcider.com/product/1635852015000-Q68A5A.jpg', 'Bông tai nơ ngọc', 2),
(3, 'Pink Love Earrings', 120000, 'https://img1.shopcider.com/product/1644461074000-xdhGwR.jpg', 'Bông tai trái tim màu hồng', 2),
(4, 'Pearl & Flower Decor Charm Hair Claw', 120000, 'https://img1.shopcider.com/product/1650268295000-4Je4bZ.jpg', 'Kẹp tóc tua rua', 2),
(5, 'Heart Knit Hat', 200000, 'https://img1.shopcider.com/product/1636169728000-jdTJWC.jpg', 'Mũ len màu đen', 2),
(6, 'Denim Bucket Hat', 300000, 'https://img1.shopcider.com/product/1631073982000-68Eekc.jpg', 'Mũ len màu xanh đậm', 2),
(7, 'Beaded Rhinestone Hair Hoop', 120000, 'https://img1.shopcider.com/product/1640747336000-4S2BJB.jpg', 'Cài tóc đính ngọc', 2),
(8, 'Heart & Pearl Decor Bracelet', 100000, 'https://img1.shopcider.com/product/1651114913000-byZZPd.jpg', 'Vòng tay màu đen', 2),
(9, 'Faux Diamond Pearl Layered Necklace', 230000, 'https://img1.shopcider.com/product/1640747211000-NTBtiW.jpg', 'Dây chuyền ngọc', 2),
(10, 'Crystal Petal Drop Earrings', 230000, 'https://img1.shopcider.com/product/1649387175000-waXZK5.jpg', 'Bông tai tua rua màu xanh', 2),
(11, 'Heart Print Headband', 90000, 'https://img1.shopcider.com/product/1649732094000-br4X4X.jpg', 'Cài tóc trái tim màu hồng', 2),
(12, 'Bow Long Stockings', 100000, 'https://img1.shopcider.com/product/1635823246000-2jhGpy.jpg', 'Tất da cổ cao màu trắng', 2),
(13, 'Floral Lace Up Corset Mini Dress', 820000, 'https://img1.shopcider.com/product/1650879712000-Cxd3xN.jpg', 'Đầm cổ vuông màu đen kèm dai lưng', 1),
(14, 'Secret Garden Corset Mini Dress', 700000, 'https://img1.shopcider.com/product/1650353170000-fjH4kk.jpg', 'Đầm hai dây màu kem', 1),
(15, 'Harvest Lemon Cut Out Knotted Mini Dress', 800000, 'https://img1.shopcider.com/product/1640768228000-HEGaM3.jpg', 'Đầm chanh màu vàng khoét ngực', 1),
(16, 'Floral Ruffle Toile de Jouy Midi Dress', 600000, 'https://img1.shopcider.com/product/1649568345000-eGQayA.jpg', 'đầm trắng xẻ tà', 1),
(17, 'Floral Bell Sleeve Corset Mini Dress', 800000, 'https://img1.shopcider.com/product/1646386022000-ahaXc4.jpg', 'Đầm xanh trễ vai tay dài', 1),
(18, 'Beetlejuice Lydia\'s Iconic Ruffled Red Dress', 900000, 'https://img1.shopcider.com/product/1631080308000-xfrQ6e.jpg', 'Đầm xòe màu đỏ hai dây', 1),
(19, 'Solid Midi Dress', 650000, 'https://img1.shopcider.com/product/1625144636000-C8rH7i.jpg', 'Đầm body dài màu đen', 1),
(20, 'Patchwork Chain Rib Tank Top', 300000, 'https://img1.shopcider.com/product/1650540675000-wHSxSJ.jpg', 'áo crop top màu xanh', 1),
(21, 'Check Pattern Tie Front Crop Top', 450000, 'https://img1.shopcider.com/product/1647852961000-HEBimc.jpg', 'áo trắng có nơ trước ngực', 1),
(22, 'Solid Off Shoulder Crop Top', 410000, 'https://img1.shopcider.com/product/1637568502000-BBbwKt.jpg', 'Áo trễ vai tay dài màu xanh', 1),
(23, 'Solid Tassel Cami Top', 370000, 'https://img1.shopcider.com/product/1642147212000-TBFG7P.jpg', 'Áo tua rua hai dây màu nâu', 1),
(24, 'Boho Paisley Bowknot Tank Top', 270000, 'https://img1.shopcider.com/product/1644808683000-NYfAFM.jpg', 'Áo yếm hai dây họa tiết', 1),
(25, 'Side Tie Denim Skirt', 550000, 'https://img1.shopcider.com/product/1642746676000-NzGJBn.jpg', 'Váy jeans cột dây trắng', 1),
(26, 'Coffee Brown Asymmetrical Faux Leather Skirt', 500000, 'https://img1.shopcider.com/product/1631104598000-Wjsxzk.jpg', 'Váy da lệch tà màu nâu', 1),
(27, 'Gingham Ruffle Layered Mini Skirt', 600000, 'https://img1.shopcider.com/product/1638260685000-iNajST.jpg', 'Váy caro đen trắng xòe', 1),
(28, 'Brown Pleated Skirt With Chain', 600000, 'https://img1.shopcider.com/product/1635492356000-iGRpA6.jpg', 'Váy đen kèm dây đeo', 1),
(29, 'Denim Ripped Flares', 700000, 'https://img1.shopcider.com/product/1631762176000-QSMYJF.jpg', 'Quần jeans rách gối ống loe.Vải chính được làm từ cotton hữu cơ.\r\n• Quần jean denim\r\n• Cotton thân thiện với môi trường\r\n• Quần bò đen\r\n• Thân thiện với môi trường\r\nCotton căng thân thiện với môi trường. Hai túi trước với túi tiền xu và hai túi sau. Họa tiết horsebit kim loại trên dây thắt lưng. Khuy và zip ở phía trước.\r\nVải chính được làm từ cotton hữu cơ và denim được rửa bằng kỹ thuật thân thiện với môi trường hơn.', 1),
(30, 'Diamond Flare Leg Jeans', 800000, 'https://img1.shopcider.com/product/1647864859000-EHBB4C.jpg', 'Quần jeans ống loe ôm dáng.Vải chính được làm từ cotton hữu cơ.\r\n• Quần jean denim\r\n• Cotton thân thiện với môi trường\r\n• Quần bò đen\r\n• Thân thiện với môi trường\r\nCotton căng thân thiện với môi trường. Hai túi trước với túi tiền xu và hai túi sau. Họa tiết horsebit kim loại trên dây thắt lưng. Khuy và zip ở phía trước.\r\nVải chính được làm từ cotton hữu cơ và denim được rửa bằng kỹ thuật thân thiện với môi trường hơn.', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
