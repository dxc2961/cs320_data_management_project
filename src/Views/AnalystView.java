package Views;

import java.sql.*;

public class AnalystView extends View {

   // package queries
   private final String package_types_total =
      "SELECT PACKAGE_TYPE, COUNT(PACKAGE_TYPE) FROM package GROUP BY PACKAGE_TYPE;";
   private final String delivery_speeds_total =
      "SELECT DELIVERY_SPEED, COUNT(DELIVERY_SPEED) FROM package GROUP BY DELIVERY_SPEED";

   // orders queries
   // ??? not sure yet

   // address queries
   // by location counts


   // payments queries




   public AnalystView(){super();}

   @Override
   public void assist() {
      System.out.println("Hello " + this.username + "! What would you like to do today?");

      while(this.isActive()) {
         System.out.println("\nAnalyst Functions");
         System.out.println("-----------------");
         System.out.println("Press p to view package functions.");
         System.out.println("Press o to view orders functions.");
         System.out.println("Press a to view address functions.");
         System.out.println("Press y to view payment method functions.");
         System.out.println("Press q to quit");
         char action = in.next().charAt(0);
         in.nextLine();

         switch (action) {
            case 'p':
               this.viewPackFunc();
               break;
            case 'o':
               this.viewOrdersFunc();
               break;
            case 'a':
               this.viewAddressesFunc();
               break;
            case 'y':
               this.viewPaymentsFunc();
               break;
            case 'c':
               this.viewCustomerFunc();
            case 'q':
               this.quit();
               break;
            default:
               System.out.println("Invalid Command");
               break;
         }
      }
   }



   private void viewPackFunc(){
      boolean go_back = false;
      char response;


      while(!go_back){
         System.out.println("\nPackage functions");
         System.out.println("-----------------");
         System.out.println("Enter p to view counts of package types.");
         System.out.println("Enter s to view counts of delivery speed types.");
         System.out.println("Enter q to go back to Analyst functions.");
         response = in.next().charAt(0);
         in.nextLine();

         switch (response){
            case 'p':
               sumPackTypes();
               break;
            case 's':
               sumSpeedTypes();
               break;
            case 'q':
               go_back = true;
               default:
                  System.out.println("Not valid command, try again!");
                  break;
         }
      }
   }

   private void printType(String str_name, String num_name, ResultSet results){
      try {
         System.out.printf("%10s: %s\n", str_name, num_name);
         while(results.next()){
            System.out.printf("%10s: %5d\n",
            results.getString(1),
            Integer.parseInt(results.getString(2)));
         }
      } catch(SQLException s){
         s.printStackTrace();
      }
   }

   private void sumPackTypes(){
      try {
         ResultSet results = this.runQuery(package_types_total);
         this.printType("Package Type", "Type Count", results);
      } catch (SQLException s){
         s.printStackTrace();
      }
   }

   private void sumSpeedTypes(){
      try{
         ResultSet results = this.runQuery(delivery_speeds_total);
         this.printType("Delivery Speed", "Speed Count", results);
      }catch(SQLException s){
         s.printStackTrace();
      }
   }

   private void viewOrdersFunc(){

   }

   private void viewAddressesFunc(){
      boolean go_back = false;
      char response;



      while(!go_back){
         System.out.println("\nAddress functions");
         System.out.println("-----------------");
         System.out.println("Enter c to view counts of addresses by country code.");
         System.out.println("Enter s to view counts of addresses by US state.");
         System.out.println("Enter z to view counts of addresses by zip code.");
         System.out.println("Enter y to view counts of addresses by city name.");
         System.out.println("Enter q to go back to Analyst functions.");
         response = in.next().charAt(0);
         in.nextLine();

         if(response=='q'){
            go_back = true;
         }else{
            addressCount(response);
         }
      }

   }

   private void addressCount(char c){

      String location;


      switch(c){
         case 'c':
            location = "COUNTRY_CODE";
            break;
         case 's':
            location = "STATE";
            break;
         case 'z':
            location = "ZIP_CODE";
            break;
         case 'y':
            location = "CITY";
            break;
         default:
            System.out.println("Invalid input!");
            return;

      }
      String query = "SELECT "+location+", COUNT("+location+") FROM address GROUP BY "+location;
      try {
         ResultSet results = this.runQuery(query);
         printAddressCount(location, results);
      }catch(SQLException e){
         e.printStackTrace();
      }

   }

   private void printAddressCount(String loc, ResultSet results){
      try{
         System.out.printf("%10s: %s\n", loc, loc+" count");
         while (results.next()){
            System.out.printf("%10s: %5d\n",
               results.getString(1),
               Integer.parseInt(results.getString(2)));
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
   }

   private void viewPaymentsFunc(){
      boolean go_back = false;
      char response;



      while(!go_back){
         System.out.println("\nPayment method functions");
         System.out.println("-----------------");
         System.out.println("Enter p to view payment methods by state.");
         System.out.println("Enter o to view payments method by frequency of use.");
         System.out.println("Enter d to view payment methods by delivery speed.");
         System.out.println("Enter s to view payment methods by package type.");
         System.out.println("Enter q to go back to Analyst functions.");
         response = in.next().charAt(0);
         in.nextLine();

         if(response=='q'){
            go_back = true;
         }else{
            paymentCount(response);
         }
      }
   }

   private void paymentCount(char response){
      String query = "";
      String methods[] = {"credit_card", "gift_card", "checks"};
      switch (response){
         case 'p':
            break;
         case 'o':
            paymentByState(methods);
            break;
         case 'd':
            break;
         case 's':
            break;
         default:
            break;
      }
      /*
      try {
         ResultSet results = this.runQuery(query);
         printPaymentCount(results);
      }catch(SQLException e){
         e.printStackTrace();
      }*/

   }
   ////
   // SORT IN ASC/DEC ORDER
   ////
   private void paymentByState(String methods[]){
      StringBuilder sb = new StringBuilder();
      for(int m = 0; m < methods.length; m++){
         sb.append("(");
         sb.append("SELECT count(");
         sb.append(methods[m]);
         sb.append(".payment_id) from orders, ");
         sb.append(methods[m]);
         sb.append(" WHERE orders.payment_id = ");
         sb.append(methods[m]);
         sb.append(".payment_id");
         sb.append(")");
         if( m < methods.length - 1){
            sb.append("\nUNION\n");
         }
      }
      String query = sb.toString();
      try {
         ResultSet results = this.runQuery(query);
         printPaymentCount(results, methods);
      }catch(SQLException e){
         e.printStackTrace();
      }

   }

   private void printPaymentCount(ResultSet results, String methods[]){
      try{
         int m = 0;
         System.out.printf("%s: %s\n", "Payment method","Payment order count");
         while (results.next()){
            System.out.printf("%10s: %5d\n",
            methods[m],
            Integer.parseInt(results.getString(1)));
            m+=1;
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
   }

   private void viewCustomerFunc(){
      boolean go_back = false;
      char response;



      while(!go_back){
         System.out.println("\nAddress functions");
         System.out.println("-----------------");
         System.out.println("Enter c to view counts of addresses by country code.");
         System.out.println("Enter s to view counts of addresses by US state.");
         System.out.println("Enter z to view counts of addresses by zip code.");
         System.out.println("Enter y to view counts of addresses by city name.");
         System.out.println("Enter q to go back to Analyst functions.");
         response = in.next().charAt(0);
         in.nextLine();

         if(response=='q'){
            go_back = true;
         }else{
            addressCount(response);
         }
      }
   }

}
