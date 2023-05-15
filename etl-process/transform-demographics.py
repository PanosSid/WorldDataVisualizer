import pandas as pd


def transformToLongFormat(df,idColumnNames, metricColumnName, metricValueColumnName):
    df = pd.melt(df, id_vars = idColumnNames, value_vars = list(df.columns),
                   var_name= metricColumnName, value_name=metricValueColumnName)                  
    return df

def formatCleanDataSetFileName(name):
    return "transformed-data\clean_"+name.lower().replace(' ', '_')+".csv"


# pivot_df = pd.read_csv("extracted-data\countries.csv", encoding="ANSI")
pivot_df = pd.read_csv("tmp_files\\tmp_countries.csv")

pivot_df = pivot_df[["ISO_Code", "FIPS"]]
indicator_ids_df = pd.read_csv("indicator-id-mapping.csv")


demographicsCSVNames = [
    # "birth_death_growth_rates",
    "age_specific_fertility_rates",
    "mortality_life_expectancy",
    "country_names_area",
    "midyear_population_age_sex"
]

fileName = "birth_death_growth_rates"

print("Cleaning: ...")
# df = pd.read_csv("extracted-data\mortality_life_expectancy.csv", encoding="UTF8")
# df = pd.read_csv("extracted-data\\birth_death_growth_rates.csv", encoding="UTF8")
df = pd.read_csv("extracted-data\\"+fileName+".csv", encoding="UTF8")

# df.rename(columns = {'country_code':'country_id'}, inplace = True)
df = df.drop(['country_name'], axis=1, errors='ignore')
df = transformToLongFormat(df, ['country_code', 'year'], 'indicator_name', 'indicator_value')
df = pd.merge(df, pivot_df, left_on = "country_code", right_on = "FIPS")
df = df.drop(['FIPS', 'country_code'], axis=1)
df = pd.merge(df, indicator_ids_df, left_on = "indicator_name", right_on = "indicator_name")
df = df.drop(['indicator_name'], axis=1)

df.rename(columns = {'ISO_Code':'country_id'}, inplace = True)
df = df[['country_id', 'year', 'indicator_id', 'indicator_value']]
# print(df['country_id'].unique().size)
print(df)
df.to_csv(formatCleanDataSetFileName(fileName), index=False)
print(df["country_id"].nunique())




