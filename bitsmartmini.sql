CREATE DATABASE  IF NOT EXISTS `bitsmartmini` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bitsmartmini`;
-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: bitsmartmini
-- ------------------------------------------------------
-- Server version	5.7.9

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `brand_name` varchar(200) NOT NULL,
  PRIMARY KEY (`brand_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES ('Bounty'),('Clorox'),('Colgate'),('Famous Amos'),('Goya'),('Hunts'),('Lipton'),('Martinellis '),('Nabisco'),('Ocean Spray'),('Pepsodent'),('Pringles'),('Quaker'),('Suavitel'),('Welchs');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business` (
  `b_name` varchar(145) NOT NULL,
  `b_addr` varchar(45) NOT NULL,
  `b_mobile` varchar(45) NOT NULL,
  `b_email` varchar(45) NOT NULL,
  `b_logo` varchar(245) DEFAULT NULL,
  `b_country` varchar(245) NOT NULL,
  `b_currency` varchar(45) NOT NULL,
  `license_key` int(11) NOT NULL,
  PRIMARY KEY (`b_name`),
  UNIQUE KEY `license_key` (`license_key`),
  CONSTRAINT `business_ibfk_1` FOREIGN KEY (`license_key`) REFERENCES `licensing` (`license_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

LOCK TABLES `business` WRITE;
/*!40000 ALTER TABLE `business` DISABLE KEYS */;
INSERT INTO `business` VALUES ('Bitstrybe Pharmacy Ltd','P.O Box MP2580, Accra','0246002100','info@bistrybe.com',NULL,'Ghana','GH¢',1);
/*!40000 ALTER TABLE `business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_name` varchar(250) NOT NULL,
  PRIMARY KEY (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('Beauty & Personal Care'),('Grocery'),('Grocery & Gourmet Food'),('Health and Beauty');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(145) NOT NULL,
  `mobile` varchar(145) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Anonymous','(000)0000000');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generic`
--

DROP TABLE IF EXISTS `generic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generic` (
  `gene_name` varchar(250) NOT NULL,
  PRIMARY KEY (`gene_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generic`
--

LOCK TABLES `generic` WRITE;
/*!40000 ALTER TABLE `generic` DISABLE KEYS */;
/*!40000 ALTER TABLE `generic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `upc` varchar(45) NOT NULL,
  `item_desc` varchar(200) NOT NULL,
  `item_img` varchar(245) NOT NULL,
  `category` varchar(150) NOT NULL,
  `brand` varchar(150) NOT NULL,
  `rol` int(11) NOT NULL DEFAULT '0',
  `cp` double NOT NULL,
  `sp` double NOT NULL,
  `users` int(11) NOT NULL,
  `entry_log` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`upc`),
  KEY `FK_ITEM_BRAND_idx` (`brand`),
  KEY `FK_ITEM_CAT_idx` (`category`),
  KEY `FK_ITEM_USER_idx` (`users`),
  CONSTRAINT `FK_ITEM_BRAND` FOREIGN KEY (`brand`) REFERENCES `brands` (`brand_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ITEM_CAT` FOREIGN KEY (`category`) REFERENCES `category` (`category_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ITEM_USER` FOREIGN KEY (`users`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('027000380116','Hunts Whole Tomatoes, 28 Ounce','./img/027000380116.jpg','Grocery & Gourmet Food','Hunts',10,83.78,108,1,'2021-02-05 02:37:00','2021-02-05 12:43:41'),('030000315828','Quaker Quick 1-Minute Oats','./img/030000315828.jpg','Grocery & Gourmet Food','Quaker',10,28.63,35.2,1,'2021-02-05 02:37:00','2021-02-05 12:15:15'),('031200203007','Ocean Spray Cranberry Juice Cocktail','./img/031200203007.jpg','Grocery & Gourmet Food','Goya',10,24.56,30,1,'2021-02-05 02:37:00','2021-02-05 12:14:55'),('033200000921','Pepsodent Complete Care Anticavity Flouride Toothpaste','./img/033200000921.jpg','Health and Beauty','Pepsodent',10,73.62,83.5,1,'2021-02-05 02:37:00','2021-02-05 11:20:29'),('035000393685','Suavitel Fast Dry Fabric Conditioner, Fabulous Field Flowers','./img/035000393685.jpg','Beauty & Personal Care','Suavitel',10,196.65,215,1,'2021-02-05 02:37:00','2021-02-05 12:39:26'),('035000783318','Colgate Kids Toothpaste Cavity Protection, Bubble Fruit','./img/030000315828.jpg','Beauty & Personal Care','Colgate',10,9.63,13.5,1,'2021-02-05 02:37:00','2021-02-05 12:34:07'),('038000182525','Pringles Potato Crisps Chips Variety Pack','./img/038000182525.jpg','Grocery & Gourmet Food','Pringles',10,71.76,85.5,1,'2021-02-05 02:37:00','2021-02-05 11:00:23'),('041000002878','Lipton Regular Tea Bag','./img/041000002878.jpg','Grocery & Gourmet Food','Lipton',10,4.43,5.6,1,'2021-02-05 12:01:41','2021-02-05 12:01:41'),('041244102334','Martinelli\'s Gold Medal® Organic Apple 100% Juice 10 Fl','./img/041244102334.jpg','Grocery & Gourmet Food','Martinellis ',10,7.63,9.65,1,'2021-02-05 11:47:56','2021-02-05 11:47:56'),('041331038270','Goya Adobo with Pepper 16.5 OZ','./img/041331038270.jpg','Grocery & Gourmet Food','Goya',10,40.1,55,1,'2021-02-05 02:37:00','2021-02-05 02:46:18'),('041800301959','Welchs Cranberry Cocktail Juice, 16 Fluid Ounce','./img/041800301959.jpg','Beauty & Personal Care','Welchs',10,156.74,204.63,1,'2021-02-05 02:37:00','2021-02-05 12:36:28'),('044600309668','Clorox Concentrated Germicidal Bleach','./img/044600309668.jpg','Health and Beauty','Clorox',10,51.74,56,1,'2021-02-05 02:37:00','2021-02-05 10:43:19'),('074305001321','Bragg Organic Raw Unfiltered Apple Cider Vinegar','./img/074305001321.jpg','Grocery & Gourmet Food','Famous Amos',10,150,157,1,'2021-02-05 02:37:00','2021-02-05 02:46:18'),('786173965055','Famous Amos Chocolate Chip Cookies','./img/786173965055.jpg','Grocery & Gourmet Food','Famous Amos',10,150,157,1,'2021-02-05 02:37:00','2021-02-05 02:46:18');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `licensing`
--

DROP TABLE IF EXISTS `licensing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `licensing` (
  `license_id` int(11) NOT NULL AUTO_INCREMENT,
  `license_key` varchar(2000) NOT NULL,
  PRIMARY KEY (`license_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `licensing`
--

LOCK TABLES `licensing` WRITE;
/*!40000 ALTER TABLE `licensing` DISABLE KEYS */;
INSERT INTO `licensing` VALUES (1,'otRjJjneiSafSIqqBkpBpkjWlFD9hq_6yQx3h3n2C9ikvvmYLeHTWpjyZ6zKWa6X-ylNbG6Purm21J4WJ6QaAg');
/*!40000 ALTER TABLE `licensing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `manufacturer` varchar(245) NOT NULL,
  PRIMARY KEY (`manufacturer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES ('21st Century'),('Aculife Healthcare Pvt Ltd'),('Amanta Healthcare'),('Arytons'),('Ascot'),('Aspar Pharmaceuticals'),('Axa Parentals'),('Ayrton'),('B Overgreens'),('Baseline'),('Bicon Pharmaceuticals'),('Bliss Gvs Pharma'),('Bragg'),('Bristol'),('Changsha Sinocare'),('Ciron Drugs'),('Co-Pharma'),('Crescent'),('Dannex'),('Denk'),('Erest Chemist'),('Ernest Chemist'),('Eskay'),('Exeter'),('Gb Pharma Limited'),('General Consumables'),('Genesis Pharmaceuticals'),('Golden Tower'),('Gopaldas Visram & Co'),('GR Pharma'),('Guilin Pharmaceutical'),('Hovid'),('innocia Lifesciences Pvt Ltd'),('Joramay Pharmacy'),('Kama Industries'),('Kinapharma'),('Kojack'),('Letap'),('M&G'),('Meyer'),('MGP'),('Mikado Laboratories'),('MVC Pharmaceuticals'),('Nirlife (Aculife)'),('OA & J Pharmaceuticals'),('Omer Investment Limited'),('Osons'),('Others'),('Pharm-Inter'),('Pharmanova'),('Phyto-Riker Pharmaceuticals Ltd'),('PokuPharma'),('Ronak Exim Pvt. Ltd'),('Shalina'),('Shandong Xier Kangtai Pharmaceuticals'),('Stedman Pharmaceuticals'),('Swiss Parenterals'),('Teva'),('Tobinco'),('Troge'),('UK'),('Unichem Industries'),('Unicom'),('Valupak'),('Xl Laboratories');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `receipt_id` int(11) NOT NULL,
  `receipt_date` date NOT NULL,
  `receipt_time` time NOT NULL,
  `sales_id` int(11) NOT NULL,
  `amount_paid` double NOT NULL,
  `pay_mode` varchar(25) NOT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `users` int(11) DEFAULT '0',
  `entry_log` datetime DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`receipt_id`),
  KEY `sales_id` (`sales_id`),
  KEY `users` (`users`),
  CONSTRAINT `FK_REC_SALES` FOREIGN KEY (`sales_id`) REFERENCES `sales` (`sales_id`),
  CONSTRAINT `FK_REC_USER` FOREIGN KEY (`users`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund_policy`
--

DROP TABLE IF EXISTS `refund_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refund_policy` (
  `refund_period` varchar(45) NOT NULL,
  `refund_period_val` int(11) NOT NULL,
  `refund_custom_msg` varchar(1045) NOT NULL,
  `refund_elog` datetime NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`refund_period`),
  KEY `FK_REFUND_USER_idx` (`user`),
  CONSTRAINT `FK_REFUND_USER` FOREIGN KEY (`user`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund_policy`
--

LOCK TABLES `refund_policy` WRITE;
/*!40000 ALTER TABLE `refund_policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `refund_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rtd_item`
--

DROP TABLE IF EXISTS `rtd_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rtd_item` (
  `salescode` int(11) NOT NULL,
  `rtd_date` date NOT NULL,
  `rtd_time` time NOT NULL,
  `rtd_qty` int(11) NOT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `users` int(11) NOT NULL,
  `entry_log` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`salescode`),
  KEY `users` (`users`),
  CONSTRAINT `FK_RTD_SLD` FOREIGN KEY (`salescode`) REFERENCES `sales_details` (`sales_details_id`),
  CONSTRAINT `FK_RTD_USER` FOREIGN KEY (`users`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rtd_item`
--

LOCK TABLES `rtd_item` WRITE;
/*!40000 ALTER TABLE `rtd_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `rtd_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `sales_id` int(11) NOT NULL,
  `sales_date` date NOT NULL,
  `users` int(11) NOT NULL,
  `customers` int(11) NOT NULL,
  `entry_date` date NOT NULL,
  PRIMARY KEY (`sales_id`),
  KEY `customers` (`customers`),
  KEY `sales_user_idx` (`users`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`users`) REFERENCES `users` (`userid`),
  CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`customers`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_details`
--

DROP TABLE IF EXISTS `sales_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_details` (
  `sales_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `sale_id` int(11) NOT NULL,
  `upc` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `cost_price` double NOT NULL,
  `sales_price` double NOT NULL,
  `discount` double NOT NULL DEFAULT '0',
  `entry_date` datetime DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sales_details_id`),
  KEY `sale_id` (`sale_id`),
  KEY `FK_SLD_ITEM_idx` (`upc`),
  CONSTRAINT `FK_SLD_ITEM` FOREIGN KEY (`upc`) REFERENCES `items` (`upc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SLD_SL` FOREIGN KEY (`sale_id`) REFERENCES `sales` (`sales_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_details`
--

LOCK TABLES `sales_details` WRITE;
/*!40000 ALTER TABLE `sales_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shelf`
--

DROP TABLE IF EXISTS `shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shelf` (
  `shelf_desc` varchar(250) NOT NULL,
  PRIMARY KEY (`shelf_desc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shelf`
--

LOCK TABLES `shelf` WRITE;
/*!40000 ALTER TABLE `shelf` DISABLE KEYS */;
/*!40000 ALTER TABLE `shelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockin`
--

DROP TABLE IF EXISTS `stockin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockin` (
  `stockin_id` int(11) NOT NULL,
  `stockin_date` date NOT NULL,
  `upc` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `expiry_date` date DEFAULT NULL,
  `users` int(11) NOT NULL,
  `entry_log` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`stockin_id`),
  KEY `users` (`users`),
  KEY `FK_STKIN_ITEM_idx` (`upc`),
  CONSTRAINT `FK_STKIN_ITEM` FOREIGN KEY (`upc`) REFERENCES `items` (`upc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STKIN_USER` FOREIGN KEY (`users`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockin`
--

LOCK TABLES `stockin` WRITE;
/*!40000 ALTER TABLE `stockin` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockout`
--

DROP TABLE IF EXISTS `stockout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockout` (
  `stockout_id` int(11) NOT NULL AUTO_INCREMENT,
  `stk_date` date NOT NULL,
  `upc` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `remarks` varchar(145) NOT NULL,
  `users` int(11) NOT NULL,
  `entry_log` datetime NOT NULL,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`stockout_id`),
  KEY `users` (`users`),
  KEY `FK_STKOUT_ITEM_idx` (`upc`),
  CONSTRAINT `FK_STKOUT_ITEM` FOREIGN KEY (`upc`) REFERENCES `items` (`upc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STKOUT_USER` FOREIGN KEY (`users`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockout`
--

LOCK TABLES `stockout` WRITE;
/*!40000 ALTER TABLE `stockout` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uom`
--

DROP TABLE IF EXISTS `uom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uom` (
  `uom_desc` varchar(50) NOT NULL,
  PRIMARY KEY (`uom_desc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uom`
--

LOCK TABLES `uom` WRITE;
/*!40000 ALTER TABLE `uom` DISABLE KEYS */;
INSERT INTO `uom` VALUES ('Ampoule'),('Bottle'),('Box'),('Jacket'),('Pack'),('Strips'),('Tablet'),('Vial');
/*!40000 ALTER TABLE `uom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlogs`
--

DROP TABLE IF EXISTS `userlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userlogs` (
  `logsid` int(11) NOT NULL AUTO_INCREMENT,
  `username` int(11) NOT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `logout_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`logsid`),
  KEY `username` (`username`),
  CONSTRAINT `userlogs_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogs`
--

LOCK TABLES `userlogs` WRITE;
/*!40000 ALTER TABLE `userlogs` DISABLE KEYS */;
INSERT INTO `userlogs` VALUES (1,1,'2021-02-02 17:43:21','2021-02-02 17:43:57'),(2,1,'2021-02-02 17:44:51','2021-02-02 17:47:48'),(3,1,'2021-02-02 18:13:33','2021-02-02 18:43:21'),(4,3,'2021-02-03 07:05:56','2021-02-03 07:06:02'),(5,3,'2021-02-03 07:06:49',NULL),(6,3,'2021-02-03 07:10:04','2021-02-03 07:10:25'),(7,3,'2021-02-03 07:20:23',NULL),(8,3,'2021-02-03 07:21:36',NULL),(9,3,'2021-02-03 07:23:41',NULL),(10,3,'2021-02-03 07:42:25',NULL),(11,3,'2021-02-03 07:45:36',NULL),(12,3,'2021-02-03 07:47:25',NULL),(13,3,'2021-02-03 07:48:29','2021-02-03 07:49:10'),(14,3,'2021-02-03 07:50:58','2021-02-03 07:51:26'),(15,3,'2021-02-03 07:52:42','2021-02-03 07:52:55'),(16,3,'2021-02-03 07:56:23','2021-02-03 07:56:33'),(17,3,'2021-02-03 07:59:00','2021-02-03 07:59:32'),(18,3,'2021-02-03 08:01:35',NULL),(19,3,'2021-02-03 08:02:28',NULL),(20,3,'2021-02-03 08:13:23',NULL),(21,3,'2021-02-03 08:16:17',NULL),(22,3,'2021-02-03 08:19:35',NULL),(23,3,'2021-02-03 08:22:16',NULL),(24,3,'2021-02-03 08:33:30',NULL),(25,3,'2021-02-03 08:34:58',NULL),(26,3,'2021-02-03 08:36:47',NULL),(27,3,'2021-02-03 08:39:10',NULL),(28,3,'2021-02-03 08:41:49',NULL),(29,3,'2021-02-03 09:02:06',NULL),(30,3,'2021-02-03 09:26:23',NULL),(31,3,'2021-02-03 09:27:29',NULL),(32,3,'2021-02-03 09:30:40',NULL),(33,3,'2021-02-03 09:35:41',NULL),(34,3,'2021-02-03 09:37:21',NULL),(35,3,'2021-02-03 09:45:20',NULL),(36,3,'2021-02-03 09:52:29',NULL),(37,3,'2021-02-03 09:57:29',NULL),(38,3,'2021-02-03 09:59:19','2021-02-03 10:00:46'),(39,3,'2021-02-03 10:26:21',NULL),(40,3,'2021-02-03 10:27:43','2021-02-03 10:27:58'),(41,3,'2021-02-03 10:44:36',NULL),(42,3,'2021-02-03 10:45:25','2021-02-03 10:48:00'),(43,3,'2021-02-03 10:48:17',NULL),(44,3,'2021-02-03 10:50:31',NULL),(45,3,'2021-02-03 10:51:55',NULL),(46,1,'2021-02-03 15:21:44',NULL),(47,1,'2021-02-03 15:23:36',NULL),(48,1,'2021-02-03 15:24:28',NULL),(49,1,'2021-02-03 15:28:00',NULL),(50,1,'2021-02-03 15:30:03',NULL),(51,1,'2021-02-03 15:32:45',NULL),(52,1,'2021-02-03 15:34:45',NULL),(53,1,'2021-02-03 15:38:45',NULL),(54,1,'2021-02-03 15:44:32',NULL),(55,1,'2021-02-03 15:57:23','2021-02-03 15:57:36'),(56,1,'2021-02-03 15:58:52',NULL),(57,3,'2021-02-03 16:01:00',NULL),(58,1,'2021-02-03 16:04:01','2021-02-03 16:04:08'),(59,3,'2021-02-03 16:04:13',NULL),(60,1,'2021-02-03 16:04:57','2021-02-03 16:05:35'),(61,1,'2021-02-03 16:06:32',NULL),(62,1,'2021-02-03 16:09:05',NULL),(63,1,'2021-02-03 16:10:33',NULL),(64,1,'2021-02-03 16:11:29','2021-02-03 16:11:46'),(65,3,'2021-02-03 16:11:52',NULL),(66,1,'2021-02-03 16:14:25','2021-02-03 16:15:22'),(67,3,'2021-02-03 16:15:27',NULL),(68,3,'2021-02-03 16:17:39',NULL),(69,1,'2021-02-03 16:23:49','2021-02-03 16:24:11'),(70,3,'2021-02-03 16:24:17','2021-02-03 16:24:21'),(71,3,'2021-02-03 16:27:03',NULL),(72,3,'2021-02-03 16:29:42','2021-02-03 16:30:01'),(73,1,'2021-02-03 16:30:05',NULL),(74,1,'2021-02-03 16:34:41','2021-02-03 16:38:51'),(75,1,'2021-02-03 16:39:39','2021-02-03 16:39:58'),(76,3,'2021-02-03 16:40:09',NULL),(77,3,'2021-02-03 16:41:31','2021-02-03 16:44:05'),(78,1,'2021-02-03 16:44:18',NULL),(79,1,'2021-02-03 16:50:34',NULL),(80,1,'2021-02-03 16:52:16',NULL),(81,1,'2021-02-03 16:53:48','2021-02-03 16:53:57'),(82,1,'2021-02-03 16:54:02',NULL),(83,1,'2021-02-03 16:59:38',NULL),(84,3,'2021-02-03 17:07:35','2021-02-03 17:07:46'),(85,1,'2021-02-03 17:07:50',NULL),(86,1,'2021-02-03 17:08:47',NULL),(87,1,'2021-02-03 17:24:05',NULL),(88,1,'2021-02-03 17:26:50',NULL),(89,1,'2021-02-03 17:41:17',NULL),(90,3,'2021-02-03 17:54:35',NULL),(91,1,'2021-02-03 18:07:06','2021-02-03 18:07:20'),(92,3,'2021-02-03 18:07:26',NULL),(93,3,'2021-02-03 18:10:33','2021-02-04 07:54:34'),(94,3,'2021-02-04 08:39:48','2021-02-04 08:40:01'),(95,1,'2021-02-04 08:40:06','2021-02-04 08:45:20'),(96,3,'2021-02-04 08:45:28','2021-02-04 08:48:22'),(97,3,'2021-02-04 08:51:30',NULL),(98,3,'2021-02-04 08:57:44','2021-02-04 08:58:43'),(99,3,'2021-02-04 08:59:35','2021-02-04 09:02:50'),(100,1,'2021-02-04 09:07:14','2021-02-04 09:08:19'),(101,3,'2021-02-04 09:11:45',NULL),(102,3,'2021-02-04 09:16:13','2021-02-04 09:17:56'),(103,3,'2021-02-04 09:18:36','2021-02-04 09:19:41'),(104,3,'2021-02-04 09:21:46',NULL),(105,3,'2021-02-04 09:31:09',NULL),(106,3,'2021-02-04 09:34:29','2021-02-04 09:42:44'),(107,3,'2021-02-04 09:43:55',NULL),(108,3,'2021-02-04 10:30:20',NULL),(109,3,'2021-02-04 10:33:57',NULL),(110,3,'2021-02-04 10:35:04','2021-02-04 10:35:16'),(111,3,'2021-02-04 10:37:58','2021-02-04 10:38:08'),(112,3,'2021-02-04 10:38:48','2021-02-04 10:39:00'),(113,3,'2021-02-04 10:40:07','2021-02-04 10:40:15'),(114,3,'2021-02-04 10:42:04',NULL),(115,3,'2021-02-04 10:44:28',NULL),(116,3,'2021-02-04 10:48:31','2021-02-04 10:49:20'),(117,3,'2021-02-04 10:49:49',NULL),(118,3,'2021-02-04 10:50:44','2021-02-04 10:56:54'),(119,3,'2021-02-04 10:57:14',NULL),(120,1,'2021-02-04 11:37:44',NULL),(121,1,'2021-02-04 14:26:11','2021-02-04 14:36:16'),(122,1,'2021-02-04 17:06:57',NULL),(123,1,'2021-02-04 19:55:43','2021-02-04 19:57:30'),(124,1,'2021-02-04 19:58:27','2021-02-04 19:59:38'),(125,1,'2021-02-04 19:59:57','2021-02-04 20:03:33'),(126,1,'2021-02-04 20:21:30',NULL),(127,1,'2021-02-04 20:25:04',NULL),(128,1,'2021-02-04 20:26:29',NULL),(129,1,'2021-02-04 20:42:26','2021-02-04 20:43:26'),(130,1,'2021-02-04 20:43:40',NULL),(131,1,'2021-02-04 20:48:46',NULL),(132,1,'2021-02-04 20:53:15',NULL),(133,1,'2021-02-04 20:57:54',NULL),(134,1,'2021-02-04 21:04:20',NULL),(135,1,'2021-02-04 21:10:30',NULL),(136,1,'2021-02-04 21:12:15',NULL),(137,1,'2021-02-04 21:16:31',NULL),(138,1,'2021-02-04 22:59:50','2021-02-05 00:01:39'),(139,1,'2021-02-05 02:32:55','2021-02-05 02:33:05'),(140,1,'2021-02-05 02:48:49',NULL),(141,1,'2021-02-05 03:00:24','2021-02-05 03:01:50'),(142,1,'2021-02-05 03:20:23','2021-02-05 03:22:03'),(143,1,'2021-02-05 03:22:27',NULL),(144,1,'2021-02-05 04:12:51','2021-02-05 04:15:21'),(145,1,'2021-02-05 04:15:40',NULL),(146,1,'2021-02-05 10:07:18',NULL),(147,1,'2021-02-05 10:19:32','2021-02-05 10:19:37'),(148,1,'2021-02-05 10:21:52','2021-02-05 10:22:21'),(149,1,'2021-02-05 10:22:25',NULL),(150,1,'2021-02-05 10:34:28',NULL),(151,1,'2021-02-05 10:37:06',NULL),(152,1,'2021-02-05 10:38:30','2021-02-05 10:38:59'),(153,1,'2021-02-05 10:50:14',NULL),(154,1,'2021-02-05 10:51:43','2021-02-05 10:52:17'),(155,1,'2021-02-05 10:52:37',NULL),(156,1,'2021-02-05 11:00:43',NULL),(157,1,'2021-02-05 11:02:07',NULL),(158,1,'2021-02-05 11:03:05',NULL),(159,1,'2021-02-05 11:04:02','2021-02-05 11:04:26'),(160,1,'2021-02-05 11:12:53',NULL),(161,1,'2021-02-05 11:33:14',NULL),(162,1,'2021-02-05 11:36:36','2021-02-05 11:39:17'),(163,1,'2021-02-05 11:39:34','2021-02-05 11:44:07'),(164,1,'2021-02-05 11:47:21','2021-02-05 11:54:41'),(165,1,'2021-02-05 11:57:08','2021-02-05 12:02:15'),(166,1,'2021-02-05 12:15:56','2021-02-05 12:24:03'),(167,1,'2021-02-05 12:44:12',NULL),(168,1,'2021-02-05 12:47:16',NULL),(169,1,'2021-02-05 12:48:46',NULL),(170,1,'2021-02-05 12:58:43',NULL),(171,1,'2021-02-05 13:04:00',NULL),(172,1,'2021-02-05 13:05:26','2021-02-05 13:11:12'),(173,1,'2021-02-05 13:17:34','2021-02-05 13:17:58');
/*!40000 ALTER TABLE `userlogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `fname` varchar(145) NOT NULL,
  `lname` varchar(145) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `roles` varchar(100) NOT NULL,
  `date_created` date NOT NULL,
  `modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pwd_status` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Mr','Kofi','Moses','0246002100','kofim@gmail.com','kofi','ab55a9ef5cdd19db785e11794c9c7b9c','Administrator','2020-02-11','2020-05-05 07:18:19',1,1),(2,'Mr','Andrews','Phil','0270900032','andrews@gmail.com','andrews','ab55a9ef5cdd19db785e11794c9c7b9c','Supervisor','2020-02-11','2020-11-24 08:15:22',1,1),(3,'Miss','Venessa','Blue','0000000000','jb@gmail.com','vanesa','ab55a9ef5cdd19db785e11794c9c7b9c','Sales','2020-02-11','2020-11-24 10:18:31',1,1),(4,'Miss','Sarah','Jay','0000000000','Sarah@gmail.com','saraj','ab55a9ef5cdd19db785e11794c9c7b9c','Sales','2020-02-11','2020-05-05 07:18:19',0,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bitsmartmini'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-05 13:18:43
