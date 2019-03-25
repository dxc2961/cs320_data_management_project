package PackageDatabase;

public class Check {

   private int payment_id;
   private int routing_number;
   private int account_number;
   private int check_number;
   // idk if we need, just assume proper amount?
   //int amount;

   public Check(int routing_number, int account_number, int check_number, int payment_id) {
      this.payment_id = payment_id;
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

   public int getPayment_id(){
      return payment_id;
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
