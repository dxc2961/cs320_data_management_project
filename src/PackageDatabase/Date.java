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

   /**
    * Finds the time until the future date
    * @param future the date in the future to get the time to from this date
    * @return
    */
   public Date timeTil(Date future){
      int diffYears = future.getYear() - this.getYear();
      if(diffYears < 0){
         return new Date(0,0,0);
      }
      int diffMonths = (future.getMonth() - this.getMonth() + diffYears*12) % 12 ;
      int diffDays = future.getDay() - this.getDay();

      return new Date(0, diffMonths, diffYears);

   }

   public int getDay() {
      return day;
   }

   public int getMonth() {
      return month;
   }

   public int getYear() {
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
