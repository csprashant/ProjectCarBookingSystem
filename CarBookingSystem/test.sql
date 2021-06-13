-- MySQL dump 10.13  Distrib 5.5.16, for Win64 (x86)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (40),(40),(40);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservationn`
--

DROP TABLE IF EXISTS `reservationn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservationn` (
  `id` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fromDate` datetime DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `toDate` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `vehicleId` int(11) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `vehicleId` (`vehicleId`),
  CONSTRAINT `reservationn_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservationn_ibfk_2` FOREIGN KEY (`vehicleId`) REFERENCES `vehicle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservationn`
--

LOCK TABLES `reservationn` WRITE;
/*!40000 ALTER TABLE `reservationn` DISABLE KEYS */;
INSERT INTO `reservationn` VALUES (33,'2021-06-12 07:06:56','2021-01-17 00:06:00','','2021-01-19 00:06:00','2021-06-12 12:36:56',18,26,'Ajay'),(35,'2021-06-12 07:09:57','2021-01-16 00:06:00','','2021-01-23 00:06:00','2021-06-12 12:39:57',18,25,'Ajay'),(36,'2021-06-12 10:23:10','2021-01-16 00:06:00','','2021-01-18 00:06:00','2021-06-12 15:53:10',18,24,'Ajay'),(37,'2021-06-12 10:31:02','2021-01-15 00:06:00','','2021-01-23 00:06:00','2021-06-12 16:01:02',34,24,'Himank'),(38,'2021-06-12 13:35:19','2021-01-17 00:06:00','','2021-01-30 00:06:00','2021-06-12 19:05:19',18,25,'Ajay');
/*!40000 ALTER TABLE `reservationn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (17,'2021-06-12 06:39:36','Rupesh@gmail.com','Rupesh','9211',2,'2021-06-12 12:09:35'),(18,'2021-06-12 06:40:00','Ajay@gmail.com','Ajay','9211',2,'2021-06-12 12:10:00'),(19,'2021-06-12 06:40:42','Shruti@yahoo.in','Shruti Yadav','9211',2,'2021-06-12 12:10:42'),(20,'2021-06-12 06:41:05','Sumit@gmail.com','sumit','9634',2,'2021-06-12 12:11:05'),(34,'2021-06-12 07:09:13','Himak@yahoo.in','Himank','9211',2,NULL),(39,'2021-06-13 07:29:52','Prashant@phuse@gmail.com','Prashant','9211',2,NULL),(500,'2021-06-12 05:22:02','admin','admin','admin',1,'2021-06-12 10:51:54');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL,
  `vColor` varchar(255) DEFAULT NULL,
  `vName` varchar(255) DEFAULT NULL,
  `vNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (22,'2021-06-12 06:42:46','2021-06-12 12:12:46','Black','Maruti ZEN','CG 07 4875'),(23,'2021-06-12 06:43:30','2021-06-12 12:13:30','Green','Eeco','CG 07 8585'),(24,'2021-06-12 06:43:58','2021-06-12 12:13:58','White','Alto ','CG 07 8965'),(25,'2021-06-12 06:44:24','2021-06-12 12:14:23','Black','Tata Innova','CG 07 4545'),(26,'2021-06-12 06:44:51','2021-06-12 12:14:51','Silver','Tata Sumo','CG 04 7854'),(27,'2021-06-12 06:45:32','2021-06-12 12:15:32','Brown ','Tata Mahindra','CG 07 8956'),(28,'2021-06-12 06:46:50','2021-06-12 12:16:50','Yellow','Baleno','CG 07 8585'),(29,'2021-06-12 06:47:22','2021-06-12 12:17:22','white','Tata Safari','CG 07 8585');
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-13 15:09:48
