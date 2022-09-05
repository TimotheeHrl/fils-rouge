CREATE DATABASE /*!32312 IF NOT EXISTS*/ `filrouge` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `filrouge`;

--
-- Table structure for table `customer`
--
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `active` bit(1) DEFAULT NULL,
                            `adress` varchar(200) DEFAULT NULL,
                            `city` varchar(100) DEFAULT NULL,
                            `company` varchar(50) DEFAULT NULL,
                            `country` varchar(50) DEFAULT NULL,
                            `firstname` varchar(50) DEFAULT NULL,
                            `lastname` varchar(50) DEFAULT NULL,
                            `mail` varchar(255) DEFAULT NULL,
                            `password` varchar(255) DEFAULT NULL,
                            `phone` varchar(10) DEFAULT NULL,
                            `zip_code` varchar(10) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_9bs0cm53439brbopbpxhg68e` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,_binary '','2 rue du marché','zefze','dccds','Jersey','Dwight','ddsdsf','ddsers@dddddddddsd',NULL,'0987654321','ezfez'),(6,_binary '','3 rue de la chaussure','zefze','dccds','Jakarta','zebre','dzeezdsdsf','ddseevsdvzerzfzezers@dddddddddsd',NULL,'0987654321','ezfez'),(8,_binary '','1 rue du Due','zefze','dccds','Java','croco','dzeezdsdsf','customer@mail.com',NULL,'0987654321','ezfez');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `label` varchar(255) DEFAULT NULL,
                          `number_of_day` bigint(20) DEFAULT NULL,
                          `status` varchar(255) DEFAULT NULL,
                          `type` varchar(255) DEFAULT NULL,
                          `unit_price` double DEFAULT NULL,
                          `customer_id` bigint(20) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`),
                          CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'label',22,'DONE','TRAI',200.2,1),(2,'prout',32,'NO','FULL',211.23,1),(3,'label',22,'DONE','TRAI',200.2,1),(4,'label',22,'DONE','TRAI',200.2,1),(5,'label',22,'DONE','TRAI',200.2,1),(6,'label',22,'DONE','TRAI',200.2,1),(7,'label',22,'DONE','TRAI',200.2,1),(8,'label',22,'DONE','TRAI',200.2,1),(9,'label',22,'DONE','TRAI',200.2,1),(10,'label',22,'DONE','TRAI',200.2,1),(11,'label',22,'DONE','TRAI',200.2,1),(12,'label',22,'DONE','TRAI',200.2,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(20) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
                              `user_id` bigint(20) NOT NULL,
                              `role_id` int(11) NOT NULL,
                              PRIMARY KEY (`user_id`,`role_id`),
                              KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
                              CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                              CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (4,1),(1,2),(2,3),(3,3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `email` varchar(50) DEFAULT NULL,
                         `password` varchar(120) DEFAULT NULL,
                         `username` varchar(20) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
                         UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'moderateur@email.com','$2a$10$hTaiVeLc83iP97j3MqT82OujvbTrUz8UDim7vpDKFn88Uovo6ef/O','moderateur'),(2,'admin@email.com','$2a$10$hTaiVeLc83iP97j3MqT82OujvbTrUz8UDim7vpDKFn88Uovo6ef/O','admin'),(3,'admin2@email.com','$2a$10$hTaiVeLc83iP97j3MqT82OujvbTrUz8UDim7vpDKFn88Uovo6ef/O','admin2'),(4,'admina@email.com','$2a$10$hTaiVeLc83iP97j3MqT82OujvbTrUz8UDim7vpDKFn88Uovo6ef/O','admina');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

UPDATE orders SET status = 'CONFIRMED' ;
UPDATE `user_roles` SET `role_id` = 3 WHERE `user_roles`.`user_id` = 4;

SET FOREIGN_KEY_CHECKS=1;