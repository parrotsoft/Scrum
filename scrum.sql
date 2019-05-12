-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: May 12, 2019 at 05:33 PM
-- Server version: 5.7.25
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scrum`
--
CREATE DATABASE IF NOT EXISTS `scrum` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `scrum`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `guardar_cliente` (`_id` INT, `_rut` VARCHAR(100), `_nombre` VARCHAR(100), `_direccion` VARCHAR(100), `_telefono` VARCHAR(100), `_activo` BINARY)  BEGIN
	INSERT INTO clientes (id, rut, nombre, direccion, telefono, activo) VALUES (_id, _rut, _nombre, _direccion, _telefono, _activo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `guardar_detalle_venta` (`_id` INT, `_venta` INT, `_producto` VARCHAR(100), `_precio` DOUBLE, `_cantidad` INT, `_total` DOUBLE, `_productoid` INT, `_activo` BINARY)  BEGIN
	INSERT INTO detalleVentas (id, venta, producto, precio, cantidad, total, productoid, activo) VALUES(_id, _venta, _producto, _precio, _cantidad, _total, _productoid, _activo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `guardar_producto` (`_id` INT, `_nombre` VARCHAR(100), `_precio` DOUBLE, `_stock` INT, `_proveedor` INT, `_activo` BINARY)  BEGIN
	INSERT INTO productos (id, nombre, precio, stock, proveedor, activo) VALUES (_id, _nombre, _precio, _stock, _proveedor, _activo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `guardar_proveedor` (`_id` INT, `_rut` VARCHAR(100), `_nombre` VARCHAR(100), `_direccion` VARCHAR(100), `_telefono` VARCHAR(100), `_web` VARCHAR(100), `_activo` BINARY)  BEGIN
	INSERT INTO proveedores (id, rut, nombre, direccion, telefono, web, activo) VALUES(_id, _rut, _nombre, _direccion, _telefono, _web, _activo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `guardar_usuario` (`_id` INT, `_usuario` VARCHAR(100), `_clave` VARCHAR(100), `_nombre` VARCHAR(100), `_rol` INT, `_activo` BINARY)  BEGIN
	INSERT INTO usuarios(id, usuario, clave, nombre, rol, activo) VALUES(_id, _usuario, _clave, _nombre, _rol, _activo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `guardar_venta` (`_id` INT, `_cliente` VARCHAR(100), `_rut` VARCHAR(100), `_total` DOUBLE, `_fecha` VARCHAR(100), `_activo` BINARY)  BEGIN
	INSERT INTO ventas (id, cliente, rut, total, fecha, activo) VALUES(_id, _cliente, _rut, _total, _fecha, _activo);
	
	SELECT LAST_INSERT_ID() AS ID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_clientes` ()  BEGIN
	SELECT * FROM clientes;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_productos` ()  BEGIN
	SELECT * FROM productos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_proveedores` ()  BEGIN
	SELECT * FROM proveedores;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_usuarios` ()  BEGIN
	SELECT * FROM usuarios;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_ventas` ()  BEGIN
	SELECT * FROM ventas;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `login` (`_usuario` VARCHAR(100), `_clave` VARCHAR(100))  BEGIN
	SELECT * FROM usuarios WHERE usuario = _usuario AND clave = _clave;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `clientes`
--

CREATE TABLE `clientes` (
  `id` int(10) NOT NULL,
  `rut` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `telefono` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `clientes`
--

INSERT INTO `clientes` (`id`, `rut`, `nombre`, `direccion`, `telefono`, `activo`) VALUES
(3, '1043605421', 'miguel', 'calle 222', '23232323', 1),
(4, '2132', '12312', '12312', '123123', 1),
(5, '23', 'juan manuel', 'll', 'll', 1);

-- --------------------------------------------------------

--
-- Table structure for table `detalleVentas`
--

CREATE TABLE `detalleVentas` (
  `id` int(11) NOT NULL,
  `venta` int(11) NOT NULL,
  `producto` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` double NOT NULL,
  `cantidad` int(11) NOT NULL,
  `total` double NOT NULL,
  `productoid` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `detalleVentas`
--

INSERT INTO `detalleVentas` (`id`, `venta`, `producto`, `precio`, `cantidad`, `total`, `productoid`, `activo`) VALUES
(2, 9, 'Pan de mantequilla', 1000, 3, 3000, 1, 1),
(3, 10, 'Prueba', 1000, 4, 4000, 3, 1),
(4, 10, 'Producto2', 1000, 2, 2000, 4, 1),
(5, 11, 'Prueba', 1000, 2, 2000, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` double NOT NULL,
  `stock` int(10) NOT NULL,
  `proveedor` int(10) NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `precio`, `stock`, `proveedor`, `activo`) VALUES
(1, 'Pan de mantequilla', 1000, 100, 1, 1),
(2, 'Arroz diana', 2000, 10, 1, 1),
(3, 'Prueba', 1000, 100, 2, 1),
(4, 'Producto2', 1000, 11, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL,
  `rut` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `telefono` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `web` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `proveedores`
--

INSERT INTO `proveedores` (`id`, `rut`, `nombre`, `direccion`, `telefono`, `web`, `activo`) VALUES
(1, '1043605421', 'GraficoApp', 'Calle 38 # 48-59', '3182964859', 'www.google.com', 1),
(2, '2312312', 'ZepolDP', '0', '0', '0', 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `usuario` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `clave` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `rol` int(10) NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `clave`, `nombre`, `rol`, `activo`) VALUES
(1, 'admin', 'admin', 'Miguel Lopez Ariza', 1, 1),
(2, 'kt', 'kt', 'Katherine', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ventas`
--

CREATE TABLE `ventas` (
  `id` int(11) NOT NULL,
  `cliente` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `rut` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `total` double NOT NULL,
  `fecha` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `ventas`
--

INSERT INTO `ventas` (`id`, `cliente`, `rut`, `total`, `fecha`, `activo`) VALUES
(9, 'miguel', '1043605421', 3000, 'Sun May 12 12:18:59 COT 2019', 1),
(10, 'miguel', '1043605421', 6000, 'Sun May 12 12:19:37 COT 2019', 1),
(11, 'juan manuel', '23', 2000, 'Sun May 12 12:32:36 COT 2019', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detalleVentas`
--
ALTER TABLE `detalleVentas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `detalleVentas`
--
ALTER TABLE `detalleVentas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
