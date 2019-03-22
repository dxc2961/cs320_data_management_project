package PackageDatabase;

public class Check extends PaymentMethod {

   int routing_number;
   int account_number;
   int check_number;
   // idk if we need, just assume proper amount?
   //int amount;

   public Check(int payment_ID, String email,
                int routing_number, int account_number, int check_number) {
      super(payment_ID, email);
      this.routing_number = routing_number;
      this.account_number = account_number;
      this.check_number = check_number;
   }

   /**
    * GETTERS
    */

   //IDK IF NEED THIS
   public boolean isValid(){
      // some logic like check if check, routing, etc. are valid
      return true;
   }

   public int getAccount_number() {
      return account_number;
   }

   public int getRouting_number() {
      return routing_number;
   }

   public int getCheck_number() {
      return check_number;
   }

   /**
    * SETTERS
    */
}
