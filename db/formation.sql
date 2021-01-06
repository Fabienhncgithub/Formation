/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100414
 Source Host           : localhost:3306
 Source Schema         : formation

 Target Server Type    : MySQL
 Target Server Version : 100414
 File Encoding         : 65001

 Date: 06/01/2021 13:22:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for formation
-- ----------------------------
DROP TABLE IF EXISTS `formation`;
CREATE TABLE `formation`  (
  `idFormation` int(255) NOT NULL AUTO_INCREMENT,
  `nomFormation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `prix` double(255, 0) NULL DEFAULT NULL,
  `duree` int(255) NULL DEFAULT NULL,
  `participantMax` int(255) NULL DEFAULT NULL,
  `participantMin` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idFormation`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of formation
-- ----------------------------
INSERT INTO `formation` VALUES (4, 'PHP', 800, 50, 5, 2);
INSERT INTO `formation` VALUES (5, 'NED', 2, 3, 4, 2);
INSERT INTO `formation` VALUES (6, 'AN', 4, 5, 9, 7);
INSERT INTO `formation` VALUES (7, 'MySQL', 20, 10, 2, 1);
INSERT INTO `formation` VALUES (8, 'HTML', 10, 10, 10, 0);
INSERT INTO `formation` VALUES (9, 'NEDER', 200, 400, 5, 2);
INSERT INTO `formation` VALUES (10, 'NONONONO', 12, 23, 56, 45);

-- ----------------------------
-- Table structure for inscription
-- ----------------------------
DROP TABLE IF EXISTS `inscription`;
CREATE TABLE `inscription`  (
  `idSession` int(255) NOT NULL,
  `idUser` int(255) NULL DEFAULT NULL,
  `statutPaiement` int(255) NULL DEFAULT NULL,
  `notificationPaiement` int(255) NULL DEFAULT NULL,
  INDEX `idUser`(`idUser`) USING BTREE,
  INDEX `sessioninscript`(`idSession`) USING BTREE,
  CONSTRAINT `sessioninscript` FOREIGN KEY (`idSession`) REFERENCES `session` (`idSession`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userIns` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for local
-- ----------------------------
DROP TABLE IF EXISTS `local`;
CREATE TABLE `local`  (
  `idLocal` int(255) NOT NULL AUTO_INCREMENT,
  `nomLocal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idLocal`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of local
-- ----------------------------
INSERT INTO `local` VALUES (1, 'Uccle');
INSERT INTO `local` VALUES (2, 'Charleroi');
INSERT INTO `local` VALUES (3, 'Paris');
INSERT INTO `local` VALUES (4, 'Londres');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `idRole` int(255) NOT NULL AUTO_INCREMENT,
  `nomRole` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idRole`) USING BTREE,
  INDEX `idRole`(`idRole`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'stagiaire');
INSERT INTO `role` VALUES (2, 'admin');
INSERT INTO `role` VALUES (3, 'formateur');

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `idSession` int(255) NOT NULL AUTO_INCREMENT,
  `idFormation` int(255) NULL DEFAULT NULL,
  `idFormateur` int(255) NULL DEFAULT NULL,
  `idLocal` int(255) NULL DEFAULT NULL,
  `dateDebut` datetime(0) NULL DEFAULT NULL,
  `dateFin` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`idSession`) USING BTREE,
  INDEX `idFormation`(`idFormation`) USING BTREE,
  INDEX `formaSession`(`idFormateur`) USING BTREE,
  CONSTRAINT `formaSession` FOREIGN KEY (`idFormateur`) REFERENCES `user` (`idUser`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `formationSession` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`idFormation`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `localSession` FOREIGN KEY (`idSession`) REFERENCES `local` (`idLocal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES (1, 4, 1, 1, '2020-11-19 23:31:24', '2020-11-20 23:31:27');

-- ----------------------------
-- Table structure for statut
-- ----------------------------
DROP TABLE IF EXISTS `statut`;
CREATE TABLE `statut`  (
  `idStatut` int(255) NOT NULL AUTO_INCREMENT,
  `nomStatut` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idStatut`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statut
-- ----------------------------
INSERT INTO `statut` VALUES (1, 'etudiant');
INSERT INTO `statut` VALUES (2, 'chomeur');
INSERT INTO `statut` VALUES (3, 'ouvrier');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `idUser` int(255) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `prenom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `adresse` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int(255) NULL DEFAULT NULL,
  `statut` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idUser`) USING BTREE,
  INDEX `statutUser`(`statut`) USING BTREE,
  INDEX `roleUser`(`role`) USING BTREE,
  CONSTRAINT `roleUser` FOREIGN KEY (`role`) REFERENCES `role` (`idRole`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `statutUser` FOREIGN KEY (`statut`) REFERENCES `statut` (`idStatut`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 'root', 'root', 'root', 'root', 1, 2);
INSERT INTO `user` VALUES (2, 'nomAdmin', 'prenomAdmin', 'b', 'admin', 'admin', 2, NULL);
INSERT INTO `user` VALUES (8, 't', 't', 't', 't', 't', 1, 1);
INSERT INTO `user` VALUES (10, 'prof', 'prof', 'prof', 'prof', 'prof', 3, NULL);

SET FOREIGN_KEY_CHECKS = 1;
