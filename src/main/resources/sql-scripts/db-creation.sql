CREATE DATABASE IF NOT EXISTS world_data_visualizer;

USE world_data_visualizer;

DROP TABLE IF EXISTS countries_population;
CREATE TABLE `countries_population` (
  `country_id` int NOT NULL,
  `year` int NOT NULL,
  `population` int DEFAULT NULL,
  PRIMARY KEY (`country_id`,`year`)
--   CONSTRAINT `fk_country_id` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(1, 2000, 100);
INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(1, 2010, 110);
INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(1, 2020, 120);
INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(2, 2000, 200);
INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(2, 2010, 210);
INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(2, 2020, 220);
INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(3, 2000, 300);
INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(3, 2010, 310);
INSERT INTO `world_data_visualizer`.`countries_population`(`country_id`,`year`,`population`)VALUES(3, 2020, 320);
