-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 16, 2018 at 04:31 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `clientdetails`
--

CREATE TABLE `clientdetails` (
  `salesid` int(11) NOT NULL,
  `clientname` varchar(20) NOT NULL,
  `clientphone` varchar(11) NOT NULL,
  `clientaddress` varchar(40) NOT NULL,
  `clientemail` varchar(30) NOT NULL,
  `orderdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clientdetails`
--

INSERT INTO `clientdetails` (`salesid`, `clientname`, `clientphone`, `clientaddress`, `clientemail`, `orderdate`) VALUES
(2, 'pablo', '0908768878', 'chukwu@gmail.com', 'awknawno', '2018-10-15'),
(3, 'paul', 'paul', 'Mpape Abuja', 'paul@gmail.com', '2018-10-16');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employeeid` int(11) NOT NULL,
  `firstname` varchar(15) NOT NULL,
  `lastname` varchar(10) NOT NULL,
  `phonenumber` varchar(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `department` varchar(15) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `employeeapprovaldate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeid`, `firstname`, `lastname`, `phonenumber`, `email`, `address`, `department`, `gender`, `employeeapprovaldate`) VALUES
(1, 'Paul', 'Eze', '08059111325', 'pauleze213@gmail.com', 'Mpape Abuja', 'Sales', 'Male', '2018-10-05'),
(2, 'Sebastine', 'Ugwu', '08031842502', 'sebro@gmail.com', 'Mpape Abuja', 'Sales', 'Male', '2018-10-05');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `invoicenumber` varchar(10) NOT NULL,
  `issuedby` varchar(20) NOT NULL,
  `issuedto` varchar(20) NOT NULL,
  `issuedate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoicenumber`, `issuedby`, `issuedto`, `issuedate`) VALUES
('NMR/1', 'paul', 'omoh labake', '2018-10-05'),
('NMR/2', 'Austine', 'Dr. Anyaka', '2018-10-05'),
('NMR/3', 'As', 'AC', '2018-10-15');

-- --------------------------------------------------------

--
-- Table structure for table `loginuser`
--

CREATE TABLE `loginuser` (
  `accounttype` varchar(30) NOT NULL,
  `companyname` varchar(20) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `responsibility` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loginuser`
--

INSERT INTO `loginuser` (`accounttype`, `companyname`, `username`, `password`, `responsibility`) VALUES
('Administrator', 'Numero', 'ac', 'ac', 'Director/CEO'),
('Finance Manager', 'Numero', 'sebastine', 'sebastine', 'Manager'),
('Sales Manager', 'Numero', 'as', 'as', 'Manager'),
('HumanResources', 'Numero', 'prince', 'prince', 'Manager');

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `stockid` int(11) NOT NULL,
  `stockname` varchar(20) NOT NULL,
  `stockdescription` varchar(100) NOT NULL,
  `rate` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `purchasedate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`stockid`, `stockname`, `stockdescription`, `rate`, `quantity`, `purchasedate`) VALUES
(1, 'adc product', 'long white wooden chair', 12000, 6, '2018-10-05'),
(3, 'panadol xtra', 'panadol xtra by glaxosmithklin', 1200, 10, '2018-10-05');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `stockid` int(11) NOT NULL,
  `salesid` int(11) NOT NULL,
  `itemname` varchar(50) NOT NULL,
  `itemdescription` varchar(100) NOT NULL,
  `quantity` int(11) NOT NULL,
  `rate` double NOT NULL,
  `price` double NOT NULL,
  `orderdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`stockid`, `salesid`, `itemname`, `itemdescription`, `quantity`, `rate`, `price`, `orderdate`) VALUES
(1, 2, 'abc product', 'long white wooden chair', 4, 12600, 50400, '2018-10-15');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `stockid` int(11) NOT NULL,
  `stockname` varchar(20) NOT NULL,
  `stockdescription` varchar(50) NOT NULL,
  `rate` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `purchasedate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`stockid`, `stockname`, `stockdescription`, `rate`, `quantity`, `purchasedate`) VALUES
(1, 'abc product', 'long white wooden chair', 12000, 49, '2018-10-05'),
(2, 'chandelier', 'chandelier light', 300000, 87, '2018-10-05'),
(3, 'panadol xtra', 'panadol xtra by glaxosmithklin', 1200, 50, '2018-10-05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientdetails`
--
ALTER TABLE `clientdetails`
  ADD PRIMARY KEY (`salesid`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeid`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD UNIQUE KEY `invoicenumber` (`invoicenumber`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD KEY `fk_pstockid` (`stockid`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD KEY `fk_stockid` (`stockid`),
  ADD KEY `fk_salesid` (`salesid`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`stockid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clientdetails`
--
ALTER TABLE `clientdetails`
  MODIFY `salesid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employeeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `stockid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `fk_pstockid` FOREIGN KEY (`stockid`) REFERENCES `stock` (`stockid`);

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `fk_salesid` FOREIGN KEY (`salesid`) REFERENCES `clientdetails` (`salesid`),
  ADD CONSTRAINT `fk_stockid` FOREIGN KEY (`stockid`) REFERENCES `stock` (`stockid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
