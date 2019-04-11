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
 * Class to create/populate the "payment_method" table
 */
public class PaymentMethodTable {

    public static void createPaymentMethodTable(Connection conn){
        try {
            String str = "CREATE TABLE IF NOT EXISTS payment_method(PAYMENT_ID INT PRIMARY KEY, CUST_EMAIL VARCHAR(50));";
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populatePaymentMethodTableCSV(Connection conn, String filename) throws SQLException{
        ArrayList<PaymentMethod> payment_methodList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            reader.readLine(); //read first line of csv file with column names, don't need to do anything with this
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                payment_methodList.add(new PaymentMethod(Integer.parseInt(split[0]), split[1]));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String insertSQL = createPaymentMethodInsertSQL(payment_methodList);
        Statement stmt = conn.createStatement();
        stmt.execute(insertSQL);
    }

    private static String createPaymentMethodInsertSQL(ArrayList<PaymentMethod> list){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO payment_method VALUES");

        for(int i = 0; i < list.size(); ++i) {
            PaymentMethod method = (PaymentMethod) list.get(i);
            builder.append(String.format("(%d,\'%s\')",
                    new Object[]{method.getID(), method.getEmail()}));
            if(i != list.size() - 1) {
                builder.append(",");
            } else {
                builder.append(";");
            }
        }

        return builder.toString();
    }

    /**
     * Drop any instance of the payment_method table, if it is in the database
     * @param conn Connection to run the statement on
     * @throws SQLException
     */
    public static void removePaymentMethodTable(Connection conn) throws SQLException{
        String removeSQL = "DROP TABLE IF EXISTS payment_method";
        Statement stmt = conn.createStatement();
        stmt.execute(removeSQL);
    }
}



