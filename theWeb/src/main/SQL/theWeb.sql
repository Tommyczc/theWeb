-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.4.25-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 theweb 的数据库结构
CREATE DATABASE IF NOT EXISTS `theweb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `theweb`;

-- 导出  表 theweb.permissionlist 结构
CREATE TABLE IF NOT EXISTS `permissionlist` (
  `roleName` varchar(50) NOT NULL,
  `permission` varchar(50) NOT NULL,
  KEY `roleName` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  theweb.permissionlist 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `permissionlist` DISABLE KEYS */;
REPLACE INTO `permissionlist` (`roleName`, `permission`) VALUES
	('admin', 'admin:search'),
	('admin', 'admin:insert'),
	('user', 'user:search'),
	('admin', 'admin:delete'),
	('admin', 'admin:update'),
	('user', 'user:insert'),
	('user', 'user:update'),
	('user', 'user:delete'),
	('guest', 'guest:none');
/*!40000 ALTER TABLE `permissionlist` ENABLE KEYS */;

-- 导出  表 theweb.picturelist 结构
CREATE TABLE IF NOT EXISTS `picturelist` (
  `userName` varchar(50) NOT NULL,
  `pictureUrl` varchar(1000) NOT NULL,
  `description` varchar(5000) DEFAULT NULL,
  KEY `FK_picturelist_userlist` (`userName`),
  CONSTRAINT `FK_picturelist_userlist` FOREIGN KEY (`userName`) REFERENCES `userlist` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  theweb.picturelist 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `picturelist` DISABLE KEYS */;
/*!40000 ALTER TABLE `picturelist` ENABLE KEYS */;

-- 导出  表 theweb.rolelist 结构
CREATE TABLE IF NOT EXISTS `rolelist` (
  `roleName` varchar(50) DEFAULT NULL,
  `userName` varchar(50) NOT NULL,
  KEY `FK_rolelist_userlist` (`userName`),
  KEY `roleName` (`roleName`),
  CONSTRAINT `FK_rolelist_permissionlist` FOREIGN KEY (`roleName`) REFERENCES `permissionlist` (`roleName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_rolelist_userlist` FOREIGN KEY (`userName`) REFERENCES `userlist` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  theweb.rolelist 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `rolelist` DISABLE KEYS */;
REPLACE INTO `rolelist` (`roleName`, `userName`) VALUES
	('admin', 'tommy'),
	('user', 'tommy'),
	('user', 'lily');
/*!40000 ALTER TABLE `rolelist` ENABLE KEYS */;

-- 导出  表 theweb.userlist 结构
CREATE TABLE IF NOT EXISTS `userlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `age` int(3) unsigned zerofill DEFAULT 000,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='the basic user login information';

-- 正在导出表  theweb.userlist 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `userlist` DISABLE KEYS */;
REPLACE INTO `userlist` (`id`, `userName`, `password`, `age`, `address`) VALUES
	(1, 'tommy', '12345', 000, NULL),
	(2, 'lily', '12345', 013, NULL);
/*!40000 ALTER TABLE `userlist` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
