-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: raelsa
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_id` varchar(200) NOT NULL,
  `judul` varchar(200) DEFAULT NULL,
  `penulis` varchar(200) DEFAULT NULL,
  `tahun` varchar(200) DEFAULT NULL,
  `status` int DEFAULT '0',
  `foto` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('B1','Buku Sakti PBO','Tesalonika Sitopu','2000',1,'source\\B1.png'),('B10','Kitab Ilmu Kebal','Rio Eka Pasaribu','2000',0,'source\\B10.png'),('B11','Belajar Linux','Gabriel Cesar Hutagalung','2012',0,'source\\B11.jpg'),('B12','Rahasia Beternak','Gabriel Cesar Hutagalung','2022',0,'source\\B12.jpg'),('B13','Public Speaking','Syalom Manurung','2021',0,'source\\B13.jpg'),('B14','Fisika Dasar','Tiara Yuliandari','2019',0,'source\\B14.jpg'),('B15','Matematika Diskrit','Nigel Siregar','2017',0,'source\\B15.jpg'),('B16','Laskar Pelangi','Dwi Purba','2005',0,'source\\B16.jpg'),('B17','Resep Nasi Goreng','Derik Damero Sianipar','1999',0,'source\\B17.jpg'),('B18','Rahasia Blender','Erick Sibuea','1990',0,'source\\B18.jpg'),('B19','Game Development','Benhard Yudha Simangunsong','2022',0,'source\\B19.jpg'),('B2','Memasak Kue','Grase Panjaitan','1998',0,'source\\B2.png'),('B20','Kitab Sakti Dapat Pacar','Gabriel Cesar Hutagalung','2022',0,'source\\B20.jpg'),('B21','Rahasia Tetap Tegar','Rafael Manurung','2022',0,'source\\B21.jpg'),('B22','Tips Tidak Panik','Benhard Yudha','2019',0,'source\\B22.jpg'),('B23','Cara Tidur yang Benar','Jayfline Hutagalung','2015',1,'source\\B23.jpg'),('B24','Cara Menangkal Roh Jahat','Dwi Purba','2022',0,'source\\B24.jpg'),('B25','Tutor Masuk FYP Tiktok','Missyolin Samosir, Wilona Simbolon','2022',0,'source\\B25.jpg'),('B26','Tutorial Mengajar yang Benar','Derik Damero Sianipar','2021',0,'source\\B26.jpg'),('B3','Belajar Menyetir','Gabriel Cesar Hutagalung','2000',0,'source\\B3.png'),('B4','Cara Diet','Rafael Manurung','1999',0,'source\\B4.png'),('B5','Calculus','Purcell','1900',0,'source\\B5.png'),('B6','1 Menit Jago Java','Dimas','1700',0,'source\\B6.png'),('B7','Jago Masak','Cindy Simangunsong','2003',0,'source\\B7.png'),('B8','Engineer for Ind','Fernando Pakpahan','1999',0,'source\\B8.png'),('B9','Buku Beladiri','Partoho Pasaribu','1500',0,'source\\B9.png');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarian`
--

DROP TABLE IF EXISTS `librarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarian` (
  `nama` varchar(300) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `foto` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarian`
--

LOCK TABLES `librarian` WRITE;
/*!40000 ALTER TABLE `librarian` DISABLE KEYS */;
INSERT INTO `librarian` VALUES ('Gabriel Cesar Hutagalung','gabriel','agustus163','source\\bright.png'),('Grase T. Panjaitan','grase','53','source\\grase.png'),('Tesalonika Aprisda Sitopu','tesalonika','5','source\\tesa.png');
/*!40000 ALTER TABLE `librarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logperpus`
--

DROP TABLE IF EXISTS `logperpus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logperpus` (
  `nim` varchar(10) DEFAULT NULL,
  `nama` varchar(200) DEFAULT NULL,
  `prodi` varchar(20) DEFAULT NULL,
  `angkatan` varchar(5) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  KEY `nim` (`nim`),
  CONSTRAINT `logperpus_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `member` (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logperpus`
--

LOCK TABLES `logperpus` WRITE;
/*!40000 ALTER TABLE `logperpus` DISABLE KEYS */;
INSERT INTO `logperpus` VALUES ('11S21010','Gabriel Cesar Hutagalung','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:10:35 AM'),('11S21053','Grase Thessalonika Panjaitan','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:11:19 AM'),('11S21005','Tesalonika Aprisda Sitopu','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:25:32 AM'),('11S21001','David Vincent Gurning','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:41:15 AM'),('11S21004','Riris Iyut','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:41:21 AM'),('11S21031','Theo Aritonang','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:41:31 AM'),('11S21007','Tiara Yuliandari','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:41:36 AM'),('11S21007','Tiara Yuliandari','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:41:36 AM'),('11S21053','Grase Thessalonika Panjaitan','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:41:59 AM'),('11S21009','Yose Siagian','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:42:05 AM'),('11S21011','Archico Darius Sembiring','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:42:09 AM'),('12S22004','Bethania Hasibuan','S1 Sistem Informasi','2022','Tuesday, 29 November 2022 | 09:52:00 AM'),('11S21002','Rifqi Haikal','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:53:58 AM'),('11S21003','Benhard Yudha','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:54:02 AM'),('11S21050','Ella Silaban','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:54:13 AM'),('11S21052','Emely Simanjuntak','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:54:17 AM'),('11S21017','Dea Saragih','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:54:29 AM'),('11S21018','Syalom Manurung','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:54:36 AM'),('11S21027','Derik Sianipar','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:54:46 AM'),('11S21048','Angelika Simatupang','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:55:01 AM'),('11S21048','Angelika Simatupang','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:55:01 AM'),('11S21048','Angelika Simatupang','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:55:01 AM'),('11S21048','Angelika Simatupang','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:55:01 AM'),('11S21048','Angelika Simatupang','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:55:01 AM'),('11S21048','Angelika Simatupang','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:55:01 AM'),('11S21048','Angelika Simatupang','S1 Informatika','2021','Tuesday, 29 November 2022 | 09:55:01 AM'),('11S21033','Rio Pasaribu','S1 Informatika','2021','Tuesday, 29 November 2022 | 10:05:26 AM');
/*!40000 ALTER TABLE `logperpus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `nim` varchar(50) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `prodi` varchar(50) NOT NULL,
  `angkatan` char(4) DEFAULT NULL,
  `foto` varchar(300) DEFAULT NULL,
  `status` int DEFAULT '0',
  PRIMARY KEY (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('11322024','Immanuel Pardede','D3 Teknologi Informasi','2022','source\\11322024.jpg',0),('11S21001','David Vincent Gurning','S1 Informatika','2021','source\\11S21001.jpg',0),('11S21002','Rifqi Haikal','S1 Informatika','2021','source\\11S21002.jpg',0),('11S21003','Benhard Yudha','S1 Informatika','2021','source\\11S21003.jpg',0),('11S21004','Riris Iyut','S1 Informatika','2021','source\\11S21004.jpg',0),('11S21005','Tesalonika Aprisda Sitopu','S1 Informatika','2021','source\\11S21005.jpg',0),('11S21006','Salwa Zahira','S1 Informatika','2021','source\\11S21006.jpg',0),('11S21007','Tiara Yuliandari','S1 Informatika','2021','source\\11S21007.jpg',0),('11S21008','Missyolin Eunike','S1 Informatika','2021','source\\11S21008.jpg',0),('11S21009','Yose Siagian','S1 Informatika','2021','source\\11S21009.jpg',0),('11S21010','Gabriel Cesar Hutagalung','S1 Informatika','2021','source\\11S21010.png',1),('11S21011','Archico Darius Simpar Sembiring','S1 Informatika','2021','source\\11S21011.jpg',0),('11S21012','Dwi Purba','S1 Informatika','2021','source\\11S21012.jpg',0),('11S21014','Dedi Panggabean','S1 Informatika','2021','source\\11S21014.jpeg',0),('11S21015','Gerald Nathanael','S1 Informatika','2021','source\\11S21015.png',0),('11S21016','Putri Manurung','S1 Informatika','2021','source\\11S21016.jpeg',0),('11S21017','Dea Saragih','S1 Informatika','2021','source\\11S21017.jpeg',0),('11S21018','Syalom Manurung','S1 Informatika','2021','C:\\Users\\Gabriel\\Desktop\\source\\11S21018.jpg',0),('11S21019','Agustina Butar-butar','S1 Informatika','2021','source\\11S21019.jpg',0),('11S21020','Intan Sianipar','S1 Informatika','2021','source\\11S21020.jpg',0),('11S21021','Naomi Sihombing','S1 Informatika','2021','C:\\Users\\Gabriel\\Desktop\\member\\11S21021.jpeg',0),('11S21023','Yefta Siahaan','S1 Informatika','2021','source\\11S21023.jpg',0),('11S21024','Robert Aritonang','S1 Informatika','2021','source\\11S21024.jpg',0),('11S21025','Boy Sitorus','S1 Informatika','2021','C:\\Users\\Gabriel\\Desktop\\member\\11s21025.jpg',0),('11S21026','Royman','S1 Informatika','2021','source\\11S21026.png',0),('11S21027','Derik Sianipar','S1 Informatika','2021','source\\11S21027.jpeg',0),('11S21028','Rafael Manurung','S1 Informatika','2021','source\\11S21028.jpg',0),('11S21031','Theo Aritonang','S1 Informatika','2021','source\\11S21031.jpg',0),('11S21032','Lukas Sinaga','S1 Informatika','2021','source\\11S21032.png',0),('11S21033','Rio Pasaribu','S1 Informatika','2021','source\\11S21033.jpg',0),('11S21034','Enrico Sirait','S1 Informatika','2021','source\\11S21034.jpg',0),('11S21035','Nicholas Hutabarat','S1 Informatika','2021','source\\11S21035.jpg',0),('11S21036','Fernando Pakpahan','S1 Informatika','2021','source\\11S21036.jpeg',0),('11S21037','Dastin Sirait','S1 Informatika','2021','source\\11S21037.jpg',0),('11S21038','Jayfline Hutagalung','S1 Informatika','2021','source\\11S21038.jpg',0),('11S21040','Agnes Marbun','S1 Informatika','2021','source\\11S21040.jpeg',0),('11S21041','Itha Tarigan','S1 Informatika','2021','source\\11S21041. Taringan_S1Informatika',0),('11S21042','Pany Matondang','S1 Informatika','2021','source\\11S21042.jpeg',0),('11S21044','Icha Samosir','S1 Informatika','2021','source\\11S21044.jpeg',0),('11S21045','Cindy Simangunsong','S1 Informatika','2021','source\\11S21045.jpeg',0),('11S21046','Riskia Sitorus','S1 Informatika','2021','source\\11S21046.jpg',0),('11S21048','Angelika Simatupang','S1 Informatika','2021','source\\11S21048.jpeg',0),('11S21049','Tabitha Marbun','S1 Informatika','2021','source\\11S21049.jpg',0),('11S21050','Ella Silaban','S1 Informatika','2021','source\\11S21050.png',0),('11S21051','Wilona Simbolon','S1 Informatika','2021','source\\11S21051.jpg',0),('11S21052','Emely Simanjuntak','S1 Informatika','2021','source\\11S21052.png',0),('11S21053','Grase Thessalonika Panjaitan','S1 Informatika','2021','source\\11S21053.png',0),('11S21054','Anni Simorangkir','S1 Informatika','2021','source\\11S21054.jpg',0),('11S21055','Gerry Bukit','S1 Informatika','2021','source\\11S21055.jpg',0),('12S22004','Bethania Hasibuan','S1 Sistem Informasi','2022','source\\12S22004.jpg',0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pinjam`
--

DROP TABLE IF EXISTS `pinjam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pinjam` (
  `book_id` varchar(20) DEFAULT NULL,
  `nim` varchar(20) DEFAULT NULL,
  `waktuPinjam` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `waktuKembali` varchar(200) DEFAULT NULL,
  UNIQUE KEY `book_id` (`book_id`),
  KEY `nim` (`nim`),
  CONSTRAINT `pinjam_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `pinjam_ibfk_2` FOREIGN KEY (`nim`) REFERENCES `member` (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pinjam`
--

LOCK TABLES `pinjam` WRITE;
/*!40000 ALTER TABLE `pinjam` DISABLE KEYS */;
INSERT INTO `pinjam` VALUES ('b1','11S21010','2022-11-29 01:21:22','2022-12-02');
/*!40000 ALTER TABLE `pinjam` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-29 10:08:01
