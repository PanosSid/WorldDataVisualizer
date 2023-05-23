import mysql.connector

# MySQL database configuration
config = {
    'user': 'root',
    'password': 'root',
    'host': 'localhost',
    # 'database': 'world_data_visualizer'
}

files_to_execute = ["ddl.sql", "dml.sql"]

# # Path to the DDL script file
# ddl_script_path = 'path/to/ddl_script.sql'

def runDDLandDMLscripts():
    for scirpt in files_to_execute:
      # Read the contents of the DDL script
      with open(scirpt, 'r') as file:
          script_contents = file.read()

      # Establish a connection to the MySQL database
      conn = mysql.connector.connect(**config)

      # Create a cursor to execute SQL statements
      cursor = conn.cursor()

      try:
          cursor.execute(script_contents)
          print(scirpt+" script executed successfully.")
      except mysql.connector.Error as error:
          print(f"Error executing"+scirpt+" script: {error}")
      finally:
          # Close the cursor and connection
          cursor.close()
          conn.close()


runDDLandDMLscripts()