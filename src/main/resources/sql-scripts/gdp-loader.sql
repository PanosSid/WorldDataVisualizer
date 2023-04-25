CREATE DATABASE IF NOT EXISTS world_data_visualizer;

USE world_data_visualizer;
SET GLOBAL local_infile = TRUE;

DROP TABLE IF EXISTS gdp_total;
CREATE TABLE gdp_total (
  `country_id` int NOT NULL,
  `year` int NOT NULL,
  `country_name` varchar(70) DEFAULT NULL,
  `gdp_total_value` double DEFAULT NULL,
  PRIMARY KEY (`country_id`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


LOAD DATA LOCAL INFILE 'C:\\Users\\Panos\\Desktop\\clean_gdp_total.csv' 
INTO TABLE gdp_total
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n' 
IGNORE 1 LINES
(country_id, country_name, year, gdp_total_value);
