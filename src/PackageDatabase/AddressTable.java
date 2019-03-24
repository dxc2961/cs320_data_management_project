package PackageDatabase;

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
            String str = "CREATE TABLE IF NOT EXISTS address(ADDRESS_ID INT PRIMARY KEY," +
                    "HOUSE_NUM VARCHAR(10),STREET_NAME VARCHAR(30),CITY VARCHAR(20)," +
                    "STATE CHAR(2),COUNTRY_CODE CHAR(3),ZIP_CODE CHAR(5));";
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to populate the Address table with data from a CSV file
     * @param conn
     * @param filename CSV filename
     * @throws SQLException
     */
    public static void populateAddressTableCSV(Connection conn, String filename)throws SQLException{
        ArrayList addressList = new ArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                addressList.add(new Address(Integer.parseInt(split[0]),split[1],split[2],split[3],split[4],split[5],split[6],split[7]));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String insertSQL = createAddressInsertSQL(addressList);
        Statement stmt = conn.createStatement();
        stmt.execute(insertSQL);
    }

    public static String createAddressInsertSQL(ArrayList<Address> list){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO address VALUES");

        for(int i = 0; i < list.size(); ++i) {
            Address address = (Address)list.get(i);
            builder.append(String.format("(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                    new Object[]{Integer.valueOf(address.getAddress_id()), address.getCustomer_email(), address.getHouse_num(),
                            address.getStreet(), address.getCity(), address.getState(), address.getZip_code(), address.getCountry_code()}));
            if(i != list.size() - 1) {
                builder.append(",");
            } else {
                builder.append(";");
            }
        }

        return builder.toString();
    }

}
