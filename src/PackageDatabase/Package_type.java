package PackageDatabase;

public enum Package_type {
   ENVELOPE(1.1),
   BAG(1.2),
   TUBE(1.3),
   BOX(1.4);

   private double mult;
   Package_type(double mult){
      this.mult = mult;
   }

   public double getMult(){
      return mult;
   }
}
