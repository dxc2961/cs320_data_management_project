package PackageDatabase;

import java.util.Calendar;

public class Customer {

    private String email;
    private String display_name;
    private String password;
    private String phone_number;
    private Date date_created;

    public Customer(String email, String display_name, String password, String phone_number, Date date_created){

        this.email = email;
        this.display_name = display_name;
        this.password = password;
        this.phone_number = phone_number;
        this.date_created = date_created;
    }

    /**
     *  GETTERS
     */

    public String getEmail(){
        return email;
    }

    public String getDisplay_name(){
        return display_name;
    }

    public String getPassword(){
        return password;
    }

    public String getPhone_number(){
        return phone_number;
    }

    public Date getDate_created(){
        return date_created;
    }

    /**
     * SETTERS
     */

    public void setEmail(String email){
        this.email = email;
    }

    public void setDisplay_name(String display_name){
        this.display_name = display_name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }

    public void setDate_created(Date date_created){
        this.date_created = date_created;
    }

    /**
     * Returns the age of the account in years
     */
    public int accountAge(){
        Date created = this.getDate_created();
        int year = created.getYear();
        int currYear = Calendar.getInstance().get(Calendar.YEAR);
        return currYear - year;
    }
}
