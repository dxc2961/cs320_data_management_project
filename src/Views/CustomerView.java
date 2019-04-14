package Views;

/**
 * View for a customer accessing the application to view things such as order history,
 * account information, payment method information, and address information.
 */

import java.sql.*;


public class CustomerView extends View{



    public CustomerView(){
        super();
    }

    /**
     * Gets the relevant profile information for a user and prints it out
     * From here they can edit the information however they'd like
     */
    private void viewProfile(){
        //TODO query other relevant profile information for this user

        ResultSet results = this.runQuery("SELECT * FROM customer WHERE email=\'" + this.email + "\';");
        this.printProfile(results);
        System.out.println("querying profile data!");

    }

    /**
     * Gets the order information for the customer that is logged in.
     * From here they can track an order or cancel an order.
     */
    private void viewOrders(){
        //TODO query other relevant order information for this user

        ResultSet results = this.runQuery("SELECT * FROM customer WHERE email=\'" + this.email + "\';");
        this.printProfile(results);
        System.out.println("querying order data!");

    }

    /**
     * Gets the signed in customer's delivery address information we have on file for them
     * From here they can add a new address or delete/edit an old one
     */
    private void viewAddresses(){
        //TODO query other relevant address information for this user

        ResultSet results = this.runQuery("SELECT * FROM address WHERE customer_email=\'" + this.email + "\';");
        this.printAddresses(results);
        System.out.println("querying address data!");

    }

    /**
     * Gets the payment information attributed to the signed in user
     * From here they can add new payments or delete/edit old ones
     */
    private void viewPayments(){
        //TODO query other relevant payment method information for this user

        System.out.println("querying payment method data!");

    }


    private void printProfile(ResultSet results){
        try {
            results.next();
            System.out.println(results.getString(2) + "'s Profile:");
            //while (results.next()) {
                /*System.out.printf("\tProfile %s: %s %s %s\n",
                        results.getString(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4));*/
                System.out.println("Email: " + results.getString(1));
                //System.out.println(results.getString(3));
                System.out.println("Phone Number: " + printPhoneNumber(results.getString(4)));
            //}
            System.out.println();
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
            int address = 1;
            while (results.next()) {
                System.out.printf("\tAddress %d: %s %s %s %s %s %s\n",
                        address,
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        results.getString(6),
                        results.getString(7));
                address++;
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
    public void assist() {

        System.out.println("Hello " + this.username + "! What would you like to do today?");

        while(this.isActive()) {
            System.out.println();
            System.out.println("Press p to view your profile");
            System.out.println("Press o to view your orders");
            System.out.println("Press a to view your addresses");
            System.out.println("Press y to view your payment methods");
            System.out.println("Press q to quit");
            char action = in.next().charAt(0);

            switch (action) {
                case 'p':
                    this.viewProfile();
                    break;
                case 'o':
                    this.viewOrders();
                    break;
                case 'a':
                    this.viewAddresses();
                    break;
                case 'y':
                    this.viewPayments();
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
