-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: scholarships
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.22.04.2

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
-- Table structure for table `careers`
--

DROP TABLE IF EXISTS `careers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `careers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `career_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6ajdx9ryhxibsww45va5k2ygk` (`career_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `careers`
--

LOCK TABLES `careers` WRITE;
/*!40000 ALTER TABLE `careers` DISABLE KEYS */;
INSERT INTO `careers` VALUES (4,'ADMINISTRACIÓN DE EMPRESAS'),(6,'ARQUITECTURA'),(3,'BIOLOGÍA'),(9,'COMUNICACIÓN SOCIAL Y PERIODISMO'),(5,'DERECHO'),(1,'INGENIERÍA DE SISTEMAS'),(11,'LENGUAS Y CULTURA'),(7,'MATEMÁTICAS'),(8,'MÚSICA'),(10,'PSICOLOGÍA'),(2,'TRADUCCIÓN');
/*!40000 ALTER TABLE `careers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `image_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgkah98oo3wlx756i4k0hnxab4` (`image_id`),
  CONSTRAINT `FKgkah98oo3wlx756i4k0hnxab4` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (2,'MEDELLÍN, COLOMBIA','colanta@email.con','COLANTA','5667896',2,'2022-12-28 02:54:33.569156','2022-12-28 02:54:33.569184'),(4,' MENLO PARK, CALIFORNIA, ESTADOS UNIDOS','meta@email.com','META','1234567890',10,'2023-01-20 18:13:12.976307','2023-01-20 18:13:12.976362'),(5,' MEDELLÍN,ANTIOQUIA,COLOMBIA','terpel@email.com','TERPEL','22233345667',16,'2023-01-25 03:28:04.410891','2023-01-25 03:28:04.410948');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(255) NOT NULL,
  `country_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'COL','COLOMBIA'),(2,'USA','ESTADOS UNIDOS'),(3,'MEX','MÉXICO'),(4,'BRA','BRASIL');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_types`
--

DROP TABLE IF EXISTS `course_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_types` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1bo3ahxvkndvlmqxgd1kmp8ya` (`course_type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_types`
--

LOCK TABLES `course_types` WRITE;
/*!40000 ALTER TABLE `course_types` DISABLE KEYS */;
INSERT INTO `course_types` VALUES (3,'BOOTCAMP'),(5,'CARRERA PROFESIONAL'),(4,'CURSO CORTO'),(2,'ESPECIALIZACIÓN'),(1,'MAESTRÍA');
/*!40000 ALTER TABLE `course_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_id` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (2,'xv6fpgqetvrsex5phxjt','colanta','http://res.cloudinary.com/dveopcjzk/image/upload/v1672196073/xv6fpgqetvrsex5phxjt.png'),(10,'db3pg9thvew14szny99e','Meta-Logo','http://res.cloudinary.com/dveopcjzk/image/upload/v1674238392/db3pg9thvew14szny99e.png'),(11,'fvrbjqjwsad75hecqpod','ninja-backend','http://res.cloudinary.com/dveopcjzk/image/upload/v1674238449/fvrbjqjwsad75hecqpod.jpg'),(12,'ddcxylh6nlvhdsjajwda','Introduccion-programacipon','http://res.cloudinary.com/dveopcjzk/image/upload/v1674238821/ddcxylh6nlvhdsjajwda.jpg'),(13,'najn4rru8wpz8vq8rj5o','veterinaria','http://res.cloudinary.com/dveopcjzk/image/upload/v1674239126/najn4rru8wpz8vq8rj5o.jpg'),(16,'lx1ipl3vfxtbk5rsx3vf','terpel','http://res.cloudinary.com/dveopcjzk/image/upload/v1674617282/lx1ipl3vfxtbk5rsx3vf.jpg');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `languages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `language_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7frcoader3e973dr2c7x3i3k8` (`language_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (6,'CHINO'),(1,'ESPAÑOL'),(5,'FRANCÉS'),(2,'INGLÉS'),(4,'ITALIANO'),(7,'JAPONÉS'),(3,'PORTUGUÉS');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(3,'LEGAL REPRESENTATIVE');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scholarship_career`
--

DROP TABLE IF EXISTS `scholarship_career`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scholarship_career` (
  `scholarship_id` bigint NOT NULL,
  `career_id` bigint NOT NULL,
  KEY `FKo9x068k1ppona3311k0q0tbqe` (`career_id`),
  KEY `FK1nn7x5hxls2pnoceq0kvnaxys` (`scholarship_id`),
  CONSTRAINT `FK1nn7x5hxls2pnoceq0kvnaxys` FOREIGN KEY (`scholarship_id`) REFERENCES `scholarships` (`id`),
  CONSTRAINT `FKo9x068k1ppona3311k0q0tbqe` FOREIGN KEY (`career_id`) REFERENCES `careers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scholarship_career`
--

LOCK TABLES `scholarship_career` WRITE;
/*!40000 ALTER TABLE `scholarship_career` DISABLE KEYS */;
INSERT INTO `scholarship_career` VALUES (4,1),(4,2),(4,7),(6,3),(5,4),(5,2),(5,9),(5,5);
/*!40000 ALTER TABLE `scholarship_career` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scholarships`
--

DROP TABLE IF EXISTS `scholarships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scholarships` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text NOT NULL,
  `finish_date` datetime(6) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `country_id` bigint DEFAULT NULL,
  `course_type_id` bigint DEFAULT NULL,
  `image_id` bigint DEFAULT NULL,
  `language_id` bigint DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbi9b01hpka2r6slf5bcfuxs4b` (`company_id`),
  KEY `FKohyjhnwkqwnysj0v3uy34epty` (`country_id`),
  KEY `FKio740owpr66ppl5dc9l950c5s` (`course_type_id`),
  KEY `FKptxla7d0b81xj9fb1ooemlxrh` (`image_id`),
  KEY `FK14i466ww0ixuufukjmpaiidub` (`language_id`),
  KEY `FKfl6bcdxhgtjln3y2jehkxmwtn` (`status_id`),
  CONSTRAINT `FK14i466ww0ixuufukjmpaiidub` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`),
  CONSTRAINT `FKbi9b01hpka2r6slf5bcfuxs4b` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`),
  CONSTRAINT `FKfl6bcdxhgtjln3y2jehkxmwtn` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`),
  CONSTRAINT `FKio740owpr66ppl5dc9l950c5s` FOREIGN KEY (`course_type_id`) REFERENCES `course_types` (`id`),
  CONSTRAINT `FKohyjhnwkqwnysj0v3uy34epty` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`),
  CONSTRAINT `FKptxla7d0b81xj9fb1ooemlxrh` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scholarships`
--

LOCK TABLES `scholarships` WRITE;
/*!40000 ALTER TABLE `scholarships` DISABLE KEYS */;
INSERT INTO `scholarships` VALUES (4,'2023-01-20 18:14:10.182474','Este bootcamp está diseñado para enseñar a los estudiantes los fundamentos del desarrollo backend, incluyendo el uso de lenguajes de programación como Java, Python y JavaScript, así como las habilidades necesarias para trabajar con bases de datos, APIs y servidores. Los estudiantes aprenderán a crear aplicaciones web escalables y robustas, y se les proporcionará una comprensión profunda de las mejores prácticas de desarrollo de software. Al final del bootcamp, los estudiantes estarán preparados para trabajar como desarrolladores backend en una variedad de industrias','2023-01-24 00:00:00.000000','https://campus.open-bootcamp.com/','2023-01-30 00:00:00.000000','2023-01-20 19:04:11.358702',4,1,3,11,1,2,'BOOTCAMP DE DESARROLLO BACKEND\n'),(5,'2023-01-20 18:20:21.710652','Este curso es una introducción a las bases de la programación. Los estudiantes aprenderán los conceptos fundamentales de la programación, como variables, operadores, estructuras de control de flujo y funciones. También se les enseñará a escribir su primer código utilizando un lenguaje de programación popular como Python o JavaScript. El curso está diseñado para principiantes y no se requiere experiencia previa en programación. Al final del curso, los estudiantes tendrán las habilidades básicas necesarias para continuar con la programación y desarrollar sus propios proyectos.','2023-01-15 00:00:00.000000','https://www.freecodecamp.org/','2023-02-10 00:00:00.000000','2023-01-20 19:33:46.602546',4,4,4,12,3,3,'CURSO DE INTRODUCCIÓN A LA PROGRAMACIÓN'),(6,'2023-01-20 18:25:27.533861','Descripción: Esta maestría está diseñada para veterinarios con experiencia previa en el campo que deseen adquirir conocimientos y habilidades avanzadas en medicina veterinaria. Los estudiantes tendrán la oportunidad de especializarse en áreas como la medicina interna, cirugía, medicina reproductiva, zoonosis, epidemiología y salud pública. La maestría también incluirá cursos de metodología y técnicas avanzadas para investigación, así como una experiencia práctica en un laboratorio o clínica. Los estudiantes realizarán un proyecto de investigación independiente bajo la supervisión de un profesor experto en el campo, y tendrán la oportunidad de presentar sus hallazgos en conferencias y publicaciones científicas. Al final de la maestría, los estudiantes estarán preparados para carreras en el campo de la medicina veterinaria, ya sea en investigación, enseñanza, o en la industria.','2023-03-25 00:00:00.000000','https://colanta.com/sabe-mas/','2023-01-10 00:00:00.000000','2023-01-20 18:46:33.437137',2,1,1,13,2,1,'MAESTRÍA EN MEDICINA VETERINARIA');
/*!40000 ALTER TABLE `scholarships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statuses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n3o9sngkueva0xxqevwov92qs` (`status_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (3,'APLAZADO'),(4,'CANCELADO'),(2,'VENCIDO'),(1,'VIGENTE');
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `dni` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  KEY `FKin8gn4o1hpiwe6qe4ey7ykwq7` (`company_id`),
  CONSTRAINT `FKin8gn4o1hpiwe6qe4ey7ykwq7` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'2022-12-13 18:28:54.212573','10012331489','andres43@email.com','Andrés','andres1234567','Monsalve Gonzalez','2022-12-13 18:29:22.821718',3,NULL),(3,'2022-12-14 22:13:39.836934','999999999999','andres663@email.com','Andrés','andres1234567','Monsalve Gonzalez','2022-12-14 22:17:05.521346',3,2),(4,'2022-12-30 04:23:54.987539','4545656792','cena@email.com','Jhon','cena123456789','Cena','2022-12-30 04:23:54.987641',3,NULL),(5,'2023-01-20 18:13:00.230874','12345434343','reina@email.com','Pepe','reina1234567890','Reina','2023-01-20 18:13:00.230980',3,4),(6,'2023-01-25 03:24:42.708645','3445656768886','jfr@email.com','Juan Felipe','reina1234567890','Castrillón','2023-01-25 03:24:42.708704',3,5),(7,'2023-01-25 03:25:15.179356','9999999999','jr@email.com','Juan Román','reina1234567890','Castrillón','2023-01-25 03:25:15.179393',3,5);
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

-- Dump completed on 2023-01-31 21:23:45
