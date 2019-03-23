package PackageDatabase;

public class Address {

   int address_id;
   String customer_email;

   String house_num;
   String street;
   String city;
   String state;
   int zip_code;
   int country_code;


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
                  String house_num, String street, String city, String state, int zip_code){
      this.address_id = address_id;
      this.customer_email = customer_email;
      this.house_num = house_num;
      this.street = street;
      this.city = city;
      this.state = state;
      this.zip_code = zip_code;
      this.country_code = 1; // the US's country code (+1)

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
                  String house_num, String street, String city, String state, int zip_code, int country_code){
      this.address_id = address_id;
      this.customer_email = customer_email;
      this.house_num = house_num;
      this.street = street;
      this.city = city;
      this.state = state;
      this.zip_code = zip_code;
      this.country_code = country_code;
   }
}
