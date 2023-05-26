import pandas as pd

def transformToLongFormat(df,idColumnNames, metricColumnName, metricValueColumnName):
    df = pd.melt(df, id_vars = idColumnNames, value_vars = list(df.columns),
                   var_name= metricColumnName, value_name=metricValueColumnName)                  
    return df

def cleanMidyearPopulationAgeSex(cleandFilesPath):
    fileName = "midyear_population_age_sex"
    pivot_df = pd.read_csv(cleandFilesPath+'\cleaned_countries.csv')
    pivot_df = pivot_df[["ISO_Code", "FIPS"]]
    print("Cleaning: midyear_population_age_sex.csv")
    df = pd.read_csv("extracted-data\\"+fileName+".csv", encoding="UTF8")

    df = df.drop(['country_name','max_age'], axis=1, errors='ignore')
    df = df.rename(columns=lambda x: x.replace('population_age_', ''))
    df['sex'] = df['sex'].replace({'Male': 'M', 'Female': 'F'})

    df = transformToLongFormat(df, ['country_code', 'year', 'sex'], 'age', 'population')
    df = pd.merge(df, pivot_df, left_on = "country_code", right_on = "FIPS")

    df = df.drop(['FIPS', 'country_code'], axis=1)

    df.rename(columns = {'ISO_Code':'country_id'}, inplace = True)
    df = df[['country_id', 'year', 'sex', 'age', 'population']]

    # Group the data by country_id, year, age, and sex
    grouped = df.groupby(['country_id', 'year', 'age', 'sex']).sum().reset_index()

    # Pivot the data to have separate columns for male_population and female_population
    pivoted = grouped.pivot_table(values='population', index=['country_id', 'year', 'age'],
                              columns='sex', aggfunc=sum, fill_value=0).reset_index()

    # Rename the columns to match the desired format
    pivoted.columns = ['country_id', 'year', 'age','female_population',  'male_population']

    
    pivoted.to_csv(cleandFilesPath+"\clean_midyear_population_age_sex.csv", index=False)
    print("--- Cleaning Completed ---")





