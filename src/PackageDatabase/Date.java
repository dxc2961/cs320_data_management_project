package PackageDatabase;

public class Date {

   private int day;
   private int month;
   private int year;

   public Date(int day, int month, int year){
      this.day = day;
      this.month = month;
      this.year = year;
   }

   public Date(String day, String month, String year){
      /* to do */
   }

   /**
    * GETTERS
    */

   public int getDay(){
      return day;
   }

   public int getMonth(){
      return month;
   }

   public int getYear(){
      return year;
   }


   /**
    * SETTERS
    */

   public void setDay(int day){
      this.day = day;
   }

   public void setMonth(int month){
      this.month = month;
   }

   public void setYear(int year){
      this.year = year;
   }

}
