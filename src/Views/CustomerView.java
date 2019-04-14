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
        try {
            ResultSet results = this.runQuery("SELECT * FROM customer WHERE email=\'" + this.email + "\';");
            this.printProfile(results);
            System.out.println("Would you like to edit this information? (y/n)");
            char action = this.in.next().charAt(0);
            in.nextLine();
            if(action == 'y')
                this.editProfile(results);

        } catch (SQLException s){
            s.printStackTrace();
        }
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

    /**
     * Allows a user to edit their profile information
     */
    private void editProfile(ResultSet results){
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
                    if(input.equals(results.getString(3))){
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
            ResultSet results = this.runQuery("SELECT * FROM order WHERE email=\'" + this.email + "\';");
            this.printProfile(results);
            System.out.println("Would you like to edit this information? (y/n)");
            char action = this.in.next().charAt(0);
            in.nextLine();
            if(action == 'y')
                this.editProfile(results);

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
        try {
            ResultSet results = this.runQuery("SELECT * FROM address WHERE customer_email=\'" + this.email + "\';");
            this.printAddresses(results);
            System.out.println("Would you like to edit this information? (y/n)");
            char action = this.in.next().charAt(0);
            in.nextLine();

            switch (action) {
                case 'y':
                    this.editAddresses(results);
                    break;
            }
        } catch (SQLException s){
            s.printStackTrace();
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

    /**
     * Allows a user to edit their address information
     */
    private void editAddresses(ResultSet results){
        System.out.println("Enter the address number you would like to edit");
        int address = in.nextInt();
        in.nextLine();

        System.out.println("Editing address " + address);

        try {
            results.absolute(1);
            while (address > 1) {
                results.next();
                address--;
            }
        } catch (SQLException s){
            System.err.println("Address number out of range");
        }


        System.out.println("Press 1 to edit your house number");
        System.out.println("Press 2 to edit your street name");
        System.out.println("Press 3 to edit your city");
        System.out.println("Press 4 to edit your state");
        System.out.println("Press 5 to edit your country code");
        System.out.println("Press 6 to edit your zip code");
        char action = in.next().charAt(0);
        in.nextLine();
        String input;


        switch (action) {
            case '1':
                System.out.println("Please enter your new house number");
                input = this.in.nextLine();
                try {
                    this.runUpdate("UPDATE address SET house_num=\'" + input + "\' WHERE customer_email=\'" + this.email + "\' AND address_id=\'" + results.getString(1) + "\'");
                    System.out.println("Your house number has been updated to " + input);
                } catch(SQLException s){
                    System.err.println("Invalid house number");
                }
                break;
            case '2':
                System.out.println("Please enter your new street name");
                input = this.in.nextLine();
                try{
                    this.runUpdate("UPDATE address SET street_name=\'" + input + "\' WHERE customer_email=\'" + this.email + "\' AND address_id=\'" + results.getString(1) + "\'");
                    System.out.println("Your street name has been updated to " + input);
                } catch(SQLException s){
                    System.err.println("Invalid street name");
                }
                break;
            case '3':
                System.out.println("Please enter your new city name");
                input = this.in.nextLine();
                try{
                    this.runUpdate("UPDATE address SET city=\'" + input + "\' WHERE customer_email=\'" + this.email + "\' AND address_id=\'" + results.getString(1) + "\'");
                    System.out.println("Your city name has been updated to " + input);
                } catch(SQLException s){
                    System.err.println("Invalid city name");
                }
                break;
            case '4':
                System.out.println("Please enter your new state name");
                input = this.in.nextLine();
                try{
                    this.runUpdate("UPDATE address SET state=\'" + input + "\' WHERE customer_email=\'" + this.email + "\' AND address_id=\'" + results.getString(1) + "\'");
                    System.out.println("Your state name has been updated to " + input);
                } catch(SQLException s){
                    System.err.println("Invalid state name.");
                }
                break;
            case '5':
                System.out.println("Please enter your new country code. Please use ISO alpha-3 country codes only (USA, GBR, CAN, etc.).");
                input = this.in.nextLine();
                try{
                    this.runUpdate("UPDATE address SET country_code=\'" + input + "\' WHERE customer_email=\'" + this.email + "\' AND address_id=\'" + results.getString(1) + "\'");
                    System.out.println("Your country code has been updated to " + input);
                } catch(SQLException s){
                    System.err.println("Invalid ISO alpha-3 code. Please refer to an online source to check validity");
                }
                break;
            case '6':
                System.out.println("Please enter your new zip code.");
                input = this.in.nextLine();
                try{
                    if(!isNumeric(input))
                        System.err.println("Ensure zip code consists only of numbers");
                    else {
                        this.runUpdate("UPDATE address SET zip_code=\'" + input + "\' WHERE customer_email=\'" + this.email + "\' AND address_id=\'" + results.getString(1) + "\'");
                        System.out.println("Your zip code has been updated to " + input);
                    }
                } catch(SQLException s){
                    System.err.println("Invalid zip code. Ensure 5-digit code consisting only of numbers");
                }
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    /**
     * Gets the payment information attributed to the signed in user
     * From here they can add new payments or delete/edit old ones
     */
    private void viewPayments(){
        try {
            ResultSet results = this.runQuery(
                    "SELECT " +
                            "pay.payment_id, " +
                            "cred.card_number, cred.owner_name, " +
                            "cred.expiration_date, cred.security_code, " +
                            "checks.routing_num, checks.account_num, " +
                            "checks.check_num, " +
                            "gift.gift_card_id, gift.expiration_date, " +
                            "gift.balance " +
                            "FROM payment_method pay " +
                                "LEFT OUTER JOIN credit_card cred ON pay.payment_id = cred.payment_id " +
                                "LEFT OUTER JOIN checks ON pay.payment_id = checks.payment_id " +
                                "LEFT OUTER JOIN gift_card gift ON pay.payment_id = gift.payment_id " +

                            "WHERE pay.cust_email=\'" + this.email + "\' AND pay.active=true");
            this.printPayments(results);
            System.out.println("Would you like to edit this information? (y/n)");
            char action = this.in.next().charAt(0);
            in.nextLine();

            if(action == 'y')
                this.modifyPayments(results);
        } catch (SQLException s){
            s.printStackTrace();
        }
        System.out.println("querying payment method data!");

    }

    private void printPayments(ResultSet results){
        //TODO add censors for important information
        try {
            int payment = 1;
            while (results.next()) {
                if(results.getString(2) != null)
                    printCreditCards(results,payment);
                if(results.getString(6) != null)
                    printChecks(results,payment);
                if(results.getString(9) != null)
                    printGiftCards(results,payment);
                payment++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void printCreditCards(ResultSet results, int payment){
        try {
            System.out.printf("Payment %d, Credit Card:\n\tCard Num:%s\n\tOwner Name:%s\n\tExpiration Date:%s\n\tSecurity Code:%s\n",
                    payment,
                    results.getString(2),
                    results.getString(3),
                    results.getString(4),
                    results.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printChecks(ResultSet results, int payment){
        try {
            System.out.printf("Payment %d, Check:\n\tRouting Num:%s\n\tAccount Num:%s\n\tCheck Num:%s\n",
                    payment,
                    results.getString(6),
                    results.getString(7),
                    results.getString(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printGiftCards(ResultSet results, int payment){
        try {
            System.out.printf("Payment %d, Gift Card:\n\tGift Card ID:%s\n\tExpiration Date:%s\n\tBalance:%s\n",
                    payment,
                    results.getString(9),
                    results.getString(10),
                    results.getString(11));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCreditCard(){
        String paymentMethodInsertSQL = "INSERT INTO payment_method (cust_email) VALUES (\'" + this.email + "\')";
        try{
            this.runUpdate(paymentMethodInsertSQL);
        } catch (SQLException s){
            System.err.println("Problem creating payment_method entry");
            return;
        }

        String creditCardInsertSQL = "INSERT INTO credit_card VALUES (\'";

        boolean canContinue = false;
        while(!canContinue) {
            System.out.println("Please enter the new credit card's number, or q to quit");
            String cardNum = in.nextLine();
            if(cardNum.equals("q"))
                return;
            if (isNumeric(cardNum) && cardNum.length() == 16) {
                creditCardInsertSQL += cardNum + "\', ";
                canContinue = true;
            }
            else{
                System.err.println("Please ensure your input is 16 numeric digits");
                System.out.println(cardNum.length());
            }
        }

        try {
            ResultSet payment_id = this.runQuery("SELECT " +
                    "pay.payment_id, cred.card_number, checks.routing_num, gift.gift_card_id " +
                    "FROM payment_method pay " +
                    "LEFT OUTER JOIN credit_card cred ON pay.payment_id = cred.payment_id " +
                    "LEFT OUTER JOIN checks ON pay.payment_id = checks.payment_id " +
                    "LEFT OUTER JOIN gift_card gift ON pay.payment_id = gift.payment_id " +
                    "WHERE pay.cust_email=\'" + this.email + "\' AND pay.active=true " +
                    "AND cred.card_number IS NULL AND checks.routing_num IS NULL AND gift.gift_card_id IS NULL");
            payment_id.next();
            System.out.println(payment_id.getString(1));
            creditCardInsertSQL += payment_id.getString(1) + ", \'";
        } catch (SQLException s){
            System.err.println("Problem getting new payment_id");
            s.printStackTrace();
            return;
        }

        canContinue = false;
        while(!canContinue) {
            System.out.println("Please enter the new credit card's card holder name, or q to quit");
            String holdername = in.nextLine();
            if(holdername.equals("q"))
                return;
            if (holdername.length() > 25)
                System.err.println("Please ensure your input is less than 26 digits long");
            else{
                creditCardInsertSQL += holdername + "\', \'";
                canContinue = true;
            }
        }

        canContinue = false;
        while(!canContinue) {
            System.out.println("Please enter the new credit card's expiration date, or q to quit");
            String expDate = in.nextLine();
            if(expDate.equals("q"))
                return;
            if (expDate.length() != 5 || !isNumeric(expDate.substring(0,2)) || !isNumeric(expDate.substring(3)) || !expDate.substring(2,3).equals("/"))
                System.err.println("Please ensure your input is 5 digits long of the form '00/00'");
            else{
                creditCardInsertSQL += expDate + "\', \'";
                canContinue = true;
            }
        }

        canContinue = false;
        while(!canContinue) {
            System.out.println("Please enter the new credit card's security code, or q to quit");
            String secCode = in.nextLine();
            if(secCode.equals("q"))
                return;
            if (!isNumeric(secCode) || secCode.length() != 3)
                System.err.println("Please ensure your input is 3 numeric digits");
            else{
                creditCardInsertSQL += secCode + "\'";
                canContinue = true;
            }
        }
        creditCardInsertSQL += ")";
        try {
            this.runUpdate(creditCardInsertSQL);
        } catch (SQLException s){
            System.err.println("Could not create credit card entry");

            try {

                this.runUpdate("DELETE FROM " +
                        "payment_method " +
                        "WHERE payment_id IN (SELECT payment_id FROM " +
                        "(SELECT " +
                        "pay.payment_id, cred.card_number, checks.routing_num, gift.gift_card_id " +
                        "FROM payment_method pay " +
                        "  LEFT OUTER JOIN credit_card cred ON pay.payment_id = cred.payment_id " +
                        "  LEFT OUTER JOIN checks ON pay.payment_id = checks.payment_id " +
                        "  LEFT OUTER JOIN gift_card gift ON pay.payment_id = gift.payment_id " +
                        "WHERE pay.cust_email='sean@gmail.com' AND pay.active=true " +
                        "  AND cred.card_number IS NULL " +
                        "  AND checks.routing_num IS NULL " +
                        "  AND gift.gift_card_id IS NULL) withnulls)");



            } catch (SQLException e){
                e.printStackTrace();
            }
            s.printStackTrace();
        }
    }

    private void redeemGiftCard(){

    }

    /**
     * Allows a user to edit their payment information by creating new ones or removing old ones
     */
    private void modifyPayments(ResultSet results){
        boolean isFinished = false;

        while(!isFinished){
            System.out.println("Press a to add new payment method");
            System.out.println("Press d to delete old payment method");
            System.out.println("Press b to go back");
            char action = in.next().charAt(0);
            in.nextLine();

            switch(action) {
                case 'a':
                    System.out.println("Press c to add a new credit card");
                    System.out.println("Press g to redeem a new gift card");
                    char action2 = in.next().charAt(0);
                    in.nextLine();
                    if(action2 == 'c')
                        this.createCreditCard();
                    else if(action2 == 'g')
                        this.redeemGiftCard();
                    break;

                case 'd':
                    System.out.println("Which payment method would you like to remove?");
                    int payment = in.nextInt();
                    in.nextLine();
                    try {
                        results.absolute(1);
                        while (payment > 1) {
                            results.next();
                            payment--;
                        }
                        this.runUpdate("UPDATE payment_method SET active=false WHERE payment_id=\'" + results.getString(1) + "\'");
                    } catch (SQLException s){
                        System.err.println("Payment number out of range");
                    }
                    break;

                case 'b':
                    isFinished = true;
                    break;
            }
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
