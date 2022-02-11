-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: projekt_db
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `aktorzy`
--

DROP TABLE IF EXISTS `aktorzy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aktorzy` (
  `aktor_id` int NOT NULL AUTO_INCREMENT,
  `aktor_imie` varchar(255) NOT NULL,
  `aktor_nazwisko` varchar(255) NOT NULL,
  PRIMARY KEY (`aktor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aktorzy`
--

LOCK TABLES `aktorzy` WRITE;
/*!40000 ALTER TABLE `aktorzy` DISABLE KEYS */;
INSERT INTO `aktorzy` VALUES (1,'Al','Pacino'),(2,'Leonardo','DiCaprio'),(3,'Asher','Pearson'),(4,'Alfredo','Tapia'),(5,'Nathanael','Cabrera'),(6,'Urijah','Cherry'),(7,'Christian','Butler'),(8,'Shirley','Huang'),(9,'Christopher','Nolan');
/*!40000 ALTER TABLE `aktorzy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filmy`
--

DROP TABLE IF EXISTS `filmy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filmy` (
  `film_id` int NOT NULL AUTO_INCREMENT,
  `film_tytul` varchar(255) NOT NULL,
  `film_rok` int NOT NULL,
  `film_jezyk` varchar(55) NOT NULL,
  `film_gatunek` int NOT NULL,
  `film_rezyser` int NOT NULL,
  `film_glownyAktor` int NOT NULL,
  PRIMARY KEY (`film_id`),
  KEY `film_gatunek` (`film_gatunek`),
  KEY `film_rezyser` (`film_rezyser`),
  KEY `film_glownyAktor` (`film_glownyAktor`),
  CONSTRAINT `filmy_ibfk_1` FOREIGN KEY (`film_gatunek`) REFERENCES `gatunki` (`gatunek_id`),
  CONSTRAINT `filmy_ibfk_2` FOREIGN KEY (`film_rezyser`) REFERENCES `rezyserowie` (`rezyser_id`),
  CONSTRAINT `filmy_ibfk_3` FOREIGN KEY (`film_glownyAktor`) REFERENCES `aktorzy` (`aktor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filmy`
--

LOCK TABLES `filmy` WRITE;
/*!40000 ALTER TABLE `filmy` DISABLE KEYS */;
INSERT INTO `filmy` VALUES (1,'Wall-e',2008,'en',1,1,1),(2,'Wesele ',2004,'pl',2,2,2),(3,'Pusty dom',2004,'pl',3,3,3),(4,'Upadek',2004,'pl',4,4,4),(5,'Dogville',2003,'en',5,5,5),(6,'Dzień świra',2002,'pl',6,6,6),(7,'Lewy sercowy ',2002,'pl',7,7,7),(8,'Harmonie Werckmeistera',2000,'pl',8,8,8),(9,'Shrek 2',2004,'en',9,9,9);
/*!40000 ALTER TABLE `filmy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gatunki`
--

DROP TABLE IF EXISTS `gatunki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gatunki` (
  `gatunek_id` int NOT NULL AUTO_INCREMENT,
  `gatunek_nazwa` varchar(255) NOT NULL,
  PRIMARY KEY (`gatunek_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gatunki`
--

LOCK TABLES `gatunki` WRITE;
/*!40000 ALTER TABLE `gatunki` DISABLE KEYS */;
INSERT INTO `gatunki` VALUES (1,'horror'),(2,'romans'),(3,'akcja'),(4,'detektywistyczny'),(5,'dokumentarz'),(6,'dramat'),(7,'animowany'),(8,'live-action'),(9,'szkola'),(10,'komedia');
/*!40000 ALTER TABLE `gatunki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezyserowie`
--

DROP TABLE IF EXISTS `rezyserowie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rezyserowie` (
  `rezyser_id` int NOT NULL AUTO_INCREMENT,
  `rezyser_imie` varchar(255) NOT NULL,
  `rezyser_nazwisko` varchar(255) NOT NULL,
  PRIMARY KEY (`rezyser_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezyserowie`
--

LOCK TABLES `rezyserowie` WRITE;
/*!40000 ALTER TABLE `rezyserowie` DISABLE KEYS */;
INSERT INTO `rezyserowie` VALUES (1,'Steven','Spielberg'),(2,'Martin','Scorsese'),(3,'Alfred','Hitchcock'),(4,'Stanley','Kubrick'),(5,'Quentin','Tarantino'),(6,'Orson','Welles'),(7,'Francis','Ford'),(8,'Billy','Wilder'),(9,'Christopher','Nolan');
/*!40000 ALTER TABLE `rezyserowie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_username` varchar(45) NOT NULL,
  `user_firstname` varchar(45) NOT NULL,
  `user_lastname` varchar(45) NOT NULL,
  `user_address` varchar(55) DEFAULT NULL,
  `user_phone` varchar(12) DEFAULT NULL,
  `user_email` varchar(55) DEFAULT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_theme` varchar(15) DEFAULT NULL,
  `user_language` varchar(15) DEFAULT NULL,
  `user_admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `firstname_UNIQUE` (`user_firstname`),
  UNIQUE KEY `idusers_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Piggy','John','Bobert',NULL,NULL,NULL,'1234',NULL,NULL,0),(2,'admin','admin','admin',NULL,NULL,NULL,'admin',NULL,NULL,0),(3,'root','root','root',NULL,NULL,NULL,'root',NULL,NULL,0),(4,'bnuuy','karol','dawid1',NULL,NULL,NULL,'1234',NULL,NULL,0),(5,'Tofeefef','Jacob','Kowal',NULL,NULL,NULL,'1234',NULL,NULL,0),(6,'sajdias','adsaidjoa','dasfajio',NULL,NULL,NULL,'123',NULL,NULL,0),(7,'','','',NULL,NULL,NULL,'',NULL,NULL,0),(11,'a','s','s',NULL,NULL,NULL,'',NULL,NULL,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-10 23:12:44
