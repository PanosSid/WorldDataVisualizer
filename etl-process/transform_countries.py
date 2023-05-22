import pandas as pd

def addValue(df, pivotColumn, pivotValue, column, value):
    row_index = df.loc[df[pivotColumn] == pivotValue].index[0]
    df.at[row_index, column] = value   

def removeRow(df, pivotColumn, pivotValue):
    return df.loc[df[pivotColumn] != pivotValue]

def changeValue(df, pivotColumn, pivotValue, column, newValue):
    df.loc[df[pivotColumn] == pivotValue, column] = newValue

# def cleanCountries(cleandFilesPath):
#     print("Cleaning countries.csv")
#     df = pd.read_csv("extracted-data\countries.csv", encoding="ANSI")

#     df = df[["ISO_Code", "Official_Name"]]
#     addValue(df, "ISO_Code", 0, "Official_Name", "Republic of Kosovo")
#     addValue(df, "ISO_Code", 530, "Official_Name", "Netherlands Antilles")

#     df = removeRow(df, "ISO_Code", 891)  # "Serbia and Montenegro"
#     changeValue(df, "ISO_Code", 344, "Official_Name", "China Hong Kong Special Administrative Region")
#     changeValue(df, "ISO_Code", 446, "Official_Name", "China Macao Special Administrative Region")
#     changeValue(df, "ISO_Code", 535, "Official_Name", "Bonaire Saint Eustatius and Saba")
#     changeValue(df, "ISO_Code", 531, "Official_Name", "Curacao")
#     changeValue(df, "Official_Name", "Cτte d\'Ivoire", "Official_Name", "Cote d'Ivoire")

#     df.rename(columns = {'ISO_Code':'country_id'}, inplace = True)
#     df.rename(columns = {'Official_Name':'country_name'}, inplace = True)

#     df.to_csv(cleandFilesPath+'\cleaned_countries.csv', index=False)
#     print("--- Clean Completed ---")

def cleanCountriesPart1(cleandFilesPath):
    print("Cleaning countries.csv part1")
    df = pd.read_csv("extracted-data\countries.csv", encoding="ANSI")

    df = df[["ISO_Code", "Official_Name", "FIPS"]]
    addValue(df, "ISO_Code", 0, "Official_Name", "Republic of Kosovo")
    addValue(df, "ISO_Code", 530, "Official_Name", "Netherlands Antilles")

    df = removeRow(df, "ISO_Code", 891)  # "Serbia and Montenegro"
    changeValue(df, "ISO_Code", 344, "Official_Name", "China Hong Kong Special Administrative Region")
    changeValue(df, "ISO_Code", 446, "Official_Name", "China Macao Special Administrative Region")
    changeValue(df, "ISO_Code", 535, "Official_Name", "Bonaire Saint Eustatius and Saba")
    changeValue(df, "ISO_Code", 531, "Official_Name", "Curacao")
    changeValue(df, "Official_Name", "Cτte d\'Ivoire", "Official_Name", "Cote d'Ivoire")

    df.to_csv(cleandFilesPath+'\cleaned_countries.csv', index=False)
    print("--- Clean Completed ---")



def cleanCountriesPart2(cleandFilesPath):
    print("Cleaning countries.csv part2")
    df = pd.read_csv(cleandFilesPath+"\cleaned_countries.csv")
    df = df[["ISO_Code", "Official_Name"]]
    df.rename(columns = {'ISO_Code':'country_id'}, inplace = True)
    df.rename(columns = {'Official_Name':'country_name'}, inplace = True)

    df.to_csv(cleandFilesPath+'\cleaned_countries.csv', index=False)
    print("--- Clean Completed ---")



