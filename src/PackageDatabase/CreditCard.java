package PackageDatabase;

public class CreditCard{

   private String card_number;
   private int security_code;
   private String owner_name;
   private String expiration_date;
   private int payment_ID;

   public CreditCard(String card_number, int payment_ID, String owner_name, String expiration_date, int security_code) {
      this.payment_ID = payment_ID;
      this.card_number = card_number;
      this.owner_name = owner_name;
      this.expiration_date = expiration_date;
      this.security_code = security_code;
   }

   public String getCard_number() {
      return card_number;
   }

   public int getSecurity_code() {
      return security_code;
   }

   public int getPayment_ID() {return payment_ID;}

   public String getOwner_name() {
      return owner_name;
   }

   public String getExpiration_date() {
      return expiration_date;
   }
}

