-- MySQL dump 10.16  Distrib 10.3.9-MariaDB, for Win64 (AMD64)
--
-- Host: 167.99.227.123    Database: smart-auth
-- ------------------------------------------------------
-- Server version	10.4.5-MariaDB-1:10.4.5+maria~bionic

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
-- Table structure for table `apertura_turno`
--

DROP TABLE IF EXISTS `apertura_turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apertura_turno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `turno` bigint(20) NOT NULL,
  `fx` bigint(20) NOT NULL,
  `nominal` decimal(15,2) NOT NULL,
  `precio_promedio` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_moneda_caja_moneda_idx` (`fx`),
  KEY `fk_caja_saldo0_idx` (`turno`),
  CONSTRAINT `fk_caja_saldo_inicial` FOREIGN KEY (`turno`) REFERENCES `turno` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_moneda_saldo_inicial` FOREIGN KEY (`fx`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apertura_turno`
--

LOCK TABLES `apertura_turno` WRITE;
/*!40000 ALTER TABLE `apertura_turno` DISABLE KEYS */;
INSERT INTO `apertura_turno` VALUES (315,22,1,8856100.00,1.00),(316,22,2,19450.00,2900.00),(317,22,3,5240.00,3100.00),(318,22,4,18000.00,160.00),(319,22,5,4000.00,2300.00),(320,23,1,10380550.00,1.00),(321,23,2,8250.00,2900.00),(322,23,3,3920.00,3100.00),(323,23,4,3300.00,160.00),(324,23,5,2300.00,2300.00),(325,24,1,12470000.00,1.00),(326,24,2,7820.00,2900.00),(327,24,3,3410.00,3100.00),(328,24,4,7530.00,160.00),(329,25,1,7842000.00,1.00),(330,25,2,6410.00,2900.00),(331,25,3,5790.00,3100.00),(332,25,4,4240.00,160.00),(333,25,5,2460.00,2300.00),(334,26,1,8856100.00,1.00),(335,26,2,19450.00,2900.00),(336,26,3,5240.00,3100.00),(337,26,4,18000.00,160.00),(338,26,5,1850.00,2400.00),(339,27,1,12470000.00,1.00),(340,27,2,7820.00,2900.00),(341,27,3,3410.00,3100.00),(342,27,4,7530.00,160.00),(343,27,5,5.00,2400.00);
/*!40000 ALTER TABLE `apertura_turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arqueo`
--

DROP TABLE IF EXISTS `arqueo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arqueo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `turno` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `arqueo_turno_FK` (`turno`),
  CONSTRAINT `arqueo_turno_FK` FOREIGN KEY (`turno`) REFERENCES `turno` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=286 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arqueo`
--

LOCK TABLES `arqueo` WRITE;
/*!40000 ALTER TABLE `arqueo` DISABLE KEYS */;
/*!40000 ALTER TABLE `arqueo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caja`
--

DROP TABLE IF EXISTS `caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caja` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `sucursal` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sucursal_turnos_idx` (`sucursal`),
  CONSTRAINT `fk_sucursal_turnos` FOREIGN KEY (`sucursal`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caja`
--

LOCK TABLES `caja` WRITE;
/*!40000 ALTER TABLE `caja` DISABLE KEYS */;
INSERT INTO `caja` VALUES (9,'CAJA1',1),(10,'CAJA1',2),(11,'CAJA2',1),(12,'CAJA2',2);
/*!40000 ALTER TABLE `caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cierre`
--

DROP TABLE IF EXISTS `cierre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cierre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `moneda` bigint(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `saldo_inicial` decimal(15,4) DEFAULT NULL,
  `precio_promedio_inicial` decimal(15,4) DEFAULT NULL,
  `valor_compra_inicial` decimal(15,4) DEFAULT NULL,
  `nominal_compra` decimal(15,4) DEFAULT NULL,
  `precio_compra` decimal(15,4) DEFAULT NULL,
  `valor_compra` decimal(15,4) DEFAULT NULL,
  `nominal_venta` decimal(15,4) DEFAULT NULL,
  `precio_venta` decimal(15,4) DEFAULT NULL,
  `valor_venta` decimal(15,4) DEFAULT NULL,
  `nominal_traslados_entrantes` decimal(15,4) DEFAULT 0.0000,
  `precio_traslado_entrantes` decimal(15,4) DEFAULT 0.0000,
  `saldo_final` decimal(15,4) DEFAULT NULL,
  `precio_promedio_final` decimal(15,4) DEFAULT NULL,
  `valor_compra_final` decimal(15,4) DEFAULT 0.0000,
  `margen` decimal(15,4) DEFAULT 0.0000,
  `utilidad_diaria` decimal(15,4) DEFAULT 0.0000,
  `pyg_caja` decimal(15,4) DEFAULT 0.0000,
  `precio_valoracion` decimal(15,4) DEFAULT 0.0000,
  `margen_prec_valora_y_prec_prom` decimal(15,4) DEFAULT 0.0000,
  `valoracion` decimal(15,4) DEFAULT 0.0000,
  `pyg_valoracion` decimal(15,4) DEFAULT 0.0000,
  `pyg_valoracion_d` decimal(15,4) DEFAULT 0.0000,
  `pyg_bruto` decimal(15,4) DEFAULT 0.0000,
  `renta_caja` decimal(10,5) DEFAULT 0.00000,
  `renta_val` decimal(10,5) DEFAULT 0.00000,
  `renta_bruta` decimal(10,5) DEFAULT 0.00000,
  `egresos` decimal(15,4) DEFAULT 0.0000,
  `egresos_acumulados` decimal(15,4) DEFAULT 0.0000,
  `pyg_neto` decimal(15,4) DEFAULT 0.0000,
  `renta_neta` decimal(10,5) DEFAULT 0.00000,
  `saldo_cop` decimal(15,4) DEFAULT 0.0000,
  `pyg_traslado_diario` decimal(15,4) DEFAULT 0.0000,
  `pyg_traslado_acumulado` decimal(15,4) DEFAULT 0.0000,
  `pyg_bruto_con_traslado` decimal(15,4) DEFAULT 0.0000,
  `pyg_neto_con_traslado` decimal(15,4) DEFAULT 0.0000,
  `fc_diario` decimal(15,4) DEFAULT 0.0000,
  `fc_acumulado` decimal(15,4) DEFAULT 0.0000,
  `valor_port` decimal(15,4) DEFAULT 0.0000,
  `pyg_caja_d` decimal(15,4) DEFAULT 0.0000,
  `valor_traslados` decimal(15,4) DEFAULT 0.0000,
  `sucursal` bigint(20) NOT NULL,
  `traslados_acumulados` decimal(15,4) DEFAULT 0.0000,
  `nominal_traslados_salientes` decimal(15,4) DEFAULT 0.0000,
  `valor_giro_traslado_salientes` decimal(15,4) DEFAULT 0.0000,
  `precio_traslado_salientes` decimal(15,4) DEFAULT 0.0000,
  `egresos_moneda` decimal(15,4) DEFAULT 0.0000,
  `valor_giro_traslado_entrantes` decimal(15,4) DEFAULT 0.0000,
  `saldo_precierre` decimal(15,4) DEFAULT 0.0000 COMMENT 'saldo sin tener en cuenta los traslados',
  `valor_giro_precierre` decimal(15,4) DEFAULT 0.0000,
  `precio_valoracion_anterior` decimal(8,4) DEFAULT 0.0000,
  `valoracion_precierre_anterior` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_precierre_mensual` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_precierre_diaria` decimal(15,4) DEFAULT 0.0000,
  `pyg_traslado_entrante` decimal(15,4) DEFAULT 0.0000,
  `pyg_traslado_saliente` decimal(15,4) DEFAULT 0.0000,
  `valoracion_precierre_actual` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_cierre_diaria` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_cierre_mensual` decimal(15,4) DEFAULT 0.0000,
  `diferencia_egresos_ingresos` decimal(15,4) DEFAULT 0.0000,
  `ingresos` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_cierre_acumulada` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_precierre_acumulada` decimal(15,4) DEFAULT 0.0000,
  PRIMARY KEY (`id`),
  KEY `fk_moneda_caja_moneda_idx` (`moneda`) USING BTREE,
  KEY `cierre_sucursal_FK` (`sucursal`),
  CONSTRAINT `cierre_sucursal_FK` FOREIGN KEY (`sucursal`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_moneda_cierre` FOREIGN KEY (`moneda`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre`
--

LOCK TABLES `cierre` WRITE;
/*!40000 ALTER TABLE `cierre` DISABLE KEYS */;
/*!40000 ALTER TABLE `cierre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cierre_consolidado`
--

DROP TABLE IF EXISTS `cierre_consolidado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cierre_consolidado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `moneda` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `saldo_inicial` decimal(15,4) DEFAULT 0.0000,
  `precio_promedio_inicial` decimal(15,4) DEFAULT 0.0000,
  `valor_compra_inicial` decimal(15,4) DEFAULT 0.0000,
  `nominal_compra` decimal(15,4) DEFAULT 0.0000,
  `precio_compra` decimal(15,4) DEFAULT 0.0000,
  `valor_compra` decimal(15,4) DEFAULT 0.0000,
  `nominal_venta` decimal(15,4) DEFAULT 0.0000,
  `precio_venta` decimal(15,4) DEFAULT 0.0000,
  `valor_venta` decimal(15,4) DEFAULT 0.0000,
  `nominal_traslados_entrantes` decimal(15,4) DEFAULT 0.0000,
  `precio_traslado_entrantes` decimal(15,4) DEFAULT 0.0000,
  `saldo_final` decimal(15,4) DEFAULT 0.0000,
  `precio_promedio_final` decimal(15,4) DEFAULT 0.0000,
  `valor_compra_final` decimal(15,4) DEFAULT 0.0000,
  `margen` decimal(15,4) DEFAULT 0.0000,
  `utilidad_diaria` decimal(15,4) DEFAULT 0.0000,
  `pyg_caja` decimal(15,4) DEFAULT 0.0000,
  `precio_valoracion` decimal(15,4) DEFAULT 0.0000,
  `margen_prec_valora_y_prec_prom` decimal(15,4) DEFAULT 0.0000,
  `valoracion` decimal(15,4) DEFAULT 0.0000,
  `pyg_valoracion` decimal(15,4) DEFAULT 0.0000,
  `pyg_valoracion_d` decimal(15,4) DEFAULT 0.0000,
  `pyg_bruto` decimal(15,4) DEFAULT 0.0000,
  `renta_caja` decimal(10,5) DEFAULT 0.00000,
  `renta_val` decimal(10,5) DEFAULT 0.00000,
  `renta_bruta` decimal(10,5) DEFAULT 0.00000,
  `egresos` decimal(15,4) DEFAULT 0.0000,
  `egresos_acumulados` decimal(15,4) DEFAULT 0.0000,
  `pyg_neto` decimal(15,4) DEFAULT 0.0000,
  `renta_neta` decimal(10,5) DEFAULT 0.00000,
  `saldo_cop` decimal(15,4) DEFAULT 0.0000,
  `pyg_traslado_diario` decimal(15,4) DEFAULT 0.0000,
  `pyg_traslado_acumulado` decimal(15,4) DEFAULT 0.0000,
  `pyg_bruto_con_traslado` decimal(15,4) DEFAULT 0.0000,
  `pyg_neto_con_traslado` decimal(15,4) DEFAULT 0.0000,
  `fc_diario` decimal(15,4) DEFAULT 0.0000,
  `fc_acumulado` decimal(15,4) DEFAULT 0.0000,
  `valor_port` decimal(15,4) DEFAULT 0.0000,
  `pyg_caja_d` decimal(15,4) DEFAULT 0.0000,
  `valor_traslados` decimal(15,4) DEFAULT 0.0000,
  `traslados_acumulados` decimal(15,4) DEFAULT 0.0000,
  `nominal_traslados_salientes` decimal(15,4) DEFAULT 0.0000,
  `valor_giro_traslado_salientes` decimal(15,4) DEFAULT 0.0000,
  `precio_traslado_salientes` decimal(15,4) DEFAULT 0.0000,
  `egresos_moneda` decimal(15,4) DEFAULT 0.0000,
  `valor_giro_traslado_entrantes` decimal(15,4) DEFAULT 0.0000,
  `saldo_precierre` decimal(15,4) DEFAULT 0.0000,
  `valor_giro_precierre` decimal(15,4) DEFAULT 0.0000,
  `precio_valoracion_anterior` decimal(15,4) DEFAULT 0.0000,
  `valoracion_precierre_anterior` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_precierre_mensual` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_precierre_diaria` decimal(15,4) DEFAULT 0.0000,
  `pyg_traslado_entrante` decimal(15,4) DEFAULT 0.0000,
  `pyg_traslado_saliente` decimal(15,4) DEFAULT 0.0000,
  `valoracion_precierre_actual` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_cierre_diaria` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_cierre_mensual` decimal(15,4) DEFAULT 0.0000,
  `diferencia_egresos_ingresos` decimal(15,4) DEFAULT 0.0000,
  `ingresos` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_cierre_acumulada` decimal(15,4) DEFAULT 0.0000,
  `pyg_val_precierre_acumulada` decimal(15,4) DEFAULT 0.0000,
  PRIMARY KEY (`id`),
  KEY `conso-fk_moneda_caja_moneda_idx` (`moneda`) USING BTREE,
  CONSTRAINT `conso-fk_moneda_cierre` FOREIGN KEY (`moneda`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre_consolidado`
--

LOCK TABLES `cierre_consolidado` WRITE;
/*!40000 ALTER TABLE `cierre_consolidado` DISABLE KEYS */;
/*!40000 ALTER TABLE `cierre_consolidado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cierre_turno`
--

DROP TABLE IF EXISTS `cierre_turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cierre_turno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `turno` bigint(20) NOT NULL,
  `moneda` bigint(20) NOT NULL,
  `nominal` decimal(15,2) NOT NULL,
  `precio_promedio` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_moneda_caja_moneda_idx` (`moneda`),
  KEY `fk_caja_saldo0_idx` (`turno`),
  CONSTRAINT `fk_caja_saldo_inicial0` FOREIGN KEY (`turno`) REFERENCES `turno` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_moneda_saldo_inicial0` FOREIGN KEY (`moneda`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre_turno`
--

LOCK TABLES `cierre_turno` WRITE;
/*!40000 ALTER TABLE `cierre_turno` DISABLE KEYS */;
INSERT INTO `cierre_turno` VALUES (478,22,1,8856100.00,1.00),(479,22,2,19450.00,2900.00),(480,22,3,5240.00,3100.00),(481,22,4,18000.00,160.00),(482,22,5,1850.00,2400.00),(483,23,1,10380550.00,1.00),(484,23,2,8250.00,2900.00),(485,23,3,3920.00,3100.00),(486,23,4,3300.00,160.00),(487,23,5,2300.00,2300.00),(488,24,1,12470000.00,1.00),(489,24,2,7820.00,2900.00),(490,24,3,3410.00,3100.00),(491,24,4,7530.00,160.00),(492,24,5,5.00,2400.00),(493,25,1,7842000.00,1.00),(494,25,2,6410.00,2900.00),(495,25,3,5790.00,3100.00),(496,25,4,4240.00,160.00),(497,25,5,2460.00,2300.00),(498,26,1,8876100.00,1.00),(499,26,2,19450.00,2900.00),(500,26,3,5240.00,3100.00),(501,26,4,18000.00,160.00),(502,26,5,1850.00,2400.00),(503,27,1,12499000.00,1.00),(504,27,2,7820.00,2900.00),(505,27,3,3410.00,3100.00),(506,27,4,7680.00,160.00),(507,27,5,5.00,2400.00);
/*!40000 ALTER TABLE `cierre_turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denominacion`
--

DROP TABLE IF EXISTS `denominacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denominacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `valor` decimal(15,2) NOT NULL,
  `moneda` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fx_denominaciones_idx` (`moneda`),
  CONSTRAINT `fk_fx_denominaciones` FOREIGN KEY (`moneda`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denominacion`
--

LOCK TABLES `denominacion` WRITE;
/*!40000 ALTER TABLE `denominacion` DISABLE KEYS */;
INSERT INTO `denominacion` VALUES (113,1.00,1),(114,1000.00,1),(115,2000.00,1),(116,5000.00,1),(117,10000.00,1),(118,20000.00,1),(119,50000.00,1),(120,100000.00,1),(121,1.00,2),(122,2.00,2),(123,5.00,2),(124,10.00,2),(125,20.00,2),(126,50.00,2),(127,100.00,2),(128,5.00,3),(129,10.00,3),(130,20.00,3),(131,50.00,3),(132,100.00,3),(133,200.00,3),(134,500.00,3),(135,1000.00,4),(136,500.00,4),(137,200.00,4),(138,100.00,4),(139,50.00,4),(140,20.00,4),(141,100.00,5),(142,50.00,5),(143,20.00,5),(144,10.00,5),(145,5.00,5);
/*!40000 ALTER TABLE `denominacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denominacion_cantidad`
--

DROP TABLE IF EXISTS `denominacion_cantidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denominacion_cantidad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `denominacion` bigint(20) NOT NULL,
  `operacion` bigint(20) DEFAULT NULL,
  `cantidad` int(11) NOT NULL,
  `flujo_extraordinario` bigint(20) DEFAULT NULL,
  `traslado` bigint(20) DEFAULT NULL,
  `tipo` varchar(30) NOT NULL,
  `saldo` bigint(20) DEFAULT NULL,
  `fondeo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_denominacion_flujos_idx` (`denominacion`),
  KEY `fk_operacion_flujos_idx` (`operacion`),
  KEY `denominacion_cantidad_flujo_extraordinario_FK` (`flujo_extraordinario`),
  KEY `denominacion_cantidad_traslado_FK` (`traslado`),
  KEY `denominacion_cantidad_saldo_FK` (`saldo`),
  KEY `denominacion_cantidad_fondeo_FK` (`fondeo`),
  CONSTRAINT `denominacion_cantidad_flujo_extraordinario_FK` FOREIGN KEY (`flujo_extraordinario`) REFERENCES `flujo_extraordinario` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `denominacion_cantidad_fondeo_FK` FOREIGN KEY (`fondeo`) REFERENCES `fondeo` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `denominacion_cantidad_saldo_FK` FOREIGN KEY (`saldo`) REFERENCES `saldo` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `denominacion_cantidad_traslado_FK` FOREIGN KEY (`traslado`) REFERENCES `traslado` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_denominacion_flujos` FOREIGN KEY (`denominacion`) REFERENCES `denominacion` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_operacion_flujos` FOREIGN KEY (`operacion`) REFERENCES `operacion` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2097 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denominacion_cantidad`
--

LOCK TABLES `denominacion_cantidad` WRITE;
/*!40000 ALTER TABLE `denominacion_cantidad` DISABLE KEYS */;
INSERT INTO `denominacion_cantidad` VALUES (1293,120,NULL,51,NULL,NULL,'ENTRADA',68,NULL),(1294,119,NULL,33,NULL,NULL,'ENTRADA',68,NULL),(1295,118,NULL,110,NULL,NULL,'ENTRADA',68,NULL),(1296,117,NULL,102,NULL,NULL,'ENTRADA',68,NULL),(1297,116,NULL,51,NULL,NULL,'ENTRADA',68,NULL),(1298,115,NULL,48,NULL,NULL,'ENTRADA',68,NULL),(1299,114,NULL,50,NULL,NULL,'ENTRADA',68,NULL),(1300,113,NULL,9550,NULL,NULL,'ENTRADA',68,NULL),(1301,127,NULL,40,NULL,NULL,'ENTRADA',69,NULL),(1302,126,NULL,62,NULL,NULL,'ENTRADA',69,NULL),(1303,125,NULL,39,NULL,NULL,'ENTRADA',69,NULL),(1304,124,NULL,21,NULL,NULL,'ENTRADA',69,NULL),(1305,123,NULL,30,NULL,NULL,'ENTRADA',69,NULL),(1306,122,NULL,0,NULL,NULL,'ENTRADA',69,NULL),(1307,121,NULL,10,NULL,NULL,'ENTRADA',69,NULL),(1308,134,NULL,2,NULL,NULL,'ENTRADA',70,NULL),(1309,133,NULL,6,NULL,NULL,'ENTRADA',70,NULL),(1310,132,NULL,5,NULL,NULL,'ENTRADA',70,NULL),(1311,131,NULL,10,NULL,NULL,'ENTRADA',70,NULL),(1312,130,NULL,12,NULL,NULL,'ENTRADA',70,NULL),(1313,129,NULL,30,NULL,NULL,'ENTRADA',70,NULL),(1314,128,NULL,36,NULL,NULL,'ENTRADA',70,NULL),(1315,135,NULL,0,NULL,NULL,'ENTRADA',71,NULL),(1316,136,NULL,0,NULL,NULL,'ENTRADA',71,NULL),(1317,137,NULL,15,NULL,NULL,'ENTRADA',71,NULL),(1318,138,NULL,3,NULL,NULL,'ENTRADA',71,NULL),(1319,139,NULL,0,NULL,NULL,'ENTRADA',71,NULL),(1320,140,NULL,0,NULL,NULL,'ENTRADA',71,NULL),(1321,141,NULL,5,NULL,NULL,'ENTRADA',72,NULL),(1322,142,NULL,15,NULL,NULL,'ENTRADA',72,NULL),(1323,143,NULL,15,NULL,NULL,'ENTRADA',72,NULL),(1324,144,NULL,25,NULL,NULL,'ENTRADA',72,NULL),(1325,145,NULL,100,NULL,NULL,'ENTRADA',72,NULL),(1547,120,NULL,30,NULL,NULL,'ENTRADA',104,NULL),(1548,119,NULL,30,NULL,NULL,'ENTRADA',104,NULL),(1549,118,NULL,69,NULL,NULL,'ENTRADA',104,NULL),(1550,117,NULL,81,NULL,NULL,'ENTRADA',104,NULL),(1551,116,NULL,150,NULL,NULL,'ENTRADA',104,NULL),(1552,115,NULL,151,NULL,NULL,'ENTRADA',104,NULL),(1553,114,NULL,100,NULL,NULL,'ENTRADA',104,NULL),(1554,113,NULL,0,NULL,NULL,'ENTRADA',104,NULL),(1555,127,NULL,30,NULL,NULL,'ENTRADA',105,NULL),(1556,126,NULL,40,NULL,NULL,'ENTRADA',105,NULL),(1557,125,NULL,40,NULL,NULL,'ENTRADA',105,NULL),(1558,124,NULL,40,NULL,NULL,'ENTRADA',105,NULL),(1559,123,NULL,34,NULL,NULL,'ENTRADA',105,NULL),(1560,122,NULL,0,NULL,NULL,'ENTRADA',105,NULL),(1561,121,NULL,40,NULL,NULL,'ENTRADA',105,NULL),(1562,134,NULL,3,NULL,NULL,'ENTRADA',106,NULL),(1563,133,NULL,10,NULL,NULL,'ENTRADA',106,NULL),(1564,132,NULL,10,NULL,NULL,'ENTRADA',106,NULL),(1565,131,NULL,12,NULL,NULL,'ENTRADA',106,NULL),(1566,130,NULL,20,NULL,NULL,'ENTRADA',106,NULL),(1567,129,NULL,24,NULL,NULL,'ENTRADA',106,NULL),(1568,128,NULL,10,NULL,NULL,'ENTRADA',106,NULL),(1569,135,NULL,2,NULL,NULL,'ENTRADA',107,NULL),(1570,136,NULL,4,NULL,NULL,'ENTRADA',107,NULL),(1571,137,NULL,1,NULL,NULL,'ENTRADA',107,NULL),(1572,138,NULL,0,NULL,NULL,'ENTRADA',107,NULL),(1573,139,NULL,0,NULL,NULL,'ENTRADA',107,NULL),(1574,140,NULL,2,NULL,NULL,'ENTRADA',107,NULL),(1575,141,NULL,10,NULL,NULL,'ENTRADA',108,NULL),(1576,142,NULL,15,NULL,NULL,'ENTRADA',108,NULL),(1577,143,NULL,20,NULL,NULL,'ENTRADA',108,NULL),(1578,144,NULL,21,NULL,NULL,'ENTRADA',108,NULL),(1579,145,NULL,20,NULL,NULL,'ENTRADA',108,NULL),(1951,120,47,150,NULL,NULL,'SALIDA',NULL,NULL),(1952,118,47,100,NULL,NULL,'SALIDA',NULL,NULL),(1953,113,47,2200,NULL,NULL,'SALIDA',NULL,NULL),(1954,119,47,100,NULL,NULL,'SALIDA',NULL,NULL),(1955,117,47,20,NULL,NULL,'SALIDA',NULL,NULL),(1956,116,47,7,NULL,NULL,'SALIDA',NULL,NULL),(1957,127,47,40,NULL,NULL,'ENTRADA',NULL,NULL),(1958,125,47,50,NULL,NULL,'ENTRADA',NULL,NULL),(1959,126,47,50,NULL,NULL,'ENTRADA',NULL,NULL),(1960,124,47,33,NULL,NULL,'ENTRADA',NULL,NULL),(1961,125,48,3,NULL,NULL,'SALIDA',NULL,NULL),(1962,126,48,2,NULL,NULL,'SALIDA',NULL,NULL),(1963,127,48,10,NULL,NULL,'SALIDA',NULL,NULL),(1964,115,48,2,NULL,NULL,'ENTRADA',NULL,NULL),(1965,118,48,15,NULL,NULL,'ENTRADA',NULL,NULL),(1966,117,48,6,NULL,NULL,'ENTRADA',NULL,NULL),(1967,120,48,20,NULL,NULL,'ENTRADA',NULL,NULL),(1968,119,48,20,NULL,NULL,'ENTRADA',NULL,NULL),(1969,116,49,1,NULL,NULL,'SALIDA',NULL,NULL),(1970,118,49,15,NULL,NULL,'SALIDA',NULL,NULL),(1971,113,49,200,NULL,NULL,'SALIDA',NULL,NULL),(1972,117,49,9,NULL,NULL,'SALIDA',NULL,NULL),(1973,119,49,20,NULL,NULL,'SALIDA',NULL,NULL),(1974,120,49,15,NULL,NULL,'SALIDA',NULL,NULL),(1975,133,49,4,NULL,NULL,'ENTRADA',NULL,NULL),(1976,132,49,1,NULL,NULL,'ENTRADA',NULL,NULL),(1977,130,49,2,NULL,NULL,'ENTRADA',NULL,NULL),(1978,133,50,1,NULL,NULL,'SALIDA',NULL,NULL),(1979,131,50,1,NULL,NULL,'SALIDA',NULL,NULL),(1980,119,50,8,NULL,NULL,'ENTRADA',NULL,NULL),(1981,116,50,1,NULL,NULL,'ENTRADA',NULL,NULL),(1982,113,50,500,NULL,NULL,'ENTRADA',NULL,NULL),(1983,115,50,1,NULL,NULL,'ENTRADA',NULL,NULL),(1984,118,50,4,NULL,NULL,'ENTRADA',NULL,NULL),(1985,120,50,3,NULL,NULL,'ENTRADA',NULL,NULL),(1986,118,51,30,NULL,NULL,'SALIDA',NULL,NULL),(1987,119,51,40,NULL,NULL,'SALIDA',NULL,NULL),(1988,120,51,20,NULL,NULL,'SALIDA',NULL,NULL),(1989,117,51,10,NULL,NULL,'SALIDA',NULL,NULL),(1990,115,51,2,NULL,NULL,'SALIDA',NULL,NULL),(1991,135,51,20,NULL,NULL,'ENTRADA',NULL,NULL),(1992,136,51,15,NULL,NULL,'ENTRADA',NULL,NULL),(1993,138,51,1,NULL,NULL,'ENTRADA',NULL,NULL),(1994,137,51,9,NULL,NULL,'ENTRADA',NULL,NULL),(1995,135,52,15,NULL,NULL,'SALIDA',NULL,NULL),(1996,137,52,4,NULL,NULL,'SALIDA',NULL,NULL),(1997,138,52,1,NULL,NULL,'SALIDA',NULL,NULL),(1998,136,52,16,NULL,NULL,'SALIDA',NULL,NULL),(1999,119,52,20,NULL,NULL,'ENTRADA',NULL,NULL),(2000,113,52,1000,NULL,NULL,'ENTRADA',NULL,NULL),(2001,118,52,25,NULL,NULL,'ENTRADA',NULL,NULL),(2002,120,52,30,NULL,NULL,'ENTRADA',NULL,NULL),(2003,117,52,4,NULL,NULL,'ENTRADA',NULL,NULL),(2004,120,NULL,38,NULL,NULL,'ENTRADA',142,NULL),(2005,119,NULL,59,NULL,NULL,'ENTRADA',142,NULL),(2006,118,NULL,29,NULL,NULL,'ENTRADA',142,NULL),(2007,117,NULL,98,NULL,NULL,'ENTRADA',142,NULL),(2008,116,NULL,93,NULL,NULL,'ENTRADA',142,NULL),(2009,115,NULL,1,NULL,NULL,'ENTRADA',142,NULL),(2010,114,NULL,50,NULL,NULL,'ENTRADA',142,NULL),(2011,113,NULL,49100,NULL,NULL,'ENTRADA',142,NULL),(2012,127,NULL,100,NULL,NULL,'ENTRADA',143,NULL),(2013,126,NULL,98,NULL,NULL,'ENTRADA',143,NULL),(2014,125,NULL,127,NULL,NULL,'ENTRADA',143,NULL),(2015,124,NULL,133,NULL,NULL,'ENTRADA',143,NULL),(2016,123,NULL,100,NULL,NULL,'ENTRADA',143,NULL),(2017,122,NULL,40,NULL,NULL,'ENTRADA',143,NULL),(2018,121,NULL,100,NULL,NULL,'ENTRADA',143,NULL),(2019,134,NULL,2,NULL,NULL,'ENTRADA',144,NULL),(2020,133,NULL,13,NULL,NULL,'ENTRADA',144,NULL),(2021,132,NULL,11,NULL,NULL,'ENTRADA',144,NULL),(2022,131,NULL,6,NULL,NULL,'ENTRADA',144,NULL),(2023,130,NULL,9,NULL,NULL,'ENTRADA',144,NULL),(2024,129,NULL,6,NULL,NULL,'ENTRADA',144,NULL),(2025,128,NULL,0,NULL,NULL,'ENTRADA',144,NULL),(2026,135,NULL,12,NULL,NULL,'ENTRADA',145,NULL),(2027,136,NULL,6,NULL,NULL,'ENTRADA',145,NULL),(2028,137,NULL,12,NULL,NULL,'ENTRADA',145,NULL),(2029,138,NULL,6,NULL,NULL,'ENTRADA',145,NULL),(2030,139,NULL,0,NULL,NULL,'ENTRADA',145,NULL),(2031,140,NULL,0,NULL,NULL,'ENTRADA',145,NULL),(2032,141,NULL,10,NULL,NULL,'ENTRADA',146,NULL),(2033,142,NULL,10,NULL,NULL,'ENTRADA',146,NULL),(2034,143,NULL,10,NULL,NULL,'ENTRADA',146,NULL),(2035,144,NULL,10,NULL,NULL,'ENTRADA',146,NULL),(2036,145,NULL,10,NULL,NULL,'ENTRADA',146,NULL),(2037,120,NULL,80,NULL,NULL,'ENTRADA',147,NULL),(2038,119,NULL,81,NULL,NULL,'ENTRADA',147,NULL),(2039,118,NULL,17,NULL,NULL,'ENTRADA',147,NULL),(2040,117,NULL,9,NULL,NULL,'ENTRADA',147,NULL),(2041,116,NULL,0,NULL,NULL,'ENTRADA',147,NULL),(2042,115,NULL,0,NULL,NULL,'ENTRADA',147,NULL),(2043,114,NULL,19,NULL,NULL,'ENTRADA',147,NULL),(2044,113,NULL,0,NULL,NULL,'ENTRADA',147,NULL),(2045,127,NULL,40,NULL,NULL,'ENTRADA',148,NULL),(2046,126,NULL,40,NULL,NULL,'ENTRADA',148,NULL),(2047,125,NULL,50,NULL,NULL,'ENTRADA',148,NULL),(2048,124,NULL,50,NULL,NULL,'ENTRADA',148,NULL),(2049,123,NULL,50,NULL,NULL,'ENTRADA',148,NULL),(2050,122,NULL,0,NULL,NULL,'ENTRADA',148,NULL),(2051,121,NULL,70,NULL,NULL,'ENTRADA',148,NULL),(2052,134,NULL,2,NULL,NULL,'ENTRADA',149,NULL),(2053,133,NULL,5,NULL,NULL,'ENTRADA',149,NULL),(2054,132,NULL,10,NULL,NULL,'ENTRADA',149,NULL),(2055,131,NULL,4,NULL,NULL,'ENTRADA',149,NULL),(2056,130,NULL,10,NULL,NULL,'ENTRADA',149,NULL),(2057,129,NULL,1,NULL,NULL,'ENTRADA',149,NULL),(2058,128,NULL,0,NULL,NULL,'ENTRADA',149,NULL),(2059,135,NULL,4,NULL,NULL,'ENTRADA',150,NULL),(2060,136,NULL,4,NULL,NULL,'ENTRADA',150,NULL),(2061,137,NULL,4,NULL,NULL,'ENTRADA',150,NULL),(2062,138,NULL,4,NULL,NULL,'ENTRADA',150,NULL),(2063,139,NULL,5,NULL,NULL,'ENTRADA',150,NULL),(2064,140,NULL,4,NULL,NULL,'ENTRADA',150,NULL),(2065,141,NULL,0,NULL,NULL,'ENTRADA',151,NULL),(2066,142,NULL,0,NULL,NULL,'ENTRADA',151,NULL),(2067,143,NULL,0,NULL,NULL,'ENTRADA',151,NULL),(2068,144,NULL,0,NULL,NULL,'ENTRADA',151,NULL),(2069,145,NULL,1,NULL,NULL,'ENTRADA',151,NULL),(2070,120,53,2,NULL,NULL,'SALIDA',NULL,NULL),(2071,117,53,3,NULL,NULL,'SALIDA',NULL,NULL),(2072,141,53,1,NULL,NULL,'ENTRADA',NULL,NULL),(2073,141,54,1,NULL,NULL,'SALIDA',NULL,NULL),(2074,119,54,1,NULL,NULL,'ENTRADA',NULL,NULL),(2075,120,54,2,NULL,NULL,'ENTRADA',NULL,NULL),(2076,120,55,2,NULL,NULL,'SALIDA',NULL,NULL),(2077,118,55,4,NULL,NULL,'SALIDA',NULL,NULL),(2078,127,55,1,NULL,NULL,'ENTRADA',NULL,NULL),(2079,127,56,1,NULL,NULL,'SALIDA',NULL,NULL),(2080,120,56,2,NULL,NULL,'ENTRADA',NULL,NULL),(2081,117,56,9,NULL,NULL,'ENTRADA',NULL,NULL),(2082,120,57,3,NULL,NULL,'SALIDA',NULL,NULL),(2083,132,57,1,NULL,NULL,'ENTRADA',NULL,NULL),(2084,132,58,1,NULL,NULL,'SALIDA',NULL,NULL),(2085,118,58,1,NULL,NULL,'ENTRADA',NULL,NULL),(2086,120,58,3,NULL,NULL,'ENTRADA',NULL,NULL),(2087,117,59,4,NULL,NULL,'SALIDA',NULL,NULL),(2088,138,59,1,NULL,NULL,'ENTRADA',NULL,NULL),(2089,138,60,1,NULL,NULL,'SALIDA',NULL,NULL),(2090,114,60,19,NULL,NULL,'ENTRADA',NULL,NULL),(2091,117,61,3,NULL,NULL,'SALIDA',NULL,NULL),(2092,120,61,2,NULL,NULL,'SALIDA',NULL,NULL),(2093,141,61,1,NULL,NULL,'ENTRADA',NULL,NULL),(2094,141,62,1,NULL,NULL,'SALIDA',NULL,NULL),(2095,120,62,2,NULL,NULL,'ENTRADA',NULL,NULL),(2096,119,62,1,NULL,NULL,'ENTRADA',NULL,NULL);
/*!40000 ALTER TABLE `denominacion_cantidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nit` varchar(45) NOT NULL,
  `razon_social` varchar(150) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `ciiu` varchar(150) NOT NULL,
  `concepto` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flujo_extraordinario`
--

DROP TABLE IF EXISTS `flujo_extraordinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flujo_extraordinario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idsoipc` varchar(450) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  `valor` decimal(15,2) NOT NULL,
  `turno` bigint(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `estado` varchar(20) NOT NULL,
  `fx` bigint(20) NOT NULL,
  `descripcion` varchar(256) DEFAULT NULL,
  `criterio` varchar(100) NOT NULL,
  `naturaleza` varchar(100) DEFAULT NULL,
  `receptor` varchar(100) DEFAULT NULL,
  `cuenta` varchar(100) DEFAULT NULL,
  `concepto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `flujo_extraordinario_turno_FK` (`turno`),
  KEY `flujo_extraordinario_fx_FK` (`fx`),
  CONSTRAINT `flujo_extraordinario_fx_FK` FOREIGN KEY (`fx`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `flujo_extraordinario_turno_FK` FOREIGN KEY (`turno`) REFERENCES `turno` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flujo_extraordinario`
--

LOCK TABLES `flujo_extraordinario` WRITE;
/*!40000 ALTER TABLE `flujo_extraordinario` DISABLE KEYS */;
/*!40000 ALTER TABLE `flujo_extraordinario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fondeo`
--

DROP TABLE IF EXISTS `fondeo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fondeo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `sucursal` bigint(20) DEFAULT NULL,
  `turno_origen` bigint(20) DEFAULT NULL,
  `turno_destino` bigint(20) DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `valor_giro` decimal(14,2) NOT NULL,
  `fx` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_traslados_idx` (`turno_origen`),
  KEY `fk_usuario_traslados_idx1` (`turno_destino`),
  KEY `fk_fx_traslado_idx` (`fx`),
  KEY `fk_sucursal_destino_idx` (`sucursal`),
  CONSTRAINT `fk_fx_fondeo` FOREIGN KEY (`fx`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fondeo_turno_FK` FOREIGN KEY (`turno_origen`) REFERENCES `turno` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fondeo_turno_FK_1` FOREIGN KEY (`turno_destino`) REFERENCES `turno` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fondeo`
--

LOCK TABLES `fondeo` WRITE;
/*!40000 ALTER TABLE `fondeo` DISABLE KEYS */;
/*!40000 ALTER TABLE `fondeo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `functionality`
--

DROP TABLE IF EXISTS `functionality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `functionality` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(400) NOT NULL,
  `menu` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `menu_UNIQUE` (`menu`),
  KEY `fk_menu_functionalities_idx` (`menu`),
  CONSTRAINT `fk_menu_functionalities` FOREIGN KEY (`menu`) REFERENCES `menu` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `functionality`
--

LOCK TABLES `functionality` WRITE;
/*!40000 ALTER TABLE `functionality` DISABLE KEYS */;
/*!40000 ALTER TABLE `functionality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `functionality_resource`
--

DROP TABLE IF EXISTS `functionality_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `functionality_resource` (
  `functionality` bigint(20) NOT NULL,
  `resource` bigint(20) NOT NULL,
  PRIMARY KEY (`functionality`,`resource`),
  KEY `fk_role_permission_permission_idx` (`resource`),
  CONSTRAINT `fk_functionality_resources` FOREIGN KEY (`functionality`) REFERENCES `functionality` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_resource_functionalities` FOREIGN KEY (`resource`) REFERENCES `resource` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `functionality_resource`
--

LOCK TABLES `functionality_resource` WRITE;
/*!40000 ALTER TABLE `functionality_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `functionality_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fx`
--

DROP TABLE IF EXISTS `fx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `concepto` varchar(245) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_UNIQUE` (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fx`
--

LOCK TABLES `fx` WRITE;
/*!40000 ALTER TABLE `fx` DISABLE KEYS */;
INSERT INTO `fx` VALUES (1,'COP','Peso Colombiano'),(2,'USD','Dolar Americano'),(3,'EUR','Euro'),(4,'MXN','Peso Mexicano'),(5,'CAD','Dolar Canadiense');
/*!40000 ALTER TABLE `fx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fx_sucursal`
--

DROP TABLE IF EXISTS `fx_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fx_sucursal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fx` bigint(20) NOT NULL,
  `sucursal` bigint(20) NOT NULL,
  `precio_compra` decimal(15,2) NOT NULL,
  `precio_venta` decimal(15,2) NOT NULL,
  `precio_valoracion` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_fx_sucursal` (`fx`,`sucursal`),
  KEY `fk_sucursal_fx_sucursales_idx` (`sucursal`),
  KEY `fk_fx_fx_sucursales_idx` (`fx`),
  CONSTRAINT `fk_fx_fx_sucursales` FOREIGN KEY (`fx`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_sucursal_fx_sucursales` FOREIGN KEY (`sucursal`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fx_sucursal`
--

LOCK TABLES `fx_sucursal` WRITE;
/*!40000 ALTER TABLE `fx_sucursal` DISABLE KEYS */;
INSERT INTO `fx_sucursal` VALUES (1,2,1,2800.00,2900.00,2850.00),(2,3,1,3000.00,3200.00,3100.00),(3,2,2,2800.00,2900.00,2850.00),(4,3,2,3000.00,3200.00,3100.00),(5,4,1,160.00,190.00,175.00),(6,5,1,2300.00,2500.00,2400.00),(7,4,2,160.00,190.00,175.00),(8,5,2,2300.00,2500.00,2400.00);
/*!40000 ALTER TABLE `fx_sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_arqueo`
--

DROP TABLE IF EXISTS `item_arqueo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_arqueo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arqueo` bigint(20) NOT NULL,
  `saldo_inicial` decimal(15,4) DEFAULT 0.0000,
  `saldo_final` decimal(15,4) DEFAULT 0.0000,
  `nominal_compras` decimal(15,4) DEFAULT 0.0000,
  `nominal_ventas` decimal(15,4) DEFAULT 0.0000,
  `nominal_egresos` decimal(15,4) DEFAULT 0.0000,
  `nominal_ingresos` decimal(15,4) DEFAULT 0.0000,
  `nominal_traslados_entrantes` decimal(15,4) DEFAULT 0.0000,
  `nominal_traslados_salientes` decimal(15,4) DEFAULT 0.0000,
  `moneda` bigint(20) NOT NULL,
  `saldo_real` decimal(15,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_arqueo_arqueo_FK` (`arqueo`),
  KEY `item_arqueo_fx_FK` (`moneda`),
  CONSTRAINT `item_arqueo_arqueo_FK` FOREIGN KEY (`arqueo`) REFERENCES `arqueo` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `item_arqueo_fx_FK` FOREIGN KEY (`moneda`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_arqueo`
--

LOCK TABLES `item_arqueo` WRITE;
/*!40000 ALTER TABLE `item_arqueo` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_arqueo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `index` int(11) NOT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `target` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_menu_parent_idx` (`parent`),
  CONSTRAINT `fk_menu_children` FOREIGN KEY (`parent`) REFERENCES `menu` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacion`
--

DROP TABLE IF EXISTS `operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_operacion` datetime NOT NULL,
  `fx_base` bigint(20) NOT NULL,
  `fx_oper` bigint(20) NOT NULL,
  `tipo` varchar(10) NOT NULL,
  `nominal` decimal(14,2) NOT NULL,
  `valor_fx_operacion` decimal(14,2) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `id_soipc` varchar(45) DEFAULT NULL,
  `valor_valoracion` decimal(14,2) NOT NULL,
  `turno` bigint(20) DEFAULT NULL,
  `turno_edicion` bigint(20) DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fx_operaciones_idx` (`fx_base`),
  KEY `fk_fx_operaciones_idx1` (`fx_oper`),
  KEY `fk_sucursal_operaciones_idx` (`turno`),
  KEY `fk_turno_edicion_operaciones` (`turno_edicion`),
  CONSTRAINT `fk_fx_bases` FOREIGN KEY (`fx_base`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_fx_operaciones` FOREIGN KEY (`fx_oper`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_turno_edicion_operaciones` FOREIGN KEY (`turno_edicion`) REFERENCES `turno` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_turno_operacion` FOREIGN KEY (`turno`) REFERENCES `turno` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
INSERT INTO `operacion` VALUES (47,'2019-07-15 16:52:38',1,2,'COMPRA',7830.00,2840.00,'EJECUTADA',NULL,2850.00,22,NULL,NULL),(48,'2019-07-15 16:53:37',1,2,'VENTA',1160.00,2900.00,'EJECUTADA',NULL,2850.00,22,NULL,NULL),(49,'2019-07-15 16:54:54',1,3,'COMPRA',940.00,3080.00,'EJECUTADA',NULL,3100.00,22,NULL,NULL),(50,'2019-07-15 16:56:03',1,3,'VENTA',250.00,3150.00,'EJECUTADA',NULL,3100.00,22,NULL,NULL),(51,'2019-07-15 16:57:42',1,4,'COMPRA',29400.00,160.00,'EJECUTADA',NULL,175.00,22,NULL,NULL),(52,'2019-07-15 16:59:11',1,4,'VENTA',23900.00,190.00,'EJECUTADA',NULL,175.00,22,NULL,NULL),(53,'2019-07-15 20:00:46',1,5,'COMPRA',100.00,2300.00,'EJECUTADA',NULL,2400.00,26,NULL,NULL),(54,'2019-07-15 20:02:34',1,5,'VENTA',100.00,2500.00,'EJECUTADA',NULL,2400.00,26,NULL,NULL),(55,'2019-07-15 20:04:39',1,2,'COMPRA',100.00,2800.00,'EJECUTADA',NULL,2850.00,27,NULL,NULL),(56,'2019-07-15 20:04:57',1,2,'VENTA',100.00,2900.00,'EJECUTADA',NULL,2850.00,27,NULL,NULL),(57,'2019-07-15 20:05:14',1,3,'COMPRA',100.00,3000.00,'EJECUTADA',NULL,3100.00,27,NULL,NULL),(58,'2019-07-15 20:05:31',1,3,'VENTA',100.00,3200.00,'EJECUTADA',NULL,3100.00,27,NULL,NULL),(59,'2019-07-15 20:09:08',1,4,'COMPRA',250.00,160.00,'EJECUTADA',NULL,175.00,27,NULL,NULL),(60,'2019-07-15 20:09:39',1,4,'VENTA',100.00,190.00,'EJECUTADA',NULL,175.00,27,NULL,NULL),(61,'2019-07-15 20:09:58',1,5,'COMPRA',100.00,2300.00,'EJECUTADA',NULL,2400.00,27,NULL,NULL),(62,'2019-07-15 20:10:20',1,5,'VENTA',100.00,2500.00,'EJECUTADA',NULL,2400.00,27,NULL,NULL);
/*!40000 ALTER TABLE `operacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` bigint(20) NOT NULL,
  `functionality` bigint(20) NOT NULL,
  `access_types` varchar(45) NOT NULL COMMENT 'Lectura, Escritura, Edición, Eliminación',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_role_funct_access` (`role`,`functionality`,`access_types`),
  KEY `fk_functionality_roles_idx` (`functionality`),
  CONSTRAINT `fk_functionality_roles` FOREIGN KEY (`functionality`) REFERENCES `functionality` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_role_functionalies` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provision`
--

DROP TABLE IF EXISTS `provision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provision` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_registro` datetime NOT NULL,
  `fecha_cumplimiento` datetime NOT NULL,
  `sucursal` bigint(20) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `clase` varchar(45) NOT NULL,
  `fx` bigint(20) NOT NULL,
  `monto` decimal(14,2) NOT NULL,
  `estado` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sucursal_provisiones_idx` (`sucursal`),
  KEY `fk_fx_provisiones_idx` (`fx`),
  CONSTRAINT `fk_fx_provisiones` FOREIGN KEY (`fx`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_sucursal_provisiones` FOREIGN KEY (`sucursal`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provision`
--

LOCK TABLES `provision` WRITE;
/*!40000 ALTER TABLE `provision` DISABLE KEYS */;
/*!40000 ALTER TABLE `provision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `uri` varchar(45) NOT NULL,
  `access_type` varchar(45) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_OPERADOR'),(4,'ROL_EJEMPLO');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saldo`
--

DROP TABLE IF EXISTS `saldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saldo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `caja` bigint(20) NOT NULL,
  `moneda` bigint(20) NOT NULL,
  `nominal` decimal(15,2) NOT NULL,
  `precio_promedio` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_caja_caja_moneda_idx` (`caja`),
  KEY `fk_moneda_caja_moneda_idx` (`moneda`),
  CONSTRAINT `fk_caja_saldo` FOREIGN KEY (`caja`) REFERENCES `caja` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_moneda_saldo` FOREIGN KEY (`moneda`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo`
--

LOCK TABLES `saldo` WRITE;
/*!40000 ALTER TABLE `saldo` DISABLE KEYS */;
INSERT INTO `saldo` VALUES (68,11,1,10380550.00,1.00),(69,11,2,8250.00,2900.00),(70,11,3,3920.00,3100.00),(71,11,4,3300.00,160.00),(72,11,5,2300.00,2300.00),(104,12,1,7842000.00,1.00),(105,12,2,6410.00,2900.00),(106,12,3,5790.00,3100.00),(107,12,4,4240.00,160.00),(108,12,5,2460.00,2300.00),(142,9,1,8876100.00,1.00),(143,9,2,19450.00,2900.00),(144,9,3,5240.00,3100.00),(145,9,4,18000.00,160.00),(146,9,5,1850.00,2400.00),(147,10,1,12499000.00,1.00),(148,10,2,7820.00,2900.00),(149,10,3,3410.00,3100.00),(150,10,4,7680.00,160.00),(151,10,5,5.00,2400.00);
/*!40000 ALTER TABLE `saldo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `ip` varchar(45) NOT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_session_user_idx` (`user`),
  CONSTRAINT `fk_user_sessions` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `chk_principal` tinyint(4) NOT NULL,
  `telefonos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,'CC1','CC1','Calle 150 323','1',1,'3262'),(2,'CC2','CC2','calle 232 ','1',0,'515515');
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono`
--

DROP TABLE IF EXISTS `telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telefono` varchar(45) NOT NULL,
  `sucursal` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sucursal_telefonos_idx` (`sucursal`),
  CONSTRAINT `fk_sucursal_telefonos` FOREIGN KEY (`sucursal`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono`
--

LOCK TABLES `telefono` WRITE;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traslado`
--

DROP TABLE IF EXISTS `traslado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `traslado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `sucursal_origen` bigint(20) NOT NULL,
  `sucursal_destino` bigint(20) NOT NULL,
  `tipo_traslado` varchar(45) NOT NULL,
  `turno_origen` bigint(20) DEFAULT NULL,
  `turno_destino` bigint(20) DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `precio_traslado` decimal(14,2) NOT NULL,
  `valor_giro` decimal(14,2) NOT NULL,
  `fx` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_traslados_idx` (`turno_origen`),
  KEY `fk_usuario_traslados_idx1` (`turno_destino`),
  KEY `fk_fx_traslado_idx` (`fx`),
  KEY `fk_sucursal_origen_idx` (`sucursal_origen`),
  KEY `fk_sucursal_destino_idx` (`sucursal_destino`),
  CONSTRAINT `fk_fx_traslado` FOREIGN KEY (`fx`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_sucursal_destino` FOREIGN KEY (`sucursal_destino`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_sucursal_origen` FOREIGN KEY (`sucursal_origen`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `traslado_turno_FK` FOREIGN KEY (`turno_origen`) REFERENCES `turno` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `traslado_turno_FK_1` FOREIGN KEY (`turno_destino`) REFERENCES `turno` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traslado`
--

LOCK TABLES `traslado` WRITE;
/*!40000 ALTER TABLE `traslado` DISABLE KEYS */;
/*!40000 ALTER TABLE `traslado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno`
--

DROP TABLE IF EXISTS `turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `caja` bigint(20) NOT NULL,
  `usuario` bigint(20) NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_turno_idx` (`usuario`),
  KEY `fk_caja_turno_idx` (`caja`),
  CONSTRAINT `fk_caja_turno` FOREIGN KEY (`caja`) REFERENCES `caja` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_turno` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
INSERT INTO `turno` VALUES (22,9,1,'2019-07-15 13:37:16','2019-07-15 19:54:32'),(23,11,1,'2019-07-15 17:10:52','2019-07-15 19:55:50'),(24,10,1,'2019-07-15 19:56:04','2019-07-15 19:57:06'),(25,12,1,'2019-07-15 19:57:20','2019-07-15 19:57:36'),(26,9,1,'2019-07-15 19:58:34','2019-07-15 20:03:29'),(27,10,1,'2019-07-15 20:04:11','2019-07-15 20:10:36');
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `locked` tinyint(1) NOT NULL,
  `failed_logins` int(11) NOT NULL,
  `role` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_idx` (`role`),
  CONSTRAINT `fk_role_users` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','$2a$04$fx4HJlEUiHFwn2ajnmIp8e3GyVBNij6bKEuMZwfZYUykeXFPHinoO','123456',1,0,0,1),(2,'admin','$2a$04$fx4HJlEUiHFwn2ajnmIp8e3GyVBNij6bKEuMZwfZYUykeXFPHinoO','123456',1,0,0,1),(3,'operador','$2a$04$fx4HJlEUiHFwn2ajnmIp8e3GyVBNij6bKEuMZwfZYUykeXFPHinoO','123456',1,0,0,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  KEY `fk_user_usuario_idx` (`user`),
  CONSTRAINT `fk_user_usuario` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'smart-auth'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-15 20:19:35
