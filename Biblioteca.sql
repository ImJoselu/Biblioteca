-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.29

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alquiler`
--

LOCK TABLES `alquiler` WRITE;
/*!40000 ALTER TABLE `alquiler` DISABLE KEYS */;
INSERT INTO `alquiler` VALUES (1,'2022-01-01','2022-01-15','2022-01-10',1,1,0),(2,'2022-02-01','2022-02-15','2022-02-20',2,2,0),(3,'2022-03-01','2022-03-15','2022-03-05',3,3,0),(4,'2022-04-01','2022-04-15','2022-04-01',4,4,0),(5,'2022-05-01','2022-05-15','2022-05-15',5,5,0),(6,'2022-06-01','2022-06-15','2022-06-20',6,1,0),(7,'2022-07-01','2022-07-15','2022-07-05',7,2,0),(8,'2022-08-01','2022-08-15','2022-08-01',8,3,0),(9,'2022-09-01','2022-09-15','2022-09-15',9,4,0),(10,'2022-10-01','2022-10-15','2022-10-20',10,5,0),(11,'2023-02-27','2023-03-13',NULL,1,3,0),(12,'2023-02-27','2023-03-13',NULL,1,4,0),(13,'2022-01-01','2022-01-15',NULL,1,1,1),(14,'2022-01-01','2022-01-15',NULL,1,1,1),(15,'2022-01-01','2025-01-15',NULL,1,1,0),(16,'2022-01-01','2022-01-15',NULL,1,1,1),(17,'2022-01-01','2025-01-15',NULL,1,1,0);
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
-- Table structure for table `editorial`
--

DROP TABLE IF EXISTS `editorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editorial` (
  `codigo_editorial` int NOT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `numero_contacto` int NOT NULL,
  PRIMARY KEY (`codigo_editorial`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
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
  `id` int NOT NULL,
  `localizacion` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `prestado` tinyint(1) DEFAULT NULL,
  `fk_libro` char(17) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_libro` (`fk_libro`),
  CONSTRAINT `ejemplar_ibfk_1` FOREIGN KEY (`fk_libro`) REFERENCES `libro` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplar`
--

LOCK TABLES `ejemplar` WRITE;
/*!40000 ALTER TABLE `ejemplar` DISABLE KEYS */;
INSERT INTO `ejemplar` VALUES (1,'Sección de ciencia ficción',0,'978-1-4516-3961-9'),(2,'Sección de terror',1,'978-0-439-70818-0'),(3,'Sección de autoayuda',1,'978-84-01-35280-3'),(4,'Sección de novelas históricas',1,'978-1-609-45078-6'),(5,'Sección de poesía',1,'978-0-307-47474-8');
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
  PRIMARY KEY (`id`),
  KEY `fk_alquiler` (`fk_alquiler`),
  CONSTRAINT `multa_ibfk_1` FOREIGN KEY (`fk_alquiler`) REFERENCES `alquiler` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multa`
--

LOCK TABLES `multa` WRITE;
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
INSERT INTO `multa` VALUES (1,'2022-01-01',1,20.5,'Devolución fuera de plazo',1),(2,'2022-02-01',1,0,'Descartada por pago antes de plazo',2),(3,'2022-03-01',0,15,'Devolución fuera de plazo',3),(4,'2022-04-01',0,10,'Devolución con daños',4),(5,'2022-05-01',1,0,'Descartada por pago antes de plazo',5),(6,'2022-06-01',0,25,'Devolución fuera de plazo',6),(7,'2022-07-01',0,30,'Devolución con daños',7),(8,'2022-08-01',1,0,'Descartada por pago antes de plazo',8),(9,'2022-09-01',0,35,'Devolución fuera de plazo',9),(10,'2022-10-01',0,40,'Devolución con daños',10),(11,'2022-01-01',1,69,NULL,1),(12,'2023-02-16',1,69000,'Multa de Prueba',1),(13,'2023-02-27',1,324,'esfgergser',1),(14,'2023-02-27',1,45365467,'tgsdrhsrft',1),(41,'2023-02-27',1,5,'Entrega Tardia Automatica',16);
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;
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
  `mensaje` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
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
  `password` varchar(16) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `es_administrador` tinyint(1) NOT NULL DEFAULT '0',
  `es_cliente` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'12345678A','Juan','Perez','juanperez@email.com','juanperez','password123',0,1),(2,'87654321B','Maria','Garcia','mariagarcia@email.com','mariagarcia','password456',0,1),(3,'11111111C','Pedro','Sanchez','pedrosanchez@email.com','pedrosanchez','password789',1,0),(4,'22222222D','Ana','Martinez','anamartinez@email.com','anamartinez','password101',0,1),(5,'33333333E','Luis','Gonzalez','luisgonzalez@email.com','luisgonzalez','password102',1,0),(6,'44444444F','Sofia','Rodriguez','sofiarodriguez@email.com','sofiarodriguez','password103',0,1),(7,'55555555G','Carlos','Lopez','carloslopez@email.com','carloslopez','password104',1,0),(8,'66666666H','Paula','Garcia','paulagarcia@email.com','paulagarcia','password105',0,1),(9,'77777777I','Miguel','Martin','miguelmartin@email.com','miguelmartin','password106',1,0),(10,'88888888J','Julia','Perez','juliaperez@email.com','juliaperez','password107',0,1),(11,'99999999K','David','Gomez','davidgomez@email.com','davidgomez','password108',1,0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `eventoMultas` ON SCHEDULE EVERY 5 SECOND STARTS '2023-02-27 14:55:27' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN

  -- Accion 1
  INSERT INTO multa (fk_alquiler, fecha, descartada, importe, observaciones)
  SELECT a.id, CURDATE(), 0, 5, "Entrega Tardia Automatica"
  FROM alquiler a
  WHERE a.fecha_limite < CURDATE()  and a.fecha_entrega is null and a.fuera_plazo = 0;
  -- Accion 2
  UPDATE alquiler a SET a.fuera_plazo = 1
  WHERE a.fecha_limite < CURDATE()  and a.fecha_entrega is null and a.fuera_plazo = 0;

END */ ;;
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

-- Dump completed on 2023-02-27 15:01:32
