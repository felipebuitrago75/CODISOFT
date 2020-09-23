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
) ENGINE=InnoDB AUTO_INCREMENT=363 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apertura_turno`
--

LOCK TABLES `apertura_turno` WRITE;
/*!40000 ALTER TABLE `apertura_turno` DISABLE KEYS */;
INSERT INTO `apertura_turno` VALUES (345,28,1,17236650.00,1.00),(346,28,2,27000.00,2900.00),(347,28,3,9000.00,3100.00),(348,29,1,1000000.00,1.00),(349,29,2,700.00,2900.00),(350,29,3,160.00,3100.00),(351,30,1,19000000.00,1.00),(352,30,2,14000.00,2900.00),(353,30,3,9000.00,3100.00),(354,31,1,312000.00,1.00),(355,31,2,230.00,2900.00),(356,31,3,200.00,3100.00),(357,32,1,18436650.00,1.00),(358,32,2,26620.00,2897.94),(359,32,3,9000.00,3098.92),(360,33,1,1000000.00,1.00),(361,33,2,700.00,2897.92),(362,33,3,160.00,3098.92);
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
) ENGINE=InnoDB AUTO_INCREMENT=410 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre`
--

LOCK TABLES `cierre` WRITE;
/*!40000 ALTER TABLE `cierre` DISABLE KEYS */;
INSERT INTO `cierre` VALUES (404,2,'2019-07-19',27700.0000,2900.0000,80330000.0000,790.0000,2800.0000,2212000.0000,1260.0000,2900.0000,3654000.0000,0.0000,0.0000,27230.0000,2897.9215,78910403.3161,2.0785,2618.8697,2618.8697,2850.0000,-47.9215,77605500.0000,-1304903.3161,-1304903.3161,82715.5536,0.00266,-1.32388,-1.32122,0.0000,0.0000,-1302284.4464,-1.32122,19698650.0000,0.0000,0.0000,-1302284.4464,-1302284.4464,1442000.0000,1442000.0000,97304150.0000,2618.8697,0.0000,1,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,27230.0000,78910403.3161,2850.0000,78945000.0000,80096.6839,80096.6839,0.0000,0.0000,77605500.0000,0.0000,0.0000,0.0000,0.0000,-1304903.3161,-1304903.3161),(405,3,'2019-07-19',9160.0000,3100.0000,28396000.0000,100.0000,3000.0000,300000.0000,100.0000,3200.0000,320000.0000,0.0000,0.0000,9160.0000,3098.9224,28386129.3103,101.0776,10107.7586,10107.7586,3100.0000,1.0776,28396000.0000,9870.6897,9870.6897,19978.4483,0.02168,0.02117,0.04284,0.0000,0.0000,19978.4483,0.04284,19698650.0000,0.0000,0.0000,19978.4483,19978.4483,20000.0000,20000.0000,48094650.0000,10107.7586,0.0000,1,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,9160.0000,28386129.3103,3100.0000,28396000.0000,9870.6897,9870.6897,0.0000,0.0000,28396000.0000,0.0000,0.0000,0.0000,0.0000,9870.6897,9870.6897),(406,1,'2019-07-19',18236650.0000,1.0000,18236650.0000,2512000.0000,1.0000,2512000.0000,3974000.0000,1.0000,3974000.0000,NULL,NULL,19698650.0000,1.0000,19698650.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.0000,NULL,NULL,NULL),(407,2,'2019-07-19',14230.0000,2900.0000,41267000.0000,100.0000,2800.0000,280000.0000,100.0000,2900.0000,290000.0000,0.0000,0.0000,14230.0000,2897.9215,41237423.4002,2.0785,207.8468,207.8468,2850.0000,-47.9215,40555500.0000,-681923.4002,-681923.4002,29784.4466,0.00034,-1.12568,-1.12533,0.0000,0.0000,-681715.5534,-1.12533,19342000.0000,0.0000,0.0000,-681715.5534,-681715.5534,10000.0000,10000.0000,59897500.0000,207.8468,0.0000,2,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,14230.0000,41237423.4002,2850.0000,40555500.0000,29576.5998,29576.5998,0.0000,0.0000,40555500.0000,0.0000,0.0000,0.0000,0.0000,-681923.4002,-681923.4002),(408,3,'2019-07-19',9200.0000,3100.0000,28520000.0000,100.0000,3000.0000,300000.0000,100.0000,3200.0000,320000.0000,0.0000,0.0000,9200.0000,3098.9224,28510086.2069,101.0776,10107.7586,10107.7586,3100.0000,1.0776,28520000.0000,9913.7931,9913.7931,20021.5518,0.02113,0.02073,0.04186,0.0000,0.0000,20021.5518,0.04186,19342000.0000,0.0000,0.0000,20021.5518,20021.5518,20000.0000,20000.0000,47862000.0000,10107.7586,0.0000,2,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,9200.0000,28510086.2069,3100.0000,28520000.0000,9913.7931,9913.7931,0.0000,0.0000,28520000.0000,0.0000,0.0000,0.0000,0.0000,9913.7931,9913.7931),(409,1,'2019-07-19',19312000.0000,1.0000,19312000.0000,580000.0000,1.0000,580000.0000,610000.0000,1.0000,610000.0000,NULL,NULL,19342000.0000,1.0000,19342000.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.0000,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre_consolidado`
--

LOCK TABLES `cierre_consolidado` WRITE;
/*!40000 ALTER TABLE `cierre_consolidado` DISABLE KEYS */;
INSERT INTO `cierre_consolidado` VALUES (263,2,'2019-07-19',41930.0000,2900.0000,121597000.0000,890.0000,2800.0000,2492000.0000,1360.0000,2900.0000,3944000.0000,NULL,NULL,41460.0000,2897.9215,120147826.7163,2.0785,2826.7165,NULL,2850.0000,-47.9215,118161000.0000,-1986826.7163,NULL,112500.0002,NULL,NULL,NULL,0.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,41460.0000,120147826.7163,2850.0000,119500500.0000,109673.2837,109673.2837,NULL,NULL,118161000.0000,0.0000,0.0000,0.0000,0.0000,-1986826.7163,-1986826.7163),(264,3,'2019-07-19',18360.0000,3100.0000,56916000.0000,200.0000,3000.0000,600000.0000,200.0000,3200.0000,640000.0000,NULL,NULL,18360.0000,3098.9224,56896215.5172,101.0776,20215.5172,NULL,3100.0000,1.0776,56916000.0000,19784.4828,NULL,40000.0001,NULL,NULL,NULL,0.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18360.0000,56896215.5172,3100.0000,56916000.0000,19784.4828,19784.4828,NULL,NULL,56916000.0000,0.0000,0.0000,0.0000,0.0000,19784.4828,19784.4828),(265,1,'2019-07-19',37548650.0000,NULL,NULL,3092000.0000,1.0000,3092000.0000,4584000.0000,1.0000,4584000.0000,NULL,NULL,39040650.0000,1.0000,39040650.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.0000,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=526 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre_turno`
--

LOCK TABLES `cierre_turno` WRITE;
/*!40000 ALTER TABLE `cierre_turno` DISABLE KEYS */;
INSERT INTO `cierre_turno` VALUES (508,28,1,18436650.00,1.00),(509,28,2,26620.00,2900.00),(510,28,3,9000.00,3100.00),(511,29,1,1000000.00,1.00),(512,29,2,700.00,2900.00),(513,29,3,160.00,3100.00),(514,30,1,19030000.00,1.00),(515,30,2,14000.00,2900.00),(516,30,3,9000.00,3100.00),(517,31,1,312000.00,1.00),(518,31,2,230.00,2900.00),(519,31,3,200.00,3100.00),(520,32,1,18408650.00,1.00),(521,32,2,26630.00,2897.94),(522,32,3,9000.00,3098.92),(523,33,1,1290000.00,1.00),(524,33,2,600.00,2897.92),(525,33,3,160.00,3098.92);
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
INSERT INTO `denominacion` VALUES (113,1.00,1),(114,1000.00,1),(115,2000.00,1),(116,5000.00,1),(117,10000.00,1),(118,20000.00,1),(119,50000.00,1),(120,100000.00,1),(121,1.00,2),(122,2.00,2),(123,5.00,2),(124,10.00,2),(125,20.00,2),(126,50.00,2),(127,100.00,2),(128,5.00,3),(129,10.00,3),(130,20.00,3),(131,50.00,3),(132,100.00,3),(133,200.00,3),(134,500.00,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=2216 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denominacion_cantidad`
--

LOCK TABLES `denominacion_cantidad` WRITE;
/*!40000 ALTER TABLE `denominacion_cantidad` DISABLE KEYS */;
INSERT INTO `denominacion_cantidad` VALUES (2097,120,NULL,172,NULL,NULL,'ENTRADA',152,NULL),(2098,119,NULL,10,NULL,NULL,'ENTRADA',152,NULL),(2099,118,NULL,6,NULL,NULL,'ENTRADA',152,NULL),(2100,117,NULL,25,NULL,NULL,'ENTRADA',152,NULL),(2101,116,NULL,10,NULL,NULL,'ENTRADA',152,NULL),(2102,115,NULL,10,NULL,NULL,'ENTRADA',152,NULL),(2103,114,NULL,268,NULL,NULL,'ENTRADA',152,NULL),(2104,113,NULL,650,NULL,NULL,'ENTRADA',152,NULL),(2105,127,NULL,265,NULL,NULL,'ENTRADA',153,NULL),(2106,126,NULL,0,NULL,NULL,'ENTRADA',153,NULL),(2107,125,NULL,4,NULL,NULL,'ENTRADA',153,NULL),(2108,124,NULL,1,NULL,NULL,'ENTRADA',153,NULL),(2109,123,NULL,0,NULL,NULL,'ENTRADA',153,NULL),(2110,122,NULL,0,NULL,NULL,'ENTRADA',153,NULL),(2111,121,NULL,40,NULL,NULL,'ENTRADA',153,NULL),(2112,134,NULL,0,NULL,NULL,'ENTRADA',154,NULL),(2113,133,NULL,0,NULL,NULL,'ENTRADA',154,NULL),(2114,132,NULL,89,NULL,NULL,'ENTRADA',154,NULL),(2115,131,NULL,0,NULL,NULL,'ENTRADA',154,NULL),(2116,130,NULL,0,NULL,NULL,'ENTRADA',154,NULL),(2117,129,NULL,10,NULL,NULL,'ENTRADA',154,NULL),(2118,128,NULL,0,NULL,NULL,'ENTRADA',154,NULL),(2119,120,NULL,10,NULL,NULL,'ENTRADA',155,NULL),(2120,119,NULL,0,NULL,NULL,'ENTRADA',155,NULL),(2121,118,NULL,0,NULL,NULL,'ENTRADA',155,NULL),(2122,117,NULL,0,NULL,NULL,'ENTRADA',155,NULL),(2123,116,NULL,0,NULL,NULL,'ENTRADA',155,NULL),(2124,115,NULL,0,NULL,NULL,'ENTRADA',155,NULL),(2125,114,NULL,290,NULL,NULL,'ENTRADA',155,NULL),(2126,113,NULL,0,NULL,NULL,'ENTRADA',155,NULL),(2127,127,NULL,6,NULL,NULL,'ENTRADA',156,NULL),(2128,126,NULL,0,NULL,NULL,'ENTRADA',156,NULL),(2129,125,NULL,0,NULL,NULL,'ENTRADA',156,NULL),(2130,124,NULL,0,NULL,NULL,'ENTRADA',156,NULL),(2131,123,NULL,0,NULL,NULL,'ENTRADA',156,NULL),(2132,122,NULL,0,NULL,NULL,'ENTRADA',156,NULL),(2133,121,NULL,0,NULL,NULL,'ENTRADA',156,NULL),(2134,134,NULL,0,NULL,NULL,'ENTRADA',157,NULL),(2135,133,NULL,0,NULL,NULL,'ENTRADA',157,NULL),(2136,132,NULL,0,NULL,NULL,'ENTRADA',157,NULL),(2137,131,NULL,0,NULL,NULL,'ENTRADA',157,NULL),(2138,130,NULL,0,NULL,NULL,'ENTRADA',157,NULL),(2139,129,NULL,16,NULL,NULL,'ENTRADA',157,NULL),(2140,128,NULL,0,NULL,NULL,'ENTRADA',157,NULL),(2141,120,NULL,189,NULL,NULL,'ENTRADA',158,NULL),(2142,119,NULL,1,NULL,NULL,'ENTRADA',158,NULL),(2143,118,NULL,3,NULL,NULL,'ENTRADA',158,NULL),(2144,117,NULL,0,NULL,NULL,'ENTRADA',158,NULL),(2145,116,NULL,0,NULL,NULL,'ENTRADA',158,NULL),(2146,115,NULL,0,NULL,NULL,'ENTRADA',158,NULL),(2147,114,NULL,10,NULL,NULL,'ENTRADA',158,NULL),(2148,113,NULL,10000,NULL,NULL,'ENTRADA',158,NULL),(2149,127,NULL,139,NULL,NULL,'ENTRADA',159,NULL),(2150,126,NULL,0,NULL,NULL,'ENTRADA',159,NULL),(2151,125,NULL,0,NULL,NULL,'ENTRADA',159,NULL),(2152,124,NULL,0,NULL,NULL,'ENTRADA',159,NULL),(2153,123,NULL,0,NULL,NULL,'ENTRADA',159,NULL),(2154,122,NULL,0,NULL,NULL,'ENTRADA',159,NULL),(2155,121,NULL,100,NULL,NULL,'ENTRADA',159,NULL),(2156,134,NULL,0,NULL,NULL,'ENTRADA',160,NULL),(2157,133,NULL,0,NULL,NULL,'ENTRADA',160,NULL),(2158,132,NULL,90,NULL,NULL,'ENTRADA',160,NULL),(2159,131,NULL,0,NULL,NULL,'ENTRADA',160,NULL),(2160,130,NULL,0,NULL,NULL,'ENTRADA',160,NULL),(2161,129,NULL,0,NULL,NULL,'ENTRADA',160,NULL),(2162,128,NULL,0,NULL,NULL,'ENTRADA',160,NULL),(2163,120,NULL,0,NULL,NULL,'ENTRADA',161,NULL),(2164,119,NULL,0,NULL,NULL,'ENTRADA',161,NULL),(2165,118,NULL,0,NULL,NULL,'ENTRADA',161,NULL),(2166,117,NULL,0,NULL,NULL,'ENTRADA',161,NULL),(2167,116,NULL,0,NULL,NULL,'ENTRADA',161,NULL),(2168,115,NULL,0,NULL,NULL,'ENTRADA',161,NULL),(2169,114,NULL,312,NULL,NULL,'ENTRADA',161,NULL),(2170,113,NULL,0,NULL,NULL,'ENTRADA',161,NULL),(2171,127,NULL,0,NULL,NULL,'ENTRADA',162,NULL),(2172,126,NULL,0,NULL,NULL,'ENTRADA',162,NULL),(2173,125,NULL,0,NULL,NULL,'ENTRADA',162,NULL),(2174,124,NULL,0,NULL,NULL,'ENTRADA',162,NULL),(2175,123,NULL,0,NULL,NULL,'ENTRADA',162,NULL),(2176,122,NULL,0,NULL,NULL,'ENTRADA',162,NULL),(2177,121,NULL,230,NULL,NULL,'ENTRADA',162,NULL),(2178,134,NULL,0,NULL,NULL,'ENTRADA',163,NULL),(2179,133,NULL,0,NULL,NULL,'ENTRADA',163,NULL),(2180,132,NULL,2,NULL,NULL,'ENTRADA',163,NULL),(2181,131,NULL,0,NULL,NULL,'ENTRADA',163,NULL),(2182,130,NULL,0,NULL,NULL,'ENTRADA',163,NULL),(2183,129,NULL,0,NULL,NULL,'ENTRADA',163,NULL),(2184,128,NULL,0,NULL,NULL,'ENTRADA',163,NULL),(2185,118,63,4,NULL,NULL,'SALIDA',NULL,NULL),(2186,120,63,21,NULL,NULL,'SALIDA',NULL,NULL),(2187,114,63,4,NULL,NULL,'SALIDA',NULL,NULL),(2188,127,63,7,NULL,NULL,'ENTRADA',NULL,NULL),(2189,125,63,4,NULL,NULL,'ENTRADA',NULL,NULL),(2190,121,64,60,NULL,NULL,'SALIDA',NULL,NULL),(2191,127,64,11,NULL,NULL,'SALIDA',NULL,NULL),(2192,114,64,64,NULL,NULL,'ENTRADA',NULL,NULL),(2193,120,64,33,NULL,NULL,'ENTRADA',NULL,NULL),(2194,120,65,3,NULL,NULL,'SALIDA',NULL,NULL),(2195,132,65,1,NULL,NULL,'ENTRADA',NULL,NULL),(2196,132,66,1,NULL,NULL,'SALIDA',NULL,NULL),(2197,120,66,3,NULL,NULL,'ENTRADA',NULL,NULL),(2198,117,66,2,NULL,NULL,'ENTRADA',NULL,NULL),(2199,114,67,40,NULL,NULL,'SALIDA',NULL,NULL),(2200,120,67,2,NULL,NULL,'SALIDA',NULL,NULL),(2201,113,67,40000,NULL,NULL,'SALIDA',NULL,NULL),(2202,127,67,1,NULL,NULL,'ENTRADA',NULL,NULL),(2203,127,68,1,NULL,NULL,'SALIDA',NULL,NULL),(2204,118,68,2,NULL,NULL,'ENTRADA',NULL,NULL),(2205,119,68,1,NULL,NULL,'ENTRADA',NULL,NULL),(2206,120,68,2,NULL,NULL,'ENTRADA',NULL,NULL),(2207,120,69,3,NULL,NULL,'SALIDA',NULL,NULL),(2208,132,69,1,NULL,NULL,'ENTRADA',NULL,NULL),(2209,132,70,1,NULL,NULL,'SALIDA',NULL,NULL),(2210,118,70,1,NULL,NULL,'ENTRADA',NULL,NULL),(2211,120,70,3,NULL,NULL,'ENTRADA',NULL,NULL),(2212,114,71,28,NULL,NULL,'SALIDA',NULL,NULL),(2213,124,71,1,NULL,NULL,'ENTRADA',NULL,NULL),(2214,127,72,1,NULL,NULL,'SALIDA',NULL,NULL),(2215,114,72,290,NULL,NULL,'ENTRADA',NULL,NULL);
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
INSERT INTO `fx` VALUES (1,'COP','Peso Colombiano'),(2,'USD','Dolar Americano'),(3,'EUR','Euro');
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
INSERT INTO `fx_sucursal` VALUES (1,2,1,2800.00,2900.00,2850.00),(2,3,1,3000.00,3200.00,3100.00),(3,2,2,2800.00,2900.00,2850.00),(4,3,2,3000.00,3200.00,3100.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
INSERT INTO `operacion` VALUES (63,'2019-07-19 23:57:20',1,2,'COMPRA',780.00,2800.00,'EJECUTADA',NULL,2850.00,28,NULL,NULL),(64,'2019-07-19 23:58:16',1,2,'VENTA',1160.00,2900.00,'EJECUTADA',NULL,2850.00,28,NULL,NULL),(65,'2019-07-19 00:00:32',1,3,'COMPRA',100.00,3000.00,'EJECUTADA',NULL,3100.00,28,NULL,NULL),(66,'2019-07-19 00:00:55',1,3,'VENTA',100.00,3200.00,'EJECUTADA',NULL,3100.00,28,NULL,NULL),(67,'2019-07-19 00:08:52',1,2,'COMPRA',100.00,2800.00,'EJECUTADA',NULL,2850.00,30,NULL,NULL),(68,'2019-07-19 00:09:10',1,2,'VENTA',100.00,2900.00,'EJECUTADA',NULL,2850.00,30,NULL,NULL),(69,'2019-07-19 00:09:24',1,3,'COMPRA',100.00,3000.00,'EJECUTADA',NULL,3100.00,30,NULL,NULL),(70,'2019-07-19 00:09:39',1,3,'VENTA',100.00,3200.00,'EJECUTADA',NULL,3100.00,30,NULL,NULL),(71,'2019-07-19 00:32:16',1,2,'COMPRA',10.00,2800.00,'EJECUTADA',NULL,2850.00,32,NULL,NULL),(72,'2019-07-19 20:24:39',1,2,'VENTA',100.00,2900.00,'EJECUTADA',NULL,2850.00,33,NULL,NULL);
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
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_OPERADOR_JR'),(4,'ROL_EJEMPLO');
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
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo`
--

LOCK TABLES `saldo` WRITE;
/*!40000 ALTER TABLE `saldo` DISABLE KEYS */;
INSERT INTO `saldo` VALUES (152,9,1,18408650.00,1.00),(153,9,2,26630.00,2897.92),(154,9,3,9000.00,3098.92),(155,11,1,1290000.00,1.00),(156,11,2,600.00,2897.92),(157,11,3,160.00,3098.92),(158,10,1,19030000.00,1.00),(159,10,2,14000.00,2897.92),(160,10,3,9000.00,3098.92),(161,12,1,312000.00,1.00),(162,12,2,230.00,2897.92),(163,12,3,200.00,3098.92);
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
INSERT INTO `turno` VALUES (28,9,1,'2019-07-19 13:53:30','2019-07-19 15:01:18'),(29,11,1,'2019-07-19 00:01:49','2019-07-19 00:02:03'),(30,10,1,'2019-07-19 00:02:21','2019-07-19 00:09:53'),(31,12,1,'2019-07-19 00:10:08','2019-07-19 00:10:18'),(32,9,1,'2019-07-19 18:30:35','2019-07-19 16:32:33'),(33,11,1,'2019-07-19 20:23:48','2019-07-19 20:25:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin',2),(2,'operador','operador',3);
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

-- Dump completed on 2019-07-19 20:59:42
