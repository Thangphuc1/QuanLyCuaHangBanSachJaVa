-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 20, 2026 lúc 09:59 AM
-- Phiên bản máy phục vụ: 8.0.44
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlibansach`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `mahoadon` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `masach` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `soluong` int NOT NULL DEFAULT '0',
  `dongia` int NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`mahoadon`, `masach`, `soluong`, `dongia`) VALUES
('HD001', 'S001', 1, 130000),
('HD002', 'S002', 1, 170000),
('HD003', 'S003', 1, 150000),
('HD004', 'S004', 1, 1000),
('HD005', 'S005', 1, 1000),
('HD006', 'S006', 1, 150000),
('HD007', 'S007', 1, 210000),
('HD008', 'S008', 1, 150000),
('HD009', 'S009', 1, 1000),
('HD010', 'S010', 1, 140000),
('HD011', 'S011', 1, 190000),
('HD012', 'S012', 1, 150000),
('HD013', 'S013', 1, 250000),
('HD014', 'S014', 3, 160000),
('HD015', 'S015', 1, 150000),
('HD016', 'S016', 1, 1000),
('HD017', 'S017', 1, 1000),
('HD018', 'S018', 1, 1000),
('HD019', 'S019', 1, 150000),
('HD020', 'S020', 1, 200000),
('HD021', 'S021', 1, 150000),
('HD022', 'S022', 1, 200000),
('HD023', 'S023', 1, 130000),
('HD024', 'S024', 1, 270000),
('HD025', 'S025', 1, 120000),
('HD026', 'S026', 1, 190000),
('HD027', 'S027', 1, 150000),
('HD028', 'S028', 1, 140000),
('HD029', 'S029', 1, 160000),
('HD030', 'S030', 1, 140000),
('HD031', 'S031', 1, 200000),
('HD032', 'S032', 1, 180000),
('HD033', 'S033', 1, 250000),
('HD034', 'S034', 1, 160000),
('HD035', 'S035', 1, 140000),
('HD036', 'S036', 1, 130000),
('HD037', 'S037', 1, 150000),
('HD038', 'S038', 1, 250000),
('HD039', 'S039', 3, 200000),
('HD040', 'S040', 1, 140000),
('HD041', 'S041', 9, 200000),
('HD042', 'S042', 1, 250000),
('HD043', 'S043', 1, 150000),
('HD044', 'S044', 12, 150000),
('HD045', 'S045', 12, 190000),
('HD046', 'S046', 12, 130000),
('HD047', 'S047', 1, 210000),
('HD048', 'S048', 1, 140000),
('HD049', 'S049', 5, 140000),
('HD050', 'S050', 5, 140000),
('HD051', 'S051', 5, 120000),
('HD052', 'S052', 11, 150000),
('HD053', 'S053', 45, 200000),
('HD054', 'S054', 12, 90000),
('HD055', 'S055', 1, 200000),
('HD056', 'S056', 1, 190000),
('HD057', 'S057', 1, 160000),
('HD058', 'S058', 1, 90000),
('HD059', 'S059', 1, 90000),
('HD060', 'S060', 1, 250000),
('HD061', 'S061', 1, 270000),
('HD062', 'S062', 12, 150000),
('HD063', 'S063', 12, 150000),
('HD064', 'S064', 1, 250000),
('HD065', 'S065', 1, 150000),
('HD066', 'S066', 1, 150000),
('HD067', 'S067', 1, 150000),
('HD068', 'S068', 1, 150000),
('HD069', 'S069', 1, 130000),
('HD070', 'S070', 1, 150000),
('HD071', 'S071', 1, 120000),
('HD072', 'S072', 1, 150000),
('HD073', 'S073', 1, 190000),
('HD074', 'S074', 1, 150000),
('HD075', 'S075', 1, 150000),
('HD076', 'S076', 1, 150000),
('HD077', 'S077', 1, 150000),
('HD078', 'S078', 1, 150000),
('HD079', 'S079', 1, 130000),
('HD080', 'S080', 3, 150000),
('HD081', 'S081', 2, 150000),
('HD082', 'S082', 2, 200000),
('HD083', 'S083', 10, 90000),
('HD084', 'S084', 1, 160000),
('HD085', 'S085', 1, 130000),
('HD086', 'S086', 1, 150000),
('HD087', 'S087', 1, 210000),
('HD088', 'S088', 1, 200000),
('HD089', 'S089', 1, 120000),
('HD090', 'S090', 10, 200000),
('HD091', 'S091', 1, 190000),
('HD092', 'S092', 1, 150000),
('HD093', 'S093', 1, 150000),
('HD094', 'S094', 1, 250000),
('HD095', 'S095', 1, 180000),
('HD096', 'S096', 1, 160000),
('HD097', 'S097', 1, 120000),
('HD098', 'S098', 1, 140000),
('HD099', 'S099', 1, 130000),
('HD100', 'S100', 1, 170000),
('HD101', 'S101', 1, 200000),
('HD102', 'S102', 1, 160000),
('HD103', 'S103', 1, 150000),
('HD104', 'S104', 2, 150000),
('HD105', 'S105', 1, 150000),
('HD106', 'S106', 1, 150000),
('HD107', 'S107', 1, 180000),
('HD108', 'S108', 1, 160000),
('HD109', 'S109', 1, 120000),
('HD110', 'S110', 1, 150000),
('HD111', 'S111', 1, 150000),
('HD112', 'S112', 1, 150000),
('HD113', 'S113', 4, 150000),
('HD114', 'S114', 12, 150000),
('HD115', 'S115', 3, 150000),
('HD116', 'S116', 12, 150000),
('HD117', 'S117', 1, 150000),
('HD118', 'S118', 1, 150000),
('HD119', 'S119', 1, 140000),
('HD120', 'S120', 12, 150000),
('HD121', 'S121', 1, 130000),
('HD122', 'S122', 1, 120000),
('HD123', 'S123', 1, 150000),
('HD124', 'S124', 1, 150000),
('HD125', 'S125', 1, 140000),
('HD126', 'S126', 1, 150000),
('HD127', 'S127', 1, 150000),
('HD128', 'S128', 1, 150000),
('HD129', 'S129', 1, 150000),
('HD130', 'S130', 1, 160000),
('HD131', 'S131', 1, 210000),
('HD132', 'S132', 1, 150000),
('HD133', 'S133', 4, 150000),
('HD134', 'S134', 1, 160000),
('HD135', 'S135', 1, 150000),
('HD136', 'S136', 1, 150000),
('HD137', 'S137', 1, 150000),
('HD138', 'S138', 12, 150000),
('HD139', 'S139', 1, 140000),
('HD140', 'S140', 1, 210000),
('HD141', 'S141', 1, 210000),
('HD142', 'S142', 1, 150000),
('HD143', 'S143', 1, 150000),
('HD144', 'S144', 1, 250000),
('HD145', 'S145', 1, 190000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietkhuyenmaihd`
--

CREATE TABLE `chitietkhuyenmaihd` (
  `makm` varchar(10) NOT NULL,
  `mahoadon` varchar(10) NOT NULL,
  `tongtienHD` varchar(20) NOT NULL,
  `phantramgiamgia` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietkhuyenmaisp`
--

CREATE TABLE `chitietkhuyenmaisp` (
  `makm` varchar(10) NOT NULL,
  `masach` varchar(10) NOT NULL,
  `phantramgiamgia` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `maphieunhap` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `masach` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `soluong` int NOT NULL DEFAULT '0',
  `dongia` double UNSIGNED NOT NULL DEFAULT '0',
  `thanhtien` double UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`maphieunhap`, `masach`, `soluong`, `dongia`, `thanhtien`) VALUES
('PN001', 'S001', 100, 210000, 21000000),
('PN002', 'S002', 50, 130000, 6500000),
('PN003', 'S003', 50, 170000, 8500000),
('PN004', 'S004', 100, 160000, 16000000),
('PN005', 'S005', 100, 130000, 13000000),
('PN006', 'S006', 100, 150000, 15000000),
('PN007', 'S007', 100, 190000, 19000000),
('PN008', 'S008', 100, 160000, 16000000),
('PN009', 'S009', 100, 180000, 18000000),
('PN010', 'S010', 100, 140000, 14000000),
('PN011', 'S011', 100, 150000, 15000000),
('PN012', 'S012', 100, 250000, 25000000),
('PN013', 'S013', 100, 140000, 14000000),
('PN014', 'S014', 100, 190000, 19000000),
('PN015', 'S015', 100, 160000, 16000000),
('PN016', 'S016', 1, 150000, 150000),
('PN017', 'S017', 2, 150000, 300000),
('PN018', 'S018', 12, 150000, 1800000),
('PN019', 'S019', 12, 140000, 1680000),
('PN020', 'S020', 1, 150000, 150000),
('PN021', 'S021', 1, 140000, 140000),
('PN022', 'S022', 12, 200000, 2400000),
('PN023', 'S023', 1, 1, 1),
('PN024', 'S024', 1, 1, 1),
('PN025', 'S025', 1, 210000, 210000),
('PN026', 'S026', 2, 150000, 300000),
('PN027', 'S027', 1, 1, 1),
('PN028', 'S028', 5, 150000, 750000),
('PN029', 'S029', 1, 130000, 1),
('PN030', 'S030', 1, 1, 1),
('PN031', 'S031', 1, 250000, 250000),
('PN032', 'S032', 1, 150000, 150000),
('PN033', 'S033', 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctkm`
--

CREATE TABLE `ctkm` (
  `makm` int NOT NULL,
  `masach` int NOT NULL,
  `tilekm` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahoadon` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `manv` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `makh` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `thoigiantao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tongtien` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`mahoadon`, `manv`, `makh`, `thoigiantao`, `tongtien`) VALUES
('HD001', 'NV003', 'KH002', '2025-03-18 00:12:51', 410000),
('HD002', 'NV004', 'KH006', '2025-03-18 00:14:15', 2310000),
('HD003', 'NV006', 'KH002', '2025-03-18 00:16:42', 460000),
('HD004', 'NV003', 'KH001', '2025-03-18 00:18:07', 250000),
('HD005', 'NV006', 'KH003', '2025-04-03 00:25:53', 760000),
('HD006', 'NV001', 'KH005', '2025-04-22 00:01:33', 150000),
('HD007', 'NV003', 'KH002', '2025-04-22 00:01:39', 1000),
('HD008', 'NV006', 'KH003', '2025-04-22 00:01:46', 1000),
('HD009', 'NV003', 'KH003', '2025-04-22 01:46:41', 841000),
('HD010', 'NV001', 'KH005', '2025-04-22 14:58:30', 824000),
('HD011', 'NV001', 'KH005', '2025-04-22 15:01:00', 692700),
('HD012', 'NV004', 'KH004', '2025-04-22 15:05:44', 150000),
('HD013', 'NV002', 'KH003', '2025-04-22 15:07:01', 520000),
('HD014', 'NV006', 'KH002', '2025-04-22 15:09:14', 150000),
('HD015', 'NV006', 'KH005', '2025-04-22 17:06:11', 330000),
('HD016', 'NV005', 'KH004', '2025-04-22 17:06:32', 390000),
('HD017', 'NV001', 'KH001', '2025-04-22 17:06:42', 190000),
('HD018', 'NV003', 'KH006', '2025-04-22 17:35:12', 740000),
('HD019', 'NV004', 'KH003', '2025-04-22 17:55:31', 806000),
('HD020', 'NV005', 'KH006', '2025-04-28 10:45:26', 1000000),
('HD021', 'NV006', 'KH005', '2025-05-01 13:24:12', 140000),
('HD022', 'NV004', 'KH001', '2025-05-01 16:32:24', 1440000),
('HD023', 'NV001', 'KH002', '2025-05-05 01:38:56', 250000),
('HD024', 'NV003', 'KH006', '2025-05-05 01:40:26', 150000),
('HD025', 'NV004', 'KH004', '2025-05-06 16:38:33', 4512000),
('HD026', 'NV001', 'KH001', '2025-05-06 16:38:46', 210000),
('HD027', 'NV004', 'KH005', '2025-05-06 16:38:54', 140000),
('HD028', 'NV004', 'KH002', '2025-05-06 16:39:03', 1600000),
('HD029', 'NV006', 'KH004', '2025-05-06 16:39:20', 1320000),
('HD030', 'NV006', 'KH005', '2025-05-06 16:39:40', 7200000),
('HD031', 'NV002', 'KH002', '2025-05-02 00:00:00', 864000),
('HD032', 'NV002', 'KH005', '2025-05-06 16:40:15', 1000000),
('HD033', 'NV004', 'KH001', '2025-05-06 22:34:06', 3080000),
('HD034', 'NV005', 'KH002', '2025-05-06 23:15:04', 150000),
('HD035', 'NV002', 'KH004', '2025-05-06 23:15:26', 150000),
('HD036', 'NV002', 'KH004', '2025-05-07 01:21:11', 150000),
('HD037', 'NV003', 'KH001', '2025-05-07 01:21:41', 150000),
('HD038', 'NV001', 'KH004', '2025-05-07 01:23:19', 130000),
('HD039', 'NV002', 'KH004', '2025-05-07 01:23:30', 150000),
('HD040', 'NV001', 'KH003', '2025-05-07 01:25:59', 120000),
('HD041', 'NV004', 'KH001', '2025-04-30 01:29:25', 150000),
('HD042', 'NV004', 'KH006', '2025-05-07 01:32:16', 190000),
('HD043', 'NV003', 'KH004', '2025-04-30 01:34:30', 150000),
('HD044', 'NV006', 'KH001', '2025-05-07 01:35:57', 150000),
('HD045', 'NV003', 'KH001', '2025-05-03 00:00:00', 150000),
('HD046', 'NV003', 'KH005', '2025-05-07 02:30:54', 150000),
('HD047', 'NV004', 'KH005', '2025-05-07 02:45:34', 150000),
('HD048', 'NV001', 'KH004', '2025-05-07 02:50:56', 130000),
('HD049', 'NV003', 'KH002', '2025-05-07 17:28:57', 382500),
('HD050', 'NV004', 'KH004', '2025-05-07 22:27:35', 560000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `hokhachhang` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tenkhachhang` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sdt` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DiaChi` varchar(30) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makh`, `hokhachhang`, `tenkhachhang`, `email`, `sdt`, `DiaChi`) VALUES
('KH001', 'Nguyễn Văn', 'An', 'an.nguyen@example.com', '0912345678', ''),
('KH002', 'Trịnh Ngọc', 'Minh', 'minh.trinh@example.com', '0955566677', ''),
('KH003', 'Dương Thanh', 'Nam', 'nam.duong@example.com', '0966677788', ''),
('KH004', 'Phan Anh', 'Quân', 'quan.phan@example.com	', '0977788899', ''),
('KH005', 'Lương Thu', 'Trang', 'trang.luong@example.com', '0988899000', ''),
('KH006', 'Lương ', 'Thuận', 'thihachcf@gmail.com', '0352447642', ''),
('KH007', 'Trung', 'Trung', 'thihahcf@gmail.com', '0352447642', ''),
('KH008', 'Lê ', 'Văn Luyện', 'thi@gmail.com', '0987654321', ''),
('KH009', 'Trần Thị', 'Bình', 'binh.tran@example.com', '0987654321', ''),
('KH010', 'Lê Minh', 'Cường', 'cuong.le@example.com', '0909876543', ''),
('KH011', 'Phạm Thảo', 'Dung', 'dung.pham@example.com', '0965123456', ''),
('KH012', 'Đặng Hữu', 'Đức', 'duc.dang@example.com', '0978456123', ''),
('KH013', 'Hồ Lan', 'Hương	', 'huong.ho@example.com', '0911122233', ''),
('KH014', 'Bùi Khánh', 'Hòa', 'hoa.bui@example.com', '0922233344', ''),
('KH015', 'Võ Hoàng', 'Khang', 'khang.vo@example.com', '0933344455', ''),
('KH016', 'Nguyễn Mai', 'Linh', 'linh.nguyen@example.com', '0944455566', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `makm` varchar(10) NOT NULL,
  `tenkm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ngaybatdau` datetime NOT NULL,
  `ngayketthuc` datetime NOT NULL,
  `dieukientoithieu` double NOT NULL,
  `phantramgiam` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`makm`, `tenkm`, `ngaybatdau`, `ngayketthuc`, `dieukientoithieu`, `phantramgiam`) VALUES
('12', 's2', '2025-04-16 00:00:00', '2025-05-01 00:00:00', 500000, 13),
('14', 'S1', '2025-05-01 00:00:00', '2025-05-01 00:00:00', 100000, 12),
('15', '1', '2025-05-01 00:00:00', '2025-05-01 00:00:00', 1, 1),
('16', 'Khuyến mãi đầu năm', '2025-05-01 00:00:00', '2025-05-20 00:00:00', 500000, 20),
('17', 'khuyến mãi chơi chơi', '2025-05-07 00:00:00', '2025-05-07 00:00:00', 400000, 15),
('KM001', 's2', '2025-04-16 00:00:00', '2025-05-01 00:00:00', 500000, 13),
('KM002', 'S1', '2025-05-01 00:00:00', '2025-05-01 00:00:00', 100000, 12),
('KM003', '1', '2025-05-01 00:00:00', '2025-05-01 00:00:00', 1, 1),
('KM004', 'Khuyến mãi đầu năm', '2025-05-01 00:00:00', '2025-05-20 00:00:00', 500000, 20),
('KM005', 'khuyến mãi chơi chơi', '2025-05-07 00:00:00', '2025-05-07 00:00:00', 400000, 15);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `mancc` varchar(10) NOT NULL,
  `tenncc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `diachincc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sdt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`mancc`, `tenncc`, `diachincc`, `sdt`, `email`) VALUES
('NCC002', 'CT TNHH Phát Hành Sách Fahasa', '60-62 Lê Lợi, Quận 1, TP.HCM', '(028) 3822 5796', 'fahasa@fahasa.com'),
('NCC003', 'CT Sách Minh Long', '145 Bùi Hữu Nghĩa, Bình Thạnh, TP.HCM', '(028) 3551 5858', 'minhlongbook@gmail.com'),
('NCC004', 'CT TNHH Văn Hóa Hương Trang', '32A Lê Lợi, Q.1, TP.HCM', '(028) 3829 5890', 'huongtrangbooks@gmail.com'),
('NCC005', 'CTCP Sách Thái Hà', 'Số 53A, ngõ 64 Nguyễn Lương Bằng, Đống Đa, Hà Nội', '(024) 3513 6499', 'contact@thaihabooks.com'),
('NCC006', 'Trung', '123aaa', '0352447642', 'thi@gmail.com'),
('NCC007', 'Trung2', '1', '0352449123', 'a@gmail.com'),
('NCC008', 'Trung3', 'a', '0123456789', 'aa@gmail.com'),
('NCC942', 'Công ty cung cấp linh kiện A - UPDATED', '456 Đường Nguyễn Huệ, Quận 1, TP.HCM', '0987654321', 'newemail@ctyA.com'),
('NCC949', 'Công ty cung cấp linh kiện A - UPDATED', '456 Đường Nguyễn Huệ, Quận 1, TP.HCM', '0987654321', 'newemail@ctyA.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `honv` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `tennv` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `sdt` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `DiaChi` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `Luong` varchar(20) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `honv`, `tennv`, `sdt`, `DiaChi`, `Email`, `Luong`) VALUES
('NV001', 'Nguyễn Tiến', 'Trung', '0352447642', '', '', ''),
('NV002', 'Phạm Hoài', 'Phương', '0961640807', '', '', ''),
('NV003', 'Nguyễn Minh', 'Thuận', '0123456789', '', '', ''),
('NV004', 'Bùi Huy', 'Khải', '0123456145', '', '', ''),
('NV005', 'Nguyễn Hải', 'Đăng', '0123456076', '', '', ''),
('NV006', 'Admin', 'Master', '0999999999', '', '', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhaxuatban`
--

CREATE TABLE `nhaxuatban` (
  `manxb` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `tennxb` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `diachinxb` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `sdt` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhaxuatban`
--

INSERT INTO `nhaxuatban` (`manxb`, `tennxb`, `diachinxb`, `sdt`, `email`) VALUES
('NXB001', 'NXB Kim Đồng', '248 Cống Quỳnh, P. Phạm Ngũ Lão, Q.1', '(028) 39250987', 'cnkimdong@nxbkimdong.com.vn'),
('NXB002', 'NXB Trẻ', '161B Lý Chính Thắng, P.7, Q.3, TP. Hồ Chí Minh', '(028) 39316289', 'nxbtre@trepublishinghouse.vn'),
('NXB003', 'NXB Giáo Dục Việt Nam', 'Số 81 Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', '(024) 38220801', 'nxbgd@gmail.com'),
('NXB004', 'NXB Lao Động', '75 Giảng Võ, Đống Đa, Hà Nội', '(024) 38463796', 'nxblaodong@gmail.com'),
('NXB005', 'NXB Tổng Hợp TP.HCM', '62 Nguyễn Thị Minh Khai, P. Đa Kao, Q.1, TP. Hồ Chí Minh', '(028) 38297808', 'nxb@nxbtphcm.com.vn'),
('NXB006', '123', 'Daia', '0352447642', 'th@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `manv` int NOT NULL,
  `mancc` int NOT NULL,
  `thoigiantao` date DEFAULT (now()),
  `tongtien` decimal(20,6) NOT NULL DEFAULT (0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `manv`, `mancc`, `thoigiantao`, `tongtien`) VALUES
('PN001', 1, 1, '2021-03-17', 21000000.000000),
('PN002', 6, 1, '2025-05-06', 1800000.000000),
('PN003', 6, 1, '2025-05-06', 1970000.000000),
('PN004', 6, 1, '2025-05-06', 2400000.000000),
('PN005', 6, 1, '2025-05-06', 150000.000000),
('PN006', 6, 1, '2025-05-06', 130000.000000),
('PN007', 6, 1, '2025-05-06', 150000.000000),
('PN008', 6, 1, '2025-05-06', 130000.000000),
('PN009', 1, 3, '2025-03-01', 3000000.000000),
('PN010', 6, 1, '2025-05-06', 1.000000),
('PN011', 6, 1, '2025-05-06', 1.000000),
('PN012', 6, 1, '2025-05-06', 210000.000000),
('PN013', 6, 1, '2025-05-06', 300000.000000),
('PN014', 6, 1, '2025-05-06', 1.000000),
('PN015', 6, 1, '2025-05-06', 750000.000000),
('PN016', 6, 1, '2025-05-06', 1.000000),
('PN017', 6, 1, '2025-05-07', 1.000000),
('PN018', 6, 1, '2025-05-07', 250000.000000),
('PN019', 6, 1, '2025-05-07', 150000.000000),
('PN020', 1, 5, '2025-03-17', 44000000.000000),
('PN021', 6, 1, '2025-05-07', 1.000000),
('PN022', 1, 2, '2025-03-17', 35000000.000000),
('PN023', 1, 3, '2025-03-17', 32000000.000000),
('PN024', 1, 4, '2025-03-18', 40000000.000000),
('PN025', 3, 1, '2025-03-18', 49000000.000000),
('PN026', 6, 1, '2025-05-05', 150000.000000),
('PN027', 6, 1, '2025-05-05', 300000.000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `masach` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `tensach` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `manxb` int DEFAULT NULL,
  `matacgia` int DEFAULT NULL,
  `matheloai` int DEFAULT NULL,
  `soluongton` int DEFAULT '0',
  `namxuatban` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `dongia` int DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`masach`, `tensach`, `manxb`, `matacgia`, `matheloai`, `soluongton`, `namxuatban`, `dongia`) VALUES
('S001', 'Harry Potter và Hòn Đá Phù Thủy', 1, 1, 5, 987, '1997', 150000),
('S002', 'Dune: Hành tinh cát', 1, 10, 2, 93, '1965', 210000),
('S003', '1984', 2, 11, 2, 402, '1949	', 150000),
('S004', 'Frankenstein', 2, 12, 2, 78, '1818', 140000),
('S005', 'Trò chơi vương quyền', 2, 13, 5, 49, '1996', 190000),
('S006', 'Cô gái có hình xăm rồng', 2, 14, 4, 60, '2005', 160000),
('S007', 'Sherlock Holmes: Tên sát nhân vùng mỏ', 2, 15, 4, 41, '1893', 130000),
('S008', 'Án mạng trên sông Nile', 3, 15, 4, 50, '1937', 150000),
('S009', 'Bí ẩn cung điện Versailles', 3, 16, 4, 58, '2001', 140000),
('S010', 'IT (Gã Hề Ma Quái)', 3, 18, 3, 82, '1986', 180000),
('S011', 'Dracula', 3, 18, 3, 78, '1897', 140000),
('S012', 'Chúa tể những chiếc nhẫn', 1, 1, 1, 213, '1954', 250000),
('S013', 'Bóng ma trong nhà hát', 3, 19, 3, 95, '1910', 130000),
('S014', 'Người đàn bà bí ẩn', 3, 20, 3, 18, '1859', 120000),
('S015', 'Lược sử thời gian', 4, 21, 6, 61, '1988', 200000),
('S016', 'Thế giới phẳng', 4, 23, 8, 81, '2005', 190000),
('S017', 'Cha giàu cha nghèo', 5, 24, 8, 96, '1997', 160000),
('S018', 'One Piece', 4, 24, 9, 81, '1997', 90000),
('S019', 'Naruto', 4, 25, 9, 51, '1999', 90000),
('S020', 'Giải tích căn bản', 4, 24, 10, 103, '1965', 250000),
('S021', 'Cấu trúc dữ liệu và thuật toán', 4, 25, 10, 249, '1990', 270000),
('S022', 'a', 1, 1, 1, 3, '1', 1),
('S023', 'Những kẻ khốn khổ', 1, 2, 1, 310, '1862', 200000),
('S024', 'aa', 1, 1, 1, 3, '1', 1),
('S025', 'a', 1, 1, 1, 1, '1', 1),
('S026', 'a', 1, 1, 1, 1, '1', 1),
('S027', 'Bố già', 1, 4, 1, 34, '1969', 160000),
('S028', 'Đắc Nhân Tâm', 1, 5, 7, 52, '1936', 120000),
('S029', '7 Thói quen của người thành đạt', 2, 6, 7, 62, '1989', 140000),
('S030', 'Nhà giả kim', 1, 8, 1, 72, '1988', 130000),
('S031', 'Tội ác và trừng phạt	', 1, 8, 1, 86, '1866', 170000),
('S032', 'Chiến tranh và hòa bình	', 1, 9, 1, 952, '1869', 200000),
('S1', 'Harry Potter và Hòn Đá Phù Thủy', 1, 1, 5, 987, '1997', 150000),
('S10', 'Dune: Hành tinh cát', 1, 10, 2, 93, '1965', 210000),
('S11', '1984', 2, 11, 2, 402, '1949	', 150000),
('S12', 'Frankenstein', 2, 12, 2, 78, '1818', 140000),
('S13', 'Trò chơi vương quyền', 2, 13, 5, 49, '1996', 190000),
('S14', 'Cô gái có hình xăm rồng', 2, 14, 4, 60, '2005', 160000),
('S15', 'Sherlock Holmes: Tên sát nhân vùng mỏ', 2, 15, 4, 41, '1893', 130000),
('S16', 'Án mạng trên sông Nile', 3, 15, 4, 50, '1937', 150000),
('S17', 'Bí ẩn cung điện Versailles', 3, 16, 4, 58, '2001', 140000),
('S18', 'IT (Gã Hề Ma Quái)', 3, 18, 3, 82, '1986', 180000),
('S19', 'Dracula', 3, 18, 3, 78, '1897', 140000),
('S2', 'Chúa tể những chiếc nhẫn', 1, 1, 1, 213, '1954', 250000),
('S20', 'Bóng ma trong nhà hát', 3, 19, 3, 95, '1910', 130000),
('S21', 'Người đàn bà bí ẩn', 3, 20, 3, 18, '1859', 120000),
('S22', 'Lược sử thời gian', 4, 21, 6, 61, '1988', 200000),
('S23', 'Thế giới phẳng', 4, 23, 8, 81, '2005', 190000),
('S24', 'Cha giàu cha nghèo', 5, 24, 8, 96, '1997', 160000),
('S25', 'One Piece', 4, 24, 9, 81, '1997', 90000),
('S26', 'Naruto', 4, 25, 9, 51, '1999', 90000),
('S27', 'Giải tích căn bản', 4, 24, 10, 103, '1965', 250000),
('S28', 'Cấu trúc dữ liệu và thuật toán', 4, 25, 10, 249, '1990', 270000),
('S29', 'a', 1, 1, 1, 3, '1', 1),
('S3', 'Những kẻ khốn khổ', 1, 2, 1, 310, '1862', 200000),
('S30', 'aa', 1, 1, 1, 3, '1', 1),
('S31', 'a', 1, 1, 1, 1, '1', 1),
('S32', 'a', 1, 1, 1, 1, '1', 1),
('S4', 'Bố già', 1, 4, 1, 34, '1969', 160000),
('S5', 'Đắc Nhân Tâm', 1, 5, 7, 52, '1936', 120000),
('S6', '7 Thói quen của người thành đạt', 2, 6, 7, 62, '1989', 140000),
('S7', 'Nhà giả kim', 1, 8, 1, 72, '1988', 130000),
('S8', 'Tội ác và trừng phạt	', 1, 8, 1, 86, '1866', 170000),
('S9', 'Chiến tranh và hòa bình	', 1, 9, 1, 952, '1869', 200000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tacgia`
--

CREATE TABLE `tacgia` (
  `matacgia` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `tentacgia` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tacgia`
--

INSERT INTO `tacgia` (`matacgia`, `tentacgia`) VALUES
('1', 'J.R.R. Tolkien'),
('10', 'Frank Herbert'),
('11', 'George Orwell'),
('12', 'Mary Shelley'),
('13', 'George R.R. Martin'),
('14', 'Stieg Larsson'),
('15', 'Arthur Conan Doyle'),
('16', 'Agatha Christie'),
('17', 'Jean-Christian Petitfils'),
('18', 'Stephen King'),
('19', 'Bram Stoker'),
('2', 'Victor Hugo'),
('20', 'Gaston Leroux'),
('21', 'Wilkie Collins'),
('22', 'Stephen Hawking'),
('23', 'Hugh Trevor-Roper'),
('24', 'Baird T. Spalding'),
('25', 'Thomas L. Friedman'),
('26', 'Robert Kiyosaki & Sharon Lechter'),
('27', 'Eiichiro Oda'),
('3', 'Mario Puzo'),
('4', 'Dale Carnegie'),
('5', 'Stephen R. Covey'),
('6', 'Masashi Kishimoto'),
('7', 'Paulo Coelho'),
('8', 'Fyodor Dostoevsky'),
('9', 'Lev Tolstoy'),
('TG001', 'Agatha Christie'),
('TG002', 'Arthur Conan Doyle'),
('TG003', 'Baird T. Spalding'),
('TG004', 'Bram Stoker'),
('TG005', 'Dale Carnegie'),
('TG006', 'Eiichiro Oda'),
('TG007', 'Frank Herbert'),
('TG008', 'Fyodor Dostoevsky'),
('TG009', 'Gaston Leroux'),
('TG010', 'George Orwell'),
('TG011', 'George R.R. Martin'),
('TG012', 'Hugh Trevor-Roper'),
('TG013', 'J.R.R. Tolkien'),
('TG014', 'Jean-Christian Petitfils'),
('TG015', 'Lev Tolstoy'),
('TG016', 'Mario Puzo'),
('TG017', 'Mary Shelley'),
('TG018', 'Masashi Kishimoto'),
('TG019', 'Paulo Coelho'),
('TG020', 'Robert Kiyosaki & Sharon Lechter'),
('TG021', 'Stephen Hawking'),
('TG022', 'Stephen King'),
('TG023', 'Stephen R. Covey'),
('TG024', 'Stieg Larsson'),
('TG025', 'Thomas L. Friedman'),
('TG026', 'Victor Hugo'),
('TG027', 'Wilkie Collins');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloai`
--

CREATE TABLE `theloai` (
  `matheloai` varchar(19) COLLATE utf8mb4_general_ci NOT NULL,
  `tentheloai` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `theloai`
--

INSERT INTO `theloai` (`matheloai`, `tentheloai`) VALUES
('1', 'Tiểu thuyết'),
('10', 'Sách giáo khoa, tham khảo'),
('2', 'Khoa học viễn tưởng'),
('3', 'Kinh dị'),
('4', 'Trinh thám'),
('5', 'Kỳ ảo'),
('6', 'Lịch sử'),
('7', 'Tâm lý, kỹ năng sống'),
('8', 'Kinh doanh, đầu tư'),
('9', 'Truyện tranh, manga'),
('TL001', 'Tiểu thuyết'),
('TL002', 'Truyện tranh, manga'),
('TL003', 'Kinh doanh, đầu tư'),
('TL004', 'Tâm lý, kỹ năng sống'),
('TL005', 'Lịch sử'),
('TL006', 'Kỳ ảo'),
('TL007', 'Trinh thám'),
('TL008', 'Kinh dị'),
('TL009', 'Khoa học viễn tưởng'),
('TL010', 'Sách giáo khoa, tham khảo');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahoadon`),
  ADD KEY `FK_manv` (`manv`),
  ADD KEY `FK-makh` (`makh`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`makm`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Chỉ mục cho bảng `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  ADD PRIMARY KEY (`manxb`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`masach`);

--
-- Chỉ mục cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`matacgia`);

--
-- Chỉ mục cho bảng `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`matheloai`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK-makh` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_manv` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
