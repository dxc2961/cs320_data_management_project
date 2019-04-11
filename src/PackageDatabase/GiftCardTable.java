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
public class GiftCardTable {

    public static void createGiftCardTable(Connection conn){
        try {
            String str = "CREATE TABLE IF NOT EXISTS gift_card(GIFT_CARD_ID CHAR(16) PRIMARY KEY," +
                    "PAYMENT_ID INT,EXPIRATION_DATE DATE,BALANCE INT);";
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateGiftCardTableCSV(Connection conn, String filename) throws SQLException{
        ArrayList<GiftCard> gift_cardList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            reader.readLine(); //read first line of csv file with column names, don't need to do anything with this
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                gift_cardList.add(new GiftCard(split[0],Integer.parseInt(split[1]),split[2],Integer.parseInt(split[3])));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String insertSQL = createGiftCardInsertSQL(gift_cardList);
        Statement stmt = conn.createStatement();
        stmt.execute(insertSQL);
    }

    private static String createGiftCardInsertSQL(ArrayList<GiftCard> list){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO gift_card VALUES");

        for(int i = 0; i < list.size(); ++i) {
            GiftCard card = (GiftCard) list.get(i);
            builder.append(String.format("(\'%s\',%d,DATE \'%s\',%d)",
                    new Object[]{card.getGiftcard_id(),card.getPayment_id(),
                            card.getExpiration_date(), card.getBalance()}));
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
    public static void removeGiftCardTable(Connection conn) throws SQLException{
        String removeSQL = "DROP TABLE IF EXISTS gift_card";
        Statement stmt = conn.createStatement();
        stmt.execute(removeSQL);
    }
}


