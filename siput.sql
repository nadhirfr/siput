-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2017 at 11:55 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `siput`
--

-- --------------------------------------------------------

--
-- Table structure for table `deposit`
--

CREATE TABLE `deposit` (
  `deposit_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `deposit_jumlah` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposit`
--

INSERT INTO `deposit` (`deposit_id`, `user_id`, `deposit_jumlah`) VALUES
(00001, 00004, 0),
(00002, 00002, 20000),
(00004, 00001, 225000);

-- --------------------------------------------------------

--
-- Table structure for table `iuran`
--

CREATE TABLE `iuran` (
  `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_nama` varchar(35) NOT NULL,
  `iuran_nominal` int(9) NOT NULL,
  `iuran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `iuran`
--

INSERT INTO `iuran` (`iuran_id`, `iuran_nama`, `iuran_nominal`, `iuran_jenis_id`, `iuran_kategori_id`) VALUES
(00001, 'Iuran Sampah', 10000, 00002, 00001),
(00002, 'Iuran Pokok', 25000, 00001, 00001),
(00003, 'Iuran Sosial', 10000, 00001, 00001),
(00004, 'Iuran Syawalan', 20000, 00002, 00002),
(00005, 'Iuran 17-an', 20000, 00001, 00002),
(00006, 'Pembangunan Infrastruktur', 30000, 00001, 00002),
(00007, 'Sumbangan warga baru', 200000, 00001, 00002);

-- --------------------------------------------------------

--
-- Table structure for table `iuran_jenis`
--

CREATE TABLE `iuran_jenis` (
  `iuran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_jenis_nama` varchar(20) NOT NULL,
  `iuran_jenis_keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `iuran_jenis`
--

INSERT INTO `iuran_jenis` (`iuran_jenis_id`, `iuran_jenis_nama`, `iuran_jenis_keterangan`) VALUES
(00001, 'Iuran Wajib', 'Merupakan iuran yang wajib dibayar oleh semua anggota'),
(00002, 'Iuran tidak wajib', 'Merupakan iuran yang tidak wajib dibayar oleh semua anggota');

-- --------------------------------------------------------

--
-- Table structure for table `iuran_kategori`
--

CREATE TABLE `iuran_kategori` (
  `iuran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_kategori_nama` varchar(20) NOT NULL,
  `iuran_kategori_interval` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `iuran_kategori`
--

INSERT INTO `iuran_kategori` (`iuran_kategori_id`, `iuran_kategori_nama`, `iuran_kategori_interval`) VALUES
(00001, 'Iuran Rutin', 7),
(00002, 'Iuran Insidental', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `iuran_perubahan`
--

CREATE TABLE `iuran_perubahan` (
  `iuran_perubahan_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_perubahan_nominal` int(9) NOT NULL,
  `iuran_perubahan_date` date NOT NULL,
  `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `iuran_user`
--

CREATE TABLE `iuran_user` (
  `iuran_user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_user_status` tinyint(1) NOT NULL,
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `iuran_user`
--

INSERT INTO `iuran_user` (`iuran_user_id`, `iuran_user_status`, `user_id`, `iuran_id`) VALUES
(00001, 0, 00004, 00001),
(00002, 1, 00001, 00002),
(00003, 0, 00001, 00003),
(00004, 1, 00001, 00003),
(00005, 1, 00001, 00004),
(00006, 1, 00001, 00005),
(00007, 1, 00001, 00006);

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `pengeluaran_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_nama` varchar(30) NOT NULL,
  `pengeluaran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengeluaran`
--

INSERT INTO `pengeluaran` (`pengeluaran_id`, `pengeluaran_nama`, `pengeluaran_jenis_id`, `pengeluaran_kategori_id`) VALUES
(00001, 'Pembayaran Sampah', 00001, 00001),
(00002, 'Pembayaran Iuran RW', 00001, 00001),
(00003, 'Pembayaran Bersih Makam', 00001, 00001),
(00004, 'Pembayaran Konsum Rapat', 00001, 00001),
(00005, 'Gaji Pengelola Lampu', 00001, 00001),
(00006, 'Pembangunan Infrastruktur', 00002, 00002);

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran_jenis`
--

CREATE TABLE `pengeluaran_jenis` (
  `pengeluaran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_nama` varchar(20) NOT NULL,
  `pengeluaran_keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengeluaran_jenis`
--

INSERT INTO `pengeluaran_jenis` (`pengeluaran_jenis_id`, `pengeluaran_nama`, `pengeluaran_keterangan`) VALUES
(00001, 'wajib', 'ini jenis pengeluaran yang harus dibayar '),
(00002, 'tidak wajib', 'ini yang tidak wajib');

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran_kategori`
--

CREATE TABLE `pengeluaran_kategori` (
  `pengeluaran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_kategori_nama` varchar(20) NOT NULL,
  `pengeluaran_kategori_waktu` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengeluaran_kategori`
--

INSERT INTO `pengeluaran_kategori` (`pengeluaran_kategori_id`, `pengeluaran_kategori_nama`, `pengeluaran_kategori_waktu`) VALUES
(00001, 'rutin', 7),
(00002, 'insidental', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran_perubahan`
--

CREATE TABLE `pengeluaran_perubahan` (
  `pengeluaran_perubahan_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_perubahan_nominal` int(9) NOT NULL,
  `pengeluaran_perubahan_date` date NOT NULL,
  `pengeluaran_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `session_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `session_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `transaksi_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `transaksi_date` date NOT NULL,
  `transaksi_nama` varchar(30) NOT NULL,
  `transaksi_nominal` int(9) NOT NULL,
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `transaksi_tipe` enum('iuran','pengeluaran') NOT NULL,
  `iuran_id` int(5) UNSIGNED ZEROFILL DEFAULT NULL,
  `pengeluaran_id` int(5) UNSIGNED ZEROFILL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`transaksi_id`, `transaksi_date`, `transaksi_nama`, `transaksi_nominal`, `user_id`, `transaksi_tipe`, `iuran_id`, `pengeluaran_id`) VALUES
(00034, '2017-04-20', 'Pembangunan Infrastruktur', 30000, 00001, 'iuran', 00006, NULL),
(00035, '2017-04-20', 'Iuran Pokok', 20000, 00001, 'iuran', 00002, NULL),
(00036, '2017-04-20', 'Iuran Pokok', 5000, 00001, 'iuran', 00002, NULL),
(00037, '2017-04-20', 'Iuran Sosial', 10000, 00001, 'iuran', 00003, NULL),
(00038, '2017-04-20', 'Iuran Syawalan', 20000, 00001, 'iuran', 00004, NULL),
(00039, '2017-04-20', 'Iuran 17-an', 20000, 00001, 'iuran', 00005, NULL),
(00040, '2017-04-20', 'Pembayaran Sampah', 10000, 00001, 'pengeluaran', NULL, 00001),
(00041, '2017-04-20', 'Pembayaran Iuran RW', 10000, 00001, 'pengeluaran', NULL, 00002),
(00042, '2017-04-21', 'Pembayaran Sampah', 20000, 00001, 'pengeluaran', NULL, 00001),
(00043, '2017-04-21', 'Iuran Sampah', 5000, 00004, 'iuran', 00001, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `user_username` varchar(8) NOT NULL,
  `user_displayname` varchar(30) NOT NULL,
  `user_password` varchar(10) NOT NULL,
  `user_tipe` enum('admin','operator','anggota') NOT NULL DEFAULT 'anggota'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_username`, `user_displayname`, `user_password`, `user_tipe`) VALUES
(00001, 'admin', 'Administrator', 'admin', 'admin'),
(00002, 'op1', 'Operator-1', 'op1', 'operator'),
(00004, 'anggota1', 'Anggota 1', 'anggota1', 'anggota'),
(00005, 'kuda', 'kuda', 'kuda', 'anggota');

-- --------------------------------------------------------

--
-- Table structure for table `utang`
--

CREATE TABLE `utang` (
  `utang_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `utang_nominal` int(10) NOT NULL,
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `deposit`
--
ALTER TABLE `deposit`
  ADD PRIMARY KEY (`deposit_id`),
  ADD UNIQUE KEY `user_id_2` (`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `iuran`
--
ALTER TABLE `iuran`
  ADD PRIMARY KEY (`iuran_id`),
  ADD KEY `iuran_jenis_id` (`iuran_jenis_id`,`iuran_kategori_id`),
  ADD KEY `iuran_kategori_id` (`iuran_kategori_id`);

--
-- Indexes for table `iuran_jenis`
--
ALTER TABLE `iuran_jenis`
  ADD PRIMARY KEY (`iuran_jenis_id`);

--
-- Indexes for table `iuran_kategori`
--
ALTER TABLE `iuran_kategori`
  ADD PRIMARY KEY (`iuran_kategori_id`);

--
-- Indexes for table `iuran_perubahan`
--
ALTER TABLE `iuran_perubahan`
  ADD PRIMARY KEY (`iuran_perubahan_id`),
  ADD KEY `iuran_id` (`iuran_id`);

--
-- Indexes for table `iuran_user`
--
ALTER TABLE `iuran_user`
  ADD PRIMARY KEY (`iuran_user_id`),
  ADD KEY `user_id` (`user_id`,`iuran_id`),
  ADD KEY `iuran_id` (`iuran_id`);

--
-- Indexes for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`pengeluaran_id`),
  ADD KEY `pengeluaran_jenis_id` (`pengeluaran_jenis_id`),
  ADD KEY `pengeluaran_kategori_id` (`pengeluaran_kategori_id`);

--
-- Indexes for table `pengeluaran_jenis`
--
ALTER TABLE `pengeluaran_jenis`
  ADD PRIMARY KEY (`pengeluaran_jenis_id`);

--
-- Indexes for table `pengeluaran_kategori`
--
ALTER TABLE `pengeluaran_kategori`
  ADD PRIMARY KEY (`pengeluaran_kategori_id`);

--
-- Indexes for table `pengeluaran_perubahan`
--
ALTER TABLE `pengeluaran_perubahan`
  ADD PRIMARY KEY (`pengeluaran_perubahan_id`),
  ADD KEY `iuran_id` (`pengeluaran_id`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`session_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`transaksi_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `iuran_id` (`iuran_id`),
  ADD KEY `pengeluaran_id` (`pengeluaran_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `utang`
--
ALTER TABLE `utang`
  ADD PRIMARY KEY (`utang_id`),
  ADD KEY `user_id` (`user_id`,`iuran_id`),
  ADD KEY `utang_iuran_id` (`iuran_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `deposit`
--
ALTER TABLE `deposit`
  MODIFY `deposit_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `iuran`
--
ALTER TABLE `iuran`
  MODIFY `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `iuran_kategori`
--
ALTER TABLE `iuran_kategori`
  MODIFY `iuran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `iuran_perubahan`
--
ALTER TABLE `iuran_perubahan`
  MODIFY `iuran_perubahan_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `iuran_user`
--
ALTER TABLE `iuran_user`
  MODIFY `iuran_user_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  MODIFY `pengeluaran_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `pengeluaran_jenis`
--
ALTER TABLE `pengeluaran_jenis`
  MODIFY `pengeluaran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pengeluaran_kategori`
--
ALTER TABLE `pengeluaran_kategori`
  MODIFY `pengeluaran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pengeluaran_perubahan`
--
ALTER TABLE `pengeluaran_perubahan`
  MODIFY `pengeluaran_perubahan_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `session_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `transaksi_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `utang`
--
ALTER TABLE `utang`
  MODIFY `utang_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `deposit`
--
ALTER TABLE `deposit`
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `iuran`
--
ALTER TABLE `iuran`
  ADD CONSTRAINT `iuran_ibfk_1` FOREIGN KEY (`iuran_kategori_id`) REFERENCES `iuran_kategori` (`iuran_kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `iuran_jenis` FOREIGN KEY (`iuran_jenis_id`) REFERENCES `iuran_jenis` (`iuran_jenis_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `iuran_perubahan`
--
ALTER TABLE `iuran_perubahan`
  ADD CONSTRAINT `iuran_perubahan_ibfk_1` FOREIGN KEY (`iuran_id`) REFERENCES `iuran` (`iuran_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `iuran_user`
--
ALTER TABLE `iuran_user`
  ADD CONSTRAINT `iuran_id` FOREIGN KEY (`iuran_id`) REFERENCES `iuran` (`iuran_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `iuran_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD CONSTRAINT `kategori_kategori_id` FOREIGN KEY (`pengeluaran_kategori_id`) REFERENCES `pengeluaran_kategori` (`pengeluaran_kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pengeluaran_jenis_id` FOREIGN KEY (`pengeluaran_jenis_id`) REFERENCES `pengeluaran_jenis` (`pengeluaran_jenis_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pengeluaran_perubahan`
--
ALTER TABLE `pengeluaran_perubahan`
  ADD CONSTRAINT `pengeluaran_perubahan_ibfk_1` FOREIGN KEY (`pengeluaran_id`) REFERENCES `pengeluaran` (`pengeluaran_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `iuran_id_transaksi` FOREIGN KEY (`iuran_id`) REFERENCES `iuran` (`iuran_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pengeluaran_id_transaksi` FOREIGN KEY (`pengeluaran_id`) REFERENCES `pengeluaran` (`pengeluaran_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_id_` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `utang`
--
ALTER TABLE `utang`
  ADD CONSTRAINT `utang_iuran_id` FOREIGN KEY (`iuran_id`) REFERENCES `iuran` (`iuran_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `utang_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
