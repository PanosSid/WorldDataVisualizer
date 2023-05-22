
CREATE DATABASE IF NOT EXISTS world_data_visualizer;

USE world_data_visualizer;

SET GLOBAL local_infile = TRUE;

CREATE TABLE `countries` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `official_name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `indicators` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `indicators_values` (
  `country_id` int NOT NULL,
  `year` int NOT NULL,
  `indicator_id` int NOT NULL,
  `indicator_value` double DEFAULT NULL,
  PRIMARY KEY (`country_id`,`year`,`indicator_id`),
  KEY `fk_indicator_id_idx` (`indicator_id`),
  CONSTRAINT `fk_c_id` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_indicator_id` FOREIGN KEY (`indicator_id`) REFERENCES `indicators` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `populations` (
  `country_id` int NOT NULL,
  `year` int NOT NULL,
  `sex` varchar(1) NOT NULL,
  `age` int NOT NULL,
  `population` int DEFAULT NULL,
  PRIMARY KEY (`country_id`,`year`,`sex`,`age`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

