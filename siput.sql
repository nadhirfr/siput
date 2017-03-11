-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 11, 2017 at 10:34 AM
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

-- --------------------------------------------------------

--
-- Table structure for table `deposit_perubahan`
--

CREATE TABLE `deposit_perubahan` (
  `deposit_perubahan_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `deposit_perubahan_date` date NOT NULL,
  `deposit_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `transaksi_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `iuran`
--

CREATE TABLE `iuran` (
  `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_nama` varchar(20) NOT NULL,
  `iuran_nominal` int(9) NOT NULL,
  `iuran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `iuran_jenis`
--

CREATE TABLE `iuran_jenis` (
  `iuran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_jenis_nama` varchar(20) NOT NULL,
  `iuran_jenis_keterangan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `iuran_kategori`
--

CREATE TABLE `iuran_kategori` (
  `iuran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_kategori_nama` varchar(20) NOT NULL,
  `iuran_kategori_interval` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `transaksi_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `pengeluaran_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_nama` varchar(20) NOT NULL,
  `pengeluaran_nominal` int(9) NOT NULL,
  `pengeluaran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran_jenis`
--

CREATE TABLE `pengeluaran_jenis` (
  `pengeluaran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_nama` varchar(20) NOT NULL,
  `pengeluaran_keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran_kategori`
--

CREATE TABLE `pengeluaran_kategori` (
  `pengeluaran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_kategori_nama` varchar(20) NOT NULL,
  `pengeluaran_kategori_waktu` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Table structure for table `pengeluaran_user`
--

CREATE TABLE `pengeluaran_user` (
  `pengeluaran_user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `pengeluaran_user_status` tinyint(1) NOT NULL,
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `transaksi_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `session_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `session_time` time NOT NULL,
  `session_status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `transaksi_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `transaksi_date` date NOT NULL,
  `transaksi_nama` varchar(20) NOT NULL,
  `user_id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `transaksi_tipe_id` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_tipe`
--

CREATE TABLE `transaksi_tipe` (
  `transaksi_tipe_id` int(5) NOT NULL,
  `transaksi_tipe_nama` varchar(20) NOT NULL,
  `transaksi_tipe_keterangan` text
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
(00002, 'op1', 'Operator-1', 'op1', 'operator');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `deposit`
--
ALTER TABLE `deposit`
  ADD PRIMARY KEY (`deposit_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `deposit_perubahan`
--
ALTER TABLE `deposit_perubahan`
  ADD PRIMARY KEY (`deposit_perubahan_id`),
  ADD KEY `deposit_id` (`deposit_id`),
  ADD KEY `transaksi_id` (`transaksi_id`);

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
  ADD KEY `user_id` (`user_id`,`iuran_id`,`transaksi_id`),
  ADD KEY `iuran_id` (`iuran_id`),
  ADD KEY `transaksi_id` (`transaksi_id`);

--
-- Indexes for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`pengeluaran_id`),
  ADD KEY `pengeluaran_jenis_id` (`pengeluaran_jenis_id`);

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
-- Indexes for table `pengeluaran_user`
--
ALTER TABLE `pengeluaran_user`
  ADD PRIMARY KEY (`pengeluaran_user_id`),
  ADD KEY `user_id` (`user_id`,`iuran_id`,`transaksi_id`),
  ADD KEY `pengeluaran_user_iuran_id` (`iuran_id`),
  ADD KEY `pengeluaran_user_transaksi_id` (`transaksi_id`);

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
  ADD KEY `user_id` (`user_id`,`transaksi_tipe_id`),
  ADD KEY `transaksi_ibfk_2` (`transaksi_tipe_id`);

--
-- Indexes for table `transaksi_tipe`
--
ALTER TABLE `transaksi_tipe`
  ADD PRIMARY KEY (`transaksi_tipe_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `deposit`
--
ALTER TABLE `deposit`
  MODIFY `deposit_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `deposit_perubahan`
--
ALTER TABLE `deposit_perubahan`
  MODIFY `deposit_perubahan_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `iuran`
--
ALTER TABLE `iuran`
  MODIFY `iuran_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `iuran_kategori`
--
ALTER TABLE `iuran_kategori`
  MODIFY `iuran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `iuran_perubahan`
--
ALTER TABLE `iuran_perubahan`
  MODIFY `iuran_perubahan_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `iuran_user`
--
ALTER TABLE `iuran_user`
  MODIFY `iuran_user_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  MODIFY `pengeluaran_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pengeluaran_jenis`
--
ALTER TABLE `pengeluaran_jenis`
  MODIFY `pengeluaran_jenis_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pengeluaran_kategori`
--
ALTER TABLE `pengeluaran_kategori`
  MODIFY `pengeluaran_kategori_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pengeluaran_perubahan`
--
ALTER TABLE `pengeluaran_perubahan`
  MODIFY `pengeluaran_perubahan_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pengeluaran_user`
--
ALTER TABLE `pengeluaran_user`
  MODIFY `pengeluaran_user_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `session_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `transaksi_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaksi_tipe`
--
ALTER TABLE `transaksi_tipe`
  MODIFY `transaksi_tipe_id` int(5) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `deposit`
--
ALTER TABLE `deposit`
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `deposit_perubahan`
--
ALTER TABLE `deposit_perubahan`
  ADD CONSTRAINT `deposit_id` FOREIGN KEY (`deposit_id`) REFERENCES `deposit` (`deposit_id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `iuran_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_id` FOREIGN KEY (`transaksi_id`) REFERENCES `transaksi` (`transaksi_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD CONSTRAINT `pengeluaran_jenis_id` FOREIGN KEY (`pengeluaran_jenis_id`) REFERENCES `pengeluaran_jenis` (`pengeluaran_jenis_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pengeluaran_perubahan`
--
ALTER TABLE `pengeluaran_perubahan`
  ADD CONSTRAINT `pengeluaran_perubahan_ibfk_1` FOREIGN KEY (`pengeluaran_id`) REFERENCES `pengeluaran` (`pengeluaran_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pengeluaran_user`
--
ALTER TABLE `pengeluaran_user`
  ADD CONSTRAINT `pengeluaran_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pengeluaran_user_iuran_id` FOREIGN KEY (`iuran_id`) REFERENCES `iuran` (`iuran_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pengeluaran_user_transaksi_id` FOREIGN KEY (`transaksi_id`) REFERENCES `transaksi` (`transaksi_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`transaksi_tipe_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
