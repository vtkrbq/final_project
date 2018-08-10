CREATE DATABASE  IF NOT EXISTS `games_backlog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `games_backlog`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: games_backlog
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `games` (
  `gameName` varchar(255) NOT NULL,
  `gameDev` varchar(255) DEFAULT NULL,
  `gamePlatform` set('PC','PlayStation','xBox','Nintendo','Nintendo3ds','PSP','Switch') DEFAULT NULL,
  `gameGenre` set('RTS','Action','TBS','MOBA','Race','Survival') DEFAULT NULL,
  `gamePic` mediumblob,
  `gameInfo` longtext,
  PRIMARY KEY (`gameName`),
  UNIQUE KEY `gameName_UNIQUE` (`gameName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES ('Dota 2','Valve','PC','MOBA',NULL,'Dota 2 — компьютерная многопользовательская командная игра в жанре multiplayer online battle arena, разработанная Valve Corporation. Является независимым продолжением карты-модификации DotA для игры Warcraft III. Dota 2 работает по модели free-to-play с элементами микроплатежей. Изначально Dota 2 была выпущена на игровом движке Source, после чего в 2015 году была портирована на Source 2, став первой работающей на нём игрой[11][12]. В Dota 2 предусмотрена возможность создавать пользовательские режимы игры, оформление карты и косметические предметы для героев, после чего добавить их в Dota 2 при помощи Steam Workshop. Dota 2 является одной из наиболее популярных игр в Steam и получила в целом положительные отзывы критиков за игровой процесс, качество производства и сохранение положительных сторон своей предшественницы[13]. Среди основных недостатков игры была отмечена сложность обучения.'),('Need For Speed','Electronic Arts','PC','Race',NULL,'Оригинал The Need for Speed был выпущен для 3DO в 1994 году, вскоре после этого были следующие версии для PC DOS (1995), PlayStation и Sega Saturn (1996). Специальное издание The Need for Speed было выпущено только для системы DOS. В последующих версиях на ПК только для Windows.'),('Космические рейнджеры 2','Elemental Games','PC','TBS',NULL,'Космические рейнджеры 2: Доминаторы — компьютерная игра, разработанная в России в 2004 году компанией Elemental Games, издаваемая в России и на территории стран СНГ и Балтии компанией 1С. Является продолжением первой части. На Западе игра была выпущена под названием Space Rangers 2: Rise of the Dominators. Игра совмещает в себе элементы следующих жанров: RPG, пошаговой стратегии, текстового квеста, стратегии в реальном времени, экшен и аркады. Основные события второй части игры разворачиваются спустя 250 лет после окончания войны с клисанами — в 3300 году. Человечество поддерживает отношения с четырьмя другими развитыми цивилизациями нашей галактики: малоками, пеленгами, фэянами и гаальцами. Продолжает развиваться Коалиция Разумных Рас (сокращённо — КРР).');
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `profile` (
  `login` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `nameOfCompletedGames` varchar(45) DEFAULT NULL,
  `nameOfCurrentGames` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`login`),
  KEY `login_idx` (`login`,`name`),
  KEY `nameOfCompletedGame_idx` (`nameOfCompletedGames`),
  KEY `nameOfCurrentGame_idx` (`nameOfCurrentGames`),
  CONSTRAINT `login` FOREIGN KEY (`login`) REFERENCES `users` (`login`),
  CONSTRAINT `nameOfCompletedGame` FOREIGN KEY (`nameOfCompletedGames`) REFERENCES `games` (`gamename`),
  CONSTRAINT `nameOfCurrentGame` FOREIGN KEY (`nameOfCurrentGames`) REFERENCES `games` (`gamename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`login`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Aleksandr','admin','admin'),('Aserty','aserty','aserty'),('Alina','david15','Chashka123'),('Qwerty','qwerty','qwerty'),('Test','test','test');
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

-- Dump completed on 2018-08-11  1:58:45
