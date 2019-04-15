package Views;

import java.sql.*;

public class AnalystView extends View {

   // package queries
   private final String package_types_total =
      "SELECT PACKAGE_TYPE, SUM(PACKAGE_TYPE) FROM package GROUPBY PACKAGE_TYPE";
   private final String delivery_speeds_total =
      "SELECT DELIVERY_SPEED, SUM(DELIVERY_SPEED) FROM package GROUPBY DELIVERY_SPEED";

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
         System.out.println();
         System.out.println("Press p to view package functions.");
         System.out.println("Press c to view orders functions.");
         System.out.println("Press a to view address functions.");
         System.out.println("Press y to view payment functions.");
         System.out.println("Press q to quit");
         char action = in.next().charAt(0);
         in.nextLine();

         switch (action) {
            case 'p':
               this.viewPackFunc();
               break;
            case 'c':
               this.viewOrdersFunc();
               break;
            case 'a':
               this.viewAddressesFunc();
               break;
            case 'y':
               this.viewPaymentsFunc();
               break;
            case 'q':
               this.quit();
               break;
            default:
               System.out.println("Invalid Command");
               break;
         }
      }
   }

   public void printType(String str_name, String num_name, ResultSet results){
      try {
         System.out.printf("%10s: %s\n", str_name, num_name);
         while(results.next()){
            System.out.printf("%10s: %5d\n",
               results.getString(0),
               Integer.parseInt(results.getString(1)));
         }
      } catch(SQLException s){
         s.printStackTrace();
      }
   }

   public void sumPackTypes(){
      try {
         ResultSet results = this.runQuery(package_types_total);
         this.printType("Package Type", "Type Count", results);
      } catch (SQLException s){
         s.printStackTrace();
      }
   }

   public void sumSpeedTypes(){
      try{
         ResultSet results = this.runQuery(delivery_speeds_total);
         this.printType("Delivery Speed", "Speed Count", results);
      }catch(SQLException s){
         s.printStackTrace();
      }
   }

   public void viewPackFunc(){
      boolean go_back = false;
      char response;

      while(!go_back){
         System.out.println();
         System.out.println("Press p to view counts of package types.");
         System.out.println("Press s to view counts of delivery speed types.");
         response = in.next().charAt(0);
         in.nextLine();

         switch (response){
            case 'p':
               sumPackTypes();
               break;
            case 's':
               sumSpeedTypes();
               break;
               default:
                  System.out.println("Not valid command, try again!");
                  break;
         }

         System.out.println("Go back?(y/n)");
         response = in.next().charAt(0);
         in.nextLine();

         if (response == 'y'){
            go_back = true;
         }
      }
   }

   public void viewOrdersFunc(){

   }

   public void viewAddressesFunc(){
      boolean go_back = false;
      char response;

      while(!go_back){
         System.out.println();
         System.out.println("Press c to view counts of addresses by country code.");
         System.out.println("Press s to view counts of addresses by US state.");
         System.out.println("Press z to view counts of addresses by zip code.");
         System.out.println("Press y to view counts of addresses by city name.");
         response = in.next().charAt(0);
         in.nextLine();

         switch (response){
            case 'c':
               //sumPackTypes();
               break;
            case 's':
               //sumSpeedTypes();
               break;
            case 'z':
               break;
            case 'y':
               break;
            default:
               System.out.println("Not valid command, try again!");
               break;
         }

         System.out.println("Go back?(y/n)");
         response = in.next().charAt(0);
         in.nextLine();

         if (response == 'y'){
            go_back = true;
         }
      }

   }

   public void viewPaymentsFunc(){

   }

}
