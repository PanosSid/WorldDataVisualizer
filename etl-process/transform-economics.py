import pandas as pd

# drops all rows that contain at least one null value
def dropNullValues(df):
    return df.drop(df[df.eq('..').any(axis=1)].index)

def dropUnknwonCountries(df):
    mask = df["Country"].isin(KNOWN_COUNTRIES)
    return df.drop(df[~mask].index)

def aggregatedPer5Years(df):
    columns = df.columns.tolist()
    numericColumns = columns[1:]   #skip first colomn that is string
    sublistIndex = 0
    for i in range(len(numericColumns)-1):  
        if (numericColumns[i+1] - numericColumns[i] == 1):
            sublistIndex = i+1; 
            break
    consecutiveColumns = numericColumns[sublistIndex:]
    columnsGroupsPer5 = [consecutiveColumns[i:i+5] for i in range(0, len(consecutiveColumns), 5)]
    # print(columns)
    # print(columnsGroupsPer5)

    for group in columnsGroupsPer5:
        df[group[-1]] = df[group].mean(axis=1).round(1)
        for c in group[:-1]:
            df = df.drop([c], axis=1)
    return df

# transform df columns form this [Country 1990, 1995, 2000 ... 2018]
# to this [country_code, year, metric_name]
def transformToLongFormat(df, metric_name):
    df = pd.melt(df, id_vars=["Country"], value_vars = getDfColumnsToBecomeRows(df, ["country_code","year"]),
                   var_name='year', value_name=sheet_name)                  
    return df

def getDfColumnsToBecomeRows(df, idColumns):
    list = []
    for column in df.columns:
        if column not in idColumns:
            list.append(column)
    return list

def formatCleanDataSetFileName(sheet_name):
    return "transformed-data\clean_"+sheet_name.lower().replace(' ', '_')+".csv"



pivot_df = pd.read_csv("extracted-data\countries.csv", encoding="ANSI")
pivot_df = pivot_df[["Official_Name", "ISO_Code"]]
KNOWN_COUNTRIES = pivot_df["Official_Name"].tolist()

xls_file = pd.ExcelFile("extracted-data\Income by Country.xlsx")
sheet_names = xls_file.sheet_names

for sheet_name in sheet_names:
    print("Cleaning: "+sheet_name+"...")
    df = pd.read_excel(xls_file, sheet_name)
    df = dropNullValues(df)
    df = df.drop(['Info'], axis=1, errors='ignore')
    df = dropUnknwonCountries(df)
    df = aggregatedPer5Years(df)
    df = transformToLongFormat(df, sheet_name)
    df = pd.merge(df, pivot_df, left_on = "Country", right_on = "Official_Name")
    df = df[["ISO_Code", "Country", "year", sheet_name]]
    df.to_csv(formatCleanDataSetFileName(sheet_name), index=False)











