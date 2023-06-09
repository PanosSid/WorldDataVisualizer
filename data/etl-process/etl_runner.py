import transform_demographics
import transform_economics
import transform_midyear_pop_age_sex
import transform_midyear_pop_age_sex
import transform_countries

CLEANED_FILES_PATH = "etl-process\\transformed-data"

transform_countries.cleanCountriesPart1(CLEANED_FILES_PATH)
transform_demographics.cleanDemographics(CLEANED_FILES_PATH)
transform_economics.cleanEconomics(CLEANED_FILES_PATH)
transform_midyear_pop_age_sex.cleanMidyearPopulationAgeSex(CLEANED_FILES_PATH)
transform_countries.cleanCountriesPart2(CLEANED_FILES_PATH)