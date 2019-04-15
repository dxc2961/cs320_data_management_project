
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
 * Class to create/populate the "order" table
 */
public class OrderTable {

    public static void createOrderTable(Connection conn){
        try {
            String str = "CREATE TABLE IF NOT EXISTS orders(ORDER_ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "PAYMENT_ID INT,EMAIL VARCHAR(50),ORDER_DATE DATE NOT NULL,DELIVERY_DATE DATE NOT NULL," +
                    "DELIVERY_ADDRESS_ID INT NOT NULL, RETURN_ADDRESS_ID INT NOT NULL);";
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void populateOrderTableCSV(Connection conn, String filename) throws SQLException{
        ArrayList<Order> orderList= new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            reader.readLine(); //read first line of csv file with column names, don't need to do anything with this
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");

                orderList.add(new Order(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2], split[3],
                        split[4], Integer.parseInt(split[5]), Integer.parseInt(split[6])));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String insertSQL = createOrderInsertSQL(orderList);
        Statement stmt = conn.createStatement();
        stmt.execute(insertSQL);
    }

    private static String createOrderInsertSQL(ArrayList<Order> list){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO orders VALUES");

        for(int i = 0; i < list.size(); ++i) {
            Order order= (Order) list.get(i);
            String order_date = order.getOrder_date();
            String delivery_date = order.getDelivery_date();
            builder.append(String.format("(%d,%d,\'%s\',DATE \'%s\',DATE \'%s\'," +
                            "%d,%d)",
                    new Object[]{order.getOrder_id(), order.getPayment_id(),order.getEmail(),order_date, delivery_date,
                    order.getDelivery_address_id(), order.getReturn_address_id()}));
            if(i != list.size() - 1) {
                builder.append(",");
            } else {
                builder.append(";");
            }
        }

        return builder.toString();
    }

    /**
     * Drop any instance of the credit_card table, if it is in the database
     * @param conn Connection to run the statement on
     * @throws SQLException
     */
    public static void removeOrderTable(Connection conn) throws SQLException{
        String removeSQL = "DROP TABLE IF EXISTS orders";
        Statement stmt = conn.createStatement();
        stmt.execute(removeSQL);
    }
}

