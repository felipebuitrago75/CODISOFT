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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apertura_turno`
--

LOCK TABLES `apertura_turno` WRITE;
/*!40000 ALTER TABLE `apertura_turno` DISABLE KEYS */;
INSERT INTO `apertura_turno` VALUES (51,27,1,30000000.00,1.00),(52,27,2,25000.00,2700.00),(55,28,1,28822500.00,1.00),(56,28,2,25450.00,2698.92);
/*!40000 ALTER TABLE `apertura_turno` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caja`
--

LOCK TABLES `caja` WRITE;
/*!40000 ALTER TABLE `caja` DISABLE KEYS */;
INSERT INTO `caja` VALUES (1,'VENTANILLA',10),(2,'VENTANILLA 2',10);
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
  `caja` bigint(20) NOT NULL,
  `moneda` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `saldo_inicial` decimal(15,4) NOT NULL,
  `precio_promedio_inicial` decimal(15,4) NOT NULL,
  `valor_compra_inicial` decimal(15,4) NOT NULL,
  `nominal_compra` decimal(15,4) NOT NULL,
  `precio_compra` decimal(15,4) NOT NULL,
  `valor_compra` decimal(15,4) NOT NULL,
  `nominal_venta` decimal(15,4) NOT NULL,
  `precio_venta` decimal(15,4) NOT NULL,
  `valor_venta` decimal(15,4) NOT NULL,
  `nominal_traslados` decimal(15,4) DEFAULT NULL,
  `precio_traslado` decimal(15,4) DEFAULT NULL,
  `saldo_final` decimal(15,4) NOT NULL,
  `precio_promedio_final` decimal(15,4) NOT NULL,
  `valor_compra_final` decimal(15,4) DEFAULT NULL,
  `margen` decimal(15,4) DEFAULT NULL,
  `utilidad_diaria` decimal(15,4) NOT NULL,
  `pyg_caja` decimal(15,4) NOT NULL,
  `precio_valoracion` decimal(15,4) NOT NULL,
  `margen_prec_valora_y_prec_prom` decimal(15,4) NOT NULL,
  `valoracion` decimal(15,4) NOT NULL,
  `pyg_valoracion` decimal(15,4) NOT NULL,
  `pyg_valoracion_d` decimal(15,4) NOT NULL,
  `pyg_bruto` decimal(15,4) NOT NULL,
  `renta_caja` decimal(5,5) DEFAULT NULL,
  `renta_val` decimal(5,5) NOT NULL,
  `renta_bruta` decimal(5,5) NOT NULL,
  `egresos` decimal(15,4) DEFAULT NULL,
  `egresos_acumulados` decimal(15,4) DEFAULT NULL,
  `pyg_neto` decimal(15,4) NOT NULL,
  `renta_neta` decimal(5,5) NOT NULL,
  `saldo_cop` decimal(15,4) NOT NULL,
  `valor_giro_traslado` decimal(15,4) DEFAULT NULL,
  `valor_traslados` decimal(15,4) DEFAULT NULL,
  `pyg_traslado_diario` decimal(15,4) DEFAULT NULL,
  `pyg_traslado_acumulado` decimal(15,4) DEFAULT NULL,
  `pyg_bruto_con_traslado` decimal(15,4) DEFAULT NULL,
  `pyg_neto_con_traslado` decimal(15,4) DEFAULT NULL,
  `fc_diario` decimal(15,4) NOT NULL,
  `fc_acumulado` decimal(15,4) NOT NULL,
  `valor_port` decimal(15,4) NOT NULL,
  `pyg_caja_d` decimal(15,4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_caja_caja_moneda_idx` (`caja`) USING BTREE,
  KEY `fk_moneda_caja_moneda_idx` (`moneda`) USING BTREE,
  CONSTRAINT `fk_moneda_cierre` FOREIGN KEY (`moneda`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre`
--

LOCK TABLES `cierre` WRITE;
/*!40000 ALTER TABLE `cierre` DISABLE KEYS */;
INSERT INTO `cierre` VALUES (6,1,2,'2019-01-01',25000.0000,2700.0000,67500000.0000,550.0000,2650.0000,1457500.0000,100.0000,2800.0000,280000.0000,0.0000,0.0000,25450.0000,2698.9200,68687607.6300,101.0800,10107.6300,10107.6300,2725.0000,26.0800,69351250.0000,663642.3700,663642.3700,673750.0000,0.00000,0.01000,0.01000,0.0000,0.0000,673750.0000,0.01000,28822500.0000,0.0000,0.0000,0.0000,0.0000,673750.0000,673750.0000,-1177500.0000,-1177500.0000,98173750.0000,10107.6300),(12,1,2,'2019-01-02',25450.0000,2698.9200,68687514.0000,200.0000,2650.0000,530000.0000,100.0000,2800.0000,280000.0000,0.0000,0.0000,25550.0000,2698.5386,68947660.1441,101.4614,10146.1443,20253.7743,2725.0000,26.4614,69623750.0000,676089.8559,12447.4859,696343.6301,0.02052,0.68508,0.70561,0.0000,0.0000,696343.6301,0.70561,28572500.0000,0.0000,0.0000,0.0000,0.0000,696343.6301,696343.6301,-250000.0000,-1427500.0000,98196250.0000,10146.1443);
/*!40000 ALTER TABLE `cierre` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre_turno`
--

LOCK TABLES `cierre_turno` WRITE;
/*!40000 ALTER TABLE `cierre_turno` DISABLE KEYS */;
INSERT INTO `cierre_turno` VALUES (57,27,1,28822500.00,1.00),(58,27,2,25450.00,2700.00),(61,28,1,28572500.00,1.00),(62,28,2,25550.00,2698.92);
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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denominacion`
--

LOCK TABLES `denominacion` WRITE;
/*!40000 ALTER TABLE `denominacion` DISABLE KEYS */;
INSERT INTO `denominacion` VALUES (31,10000.00,1),(32,20000.00,1),(33,50000.00,1),(87,1000.00,1),(89,1.00,2),(90,2.00,2),(91,3.00,2),(92,4.00,2),(93,5.00,2);
/*!40000 ALTER TABLE `denominacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denominacion_operacion`
--

DROP TABLE IF EXISTS `denominacion_operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denominacion_operacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `denominacion` bigint(20) NOT NULL,
  `operacion` bigint(20) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_denominacion_flujos_idx` (`denominacion`),
  KEY `fk_operacion_flujos_idx` (`operacion`),
  CONSTRAINT `fk_denominacion_flujos` FOREIGN KEY (`denominacion`) REFERENCES `denominacion` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_operacion_flujos` FOREIGN KEY (`operacion`) REFERENCES `operacion` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denominacion_operacion`
--

LOCK TABLES `denominacion_operacion` WRITE;
/*!40000 ALTER TABLE `denominacion_operacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `denominacion_operacion` ENABLE KEYS */;
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
  `fx` bigint(20) NOT NULL,
  `idsoipc` varchar(45) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  `valor` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_fx_flujo` FOREIGN KEY (`id`) REFERENCES `fx` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
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
  `sucursal` bigint(20) NOT NULL,
  `usuario_origen` bigint(20) DEFAULT NULL,
  `usuario_destino` bigint(20) DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `valor_giro` decimal(14,2) NOT NULL,
  `fx` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_traslados_idx` (`usuario_origen`),
  KEY `fk_usuario_traslados_idx1` (`usuario_destino`),
  KEY `fk_fx_traslado_idx` (`fx`),
  KEY `fk_sucursal_destino_idx` (`sucursal`),
  CONSTRAINT `fk_fx_fondeo` FOREIGN KEY (`fx`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_sucursal_destino_fondeo` FOREIGN KEY (`sucursal`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_destino` FOREIGN KEY (`usuario_destino`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_origen` FOREIGN KEY (`usuario_origen`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fx`
--

LOCK TABLES `fx` WRITE;
/*!40000 ALTER TABLE `fx` DISABLE KEYS */;
INSERT INTO `fx` VALUES (1,'COP','Peso Colombiano'),(2,'US','Dolar'),(3,'EU','Euro'),(4,'MX','Peso Mexicano');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fx_sucursal`
--

LOCK TABLES `fx_sucursal` WRITE;
/*!40000 ALTER TABLE `fx_sucursal` DISABLE KEYS */;
INSERT INTO `fx_sucursal` VALUES (5,2,10,2600.00,2800.00,2740.00),(6,3,10,2800.00,3000.00,2900.00);
/*!40000 ALTER TABLE `fx_sucursal` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
INSERT INTO `operacion` VALUES (68,'2019-01-01 22:35:24',1,2,'COMPRA',550.00,2650.00,'EJECUTADA',NULL,2740.00,27,NULL,NULL),(69,'2019-01-01 22:35:39',1,2,'VENTA',100.00,2800.00,'EJECUTADA',NULL,2740.00,27,NULL,NULL),(70,'2019-01-02 23:04:04',1,2,'COMPRA',200.00,2650.00,'EJECUTADA',NULL,2740.00,28,NULL,NULL),(71,'2019-01-02 23:04:21',1,2,'VENTA',100.00,2800.00,'EJECUTADA',NULL,2740.00,28,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(3,'PRUEBA');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo`
--

LOCK TABLES `saldo` WRITE;
/*!40000 ALTER TABLE `saldo` DISABLE KEYS */;
INSERT INTO `saldo` VALUES (1,1,1,28572500.00,1.00),(2,1,2,25550.00,2698.54);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (10,'CC1','CC UNICENTRO','Cr 21 a 17-34','1',1,'2323232-3232221');
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
  `usuario_origen` bigint(20) DEFAULT NULL,
  `usuario_destino` bigint(20) DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `precio_traslado` decimal(14,2) NOT NULL,
  `valor_giro` decimal(14,2) NOT NULL,
  `fx` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_traslados_idx` (`usuario_origen`),
  KEY `fk_usuario_traslados_idx1` (`usuario_destino`),
  KEY `fk_fx_traslado_idx` (`fx`),
  KEY `fk_sucursal_origen_idx` (`sucursal_origen`),
  KEY `fk_sucursal_destino_idx` (`sucursal_destino`),
  CONSTRAINT `fk_fx_traslado` FOREIGN KEY (`fx`) REFERENCES `fx` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_sucursal_destino` FOREIGN KEY (`sucursal_destino`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_sucursal_origen` FOREIGN KEY (`sucursal_origen`) REFERENCES `sucursal` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_registrados` FOREIGN KEY (`usuario_origen`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_transferidos` FOREIGN KEY (`usuario_destino`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
INSERT INTO `turno` VALUES (27,1,1,'2019-01-01 22:35:06','2019-01-01 22:36:24'),(28,1,1,'2019-01-02 23:03:49','2019-01-02 23:04:31');
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

-- Dump completed on 2019-01-03 13:14:46