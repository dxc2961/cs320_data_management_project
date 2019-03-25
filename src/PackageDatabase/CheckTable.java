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
 * Method to create/populate the "check" table
 */
public class CheckTable {

    public static void createCheckTable(Connection conn){
        try {
            String str = "CREATE TABLE IF NOT EXISTS check(ROUTING_NUM CHAR(9) PRIMARY KEY," +
                    "ACCOUNT_NUM VARCHAR(15) PRIMARY KEY,CHECK_NUM VARCHAR(8) PRIMARY KEY,PAYMENT_ID INT);";
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateCheckTableCSV(Connection conn, String filename) throws SQLException{
        ArrayList<Check> checkList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            reader.readLine(); //read first line of csv file with column names, don't need to do anything with this
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                checkList.add(new Check(Integer.parseInt(split[0]),Integer.parseInt(split[1]),
                        Integer.parseInt(split[2]),Integer.parseInt(split[3])));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String insertSQL = createCheckInsertSQL(checkList);
        Statement stmt = conn.createStatement();
        stmt.execute(insertSQL);
    }

    private static String createCheckInsertSQL(ArrayList<Check> list){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO check VALUES");

        for(int i = 0; i < list.size(); ++i) {
            Check check = (Check)list.get(i);
            builder.append(String.format("(%d,%d,%d,%d)",
                    new Object[]{Integer.valueOf(check.getRouting_number()), Integer.valueOf(check.getAccount_number()),
                    Integer.valueOf(check.getCheck_number()), Integer.valueOf(check.getPayment_id())}));
            if(i != list.size() - 1) {
                builder.append(",");
            } else {
                builder.append(";");
            }
        }

        return builder.toString();
    }
}
