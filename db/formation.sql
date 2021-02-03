-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  mer. 03 fév. 2021 à 13:00
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `formation`
--

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `cleanDb` ()  UPDATE session SET supprime = 1 WHERE DATEDIFF(now(),session.dateFin)> 365$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `enseigne`
--

CREATE TABLE `enseigne` (
  `idUser` int(11) DEFAULT NULL,
  `idFormation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseigne`
--

INSERT INTO `enseigne` (`idUser`, `idFormation`) VALUES
(8, 4),
(14, 15),
(14, 8),
(15, 17);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `idFormation` int(255) NOT NULL,
  `nomFormation` varchar(255) DEFAULT NULL,
  `prix` double(255,0) DEFAULT NULL,
  `duree` int(255) DEFAULT NULL,
  `participantMax` int(255) DEFAULT NULL,
  `supprime` tinyint(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`idFormation`, `nomFormation`, `prix`, `duree`, `participantMax`, `supprime`) VALUES
(4, 'PHP', 800, 50, 5, 0),
(5, 'NED', 2, 3, 4, 0),
(6, 'AN', 4, 5, 9, 0),
(7, 'MySQL', 20, 10, 2, 0),
(8, 'HTML', 10, 10, 10, 0),
(9, 'NEDER', 200, 400, 5, 0),
(10, 'NONONONO', 12, 23, 56, 0),
(11, 'SGBD2', 36, 71, 9, 1),
(13, 'NONONONO', 12, 23, 56, 1),
(14, 'NONONONO', 12, 23, 56, 1),
(15, 'Amin', 12, 3, 13, 0),
(16, 'Strucute des ordinateurs', 10, 3, 1, 0),
(17, 'Latin', 134, 4, 1, 0),
(18, 'Nederlands', 424, 10, 11, 1);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `idinscription` int(11) NOT NULL,
  `idSession` int(255) NOT NULL,
  `idUser` int(255) DEFAULT NULL,
  `statutPaiement` int(255) DEFAULT NULL,
  `notificationPaiement` int(255) DEFAULT NULL,
  `prix` double(255,0) DEFAULT '0',
  `annule` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`idinscription`, `idSession`, `idUser`, `statutPaiement`, `notificationPaiement`, `prix`, `annule`) VALUES
(1, 2, 1, 1, 1, 0, 0),
(3, 22, 1, 0, 0, 0, 0),
(4, 23, 1, 0, 0, 12, 0),
(6, 28, 20, 0, 0, 39, 0);

-- --------------------------------------------------------

--
-- Structure de la table `local`
--

CREATE TABLE `local` (
  `idLocal` int(255) NOT NULL,
  `nomLocal` varchar(255) DEFAULT NULL,
  `supprime` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `local`
--

INSERT INTO `local` (`idLocal`, `nomLocal`, `supprime`) VALUES
(1, 'Uccle', 0),
(2, 'Charleroi', 1),
(3, 'Paris', 0),
(4, 'Londres', 0),
(5, 'Genève', 0),
(6, 'NEw-York', 0);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `idRole` int(255) NOT NULL,
  `nomRole` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`idRole`, `nomRole`) VALUES
(1, 'stagiaire'),
(2, 'admin'),
(3, 'formateur');

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `idSession` int(255) NOT NULL,
  `idFormation` int(255) DEFAULT NULL,
  `idFormateur` int(255) DEFAULT NULL,
  `idLocal` int(11) DEFAULT '0',
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `supprime` tinyint(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`idSession`, `idFormation`, `idFormateur`, `idLocal`, `dateDebut`, `dateFin`, `supprime`) VALUES
(1, 4, 11, 1, '2021-01-29', '2021-05-20', 0),
(2, 5, 10, 2, '2021-01-12', '2021-01-30', 0),
(3, 6, 1, 3, '2020-12-17', '2021-02-19', 0),
(4, 14, 8, 3, '2020-10-10', '2020-11-11', 0),
(13, 14, 10, 4, '2020-10-10', '2020-11-11', 0),
(14, 13, 10, 4, '2020-10-10', '2020-09-09', 0),
(15, 13, 10, 4, '2020-09-09', '2020-10-10', 0),
(16, 13, 8, 4, '2020-10-10', '2020-09-09', 0),
(17, 14, 10, 4, '2020-10-10', '2020-09-09', 0),
(18, 14, 8, 2, '2020-09-09', '2020-10-10', 0),
(19, 14, 10, 4, '2020-10-10', '2020-09-09', 0),
(20, 14, 10, 4, '2020-10-10', '2020-09-09', 0),
(21, 14, 8, 4, '2020-10-10', '2020-09-09', 0),
(22, 14, 8, 4, '2020-10-10', '2020-09-09', 0),
(23, 14, 8, 4, '2020-10-10', '2020-10-11', 0),
(24, 14, 10, 4, '2020-10-10', '2021-01-24', 0),
(25, 15, 14, 2, '2020-10-10', '2021-01-24', 0),
(26, 15, 14, 4, '2023-10-10', '2023-10-12', 0),
(27, 15, 14, 1, '1290-10-10', '1290-10-12', 1),
(28, 17, 15, 6, '2021-12-31', '2022-01-05', 0);

-- --------------------------------------------------------

--
-- Structure de la table `statut`
--

CREATE TABLE `statut` (
  `idStatut` int(255) NOT NULL,
  `nomStatut` varchar(255) DEFAULT NULL,
  `discount` double(255,0) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `statut`
--

INSERT INTO `statut` (`idStatut`, `nomStatut`, `discount`) VALUES
(1, 'etudiant', 25),
(2, 'chomeur', 50),
(3, 'ouvrier', 30),
(4, 'employé', 0),
(5, 'demandeur d\'emploi', 75),
(6, 'retraité', 95),
(7, '10', 16);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `idUser` int(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT '1',
  `statut` int(11) DEFAULT '4',
  `supprime` tinyint(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `nom`, `prenom`, `adresse`, `email`, `password`, `role`, `statut`, `supprime`) VALUES
(1, 'root', 'prenom de root', 'root', 'root', '$2a$10$BIW9tm/kFAz0kgkw4bTulumUGgBBUDNrSWX.aCMIrrb8vi20njAKe', 1, 2, 0),
(2, 'nomAdmin', 'prenomAdmin', 'b', 'admin', '$2a$10$BIW9tm/kFAz0kgkw4bTulumUGgBBUDNrSWX.aCMIrrb8vi20njAKe', 2, NULL, 0),
(8, 'huit', 'huit', 'huit', 'formateur', '$2a$10$BIW9tm/kFAz0kgkw4bTulumUGgBBUDNrSWX.aCMIrrb8vi20njAKe', 3, 1, 1),
(10, 'prof', 'prof', 'prof', 'prof', 'prof', 3, NULL, 0),
(11, 'test', 'test', 'test', 'test', 'test', 3, 2, 1),
(12, 'formateur1', 'formateur1', 'adresseformateur', 'email', 'formateur', 3, NULL, 1),
(13, 'root', 'root', 'root', 'rooot', '$2a$10$jf1Y9rNSWCuenCka0c0TVuYetaOCkbJCfvo/GiyyQuuaa8aDCq4xC', 1, 1, 0),
(14, 'El Amin', 'Gandouz', 'avenue du sandwich', 'gandouz@prof.com', '$2a$10$eSnpPPeuGN1LLi0xQ/V0z.FWGevpRXvLhQOSujg5ifTSgUQv48Wwe', 3, 1, 0),
(15, 'Jimmy', 'Louis', 'Avenue', 'des', '$2a$10$IxJKmH8hUUYnS02ZJ/Z8a.Y/a.bfHDCC8A7FC9gCwnXTFf9/MDgqa', 3, 4, 0),
(16, 'louis michel', 'luc', 'avenue des touriste 34', 'lmluc@sncb.be', '$2a$10$HkL1hjUw2M8/VT8rXgD27uVjkfaBhIcrDrV63YSZbhrg1Yx0EMGcO', 3, 4, 1),
(17, 'Hance', 'Fabien', 'Avenue de la libération 71', 'piperley@hotmail.com', '$2a$10$Rhz3MlcKN0s59yTEkE5c7ewYl.0eRTpU4Wgyy0mdu704weaxx1Wiq', 1, 4, 0),
(18, 'pierre paul', 'Van den Moyave', 'Avenue du chat 3', 'croquette@fromage.be', '$2a$10$PQVJtt1xsw8jEnWqAZAlIulqDktbo6BuCPVHf4F7m77sEIA0QcVqK', 1, 6, 0),
(19, 'a', 'a', 'a', 'napolitin@pizza.com', '$2a$10$3yuoOTBPt6Eq/Po7k9iqO.7P0051Y2vH96.HQsA6AkSU0a0c6/.ly', 1, 1, 0),
(20, 'simba', 'loubaba', 'avenue du singelton 1', 'factory@street.com', '$2a$10$PUcaAcfmAomLEAh3ek5U1ekW8Bo9XVRx9t3j9kDYSxdx7jx2ecMh6', 1, 6, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `enseigne`
--
ALTER TABLE `enseigne`
  ADD KEY `userkey` (`idUser`),
  ADD KEY `form` (`idFormation`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`idFormation`) USING BTREE;

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`idinscription`),
  ADD KEY `idUser` (`idUser`) USING BTREE,
  ADD KEY `sessioninscript` (`idSession`) USING BTREE;

--
-- Index pour la table `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`idLocal`) USING BTREE;

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`idRole`) USING BTREE,
  ADD KEY `idRole` (`idRole`) USING BTREE;

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`idSession`) USING BTREE,
  ADD KEY `idFormation` (`idFormation`) USING BTREE,
  ADD KEY `formaSession` (`idFormateur`) USING BTREE,
  ADD KEY `localSession` (`idLocal`);

--
-- Index pour la table `statut`
--
ALTER TABLE `statut`
  ADD PRIMARY KEY (`idStatut`) USING BTREE;

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`) USING BTREE,
  ADD KEY `statutUser` (`statut`) USING BTREE,
  ADD KEY `roleUser` (`role`) USING BTREE;

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `idFormation` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `idinscription` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `local`
--
ALTER TABLE `local`
  MODIFY `idLocal` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `idRole` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `idSession` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `statut`
--
ALTER TABLE `statut`
  MODIFY `idStatut` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `enseigne`
--
ALTER TABLE `enseigne`
  ADD CONSTRAINT `form` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`idFormation`),
  ADD CONSTRAINT `userkey` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `sessioninscript` FOREIGN KEY (`idSession`) REFERENCES `session` (`idSession`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userIns` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `formaSession` FOREIGN KEY (`idFormateur`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `formationSession` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`idFormation`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `localSession` FOREIGN KEY (`idLocal`) REFERENCES `local` (`idLocal`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `roleUser` FOREIGN KEY (`role`) REFERENCES `role` (`idRole`),
  ADD CONSTRAINT `statutUser` FOREIGN KEY (`statut`) REFERENCES `statut` (`idStatut`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
