import pandas as pd

# df = pd.read_csv("extracted-data\countries.csv", encoding="ANSI")
df = pd.read_csv("tmp_files\\tmp_countries.csv")

df = df[["ISO_Code", "Official_Name"]]
df.rename(columns = {'ISO_Code':'country_id'}, inplace = True)
df.rename(columns = {'Official_Name':'country_name'}, inplace = True)

df.to_csv('cleaned_countries.csv', index=False)



