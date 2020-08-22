-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Aug 22, 2020 at 04:47 AM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `amo`
--
CREATE DATABASE IF NOT EXISTS `amo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `amo`;

-- --------------------------------------------------------

--
-- Table structure for table `contratista`
--

CREATE TABLE `contratista` (
  `idContratista` int(11) NOT NULL,
  `nombreContratista` varchar(45) NOT NULL,
  `apellidoContratista` varchar(45) NOT NULL,
  `telefonoContratista` varchar(45) NOT NULL,
  `correoContratista` varchar(45) NOT NULL,
  `EmpresaCliente_idEmpresaCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contratista`
--

INSERT INTO `contratista` (`idContratista`, `nombreContratista`, `apellidoContratista`, `telefonoContratista`, `correoContratista`, `EmpresaCliente_idEmpresaCliente`) VALUES
(1, 'Diana', 'Gonzalez', '331312', 'dasdasdmakmsd', 1),
(3, 'Andrea', 'Gill', '3211920436', 'Andreag@contacto.com', 3),
(4, 'Daysi', 'Yara', '13123123123', 'correo@gmail.com', 4);

-- --------------------------------------------------------

--
-- Table structure for table `contrato`
--

CREATE TABLE `contrato` (
  `idContrato` int(11) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFinal` date NOT NULL,
  `costoContrato` int(11) NOT NULL,
  `costoMensual` int(11) NOT NULL,
  `EmpresaCliente_idEmpresaCliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contrato`
--

INSERT INTO `contrato` (`idContrato`, `fechaInicio`, `fechaFinal`, `costoContrato`, `costoMensual`, `EmpresaCliente_idEmpresaCliente`) VALUES
(5054, '2020-08-22', '2020-11-22', 180000, 60000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `empresacliente`
--

CREATE TABLE `empresacliente` (
  `idEmpresaCliente` int(11) NOT NULL,
  `nombreEmpresa` varchar(45) NOT NULL,
  `nitEmpresa` varchar(45) NOT NULL,
  `direccionEmpresa` varchar(45) NOT NULL,
  `telefonoEmpresa` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `empresacliente`
--

INSERT INTO `empresacliente` (`idEmpresaCliente`, `nombreEmpresa`, `nitEmpresa`, `direccionEmpresa`, `telefonoEmpresa`) VALUES
(1, 'AMO', '3313113', 'dakmaefmaef', '131313'),
(3, 'Coca cola', '331233121', 'asadsasd', '4434321'),
(4, 'Hadami', '1123123132', 'La casa', '41413');

-- --------------------------------------------------------

--
-- Table structure for table `inventarioempresa`
--

CREATE TABLE `inventarioempresa` (
  `idInventarioEmpresa` int(11) NOT NULL,
  `cantidadAlquilada` int(11) NOT NULL,
  `disponibleEnBodega` int(11) NOT NULL,
  `totalExistencias` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `inventarioempresa`
--

INSERT INTO `inventarioempresa` (`idInventarioEmpresa`, `cantidadAlquilada`, `disponibleEnBodega`, `totalExistencias`) VALUES
(1, 6, 6, 12);

-- --------------------------------------------------------

--
-- Table structure for table `mueble`
--

CREATE TABLE `mueble` (
  `idMueble` int(11) NOT NULL,
  `nombreMueble` varchar(45) NOT NULL,
  `tipoMueble` varchar(45) NOT NULL,
  `Contrato_idContrato` int(11) DEFAULT NULL,
  `InventarioEmpresa_idInventarioEmpresa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mueble`
--

INSERT INTO `mueble` (`idMueble`, `nombreMueble`, `tipoMueble`, `Contrato_idContrato`, `InventarioEmpresa_idInventarioEmpresa`) VALUES
(1000, 'Mesa de juntas', 'Mesa', NULL, 1),
(1001, 'Mesa de juntas', 'Mesa', NULL, 1),
(1006, 'Mesa de juntas', 'Mesa', 5054, NULL),
(1400, 'Silla giratoria', 'Silla', 5054, NULL),
(1401, 'Silla giratoria', 'Silla', NULL, 1),
(1402, 'Silla giratoria', 'Silla', NULL, 1),
(1408, 'Silla giratoria', 'Silla', 5054, NULL),
(1701, 'Silla empresarial', 'Silla', 5054, NULL),
(1702, 'Silla empresarial', 'Silla', NULL, 1),
(1703, 'Silla empresarial', 'Silla', NULL, 1),
(1708, 'Silla empresarial', 'Silla', 5054, NULL),
(1719, 'Silla empresarial', 'Silla', 5054, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contratista`
--
ALTER TABLE `contratista`
  ADD PRIMARY KEY (`idContratista`,`EmpresaCliente_idEmpresaCliente`),
  ADD KEY `fk_Contratista_EmpresaCliente_idx` (`EmpresaCliente_idEmpresaCliente`);

--
-- Indexes for table `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`idContrato`),
  ADD KEY `fk_Contrato_EmpresaCliente1_idx` (`EmpresaCliente_idEmpresaCliente`);

--
-- Indexes for table `empresacliente`
--
ALTER TABLE `empresacliente`
  ADD PRIMARY KEY (`idEmpresaCliente`),
  ADD UNIQUE KEY `idEmpresaCliente_UNIQUE` (`idEmpresaCliente`),
  ADD UNIQUE KEY `nombreEmpresa_UNIQUE` (`nombreEmpresa`),
  ADD UNIQUE KEY `nitEmpresa_UNIQUE` (`nitEmpresa`),
  ADD UNIQUE KEY `telefonoEmpresa_UNIQUE` (`telefonoEmpresa`);

--
-- Indexes for table `inventarioempresa`
--
ALTER TABLE `inventarioempresa`
  ADD PRIMARY KEY (`idInventarioEmpresa`);

--
-- Indexes for table `mueble`
--
ALTER TABLE `mueble`
  ADD PRIMARY KEY (`idMueble`),
  ADD KEY `fk_Mueble_Contrato1_idx` (`Contrato_idContrato`),
  ADD KEY `fk_Mueble_InventarioEmpresa1_idx` (`InventarioEmpresa_idInventarioEmpresa`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contratista`
--
ALTER TABLE `contratista`
  ADD CONSTRAINT `fk_Contratista_EmpresaCliente` FOREIGN KEY (`EmpresaCliente_idEmpresaCliente`) REFERENCES `empresacliente` (`idEmpresaCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `fk_Contrato_EmpresaCliente1` FOREIGN KEY (`EmpresaCliente_idEmpresaCliente`) REFERENCES `empresacliente` (`idEmpresaCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mueble`
--
ALTER TABLE `mueble`
  ADD CONSTRAINT `fk_Mueble_Contrato1` FOREIGN KEY (`Contrato_idContrato`) REFERENCES `contrato` (`idContrato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Mueble_InventarioEmpresa1` FOREIGN KEY (`InventarioEmpresa_idInventarioEmpresa`) REFERENCES `inventarioempresa` (`idInventarioEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
