-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 30 Août 2015 à 17:09
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `banque`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `numcompte` varchar(32) NOT NULL,
  `nom` varchar(32) NOT NULL,
  `prenom` varchar(32) NOT NULL,
  `solde` int(11) NOT NULL DEFAULT '0',
  `code` varchar(8) NOT NULL,
  `etat` int(11) NOT NULL DEFAULT '1',
  `sexe` varchar(10) NOT NULL DEFAULT 'masculin',
  `cni` varchar(10) NOT NULL,
  `etat_supp` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`numcompte`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`numcompte`, `nom`, `prenom`, `solde`, `code`, `etat`, `sexe`, `cni`, `etat_supp`) VALUES
('254623497', 'WILIDA', 'JACQUELINE', 4850000, '0248', 1, 'féminin', '108465124', 0),
('254623498', 'ZAMID', 'ismaël', 400000, '0000', 1, 'masculin', '108546279', 0),
('254623501', 'zangué', 'josué', 50000, '0000', 1, 'masculin', '109300254', 0),
('254623499', 'NAMDOU', 'GERMAINE', 50000, '0000', 1, 'féminin', '108654213', 0),
('254623500', 'NAMA', 'FATIMÉ', 95000, '0000', 1, 'féminin', '108621548', 0),
('254623502', 'kepnan', 'joel didier', 100000, '0000', 1, 'masculin', '109510456', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
