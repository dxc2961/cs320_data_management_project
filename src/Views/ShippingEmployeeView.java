package Views;

/**
 * This is the view that might be used by someone who works in the shipping department of our company.
 *
 * They would be the ones actually delivering the packages, and can search for order, package, or delivery address
 * in order to find out more about that entity.
 */

import java.sql.*;


public class ShippingEmployeeView extends View {


    public ShippingEmployeeView(){
        super();
    }

    /**
     * Search for the order and package information based off an order number
     * They need to be able to update the status of an order,
     * the delivery date of an order, and the current status of an order
     */
    private void searchOrder(){
        //TODO query for the provided order number

        System.out.println("querying for order!");

    }

    /**
     * Search for the order and package information based off a package number
     * They need to be able to update the status of an order,
     * the delivery date of an order, and the current status of an order
     */
    private void searchPackage(){
        //TODO query for the provided package number

        System.out.println("querying for package!");

    }

    /**
     * Search for the order and package information based off an order number
     * They need to be able to update the status of an order,
     * the delivery date of an order, and the current status of an order
     */
    private void searchAddress(){
        //TODO query for the provided address deliveries

        System.out.println("querying for delivery address!");

    }


    private void printOrder(ResultSet results){
        try {
            while (results.next()) {
                System.out.println("order details:");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void printPackage(ResultSet results){
        try {
            while (results.next()) {
                System.out.println("package details:");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void printAddress(ResultSet results){
        try {
            while (results.next()) {
                System.out.println("delivery address details:");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void assist() {
        System.out.println("Hello " + this.username + "! What are you searching for?");


        while(this.isActive()) {

            System.out.println("Press o to search for an order");
            System.out.println("Press p to search for a package");
            System.out.println("Press a to search for an address");
            System.out.println("Press q to quit");
            char action = in.next().charAt(0);

            switch (action) {
                case 'o':
                    this.searchOrder();
                    break;
                case 'p':
                    this.searchPackage();
                    break;
                case 'a':
                    this.searchAddress();
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
