package PackageDatabase;

public class CreditCard extends PaymentMethod {

   private int card_number;
   private int security_code;
   private String owner_name;
   private int[] expiration_date;

   public CreditCard(int payment_ID, String email,
                     int card_number, String owner_name, int[] expiration_date, int security_code) {
      super(payment_ID, email);
      this.card_number = card_number;
      this.owner_name = owner_name;
      this.expiration_date = expiration_date;
      this.security_code = security_code;
   }

   public int getCard_number() {
      return card_number;
   }

   public int getSecurity_code() {
      return security_code;
   }

   public String getOwner_name() {
      return owner_name;
   }

   public int[] getExpiration_date() {
      return expiration_date;
   }
}
