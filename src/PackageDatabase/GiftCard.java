package PackageDatabase;

public class GiftCard extends PaymentMethod {

   private int giftcard_number;
   private String expiration_date;
   private int balance;

   public GiftCard(int payment_ID, String email,
                   int giftcard_number, String expiration_date, int balance) {
      super(payment_ID, email);
      this.expiration_date = expiration_date;
      this.giftcard_number = giftcard_number;
      this.balance = balance;
   }

   /**
    * GETTERS
    */

   public String getExpiration_date() {
      return expiration_date;
   }

   public boolean isExpired(String current_date){
      return true;
   }

   public int getGiftcard_number() {
      return giftcard_number;
   }

   public int getBalance() {
      return balance;
   }

   public boolean canPurchase(int amount){
      return (getBalance() - amount) >= 0;
   }

   /**
    * SETTERS
    */

   // IDK IF BALANCE CHECK NEEDED IF WE ADD A CONSTRAINT

   /**
    *
    *
    * @param amount the amount that would be subtracted from balance
    * @return whether or not the purchase was successful
    */
   public boolean purchase_made(int amount){
      if(canPurchase(amount)){
         this.balance = this.balance - amount;
         return true;
      }else{
         return false;
      }
   }
}
