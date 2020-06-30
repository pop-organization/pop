#script para crear un estado en un Mariadb dockerizado

CREATE DATABASE IF NOT EXISTS `POP` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `POP`;

# words
CREATE TABLE IF NOT EXISTS `words` (
  `id` varchar(32) NOT NULL ,
  `original` varchar(50) NOT NULL,
  `modified` varchar(50) NOT NULL,
  `date_insert` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
