package PackageDatabase;

public class GiftCard {

   private final int DEFAULT_BAL = 50;

   private int payment_id;
   private String giftcard_id;
   private String expiration_date;
   private int balance;


   public GiftCard(String giftcard_id,
                   int payment_ID, String expiration_date) {
      this.payment_id = payment_ID;
      this.expiration_date = expiration_date;
      this.giftcard_id = giftcard_id;
      this.balance = DEFAULT_BAL;
   }

   /**
    *
    * @param payment_ID
    * @param giftcard_id
    * @param expiration_date
    * @param balance
    */
   public GiftCard(String giftcard_id, int payment_ID,
                   String expiration_date, int balance) {
      this.payment_id = payment_ID;
      this.expiration_date = expiration_date;
      this.giftcard_id = giftcard_id;
      this.balance = balance;
   }

   /**
    * GETTERS
    */

   public String getExpiration_date() {
      return expiration_date;
   }

   public int getPayment_id() {
      return payment_id;
   }

   public boolean isExpired(String current_date){
      return true;
   }

   public String getGiftcard_id() {
      return giftcard_id;
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
