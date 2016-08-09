-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- Host: sql6.freesqldatabase.com
-- Generation Time: Aug 09, 2016 at 01:59 PM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sql6126036`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblanswer`
--

CREATE TABLE IF NOT EXISTS `tblanswer` (
  `AnswerID` int(11) NOT NULL,
  `PosibleAns` int(11) NOT NULL,
  `Answer1` varchar(45) DEFAULT NULL,
  `Answer2` varchar(45) DEFAULT NULL,
  `Answer3` varchar(45) DEFAULT NULL,
  `Answer4` varchar(45) DEFAULT NULL,
  `RightAnswer` int(11) DEFAULT NULL,
  PRIMARY KEY (`AnswerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblanswer`
--

INSERT INTO `tblanswer` (`AnswerID`, `PosibleAns`, `Answer1`, `Answer2`, `Answer3`, `Answer4`, `RightAnswer`) VALUES
(2000, 4, '2008', '2004', '2011', '2012', 1),
(2001, 4, '1990', '1988', '1995', '2000', 3),
(2002, 4, 'Sundar Pichai', 'Bil gats', 'Mark zokerberg', 'Stive jobs', 1),
(2003, 4, '2008', '2011', '2005', '2012', 1),
(2004, 4, 'Bob Marly', 'michel jecson', 'santana', 'Alvis Preslly', 1),
(2005, 4, 'Russia', 'France', 'UK', 'US', 1),
(2006, 2, 'True', 'False', NULL, NULL, 2),
(2007, 2, 'True', 'False', NULL, NULL, 1),
(2008, 2, 'True', 'False', NULL, NULL, 2),
(2009, 2, 'True', 'False', NULL, NULL, 1),
(2010, 1, 'Jerusalem', NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tblquestion`
--

CREATE TABLE IF NOT EXISTS `tblquestion` (
  `QuestionID` int(11) NOT NULL,
  `Question` varchar(81) NOT NULL,
  `Level` varchar(45) DEFAULT NULL,
  `Point` int(11) NOT NULL DEFAULT '1',
  `QuesStatusAskOrNot` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`QuestionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblquestion`
--

INSERT INTO `tblquestion` (`QuestionID`, `Question`, `Level`, `Point`, `QuesStatusAskOrNot`) VALUES
(2000, 'Wich year Osama bin laden diye?', '1', 1, 0),
(2001, 'When JAVA was made out?', '1', 1, 0),
(2002, 'Who is th CEO of Google', '2', 3, 0),
(2003, 'Wich year the androaid OS was created?', '1', 1, 0),
(2004, 'Which reggae singing star died 11th May 1981?', '1', 1, 0),
(2005, 'which country has the largest area?', '2', 3, 0),
(2006, 'Lightning never strikes in the same place twice.', '2', 3, 0),
(2007, 'Adults have fewer bones than babies do.', '3', 5, 0),
(2008, 'Goldfish only have a memory of three seconds.', '2', 3, 0),
(2009, 'Humans can’t breathe and swallow at the same time.', '3', 5, 0),
(2010, 'Wat is the capital city of isreal?', '1', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblrecords`
--

CREATE TABLE IF NOT EXISTS `tblrecords` (
  `UserID` int(11) NOT NULL,
  `Score` int(11) DEFAULT '0',
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblrecords`
--

INSERT INTO `tblrecords` (`UserID`, `Score`, `Date`) VALUES
(1234, 12, '2016-01-26'),
(1243, 0, '2016-01-31'),
(2000, 19, '1970-01-01'),
(2001, 18, '1970-01-01'),
(3097, 7, '1970-01-01'),
(4321, 8, '2016-01-13'),
(5678, 47, '2016-01-26'),
(6767, 10, '1970-01-01'),
(7654, 19, '2015-11-02'),
(7890, 3, '2015-12-08'),
(8765, 34, '2016-01-02'),
(9876, 11, '2016-01-26'),
(99999, 5, '1970-01-01'),
(121212, 30, '2015-12-23'),
(123123, 13, '1970-01-01'),
(567567, 15, '2016-02-02'),
(567568, 0, '2016-07-04'),
(567569, 2, '2016-07-20'),
(567570, 0, '2016-07-28');

-- --------------------------------------------------------

--
-- Table structure for table `tblusers`
--

CREATE TABLE IF NOT EXISTS `tblusers` (
  `UserId` int(11) NOT NULL,
  `UserName` char(80) DEFAULT NULL,
  `Password` int(11) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblusers`
--

INSERT INTO `tblusers` (`UserId`, `UserName`, `Password`) VALUES
(1243, 'koko', 7777),
(2000, 'naor', 101),
(2001, 'dodo', 1234),
(3097, 'ester', 5555),
(5678, 'lidor', 1111),
(6767, 'orly', 4321),
(7654, 'moshe', 12345),
(7890, 'yair', 987),
(8765, 'lior', 101),
(9876, 'moshe', 5555),
(99999, 'amr', 123456),
(121212, 'mayyn', 56789),
(123123, 'avi', 112233),
(567567, 'anel', 1234),
(567568, 'yoyodd', 12345),
(567569, '000', 0),
(567570, 'mhao', 1234);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
