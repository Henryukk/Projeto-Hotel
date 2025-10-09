CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `alocacao_vaga`
--

DROP TABLE IF EXISTS `alocacao_vaga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alocacao_vaga` (
  `id` int NOT NULL AUTO_INCREMENT,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `veiculo_id` int NOT NULL,
  `vaga_estacionamento_id` int NOT NULL,
  `check_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_alocacao_vaga_veiculo1_idx` (`veiculo_id`),
  KEY `fk_alocacao_vaga_vaga_estacionamento1_idx` (`vaga_estacionamento_id`),
  KEY `fk_alocacao_vaga_check1_idx` (`check_id`),
  CONSTRAINT `fk_alocacao_vaga_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`),
  CONSTRAINT `fk_alocacao_vaga_vaga_estacionamento1` FOREIGN KEY (`vaga_estacionamento_id`) REFERENCES `vaga_estacionamento` (`id`),
  CONSTRAINT `fk_alocacao_vaga_veiculo1` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alocacao_vaga`
--

LOCK TABLES `alocacao_vaga` WRITE;
/*!40000 ALTER TABLE `alocacao_vaga` DISABLE KEYS */;
/*!40000 ALTER TABLE `alocacao_vaga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caixa`
--

DROP TABLE IF EXISTS `caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caixa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `valor_de_abertura` float NOT NULL,
  `valor_de_fechamento` float NOT NULL,
  `data_hora_abertura` datetime NOT NULL,
  `data_hora_fechamento` datetime NOT NULL,
  `obs` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caixa`
--

LOCK TABLES `caixa` WRITE;
/*!40000 ALTER TABLE `caixa` DISABLE KEYS */;
/*!40000 ALTER TABLE `caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check`
--

DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_hora_cadastro` datetime NOT NULL,
  `data_hora_entrada` datetime NOT NULL,
  `data_hora_saida` datetime NOT NULL,
  `obs` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  `check_quarto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_check_check_quarto1_idx` (`check_quarto_id`),
  CONSTRAINT `fk_check_check_quarto1` FOREIGN KEY (`check_quarto_id`) REFERENCES `check_quarto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check`
--

LOCK TABLES `check` WRITE;
/*!40000 ALTER TABLE `check` DISABLE KEYS */;
/*!40000 ALTER TABLE `check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_hospede`
--

DROP TABLE IF EXISTS `check_hospede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_hospede` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_hospede` varchar(45) NOT NULL,
  `obs` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  `check_id` int NOT NULL,
  `hospede_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_check_hospede_check1_idx` (`check_id`),
  KEY `fk_check_hospede_hospede1_idx` (`hospede_id`),
  CONSTRAINT `fk_check_hospede_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`),
  CONSTRAINT `fk_check_hospede_hospede1` FOREIGN KEY (`hospede_id`) REFERENCES `hospede` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_hospede`
--

LOCK TABLES `check_hospede` WRITE;
/*!40000 ALTER TABLE `check_hospede` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_hospede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_quarto`
--

DROP TABLE IF EXISTS `check_quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_quarto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_hora_inicio` datetime NOT NULL,
  `data_hora_fim` datetime NOT NULL,
  `obs` varchar(45) NOT NULL,
  `status` varchar(1) NOT NULL,
  `quarto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_check_quarto_quarto1_idx` (`quarto_id`),
  CONSTRAINT `fk_check_quarto_quarto1` FOREIGN KEY (`quarto_id`) REFERENCES `quarto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_quarto`
--

LOCK TABLES `check_quarto` WRITE;
/*!40000 ALTER TABLE `check_quarto` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copa_quarto`
--

DROP TABLE IF EXISTS `copa_quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `copa_quarto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantidade` float NOT NULL,
  `data_hora_pedido` datetime NOT NULL,
  `obs` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `check_quarto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_copa_quarto_check_quarto1_idx` (`check_quarto_id`),
  CONSTRAINT `fk_copa_quarto_check_quarto1` FOREIGN KEY (`check_quarto_id`) REFERENCES `check_quarto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copa_quarto`
--

LOCK TABLES `copa_quarto` WRITE;
/*!40000 ALTER TABLE `copa_quarto` DISABLE KEYS */;
/*!40000 ALTER TABLE `copa_quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `fone` varchar(14) DEFAULT NULL,
  `fone2` varchar(14) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(14) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `razao_social` varchar(100) DEFAULT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `inscricao_estadual` varchar(15) DEFAULT NULL,
  `contato` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'fabio ltda','(34)53243-4342','(32)43342-4323','wqeqweq','10209-209','3423','32423434','tubarao','32423434324','2025-05-10 00:00:00','232.323.333-33','32423334','3243434','A','1234','5678','23232132321','21323223');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `fone` varchar(14) DEFAULT NULL,
  `fone2` varchar(14) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(14) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'fabio henry','(  )     -    ','(  )     -    ','sadsa@gmail.com','     -   ','','','','','2025-05-10 00:00:00','232.132.213-32','','','A','fabio.hs22','1234','Masculino'),(2,'fabio2','(12)31231-2321','(23)12312-3213','asds','12312-321','afaffafaffa','qwewqewqe','fafafaf','afafafafafaf','2025-05-10 00:00:00','122.222.222-22','asdasdsa','affaaffaaf','A','fabioaa','sdass','Masculino');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospede`
--

DROP TABLE IF EXISTS `hospede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospede` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `fone` varchar(14) DEFAULT NULL,
  `fone2` varchar(14) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(14) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `razao_social` varchar(100) DEFAULT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `inscricao_estadual` varchar(15) DEFAULT NULL,
  `contato` varchar(100) DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospede`
--

LOCK TABLES `hospede` WRITE;
/*!40000 ALTER TABLE `hospede` DISABLE KEYS */;
INSERT INTO `hospede` VALUES (1,'Fabio Henry Medeiros Silveira','(49)99139-5490','(  )     -    ','henrymedeiros02@gmail.com','88705-211','Pedro Mendes de Farias','Passo Do Gado','Tubarao','Rua','2025-10-04 00:00:00','107.242.539-47','','','A',NULL,NULL,'','',NULL,'','Masculino'),(2,'Julio','(  )     -    ','(  )     -    ','eweree@gmail.com','     -   ','','','','','2025-05-10 00:00:00','107.232.323-23','','','A',NULL,NULL,'','',NULL,'','Masculino'),(3,'fabio2opokpoopopik','(12)31287-6878','(12)31231-2321','dsfsfdsfsd@gmail.com','43424-332','pedoororda','7787878ddsa','tubarao','wqeqwqwe','2025-05-10 00:00:00','107.397.846-78','786876876','wdqweqweqweqwe','A',NULL,NULL,'wqeqwe','21281382121','232323213','dwqedqwew','Feminino'),(4,'fernando','(76)55765-6785','(76)56756-7567','kjhiuhiuh','57467-576','uygyutyawsedasdasdsa','fuytfytft','sharcity','ojhiuh','2025-05-10 00:00:00','107.564.654-65','675766757','cara bom','A',NULL,NULL,'67576567','67457657657','8979879878','tf756r576r67','Masculino'),(5,'ewewewqewqew','(  )     -    ','(  )     -    ','','     -   ','','','','','2025-05-10 00:00:00','213.123.223-12','','','A',NULL,NULL,'','','','','Feminino'),(6,'gabriel','(  )     -    ','(  )     -    ','','     -   ','','','','','2025-05-10 00:00:00','123.123.232-33','','','A',NULL,NULL,'','','','','Masculino'),(7,'Fernando','(29)03129-0391','(43)24234-2342','sdojikahiuodjiaosu@gmail.com','12312-312','weqweqwe','wqeqweqweqw','tubarao','rua skdasopdkaspo','2025-05-10 00:00:00','203.329.048-02','213123123','eweqeqwe','A',NULL,NULL,'23213232','231231232132','12312312312321','231231231231','Masculino');
/*!40000 ALTER TABLE `hospede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'Mazda','A'),(2,'Mazda','A'),(3,'Honda','A'),(4,'Nissan','A');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelo`
--

DROP TABLE IF EXISTS `modelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  `marca_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_modelo_marca1_idx` (`marca_id`),
  CONSTRAINT `fk_modelo_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo`
--

LOCK TABLES `modelo` WRITE;
/*!40000 ALTER TABLE `modelo` DISABLE KEYS */;
INSERT INTO `modelo` VALUES (1,'Civic','A',3),(2,'CRXa','A',3),(3,'350z','A',4);
/*!40000 ALTER TABLE `modelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimento_caixa`
--

DROP TABLE IF EXISTS `movimento_caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimento_caixa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_hora_movimento` datetime NOT NULL,
  `valor` float NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `obs` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  `caixa_id` int NOT NULL,
  `receber_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movimento_caixa_caixa_idx` (`caixa_id`),
  KEY `fk_movimento_caixa_receber1_idx` (`receber_id`),
  CONSTRAINT `fk_movimento_caixa_caixa` FOREIGN KEY (`caixa_id`) REFERENCES `caixa` (`id`),
  CONSTRAINT `fk_movimento_caixa_receber1` FOREIGN KEY (`receber_id`) REFERENCES `receber` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimento_caixa`
--

LOCK TABLES `movimento_caixa` WRITE;
/*!40000 ALTER TABLE `movimento_caixa` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimento_caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordem_servico`
--

DROP TABLE IF EXISTS `ordem_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordem_servico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_hora_cadastro` datetime NOT NULL,
  `data_hora_prevista_inicio` datetime NOT NULL,
  `data_hora_prevista_termino` datetime NOT NULL,
  `obs` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  `check_id` int NOT NULL,
  `servico_id` int NOT NULL,
  `quarto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_oderm_servico_check1_idx` (`check_id`),
  KEY `fk_oderm_servico_servico1_idx` (`servico_id`),
  KEY `fk_oderm_servico_quarto1_idx` (`quarto_id`),
  CONSTRAINT `fk_oderm_servico_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`),
  CONSTRAINT `fk_oderm_servico_quarto1` FOREIGN KEY (`quarto_id`) REFERENCES `quarto` (`id`),
  CONSTRAINT `fk_oderm_servico_servico1` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordem_servico`
--

LOCK TABLES `ordem_servico` WRITE;
/*!40000 ALTER TABLE `ordem_servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_copa`
--

DROP TABLE IF EXISTS `produto_copa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto_copa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `valor` float DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `copa_quarto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_produto_copa_copa_quarto1_idx` (`copa_quarto_id`),
  CONSTRAINT `fk_produto_copa_copa_quarto1` FOREIGN KEY (`copa_quarto_id`) REFERENCES `copa_quarto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_copa`
--

LOCK TABLES `produto_copa` WRITE;
/*!40000 ALTER TABLE `produto_copa` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto_copa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quarto`
--

DROP TABLE IF EXISTS `quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quarto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `capacidade_hospedes` int DEFAULT NULL,
  `metragem` float DEFAULT NULL,
  `identificacao` varchar(45) DEFAULT NULL,
  `andar` int DEFAULT NULL,
  `flag_animais` tinyint DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarto`
--

LOCK TABLES `quarto` WRITE;
/*!40000 ALTER TABLE `quarto` DISABLE KEYS */;
INSERT INTO `quarto` VALUES (1,'a',3,22.3,'2A',2,0,'','A'),(2,'awqeqw',2,22.5,'2B',2,0,'aweqwe','A');
/*!40000 ALTER TABLE `quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receber`
--

DROP TABLE IF EXISTS `receber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receber` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_hora_cadastro` datetime NOT NULL,
  `valor_original` float NOT NULL,
  `desconto` float NOT NULL,
  `acrescimo` float NOT NULL,
  `valor_pago` float NOT NULL,
  `obs` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  `check_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_receber_check1_idx` (`check_id`),
  CONSTRAINT `fk_receber_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receber`
--

LOCK TABLES `receber` WRITE;
/*!40000 ALTER TABLE `receber` DISABLE KEYS */;
/*!40000 ALTER TABLE `receber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_hora_reserva` datetime NOT NULL,
  `data_prevista_entrada` date NOT NULL,
  `data_prevista_saida` date NOT NULL,
  `obs` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `check_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reserva_check1_idx` (`check_id`),
  CONSTRAINT `fk_reserva_check1` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_quarto`
--

DROP TABLE IF EXISTS `reserva_quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_quarto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_hora_inicio` datetime NOT NULL,
  `data_hora_fim` datetime NOT NULL,
  `obs` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `reserva_id` int NOT NULL,
  `quarto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reserva_quarto_reserva1_idx` (`reserva_id`),
  KEY `fk_reserva_quarto_quarto1_idx` (`quarto_id`),
  CONSTRAINT `fk_reserva_quarto_quarto1` FOREIGN KEY (`quarto_id`) REFERENCES `quarto` (`id`),
  CONSTRAINT `fk_reserva_quarto_reserva1` FOREIGN KEY (`reserva_id`) REFERENCES `reserva` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_quarto`
--

LOCK TABLES `reserva_quarto` WRITE;
/*!40000 ALTER TABLE `reserva_quarto` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva_quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (1,'bolosadasdsa','sdfdhbjashbdjhbjasdsa','A'),(2,'servico manutencao quarto','limpar quarto','A');
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaga_estacionamento`
--

DROP TABLE IF EXISTS `vaga_estacionamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaga_estacionamento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `metragem_vaga` float DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaga_estacionamento`
--

LOCK TABLES `vaga_estacionamento` WRITE;
/*!40000 ALTER TABLE `vaga_estacionamento` DISABLE KEYS */;
INSERT INTO `vaga_estacionamento` VALUES (1,'deficiente','assim teste',22.4,'A');
/*!40000 ALTER TABLE `vaga_estacionamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veiculo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(7) NOT NULL,
  `cor` varchar(45) NOT NULL,
  `modelo_id` int NOT NULL,
  `funcionario_id` int DEFAULT NULL,
  `fornecedor_id` int DEFAULT NULL,
  `hospede_id` int DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_veiculo_modelo1_idx` (`modelo_id`),
  KEY `fk_veiculo_funcionario1_idx` (`funcionario_id`),
  KEY `fk_veiculo_fornecedor1_idx` (`fornecedor_id`),
  KEY `fk_veiculo_hospede1_idx` (`hospede_id`),
  CONSTRAINT `fk_veiculo_fornecedor1` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`),
  CONSTRAINT `fk_veiculo_funcionario1` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`),
  CONSTRAINT `fk_veiculo_hospede1` FOREIGN KEY (`hospede_id`) REFERENCES `hospede` (`id`),
  CONSTRAINT `fk_veiculo_modelo1` FOREIGN KEY (`modelo_id`) REFERENCES `modelo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculo`
--

LOCK TABLES `veiculo` WRITE;
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
INSERT INTO `veiculo` VALUES (1,'2323232','Prata',1,NULL,NULL,NULL,'A'),(2,'3232323','Branco',1,NULL,NULL,NULL,'A'),(3,'7878788','Prata',3,NULL,NULL,NULL,'A');
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-09  0:11:42
