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
 * Class to create/populate the "credit_card" table
 */
public class CreditCardTable {

    public static void createCreditCardTable(Connection conn){
        try {
            String str = "CREATE TABLE IF NOT EXISTS credit_card(CARD_NUMBER CHAR(16) PRIMARY KEY," +
                    "PAYMENT_ID INT,OWNER_NAME VARCHAR(25),EXPIRATION_DATE VARCHAR(5),SECURITY_CODE INT);";
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateCreditCardTableCSV(Connection conn, String filename) throws SQLException{
        ArrayList<CreditCard> credit_cardList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            reader.readLine(); //read first line of csv file with column names, don't need to do anything with this
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                credit_cardList.add(new CreditCard(split[0],Integer.parseInt(split[1]),
                        split[2],split[3],Integer.parseInt(split[4])));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String insertSQL = createCheckInsertSQL(credit_cardList);
        Statement stmt = conn.createStatement();
        stmt.execute(insertSQL);
    }

    private static String createCheckInsertSQL(ArrayList<CreditCard> list){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO credit_card VALUES");

        for(int i = 0; i < list.size(); ++i) {
            CreditCard card = (CreditCard) list.get(i);
            builder.append(String.format("(\'%s\',%d,\'%s\',\'%s\',%d)",
                    new Object[]{card.getCard_number(),card.getPayment_ID(),
                            card.getOwner_name(), card.getExpiration_date(), card.getSecurity_code()}));
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
    public static void removeCreditCardTable(Connection conn) throws SQLException{
        String removeSQL = "DROP TABLE IF EXISTS credit_card";
        Statement stmt = conn.createStatement();
        stmt.execute(removeSQL);
    }
}

