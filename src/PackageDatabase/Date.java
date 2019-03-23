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

   @Override
   public String toString(){
      return Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
   }

   /**
    * How far away is current date from Date d
    * @param d the future date
    * @return String of how far away the two dates are
    */
   public String timeLength(Date d){
      Date diff = new Date(d.getDay() -  this.getDay(),
                           d.getMonth() * d.getYear() - this.getMonth() * this.getYear(),
                           d.getYear() - this.getYear());
      StringBuilder sb = new StringBuilder();
      sb.append(diff.getDay());
      sb.append(" days");
      if(diff.getMonth() != 0){
         sb.append(" and ");
         sb.append(diff.getMonth() + diff.getYear()*12);
         sb.append(" months");
      }

      return sb.toString();
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


}
