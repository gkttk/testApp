-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: testwebapp
-- ------------------------------------------------------
-- Server version	8.0.19

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
  `score` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `questionnaire_theme_fk` (`theme_id`),
  KEY `questionnaire_student_fk` (`user_id`),
  CONSTRAINT `questionnaire_student_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `questionnaire_theme_fk` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` VALUES (22,1,2,40),(23,1,2,80),(24,1,2,60),(25,2,2,100),(26,2,2,40),(27,2,2,60),(28,3,2,20),(29,3,2,100),(30,3,2,60),(31,1,3,100),(32,1,3,60),(33,1,3,80),(34,2,3,0),(35,2,3,40),(36,3,3,100),(37,3,3,80),(38,3,3,60),(39,1,1,80),(40,1,1,40),(41,1,1,100),(42,2,1,0),(43,2,1,80),(44,2,1,80),(45,3,1,80),(46,3,1,100),(47,3,1,80),(48,2,2,60);
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions_questionnaires`
--

DROP TABLE IF EXISTS `questions_questionnaires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions_questionnaires` (
  `question_id` int DEFAULT NULL,
  `questionnaire_id` int DEFAULT NULL,
  KEY `question_id_fk` (`question_id`),
  KEY `questionnaire_id_fk` (`questionnaire_id`),
  CONSTRAINT `question_id_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `questionnaire_id_fk` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions_questionnaires`
--

LOCK TABLES `questions_questionnaires` WRITE;
/*!40000 ALTER TABLE `questions_questionnaires` DISABLE KEYS */;
INSERT INTO `questions_questionnaires` VALUES (9,22),(5,22),(6,22),(3,22),(4,22),(10,23),(11,23),(7,23),(3,23),(8,23),(3,24),(12,24),(4,24),(10,24),(7,24),(31,25),(28,25),(23,25),(29,25),(32,25),(27,26),(28,26),(23,26),(26,26),(29,26),(29,27),(25,27),(24,27),(30,27),(23,27),(15,28),(22,28),(18,28),(20,28),(13,28),(13,29),(19,29),(16,29),(15,29),(17,29),(19,30),(21,30),(16,30),(20,30),(14,30),(5,31),(10,31),(6,31),(11,31),(12,31),(12,32),(5,32),(7,32),(9,32),(6,32),(6,33),(5,33),(10,33),(8,33),(12,33),(24,34),(23,34),(29,34),(25,34),(31,34),(27,35),(30,35),(28,35),(25,35),(29,35),(17,36),(14,36),(13,36),(21,36),(20,36),(18,37),(15,37),(17,37),(16,37),(20,37),(15,38),(17,38),(18,38),(19,38),(22,38),(8,39),(5,39),(3,39),(12,39),(9,39),(6,40),(11,40),(3,40),(5,40),(9,40),(10,41),(4,41),(3,41),(9,41),(8,41),(28,42),(32,42),(23,42),(24,42),(29,42),(23,43),(30,43),(25,43),(27,43),(24,43),(27,44),(29,44),(24,44),(25,44),(32,44),(19,45),(15,45),(18,45),(17,45),(13,45),(18,46),(19,46),(22,46),(17,46),(13,46),(13,47),(20,47),(22,47),(15,47),(17,47),(30,48),(27,48),(26,48),(31,48),(32,48);
/*!40000 ALTER TABLE `questions_questionnaires` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin@mail.ru','ADMIN'),(2,'Kirill','123','gkttk@mail.ru','STUDENT'),(3,'teacher','321','teacher@mail.ru','TEACHER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` tinytext,
  `surname` tinytext,
  `age` int DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_details_user_fk` (`user_id`),
  CONSTRAINT `user_details_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES (1,'ИмяАдмин','ФамилияАдмин',800,'2020-02-01',1),(2,'ИмяУчитель','ФамилияУчитель',35,'2020-02-01',3),(3,'Кирилл','Свиридов',29,'2020-02-01',2);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-15 11:58:14
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: testscheme
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` int DEFAULT NULL,
  `text` tinytext,
  `correctness` tinytext,
  `question_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (3,'Для чего нужен метод main()?',1),(4,'Что такое инкапсуляция?',1),(5,'Что такое область видимости?',1),(6,'Для чего нужны статические поля и методы?',1),(7,'Ключевое слово this',1),(8,'Для чего нужны геттеры и сеттеры?',1),(9,'Что делает перегрузка методов?',1),(10,'Для чего нужны модификаторы доступа?',1),(11,'Как писать комментарии в Java?',1),(12,'Что такое перегрузка методов?',1),(13,'Какие преимущества у ArrayList?',3),(14,'Какие преимущества у LinkedList?',3),(15,'Что такое Iterator?',3),(16,'Что такое Map?',3),(17,'Как работает TreeSet?',3),(18,'Что такое Node в LinkedList?',3),(19,'В чем особенность Set\'ов?',3),(20,'Что такое Queue?',3),(21,'В какой коллекции данные хранятся в виде ключ-значение?',3),(22,'В чем преимущество HashMap?',3),(33,'Иерархия наследования?',2),(34,'Полиморфизм?',2),(35,'Что такое абстрактный класс?',2),(36,'Что такое абстрактный метод?',2),(37,'Для чего нужно переопределение методов?',2),(38,'Методы класса Object',2),(39,'Что такое сборка мусора?',2),(40,'Для чего нужны интерфейсы?',2),(41,'Что такое интерфейсы-маркеры?',2),(42,'Что такое функциональные интерфейсы?',2);
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
  `score` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `questionnaire_student_fk` (`user_id`),
  KEY `questionnaire_theme_fk` (`theme_id`),
  CONSTRAINT `questionnaire_student_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `questionnaire_theme_fk` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` VALUES (2,1,3,80),(3,3,1,100);
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions_questionnaires`
--

DROP TABLE IF EXISTS `questions_questionnaires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions_questionnaires` (
  `question_id` int NOT NULL,
  `questionnaire_id` int NOT NULL,
  KEY `question_id_fk` (`question_id`),
  KEY `questionnaire_id_fk` (`questionnaire_id`),
  CONSTRAINT `question_id_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `questionnaire_id_fk` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions_questionnaires`
--

LOCK TABLES `questions_questionnaires` WRITE;
/*!40000 ALTER TABLE `questions_questionnaires` DISABLE KEYS */;
/*!40000 ALTER TABLE `questions_questionnaires` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `theme_user_fk` (`owner_id`),
  CONSTRAINT `theme_user_fk` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin@mail.ru','ADMIN'),(2,'Kirill','123','gkttk@mail.ru','STUDENT'),(3,'teacher','321','teacher@mail.ru','TEACHER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_details_user_id_uindex` (`user_id`),
  CONSTRAINT `user_details_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES (1,'ИмяАдмин','ФамилияАдмин',800,'2020-02-01',1),(2,'ИмяУчитель','ФамилияУчитель',35,'2020-02-01',3),(3,'Кирилл','Свиридов',29,'2020-02-01',2);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-15 11:58:14
