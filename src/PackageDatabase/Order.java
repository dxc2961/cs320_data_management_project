package PackageDatabase;

public class Order {

   private int order_id;
   private int payment_id;
   private String email;
   private Date order_date; // the date the order was placed
   private Date delivery_date; // estimated date of delivery
   private int delivery_address_id;
   private int return_address_id;

   public Order(int order_id, int payment_id, String email, Date order_date, Date delivery_date,
                int delivery_address_id, int return_address_id){
      this.order_id = order_id;
      this.payment_id = payment_id;
      this.email = email;
      this.order_date = order_date;
      this.delivery_date = delivery_date;
      this.delivery_address_id = delivery_address_id;
      this.return_address_id = return_address_id;
   }

   /////////
   //GETTERS

   public Date getDeliveryLength(){
      return order_date;
   }
}
