package PackageDatabase;

public enum Delivery_speed {
   NEXT_DAY(1.5),
   NEXT_3DAY(1.3),
   NEXT_WEEK(1.1),
   NEXT_MONTH(1);

   private double multi;
   private int distance = 0; // miles
   Delivery_speed(double multiplier){
      multi = multiplier;
   }

   public double getMult(){
      return multi * (distance / 100 * 1.1);
   }
}
