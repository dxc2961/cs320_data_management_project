package PackageDatabase;

import static PackageDatabase.Package_type.*;

public class Package {

   // CONSTANTS
   private double PRICE_PER_OZ = 0.15;

   // ATTRIBUTES

   private int order_id;
   private int package_id;
   private Package_type package_type;
   private int weight; // in ounces (oz)
   private Delivery_speed delivery_speed;
   private Delivery_status delivery_status;
   private boolean sign_required;
   private boolean insurance_status;
   private boolean hazard_status;
   private boolean fragile_status;
   private boolean perishable_status;
   private String item_description;
   private int provided_value;

   /**
    * Constructor for an international package.
    * @param order_id
    * @param package_id
    * @param package_type
    * @param weight
    * @param delivery_speed
    * @param delivery_status
    * @param sign_required
    * @param insurance_status
    * @param hazard_status
    * @param fragile_status
    * @param perishable_status
    * @param item_description
    * @param provided_value
    */
   public Package(int order_id, int package_id, Package_type package_type, int weight, Delivery_speed delivery_speed,
                  Delivery_status delivery_status, boolean sign_required,boolean insurance_status,boolean hazard_status,
                  boolean fragile_status, boolean perishable_status, String item_description, int provided_value) {
      this.order_id = order_id;
      this.package_id = package_id;
      this.package_type = package_type;
      this.weight = weight;
      this.delivery_speed = delivery_speed;
      this.delivery_status = delivery_status;
      this.sign_required = sign_required;
      this.insurance_status = insurance_status;
      this.hazard_status = hazard_status;
      this.fragile_status = fragile_status;
      this.perishable_status = perishable_status;
      this.item_description = item_description;
      this.provided_value = provided_value;
   }

   /**
    * Constructor for a domestic package.
    * @param order_id
    * @param package_id
    * @param package_type
    * @param weight
    * @param delivery_speed
    * @param delivery_status
    * @param sign_required
    * @param insurance_status
    * @param hazard_status
    * @param fragile_status
    * @param perishable_status
    */
   public Package(int order_id, int package_id, Package_type package_type, int weight, Delivery_speed delivery_speed,
                  Delivery_status delivery_status, boolean sign_required, boolean insurance_status,
                  boolean hazard_status, boolean fragile_status, boolean perishable_status) {
      this.order_id = order_id;
      this.package_id = package_id;
      this.package_type = package_type;
      this.weight = weight;
      this.delivery_speed = delivery_speed;
      this.delivery_status = delivery_status;
      this.sign_required = sign_required;
      this.insurance_status = insurance_status;
      this.hazard_status = hazard_status;
      this.fragile_status = fragile_status;
      this.perishable_status = perishable_status;
   }

   /////////
   //GETTERS
   //

   public double getCost(){
      double cost = 5; // baseline cost of a delivery

      cost += weight * PRICE_PER_OZ; // 15 cents per oz

      cost *= package_type.getMult();

      cost *= delivery_speed.getMult();

      return cost;
   }

   public boolean isFragile_status() {
      return fragile_status;
   }

   public boolean isHazard_status() {
      return hazard_status;
   }

   public boolean isInsurance_status() {
      return insurance_status;
   }

   public boolean isPerishable_status() {
      return perishable_status;
   }

   public boolean isSign_required() {
      return sign_required;
   }

   public String getItem_description() {
      return item_description;
   }

   public Delivery_status getDelivery_status() {
      return delivery_status;
   }

   public int getProvided_value() {
      return provided_value;
   }

   /////////
   //SETTERS

   public void progressDelivery(){
      switch (delivery_status){
         case ORDER_PROCESSING:
            delivery_status = Delivery_status.IN_TRANSIENT;
            break;
         case IN_TRANSIENT:
            delivery_status = Delivery_status.OUT_FOR_DELIVERY;
            break;
         case OUT_FOR_DELIVERY:
            delivery_status = Delivery_status.DELIVERED;
            break;
         case DELIVERED:
            break;
      }
   }

}
