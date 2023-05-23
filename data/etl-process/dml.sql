USE world_data_visualizer;

SET GLOBAL local_infile = TRUE;

LOAD DATA LOCAL INFILE 'C:\\Users\\Panos\\Desktop\\indicators.csv' 
INTO TABLE indicators
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n' 
IGNORE 1 LINES
(id, name);

LOAD DATA LOCAL INFILE 'C:\\Users\\Panos\\Desktop\\cleaned_countries.csv' 
INTO TABLE countries
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n' 
IGNORE 1 LINES
(id, name);

LOAD DATA LOCAL INFILE 'C:\\Users\\Panos\\Desktop\\clean_midyear_population_age_sex.csv' 
INTO TABLE populations
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n' 
IGNORE 1 LINES
(country_id, year, age, female_population, male_population);

LOAD DATA LOCAL INFILE 'C:\\Users\\Panos\\Desktop\\clean_economics.csv' 
INTO TABLE indicators_values
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n' 
IGNORE 1 LINES
(country_id, year, indicator_id, indicator_value);

LOAD DATA LOCAL INFILE 'C:\\Users\\Panos\\Desktop\\clean_mortality_life_expectancy.csv' 
INTO TABLE indicators_values
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n' 
IGNORE 1 LINES
(country_id, year, indicator_id, indicator_value);

LOAD DATA LOCAL INFILE 'C:\\Users\\Panos\\Desktop\\clean_age_specific_fertility_rates.csv' 
INTO TABLE indicators_values
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n' 
IGNORE 1 LINES
(country_id, year, indicator_id, indicator_value);

LOAD DATA LOCAL INFILE 'C:\\Users\\Panos\\Desktop\\clean_birth_death_growth_rates.csv' 
INTO TABLE indicators_values
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n' 
IGNORE 1 LINES
(country_id, year, indicator_id, indicator_value);

-- remove all countries that dont have an indicator
SET SQL_SAFE_UPDATES = 0;
DELETE FROM countries
WHERE id NOT IN (
    SELECT DISTINCT country_id FROM indicators_values
    UNION
    SELECT DISTINCT country_id FROM populations
);
SET SQL_SAFE_UPDATES = 1;

