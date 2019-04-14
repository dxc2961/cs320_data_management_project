package PackageDatabase;

public class PaymentMethod {

   private int payment_ID;
   private String email;
   private boolean active;

   public PaymentMethod(int payment_ID, String email, boolean active){
      this.payment_ID = payment_ID;
      this.email = email;
      this.active = active;
   }

   public int getID(){
      return payment_ID;
   }

   public String getEmail(){
      return email;
   }

   public boolean isActive() { return active; }

}
