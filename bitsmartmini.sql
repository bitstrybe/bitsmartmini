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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backup_log`
--

LOCK TABLES `backup_log` WRITE;
/*!40000 ALTER TABLE `backup_log` DISABLE KEYS */;
INSERT INTO `backup_log` VALUES (1,'2021-02-24 18:01:52'),(2,'2021-02-24 20:29:22'),(3,'2021-02-26 15:51:21'),(4,'2021-02-26 18:52:45'),(5,'2021-02-28 11:27:04'),(6,'2021-02-28 20:55:57'),(7,'2021-02-28 20:56:10'),(8,'2021-03-01 20:22:08'),(9,'2021-03-02 18:48:50'),(10,'2021-03-05 15:10:56'),(11,'2021-03-05 16:05:11'),(12,'2021-03-27 19:52:15'),(13,'2021-04-01 21:14:23'),(14,'2021-04-01 21:46:47'),(15,'2021-04-02 18:43:00'),(16,'2021-04-02 21:49:45'),(17,'2021-04-03 19:00:45'),(18,'2021-04-05 07:14:46'),(19,'2021-04-05 07:15:05'),(20,'2021-04-05 11:16:16'),(21,'2021-04-05 11:16:22'),(22,'2021-04-05 13:04:16'),(23,'2021-04-05 16:45:48'),(24,'2021-04-12 19:19:55'),(25,'2021-04-12 21:51:48'),(26,'2021-04-12 22:03:25'),(27,'2021-04-15 15:12:58'),(28,'2021-04-15 15:13:22'),(29,'2021-04-20 21:38:52'),(30,'2021-04-20 21:39:38'),(31,'2021-04-21 18:06:41'),(32,'2021-04-21 19:26:26'),(33,'2021-04-21 19:28:43'),(34,'2021-04-21 21:15:47'),(35,'2021-04-24 21:16:09'),(36,'2021-04-26 20:51:19'),(37,'2021-05-09 11:14:49'),(38,'2021-05-19 15:15:54');
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
INSERT INTO `brands` VALUES (''),('613008724221'),('Abbott'),('Air Wick'),('Apple&eve'),('Arizona'),('Bel-aqua'),('Bigelow'),('Bounty'),('Bragg'),('Cadbury'),('Caprisun'),('Cheerios'),('Clorox'),('Coca-cola'),('Colgate'),('Contadina'),('Ensure'),('Even Milk'),('Fabuloso'),('Famous Amos'),('Gerber'),('Glade'),('Goya'),('Great Value'),('Heaven'),('Heinz'),('Honey Nut'),('Hungry Jack'),('Hunts'),('Irish Spring'),('Jergens'),('Kellog\'s'),('Kirklannd'),('Lipton'),('Listerine'),('Malta Guiness'),('Martinellis '),('Member\'s Mark'),('Milo '),('N\'joy'),('Nabisco'),('Nature Valley'),('Nestle Coffee Mate'),('Ocean Spray'),('Olay'),('Oreo'),('Pepsodent'),('Pringles'),('Purina'),('Quaker'),('Realmon'),('Skippy'),('Snapple'),('Suavitel'),('Thai Hom Mali'),('Unilever'),('Vinegarette'),('Voltic'),('Welch\'s'),('Wesson Canola'),('Ziploc');
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
INSERT INTO `category` VALUES ('Beauty & Personal Care'),('Dairy'),('Drinks'),('Flakes'),('Grocery'),('Grocery & Gourmet Food'),('Health and Beauty'),('Health and Beautyz'),('Snack'),('Tea');
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
  `uomset` varchar(45) DEFAULT NULL,
  `users` int(11) NOT NULL,
  `entry_log` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`upc`),
  KEY `FK_ITEM_BRAND_idx` (`brand`),
  KEY `FK_ITEM_CAT_idx` (`category`),
  KEY `FK_ITEM_USER_idx` (`users`),
  KEY `FK_ITEM_UOMSET_idx` (`uomset`),
  CONSTRAINT `FK_ITEM_BRAND` FOREIGN KEY (`brand`) REFERENCES `brands` (`brand_name`),
  CONSTRAINT `FK_ITEM_CAT` FOREIGN KEY (`category`) REFERENCES `category` (`category_name`),
  CONSTRAINT `FK_ITEM_UOMSET` FOREIGN KEY (`uomset`) REFERENCES `uom_set` (`uom_set_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ITEM_USER` FOREIGN KEY (`users`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (' 074305001161','Bragg Apple Cider Vinegar Small','074305001161.jpg','Health and Beauty','Bragg',12,28.5,42,NULL,7,'2021-02-28 16:44:35','2021-05-25 14:18:08'),('013000572408','Heinz Tomato Sauce','013000572408.jpg','Grocery & Gourmet Food','Heinz',6,32,58,NULL,7,'2021-03-01 19:20:01','2021-05-25 14:18:08'),('014800582284','ReaLemon Juice 100%','014800582284.jpg','Drinks','Realmon',8,22,40,NULL,7,'2021-02-28 18:27:41','2021-05-25 14:18:08'),('015000102104','Gerber  Yoghurt','015000102104.jpg','Dairy','Gerber',10,60,80,NULL,7,'2021-04-21 18:05:46','2021-05-25 14:18:08'),('016000264694','Nature Valley Crunchy','016000264694.jpg','Snack','Nature Valley',49,1.87,4,NULL,7,'2021-03-01 19:05:10','2021-05-25 14:18:08'),('016000486652','Honey Nut Cherios','016000486652.jpg','Flakes','Cheerios',5,29,40,NULL,7,'2021-02-28 18:36:27','2021-05-25 14:18:08'),('017800177382','Dog Chow Purina','017800177382.jpg','Snack','Purina',2,198,230,NULL,7,'2021-03-01 19:58:41','2021-05-25 14:18:08'),('019100257191','Jergens Ultra Healing','019100257191.jpg','Beauty & Personal Care','Jergens',5,80,130,NULL,7,'2021-02-28 18:03:02','2021-05-25 14:18:08'),('024000356875','Contadina Tomato Sauce','024000356875.jpg','Grocery & Gourmet Food','Contadina',6,32,58,NULL,7,'2021-03-01 19:18:17','2021-05-25 14:18:08'),('025700148876','Ziploc','025700148876.jpg','Grocery & Gourmet Food','Ziploc',10,31,55,NULL,1,'2021-03-05 15:55:43','2021-05-25 14:18:08'),('027000380116','Hunts Whole Tomatoes, 28 Ounce','027000380116.jpg','Grocery & Gourmet Food','Hunts',10,83.78,108,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('027000390610','Hunts Tomato Sauce','027000390610.jpg','Grocery & Gourmet Food','Hunts',6,32,58,NULL,7,'2021-03-01 19:22:26','2021-05-25 14:18:08'),('027000690659','Wesson Canola Oil','027000690659.jpg','Grocery & Gourmet Food','Wesson Canola',8,60,80,NULL,7,'2021-02-28 19:52:56','2021-05-25 14:18:08'),('030000315828','Quaker Quick 1-Minute Oats','030000315828.jpg','Grocery & Gourmet Food','Quaker',10,28.63,35.2,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('030000432938','Quaker Oats Old Fashioned ','030000432938.jpg','Grocery','Quaker',12,25,35,NULL,7,'2021-02-28 17:25:27','2021-05-25 14:18:08'),('031200009906','Ocean Spray Cranberry Bottle','DEFAULT.png','Drinks','Ocean Spray',12,6.33,11,NULL,7,'2021-04-02 19:06:33','2021-05-25 14:18:08'),('031200203007','Ocean Spray Cranberry Juice Cocktail','031200203007.jpg','Grocery & Gourmet Food','Goya',10,24.56,30,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('031200467768','CRANBERRY CLASSIC 1 LITRE','031200467768.jpg','Drinks','Ocean Spray',12,9.16,15,NULL,7,'2021-04-02 12:37:50','2021-05-25 14:18:08'),('033200000914','Pepsodent Enamel-Safe Whitening','033200000914.jpg','Beauty & Personal Care','Pepsodent',24,6.04,10,NULL,7,'2021-03-01 18:42:40','2021-05-25 14:18:08'),('033200000921','PEPSODENT','033200000921.jpg','Beauty & Personal Care','Pepsodent',24,6.04,10,NULL,7,'2021-03-27 19:52:10','2021-05-25 14:18:08'),('034856890089','Welch Fruit Snacks','034856890089.jpg','Snack','Welch\'s',5,92,120,NULL,7,'2021-02-28 19:24:40','2021-05-25 14:18:08'),('035000393685','Suavitel Fast Dry Fabric Conditioner, Fabulous Field Flowers','035000393685.jpg','Beauty & Personal Care','Suavitel',10,196.65,215,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('035000447753','Fabuloso Passion Of Fruit','035000447753.jpg','Health and Beauty','Fabuloso',6,62,98,NULL,6,'2021-03-01 17:26:35','2021-05-25 14:18:08'),('035000466525','Irish Spring Bar Soap','035000466525.jpg','Beauty & Personal Care','Irish Spring',4,60,80,NULL,7,'2021-03-01 18:38:26','2021-05-25 14:18:08'),('035000531230','Fabuloso Lavender','035000531230.jpg','Health and Beauty','Fabuloso',11,62,98,NULL,7,'2021-03-01 18:24:01','2021-05-25 14:18:08'),('035000783318','Colgate Kids Toothpaste Cavity Protection, Bubble Fruit','035000783318.jpg','Beauty & Personal Care','Colgate',10,9.63,13.5,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('037000974642','OLAY Ultra Moisture Shea Butter','037000974642.jpg','Beauty & Personal Care','Olay',8,31.5,52,NULL,7,'2021-02-28 19:01:11','2021-05-25 14:18:08'),('037000983972','Bounty','037000983972.jpg','Beauty & Personal Care','Bounty',12,13.75,17.5,NULL,7,'2021-03-01 18:28:29','2021-05-25 14:18:08'),('037600106825','Skippy Creamy','037600106825.jpg','Grocery & Gourmet Food','Skippy',6,43,65,NULL,7,'2021-03-01 19:00:12','2021-05-25 14:18:08'),('038000138416','Pringles large Original','038000138416.jpg','Grocery & Gourmet Food','Pringles',14,9.64,13.5,NULL,7,'2021-02-28 16:12:25','2021-05-25 14:18:08'),('038000138430','Pringles LARGE sour cream& onion','038000138430.jpg','Grocery','Pringles',14,9.64,13.5,NULL,7,'2021-02-28 15:49:32','2021-05-25 14:18:08'),('038000138577','Pringles Large Cheddar Cheese','038000138577.jpg','Grocery & Gourmet Food','Pringles',14,9.64,13.5,NULL,7,'2021-02-28 16:17:33','2021-05-25 14:18:08'),('038000138638','Pringles Large','038000138638.png','Grocery','Pringles',14,9.64,13.5,NULL,7,'2021-02-28 16:04:45','2021-05-25 14:18:08'),('038000146367','Kellogg\'s Frosted Flakes','038000146367.jpg','Flakes','Kellog\'s',5,58,83,NULL,7,'2021-02-28 18:47:52','2021-05-25 14:18:08'),('038000149917','Pringles Variety 48 Pack','038000149917.jpg','Snack','Pringles',5,94,120,NULL,7,'2021-03-01 19:50:35','2021-05-25 14:18:08'),('038000182525','Pringles Potato Crisps Chips Variety Pack','038000182525.jpg','Grocery & Gourmet Food','Pringles',10,71.76,85.5,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('038000183690','Pringles Medium BBQ','038000183690.jpg','Grocery & Gourmet Food','Pringles',12,5.42,7.5,NULL,7,'2021-02-28 16:37:15','2021-05-25 14:18:08'),('038000845246','Pringles Medium Original','038000845246.jpg','Snack','Pringles',12,5.41,7.5,NULL,7,'2021-03-01 18:56:26','2021-05-25 14:18:08'),('038000845253','Pringles Medium Sour Cream & Onion','038000845253.jpg','Grocery & Gourmet Food','Pringles',12,5.42,7.5,NULL,7,'2021-02-28 16:33:18','2021-05-25 14:18:08'),('038000845260','Pringles Medium Cheddar Chesse','038000845260.jpg','Snack','Pringles',12,5.41,7.5,NULL,7,'2021-03-01 18:51:40','2021-05-25 14:18:08'),('038000845512','Pringles Small Original','038000845512.jpg','Grocery & Gourmet Food','Pringles',12,3.75,5.5,NULL,7,'2021-02-28 16:29:25','2021-05-25 14:18:08'),('038000845536','Pringles Small Cheddar Chesse','DEFAULT.png','Grocery & Gourmet Food','Pringles',12,3.75,5.5,'Bottle-Pack 1 X 12',1,'2021-05-19 17:01:42','2021-05-25 14:18:08'),('038000991400','Kellogg\'s Cornflakes 2 in 1','038000991400.jpg','Flakes','Kellog\'s',5,52,80,NULL,7,'2021-02-28 18:44:22','2021-05-25 14:18:08'),('038000991608','Kellogg\'s Froot Loops 2 in 1','038000991608.jpg','Flakes','Kellog\'s',5,58,80,NULL,7,'2021-02-28 18:41:55','2021-05-25 14:18:08'),('04050033205400','Heinz Tomato Ketchup','04050033205400.jpg','Grocery','Heinz',12,20,35,NULL,7,'2021-02-28 18:18:42','2021-05-25 14:18:08'),('041000002878','Lipton Regular Tea Bag','041000002878.jpg','Grocery & Gourmet Food','Lipton',10,4.43,5.6,NULL,1,'2021-02-05 12:01:41','2021-05-25 14:18:08'),('041000731334','Lipton Black Tea','041000731334.jpg','Drinks','Lipton',6,20.66,35,NULL,7,'2021-02-28 18:13:08','2021-05-25 14:18:08'),('041000731488','Lipton Ice Tea Lemon','041000731488.jpg','Drinks','Lipton',6,48,78,NULL,7,'2021-02-28 17:20:34','2021-05-25 14:18:08'),('041244000098','Martinellis Apple Drink','041244000098.jpg','Drinks','Martinellis ',24,7.08,10.5,NULL,7,'2021-03-01 20:11:58','2021-05-25 14:18:08'),('041244102334','Martinelli\'s Gold Medal® Organic Apple 100% Juice 10 Fl','041244102334.jpg','Grocery & Gourmet Food','Martinellis ',10,7.63,9.65,NULL,1,'2021-02-05 11:47:56','2021-05-25 14:18:08'),('041244121090','Martinellis Apple Juice','041244121090.jpg','Drinks','Martinellis ',24,7.08,10.5,NULL,7,'2021-03-01 20:10:55','2021-05-25 14:18:08'),('041331038270','Goya Adobo with Pepper 16.5 OZ','041331038270.jpg','Grocery & Gourmet Food','Goya',10,40.1,55,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('041331039857','MALTA GOYA','041331039857.jpg','Drinks','Goya',24,7.08,10,NULL,7,'2021-04-01 13:20:21','2021-05-25 14:18:08'),('041800236008','Welch 100% Juice Apple','041800236008.jpg','Drinks','Welch\'s',60,7.83,11.5,NULL,7,'2021-02-26 18:41:50','2021-05-25 14:18:08'),('041800301232','Welch 100% Oran Pineapple Juice','041800301232.jpg','Drinks','Welch\'s',24,4.08,6.5,NULL,7,'2021-03-01 20:02:34','2021-05-25 14:18:08'),('041800316007','Welch 100% Apple Juice ','041800316007.jpg','Drinks','Welch\'s',24,4.08,6.5,NULL,7,'2021-03-01 20:04:35','2021-05-25 14:18:08'),('041800352166','Welch\'s Tropical Carrot','041800352166.jpg','Drinks','Welch\'s',60,7.83,11.5,NULL,7,'2021-02-26 18:43:24','2021-05-25 14:18:08'),('041800354009','Welch 100% Grapes Juice','041800354009.jpg','Drinks','Welch\'s',24,4.08,6.5,NULL,7,'2021-03-01 20:06:10','2021-05-25 14:18:08'),('041800354504','Welch\'s 100% Grape Fruit Juice','041800354504.jpg','Drinks','Welch\'s',60,7.83,11.5,NULL,1,'2021-03-05 16:04:43','2021-05-25 14:18:08'),('041800429004','Welch\'s Cramberry Juice Cocktail','041800429004.png','Drinks','Welch\'s',60,7.83,11.5,NULL,7,'2021-02-26 18:46:10','2021-05-25 14:18:08'),('041800432004','Welch\'s Fruit Punch','041800432004.jpg','Drinks','Welch\'s',60,7.83,11.5,NULL,7,'2021-02-26 18:39:47','2021-05-25 14:18:08'),('041800453009','Welch\'s Juice Drink Strawberry Kiwi','041800453009.jpg','Drinks','Welch\'s',60,7.83,11.5,NULL,7,'2021-02-26 18:34:05','2021-05-25 14:18:08'),('044000047030','Oreo Biscuit','044000047030.jpg','Grocery','Oreo',5,74,95,NULL,7,'2021-03-01 19:48:40','2021-05-25 14:18:08'),('044600309668','Clorox Concentrated Germicidal Bleach','044600309668.jpg','Health and Beauty','Clorox',10,51.74,56,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('044600324289','Clorox','044600324289.jpg','Beauty & Personal Care','Clorox',6,40,65,NULL,7,'2021-04-21 17:36:14','2021-05-25 14:18:08'),('046500716973','Glade Apple Cinnamon','046500716973.jpg','Health and Beauty','Glade',12,6.33,10,NULL,7,'2021-02-28 11:33:49','2021-05-25 14:18:08'),('046500742439','Glade Solid Red honey suckle','046500742439.jpg','Health and Beauty','Glade',12,6.33,10,NULL,7,'2021-02-28 15:31:31','2021-05-25 14:18:08'),('050000302116','Nestle Coffee Mate','050000302116.jpg','Dairy','Nestle Coffee Mate',6,52,78,NULL,7,'2021-02-28 17:56:55','2021-05-25 14:18:08'),('051500280645','Hungry JACK Pancake','051500280645.jpg','Grocery','Hungry Jack',12,20,35,NULL,7,'2021-02-28 16:57:16','2021-05-25 14:18:08'),('070074407050','Ensure Strawberry','070074407050.jpg','Dairy','Ensure',24,8.25,12.5,NULL,7,'2021-03-01 19:32:30','2021-05-25 14:18:08'),('070074407111','Ensure Vanilla','070074407111.jpg','Dairy','Ensure',24,8.25,12.5,NULL,7,'2021-03-01 19:30:02','2021-05-25 14:18:08'),('070074580500','PediaSure Vanilla','070074580500.jpg','Dairy','Abbott',24,9.5,13.5,NULL,7,'2021-03-01 19:34:55','2021-05-25 14:18:08'),('070074580562','Pediasure Strawberry','070074580562.jpg','Dairy','Abbott',24,9.5,13.5,NULL,7,'2021-03-01 19:36:49','2021-05-25 14:18:08'),('072310063563','Bigelow Premium Green Tea 100% Organic','072310063563.jpg','Tea','Bigelow',5,76,120,NULL,7,'2021-02-28 18:31:05','2021-05-25 14:18:08'),('072310290884','STEEP Lemon Ginger','072310290884.jpg','Tea','Bigelow',5,70,95,NULL,7,'2021-02-28 18:24:50','2021-05-25 14:18:08'),('072736014903','Vidalia Onion Vinegrette','072736014903.jpg','Grocery','Vinegarette',12,28,38,NULL,7,'2021-02-28 17:00:37','2021-05-25 14:18:08'),('072736014910','Vidalia Onion Vinegrette','072736014910.jpg','Grocery & Gourmet Food','Vinegarette',12,25,38,NULL,7,'2021-03-01 19:14:27','2021-05-25 14:18:08'),('074305001321','Bragg Organic Raw Unfiltered Apple Cider Vinegar 946ml','074305001321.jpg','Health and Beauty','Bragg',12,40.83,65,NULL,7,'2021-04-03 12:28:16','2021-05-25 14:18:08'),('076183202036','Snapple Kiwi Strawberry Juice','076183202036.jpg','Drinks','Snapple',48,5.75,8.5,NULL,7,'2021-02-26 18:14:47','2021-05-25 14:18:08'),('076183202043','Snapple All Natural Mango Madness','076183202043.jpg','Drinks','Snapple',48,5.75,8.5,NULL,7,'2021-02-26 18:05:28','2021-05-25 14:18:08'),('076301222298','Apple &Eve','076301222298.jpg','Drinks','Apple&eve',10,98,110,NULL,7,'2021-04-21 19:22:12','2021-05-25 14:18:08'),('076677100145','Famous Amos','076677100145.jpg','Grocery','Famous Amos',42,2.24,3.5,NULL,7,'2021-02-28 17:41:06','2021-05-25 14:18:08'),('078742133065','Disinfecting  Wipes','078742133065.jpg','Beauty & Personal Care','Member\'s Mark',12,19,30,NULL,7,'2021-04-21 17:27:05','2021-05-25 14:18:08'),('078742158105','Great Value Coffee Creamer','078742158105.jpg','Dairy','Great Value',4,50,75,NULL,7,'2021-02-28 17:50:30','2021-05-25 14:18:08'),('078742235028','Members Mark Towel','078742235028.jpg','Beauty & Personal Care','Member\'s Mark',15,10.53,16,NULL,7,'2021-03-01 18:33:02','2021-05-25 14:18:08'),('078742241128','Members Mark Bath Tissue','078742241128.jpg','Beauty & Personal Care','Member\'s Mark',5,32,45,NULL,7,'2021-02-26 17:57:28','2021-05-25 14:18:08'),('078742292984','Member\'s Mark Thai Jasmine Rice (25 lb.)','078742292984.jpg','Grocery & Gourmet Food','Member\'s Mark',3,145,170,NULL,1,'2021-03-05 15:29:32','2021-05-25 14:18:08'),('078742292991','Jasmine Rice Big','078742292991.jpg','Grocery & Gourmet Food','Thai Hom Mali',2,290,350,NULL,7,'2021-03-01 19:54:46','2021-05-25 14:18:08'),('086631008493','N\'joy Coffee Creamer','086631008493.jpg','Dairy','N\'joy',8,9,16,NULL,1,'2021-03-05 15:57:21','2021-05-25 14:18:08'),('087684004449','Caprisun Pounch','087684004449.jpg','Drinks','Caprisun',17,60,100,NULL,7,'2021-04-21 19:34:45','2021-05-25 14:18:08'),('096619032938','Kirkland Organic Raw Honey','096619032938.jpg','Dairy','Kirklannd',12,30,45,NULL,7,'2021-02-28 17:47:21','2021-05-25 14:18:08'),('096619394494',' Kirkland Baby Wipes','096619394494.jpg','Beauty & Personal Care','Kirklannd',9,17.77,25,NULL,7,'2021-02-28 18:00:22','2021-05-25 14:18:08'),('096619533008','Kirkland Organic Almond Milk','096619533008.jpg','Dairy','Kirklannd',12,12.66,17.5,NULL,7,'2021-03-01 19:09:19','2021-05-25 14:18:08'),('096619816927','Kirkland Mircowave Popcorn','096619816927.jpg','Snack','Kirklannd',44,1.93,3.5,NULL,7,'2021-03-01 19:12:17','2021-05-25 14:18:08'),('096619998616','Kirkland Soy Milk Vanilla','096619998616.jpg','Drinks','Kirklannd',12,9.16,15,NULL,7,'2021-03-01 19:38:32','2021-05-25 14:18:08'),('312547306355','Listerine Total Care VIOLET','312547306355.jpg','Beauty & Personal Care','Listerine',12,30.66,55,NULL,7,'2021-02-28 18:06:49','2021-05-25 14:18:08'),('312547427555','Listerine Cool Mint','312547427555.jpg','Health and Beauty','Listerine',8,46,70,NULL,7,'2021-02-28 15:42:09','2021-05-25 14:18:08'),('3218930297002','Even Skimmed Milk','3218930297002.jpg','Dairy','Even Milk',12,9.33,17,NULL,7,'2021-04-12 19:17:52','2021-05-25 14:18:08'),('3574669909129','Johnsons Baby Oil 300ml','3574669909129.jpg','Health And Beauty','Goya',10,14.72,17,NULL,1,'2021-02-10 21:34:26','2021-05-25 14:18:08'),('5000213019160','MALTA GUINNESS','5000213019160.jpg','Drinks','Malta Guiness',12,3.16,4,NULL,7,'2021-04-01 13:32:21','2021-05-25 14:18:08'),('5059001001368','Air Wick Room Spray 250ml - Soft Cotton PMP','5059001001368.jpg','Health And Beauty','Air Wick',5,18.09,22,NULL,1,'2021-02-10 21:29:22','2021-05-25 14:18:08'),('5449000000996','Coca-Cola 33cl','5449000000996.jpg','Drinks','Coca-cola',24,2.5,5,NULL,7,'2021-04-12 18:29:27','2021-05-25 14:18:08'),('6033000085214','Nestle Milo 400g','6033000085214.jpg','Grocery','Milo ',12,15,22,NULL,7,'2021-04-12 19:00:46','2021-05-25 14:18:08'),('6034000048018','Voltic Mineral Water 750ml','6034000048018.jpg','Drinks','Voltic',12,0.96,1.5,NULL,7,'2021-04-12 18:39:50','2021-05-25 14:18:08'),('6034000048032','VOLTIC 500ML','6034000048032.jpg','Drinks','Voltic',15,0.8,1,NULL,7,'2021-04-01 14:50:01','2021-05-25 14:18:08'),('6034000053623','Heaven Insecticide Spray Plus','6034000053623.jpg','Health And Beauty','Lipton',25,7.85,12,NULL,1,'2021-02-10 20:25:19','2021-05-25 14:18:08'),('6034000106893','Malta Guiness 330ml','6034000106893.jpg','Drinks','Malta Guiness',12,3.25,5.5,NULL,7,'2021-04-12 18:46:32','2021-05-25 14:18:08'),('6034000181142','BEL-AQUA 750ML','6034000181142.jpg','Drinks','Bel-aqua',15,0.86,1.5,NULL,7,'2021-04-01 14:43:06','2021-05-25 14:18:08'),('613008724221','Arizona Green Tea','613008724221.jpg','Drinks','Arizona',24,3.83,6,NULL,7,'2021-04-21 18:14:33','2021-05-25 14:18:08'),('7622010000790','Cadbury Hot Chocolate Drink 3 in 1','7622010000790.jpg','Grocery & Gourmet Food','Cadbury',50,6.5,1,NULL,1,'2021-02-10 20:14:41','2021-05-25 14:18:08'),('786173965055','Famous Amos Chocolate Chip Cookies','786173965055.jpg','Grocery & Gourmet Food','Famous Amos',10,150,157,NULL,1,'2021-02-05 02:37:00','2021-05-25 14:18:08'),('90338052','COCA-COLA 300ML','90338052.jpg','Drinks','Coca-cola',12,1.83,2.5,NULL,7,'2021-04-01 13:30:15','2021-05-25 14:18:08');
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
  `measure` varchar(45) DEFAULT NULL,
  `munit` int(11) DEFAULT NULL,
  `mqty` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;
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
  `measure` varchar(45) DEFAULT NULL,
  `unitmeasure` int(11) DEFAULT NULL,
  `measureqty` int(11) DEFAULT NULL,
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
  `measure` varchar(45) DEFAULT NULL,
  `unitmeasure` int(11) DEFAULT NULL,
  `measureqty` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
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
INSERT INTO `uom` VALUES (''),('Ampoule'),('Bottle'),('Box'),('Jacket'),('Pack'),('Piece'),('Strips'),('Tablet'),('Vial'),('Wrapper');
/*!40000 ALTER TABLE `uom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uom_set`
--

DROP TABLE IF EXISTS `uom_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uom_set` (
  `uom_set_code` varchar(45) NOT NULL,
  `measure_1` varchar(45) NOT NULL,
  `unit_1` int(11) NOT NULL,
  `measure_2` varchar(45) DEFAULT NULL,
  `unit_2` int(11) DEFAULT NULL,
  PRIMARY KEY (`uom_set_code`),
  KEY `FK_UOMSET_UOM_idx` (`measure_1`),
  KEY `FK_UOMSET_UOM_2_idx` (`measure_2`),
  CONSTRAINT `FK_UOMSET_UOM_1` FOREIGN KEY (`measure_1`) REFERENCES `uom` (`uom_desc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_UOMSET_UOM_2` FOREIGN KEY (`measure_2`) REFERENCES `uom` (`uom_desc`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uom_set`
--

LOCK TABLES `uom_set` WRITE;
/*!40000 ALTER TABLE `uom_set` DISABLE KEYS */;
INSERT INTO `uom_set` VALUES ('Bottle-Pack 1 X 12','Bottle',1,'Pack',12),('Bottle-Pack 1 X 24','Bottle',1,'Pack',24),('Bottle-Pack 1 X 36','Bottle',1,'Pack',36),('Bottle-Pack 1 X 4','Bottle',1,'Pack',4),('Bottle-Pack 1 X 6','Bottle',1,'Pack',6),('Piece-Pack 1 X 39','Piece',1,'Pack',39),('Piece-Piece 1 X 1','Piece',1,'Piece',1);
/*!40000 ALTER TABLE `uom_set` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogs`
--

LOCK TABLES `userlogs` WRITE;
/*!40000 ALTER TABLE `userlogs` DISABLE KEYS */;
INSERT INTO `userlogs` VALUES (1,1,'2021-02-24 17:38:03','2021-02-24 21:53:41'),(3,1,'2021-02-24 20:05:51',NULL),(5,1,'2021-02-24 21:54:19',NULL),(6,1,'2021-02-25 07:43:22',NULL),(7,1,'2021-02-25 20:21:28',NULL),(8,1,'2021-02-26 15:43:40','2021-02-26 15:48:05'),(9,6,'2021-02-26 15:48:21',NULL),(10,1,'2021-02-26 16:16:54','2021-02-26 16:17:10'),(11,7,'2021-02-26 16:17:15',NULL),(12,7,'2021-02-26 16:21:22',NULL),(13,7,'2021-02-26 18:38:04',NULL),(14,7,'2021-02-27 11:10:03',NULL),(15,7,'2021-02-28 11:25:57',NULL),(16,6,'2021-03-01 17:16:50','2021-03-01 18:02:23'),(17,7,'2021-03-01 18:20:08',NULL),(18,6,'2021-03-02 18:47:18','2021-03-02 20:07:06'),(19,7,'2021-03-02 20:07:21',NULL),(20,6,'2021-03-03 19:43:51',NULL),(21,1,'2021-03-05 15:00:44','2021-03-05 15:04:52'),(22,1,'2021-03-05 15:05:00',NULL),(23,1,'2021-03-05 15:08:21',NULL),(24,1,'2021-03-05 15:27:59',NULL),(25,7,'2021-03-07 17:26:32',NULL),(26,7,'2021-03-13 20:46:51',NULL),(27,7,'2021-03-27 18:54:34',NULL),(28,6,'2021-04-01 11:42:14','2021-04-01 13:10:33'),(29,7,'2021-04-01 13:10:59',NULL),(30,7,'2021-04-01 14:17:09','2021-04-01 14:23:13'),(31,7,'2021-04-01 14:31:21',NULL),(32,7,'2021-04-01 14:39:43',NULL),(33,6,'2021-04-02 09:21:42',NULL),(34,7,'2021-04-02 12:32:51',NULL),(35,6,'2021-04-03 09:43:49',NULL),(36,7,'2021-04-03 09:58:28',NULL),(37,7,'2021-04-03 12:44:09',NULL),(38,7,'2021-04-03 17:37:11',NULL),(39,7,'2021-04-04 15:20:46',NULL),(40,6,'2021-04-05 07:14:31','2021-04-05 08:52:42'),(41,7,'2021-04-05 08:53:15',NULL),(42,7,'2021-04-05 10:31:35',NULL),(43,7,'2021-04-05 12:38:13',NULL),(44,6,'2021-04-05 13:36:48','2021-04-05 16:58:10'),(45,7,'2021-04-05 18:49:03',NULL),(46,6,'2021-04-06 11:24:45',NULL),(47,6,'2021-04-07 08:13:36',NULL),(48,6,'2021-04-08 09:33:51',NULL),(49,6,'2021-04-09 09:36:50',NULL),(50,6,'2021-04-10 08:06:26',NULL),(51,6,'2021-04-11 14:16:37',NULL),(52,7,'2021-04-12 18:15:00',NULL),(53,7,'2021-04-12 20:56:54',NULL),(54,7,'2021-04-12 21:20:36',NULL),(55,6,'2021-04-13 08:08:41',NULL),(56,6,'2021-04-14 07:48:36',NULL),(57,6,'2021-04-15 07:49:36',NULL),(58,7,'2021-04-15 14:39:02',NULL),(59,6,'2021-04-16 08:15:19',NULL),(60,6,'2021-04-17 07:34:27',NULL),(61,6,'2021-04-18 14:54:39',NULL),(62,6,'2021-04-19 19:42:17',NULL),(63,7,'2021-04-20 20:18:47',NULL),(64,7,'2021-04-21 17:18:44',NULL),(65,6,'2021-04-23 08:55:55',NULL),(66,6,'2021-04-24 09:31:05',NULL),(67,7,'2021-04-25 20:09:34',NULL),(68,7,'2021-04-26 16:46:11',NULL),(69,7,'2021-04-27 18:01:03',NULL),(70,6,'2021-04-29 10:31:34',NULL),(71,6,'2021-04-30 14:53:06',NULL),(72,7,'2021-05-02 16:47:39',NULL),(73,6,'2021-05-06 08:30:13',NULL),(74,7,'2021-05-09 11:13:50',NULL),(75,1,'2021-05-19 15:15:16',NULL),(76,1,'2021-05-19 17:08:41',NULL),(77,1,'2021-05-19 17:15:08',NULL),(78,1,'2021-05-19 17:17:55',NULL),(79,1,'2021-05-19 17:19:42',NULL),(80,1,'2021-05-19 17:32:17','2021-05-20 00:33:08'),(81,1,'2021-05-24 15:25:45','2021-05-24 16:47:35'),(82,1,'2021-05-25 12:22:36',NULL),(83,1,'2021-05-25 12:30:33',NULL),(84,1,'2021-05-25 12:31:49',NULL),(85,1,'2021-05-25 12:40:50',NULL),(86,1,'2021-05-25 14:07:43',NULL),(87,1,'2021-05-25 14:29:19',NULL),(88,1,'2021-05-25 14:31:25',NULL),(89,1,'2021-05-25 14:32:41',NULL),(90,1,'2021-05-25 14:35:44','2021-05-25 14:36:09'),(91,1,'2021-05-25 14:41:52','2021-05-25 14:46:14'),(92,1,'2021-05-25 14:54:46',NULL),(93,1,'2021-05-25 14:55:36','2021-05-25 14:58:14');
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
INSERT INTO `users` VALUES (1,'Mr','Kofi','Moses','0246002100','kofim@gmail.com','kofi','ab55a9ef5cdd19db785e11794c9c7b9c','Administrator','2020-02-11','2020-05-05 07:18:19',1,1),(6,'Mr','mark','simpini','0248815514','marxpszalez5009@gmail.com','mark','9aa04a351cba882fdd4ad734a0ca533d','Sales','2021-02-26','2021-02-26 15:49:42',1,1),(7,'Miss','Stephanie','Ofancher','0544133196','stephanieofancher17@gmail.com','steph','126a8536fea3972ce4a545383118795e','Administrator','2021-02-26','2021-02-26 16:24:21',1,1);
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

-- Dump completed on 2021-05-25 15:01:27
