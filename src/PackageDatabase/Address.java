package PackageDatabase;

public class Address {

   private int address_id;
   private String customer_email;

   private String house_num;
   private String street;
   private String city;
   private String state;
   private String zip_code;
   private String country_code;


   /**
    * Constructor Domestic(US) Address
    * @param address_id
    * @param customer_email
    * @param house_num
    * @param street
    * @param city
    * @param state
    * @param zip_code
    */
   public Address(int address_id, String customer_email,
                  String house_num, String street, String city, String state, String zip_code){
      this.address_id = address_id;
      this.customer_email = customer_email;
      this.house_num = house_num;
      this.street = street;
      this.city = city;
      this.state = state;
      this.zip_code = zip_code;
      this.country_code = "USA"; // the US's alpha3 code USA

   }

   /**
    * Constructor for an international(non-US) address.
    * @param address_id
    * @param customer_email the customer associated with the address' email
    * @param house_num the home number of the address
    * @param street the stree name of the address
    * @param city the city name of the address
    * @param state the state name of the address
    * @param zip_code the zip code of the address
    * @param country_code the number identifying what country the address is from
    */
   public Address(int address_id, String customer_email,
                  String house_num, String street, String city, String state, String zip_code, String country_code){
      this.address_id = address_id;
      this.customer_email = customer_email;
      this.house_num = house_num;
      this.street = street;
      this.city = city;
      this.state = state;
      this.zip_code = zip_code;
      this.country_code = country_code;
   }

   public int getAddress_id(){
      return address_id;
   }

   public String getCustomer_email(){
      return customer_email;
   }

   public String getHouse_num(){
      return house_num;
   }

   public String getStreet(){
      return street;
   }

   public String getCity(){
      return city;
   }

   public String getState(){
      return state;
   }

   public String getZip_code(){
      return zip_code;
   }

   public String getCountry_code(){
      return country_code;
   }
}
