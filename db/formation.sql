/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100130
 Source Host           : localhost:3306
 Source Schema         : formation

 Target Server Type    : MySQL
 Target Server Version : 100130
 File Encoding         : 65001

 Date: 21/01/2021 22:39:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enseigne
-- ----------------------------
DROP TABLE IF EXISTS `enseigne`;
CREATE TABLE `enseigne` (
  `idUser` int(11) DEFAULT NULL,
  `idFormation` int(11) DEFAULT NULL,
  KEY `userkey` (`idUser`),
  KEY `form` (`idFormation`),
  CONSTRAINT `form` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`idFormation`),
  CONSTRAINT `userkey` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enseigne
-- ----------------------------
BEGIN;
INSERT INTO `enseigne` VALUES (8, 4);
INSERT INTO `enseigne` VALUES (14, 15);
COMMIT;

-- ----------------------------
-- Table structure for formation
-- ----------------------------
DROP TABLE IF EXISTS `formation`;
CREATE TABLE `formation` (
  `idFormation` int(255) NOT NULL AUTO_INCREMENT,
  `nomFormation` varchar(255) DEFAULT NULL,
  `prix` double(255,0) DEFAULT NULL,
  `duree` int(255) DEFAULT NULL,
  `participantMax` int(255) DEFAULT NULL,
  `participantMin` int(255) DEFAULT NULL,
  `supprime` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`idFormation`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of formation
-- ----------------------------
BEGIN;
INSERT INTO `formation` VALUES (4, 'PHP', 800, 50, 5, 2, 0);
INSERT INTO `formation` VALUES (5, 'NED', 2, 3, 4, 2, 0);
INSERT INTO `formation` VALUES (6, 'AN', 4, 5, 9, 7, 0);
INSERT INTO `formation` VALUES (7, 'MySQL', 20, 10, 2, 1, 0);
INSERT INTO `formation` VALUES (8, 'HTML', 10, 10, 10, 0, 0);
INSERT INTO `formation` VALUES (9, 'NEDER', 200, 400, 5, 2, 0);
INSERT INTO `formation` VALUES (10, 'NONONONO', 12, 23, 56, 45, 0);
INSERT INTO `formation` VALUES (11, 'SGBD2', 36, 71, 9, 1, 1);
INSERT INTO `formation` VALUES (13, 'NONONONO', 12, 23, 56, 45, 0);
INSERT INTO `formation` VALUES (14, 'NONONONO', 12, 23, 56, 45, 0);
INSERT INTO `formation` VALUES (15, 'Amin', 12, 3, 13, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for inscription
-- ----------------------------
DROP TABLE IF EXISTS `inscription`;
CREATE TABLE `inscription` (
  `idinscription` int(11) NOT NULL AUTO_INCREMENT,
  `idSession` int(255) NOT NULL,
  `idUser` int(255) DEFAULT NULL,
  `statutPaiement` int(255) DEFAULT NULL,
  `notificationPaiement` int(255) DEFAULT NULL,
  `prix` double(255,0) DEFAULT '0',
  `annule` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idinscription`),
  KEY `idUser` (`idUser`) USING BTREE,
  KEY `sessioninscript` (`idSession`) USING BTREE,
  CONSTRAINT `sessioninscript` FOREIGN KEY (`idSession`) REFERENCES `session` (`idSession`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userIns` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of inscription
-- ----------------------------
BEGIN;
INSERT INTO `inscription` VALUES (1, 2, 1, 1, 1, 0, 0);
INSERT INTO `inscription` VALUES (2, 1, 1, 0, 1, 0, 0);
INSERT INTO `inscription` VALUES (3, 22, 1, 0, 0, 0, 0);
INSERT INTO `inscription` VALUES (4, 23, 1, 0, 0, 12, 0);
COMMIT;

-- ----------------------------
-- Table structure for local
-- ----------------------------
DROP TABLE IF EXISTS `local`;
CREATE TABLE `local` (
  `idLocal` int(255) NOT NULL AUTO_INCREMENT,
  `nomLocal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idLocal`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of local
-- ----------------------------
BEGIN;
INSERT INTO `local` VALUES (1, 'Uccle');
INSERT INTO `local` VALUES (2, 'Charleroi');
INSERT INTO `local` VALUES (3, 'Paris');
INSERT INTO `local` VALUES (4, 'Londres');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `idRole` int(255) NOT NULL AUTO_INCREMENT,
  `nomRole` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idRole`) USING BTREE,
  KEY `idRole` (`idRole`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'stagiaire');
INSERT INTO `role` VALUES (2, 'admin');
INSERT INTO `role` VALUES (3, 'formateur');
COMMIT;

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `idSession` int(255) NOT NULL AUTO_INCREMENT,
  `idFormation` int(255) DEFAULT NULL,
  `idFormateur` int(255) DEFAULT NULL,
  `idLocal` int(11) DEFAULT '0',
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `supprime` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`idSession`) USING BTREE,
  KEY `idFormation` (`idFormation`) USING BTREE,
  KEY `formaSession` (`idFormateur`) USING BTREE,
  KEY `localSession` (`idLocal`),
  CONSTRAINT `formaSession` FOREIGN KEY (`idFormateur`) REFERENCES `user` (`idUser`),
  CONSTRAINT `formationSession` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`idFormation`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `localSession` FOREIGN KEY (`idLocal`) REFERENCES `local` (`idLocal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of session
-- ----------------------------
BEGIN;
INSERT INTO `session` VALUES (1, 4, 11, 1, '2020-11-19', '2019-01-08', 1);
INSERT INTO `session` VALUES (2, 5, 10, 2, '2021-01-12', '2021-01-30', 0);
INSERT INTO `session` VALUES (3, 6, 1, 3, '2020-12-17', '2021-02-19', 0);
INSERT INTO `session` VALUES (4, 14, 8, 3, '2020-10-10', '2020-11-11', 0);
INSERT INTO `session` VALUES (13, 14, 10, 4, '2020-10-10', '2020-11-11', 0);
INSERT INTO `session` VALUES (14, 13, 10, 4, '2020-10-10', '2020-09-09', 0);
INSERT INTO `session` VALUES (15, 13, 10, 4, '2020-09-09', '2020-10-10', 0);
INSERT INTO `session` VALUES (16, 13, 8, 4, '2020-10-10', '2020-09-09', 0);
INSERT INTO `session` VALUES (17, 14, 10, 4, '2020-10-10', '2020-09-09', 0);
INSERT INTO `session` VALUES (18, 14, 8, 2, '2020-09-09', '2020-10-10', 0);
INSERT INTO `session` VALUES (19, 14, 10, 4, '2020-10-10', '2020-09-09', 0);
INSERT INTO `session` VALUES (20, 14, 10, 4, '2020-10-10', '2020-09-09', 0);
INSERT INTO `session` VALUES (21, 14, 8, 4, '2020-10-10', '2020-09-09', 0);
INSERT INTO `session` VALUES (22, 14, 8, 4, '2020-10-10', '2020-09-09', 0);
INSERT INTO `session` VALUES (23, 14, 8, 4, '2020-10-10', '2020-10-11', 0);
INSERT INTO `session` VALUES (24, 14, 10, 4, '2020-10-10', '2021-01-24', 0);
INSERT INTO `session` VALUES (25, 15, 14, 2, '2020-10-10', '2021-01-24', 0);
INSERT INTO `session` VALUES (26, 15, 14, 4, '2023-10-10', '2023-10-12', 0);
COMMIT;

-- ----------------------------
-- Table structure for statut
-- ----------------------------
DROP TABLE IF EXISTS `statut`;
CREATE TABLE `statut` (
  `idStatut` int(255) NOT NULL AUTO_INCREMENT,
  `nomStatut` varchar(255) DEFAULT NULL,
  `discount` double(255,0) DEFAULT '0',
  PRIMARY KEY (`idStatut`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of statut
-- ----------------------------
BEGIN;
INSERT INTO `statut` VALUES (1, 'etudiant', 25);
INSERT INTO `statut` VALUES (2, 'chomeur', 50);
INSERT INTO `statut` VALUES (3, 'ouvrier', 30);
INSERT INTO `statut` VALUES (4, 'NA', 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `idUser` int(255) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT '1',
  `statut` int(11) DEFAULT '4',
  `supprime` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`idUser`) USING BTREE,
  KEY `statutUser` (`statut`) USING BTREE,
  KEY `roleUser` (`role`) USING BTREE,
  CONSTRAINT `roleUser` FOREIGN KEY (`role`) REFERENCES `role` (`idRole`),
  CONSTRAINT `statutUser` FOREIGN KEY (`statut`) REFERENCES `statut` (`idStatut`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'root', 'prenom de root', 'root', 'root', '$2a$10$BIW9tm/kFAz0kgkw4bTulumUGgBBUDNrSWX.aCMIrrb8vi20njAKe', 1, 2, 0);
INSERT INTO `user` VALUES (2, 'nomAdmin', 'prenomAdmin', 'b', 'admin', '$2a$10$BIW9tm/kFAz0kgkw4bTulumUGgBBUDNrSWX.aCMIrrb8vi20njAKe', 2, NULL, 0);
INSERT INTO `user` VALUES (8, 'huit', 'huit', 'huit', 'formateur', '$2a$10$BIW9tm/kFAz0kgkw4bTulumUGgBBUDNrSWX.aCMIrrb8vi20njAKe', 3, 1, 1);
INSERT INTO `user` VALUES (10, 'prof', 'prof', 'prof', 'prof', 'prof', 3, NULL, 0);
INSERT INTO `user` VALUES (11, 'test', 'test', 'test', 'test', 'test', 3, 2, 1);
INSERT INTO `user` VALUES (12, 'formateur1', 'formateur1', 'adresseformateur', 'email', 'formateur', 3, NULL, 1);
INSERT INTO `user` VALUES (13, 'root', 'root', 'root', 'rooot', '$2a$10$jf1Y9rNSWCuenCka0c0TVuYetaOCkbJCfvo/GiyyQuuaa8aDCq4xC', 1, 1, 0);
INSERT INTO `user` VALUES (14, '1', '1', '1', '1', '$2a$10$BIW9tm/kFAz0kgkw4bTulumUGgBBUDNrSWX.aCMIrrb8vi20njAKe', 3, 1, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
