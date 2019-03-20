-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 04 fév. 2019 à 01:08
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_banque`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `compte_id` int(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mot_de_passe` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `numero_compte` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `solde` double NOT NULL,
  `date_creation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`compte_id`),
  UNIQUE KEY `index_numero_compte` (`numero_compte`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`compte_id`, `nom`, `prenom`, `mot_de_passe`, `numero_compte`, `solde`, `date_creation`) VALUES
(1, 'Angui', 'Bini Kouadio Josias', '0000', '010220191600', 1500000, '2019-02-01 16:02:03'),
(2, 'Koné', 'Jacques', '0101', '010220191601', 149996, '2019-02-01 16:02:03'),
(4, 'Adouko', 'Anoh', '1234', '101549206002143', 425, '2019-02-03 15:00:02'),
(16, 'Coulibaly', 'Karamoko', '7777', '101549241406829', 400000, '2019-02-04 00:50:07'),
(14, 'Guia', 'Rufus', '5555', '101549239759327', 80500, '2019-02-04 00:22:39'),
(15, 'Die', 'Thiery', '0707', '101549240427818', 10000, '2019-02-04 00:33:48');

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

DROP TABLE IF EXISTS `operation`;
CREATE TABLE IF NOT EXISTS `operation` (
  `operation_id` int(20) NOT NULL AUTO_INCREMENT,
  `type_operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'retrait, depot',
  `compte_numero` varchar(20) NOT NULL,
  `date_operation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'date et l''heure de l''operation',
  PRIMARY KEY (`operation_id`),
  KEY `fk_compte_numero` (`compte_numero`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`operation_id`, `type_operation`, `compte_numero`, `date_operation`) VALUES
(1, 'test', '010220191600', '2019-02-03 17:36:01'),
(2, 'test2', '010220191600', '2019-02-03 21:29:55'),
(3, 'Dépôt d\'un montant de: 55.0 FCFA', '010220191600', '2019-02-04 00:18:46'),
(4, 'Retrait d\'un montant de: 45.0 FCFA', '101549206002143', '2019-02-04 00:21:04'),
(5, 'Création du compte avec solde initial : 0.0 FCFA', '101549239759327', '2019-02-04 00:22:39'),
(6, 'Modification de solde de 14630.0 FCFA à 1500000.0 FCFA', '010220191600', '2019-02-04 00:31:20'),
(7, 'Création du compte avec solde initial : 0.0 FCFA', '101549240427818', '2019-02-04 00:33:48'),
(8, 'Création du compte avec un solde initial de 400000.0 FCFA', '101549241406829', '2019-02-04 00:50:07'),
(9, 'Dépôt d\'un montant de: 150000.0 FCFA', '010220191601', '2019-02-04 01:03:26'),
(10, 'Retrait d\'un montant de: 14.0 FCFA', '010220191601', '2019-02-04 01:03:55'),
(11, 'Modification de solde de 0.0 FCFA à 10000.0 FCFA', '101549240427818', '2019-02-04 01:04:25'),
(12, 'Dépôt d\'un montant de: 80500.0 FCFA', '101549239759327', '2019-02-04 01:04:50');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
