package PackageDatabase;

public class PaymentMethod {

   private int payment_ID;
   private String email;

   public PaymentMethod(int payment_ID, String email){
      this.payment_ID = payment_ID;
      this.email = email;
   }

   public int getID(){
      return payment_ID;
   }

   public String getEmail(){
      return email;
   }

}
