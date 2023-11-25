-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 06, 2023 at 12:16 AM
-- Server version: 8.0.31
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `carID` int NOT NULL,
  `carColor` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `make` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `model` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`carID`, `carColor`, `make`, `model`, `price`) VALUES
(1, 'Red', 'Toyota', 'Camry', 25000),
(2, 'Blue', 'Ford', 'F-150', 35000),
(3, 'Black', 'Honda', 'Civic', 22000),
(4, 'White', 'Chevrolet', 'Silverado', 38000),
(5, 'Red', 'Toyota', 'Corolla', 23000),
(6, 'Blue', 'Ford', 'Mustang', 42000),
(7, 'Black', 'Honda', 'Accord', 27000),
(8, 'White', 'Chevrolet', 'Camaro', 45000),
(9, 'Red', 'Toyota', 'RAV4', 28000),
(10, 'Blue', 'Ford', 'Explorer', 39000),
(11, 'Black', 'Honda', 'Fit', 18000),
(12, 'White', 'Chevrolet', 'Malibu', 26000),
(13, 'Red', 'Toyota', 'Highlander', 32000),
(14, 'Blue', 'Ford', 'Escape', 29000),
(15, 'Black', 'Honda', 'CR-V', 31000),
(16, 'White', 'Chevrolet', 'Equinox', 27000),
(17, 'Red', 'Toyota', 'Tundra', 40000),
(18, 'Blue', 'Ford', 'Edge', 34000),
(19, 'Black', 'Honda', 'Pilot', 36000),
(20, 'White', 'Chevrolet', 'Traverse', 38000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`carID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
