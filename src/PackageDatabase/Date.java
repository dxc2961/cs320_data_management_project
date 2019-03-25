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

   /**
    *
    * @param day
    * @param monthName
    */
   public Date(String day, String monthName, String year){
      this.day = Integer.parseInt(day);
      this.year = Integer.parseInt(year);

      switch (monthName){
         case "January":
            this.month = 1;
         case "February":
            this.month = 2;
         case "March":
            this.month = 3;
         case "April":
            this.month = 4;
         case "May":
            this.month = 5;
         case "June":
            this.month = 6;
         case "July":
            this.month = 7;
         case "August":
            this.month = 8;
         case "September":
            this.month = 9;
         case "October":
            this.month = 10;
         case "November":
            this.month = 11;
         case "December":
            this.month = 12;
      }

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
      if(diffDays < 0){
         diffDays = 0;
      }

      return new Date(diffDays, diffMonths, diffYears);

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
