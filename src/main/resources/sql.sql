-- MySQL dump 10.13  Distrib 5.7.25, for Win64 (x86_64)
--
-- Host: localhost    Database: campusdb
-- ------------------------------------------------------
-- Server version	5.7.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_area`
--

DROP TABLE IF EXISTS `t_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_area` (
  `area_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(255) NOT NULL COMMENT '区域名称',
  `priority` int(4) DEFAULT '0' COMMENT '权重',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_area`
--

LOCK TABLES `t_area` WRITE;
/*!40000 ALTER TABLE `t_area` DISABLE KEYS */;
INSERT INTO `t_area` VALUES (1,'北门',1,1563782241612,1563782241612),(2,'南门',1,1563782241612,1563782241612),(3,'东门',1,1563782241612,1563782241612),(4,'西门',1,1563782241612,1563782241612);
/*!40000 ALTER TABLE `t_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_award`
--

DROP TABLE IF EXISTS `t_award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_award` (
  `award_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `award_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `award_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `award_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` int(10) DEFAULT '0',
  `priority` int(2) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_edit_time` bigint(20) DEFAULT NULL,
  `enbale_status` int(2) DEFAULT '0',
  `shop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`award_id`),
  KEY `fk_award_shop_idx` (`shop_id`),
  CONSTRAINT `fk_award_shop` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_award`
--

LOCK TABLES `t_award` WRITE;
/*!40000 ALTER TABLE `t_award` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_award` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_head_line`
--

DROP TABLE IF EXISTS `t_head_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_head_line` (
  `line_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1024) DEFAULT NULL COMMENT '头像名',
  `line_link` varchar(2000) DEFAULT NULL COMMENT '头条链接',
  `line_img` varchar(2000) DEFAULT NULL COMMENT '头条图片',
  `priority` int(4) DEFAULT '0' COMMENT '权重',
  `enable_status` int(2) DEFAULT '0' COMMENT '0 禁止使用 1 允许使用',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_head_line`
--

LOCK TABLES `t_head_line` WRITE;
/*!40000 ALTER TABLE `t_head_line` DISABLE KEYS */;
INSERT INTO `t_head_line` VALUES (1,'合伙人','','\\upload\\item\\shop\\3\\2019072218005816138.jpg',1,1,1563782241612,1563782241612),(2,'趣都','','\\upload\\item\\shop\\3\\2019072218005816138.jpg',2,1,1563782241612,1563782241612);
/*!40000 ALTER TABLE `t_head_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_local_auth`
--

DROP TABLE IF EXISTS `t_local_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_local_auth` (
  `local_auth_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile` (`username`),
  KEY `fk_lacalauth_profile` (`user_id`),
  CONSTRAINT `fk_lacalauth_profile` FOREIGN KEY (`user_id`) REFERENCES `t_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_local_auth`
--

LOCK TABLES `t_local_auth` WRITE;
/*!40000 ALTER TABLE `t_local_auth` DISABLE KEYS */;
INSERT INTO `t_local_auth` VALUES (2,1,'1','e50s255sqs2e5y666y562y5qee02565e',1564650470342,1564819623255);
/*!40000 ALTER TABLE `t_local_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_person_info`
--

DROP TABLE IF EXISTS `t_person_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_person_info` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '名称',
  `image` varchar(1024) DEFAULT NULL COMMENT '图片地址',
  `email` varchar(1024) DEFAULT NULL COMMENT '邮箱',
  `gender` varchar(4) DEFAULT NULL COMMENT '性别',
  `enable_status` int(2) DEFAULT '0' COMMENT '0 禁止使用 1 允许使用',
  `user_type` int(2) DEFAULT '1' COMMENT '1 顾客 2 店家 3 超级管理员',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_person_info`
--

LOCK TABLES `t_person_info` WRITE;
/*!40000 ALTER TABLE `t_person_info` DISABLE KEYS */;
INSERT INTO `t_person_info` VALUES (1,'owner',NULL,'516535632@qq.com','男',1,1,1563782241612,1563782241612),(2,'斯德哥尔摩情人','http://thirdwx.qlogo.cn/mmopen/vi_32/Ric6VfVFX2Jx0mJ8lN6hTeribIw0Bq8ZicXJNFypZJZItqf9aokuSYXZ9AicmwUOs35sAM4ApjOwSB0RGl9dymAeWg/132',NULL,'1',1,1,1565000891581,1565000891581);
/*!40000 ALTER TABLE `t_person_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL COMMENT '描述',
  `img_addr` varchar(2000) DEFAULT NULL COMMENT '图片',
  `normal_price` varchar(100) DEFAULT NULL COMMENT '正常价格',
  `promotion_price` varchar(100) DEFAULT NULL COMMENT '折扣价格',
  `priority` int(4) DEFAULT '0' COMMENT '权重',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `enable_status` int(2) DEFAULT '0' COMMENT ' -1 不可用 0 下架 1 允许使用',
  `product_category_id` bigint(20) DEFAULT NULL,
  `shop_id` bigint(20) NOT NULL DEFAULT '0',
  `point` int(10) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_shop` (`shop_id`),
  KEY `fk_product_procate_idx` (`product_category_id`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `t_product_category` (`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
INSERT INTO `t_product` VALUES (3,'波霸奶茶','好喝','\\upload\\item\\shop\\1\\2019072716074554877.jpg','5','4',1,1564214865676,1564301848612,1,3,1,NULL),(4,'珍珠奶茶','好喝','\\upload\\item\\shop\\1\\2019072716074554877.jpg','8','7',4,1564215707009,1564215707009,1,7,1,NULL),(5,'蓝莓滨沙','好喝','\\upload\\item\\shop\\1\\201908062059315544.jpg','40','5',5,1564304416054,1565096371492,1,8,1,6),(6,'蓝莓滨沙','好喝','\\upload\\item\\shop\\1\\2019072716074554877.jpg','4','1',5,1564304416054,1565075409620,1,8,1,4),(7,'汉堡','好吃','\\upload\\item\\shop\\12\\2019073014575878579.jpg','1','1',1,1564469878749,1564469878749,1,9,12,NULL),(8,'波霸奶茶','好喝','\\upload\\item\\shop\\13\\2019080315533281275.jpg','1','1',1,1564818812166,1564818812166,1,10,13,NULL);
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product_category`
--

DROP TABLE IF EXISTS `t_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_category` (
  `product_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) NOT NULL COMMENT '名称',
  `priority` int(4) DEFAULT '0' COMMENT '权重',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `shop_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  KEY `fk_procate_shop` (`shop_id`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_category`
--

LOCK TABLES `t_product_category` WRITE;
/*!40000 ALTER TABLE `t_product_category` DISABLE KEYS */;
INSERT INTO `t_product_category` VALUES (1,'商品分类1',1,1563782241612,1),(2,'商品分类2',2,1563782241612,1),(3,'商品分类3',3,1563782241612,1),(4,'商品类别4',4,1564119565736,1),(5,'商品类别5',5,1564119565736,1),(6,'商品类别4',4,1564122409430,1),(7,'商品类别5',5,1564122409430,1),(8,'商品分类6',6,1564123294765,1),(9,'汉堡',1,1564469842122,12),(10,'奶茶',1,1564818781782,13);
/*!40000 ALTER TABLE `t_product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product_img`
--

DROP TABLE IF EXISTS `t_product_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_img` (
  `product_img_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) DEFAULT NULL COMMENT '图片地址',
  `img_desc` varchar(2000) DEFAULT NULL COMMENT '图片描述',
  `priority` int(4) DEFAULT '0' COMMENT '权重',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_proming_product` (`product_id`),
  CONSTRAINT `fk_proming_product` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_img`
--

LOCK TABLES `t_product_img` WRITE;
/*!40000 ALTER TABLE `t_product_img` DISABLE KEYS */;
INSERT INTO `t_product_img` VALUES (1,'\\upload\\item\\shop\\1\\2019072716074588668.jpg',NULL,NULL,1564214865854,3),(2,'\\upload\\item\\shop\\1\\2019072716074570010.jpg',NULL,NULL,1564214865906,3),(3,'\\upload\\item\\shop\\1\\2019072716214715206.jpg',NULL,NULL,1564215707271,4),(7,'\\upload\\item\\shop\\12\\201907301457588251.jpg',NULL,NULL,1564469878905,7),(8,'\\upload\\item\\shop\\13\\2019080315533277278.jpg',NULL,NULL,1564818812322,8),(15,'\\upload\\item\\shop\\1\\2019080620593148400.jpg',NULL,NULL,1565096371715,5);
/*!40000 ALTER TABLE `t_product_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product_sell_daily`
--

DROP TABLE IF EXISTS `t_product_sell_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_sell_daily` (
  `product_sell_daily_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  `total` int(10) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_sell_daily_id`),
  KEY `fk_product_sell_product` (`product_id`),
  KEY `fk_product_sell_shop` (`shop_id`),
  CONSTRAINT `fk_product_sell_product` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`product_id`),
  CONSTRAINT `fk_product_sell_shop` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_sell_daily`
--

LOCK TABLES `t_product_sell_daily` WRITE;
/*!40000 ALTER TABLE `t_product_sell_daily` DISABLE KEYS */;
INSERT INTO `t_product_sell_daily` VALUES (1,6,1,3,'2019-08-06 10:36:13'),(2,7,1,1,'2019-08-06 10:36:13'),(3,7,2,1,'2019-08-06 10:36:13'),(4,3,1,0,'2019-08-06 10:36:13'),(5,4,1,0,'2019-08-06 10:36:13'),(6,5,1,0,'2019-08-06 10:36:13'),(7,6,1,0,'2019-08-06 10:36:13'),(8,7,12,0,'2019-08-06 10:36:13'),(9,8,13,0,'2019-08-06 10:36:13');
/*!40000 ALTER TABLE `t_product_sell_daily` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_shop`
--

DROP TABLE IF EXISTS `t_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop` (
  `shop_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) NOT NULL,
  `area_id` bigint(20) NOT NULL,
  `shop_category_id` bigint(20) NOT NULL,
  `shop_name` varchar(256) DEFAULT '' COMMENT '店铺名称',
  `shop_desc` varchar(1024) DEFAULT NULL COMMENT '描述',
  `shop_addr` varchar(200) DEFAULT NULL COMMENT '地址',
  `phone` varchar(128) DEFAULT NULL COMMENT '电话',
  `shop_img` varchar(1024) DEFAULT NULL COMMENT '图片',
  `priority` int(4) DEFAULT '0' COMMENT '权重',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `enable_status` int(2) DEFAULT '0' COMMENT ' -1 不可用 0 审核中 1 允许使用',
  `advice` varchar(255) DEFAULT NULL COMMENT '建议',
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_area` (`area_id`),
  KEY `fk_shop_profile` (`owner_id`),
  KEY `fk_shop_shopcate` (`shop_category_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `t_area` (`area_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `t_person_info` (`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `t_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_shop`
--

LOCK TABLES `t_shop` WRITE;
/*!40000 ALTER TABLE `t_shop` DISABLE KEYS */;
INSERT INTO `t_shop` VALUES (1,1,4,1,'汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',NULL,1563789658529,1564065506415,1,'添加详细信息'),(2,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(3,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(4,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(5,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(6,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(7,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(8,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(9,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(10,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(12,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(13,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(14,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(15,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(16,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(17,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(18,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(19,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(20,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(21,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(22,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(23,1,4,2,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息'),(24,1,4,1,'宝贝汉堡','香浓可口的奶茶汉堡','闽江学院美食街','18060572168','\\upload\\item\\shop\\3\\2019072218005816138.jpg',0,1563789658529,1564065506415,1,'添加详细信息');
/*!40000 ALTER TABLE `t_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_shop_auth_map`
--

DROP TABLE IF EXISTS `t_shop_auth_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop_auth_map` (
  `shop_auth_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_flag` int(2) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_edit_time` bigint(20) DEFAULT NULL,
  `enable_status` int(2) DEFAULT '0',
  PRIMARY KEY (`shop_auth_id`),
  KEY `fk_shop_auth_map_shop` (`shop_id`),
  KEY `uk_shop_auth_map` (`employee_id`,`shop_id`),
  CONSTRAINT `fk_shop_auth_map_employee` FOREIGN KEY (`employee_id`) REFERENCES `t_person_info` (`user_id`),
  CONSTRAINT `fk_shop_auth_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_shop_auth_map`
--

LOCK TABLES `t_shop_auth_map` WRITE;
/*!40000 ALTER TABLE `t_shop_auth_map` DISABLE KEYS */;
INSERT INTO `t_shop_auth_map` VALUES (1,1,1,'店家',0,1563782241612,1563782241612,1),(2,2,1,'店员',1,1563782241612,1563782241612,1),(3,2,1,'33',1,NULL,NULL,1);
/*!40000 ALTER TABLE `t_shop_auth_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_shop_category`
--

DROP TABLE IF EXISTS `t_shop_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop_category` (
  `shop_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) DEFAULT '' COMMENT '商品类别名',
  `shop_category_desc` varchar(1000) DEFAULT '' COMMENT '商品类别描述',
  `shop_category_img` varchar(2000) DEFAULT NULL COMMENT '商品类别图片',
  `priority` int(4) DEFAULT '0' COMMENT '权重',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `fk_shop_category_self` (`parent_id`),
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `t_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_shop_category`
--

LOCK TABLES `t_shop_category` WRITE;
/*!40000 ALTER TABLE `t_shop_category` DISABLE KEYS */;
INSERT INTO `t_shop_category` VALUES (1,'奶茶咖啡','香浓可口的奶茶','\\upload\\item\\shop\\3\\2019072218005816138.jpg',1,1563782241612,1563782241612,3),(2,'文具','实惠的文具','\\upload\\item\\shop\\3\\2019072218005816138.jpg',1,1563782241612,1563782241612,4),(3,'二手市场','二手市场','\\upload\\item\\shop\\3\\2019072218005816138.jpg',1,1563782241612,1563782241612,NULL),(4,'学习用品','学习用品','\\upload\\item\\shop\\3\\2019072218005816138.jpg',1,1563782241612,1563782241612,NULL);
/*!40000 ALTER TABLE `t_shop_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_award_map`
--

DROP TABLE IF EXISTS `t_user_award_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_award_map` (
  `user_award_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `award_id` bigint(20) NOT NULL,
  `shop_id` bigint(20) NOT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `used_status` int(2) DEFAULT '0',
  `point` int(10) DEFAULT '0',
  PRIMARY KEY (`user_award_id`),
  KEY `fk_user_award_map_profile` (`user_id`),
  KEY `fk_user_award_map_award` (`award_id`),
  KEY `fk_user_award_map_shop` (`shop_id`),
  KEY `fk_user_award_map_operator` (`operator_id`),
  CONSTRAINT `fk_user_award_map_award` FOREIGN KEY (`award_id`) REFERENCES `t_award` (`award_id`),
  CONSTRAINT `fk_user_award_map_operator` FOREIGN KEY (`operator_id`) REFERENCES `t_person_info` (`user_id`),
  CONSTRAINT `fk_user_award_map_profile` FOREIGN KEY (`user_id`) REFERENCES `t_person_info` (`user_id`),
  CONSTRAINT `fk_user_award_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_award_map`
--

LOCK TABLES `t_user_award_map` WRITE;
/*!40000 ALTER TABLE `t_user_award_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_award_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_product_map`
--

DROP TABLE IF EXISTS `t_user_product_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_product_map` (
  `user_product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `used_status` int(2) DEFAULT '0',
  `point` int(10) DEFAULT '0',
  PRIMARY KEY (`user_product_id`),
  KEY `fk_user_product_map_profile` (`user_id`),
  KEY `fk_user_product_map_product` (`product_id`),
  KEY `fk_user_product_map_shop` (`shop_id`),
  KEY `fk_user_product_map_operator` (`operator_id`),
  CONSTRAINT `fk_user_product_map_operator` FOREIGN KEY (`operator_id`) REFERENCES `t_person_info` (`user_id`),
  CONSTRAINT `fk_user_product_map_product` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`product_id`),
  CONSTRAINT `fk_user_product_map_profile` FOREIGN KEY (`user_id`) REFERENCES `t_person_info` (`user_id`),
  CONSTRAINT `fk_user_product_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_product_map`
--

LOCK TABLES `t_user_product_map` WRITE;
/*!40000 ALTER TABLE `t_user_product_map` DISABLE KEYS */;
INSERT INTO `t_user_product_map` VALUES (1,1,7,1,1,1565167569764,1,6),(2,1,7,2,1,1565167569761,1,0),(3,2,6,1,1,1565167569761,1,0),(4,2,6,1,1,1565167569762,1,8),(5,2,6,1,1,1565167569762,1,7);
/*!40000 ALTER TABLE `t_user_product_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_shop_map`
--

DROP TABLE IF EXISTS `t_user_shop_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_shop_map` (
  `user_shop_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `point` int(10) DEFAULT '0',
  PRIMARY KEY (`user_shop_id`),
  UNIQUE KEY `uq_user_shop` (`user_id`,`shop_id`),
  KEY `fk_user_shop_shop` (`shop_id`),
  CONSTRAINT `fk_user_shop_shop` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`),
  CONSTRAINT `fk_user_shop_user` FOREIGN KEY (`user_id`) REFERENCES `t_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_shop_map`
--

LOCK TABLES `t_user_shop_map` WRITE;
/*!40000 ALTER TABLE `t_user_shop_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_shop_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_wechat_auth`
--

DROP TABLE IF EXISTS `t_wechat_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_wechat_auth` (
  `wechat_auth_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `open_id` varchar(1024) NOT NULL COMMENT 'openid',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`wechat_auth_id`),
  UNIQUE KEY `open_id` (`open_id`),
  KEY `fk_wechatauth_profile` (`user_id`),
  CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY (`user_id`) REFERENCES `t_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wechat_auth`
--

LOCK TABLES `t_wechat_auth` WRITE;
/*!40000 ALTER TABLE `t_wechat_auth` DISABLE KEYS */;
INSERT INTO `t_wechat_auth` VALUES (1,2,'oC2M9t8lpOHrO0k4RM_4tp8m6jus',1565000891581);
/*!40000 ALTER TABLE `t_wechat_auth` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-10 15:42:27
