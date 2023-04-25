import mysql.connector

db = mysql.connector.connect(
  host="localhost",
  user="root",
  password="root",
  database="tmp_mye030"
)

mycursor = db.cursor()

# mycursor.execute("SELECT * FROM domestic_credits")
# print(mycursor.fetchall())

# data_df = pd.read_csv("extracted-data\\birth_death_growth_rates.csv", encoding="UTF-8")


LOAD DATA LOCAL INFILE 'E:/node-projects/data-visualizer/db/etl/transformed_data/transformed_country_metadata.csv'
INTO TABLE countries
CHARACTER SET latin1
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(name, code, region, income_group, special_notes)