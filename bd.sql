CREATE DATABASE  IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `biblioteca`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `alquiler`
--

DROP TABLE IF EXISTS `alquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alquiler` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date NOT NULL,
  `fecha_limite` date NOT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `fk_usuario_alquiler` int NOT NULL,
  `fk_ejemplar` int NOT NULL,
  `fuera_plazo` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_usuario_alquiler` (`fk_usuario_alquiler`),
  KEY `fk_ejemplar` (`fk_ejemplar`),
  CONSTRAINT `alquiler_ibfk_1` FOREIGN KEY (`fk_usuario_alquiler`) REFERENCES `usuario` (`id`),
  CONSTRAINT `alquiler_ibfk_2` FOREIGN KEY (`fk_ejemplar`) REFERENCES `ejemplar` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alquiler`
--

LOCK TABLES `alquiler` WRITE;
/*!40000 ALTER TABLE `alquiler` DISABLE KEYS */;
INSERT INTO `alquiler` VALUES (1,'2022-01-01','2022-01-15','2022-01-10',1,1,0),(2,'2022-02-01','2022-02-15','2022-02-20',2,2,0),(3,'2022-03-01','2022-03-15','2022-03-05',3,3,0),(4,'2022-04-01','2022-04-15','2022-04-01',4,4,0),(5,'2022-05-01','2022-05-15','2022-05-15',5,5,0),(6,'2022-06-01','2022-06-15','2022-06-20',6,1,0),(7,'2022-07-01','2022-07-15','2022-07-05',7,2,0),(8,'2022-08-01','2022-08-15','2022-08-01',8,3,0),(9,'2022-09-01','2022-09-15','2022-09-15',9,4,0),(10,'2022-10-01','2022-10-15','2022-10-20',10,5,0),(11,'2023-02-27','2023-03-13',NULL,1,3,1),(12,'2023-02-27','2023-03-13',NULL,1,4,1),(13,'2022-01-01','2022-01-15',NULL,1,1,1),(14,'2022-01-01','2022-01-15',NULL,1,1,1),(15,'2022-01-01','2025-01-15',NULL,1,1,0),(16,'2022-01-01','2022-01-15',NULL,1,1,1),(17,'2022-01-01','2025-01-15',NULL,1,1,0),(18,'2023-05-15','2023-05-29',NULL,17,1,0),(19,'2023-05-16','2023-05-30',NULL,5,5,0),(20,'2023-06-04','2023-06-18',NULL,18,3,0);
/*!40000 ALTER TABLE `alquiler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `id` int NOT NULL,
  `nombre` varchar(40) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `apellidos` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nombre` (`nombre`,`apellidos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (11,'Charles','Dickens'),(13,'Dan','Brown'),(12,'Ernest','Cline'),(6,'Ernest','Hemingway'),(7,'F. Scott','Fitzgerald'),(3,'George','R.R. Martin'),(15,'Haruki','Murakami'),(1,'J.K.','Rowling'),(4,'J.R.R.','Tolkien'),(9,'Jane','Austen'),(5,'John','Steinbeck'),(14,'Margaret','Atwood'),(10,'Mark','Twain'),(2,'Stephen','King'),(8,'William','Shakespeare');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentario` (
  `id_comentario` int NOT NULL AUTO_INCREMENT,
  `contenido` text,
  `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_usuario` int DEFAULT NULL,
  `id_post` int DEFAULT NULL,
  `id_comentario_padre` int DEFAULT NULL,
  PRIMARY KEY (`id_comentario`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_post` (`id_post`),
  KEY `id_comentario_padre` (`id_comentario_padre`),
  CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`id_post`) REFERENCES `post` (`id_post`),
  CONSTRAINT `comentario_ibfk_3` FOREIGN KEY (`id_comentario_padre`) REFERENCES `comentario` (`id_comentario`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (1,'Buenos consejos, gracias por compartir!','2023-04-21 13:01:54',3,1,NULL),(2,'A mi me gusta mucho la ciencia ficción, ¿tienes alguna recomendación de un buen libro?','2023-04-21 13:01:54',4,2,1),(3,'Te recomiendo \"El marciano\" de Andy Weir. ¡Es muy bueno!','2023-04-21 13:01:54',5,2,2),(4,'Gracias por la recomendación, voy a buscar ese libro','2023-04-21 13:05:47',6,1,NULL),(5,'No soy fanático de los libros de autoayuda, pero siempre es interesante leer diferentes perspectivas','2023-04-21 13:05:47',2,2,NULL),(6,'Recomiendo \"Cocina en casa\" de Gordon Ramsay','2023-04-21 13:05:47',8,3,NULL),(7,'Guerra y Paz es uno de mis libros favoritos, espero tener tiempo para leerlo de nuevo algún día','2023-04-21 13:05:47',10,4,NULL),(8,'Gracias por compartir este recurso, definitivamente lo revisaré','2023-04-21 13:05:47',1,5,NULL),(9,'Totalmente de acuerdo, \"Guerra y Paz\" es una obra maestra','2023-04-21 13:05:47',5,4,NULL),(10,'¿Qué tipo de libros te gustan?','2023-04-21 13:05:47',3,2,NULL),(11,'No puedo esperar a probar alguna de las recetas del libro que recomendaste','2023-04-21 13:05:47',7,3,NULL),(12,'¿Cuál es el libro más corto que has leído?','2023-04-21 13:05:47',9,4,NULL),(13,'Estoy buscando algunos libros nuevos para leer, gracias por compartir esto','2023-04-21 13:05:47',4,5,NULL),(14,'Definitivamente, \"Guerra y Paz\" es un clásico por una razón','2023-04-21 13:05:47',6,4,5),(15,'Gracias por la recomendación, voy a agregar \"Cocina en casa\" a mi lista de libros por leer','2023-04-21 13:05:47',2,3,7),(16,'Mi género favorito es la ciencia ficción, ¿alguna recomendación?','2023-04-21 13:05:47',1,2,3),(17,'¡Las recetas de Gordon Ramsay son las mejores!','2023-04-21 13:05:47',8,3,7),(18,'Leí \"La Metamorfosis\" de Kafka, es un libro corto pero muy interesante','2023-04-21 13:05:47',10,4,9),(19,'De nada, espero que encuentres algo interesante para leer','2023-04-21 13:05:47',5,5,4),(20,'He estado leyendo más libros de no ficción últimamente, ¿alguna recomendación?','2023-04-21 13:05:47',3,2,13),(21,'¡Disfruta tu lectura!','2023-04-21 13:05:47',7,1,6);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editorial`
--

DROP TABLE IF EXISTS `editorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editorial` (
  `codigo_editorial` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `numero_contacto` int DEFAULT NULL,
  PRIMARY KEY (`codigo_editorial`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editorial`
--

LOCK TABLES `editorial` WRITE;
/*!40000 ALTER TABLE `editorial` DISABLE KEYS */;
INSERT INTO `editorial` VALUES (1,'Penguin Random House',12345678),(2,'HarperCollins',23456789),(3,'Simon & Schuster',34567890),(4,'Hachette Book Group',45678901),(5,'Macmillan Publishers',56789012),(6,'Scholastic Corporation',67890123),(7,'Wiley',78901234),(8,'Oxford University Press',89012345),(9,'Cambridge University Press',90123456),(10,'Elsevier',1234567),(11,'Springer Nature',12345678),(12,'Taylor & Francis',23456789),(13,'Routledge',34567890),(14,'SAGE Publishing',45678901),(15,'Emerald Group Publishing',56789012);
/*!40000 ALTER TABLE `editorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejemplar`
--

DROP TABLE IF EXISTS `ejemplar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejemplar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `localizacion` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `prestado` tinyint(1) DEFAULT NULL,
  `fk_libro` char(17) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_libro` (`fk_libro`),
  CONSTRAINT `ejemplar_ibfk_1` FOREIGN KEY (`fk_libro`) REFERENCES `libro` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplar`
--

LOCK TABLES `ejemplar` WRITE;
/*!40000 ALTER TABLE `ejemplar` DISABLE KEYS */;
INSERT INTO `ejemplar` VALUES (1,'Sección de ciencia ficción',0,'978-1-4516-3961-9'),(2,'Sección de terror',0,'978-0-439-70818-0'),(3,'Sección de autoayuda',0,'978-84-01-35280-3'),(4,'Sección de novelas históricas',0,'978-1-609-45078-6'),(5,'Sección de poesía',0,'978-0-307-47474-8');
/*!40000 ALTER TABLE `ejemplar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (10,'Autoayuda'),(1,'Ficción'),(9,'Histórico'),(3,'Infantil'),(4,'Juvenil'),(8,'Misterio'),(2,'No Ficción'),(5,'Romance'),(6,'Sci-Fi'),(7,'Terror');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `isbn` char(17) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `titulo` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `fk_editorial` int DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  KEY `fk_editorial` (`fk_editorial`),
  CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`fk_editorial`) REFERENCES `editorial` (`codigo_editorial`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES ('978-0-06-231569-3','Matar a un ruiseñor',6),('978-0-14-042462-2','La divina comedia',7),('978-0-15-602775-8','La iliada',8),('978-0-307-47474-8','El gran Gatsby',5),('978-0-439-70818-0','Harry Potter y la piedra filosofal',2),('978-0-679-72897-6','El extranjero',10),('978-0-8021-1789-9','El retrato de Dorian Gray',9),('978-1-4516-3961-9','El señor de los anillos',1),('978-1-609-45078-6','Cien años de soledad',4),('978-84-01-35280-3','Don Quijote de la Mancha',3);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro_escribe_autor`
--

DROP TABLE IF EXISTS `libro_escribe_autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro_escribe_autor` (
  `id` int NOT NULL,
  `fk_libro_escribe` char(17) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `fk_autor_escribe` int DEFAULT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `edad_recomendada` varchar(2) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_libro_escribe` (`fk_libro_escribe`),
  KEY `fk_autor_escribe` (`fk_autor_escribe`),
  CONSTRAINT `libro_escribe_autor_ibfk_1` FOREIGN KEY (`fk_libro_escribe`) REFERENCES `libro` (`isbn`),
  CONSTRAINT `libro_escribe_autor_ibfk_2` FOREIGN KEY (`fk_autor_escribe`) REFERENCES `autor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro_escribe_autor`
--

LOCK TABLES `libro_escribe_autor` WRITE;
/*!40000 ALTER TABLE `libro_escribe_autor` DISABLE KEYS */;
INSERT INTO `libro_escribe_autor` VALUES (1,'978-1-4516-3961-9',1,'2022-01-01','12'),(2,'978-0-439-70818-0',2,'2022-02-01','14'),(3,'978-84-01-35280-3',3,'2022-03-01','16'),(4,'978-1-609-45078-6',4,'2022-04-01','18'),(5,'978-0-307-47474-8',5,'2022-05-01','20');
/*!40000 ALTER TABLE `libro_escribe_autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro_pertenece_genero`
--

DROP TABLE IF EXISTS `libro_pertenece_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro_pertenece_genero` (
  `id` int NOT NULL,
  `fk_libro_pertenece` char(17) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `fk_genero_pertenece` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_libro_pertenece` (`fk_libro_pertenece`),
  KEY `fk_genero_pertenece` (`fk_genero_pertenece`),
  CONSTRAINT `libro_pertenece_genero_ibfk_1` FOREIGN KEY (`fk_libro_pertenece`) REFERENCES `libro` (`isbn`),
  CONSTRAINT `libro_pertenece_genero_ibfk_2` FOREIGN KEY (`fk_genero_pertenece`) REFERENCES `genero` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro_pertenece_genero`
--

LOCK TABLES `libro_pertenece_genero` WRITE;
/*!40000 ALTER TABLE `libro_pertenece_genero` DISABLE KEYS */;
INSERT INTO `libro_pertenece_genero` VALUES (1,'978-1-4516-3961-9',1),(2,'978-0-439-70818-0',2),(3,'978-84-01-35280-3',3),(4,'978-1-609-45078-6',4),(5,'978-0-307-47474-8',5);
/*!40000 ALTER TABLE `libro_pertenece_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multa`
--

DROP TABLE IF EXISTS `multa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `multa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `descartada` tinyint(1) NOT NULL DEFAULT '0',
  `importe` double NOT NULL,
  `observaciones` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `fk_alquiler` int DEFAULT NULL,
  `retraso` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_alquiler` (`fk_alquiler`),
  CONSTRAINT `multa_ibfk_1` FOREIGN KEY (`fk_alquiler`) REFERENCES `alquiler` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multa`
--

LOCK TABLES `multa` WRITE;
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
INSERT INTO `multa` VALUES (1,'2022-01-01',1,20.5,'Devolución fuera de plazo',1,0),(2,'2022-02-01',1,0,'Descartada por pago antes de plazo',2,0),(3,'2022-03-01',0,15,'Devolución fuera de plazo',3,0),(4,'2022-04-01',0,10,'Devolución con daños',4,0),(5,'2022-05-01',1,0,'Descartada por pago antes de plazo',5,0),(6,'2022-06-01',0,25,'Devolución fuera de plazo',6,0),(7,'2022-07-01',0,30,'Devolución con daños',7,0),(8,'2022-08-01',1,0,'Descartada por pago antes de plazo',8,0),(9,'2022-09-01',0,35,'Devolución fuera de plazo',9,0),(10,'2022-10-01',0,40,'Devolución con daños',10,0),(11,'2022-01-01',1,69,NULL,1,0),(12,'2023-02-16',1,69000,'Multa de Prueba',1,0),(13,'2023-02-27',1,324,'esfgergser',1,0),(14,'2023-02-27',1,45365467,'tgsdrhsrft',1,0),(42,'2023-03-21',0,15,'Multa 21/03',1,0),(50,'2023-04-22',0,60,'Entrega Tardia Automatica',11,12),(51,'2023-06-04',0,10,'Entrega Tardia Automatica',12,2),(52,'2023-06-04',0,10,'Entrega Tardia Automatica',13,2),(53,'2023-06-04',0,10,'Entrega Tardia Automatica',14,2),(54,'2023-06-04',0,10,'Entrega Tardia Automatica',16,2),(55,'2023-06-04',0,10,'Entrega Tardia Automatica',18,2),(56,'2023-06-04',0,10,'Entrega Tardia Automatica',19,2);
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id_post` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `contenido` text,
  `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `likes` int DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  PRIMARY KEY (`id_post`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Cómo elegir el mejor libro para ti','Aquí te dejamos algunos consejos para que puedas elegir el libro perfecto para tus gustos y necesidades.','2023-04-21 13:01:54',10,'Consejos de lectura',1),(2,'¿Qué libro recomendarías para leer en vacaciones?','Estoy buscando un libro para leer en mis vacaciones. ¿Cuál recomendarías?','2023-04-21 13:01:54',23,'Recomendaciones de lectura',2),(3,'Nuevo libro de ciencia ficción','Acabo de leer un nuevo libro de ciencia ficción increíble que quiero compartir con todos','2023-04-21 13:04:29',15,'Ciencia ficción',3),(4,'¿Qué opinan sobre los libros de autoayuda?','He estado leyendo algunos libros de autoayuda últimamente y quería saber su opinión','2023-04-21 13:04:29',8,'Autoayuda',7),(5,'Libros de cocina','¿Alguien puede recomendar un buen libro de cocina para principiantes?','2023-04-21 13:04:29',12,'Cocina',2),(6,'El libro más largo que he leído','Acabo de terminar de leer \"Guerra y Paz\" y tengo que decir que ha sido el libro más largo que he leído en mi vida','2023-04-21 13:04:29',23,'Clásicos',9),(7,'Libros en línea gratuitos','He encontrado una página web con una gran cantidad de libros en línea gratuitos para descargar','2023-04-21 13:04:29',33,'Gratis',4);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idusuario` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idusuario_idx` (`idusuario`),
  CONSTRAINT `idusuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROLE_ADMIN',1),(2,'ROLE_USER',2),(3,'ROLE_USER',3),(4,'ROLE_USER',4),(5,'ROLE_USER',5),(6,'ROLE_USER',6),(7,'ROLE_USER',7),(8,'ROLE_USER',8),(9,'ROLE_USER',9),(10,'ROLE_USER',10),(11,'ROLE_USER',11),(12,'ROLE_USER',14),(13,'ROLE_USER',15),(14,'ROLE_USER',16),(15,'ROLE_USER',17),(16,'ROLE_USER',18);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `isbn` varchar(17) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `titulo` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `mensaje` longtext CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `estado` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `fk_usuario_solicitud` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_solicitud` (`fk_usuario_solicitud`),
  CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`fk_usuario_solicitud`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (1,'JohnSmith','johnsmith@gmail.com','978-0-307-47304-5','Cien años de soledad','Me encantaría tener una copia de este libro en la biblioteca','Pendiente',1),(2,'Gity37','cgleztarin@hotmail.com','43354356','rgersgert','rgserthrth','Aceptada',1),(3,'fghxfgdh','cgleztarin@hotmail.com','3456456','thsrth','gfhdrtyhdrty','Aceptada',1);
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nif` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `apellidos` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(40) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `username` varchar(30) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `password` varchar(200) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `es_administrador` tinyint(1) NOT NULL DEFAULT '0',
  `es_cliente` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'12345678A','Juan','Bosh','juanperez@email.com','juanperez','$2a$10$SSr2wIvSN2qivwsGwjZObepgAYDoT39IVYlKO3k.0hhItXBq0psxW',0,1),(2,'87654321B','Maria','Belmonte','mariagarcia@email.com','mariagarcia','$2a$10$zGxenrJayGTJi.x6iLVWpe2zG03j.Cu05Xzgrd2uZE6JFzwiKDoAS',1,0),(3,'11111111C','Pedro','Sanchez','pedrosanchez@email.com','pedrosanchez','$2a$10$P1HueIJpCAEINr2fca0WfO1PYG2mr4Pz361CUleToUlqnyjmoMYve',1,0),(4,'22222222D','Ana','Martinez','anamartinez@email.com','anamartinez','$2a$10$hnZVM6mlNxnmj.k5L6nWe.ZZjpFxxcGyPTrpsd5izVXG9/nRBPre6',0,1),(5,'33333333E','Luis','Gonzalez','luisgonzalez@email.com','luisgonzalez','$2a$10$VGGcJTFpap9f/pH5m2cNAe.lnqnQQ.EDW1N4GNx8RB9TXSpxR5ttK',1,0),(6,'44444444F','Sofia','Rodriguez','sofiarodriguez@email.com','sofiarodriguez','password103',0,1),(7,'55555555G','Carlos','Lopez','carloslopez@email.com','carloslopez','password104',1,0),(8,'66666666H','Paula','Garcia','paulagarcia@email.com','paulagarcia','password105',0,1),(9,'77777777I','Miguel','Martin','miguelmartin@email.com','miguelmartin','password106',1,0),(10,'88888888J','Julia','Perez','juliaperez@email.com','juliaperez','password107',0,1),(11,'99999999K','David','Gomez','davidgomez@email.com','davidgomez','password108',1,0),(12,'11111111A','Jose','Luis','jltortola@gmail.com','ImJoselu','$2a$10$ypF/3MxBf/02hhIMFL4g0.e3vfzILMfS9wXU4Ux3I7h4gWOMtTjwy',0,0),(13,'11111112A','David','Martinez','davex@gmail.com','davex','$2a$10$EZxiBVlbx4p0tYdHajDrNuxEupG/Xc.lfyxcF2NKiZF3QlzoTy2HW',0,0),(14,'11111115A','Iker','Cochino','jltortola@gmail.com','Ikers890','$2a$10$hjiNh2Fv7HmTuqIPip7tDeGPcw7nxhAog1x8QaGtvVGTvGXB670ia',1,0),(15,'11111135A','Florida','purbea','jltortola@gmail.com','florida','$2a$10$R7omF8AZhf.pVqdGnpqQzu7vQXcl0cZEhwhMX4Y9SNWUjTwBxlILS',0,1),(16,'11111152A','David','Bosh','davidm@gmail.com','davex45a','$2a$10$Ub4.Fg6cBhh6ZVEI3HGV6OKZMieIjC1nNFzwP/2nc7zSvoCHe5zS6',1,0),(17,'11111234A','Jose','Luis','jltortcerv@gmail.com','joseluis2003','$2a$10$PupxfuAPsuCZRX9nWcEWoOmlIW4KvQKjTFbb8ue2bnPyAs8l5QPIu',0,1),(18,'23456789D','UsuarioAzure','Aplicacion','azure@hotmail.com','azureaplicacion','$2a$10$aPuO4KOtxXnQx6Lx/CJTSerOL1PIZp8SmSPqXeYY2Y1U7sfvax6a6',1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `creacionRol` AFTER INSERT ON `usuario` FOR EACH ROW BEGIN     
INSERT INTO rol (nombre, idusuario)    
VALUES ('ROLE_USER', new.id); 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'biblioteca'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `eventoMultas` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `eventoMultas` ON SCHEDULE EVERY 24 HOUR STARTS '2023-04-22 13:34:51' ON COMPLETION NOT PRESERVE ENABLE DO begin

	  -- Accion 2 Actualiza todos los dias retraso + 1 como contador de dias. cuando la fecha limite pase y la entrega sea null.
  UPDATE multa m SET m.retraso = m.retraso + 1
  WHERE m.retraso != 0
  and (select fecha_entrega from alquiler where id = m.fk_alquiler) is null;

 
  -- Accion 3 Actualizar multas con retraso, multiplicar sus dias de retraso por 5 (o lo que quiera)
  UPDATE multa m SET m.importe = m.retraso * 5
  WHERE m.retraso != 0
  and (select fecha_entrega from alquiler where id = m.fk_alquiler) is null;

  -- Accion 1  Inserta multa por primera vez cuando no tenga multas con retraso.
  INSERT INTO multa (fk_alquiler, fecha, descartada, importe, observaciones, retraso)
  SELECT a.id, CURDATE(), 0, 5, "Entrega Tardia Automatica", 1
  FROM alquiler a
  WHERE a.fecha_limite < CURDATE()  and a.fecha_entrega is null and (SELECT count(id) FROM multa 
																	WHERE retraso != 0 and fk_alquiler = a.id) = 0;
																


 
end */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'biblioteca'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-04 20:34:45
