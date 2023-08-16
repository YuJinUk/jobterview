-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: jobterview
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.20.04.4

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
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookmark` (
  `member_id` bigint NOT NULL,
  `room_id` bigint NOT NULL,
  `created_date` datetime(6) NOT NULL,
  PRIMARY KEY (`member_id`,`room_id`),
  KEY `FKq2xkwitw4aukefwrmj2bs4t34` (`room_id`),
  CONSTRAINT `FK5bm7rup91j277mc7gg63akie2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKq2xkwitw4aukefwrmj2bs4t34` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `chat_id` bigint NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chat_id`),
  KEY `FKgvc5hrt0h18xk63qosss3ti30` (`member_id`),
  KEY `FKm38tfuuhbqvc3jrrat6q4k01j` (`room_id`),
  CONSTRAINT `FKgvc5hrt0h18xk63qosss3ti30` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKm38tfuuhbqvc3jrrat6q4k01j` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_cert`
--

DROP TABLE IF EXISTS `mail_cert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mail_cert` (
  `mail_cert_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `mail_auth` bit(1) NOT NULL DEFAULT b'0',
  `mail_key` int NOT NULL,
  PRIMARY KEY (`mail_cert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_cert`
--

LOCK TABLES `mail_cert` WRITE;
/*!40000 ALTER TABLE `mail_cert` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_cert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mate`
--

DROP TABLE IF EXISTS `mate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mate` (
  `from_member_id` bigint NOT NULL,
  `to_member_id` bigint NOT NULL,
  `created_date` datetime(6) NOT NULL,
  PRIMARY KEY (`from_member_id`,`to_member_id`),
  KEY `FKjdk1n2prd8pb492jww0naf93y` (`to_member_id`),
  CONSTRAINT `FKjdk1n2prd8pb492jww0naf93y` FOREIGN KEY (`to_member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKq4gcol3qw3nacmjbshplx3w8e` FOREIGN KEY (`from_member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mate`
--

LOCK TABLES `mate` WRITE;
/*!40000 ALTER TABLE `mate` DISABLE KEYS */;
INSERT INTO `mate` VALUES (129,131,'2023-08-14 23:52:59.588048'),(138,135,'2023-08-16 16:46:32.300593'),(138,136,'2023-08-16 16:46:32.606685');
/*!40000 ALTER TABLE `mate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `auth_code` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT 'ROLE_UNVERIFIED',
  `mail_cert_id` bigint DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`),
  UNIQUE KEY `UK_hh9kg6jti4n1eoiertn2k6qsc` (`nickname`),
  KEY `FKec4bni9gyobkehs5v9pg2qow6` (`mail_cert_id`),
  CONSTRAINT `FKec4bni9gyobkehs5v9pg2qow6` FOREIGN KEY (`mail_cert_id`) REFERENCES `mail_cert` (`mail_cert_id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2023-08-13 06:22:32.000000',NULL,'member1@example.com','member1','password1','ROLE_LOCAL',NULL),(2,'2023-08-12 06:22:32.000000',NULL,'member2@example.com','member2','password2','ROLE_LOCAL',NULL),(3,'2023-08-11 06:22:32.000000',NULL,'member3@example.com','member3','password3','ROLE_LOCAL',NULL),(4,'2023-08-10 06:22:32.000000',NULL,'member4@example.com','member4','password4','ROLE_LOCAL',NULL),(5,'2023-08-09 06:22:32.000000',NULL,'member5@example.com','member5','password5','ROLE_LOCAL',NULL),(6,'2023-08-08 06:22:32.000000',NULL,'member6@example.com','member6','password6','ROLE_LOCAL',NULL),(7,'2023-08-07 06:22:32.000000',NULL,'member7@example.com','member7','password7','ROLE_LOCAL',NULL),(8,'2023-08-06 06:22:32.000000',NULL,'member8@example.com','member8','password8','ROLE_LOCAL',NULL),(9,'2023-08-05 06:22:32.000000',NULL,'member9@example.com','member9','password9','ROLE_LOCAL',NULL),(10,'2023-08-04 06:22:32.000000',NULL,'member10@example.com','member10','password10','ROLE_LOCAL',NULL),(11,'2023-08-03 06:22:32.000000',NULL,'member11@example.com','member11','password11','ROLE_LOCAL',NULL),(12,'2023-08-02 06:22:32.000000',NULL,'member12@example.com','member12','password12','ROLE_LOCAL',NULL),(13,'2023-08-01 06:22:32.000000',NULL,'member13@example.com','member13','password13','ROLE_LOCAL',NULL),(14,'2023-07-31 06:22:32.000000',NULL,'member14@example.com','member14','password14','ROLE_LOCAL',NULL),(15,'2023-07-30 06:22:32.000000',NULL,'member15@example.com','member15','password15','ROLE_LOCAL',NULL),(16,'2023-07-29 06:22:32.000000',NULL,'member16@example.com','member16','password16','ROLE_LOCAL',NULL),(17,'2023-07-28 06:22:32.000000',NULL,'member17@example.com','member17','password17','ROLE_LOCAL',NULL),(18,'2023-07-27 06:22:32.000000',NULL,'member18@example.com','member18','password18','ROLE_LOCAL',NULL),(19,'2023-07-26 06:22:32.000000',NULL,'member19@example.com','member19','password19','ROLE_LOCAL',NULL),(20,'2023-07-25 06:22:32.000000',NULL,'member20@example.com','member20','password20','ROLE_LOCAL',NULL),(21,'2023-07-24 06:22:32.000000',NULL,'member21@example.com','member21','password21','ROLE_LOCAL',NULL),(22,'2023-07-23 06:22:32.000000',NULL,'member22@example.com','member22','password22','ROLE_LOCAL',NULL),(23,'2023-07-22 06:22:32.000000',NULL,'member23@example.com','member23','password23','ROLE_LOCAL',NULL),(24,'2023-07-21 06:22:32.000000',NULL,'member24@example.com','member24','password24','ROLE_LOCAL',NULL),(25,'2023-07-20 06:22:32.000000',NULL,'member25@example.com','member25','password25','ROLE_LOCAL',NULL),(26,'2023-07-19 06:22:32.000000',NULL,'member26@example.com','member26','password26','ROLE_LOCAL',NULL),(27,'2023-07-18 06:22:32.000000',NULL,'member27@example.com','member27','password27','ROLE_LOCAL',NULL),(28,'2023-07-17 06:22:32.000000',NULL,'member28@example.com','member28','password28','ROLE_LOCAL',NULL),(29,'2023-07-16 06:22:32.000000',NULL,'member29@example.com','member29','password29','ROLE_LOCAL',NULL),(30,'2023-07-15 06:22:32.000000',NULL,'member30@example.com','member30','password30','ROLE_LOCAL',NULL),(31,'2023-07-14 06:22:32.000000',NULL,'member31@example.com','member31','password31','ROLE_LOCAL',NULL),(32,'2023-07-13 06:22:32.000000',NULL,'member32@example.com','member32','password32','ROLE_LOCAL',NULL),(33,'2023-07-12 06:22:32.000000',NULL,'member33@example.com','member33','password33','ROLE_LOCAL',NULL),(34,'2023-07-11 06:22:32.000000',NULL,'member34@example.com','member34','password34','ROLE_LOCAL',NULL),(35,'2023-07-10 06:22:32.000000',NULL,'member35@example.com','member35','password35','ROLE_LOCAL',NULL),(36,'2023-07-09 06:22:32.000000',NULL,'member36@example.com','member36','password36','ROLE_LOCAL',NULL),(37,'2023-07-08 06:22:32.000000',NULL,'member37@example.com','member37','password37','ROLE_LOCAL',NULL),(38,'2023-07-07 06:22:32.000000',NULL,'member38@example.com','member38','password38','ROLE_LOCAL',NULL),(39,'2023-07-06 06:22:32.000000',NULL,'member39@example.com','member39','password39','ROLE_LOCAL',NULL),(40,'2023-07-05 06:22:32.000000',NULL,'member40@example.com','member40','password40','ROLE_LOCAL',NULL),(41,'2023-07-04 06:22:32.000000',NULL,'member41@example.com','member41','password41','ROLE_LOCAL',NULL),(42,'2023-07-03 06:22:32.000000',NULL,'member42@example.com','member42','password42','ROLE_LOCAL',NULL),(43,'2023-07-02 06:22:32.000000',NULL,'member43@example.com','member43','password43','ROLE_LOCAL',NULL),(44,'2023-07-01 06:22:32.000000',NULL,'member44@example.com','member44','password44','ROLE_LOCAL',NULL),(45,'2023-06-30 06:22:32.000000',NULL,'member45@example.com','member45','password45','ROLE_LOCAL',NULL),(46,'2023-06-29 06:22:32.000000',NULL,'member46@example.com','member46','password46','ROLE_LOCAL',NULL),(47,'2023-06-28 06:22:32.000000',NULL,'member47@example.com','member47','password47','ROLE_LOCAL',NULL),(48,'2023-06-27 06:22:32.000000',NULL,'member48@example.com','member48','password48','ROLE_LOCAL',NULL),(49,'2023-06-26 06:22:32.000000',NULL,'member49@example.com','member49','password49','ROLE_LOCAL',NULL),(50,'2023-06-25 06:22:32.000000',NULL,'member50@example.com','member50','password50','ROLE_LOCAL',NULL),(51,'2023-06-24 06:22:32.000000',NULL,'member51@example.com','member51','password51','ROLE_LOCAL',NULL),(52,'2023-06-23 06:22:32.000000',NULL,'member52@example.com','member52','password52','ROLE_LOCAL',NULL),(53,'2023-06-22 06:22:32.000000',NULL,'member53@example.com','member53','password53','ROLE_LOCAL',NULL),(54,'2023-06-21 06:22:32.000000',NULL,'member54@example.com','member54','password54','ROLE_LOCAL',NULL),(55,'2023-06-20 06:22:32.000000',NULL,'member55@example.com','member55','password55','ROLE_LOCAL',NULL),(56,'2023-06-19 06:22:32.000000',NULL,'member56@example.com','member56','password56','ROLE_LOCAL',NULL),(57,'2023-06-18 06:22:32.000000',NULL,'member57@example.com','member57','password57','ROLE_LOCAL',NULL),(58,'2023-06-17 06:22:32.000000',NULL,'member58@example.com','member58','password58','ROLE_LOCAL',NULL),(59,'2023-06-16 06:22:32.000000',NULL,'member59@example.com','member59','password59','ROLE_LOCAL',NULL),(60,'2023-06-15 06:22:32.000000',NULL,'member60@example.com','member60','password60','ROLE_LOCAL',NULL),(61,'2023-06-14 06:22:32.000000',NULL,'member61@example.com','member61','password61','ROLE_LOCAL',NULL),(62,'2023-06-13 06:22:32.000000',NULL,'member62@example.com','member62','password62','ROLE_LOCAL',NULL),(63,'2023-06-12 06:22:32.000000',NULL,'member63@example.com','member63','password63','ROLE_LOCAL',NULL),(64,'2023-06-11 06:22:32.000000',NULL,'member64@example.com','member64','password64','ROLE_LOCAL',NULL),(65,'2023-06-10 06:22:32.000000',NULL,'member65@example.com','member65','password65','ROLE_LOCAL',NULL),(66,'2023-06-09 06:22:32.000000',NULL,'member66@example.com','member66','password66','ROLE_LOCAL',NULL),(67,'2023-06-08 06:22:32.000000',NULL,'member67@example.com','member67','password67','ROLE_LOCAL',NULL),(68,'2023-06-07 06:22:32.000000',NULL,'member68@example.com','member68','password68','ROLE_LOCAL',NULL),(69,'2023-06-06 06:22:32.000000',NULL,'member69@example.com','member69','password69','ROLE_LOCAL',NULL),(70,'2023-06-05 06:22:32.000000',NULL,'member70@example.com','member70','password70','ROLE_LOCAL',NULL),(71,'2023-06-04 06:22:32.000000',NULL,'member71@example.com','member71','password71','ROLE_LOCAL',NULL),(72,'2023-06-03 06:22:32.000000',NULL,'member72@example.com','member72','password72','ROLE_LOCAL',NULL),(73,'2023-06-02 06:22:32.000000',NULL,'member73@example.com','member73','password73','ROLE_LOCAL',NULL),(74,'2023-06-01 06:22:32.000000',NULL,'member74@example.com','member74','password74','ROLE_LOCAL',NULL),(75,'2023-05-31 06:22:32.000000',NULL,'member75@example.com','member75','password75','ROLE_LOCAL',NULL),(76,'2023-05-30 06:22:32.000000',NULL,'member76@example.com','member76','password76','ROLE_LOCAL',NULL),(77,'2023-05-29 06:22:32.000000',NULL,'member77@example.com','member77','password77','ROLE_LOCAL',NULL),(78,'2023-05-28 06:22:32.000000',NULL,'member78@example.com','member78','password78','ROLE_LOCAL',NULL),(79,'2023-05-27 06:22:32.000000',NULL,'member79@example.com','member79','password79','ROLE_LOCAL',NULL),(80,'2023-05-26 06:22:32.000000',NULL,'member80@example.com','member80','password80','ROLE_LOCAL',NULL),(81,'2023-05-25 06:22:32.000000',NULL,'member81@example.com','member81','password81','ROLE_LOCAL',NULL),(82,'2023-05-24 06:22:32.000000',NULL,'member82@example.com','member82','password82','ROLE_LOCAL',NULL),(83,'2023-05-23 06:22:32.000000',NULL,'member83@example.com','member83','password83','ROLE_LOCAL',NULL),(84,'2023-05-22 06:22:32.000000',NULL,'member84@example.com','member84','password84','ROLE_LOCAL',NULL),(85,'2023-05-21 06:22:32.000000',NULL,'member85@example.com','member85','password85','ROLE_LOCAL',NULL),(86,'2023-05-20 06:22:32.000000',NULL,'member86@example.com','member86','password86','ROLE_LOCAL',NULL),(87,'2023-05-19 06:22:32.000000',NULL,'member87@example.com','member87','password87','ROLE_LOCAL',NULL),(88,'2023-05-18 06:22:32.000000',NULL,'member88@example.com','member88','password88','ROLE_LOCAL',NULL),(89,'2023-05-17 06:22:32.000000',NULL,'member89@example.com','member89','password89','ROLE_LOCAL',NULL),(90,'2023-05-16 06:22:32.000000',NULL,'member90@example.com','member90','password90','ROLE_LOCAL',NULL),(91,'2023-05-15 06:22:32.000000',NULL,'member91@example.com','member91','password91','ROLE_LOCAL',NULL),(92,'2023-05-14 06:22:32.000000',NULL,'member92@example.com','member92','password92','ROLE_LOCAL',NULL),(93,'2023-05-13 06:22:32.000000',NULL,'member93@example.com','member93','password93','ROLE_LOCAL',NULL),(94,'2023-05-12 06:22:32.000000',NULL,'member94@example.com','member94','password94','ROLE_LOCAL',NULL),(95,'2023-05-11 06:22:32.000000',NULL,'member95@example.com','member95','password95','ROLE_LOCAL',NULL),(96,'2023-05-10 06:22:32.000000',NULL,'member96@example.com','member96','password96','ROLE_LOCAL',NULL),(97,'2023-05-09 06:22:32.000000',NULL,'member97@example.com','member97','password97','ROLE_LOCAL',NULL),(98,'2023-05-08 06:22:32.000000',NULL,'member98@example.com','member98','password98','ROLE_LOCAL',NULL),(99,'2023-05-07 06:22:32.000000',NULL,'member99@example.com','member99','password99','ROLE_LOCAL',NULL),(100,'2023-05-06 06:22:32.000000',NULL,'member100@example.com','member100','password100','ROLE_LOCAL',NULL),(128,'2023-08-14 15:23:42.548211','gCXtF5QF','admin@naver.com','킹왕짱관리자','$2a$10$IkwWTQ1cmHdQ/1mDvAdV1uBblQ3rBPm8.Q2AO3s8ViRa8Oyf9w1g6','ROLE_LOCAL',NULL),(129,'2023-08-14 15:25:05.386262',NULL,'chch4594@naver.com','강수창','$2a$10$wgNjLMKeZLLSvfhQGJYlD.20HH.txVogMldkTcP.e6M8XM6mVMIte','ROLE_LOCAL',NULL),(130,'2023-08-14 15:25:57.726793',NULL,'yujinuk5140@gmail.com','유진욱','$2a$10$6BWPyiqpzd9AQH2NIbpqlukrF9bdKxyWMa2M.uwu1.lA.uF6snmpy','ROLE_LOCAL',NULL),(131,'2023-08-14 15:39:08.903932',NULL,'sangwoo9808@gmail.com','이상우','$2a$10$/3UYMYHhRwaJjji2fAeeZul5.PYc5Lp5UwRUOPk5DlAvegc3Y/6aS','ROLE_LOCAL',NULL),(132,'2023-08-16 10:08:29.034146',NULL,'rudghdmlmail@gmail.com','123','$2a$10$OQgtxox1k/9jaYEDME/fbOG8LZO79jLAzLXWJZo9Cgu0CEgApkd7u','ROLE_LOCAL',NULL),(133,'2023-08-16 10:09:48.491293','xoI2vkHQ','myTestIddlqslek@myTestIddlqslek.com','안녕하세요','$2a$10$tWxRkPDdAtq1ZHkjqRIlaOWN1TLlcgFPt8OwksRc4pDz88AFT8hCa','ROLE_LOCAL',NULL),(135,'2023-08-16 10:29:28.922889','b1T8w0jp','wkqxjqb@wkqxjqbwkqxjqb.com','ㅋㅋ','$2a$10$dxo4RLKpOXQ.Nu8OxsXhQOEis4U4vIXWyeoKB4S/6czmTYg8CHwb2','ROLE_LOCAL',NULL),(136,'2023-08-16 13:35:34.676683',NULL,'hoyungss1237@gmail.com','박대균','$2a$10$hqt3KB7r9zS8yQKWQ23ON.gtcX.7ZdIvpgz9lPgslguYYHbXhb0nS','ROLE_SOCIAL',NULL),(137,'2023-08-16 13:40:21.734245','NVDr0404','th@naver.com','취준생','$2a$10$1WWN5O9hUCFF3qP4hF45R.3Z/jE5UhRfnVQKmCCpWuwEFMoE/m1/m','ROLE_UNVERIFIED',NULL),(138,'2023-08-16 16:46:15.573877',NULL,'gmlrud444@gmail.com','정희태','$2a$10$I6S599.GLGZypyAptIquzOovv6hEDgFFC2jRSI/.kaCyGYokU7FKa','ROLE_SOCIAL',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` bigint NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `from_member_visible` bit(1) NOT NULL DEFAULT b'1',
  `is_read` bit(1) NOT NULL DEFAULT b'0',
  `to_member_visible` bit(1) NOT NULL DEFAULT b'1',
  `receiver_id` bigint DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK3278rrjnqnqc6vwm8l4v2xydc` (`receiver_id`),
  KEY `FKt23a3aa8rhuah50164n3syecy` (`sender_id`),
  CONSTRAINT `FK3278rrjnqnqc6vwm8l4v2xydc` FOREIGN KEY (`receiver_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKt23a3aa8rhuah50164n3syecy` FOREIGN KEY (`sender_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'2023-08-14 23:53:14.678243','안녕',_binary '',_binary '\0',_binary '',131,129);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` bigint NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `selected_cnt` bigint DEFAULT '0',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'2023-08-14 06:24:45.000000','VISION','입사 5년 후, 10년 후 자신의 모습은 어떨 것이라고 생각합니까?',0),(2,'2023-08-14 06:24:45.000000','VISION','본인의 직업관은 무엇입니까?',0),(3,'2023-08-14 06:24:45.000000','VISION','당신에게 일이 왜 중요합니까?',0),(4,'2023-08-14 06:24:45.000000','VISION','직장은 어떤 면을 보고 선택합니까?',0),(5,'2023-08-14 06:24:45.000000','VISION','일하는 목적이 무엇입니까?',0),(6,'2023-08-14 06:24:45.000000','VISION','어떤 회사가 훌륭한 회사라고 생각합니까?',0),(7,'2023-08-14 06:24:45.000000','VISION','인생에서 가장 필요한 사항은 무엇이라 생각하나요?',0),(8,'2023-08-14 06:24:45.000000','VISION','중소기업을 선택한 이유는 무엇입니까?',0),(9,'2023-08-14 06:24:45.000000','VISION','바람직한 사원상은 무엇이라고 생각합니까?',0),(10,'2023-08-14 06:24:45.000000','VISION','일과 사생활에 대해서 어떻게 생각합니까?',0),(11,'2023-08-14 06:24:45.000000','VISION','인생의 목표는 무엇입니까?',0),(12,'2023-08-14 06:24:45.000000','VISION','회사 근무를 하면서 가장 중요하다고 생각하는 것은 무엇입니까?',0),(13,'2023-08-14 06:24:45.000000','VISION','기업의 사회적인 책임은 무엇이라고 생각합니까?',0),(14,'2023-08-14 06:24:45.000000','VISION','어떤 아이템을 가지고 어떤 일을 해보고 싶습니까?',0),(15,'2023-08-14 06:24:45.000000','VISION','본인이 리더로 추진했던 일이 있습니까? 있다면 어떤 성과가 나왔는지 말해보세요.',0),(16,'2023-08-14 06:24:45.000000','VISION','본인은 따라가는 스타일입니까, 아니면 주도하는 스타일입니까?',0),(17,'2023-08-14 06:24:45.000000','VISION','자신의 능력 밖을 벗어난 업무가 주어진다면 어떻게 하겠습니까?',0),(18,'2023-08-14 06:24:45.000000','VISION','인생에서 가장 열정적인 순간은 언제였습니까?',0),(19,'2023-08-14 06:24:45.000000','VISION','어떤 일에 적극적으로 임한 순간은 언제였습니까?',0),(20,'2023-08-14 06:24:45.000000','VISION','유능한 영업맨이 되고 싶다고 했는데, 어린 시절의 꿈은 무엇입니까?',0),(21,'2023-08-14 06:24:45.000000','VISION','인생의 최종 꿈은 무엇입니까?',0),(22,'2023-08-14 06:24:45.000000','ADAPT','어떤 경영 스타일일 때 자신의 능력이 최대가 되나',0),(23,'2023-08-14 06:24:45.000000','ADAPT','상사의 말이 확실히 틀렸을 때는 어떻게 할 것인가',0),(24,'2023-08-14 06:24:45.000000','ADAPT','본인 만의 커뮤니케이션 방법은',0),(25,'2023-08-14 06:24:45.000000','ADAPT','직원으로서 필요한 덕목이 무엇이라고 생각합니까?',0),(26,'2023-08-14 06:24:45.000000','ADAPT','입사 후 회사와 맞지 않는다면 어떻게 하시겠습니까?',0),(27,'2023-08-14 06:24:45.000000','ADAPT','당신은 조직에서 어떤 유형의 사람을 싫어하나요?',0),(29,'2023-08-14 06:24:45.000000','ADAPT','당신은 조직 활동을 하면서 어떤 성취를 경험했나요?',0),(30,'2023-08-14 06:24:45.000000','ADAPT','어떤 상황에서 스트레스를 받나요?',0),(31,'2023-08-14 06:24:45.000000','ADAPT','본인의 업무 스타일은 어떤 유형인가요?',0),(32,'2023-08-14 06:24:45.000000','ADAPT','업무 강도가 센 편입니다. 괜찮습니까?',0),(33,'2023-08-14 06:24:45.000000','ADAPT','상사와 의견이 다를 때는 어떻게 대처할 것입니까?',0),(34,'2023-08-14 06:24:45.000000','ADAPT','상사가 부당한 업무 지시를 시킨다면 어떻게 할 것입니까?',0),(35,'2023-08-14 06:24:45.000000','ADAPT','남이 하기 싫어하는 일을 한 경험을 말해보세요.',0),(36,'2023-08-14 06:24:45.000000','ADAPT','노래방에서 몇 시간이나 놀 수 있습니까?',0),(37,'2023-08-14 06:24:45.000000','ADAPT','거래처와의 갈등이 있을 경우 어떻게 대처하겠습니까?',0),(38,'2023-08-14 06:24:45.000000','ADAPT','고객이 불만 사항을 제기하면 어떻게 대처하겠습니까?',0),(39,'2023-08-14 06:24:45.000000','ADAPT','단체 활동에서 의견 충돌이 일어날 경우 어떻게 해결하겠습니까?',0),(40,'2023-08-14 06:24:45.000000','ADAPT','대인 관계에서 가장 중요하게 생각하는 것은 무엇입니까?',0),(41,'2023-08-14 06:24:45.000000','ADAPT','오늘 면접 보는 지원자들 중 누가 제일 먼저 말을 걸었습니까?',0),(43,'2023-08-14 06:24:45.000000','VALUES','가장 존경하는 인물은 누구입니까?',0),(44,'2023-08-14 06:24:45.000000','VALUES','가장 인상 깊게 본 영화 한 편과 이유는 무엇입니까?',0),(46,'2023-08-14 06:24:45.000000','VALUES','가장 존경하는 인물로 부모님을 언급했는데, 그 이유는 무엇입니까?',0),(47,'2023-08-14 06:24:45.000000','VALUES','매일 아침 신문을 읽는다고 했는데, 오늘 아침 신문의 톱 기사는 무엇입니까?',0),(48,'2023-08-14 06:24:45.000000','VALUES','한 달에 책을 몇 권 정도 읽나요?',0),(49,'2023-08-14 06:24:45.000000','VALUES','본인만의 스트레스 해소법에 대해 이야기해주세요.',0),(50,'2023-08-14 06:24:45.000000','VALUES','정보를 수집할 때 효율적인 자신만의 방안은 무엇입니까?',0),(51,'2023-08-14 06:24:45.000000','VALUES','공익과 사익 중 무엇을 더 추구해야 한다고 생각합니까?',0),(52,'2023-08-14 06:24:45.000000','VALUES','평소에 결정할 때 과감하게 하는 편입니까, 신중하게 하는 편입니까?',0),(54,'2023-08-14 06:24:45.000000','VALUES','만약 당신이 일할 때 로비나 뒷거래가 일어난다면 어떻게 하겠습니까?',0),(55,'2023-08-14 06:24:45.000000','VALUES','당신이 면접 관이라면 어떤 것을 중심으로 평가하겠습니까?',0),(56,'2023-08-14 06:24:45.000000','PRESSURE','다른 회사도 지원했습니까?',0),(57,'2023-08-14 06:24:45.000000','PRESSURE','다른 회사는 전형이 어디까지 진행되었습니까?',0),(58,'2023-08-14 06:24:45.000000','PRESSURE','다른 회사에도 합격하면 어느 회사에 입사할 것입니까?',0),(59,'2023-08-14 06:24:45.000000','PRESSURE','열심히 일하겠다고 밝혔는데, 구체적인 계획은 무엇입니까?',0),(61,'2023-08-14 06:24:45.000000','PRESSURE','다른 회사에 지원했다가 떨어진 이유가 무엇입니까?',0),(62,'2023-08-14 06:24:45.000000','PRESSURE','면접을 본 다른 기업이 있습니까?',0),(63,'2023-08-14 06:24:45.000000','PRESSURE','우리 회사에 떨어진다면 어떻게 할 생각입니까?',0),(64,'2023-08-14 06:24:45.000000','PRESSURE','본인이 오늘 면접에 떨어진다면 이유가 무엇이겠습니까?',0),(65,'2023-08-14 06:24:45.000000','PRESSURE','직무가 바뀌어도 괜찮습니까?',0),(66,'2023-08-14 06:24:45.000000','PRESSURE','입사 후 희망 부서에 배치가 되지 않는다면?',0),(67,'2023-08-14 06:24:45.000000','PRESSURE','다른 지원자에 비해 스펙이 부족한 것 같은데 어떻게 생각하세요?',0),(68,'2023-08-14 06:24:45.000000','PRESSURE','스펙이 굉장히 좋은데 왜 이 일을 하려고 하나요?',0),(69,'2023-08-14 06:24:45.000000','PRESSURE','해외 경험이 많은데 경비를 어떻게 조달했나요?',0),(70,'2023-08-14 06:24:45.000000','PRESSURE','군대 면제인데, 그 이유는 무엇인가요?',0),(71,'2023-08-14 06:24:45.000000','PRESSURE','업무에 도움이 될 만한 자격증이 없는데, 업무 하는데 지장이 있지 않을까요?',0),(72,'2023-08-14 06:24:45.000000','PRESSURE','개인과 조직, 어느 것이 더 중요하다고 생각합니까?',0),(73,'2023-08-14 06:24:45.000000','PRESSURE','기존 직원들보다 나이가 많은데 어울릴 수 있는지?',0),(74,'2023-08-14 06:24:45.000000','PRESSURE','학점이 낮은 이유에 대해서 설명해주세요.',0),(75,'2023-08-14 06:24:45.000000','PRESSURE','학점이 다른 지원자에 비해서 높은데, 공부만 하셨나요?',0),(76,'2023-08-14 06:24:45.000000','PRESSURE','가장 학점이 높은 과목과 낮은 과목에 대해서 말해보세요.',0),(77,'2023-08-14 06:24:45.000000','PRESSURE','스트레스를 잘 받을 것 같은데, 실제로는 어떤가요?',0),(78,'2023-08-14 06:24:45.000000','PRESSURE','외동이신데, 사람들과의 관계는 어떤가요?',0),(79,'2023-08-14 06:24:45.000000','PRESSURE','여자가 사회 생활에 더 불리하다고 생각하나요?',0),(80,'2023-08-14 06:24:45.000000','PRESSURE','공백기에 대해 설명해주세요. /졸업 후 지금까지 뭐 했습니까?',0),(81,'2023-08-14 06:24:45.000000','PRESSURE','동기들보다 급여 처우가 안 좋다는 것을 알게 된다면 어떻게 대처하겠습니까?',0),(82,'2023-08-14 06:24:45.000000','PRESSURE','자신의 옆의 두 명보다 어떤 점이 뛰어나 뽑혀야만 된다고 생각합니까?',0),(83,'2023-08-14 06:24:45.000000','JOB','해당 직무와 전공이 맞지 않은데 왜 지원을 했나요?',0),(84,'2023-08-14 06:24:45.000000','JOB','해당 업종의 최근 이슈에 대해서 말해보세요.',0),(85,'2023-08-14 06:24:45.000000','JOB','지원 직무를 위해서 본인이 준비한 것은 무엇입니까?',0),(86,'2023-08-14 06:24:45.000000','JOB','우리 회사 매장에 다녀온 소감이나 개선해야 할 점을 말해보세요.',0),(87,'2023-08-14 06:24:45.000000','JOB','우리 회사의 사업 분야에 대해 아는 대로 설명해주세요.',0),(88,'2023-08-14 06:24:45.000000','JOB','우리 회사의 인재 상 중 본인에 해당하는 한 가지와 그 이유에 대해 말해보세요.',0),(89,'2023-08-14 06:24:45.000000','JOB','우리 회사의 장단점에 대해 설명해주세요.',0),(90,'2023-08-14 06:24:45.000000','JOB','우리 회사의 이미지에 대해 이야기해주세요.',0),(91,'2023-08-14 06:24:45.000000','JOB','우리 회사에 궁금한 점이나 질문이 있나요?',0),(92,'2023-08-14 06:24:45.000000','JOB','지원한 직무가 본인에게 잘 맞는 직무라고 생각하나요?',0),(93,'2023-08-14 06:24:45.000000','JOB','우리 회사를 알게 된 경로는 무엇인가요?',0),(94,'2023-08-14 06:24:45.000000','JOB','조직을 이해하는 관점이 중요한데, 우리 회사의 핵심 가치는 무엇이라고 생각합니까?',0),(95,'2023-08-14 06:24:45.000000','JOB','우리 회사가 나아가야 할 방향이 무엇이라고 생각합니까?',0),(96,'2023-08-14 06:24:45.000000','JOB','우리 회사가 왜 지원자를 뽑아야 된다고 생각합니까?',0),(97,'2023-08-14 06:24:45.000000','JOB','입사를 위해 어떤 노력을 했습니까?',0);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `reported_id` bigint NOT NULL,
  `reporter_id` bigint NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `reason` varchar(255) NOT NULL,
  PRIMARY KEY (`reported_id`,`reporter_id`),
  KEY `FK1uivt2jamt7slp3banldgnsef` (`reporter_id`),
  CONSTRAINT `FK1uivt2jamt7slp3banldgnsef` FOREIGN KEY (`reporter_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FK6ovdlwgf174uw16m9cynvbgal` FOREIGN KEY (`reported_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `max_member` int NOT NULL DEFAULT '1',
  `now_member` int NOT NULL DEFAULT '1',
  `room_name` varchar(255) DEFAULT NULL,
  `room_password` varchar(255) NOT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `UK_2tklvare2e5touoeqsdgdsdgm` (`room_name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (8,'2023-08-16 01:23:10.000000',4,4,'삼x전자 최종면접 4명 모이면 시작',''),(9,'2023-08-16 01:30:43.000000',6,3,'삼x전자 최종면접 무조건 합격하는 방',''),(10,'2023-08-16 01:30:43.000000',5,1,'티X스 면접 다같이 취준 탈출합시다!!!',''),(11,'2023-08-16 01:30:43.000000',4,3,'삼x전자 최종면접 준식님 빨리오세요','1234'),(12,'2023-08-16 01:30:43.000000',3,3,'x한은행 PT면접 연습방',''),(13,'2023-08-16 01:30:43.000000',4,4,'XX화학 준비방 어제에 이어서 해봅시다','1234'),(14,'2023-08-16 01:30:43.000000',4,1,'혼자 명상하는 방',''),(15,'2023-08-16 01:30:43.000000',6,4,'인성면접 질문 서로 내주면서 연습',''),(16,'2023-08-16 01:30:43.000000',4,4,'IT CS면접 연습해보실 분',''),(17,'2023-08-16 01:30:43.000000',4,4,'123',''),(18,'2023-08-16 01:30:43.000000',4,4,'456',''),(21,'2023-08-16 01:31:14.000000',6,3,'삼x전자 최종면접 무조건 합격하는 방2',''),(22,'2023-08-16 01:31:16.000000',5,1,'티X스 면접 다같이 취준 탈출합시다!!!2',''),(23,'2023-08-16 01:31:16.000000',4,3,'삼x전자 최종면접 준식님 빨리오세요2','1234'),(24,'2023-08-16 01:31:17.000000',3,3,'x한은행 PT면접 연습방2',''),(25,'2023-08-16 01:31:17.000000',4,4,'XX화학 준비방 어제에 이어서 해봅시다2','1234'),(26,'2023-08-16 01:31:18.000000',4,1,'혼자 명상하는 방2',''),(27,'2023-08-16 01:31:18.000000',6,4,'인성면접 질문 서로 내주면서 연습2',''),(28,'2023-08-16 01:31:19.000000',4,4,'IT CS면접 연습해보실 분2',''),(29,'2023-08-16 01:31:20.000000',4,4,'1234',''),(30,'2023-08-16 01:31:20.000000',4,4,'4567','');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-16 17:58:24
