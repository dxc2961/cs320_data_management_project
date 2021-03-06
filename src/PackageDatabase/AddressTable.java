package PackageDatabase;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class for Address table
 */
public class AddressTable {

    /**
     * Method to create the address table in the database
     * @param conn
     */
    public static void createAddressTable(Connection conn){
        try {
            String str = "CREATE TABLE IF NOT EXISTS address(ADDRESS_ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "HOUSE_NUM VARCHAR(10) NOT NULL,STREET_NAME VARCHAR(30) NOT NULL,CITY VARCHAR(20) NOT NULL," +
                    "STATE VARCHAR(30) NOT NULL,COUNTRY_CODE CHAR(3) NOT NULL,ZIP_CODE CHAR(5) NOT NULL,CUSTOMER_EMAIL VARCHAR(50));";
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to populate the Address table with data from a CSV file
     * @param conn the H2 database connection object
     * @param filename CSV filename
     * @throws SQLException
     */
    public static void populateAddressTableCSV(Connection conn, String filename)throws SQLException{
        ArrayList<Address> addressList = new ArrayList<Address>();


        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            reader.readLine(); //read first line of csv file with column names, don't need to do anything with this
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                addressList.add(new Address(Integer.parseInt(split[0]),split[7],split[1],split[2],split[3],split[4],
                split[6],split[5]));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String insertSQL = createAddressInsertSQL(addressList);
        Statement stmt = conn.createStatement();
        stmt.execute(insertSQL);
    }

    /**
     * Drop any instance of the address table, if it is in the database
     * @param conn Connection to run the statement on
     * @throws SQLException
     */
    public static void removeAddressTable(Connection conn) throws SQLException{
        String removeSQL = "DROP TABLE IF EXISTS address";
        Statement stmt = conn.createStatement();
        stmt.execute(removeSQL);
    }


    public static String createAddressInsertSQL(ArrayList<Address> list){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO address VALUES ");

        for(int i = 0; i < list.size(); ++i) {
            Address address = (Address)list.get(i);
            builder.append(String.format("(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                address.getAddress_id(), address.getHouse_num(), address.getStreet(), address.getCity(),
                address.getState(), address.getCountry_code(), address.getZip_code(), address.getCustomer_email()));

            if(i != list.size() - 1) {
                builder.append(",");
            } else {
                builder.append(";");
            }
        }
        return builder.toString();
    }

}
