package PackageDatabase;


import java.sql.*;
import java.util.ArrayList;

/**
 * DOCUMENTATION
 *
 * @author Dylan C
 */
public class PackageMain {

   //The connection to the database
   private Connection conn;

   /**
    * Create a database connection with the given params
    *
    * @param location: path of where to place the database
    * @param user:     user name for the owner of the database
    * @param password: password of the database owner
    */
   public void createConnection(String location,
                                String user,
                                String password) {
      try {

         //This needs to be on the front of your location
         String url = "jdbc:h2:" + location;

         //This tells it to use the h2 driver
         Class.forName("org.h2.Driver");

         //creates the connection
         conn = DriverManager.getConnection(url,
         user,
         password);
      } catch (SQLException | ClassNotFoundException e) {
         //You should handle this better
         e.printStackTrace();
      }
   }

   /**
    * just returns the connection
    *
    * @return: returns class level connection
    */
   public Connection getConnection() {
      return conn;
   }

   /**
    * When your database program exits
    * you should close the connection
    */
   public void closeConnection() {
      try {
         conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * Method used to create views used throughout the application
    */
   public void createViews(){
      String paymentViewCreateSQL = "CREATE VIEW payment_details AS " +
              "SELECT " +
              "payment_method.payment_id, payment_method.cust_email, payment_method.active, " +
              "credit_card.card_number, credit_card.owner_name, credit_card.expiration_date as credit_expiration_date, credit_card.security_code, " +
              "checks.routing_num, checks.account_num, checks.check_num, " +
              "gift_card.gift_card_id, gift_card.expiration_date as gift_expiration_date, gift_card.balance " +
              "FROM payment_method " +
              "LEFT OUTER JOIN credit_card ON payment_method.payment_id = credit_card.payment_id " +
              "LEFT OUTER JOIN checks ON payment_method.payment_id = checks.payment_id " +
              "LEFT OUTER JOIN gift_card ON payment_method.payment_id = gift_card.payment_id ";
      try{
         Statement stmt = this.getConnection().createStatement();
         stmt.execute(paymentViewCreateSQL);
      } catch (SQLException s){
         System.err.println("Could not create payment view");
         s.printStackTrace();
      }
   }

   /**
    * Starts and runs the database
    *
    * @param args: not used but you can use them
    */
   public static void main(String[] args) {

      PackageMain packageMain = new PackageMain();

      //Hard drive location of the database
      String location = "./packageMain/packageMain";
      String user = "me";
      String password = "password";

      //Create the database connections, basically makes the database
      packageMain.createConnection(location, user, password);

      try{

         //remove existing tables
         AddressTable.removeAddressTable(packageMain.getConnection());
         CheckTable.removeCheckTable(packageMain.getConnection());
         CustomerTable.removeCustomerTable(packageMain.getConnection());
         CreditCardTable.removeCreditCardTable(packageMain.getConnection());
         OrderTable.removeOrderTable(packageMain.getConnection());
         GiftCardTable.removeGiftCardTable(packageMain.getConnection());
         PaymentMethodTable.removePaymentMethodTable(packageMain.getConnection());

         //create tables
         AddressTable.createAddressTable(packageMain.getConnection());
         CheckTable.createCheckTable(packageMain.getConnection());
         CustomerTable.createCustomerTable(packageMain.getConnection());
         CreditCardTable.createCreditCardTable(packageMain.getConnection());
         OrderTable.createOrderTable(packageMain.getConnection());
         GiftCardTable.createGiftCardTable(packageMain.getConnection());
         PaymentMethodTable.createPaymentMethodTable(packageMain.getConnection());

         //populate tables
         AddressTable.populateAddressTableCSV(packageMain.getConnection(), "data/delivery_address.csv");
         CheckTable.populateCheckTableCSV(packageMain.getConnection(), "data/check.csv");
         CustomerTable.populateCustomerTableCSV(packageMain.getConnection(), "data/customer.csv");
         CreditCardTable.populateCreditCardTableCSV(packageMain.getConnection(), "data/creditcard.csv");
         OrderTable.populateOrderTableCSV(packageMain.getConnection(), "data/order.csv");
         GiftCardTable.populateGiftCardTableCSV(packageMain.getConnection(), "data/giftcard.csv");
         PaymentMethodTable.populatePaymentMethodTableCSV(packageMain.getConnection(), "data/payment.csv");

         packageMain.createViews();

      }catch(SQLException e){
         e.printStackTrace();
      }

      packageMain.closeConnection();

   }
}

