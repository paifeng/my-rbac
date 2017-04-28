-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: privilegesmanage
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `privileges`
--

DROP TABLE IF EXISTS `privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privileges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `privilegename` varchar(50) NOT NULL,
  `privilegeUrl` varchar(500) NOT NULL,
  `requestaction` varchar(100) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `nologin` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `privilegename` (`privilegename`),
  UNIQUE KEY `privilegeUrl` (`privilegeUrl`,`requestaction`,`nologin`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privileges`
--

LOCK TABLES `privileges` WRITE;
/*!40000 ALTER TABLE `privileges` DISABLE KEYS */;
INSERT INTO `privileges` VALUES (1,'查看权限','/privilege/servlet/PrivilegeServlet             ','listprivileges','查看所有的权限信息','0'),(2,'删除权限','/privilege/servlet/PrivilegeServlet ','deleteprivilege','对权限进行删除操作','0'),(3,'更新权限UI','/privilege/servlet/PrivilegeServlet ','updateprivilegeUI','打开更新权限的页面','0'),(4,'更新权限','/privilege/servlet/PrivilegeServlet ','updateprivilege','更新权限信息','0'),(5,'添加权限UI','/privilege/servlet/PrivilegeServlet  ','addprivilegeUI','打开添加权限信息页面','0'),(6,'添加权限','/privilege/servlet/PrivilegeServlet ','addprivilege','添加权限信息','0'),(7,'查看角色','/privilege/servlet/RoleServlet ','listrole','查看角色信息','0'),(8,'添加角色UI','/privilege/servlet/RoleServlet ','addroleUI','打开添加角页面','0'),(9,'添加角色','/privilege/servlet/RoleServlet ','addrole','添加角色信息','0'),(10,'删除角色','/privilege/servlet/RoleServlet ','deleterole','删除角色','0'),(11,'更新角色UI','/privilege/servlet/RoleServlet ','updateroleUI','打开更新角色UI','0'),(12,'更新角色','/privilege/servlet/RoleServlet ','updaterole','更新角色信息','0'),(13,'查看用户','/privilege/servlet/UserManageServlet ','listuser','查看所有的用户信息','0'),(14,'添加用户','/privilege/servlet/UserManageServlet ','adduser','添加用户信息','0'),(15,'添加用户UI','/privilege/servlet/UserManageServlet ','adduserUI','打开添加用户信息页面','0'),(16,'删除用户','/privilege/servlet/UserManageServlet ','deleteuser','删除用户','0'),(17,'更新用户UI','/privilege/servlet/UserManageServlet ','updateuserUI','打开更新用户页面','0'),(18,'更新用户','/privilege/servlet/UserManageServlet ','updateuser','更新用户信息','0'),(19,'访问主页','/privilege/','noaction','打开用户管理系统的主页','1'),(21,'访问头部jsp页面','/privilege/page/head.jsp','noaction','打开头部jsp页面','1'),(22,'访问菜单页面','/privilege/page/menu.jsp','noaction','打开菜单页面','1'),(23,'访问main.jsp','/privilege/page/main.jsp','noaction','打开main.jsp页面','1'),(24,'登录页面','/privilege/page/login.jsp','noaction','打开登录页面','1'),(25,'登录','/privilege/servlet/UserServlet','login','登录请求','1'),(26,'请求验证码','/privilege/servlet/VerifiCodeServlet','noaction','请求一张验证码','1'),(27,'电子邮件管理页面','/privilege/page/emailmanage.jsp ','noaction','管理电子邮件','0'),(28,'管理权限页面','/privilege/page/privilegemanage.jsp ','noaction','打开管理权限的页面','0'),(29,'角色管理界面','/privilege/page/rolemanage.jsp','noaction','打开角色管理页面','0'),(30,'用户管理页面','/privilege/page/usermanage.jsp','noaction','打开用户管理界面','0'),(31,'退出登录','/privilege/servlet/UserServlet','exit','退出登录状态','0');
/*!40000 ALTER TABLE `privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roleprivilege`
--

DROP TABLE IF EXISTS `roleprivilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roleprivilege` (
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`privilege_id`),
  KEY `privilege_id` (`privilege_id`),
  CONSTRAINT `roleprivilege_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `roleprivilege_ibfk_2` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleprivilege`
--

LOCK TABLES `roleprivilege` WRITE;
/*!40000 ALTER TABLE `roleprivilege` DISABLE KEYS */;
INSERT INTO `roleprivilege` VALUES (21,1),(21,2),(21,3),(21,4),(21,5),(21,6),(21,7),(22,7),(21,8),(21,9),(21,10),(21,11),(21,12),(21,13),(22,13),(21,14),(21,15),(21,16),(21,17),(21,18),(21,19),(22,19),(21,21),(22,21),(21,22),(22,22),(21,23),(22,23),(21,24),(22,24),(21,25),(22,25),(21,26),(22,26),(21,27),(22,27),(21,28),(21,29),(22,29),(21,30),(22,30),(21,31),(22,31);
/*!40000 ALTER TABLE `roleprivilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(20) NOT NULL,
  `description` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rolename` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (21,'admin','超级管理员、拥有最高权限'),(22,'manager','管理员、拥有指定的管理员权限');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `user_id` char(32) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `userrole_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userrole_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES ('fe1f9c8e496434c28dcce0ea7e347b28',21),('68bec08ad44b3996b2aaa74fbdc4737e',22);
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` char(32) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `password` char(32) NOT NULL,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('68bec08ad44b3996b2aaa74fbdc4737e','343212295@qq.com','锋锋','e10adc3949ba59abbe56e057f20f883e','2016-08-21 07:12:07'),('fe1f9c8e496434c28dcce0ea7e347b28','hooverz@foxmail.com','张海锋','e10adc3949ba59abbe56e057f20f883e','2016-08-20 13:19:29');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-28 17:01:00
