package Views;

import java.sql.*;

/**
 * This View would be the view used by someone who provides customer service for our company.
 *
 * They have the ability to select a customer account to investigate, then from there they can get
 * information about that customer, including their profile details, order details, address information,
 * and (to a limited extent) their payment method information.
 */

public class ServiceEmployeeView extends View {

    private String custUsername;


    public ServiceEmployeeView(){
        super();
    }

    /**
     * Gets the profile information for the customer this CSR is helping
     */
    private void findProfile(){
        //TODO query other relevant profile information for the customer

        System.out.println("querying profile data for " + this.custUsername + "!");

    }

    /**
     * Gets the order information for the customer this CSR is helping
     */
    private void findOrders(){
        //TODO query other relevant order information for this user

        System.out.println("querying order data for " + this.custUsername + "!");

    }

    /**
     * Gets the address information for the customer this CSR is helping
     */
    private void findAddresses(){
        //TODO query other relevant profile information for this user

        System.out.println("querying address data for " + this.custUsername + "!");

    }

    /**
     * Gets the payment information for the customer this CSR is helping
     */
    private void findPayments(){
        //TODO query other relevant profile information for this user

        System.out.println("querying payment method data for " + this.custUsername + "!");

    }


    private void printProfile(ResultSet results){
        try {
            while (results.next()) {
                System.out.println("profile details:");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void printOrders(ResultSet results){
        try {
            while (results.next()) {
                System.out.println("order details:");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void printAddresses(ResultSet results){
        try {
            while (results.next()) {
                System.out.printf("\tAddress %d: %s %s %s %s %s %s\n",
                        results.getInt(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        results.getString(6),
                        results.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void printPayments(ResultSet results){
        try {
            while (results.next()) {
                System.out.println("payment details:");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void assist(){

        System.out.println("Hello " + this.username + "! What customer are you assisting?");

        this.custUsername = in.nextLine();
        //if custusername is in database

        System.out.println("What would you like to do?");

        while(this.isActive()) {
            System.out.println("Press p to view a user's profile");
            System.out.println("Press o to view a user's orders");
            System.out.println("Press a to view a user's addresses");
            System.out.println("Press y to view a user's payment methods");
            System.out.println("Press n to select a new customer");
            System.out.println("Press q to quit");
            char action = in.next().charAt(0);

            switch (action) {
                case 'p':
                    this.findProfile();
                    break;
                case 'o':
                    this.findOrders();
                    break;
                case 'a':
                    this.findAddresses();
                    break;
                case 'y':
                    this.findPayments();
                    break;
                case 'n':
                    System.out.println("What is the new customer name?");
                    this.custUsername = this.in.nextLine();
                    //if in database continue
                    break;
                case 'q':
                    this.quit();
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }

    }

}
