import transform_demographics
import transform_economics
import transform_midyear_pop_age_sex
import transform_midyear_pop_age_sex
import transform_countries

# CLEANED_FILES_PATH = "D:\\Panos\\CSE UOI\\10o εξάμηνο\\Προχωρημένα Θέματα ΤΕΒΔ\\db2-project material 22-23\\ProjectCode - WorldDataVisualizer\\WorldDataVisualizer\\etl-process\\transformed-data"
CLEANED_FILES_PATH = "C:\\Users\\Panos\\Desktop"

transform_countries.cleanCountriesPart1(CLEANED_FILES_PATH)
transform_demographics.cleanDemographics(CLEANED_FILES_PATH)
transform_economics.cleanEconomics(CLEANED_FILES_PATH)
transform_midyear_pop_age_sex.cleanMidyearPopulationAgeSex(CLEANED_FILES_PATH)
transform_countries.cleanCountriesPart2(CLEANED_FILES_PATH)