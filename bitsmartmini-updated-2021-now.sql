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
-- Table structure for table `backup_log`
--

DROP TABLE IF EXISTS `backup_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `backup_log` (
  `backup_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `backup_log_stamp` datetime NOT NULL,
  PRIMARY KEY (`backup_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backup_log`
--

LOCK TABLES `backup_log` WRITE;
/*!40000 ALTER TABLE `backup_log` DISABLE KEYS */;
INSERT INTO `backup_log` VALUES (1,'2021-02-24 18:01:52'),(2,'2021-02-24 20:29:22'),(3,'2021-02-26 15:51:21');
/*!40000 ALTER TABLE `backup_log` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `brands` VALUES ('Air Wick'),('Bounty'),('Cadbury'),('Clorox'),('Colgate'),('Famous Amos'),('Goya'),('Heaven'),('Hunts'),('Lipton'),('Martinellis '),('Member\'s Mark'),('N\'joy'),('Nabisco'),('Ocean Spray'),('Pepsodent'),('Pringles'),('Quaker'),('Suavitel'),('Welchs');
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
  `b_mobile_1` varchar(45) DEFAULT NULL,
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
INSERT INTO `business` VALUES ('Kardy Ventures','P.O.Box AF1944 Adenta-Accra','0544133196','0208129348','stephanieofancher@gmail.com','.\\img\\LOGO.jpg','Ghana','GH¢',1);
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
INSERT INTO `category` VALUES ('Beauty & Personal Care'),('Dairy'),('Grocery'),('Grocery & Gourmet Food'),('Health and Beauty'),('Health and Beautyz');
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
  CONSTRAINT `FK_ITEM_BRAND` FOREIGN KEY (`brand`) REFERENCES `brands` (`brand_name`),
  CONSTRAINT `FK_ITEM_CAT` FOREIGN KEY (`category`) REFERENCES `category` (`category_name`),
  CONSTRAINT `FK_ITEM_USER` FOREIGN KEY (`users`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('027000380116','Hunts Whole Tomatoes, 28 Ounce','./img/027000380116.jpg','Grocery & Gourmet Food','Hunts',10,83.78,108,1,'2021-02-05 02:37:00','2021-02-05 12:43:41'),('030000315828','Quaker Quick 1-Minute Oats','./img/030000315828.jpg','Grocery & Gourmet Food','Quaker',10,28.63,35.2,1,'2021-02-05 02:37:00','2021-02-05 12:15:15'),('031200203007','Ocean Spray Cranberry Juice Cocktail','./img/031200203007.jpg','Grocery & Gourmet Food','Goya',10,24.56,30,1,'2021-02-05 02:37:00','2021-02-05 12:14:55'),('033200000921','Pepsodent Complete Care Anticavity Flouride Toothpaste','./img/033200000921.jpg','Health and Beauty','Pepsodent',10,73.62,83.5,1,'2021-02-05 02:37:00','2021-02-05 11:20:29'),('035000393685','Suavitel Fast Dry Fabric Conditioner, Fabulous Field Flowers','./img/035000393685.jpg','Beauty & Personal Care','Suavitel',10,196.65,215,1,'2021-02-05 02:37:00','2021-02-05 12:39:26'),('035000783318','Colgate Kids Toothpaste Cavity Protection, Bubble Fruit','./img/035000783318.jpg','Beauty & Personal Care','Colgate',10,9.63,13.5,1,'2021-02-05 02:37:00','2021-02-06 16:39:29'),('038000182525','Pringles Potato Crisps Chips Variety Pack','./img/038000182525.jpg','Grocery & Gourmet Food','Pringles',10,71.76,85.5,1,'2021-02-05 02:37:00','2021-02-05 11:00:23'),('041000002878','Lipton Regular Tea Bag','./img/041000002878.jpg','Grocery & Gourmet Food','Lipton',10,4.43,5.6,1,'2021-02-05 12:01:41','2021-02-05 12:01:41'),('041244102334','Martinelli\'s Gold Medal® Organic Apple 100% Juice 10 Fl','./img/041244102334.jpg','Grocery & Gourmet Food','Martinellis ',10,7.63,9.65,1,'2021-02-05 11:47:56','2021-02-05 11:47:56'),('041331038270','Goya Adobo with Pepper 16.5 OZ','./img/041331038270.jpg','Grocery & Gourmet Food','Goya',10,40.1,55,1,'2021-02-05 02:37:00','2021-02-05 02:46:18'),('041800301959','Welchs Cranberry Cocktail Juice, 16 Fluid Ounce','./img/041800301959.jpg','Beauty & Personal Care','Welchs',10,156.74,204.63,1,'2021-02-05 02:37:00','2021-02-05 12:36:28'),('044600309668','Clorox Concentrated Germicidal Bleach','./img/044600309668.jpg','Health and Beauty','Clorox',10,51.74,56,1,'2021-02-05 02:37:00','2021-02-05 10:43:19'),('074305001321','Bragg Organic Raw Unfiltered Apple Cider Vinegar','./img/074305001321.jpg','Grocery & Gourmet Food','Famous Amos',10,150,157,1,'2021-02-05 02:37:00','2021-02-05 02:46:18'),('078742292984','Member\'s Mark Thai Jasmine Rice (25 lb.)','.\\img\\078742292984.png','Grocery & Gourmet Food','Member\'s Mark',3,145,170,1,'2021-02-24 20:07:41','2021-02-24 20:07:41'),('086631008493','N\'joy Coffee Creamer','.\\img\\086631008493.jpg','Dairy','N\'joy',8,9,16,1,'2021-02-24 20:25:49','2021-02-24 20:25:49'),('3574669909129','Johnsons Baby Oil 300ml','./img/3574669909129.jpg','Health And Beauty','Goya',10,14.72,17,1,'2021-02-10 21:34:26','2021-02-10 21:34:26'),('5059001001368','Air Wick Room Spray 250ml - Soft Cotton PMP','./img/5059001001368.jpg','Health And Beauty','Air Wick',5,18.09,22,1,'2021-02-10 21:29:22','2021-02-10 21:30:55'),('6034000053623','Heaven Insecticide Spray Plus','./img/6034000053623.jpg','Health And Beauty','Lipton',25,7.85,12,1,'2021-02-10 20:25:19','2021-02-10 20:25:19'),('7622010000790','Cadbury Hot Chocolate Drink 3 in 1','./img/7622010000790.jpg','Grocery & Gourmet Food','Cadbury',50,6.5,1,1,'2021-02-10 20:14:41','2021-02-10 20:24:59'),('786173965055','Famous Amos Chocolate Chip Cookies','./img/786173965055.jpg','Grocery & Gourmet Food','Famous Amos',10,150,157,1,'2021-02-05 02:37:00','2021-02-05 02:46:18');
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
INSERT INTO `licensing` VALUES (1,'6rNt4vUJpfzrLXF3NVfiAqsPqlyErGL3F4kPgDEVOWaDNBFbhjFJufutpSx_if07OaybRZ85hDzJeypCyOTLrw');
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
INSERT INTO `manufacturer` VALUES (''),('21st Century'),('Aculife Healthcare Pvt Ltd'),('Amanta Healthcare'),('Arytons'),('Ascot'),('Aspar Pharmaceuticals'),('Axa Parentals'),('Ayrton'),('B Overgreens'),('Baseline'),('Bicon Pharmaceuticals'),('Bliss Gvs Pharma'),('Bragg'),('Bristol'),('Cadbury'),('Changsha Sinocare'),('Ciron Drugs'),('Co-Pharma'),('Crescent'),('Dannex'),('Denk'),('Erest Chemist'),('Ernest Chemist'),('Eskay'),('Exeter'),('Gb Pharma Limited'),('General Consumables'),('Genesis Pharmaceuticals'),('Golden Tower'),('Gopaldas Visram & Co'),('GR Pharma'),('Guilin Pharmaceutical'),('Hovid'),('innocia Lifesciences Pvt Ltd'),('Joramay Pharmacy'),('Kama Industries'),('Kinapharma'),('Kojack'),('Letap'),('M&G'),('Meyer'),('MGP'),('Mikado Laboratories'),('MVC Pharmaceuticals'),('Nirlife (Aculife)'),('OA & J Pharmaceuticals'),('Omer Investment Limited'),('Osons'),('Others'),('Pharm-Inter'),('Pharmanova'),('Phyto-Riker Pharmaceuticals Ltd'),('PokuPharma'),('Ronak Exim Pvt. Ltd'),('Shalina'),('Shandong Xier Kangtai Pharmaceuticals'),('Stedman Pharmaceuticals'),('Swiss Parenterals'),('Teva'),('Tobinco'),('Troge'),('UK'),('Unichem Industries'),('Unicom'),('Valupak'),('Xl Laboratories');
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
  `return_policy` varchar(545) DEFAULT NULL,
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
  CONSTRAINT `FK_REFUND_USER` FOREIGN KEY (`user`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund_policy`
--

LOCK TABLES `refund_policy` WRITE;
/*!40000 ALTER TABLE `refund_policy` DISABLE KEYS */;
INSERT INTO `refund_policy` VALUES ('hour',24,'Based on our return policy, we only allow items to be returned within ? of purchase. So we strongly advise to check the items before leaving. Thank you.','2021-02-11 01:44:41',1);
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
  CONSTRAINT `FK_SLD_ITEM` FOREIGN KEY (`upc`) REFERENCES `items` (`upc`),
  CONSTRAINT `FK_SLD_SL` FOREIGN KEY (`sale_id`) REFERENCES `sales` (`sales_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  CONSTRAINT `FK_STKIN_ITEM` FOREIGN KEY (`upc`) REFERENCES `items` (`upc`),
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
  CONSTRAINT `FK_STKOUT_ITEM` FOREIGN KEY (`upc`) REFERENCES `items` (`upc`),
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogs`
--

LOCK TABLES `userlogs` WRITE;
/*!40000 ALTER TABLE `userlogs` DISABLE KEYS */;
INSERT INTO `userlogs` VALUES (1,1,'2021-02-24 17:38:03','2021-02-24 21:53:41'),(3,1,'2021-02-24 20:05:51',NULL),(5,1,'2021-02-24 21:54:19',NULL),(6,1,'2021-02-25 07:43:22',NULL),(7,1,'2021-02-25 20:21:28',NULL),(8,1,'2021-02-26 15:43:40','2021-02-26 15:48:05'),(9,6,'2021-02-26 15:48:21',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Mr','Kofi','Moses','0246002100','kofim@gmail.com','kofi','ab55a9ef5cdd19db785e11794c9c7b9c','Administrator','2020-02-11','2020-05-05 07:18:19',1,1),(6,'Mr','mark','simpini','0248815514','marxpszalez5009@gmail.com','mark','9aa04a351cba882fdd4ad734a0ca533d','Sales','2021-02-26','2021-02-26 15:49:42',1,1),(7,'Miss','Stephanie','Ofancher','0544133196','stephanieofancher@gmail.com','steph','ab55a9ef5cdd19db785e11794c9c7b9c','Administrator','2021-02-26','2021-02-26 15:49:42',0,NULL);
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

-- Dump completed on 2021-02-26 16:03:26
