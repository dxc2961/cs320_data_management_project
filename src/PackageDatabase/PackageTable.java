package PackageDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class to create/populate the "package" table
 */
public class PackageTable {

    public static void createPackageTable(Connection conn) {
        try {
            String str = "CREATE TABLE IF NOT EXISTS package(ORDER_ID INT, PACKAGE_ID PRIMARY KEY AUTO_INCREMENT" +
                    "PACKAGE_TYPE VARCHAR(10), WEIGHT DECIMAL(12, 4), DELIVERY_SPEED VARCHAR(10), " +
                    "CURRENT_STATUS VARCHAR(16), SIGN_REQUIRED_STATUS BOOLEAN, INSURANCE_STATUS BOOLEAN, HAZARD_STATUS BOOLEAN, " +
                    "FRAGILE_STATUS BOOLEAN, PERISHABLE_STATUS BOOLEAN, ITEM_DESCRIPTION VARCHAR(100), PROVIDED_VALUE DECIMAL(20,2));";
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void populatePackageTableCSV(Connection conn, String filename) throws SQLException {
        ArrayList<Package> packageList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            reader.readLine(); //read first line of csv file with column names, don't need to do anything with this
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");


                packageList.add(new Package(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2], Double.parseDouble(split[3]),
                        split[4], split[5], split[6].equals("yes"), split[7].equals("yes"), split[8].equals("yes"),
                        split[9].equals("yes"), split[10].equals("yes"), split[11], Double.parseDouble(split[12])));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String insertSQL = createPackageInsertSQL(packageList);
        Statement stmt = conn.createStatement();
        stmt.execute(insertSQL);
    }

    private static String createPackageInsertSQL(ArrayList<Package> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO package VALUES");

        for (int i = 0; i < list.size(); ++i) {
            Package pack = (Package) list.get(i);



            /*
            String order_date = order.getPackage_date();
            String delivery_date = order.getDelivery_date();
            builder.append(String.format("(%d,%d,\'%s\',DATE \'%s\',DATE \'%s\'," +
                            "%d,%d)",
                    new Object[]{order.getPackage_id(), order.getPayment_id(), order.getEmail(), order_date, delivery_date,
                            order.getDelivery_address_id(), order.getReturn_address_id()}));
            if (i != list.size() - 1) {
                builder.append(",");
            } else {
                builder.append(";");
            }*/
        }

        return builder.toString();
    }

    /**
     * Drop any instance of the credit_card table, if it is in the database
     *
     * @param conn Connection to run the statement on
     * @throws SQLException
     */
    public static void removePackageTable(Connection conn) throws SQLException {
        String removeSQL = "DROP TABLE IF EXISTS package";
        Statement stmt = conn.createStatement();
        stmt.execute(removeSQL);
    }

}