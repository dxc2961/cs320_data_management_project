package PackageDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerTable {
   /**
    * Method to create the address table in the database
    * @param conn
    */
   public static void createCustomerTable(Connection conn){
      try {
         String str = "CREATE TABLE IF NOT EXISTS customer(EMAIL VARCHAR(50) PRIMARY KEY," +
         "DISPLAY_NAME VARCHAR(20),PASSWORD VARCHAR(30),HOME_ADDRESS_ID INT," +
         "PHONE_NUM CHAR(10));";
         Statement stmt = conn.createStatement();
         stmt.execute(str);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * Method to populate the Customer table with data from a CSV file
    * @param conn the H2 database connection object
    * @param filename CSV filename
    * @throws SQLException
    */
   public static void populateCustomerTableCSV(Connection conn, String filename)throws SQLException{
      ArrayList<Customer> customerList;
      customerList = new ArrayList<Customer>();

      try {
         BufferedReader reader = new BufferedReader(new FileReader(filename));

         String line;
         while((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            customerList.add(
               new Customer(split[0],split[1],split[3],Integer.parseInt(split[2]),split[4],
                  new Date(split[5], split[6], split[7])));
         }

         reader.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

      String insertSQL = createCustomerInsertSQL(customerList);
      Statement stmt = conn.createStatement();
      stmt.execute(insertSQL);
   }

   public static String createCustomerInsertSQL(ArrayList<Customer> list){
      StringBuilder builder = new StringBuilder();
      builder.append("INSERT INTO customer VALUES");

      for(int i = 0; i < list.size(); ++i) {
         Customer c = (Customer)list.get(i);
         builder.append(String.format("(%s,\'%s\',\'%s\',\'%d\',\'%s\')",
         new Object[]{c.getEmail(), c.getDisplay_name(), c.getPassword(), c.getHome_address_id(),
         c.getPhone_number()}));
         if(i != list.size() - 1) {
            builder.append(",");
         } else {
            builder.append(";");
         }
      }

      return builder.toString();
   }

}
