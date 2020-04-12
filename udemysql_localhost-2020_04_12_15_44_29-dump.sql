-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: testwebapp
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(150) NOT NULL,
  `correctness` varchar(5) NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `answer_question_fk` (`question_id`),
  CONSTRAINT `answer_question_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'Ответ о перегрузке методов 1','false',12),(2,'Ответ о перегрузке методов 2','false',12),(3,'Ответ о перегрузке методов 3(правильный)','true',12),(4,'Ответ о перегрузке методов 4','false',12),(7,'Ответ об области видимости 1(правильный)','true',5),(8,'Ответ об области видимости 2','false',5),(9,'Ответ об области видимости 3','false',5),(10,'Ответ об инкапсуляции 1','false',4),(11,'Ответ об инкапсуляции 2','false',4),(12,'Ответ об инкапсуляции 3','false',4),(13,'Ответ об инкапсуляции 4(правильный)','true',4),(14,'Ответ об инкапсуляции 5(правильный)','true',4),(17,'Ответ о перегрузке методов 1','false',9),(18,'Ответ о перегрузке методов 2(правильный)','true',9),(19,'Ответ о перегрузке методов 1','false',9),(20,'Ответ о ключевом слове this 1','true',7),(21,'Ответ о ключевом слове this 2(правильный)','false',7),(22,'Ответ о комментариях в Java 1','false',11),(23,'Ответ о комментариях в Java 2','false',11),(24,'Ответ о комментариях в Java 3(правильный)','true',11),(25,'Ответ о комментариях в Java 4','false',11),(26,'Ответ о статических полях и методах 1','false',6),(27,'Ответ о статических полях и методах 2','false',6),(28,'Ответ о статических полях и методах 3(правильный)','true',6),(29,'Ответ о модификаторах доступа 1','false',10),(30,'Ответ о модификаторах доступа 2(правильный)','true',10),(31,'Ответ о модификаторах доступа 3(правильный) ','true',10),(32,'Ответ о модификаторах доступа 4','false',10),(33,'Ответ о геттерах и сеттерах 1(правильный)','true',8),(34,'Ответ о геттерах и сеттерах 2','false',8),(35,'Ответ о методе main() 1','false',3),(36,'Ответ о методе main() 2','false',3),(37,'Ответ о методе main() 3(правильный)','true',3),(38,'Ответ об преимуществах ArrayList 1','false',13),(39,'Ответ об преимуществах ArrayList 2(правильный)','true',13),(40,'Ответ об преимуществах ArrayList 3','false',13),(41,'Ответ об преимуществах ArrayList 4(правильный)','true',13),(42,'Ответ об преимуществах Linked 1(правильный)','true',14),(43,'Ответ об преимуществах Linked 2','false',14),(44,'Ответ об преимуществах Linked 3','false',14),(45,'Ответ об Iterator 1','false',15),(46,'Ответ об Iterator 2','false',15),(47,'Ответ об Iterator 3(правильный)','true',15),(48,'Ответ об Iterator 4','false',15),(49,'Ответ о Map 1','false',16),(50,'Ответ о Map 2(правильный)','true',16),(51,'Ответ о TreeSet 1','false',17),(52,'Ответ о TreeSet 2(правильный)','true',17),(53,'Ответ о TreeSet 3','false',17),(90,'Ответ о Node у LinkedList 1(правильный)','true',18),(91,'Ответ о Node у LinkedList 2','false',18),(92,'Ответ об особенностях Set 1(правильный)','true',19),(93,'Ответ об особенностях Set 2','false',19),(94,'Ответ об особенностях Set 3','false',19),(95,'Ответ об особенностях Set 4(правильный)','true',19),(96,'Ответ об Queue 1','false',20),(97,'Ответ об Queue 2','false',20),(98,'Ответ об Queue 3(правильный)','true',20),(99,'о коллекциях данные хранятся как k-v 1','false',21),(100,'о коллекциях данные хранятся как k-v 2','false',21),(101,'о коллекциях данные хранятся как k-v 3','false',21),(102,'о коллекциях данные хранятся как k-v 4(правильный)','true',21),(103,'о коллекциях данные хранятся как k-v 5','false',21),(104,'Ответ о преимуществах HashMap 1(правильный)','true',22),(105,'Ответ о преимуществах HashMap 2(правильный)','true',22),(106,'Ответ о преимуществах HashMap 3','false',22),(107,'Ответ о преимуществах HashMap 4','false',22),(108,'Ответ о иерархии наследования 1','false',23),(109,'Ответ о иерархии наследования 2(правильный)','true',23),(110,'Ответ о иерархии наследования 3','false',23),(111,'Ответ о полиморфизме 1','false',24),(112,'Ответ о полиморфизме 2','false',24),(113,'Ответ о полиморфизме 3(правильный)','true',24),(114,'Ответ о полиморфизме 4','false',24),(115,'Ответ об абстрактном классе 1(правильный)','true',25),(116,'Ответ об абстрактном классе 2','false',25),(117,'Ответ об абстрактном классе 3','false',25),(118,'Ответ об абстрактном методе 1','false',26),(119,'Ответ об абстрактном методе 2(правильный)','true',26),(120,'Ответ о переопределении методов 1','false',27),(121,'Ответ о переопределении методов 2(правильный)','true',27),(122,'Ответ о переопределении методов 3','false',27),(123,'Ответ о методах класса Object (правильный)','true',28),(124,'Ответ о методах класса Object (правильный)','true',28),(125,'Ответ о методах класса Object 3(правильный)','true',28),(126,'Ответ о методах класса Object 4','false',28),(127,'Ответ о методах класса Object 5(правильный)','true',28),(128,'Ответ о сборке мусора 1(правильный)','true',29),(129,'Ответ о сборке мусора 2','false',29),(130,'Ответ о сборке мусора 3','false',29),(131,'Ответ об интерфейсах 1(правильный)','true',30),(132,'Ответ об интерфейсах 2','false',30),(133,'Ответ об интерфейсах 3(правильный)','true',30),(134,'Ответ об интерфейсах 4','false',30),(135,'Ответ об интерфейсах - маркерах','false',31),(136,'Ответ об интерфейсах - маркерах(правильный)','true',31),(137,'Ответ об интерфейсах - маркерах','false',31),(138,'Ответ о функциональных интерфейсах 1(правильный)','true',32),(139,'Ответ о функциональных интерфейсах 2(правильный)','true',32),(140,'Ответ о функциональных интерфейсах 3','false',32),(141,'Ответ о функциональных интерфейсах 4','false',32);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(120) NOT NULL,
  `theme_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `question_theme_fk` (`theme_id`),
  CONSTRAINT `question_theme_fk` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (3,'Для чего нужен метод main()?',1),(4,'Что такое инкапсуляция?',1),(5,'Что такое область видимости?',1),(6,'Для чего нужны статические поля и методы?',1),(7,'Ключевое слово this',1),(8,'Для чего нужны геттеры и сеттеры?',1),(9,'Что делает перегрузка методов?',1),(10,'Для чего нужны модификаторы доступа?',1),(11,'Как писать комментарии в Java?',1),(12,'Что такое перегрузка методов?',1),(13,'Какие преимущества у ArrayList?',3),(14,'Какие преимущества у LinkedList?',3),(15,'Что такое Iterator?',3),(16,'Что такое Map?',3),(17,'Как работает TreeSet?',3),(18,'Что такое Node в LinkedList?',3),(19,'В чем особенность Set\'ов?',3),(20,'Что такое Queue?',3),(21,'В какой коллекции данные хранятся в виде ключ-значение?',3),(22,'В чем преимущество HashMap?',3),(23,'Иерархия наследования?',2),(24,'Полиморфизм?',2),(25,'Что такое абстрактный класс?',2),(26,'Что такое абстрактный метод?',2),(27,'Для чего нужно переопределение методов?',2),(28,'Методы класса Object',2),(29,'Что такое сборка мусора?',2),(30,'Для чего нужны интерфейсы?',2),(31,'Что такое интерфейсы-маркеры?',2),(32,'Что такое функциональные интерфейсы?',2);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionnaire` (
  `id` int NOT NULL AUTO_INCREMENT,
  `theme_id` int NOT NULL,
  `user_id` int NOT NULL,
  `score` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `questionnaire_theme_fk` (`theme_id`),
  KEY `questionnaire_student_fk` (`user_id`),
  CONSTRAINT `questionnaire_student_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `questionnaire_theme_fk` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` VALUES (1,2,2,40.00),(2,1,3,80.00),(3,3,1,100.00);
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theme` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `owner_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (1,'Объекты и классы',1),(2,'Наследование',1),(3,'Коллекции',1);
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'STUDENT',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin@mail.ru','ADMIN'),(2,'Kirill','123','gkttk@mail.ru','STUDENT'),(3,'teacher','321','teacher@mail.ru','TEACHER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-12 15:44:29
