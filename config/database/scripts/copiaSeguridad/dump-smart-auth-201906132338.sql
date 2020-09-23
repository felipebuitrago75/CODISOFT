-- MySQL dump 10.16  Distrib 10.3.9-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: smart-auth
-- ------------------------------------------------------
-- Server version	10.3.10-MariaDB-1:10.3.10+maria~bionic

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
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apertura_turno`
--

LOCK TABLES `apertura_turno` WRITE;
/*!40000 ALTER TABLE `apertura_turno` DISABLE KEYS */;
INSERT INTO `apertura_turno` VALUES (282,13,1,3080000.00,1.00),(283,13,2,100.00,2800.00),(284,13,3,850.00,3000.00),(285,14,1,9300000.00,1.00),(286,14,2,1500.00,2800.00),(287,14,3,1100.00,3000.00),(288,15,1,1.00,1.00),(289,15,2,1.00,2800.00),(290,15,3,5.00,3000.00),(291,16,1,1750001.00,1.00),(292,16,2,501.00,2800.00),(293,16,3,305.00,3000.00),(294,17,1,2510000.00,1.00),(295,17,2,200.00,2816.60),(296,17,3,700.00,3002.53);
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
) ENGINE=InnoDB AUTO_INCREMENT=284 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arqueo`
--

LOCK TABLES `arqueo` WRITE;
/*!40000 ALTER TABLE `arqueo` DISABLE KEYS */;
INSERT INTO `arqueo` VALUES (283,'2019-05-29 22:59:27',17);
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
INSERT INTO `cierre` VALUES (314,2,'2019-05-20',601.0000,2800.0000,1682800.0000,1000.0000,2800.0000,2800000.0000,900.0000,2900.0000,2610000.0000,0.0000,0.0000,701.0000,2816.6045,1974439.7831,83.3955,75055.9133,75055.9133,2869.0712,52.4667,2011218.9164,36779.1333,36779.1333,70323.2509,1.15244,0.56472,1.71716,0.0000,0.0000,111835.0465,1.71716,4260001.0000,0.0000,0.0000,111835.0465,111835.0465,-190000.0000,-190000.0000,6271219.9164,75055.9133,0.0000,1,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,701.0000,1974439.7831,2869.0712,1724311.7957,36779.1333,-4732.6624,0.0000,0.0000,2011218.9164,0.0000,36779.1333,0.0000,0.0000),(315,3,'2019-05-20',1155.0000,3000.0000,3465000.0000,500.0000,3000.0000,1500000.0000,350.0000,3200.0000,1120000.0000,0.0000,0.0000,1005.0000,3002.5316,3017544.3038,197.4684,69113.9241,69113.9241,3123.7993,121.2676,3139418.2796,121873.9758,121873.9758,77240.2341,0.83320,1.46925,2.30245,0.0000,0.0000,190987.8998,2.30245,4260001.0000,29240.5063,29240.5063,220228.4062,220228.4062,-380000.0000,-380000.0000,7399419.2796,69113.9241,900759.4937,1,0.0000,300.0000,930000.0000,3100.0000,0.0000,0.0000,1305.0000,3918303.7975,3123.7993,3607988.1720,1059013.7607,15266.0950,0.0000,29240.5063,4076558.0645,-36380.2913,121873.9758,0.0000,0.0000),(316,1,'2019-05-20',4830001.0000,1.0000,4830001.0000,4300000.0000,1.0000,4300000.0000,3730000.0000,1.0000,3730000.0000,NULL,NULL,4260001.0000,1.0000,4260001.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.0000,NULL),(317,2,'2019-05-20',1501.0000,2800.0000,4202800.0000,2800.0000,2835.0000,7938000.0000,4200.0000,2915.0000,12243000.0000,0.0000,0.0000,101.0000,2816.6045,284477.0586,98.3955,413260.9285,413260.9285,2869.0712,52.4667,289776.1920,5299.1333,5299.1333,314884.1795,3.06056,0.03925,3.09980,0.0000,0.0000,418560.0618,3.09980,16297001.0000,0.0000,0.0000,418560.0618,418560.0618,4305000.0000,4305000.0000,16586777.1920,413260.9285,0.0000,2,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,101.0000,284477.0586,2869.0712,4306475.8824,5299.1333,-98376.7490,0.0000,0.0000,289776.1920,0.0000,5299.1333,0.0000,0.0000),(318,3,'2019-05-20',1105.0000,3000.0000,3315000.0000,400.0000,3020.0000,1208000.0000,1200.0000,3250.0000,3900000.0000,300.0000,3100.0000,605.0000,3002.5316,1816531.6456,247.4684,296962.0253,296962.0253,3123.7993,121.2676,1889898.5663,73366.9207,73366.9207,204290.2318,2.35404,0.58159,2.93562,0.0000,0.0000,370328.9461,2.93562,16297001.0000,-29240.5063,0.0000,370328.9461,370328.9461,2692000.0000,2692000.0000,18186899.5663,296962.0253,0.0000,2,300.0000,0.0000,0.0000,0.0000,0.0000,930000.0000,305.0000,915772.1519,3123.7993,3451798.2079,-863772.8642,-99811.5784,-29240.5063,0.0000,952758.7814,36380.2913,73366.9207,0.0000,0.0000),(319,1,'2019-05-20',9300001.0000,1.0000,9300001.0000,9146000.0000,1.0000,9146000.0000,16143000.0000,1.0000,16143000.0000,NULL,NULL,16297001.0000,1.0000,16297001.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.0000,NULL);
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
INSERT INTO `cierre_consolidado` VALUES (218,2,'2019-05-20',2102.0000,2800.0000,5885600.0000,3800.0000,2825.7895,10738000.0000,5100.0000,2912.3529,14853000.0000,NULL,NULL,802.0000,2816.6045,2258916.8417,95.7484,488316.8418,NULL,2869.0712,52.4667,2300995.1084,42078.2666,NULL,385207.4304,NULL,NULL,NULL,0.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,802.0000,2258916.8417,2869.0712,6030787.6780,42078.2666,-103109.4114,NULL,NULL,2300995.1084,0.0000,42078.2666,0.0000,0.0000),(219,3,'2019-05-20',2260.0000,3000.0000,6780000.0000,900.0000,3008.8889,2708000.0000,1550.0000,3238.7097,5020000.0000,NULL,NULL,1610.0000,3002.5316,4834075.9494,236.1780,366075.9494,NULL,3123.7993,121.2676,5029316.8459,195240.8965,NULL,281530.4659,NULL,NULL,NULL,0.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1610.0000,4834075.9494,3123.7993,7059786.3799,195240.8965,-84545.4834,NULL,NULL,5029316.8459,0.0000,195240.8965,0.0000,0.0000),(220,1,'2019-05-20',14130002.0000,NULL,NULL,13446000.0000,1.0000,13446000.0000,19873000.0000,1.0000,19873000.0000,NULL,NULL,20557002.0000,1.0000,20557002.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.0000,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=468 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre_turno`
--

LOCK TABLES `cierre_turno` WRITE;
/*!40000 ALTER TABLE `cierre_turno` DISABLE KEYS */;
INSERT INTO `cierre_turno` VALUES (453,14,1,16297000.00,1.00),(454,14,2,100.00,2800.00),(455,14,3,600.00,3000.00),(456,15,1,1.00,1.00),(457,15,2,1.00,2800.00),(458,15,3,5.00,3000.00),(459,13,1,2510000.00,1.00),(460,13,2,200.00,2800.00),(461,13,3,700.00,3000.00),(462,16,1,1750001.00,1.00),(463,16,2,501.00,2800.00),(464,16,3,305.00,3000.00),(465,17,1,2510000.00,1.00),(466,17,2,200.00,2816.60),(467,17,3,700.00,3002.53);
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
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=984 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denominacion_cantidad`
--

LOCK TABLES `denominacion_cantidad` WRITE;
/*!40000 ALTER TABLE `denominacion_cantidad` DISABLE KEYS */;
INSERT INTO `denominacion_cantidad` VALUES (854,120,NULL,22,NULL,NULL,'ENTRADA',13,NULL),(855,119,NULL,0,NULL,NULL,'ENTRADA',13,NULL),(856,118,NULL,1,NULL,NULL,'ENTRADA',13,NULL),(857,117,NULL,0,NULL,NULL,'ENTRADA',13,NULL),(858,116,NULL,40,NULL,NULL,'ENTRADA',13,NULL),(859,115,NULL,40,NULL,NULL,'ENTRADA',13,NULL),(860,114,NULL,0,NULL,NULL,'ENTRADA',13,NULL),(861,113,NULL,10000,NULL,NULL,'ENTRADA',13,NULL),(862,127,NULL,2,NULL,NULL,'ENTRADA',14,NULL),(863,126,NULL,0,NULL,NULL,'ENTRADA',14,NULL),(864,125,NULL,0,NULL,NULL,'ENTRADA',14,NULL),(865,124,NULL,0,NULL,NULL,'ENTRADA',14,NULL),(866,123,NULL,0,NULL,NULL,'ENTRADA',14,NULL),(867,122,NULL,0,NULL,NULL,'ENTRADA',14,NULL),(868,121,NULL,0,NULL,NULL,'ENTRADA',14,NULL),(869,134,NULL,1,NULL,NULL,'ENTRADA',15,NULL),(870,133,NULL,0,NULL,NULL,'ENTRADA',15,NULL),(871,132,NULL,2,NULL,NULL,'ENTRADA',15,NULL),(872,131,NULL,0,NULL,NULL,'ENTRADA',15,NULL),(873,130,NULL,0,NULL,NULL,'ENTRADA',15,NULL),(874,129,NULL,0,NULL,NULL,'ENTRADA',15,NULL),(875,128,NULL,0,NULL,NULL,'ENTRADA',15,NULL),(876,120,NULL,160,NULL,NULL,'ENTRADA',16,NULL),(877,119,NULL,4,NULL,NULL,'ENTRADA',16,NULL),(878,118,NULL,0,NULL,NULL,'ENTRADA',16,NULL),(879,117,NULL,0,NULL,NULL,'ENTRADA',16,NULL),(880,116,NULL,0,NULL,NULL,'ENTRADA',16,NULL),(881,115,NULL,0,NULL,NULL,'ENTRADA',16,NULL),(882,114,NULL,97,NULL,NULL,'ENTRADA',16,NULL),(883,113,NULL,0,NULL,NULL,'ENTRADA',16,NULL),(884,127,NULL,1,NULL,NULL,'ENTRADA',17,NULL),(885,126,NULL,0,NULL,NULL,'ENTRADA',17,NULL),(886,125,NULL,0,NULL,NULL,'ENTRADA',17,NULL),(887,124,NULL,0,NULL,NULL,'ENTRADA',17,NULL),(888,123,NULL,0,NULL,NULL,'ENTRADA',17,NULL),(889,122,NULL,0,NULL,NULL,'ENTRADA',17,NULL),(890,121,NULL,0,NULL,NULL,'ENTRADA',17,NULL),(891,134,NULL,0,NULL,NULL,'ENTRADA',18,NULL),(892,133,NULL,0,NULL,NULL,'ENTRADA',18,NULL),(893,132,NULL,6,NULL,NULL,'ENTRADA',18,NULL),(894,131,NULL,0,NULL,NULL,'ENTRADA',18,NULL),(895,130,NULL,0,NULL,NULL,'ENTRADA',18,NULL),(896,129,NULL,0,NULL,NULL,'ENTRADA',18,NULL),(897,128,NULL,0,NULL,NULL,'ENTRADA',18,NULL),(898,120,NULL,17,NULL,NULL,'ENTRADA',19,NULL),(899,119,NULL,1,NULL,NULL,'ENTRADA',19,NULL),(900,118,NULL,0,NULL,NULL,'ENTRADA',19,NULL),(901,117,NULL,0,NULL,NULL,'ENTRADA',19,NULL),(902,116,NULL,0,NULL,NULL,'ENTRADA',19,NULL),(903,115,NULL,0,NULL,NULL,'ENTRADA',19,NULL),(904,114,NULL,0,NULL,NULL,'ENTRADA',19,NULL),(905,113,NULL,1,NULL,NULL,'ENTRADA',19,NULL),(906,127,NULL,5,NULL,NULL,'ENTRADA',20,NULL),(907,126,NULL,0,NULL,NULL,'ENTRADA',20,NULL),(908,125,NULL,0,NULL,NULL,'ENTRADA',20,NULL),(909,124,NULL,0,NULL,NULL,'ENTRADA',20,NULL),(910,123,NULL,0,NULL,NULL,'ENTRADA',20,NULL),(911,122,NULL,0,NULL,NULL,'ENTRADA',20,NULL),(912,121,NULL,1,NULL,NULL,'ENTRADA',20,NULL),(913,134,NULL,0,NULL,NULL,'ENTRADA',21,NULL),(914,133,NULL,0,NULL,NULL,'ENTRADA',21,NULL),(915,132,NULL,3,NULL,NULL,'ENTRADA',21,NULL),(916,131,NULL,0,NULL,NULL,'ENTRADA',21,NULL),(917,130,NULL,0,NULL,NULL,'ENTRADA',21,NULL),(918,129,NULL,0,NULL,NULL,'ENTRADA',21,NULL),(919,128,NULL,1,NULL,NULL,'ENTRADA',21,NULL),(920,120,NULL,0,NULL,NULL,'ENTRADA',22,NULL),(921,119,NULL,0,NULL,NULL,'ENTRADA',22,NULL),(922,118,NULL,0,NULL,NULL,'ENTRADA',22,NULL),(923,117,NULL,0,NULL,NULL,'ENTRADA',22,NULL),(924,116,NULL,0,NULL,NULL,'ENTRADA',22,NULL),(925,115,NULL,0,NULL,NULL,'ENTRADA',22,NULL),(926,114,NULL,0,NULL,NULL,'ENTRADA',22,NULL),(927,113,NULL,1,NULL,NULL,'ENTRADA',22,NULL),(928,127,NULL,0,NULL,NULL,'ENTRADA',23,NULL),(929,126,NULL,0,NULL,NULL,'ENTRADA',23,NULL),(930,125,NULL,0,NULL,NULL,'ENTRADA',23,NULL),(931,124,NULL,0,NULL,NULL,'ENTRADA',23,NULL),(932,123,NULL,0,NULL,NULL,'ENTRADA',23,NULL),(933,122,NULL,0,NULL,NULL,'ENTRADA',23,NULL),(934,121,NULL,1,NULL,NULL,'ENTRADA',23,NULL),(935,134,NULL,0,NULL,NULL,'ENTRADA',24,NULL),(936,133,NULL,0,NULL,NULL,'ENTRADA',24,NULL),(937,132,NULL,0,NULL,NULL,'ENTRADA',24,NULL),(938,131,NULL,0,NULL,NULL,'ENTRADA',24,NULL),(939,130,NULL,0,NULL,NULL,'ENTRADA',24,NULL),(940,129,NULL,0,NULL,NULL,'ENTRADA',24,NULL),(941,128,NULL,1,NULL,NULL,'ENTRADA',24,NULL),(942,120,22,10,NULL,NULL,'SALIDA',NULL,NULL),(943,119,22,15,NULL,NULL,'SALIDA',NULL,NULL),(944,118,22,30,NULL,NULL,'SALIDA',NULL,NULL),(945,117,22,15,NULL,NULL,'SALIDA',NULL,NULL),(946,116,22,60,NULL,NULL,'SALIDA',NULL,NULL),(947,127,22,10,NULL,NULL,'ENTRADA',NULL,NULL),(948,127,23,9,NULL,NULL,'SALIDA',NULL,NULL),(949,120,23,26,NULL,NULL,'ENTRADA',NULL,NULL),(950,113,23,10000,NULL,NULL,'ENTRADA',NULL,NULL),(951,120,24,15,NULL,NULL,'SALIDA',NULL,NULL),(952,132,24,5,NULL,NULL,'ENTRADA',NULL,NULL),(953,133,25,1,NULL,NULL,'SALIDA',NULL,NULL),(954,132,25,1,NULL,NULL,'SALIDA',NULL,NULL),(955,131,25,1,NULL,NULL,'SALIDA',NULL,NULL),(956,120,25,11,NULL,NULL,'ENTRADA',NULL,NULL),(957,118,25,1,NULL,NULL,'ENTRADA',NULL,NULL),(958,120,26,79,NULL,NULL,'SALIDA',NULL,NULL),(959,114,26,38,NULL,NULL,'SALIDA',NULL,NULL),(960,127,26,28,NULL,NULL,'ENTRADA',NULL,NULL),(961,127,27,42,NULL,NULL,'SALIDA',NULL,NULL),(962,120,27,122,NULL,NULL,'ENTRADA',NULL,NULL),(963,114,27,43,NULL,NULL,'ENTRADA',NULL,NULL),(964,120,28,12,NULL,NULL,'SALIDA',NULL,NULL),(965,114,28,8,NULL,NULL,'SALIDA',NULL,NULL),(966,132,28,4,NULL,NULL,'ENTRADA',NULL,NULL),(967,132,29,12,NULL,NULL,'SALIDA',NULL,NULL),(968,120,29,39,NULL,NULL,'ENTRADA',NULL,NULL),(969,129,NULL,0,NULL,2,'SALIDA',NULL,NULL),(970,132,NULL,3,NULL,2,'SALIDA',NULL,NULL),(971,130,NULL,0,NULL,2,'SALIDA',NULL,NULL),(972,133,NULL,0,NULL,2,'SALIDA',NULL,NULL),(973,134,NULL,0,NULL,2,'SALIDA',NULL,NULL),(974,128,NULL,0,NULL,2,'SALIDA',NULL,NULL),(975,131,NULL,0,NULL,2,'SALIDA',NULL,NULL),(976,117,NULL,0,1,NULL,'SALIDA',NULL,NULL),(977,120,NULL,1,1,NULL,'SALIDA',NULL,NULL),(978,115,NULL,0,1,NULL,'SALIDA',NULL,NULL),(979,114,NULL,0,1,NULL,'SALIDA',NULL,NULL),(980,113,NULL,0,1,NULL,'SALIDA',NULL,NULL),(981,118,NULL,0,1,NULL,'SALIDA',NULL,NULL),(982,119,NULL,0,1,NULL,'SALIDA',NULL,NULL),(983,116,NULL,0,1,NULL,'SALIDA',NULL,NULL);
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
INSERT INTO `flujo_extraordinario` VALUES (1,'{\"idSoipc\":\"1\",\"fecha\":\"2019-05-30T03:53:29.180Z\",\"empresa\":\"11\",\"nit\":null,\"operador\":\"1\",\"beneficiario\":\"1\"}','EGRESO',100000.00,17,'2019-05-29 22:54:08','CANCELADO',1,NULL,'NOMINA','OPERACION','GENERAL','12131','1112');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fx`
--

LOCK TABLES `fx` WRITE;
/*!40000 ALTER TABLE `fx` DISABLE KEYS */;
INSERT INTO `fx` VALUES (1,'COP','Pesos Colombianos'),(2,'USD','Dolares Americanos'),(3,'EUR','Euro');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_arqueo`
--

LOCK TABLES `item_arqueo` WRITE;
/*!40000 ALTER TABLE `item_arqueo` DISABLE KEYS */;
INSERT INTO `item_arqueo` VALUES (300,283,2510000.0000,2510000.0000,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,1,0.0000),(301,283,200.0000,200.0000,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,2,0.0000),(302,283,700.0000,700.0000,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,3,0.0000);
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
INSERT INTO `operacion` VALUES (22,'2019-05-20 19:40:27',1,2,'COMPRA',1000.00,2800.00,'EJECUTADA',NULL,2850.00,13,NULL,NULL),(23,'2019-05-20 19:41:23',1,2,'VENTA',900.00,2900.00,'EJECUTADA',NULL,2850.00,13,NULL,NULL),(24,'2019-05-20 19:42:14',1,3,'COMPRA',500.00,3000.00,'EJECUTADA',NULL,3100.00,13,NULL,NULL),(25,'2019-05-20 19:43:10',1,3,'VENTA',350.00,3200.00,'EJECUTADA',NULL,3100.00,13,NULL,NULL),(26,'2019-05-20 19:46:14',1,2,'COMPRA',2800.00,2835.00,'EJECUTADA',NULL,2850.00,14,NULL,NULL),(27,'2019-05-20 19:47:05',1,2,'VENTA',4200.00,2915.00,'EJECUTADA',NULL,2850.00,14,NULL,NULL),(28,'2019-05-20 19:47:42',1,3,'COMPRA',400.00,3020.00,'EJECUTADA',NULL,3100.00,14,NULL,NULL),(29,'2019-05-20 19:48:40',1,3,'VENTA',1200.00,3250.00,'EJECUTADA',NULL,3100.00,14,NULL,NULL);
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
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(3,'PRUEBA'),(4,'ROL_EJEMPLO');
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo`
--

LOCK TABLES `saldo` WRITE;
/*!40000 ALTER TABLE `saldo` DISABLE KEYS */;
INSERT INTO `saldo` VALUES (13,9,1,2510000.00,1.00),(14,9,2,200.00,2816.60),(15,9,3,700.00,3002.53),(16,10,1,16297000.00,1.00),(17,10,2,100.00,2816.60),(18,10,3,600.00,3002.53),(19,11,1,1750001.00,1.00),(20,11,2,501.00,2816.60),(21,11,3,305.00,3002.53),(22,12,1,1.00,1.00),(23,12,2,1.00,2816.60),(24,12,3,5.00,3002.53);
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
INSERT INTO `traslado` VALUES (2,'2019-05-20 19:53:39',1,2,'PRECIO ESPECIFICO',13,14,'EJECUTADO',3100.00,300.00,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
INSERT INTO `turno` VALUES (13,9,1,'2019-05-20 19:28:59','2019-05-20 19:56:02'),(14,10,1,'2019-05-20 19:44:18','2019-05-20 19:55:09'),(15,12,1,'2019-05-20 19:55:31','2019-05-20 19:55:46'),(16,11,1,'2019-05-20 19:56:28','2019-05-20 19:56:48'),(17,9,1,'2019-05-29 22:53:13','2019-05-29 23:32:14');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','$2a$04$fx4HJlEUiHFwn2ajnmIp8e3GyVBNij6bKEuMZwfZYUykeXFPHinoO','123456',1,0,0,1),(2,'admin','$2a$04$fx4HJlEUiHFwn2ajnmIp8e3GyVBNij6bKEuMZwfZYUykeXFPHinoO','123456',1,0,0,1);
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

-- Dump completed on 2019-06-13 23:38:17
