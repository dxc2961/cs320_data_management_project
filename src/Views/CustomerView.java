package Views;

/**
 * View for a customer accessing the application to view things such as order history,
 * account information, payment method information, and address information.
 */

import com.sun.org.apache.regexp.internal.RESyntaxException;

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

        try {
            ResultSet results = this.runQuery("SELECT * FROM customer WHERE email=\'" + this.email + "\';");
            //System.out.println("YOUR EMAIL IS: " + this.email + "AND USERNAME IS: " + this.username); + "VS " + results.getString(2));
            this.printProfile(results);
            System.out.println("Would you like to edit this information? (y/n)");
            char action = this.in.next().charAt(0);
            in.nextLine();

            switch (action) {
                case 'y':
                    this.editProfile(results);
                    break;
            }
        } catch (SQLException s){
            s.printStackTrace();
        }
        System.out.println("querying profile data!");

    }

    private void editProfile(ResultSet profInfo){
        System.out.println("Press 1 to edit your email");
        System.out.println("Press 2 to edit your username");
        System.out.println("Press 3 to change your password");
        System.out.println("Press 4 to edit your phone number");
        char action = in.next().charAt(0);
        in.nextLine();
        String input;

        switch (action) {
            case '1':
                System.out.println("Please enter your new email");
                input = this.in.nextLine();
                try{
                    this.runUpdate("UPDATE customer SET email=\'" + input + "\' WHERE email=\'" + this.email + "\'");
                    this.email = input;
                    System.out.println("Your email has been updated to " + input);
                } catch(SQLException s){
                    s.printStackTrace();
                }
                break;
            case '2':
                System.out.println("Please enter your new username");
                input = this.in.nextLine();
                try{
                    this.runUpdate("UPDATE customer SET display_name=\'" + input + "\' WHERE email=\'" + this.email + "\'");
                    this.username = input;
                    System.out.println("Your username has been updated to " + input);
                } catch(SQLException s){
                    s.printStackTrace();
                }
                break;
            case '3':
                System.out.println("Please enter your current password");
                input = this.in.nextLine();
                boolean correctPass = false;
                try{
                    if(input.equals(profInfo.getString(3))){
                        System.out.println("Please enter new password");
                        input = this.in.nextLine();
                        correctPass = true;
                        this.runUpdate("UPDATE customer SET password = \'" + input + "\' WHERE email=\'" + this.email + "\'");
                        System.out.println("Your password has been updated");
                    }

                } catch (SQLException s){
                    if(correctPass)
                        System.out.println("Invalid new password");
                    else
                        System.out.println("Incorrect password. Exiting");
                }

                break;
            case '4':
                System.out.println("Please enter your new phone number, do not use any non-numeric characters");
                input = this.in.nextLine();
                try{
                    this.runUpdate("UPDATE customer SET phone_num=\'" + input + "\' WHERE email=\'" + this.email + "\'");
                    System.out.println("Your phone number has been updated to " + printPhoneNumber(input));
                } catch(SQLException s){
                    s.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }


    /**
     * Gets the order information for the customer that is logged in.
     * From here they can track an order or cancel an order.
     */
    private void viewOrders(){
        //TODO query other relevant order information for this user
        try{
            ResultSet results = this.runQuery("SELECT * FROM customer WHERE email=\'" + this.email + "\';");
            this.printProfile(results);
        } catch (SQLException s){
            s.printStackTrace();
        }
        System.out.println("querying order data!");

    }

    /**
     * Gets the signed in customer's delivery address information we have on file for them
     * From here they can add a new address or delete/edit an old one
     */
    private void viewAddresses(){
        //TODO query other relevant address information for this user

        try {
            ResultSet results = this.runQuery("SELECT * FROM address WHERE customer_email=\'" + this.email + "\';");
            this.printAddresses(results);
        } catch (SQLException s){
            s.printStackTrace();
        }
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
            System.out.printf("Email: %s\nUsername: %s\nPhone Number: %s", results.getString(1), results.getString(2), printPhoneNumber(results.getString(4)));
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
            in.nextLine();

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
