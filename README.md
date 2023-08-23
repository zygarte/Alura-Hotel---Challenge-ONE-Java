## Alura Hotel - Challenge ONE

Proyecto Alura Hotel, usando Java y Jdbc



## SQL requerido para la ejecucion del proyecto

CREATE DATABASE alura_hotel;

USE alura_hotel;

DROP TABLE IF EXISTS `reservas`;
CREATE TABLE `reservas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_entrada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `valor` varchar(50) DEFAULT NULL,
  `forma_de_pago` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `huespedes`;
CREATE TABLE `huespedes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `nacionalidad` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `id_reserva` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_reserva` (`id_reserva`),
  CONSTRAINT `huespedes_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id`)
);

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `nombre` varchar(50) DEFAULT NULL,
  `contraseña` varchar(50) DEFAULT NULL
);

INSERT INTO `usuarios` VALUES ('admin','admin');


