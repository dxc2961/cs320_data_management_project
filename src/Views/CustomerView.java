package Views;

/**
 * View for a customer accessing the application to view things such as order history,
 * account information, payment method information, and address information.
 */



import javax.xml.transform.Result;
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
    private void viewOrders(boolean openOnly){
        try{
            String orderQuery = "SELECT " +
                    "orders.order_id, orders.email, orders.order_date, orders.delivery_date, package.package_count, payment.payment_type, " +
                    "CASE " +
                      "WHEN payment.payment_type='credit' THEN payment.card_number " +
                      "WHEN payment.payment_type='check' THEN payment.check_num " +
                      "WHEN payment.payment_type='gift card' THEN payment.gift_card_id " +
                      "ELSE payment.payment_type " +
                    "END AS payment_identifier, " +
                    "delivery.house_num, delivery.street_name, delivery.city, delivery.state, delivery.country_code, delivery.zip_code, " +
                    "return.house_num, return.street_name, return.city, return.state, return.country_code, return.zip_code " +
                    "FROM orders " +
                    "LEFT OUTER JOIN " +
                      "(SELECT " +
                      "PAYMENT_ID, payment_type, " +
                      "card_number, check_num, gift_card_id " +
                      "FROM payment_details) AS payment " +
                    "ON payment.payment_id=orders.payment_id " +
                    "LEFT OUTER JOIN " +
                      "address AS return " +
                    "ON return.address_id=orders.return_address_id " +
                    "LEFT OUTER JOIN " +
                      "address AS delivery " +
                    "ON delivery.address_id=orders.delivery_address_id " +
                    "LEFT OUTER JOIN " +
                      "(SELECT order_id, COUNT(package_id) AS package_count FROM package GROUP BY order_id) package " +
                    "ON orders.order_id=package.order_id " +
                    "WHERE orders.email=\'" + this.email + "\'";
            if(openOnly)
                orderQuery = orderQuery.concat(" AND orders.delivery_date >= SYSDATE");
            ResultSet results = this.runQuery(orderQuery);
            this.printOrders(results);
            /*System.out.println("Would you like to edit this information? (y/n)");
            char action = this.in.next().charAt(0);
            in.nextLine();
            if(action == 'y')
                this.editProfile(results);*/
        } catch (SQLException s){
            s.printStackTrace();
        }
    }

    private void printOrders(ResultSet results){
        try {
            int order = 1;
            while (results.next()) {
                System.out.printf("\tOrder %d: \n\t\tOrder Date: %s \n\t\tDelivery Date: %s \n\t\tPackage Count: %s \n\t\tPayment Type Used: %s\t%s\n",
                        order,
                        //results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        results.getString(6),
                        results.getString(7));

                System.out.printf("\t\tDelivery Address: %s %s %s %s %s %s\n",
                        results.getString(8),
                        results.getString(9),
                        results.getString(10),
                        results.getString(11),
                        results.getString(12),
                        results.getString(13));

                System.out.printf("\t\tReturn Address: %s %s %s %s %s %s\n",
                        results.getString(14),
                        results.getString(15),
                        results.getString(16),
                        results.getString(17),
                        results.getString(18),
                        results.getString(19));
                order++;
            }
            System.out.println("Would you like to edit any of these orders? (y/n)");
            if(in.next().charAt(0) == 'y'){
                in.nextLine();
                editOrders(results);
            }
            else
                in.nextLine();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Allows a user to edit their order information
     */
    private void editOrders(ResultSet results){
        System.out.println("Enter the order number you would like to edit");
        int order = in.nextInt();
        in.nextLine();

        System.out.println("Editing order " + order);

        int orderId;

        try {
            results.absolute(1);
            while (order > 1) {
                results.next();
                order--;
            }
            orderId = results.getInt(1);
        } catch (SQLException s){
            System.err.println("Order number out of range");
            return;
        }

        String checkStatus =
                "SELECT order_id, " +
                "sum(CASE " +
                      "WHEN current_status='in_warehouse' THEN 1 " +
                      "ELSE 0 " +
                    "END) AS in_warehouse_count, " +
                "count(*) AS total_count " +
                "FROM " +
                  "(SELECT " +
                  "orders.order_id, package.package_id, " +
                  "orders.email, package.current_status " +
                  "FROM orders NATURAL JOIN package " +
                  "WHERE email=\'" + this.email + "\' " +
                    "AND orders.order_id=" + orderId + ") " +
                "GROUP BY order_id";
        try{
            ResultSet validator = this.runQuery(checkStatus);
            validator.next();
            if(!validator.getString(2).equals(validator.getString(3))) {
                System.err.println("This order has packages that have left the warehouse, therefore you cannot edit the addresses for that order");
                return;
            }
        } catch (SQLException s){
            System.err.println("Unable to analyze order");
            return;
        }

        System.out.println("Press r to edit the order's return address");
        System.out.println("Press d to edit the order's delivery address");
        System.out.println("Press b to go back");
        char action = in.next().charAt(0);
        in.nextLine();
        int newAddressNum;

        ResultSet addresses = null;
        if(action!='b'){
            System.out.println("Here are your current addresses:");
            addresses = this.viewAddresses(false);
        }

        switch (action) {
            case 'b':
                return;
            case 'r':
                System.out.println("Please enter the number of the new return address");
                newAddressNum = this.in.nextInt();
                in.nextLine();
                try {
                    addresses.absolute(newAddressNum);
                }
                catch(SQLException s){
                    System.err.println("Address number out of bounds");
                    return;
                }

                try{
                    //validate they can update this information
                    this.runUpdate("UPDATE orders SET return_address_id=" + addresses.getInt(1) + " WHERE order_id=" + orderId);
                    System.out.println("Your return address has been updated");
                } catch(SQLException s){
                    System.err.println("Invalid street name");
                }
                break;
            case 'd':
                System.out.println("Please enter the number of the new delivery address");
                newAddressNum = this.in.nextInt();
                in.nextLine();
                try {
                    addresses.absolute(newAddressNum);
                }
                catch(SQLException s){
                    System.err.println("Address number out of bounds");
                    return;
                }

                try{
                    //validate they can update this information
                    this.runUpdate("UPDATE orders SET delivery_address_id=" + addresses.getInt(1) + " WHERE order_id=" + orderId);
                    System.out.println("Your delivery address has been updated");
                } catch(SQLException s){
                    System.err.println("Invalid city name");
                }
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }




    /**
     * Gets the signed in customer's delivery address information we have on file for them
     * From here they can add a new address or delete/edit an old one
     */
    private ResultSet viewAddresses(boolean shouldPrompt){
        ResultSet results = null;
        try {
            results = this.runQuery("SELECT * FROM address WHERE customer_email=\'" + this.email + "\';");
            this.printAddresses(results);

            if(shouldPrompt) {
                System.out.println("Press e to edit an existing address");
                System.out.println("Press a to add a new address");
                System.out.println("Press b to go back");
                char action = this.in.next().charAt(0);
                in.nextLine();
                if (action == 'e')
                    this.editAddresses(results);
                else if (action == 'a')
                    this.createAddress(results);
            }
        } catch (SQLException s){
            s.printStackTrace();
        }
        return results;
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
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    private void createAddress(ResultSet results){
        String housenum = "", street = "", city = "", state = "", country = "", zip = "";
        boolean canContinue = false;

        while(!canContinue) {
            System.out.println("What is the house number of the new address?");
            housenum = in.nextLine();
            if(housenum.length() <= 10){
                canContinue = true;
            }
            else
                System.out.println("House number must be of length 10 or less");
        }
        canContinue = false;
        while(!canContinue) {
            System.out.println("What is the street name of the new address?");
            street = in.nextLine();
            if(street.length() <= 30){
                canContinue = true;
            }
            else
                System.out.println("Street name must be of length 30 or less");
        }
        canContinue = false;
        while(!canContinue) {
            System.out.println("What city is the new address in?");
            city = in.nextLine();
            if(city.length() <= 20){
                canContinue = true;
            }
            else
                System.out.println("City name must be of length 20 or less");
        }
        canContinue = false;
        while(!canContinue) {
            System.out.println("What state is the new address in?");
            state = in.nextLine();
            if(state.length() <= 30){
                canContinue = true;
            }
            else
                System.out.println("State name must be of length 30 or less");
        }
        canContinue = false;
        while(!canContinue) {
            System.out.println("What is ISO alpha-3 country code of the new address?");
            country = in.nextLine();
            if(country.length() == 3){
                canContinue = true;
            }
            else
                System.out.println("Country code must be of length 3");
        }
        canContinue = false;
        while(!canContinue) {
            System.out.println("What is the zip code of the new address?");
            zip = in.nextLine();
            if(zip.length() == 5 && isNumeric(zip)){
                canContinue = true;
            }
            else
                System.out.println("Zip code must be of length 5 and consist only of numbers");
        }

        String insertSQL = "INSERT INTO address (house_num, street_name, city, " +
                "state, country_code, zip_code, customer_email) VALUES (\'" +
                housenum + "\', \'" + street + "\', \'" + city + "\', \'" +
                state + "\', \'" + country + "\', \'" + zip + "\', \'" + this.email + "\')";

        try {
            this.runUpdate(insertSQL);
        } catch (SQLException s){
            s.printStackTrace();
            System.err.println("Unable to add new address");
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
                    "SELECT * FROM payment_details " +
                            "WHERE cust_email=\'" + this.email + "\' AND active=true AND payment_type<>\'none\'");
            this.printPayments(results);
            System.out.println("Would you like to edit this information? (y/n)");
            char action = this.in.next().charAt(0);
            in.nextLine();

            if(action == 'y')
                this.modifyPayments(results);
        } catch (SQLException s){
            s.printStackTrace();
        }

    }

    /**
     * Prints out all the payment methods that are active and attributed to the customer
     *
     * @param results the results from the query looking for payment methods
     */
    private void printPayments(ResultSet results){
        try {
            this.printGiftCardTotal();
            int payment = 1;
            while (results.next()) {
                if(results.getString(14).equals("credit"))
                    printCreditCards(results,payment);
                if(results.getString(14).equals("check"))
                    printChecks(results,payment);
                if(results.getString(14).equals("gift card"))
                    printGiftCards(results,payment);
                payment++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void printGiftCardTotal(){
        String balanceSQL = "SELECT SUM(balance) FROM payment_details WHERE cust_email=\'" + this.email + "\' AND active=TRUE";

        try {
            ResultSet results = this.runQuery(balanceSQL);
            results.next();
            System.out.println("You have " + results.getInt(1) + "$ in gift card balance");
        } catch (SQLException s){
            System.err.println("Unable to calculate gift card balance");
        }


    }

    private void printCreditCards(ResultSet results, int payment){
        try {
            System.out.printf("Payment %d, Credit Card:\n\tCard Num:%s\n\tOwner Name:%s\n\tExpiration Date:%s\n\tSecurity Code:%s\n",
                    payment,
                    results.getString(4),
                    results.getString(5),
                    results.getString(6),
                    results.getString(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printChecks(ResultSet results, int payment){
        try {
            System.out.printf("Payment %d, Check:\n\tRouting Num:%s\n\tAccount Num:%s\n\tCheck Num:%s\n",
                    payment,
                    results.getString(8),
                    results.getString(9),
                    results.getString(10));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printGiftCards(ResultSet results, int payment){
        try {
            System.out.printf("Payment %d, Gift Card:\n\tGift Card ID:%s\n\tExpiration Date:%s\n\tBalance:%s\n",
                    payment,
                    results.getString(11),
                    results.getString(12),
                    results.getString(13));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows a customer to add new Credit Cards to their account's payment methods
     */
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
            ResultSet payment_id = this.runQuery("SELECT * FROM payment_details " +
                    "WHERE cust_email=\'" + this.email + "\' AND active=true " +
                    "AND card_number IS NULL AND routing_num IS NULL AND gift_card_id IS NULL");
            payment_id.next();
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
                        "SELECT * FROM payment_details " +
                        "WHERE cust_email=\'" + this.email + "\' AND active=true " +
                        "  AND card_number IS NULL " +
                        "  AND routing_num IS NULL " +
                        "  AND gift_card_id IS NULL)");
            } catch (SQLException e){
                System.err.println("Could not remove old null entry. Contact your system administrator");
            }
            s.printStackTrace();
        }
    }

    private void redeemGiftCard(){
        boolean bad = true;
        while(bad) {
            System.out.println("Please enter the unique 15-character ID on the back of the card, q to quit");

            String id = in.nextLine();
            if(id.equals("q"))
                break;
            if (id.length() != 15)
                System.out.println("Invalid redemption ID");
            else {
                String testGiftCardIDSQL = "SELECT * FROM gift_card " +
                        "LEFT OUTER JOIN payment_method ON gift_card.payment_id=payment_method.payment_id " +
                        "WHERE gift_card_id=\'" + id + "\' AND expiration_date>SYSDATE";
                try {
                    ResultSet results = this.runQuery(testGiftCardIDSQL);
                    results.next();

                    if (results.getInt(2) == 0) { //it was a correct gift card id

                        String giftCardRedeemSQL = "UPDATE gift_card SET payment_id=\'";

                        String paymentMethodInsertSQL = "INSERT INTO payment_method (cust_email) VALUES (\'" + this.email + "\')";
                        try {
                            this.runUpdate(paymentMethodInsertSQL);
                        } catch (SQLException s) {
                            System.err.println("Problem creating payment_method entry");
                            return;
                        }

                        try {
                            ResultSet payment_id = this.runQuery("SELECT * FROM payment_details " +
                                    "WHERE cust_email=\'" + this.email + "\' AND active=true " +
                                    "AND card_number IS NULL AND routing_num IS NULL AND gift_card_id IS NULL");
                            payment_id.next();
                            System.out.println(payment_id.getString(1));
                            giftCardRedeemSQL += payment_id.getString(1) + "\' WHERE gift_card_id=\'" + id + "\'";
                        } catch (SQLException s) {
                            System.err.println("Problem getting new payment_id");
                        }
                        try{
                            this.runUpdate(giftCardRedeemSQL);
                        } catch (SQLException s){
                            System.out.println("Unable to redeem card");
                            s.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("Gift card already used");
                    }

                    System.out.println(results.getString(1));
                } catch (SQLException s){
                    System.out.println("Invalid gift card");
                }
            }
        }
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
                    System.out.println("Would you like to only view open orders? (y/n)");
                    char action2 = in.nextLine().charAt(0);
                    this.viewOrders(action2 == 'y');
                    break;
                case 'a':
                    this.viewAddresses(true);
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
