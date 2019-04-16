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

        System.out.println("Please enter the order number: ");
        int order_id = Integer.parseInt(in.next());

        System.out.println("querying for order!");
        //query to get customer name for printing order details
        String query3 = String.format("SELECT * FROM customer LEFT OUTER JOIN orders ON customer.EMAIL = orders.EMAIL " +
                "WHERE orders.ORDER_ID = %d", order_id);
        try{
            ResultSet results = runQuery(query3);
            printOrderName(results);
        }catch (SQLException e){
            e.printStackTrace();
        }
        //query to get order info for printing order details
        String query = String.format("SELECT * FROM orders LEFT OUTER JOIN address ON orders.DELIVERY_ADDRESS_ID = address.ADDRESS_ID " +
                "WHERE ORDER_ID = %d;", order_id);
        try {
            ResultSet results = runQuery(query);
            printOrder(results);
        }catch (SQLException e){
            e.printStackTrace();
        }
        //query to get return address for printing order details
        String query2 = String.format("SELECT * FROM orders LEFT OUTER JOIN address ON orders.RETURN_ADDRESS_ID = address.ADDRESS_ID " +
                "WHERE ORDER_ID = %d;", order_id);

        try{
            ResultSet results = runQuery(query2);
            printOrderReturn(results);
        }catch (SQLException e){
            e.printStackTrace();
        }

        String query4 = String.format("SELECT COUNT(*) FROM package WHERE ORDER_ID = %d;",order_id);

        try{
            ResultSet results = runQuery(query4);
            printOrderCount(results);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Search for the order and package information based off a package number
     * They need to be able to update the status of an order,
     * the delivery date of an order, and the current status of an order
     */
    private void searchPackage(){
        //TODO query for the provided package number
        System.out.println("Would you like to search by package id or order id? (p/o)");
        String search = in.next();
        String query;
        switch (search){
            case "p":
                System.out.println("Enter package id:");
                int p_id = Integer.parseInt(in.next());
                query = String.format("SELECT * FROM package WHERE PACKAGE_ID = %d", p_id);
                System.out.println("querying for package!");
                try{
                    ResultSet results = runQuery(query);
                    printPackage(results);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case "o":
                System.out.println("Enter order id:");
                int o_id = Integer.parseInt(in.next());
                query = String.format("SELECT * FROM package LEFT OUTER JOIN orders ON package.ORDER_ID = orders.ORDER_ID " +
                        "WHERE package.ORDER_ID = %d;", o_id);
                System.out.println("querying for package!");
                try{
                    ResultSet results = runQuery(query);
                    printPackage(results);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid Command");
                break;
        }

    }


    /**
     * Method called by searchOrder to print the customer name in the order details
     * @param results
     */
    private void printOrderName(ResultSet results){
        try{
            while (results.next()){
                System.out.printf("order details: \n\tcustomer name: %s \n\t",
                        results.getString(2));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Method called by searchOrder to print the order info in the order details
     * @param results
     */
    private void printOrder(ResultSet results){
        try {
            while (results.next()) {
                System.out.printf("customer email: %s \n\torder date: %s \n\t" +
                                "delivery date: %s \n\tdelivery address: %s %s %s, %s %s %s\n\t",
                        results.getString(3),
                        results.getDate(4),
                        results.getDate(5),
                        results.getString(9),
                        results.getString(10),
                        results.getString(11),
                        results.getString(12),
                        results.getString(13),
                        results.getString(14));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method called by searchOrder to print the return address in the order details
     * @param results
     */
    private void printOrderReturn(ResultSet results){
        try{
            while (results.next()){
                System.out.printf("return address: %s %s %s, %s %s %s\n\t",
                        results.getString(9),
                        results.getString(10),
                        results.getString(11),
                        results.getString(12),
                        results.getString(13),
                        results.getString(14));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void printOrderCount(ResultSet results){
        try{
            while(results.next()){
                System.out.printf("package count: %d\n\n", results.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    private void printPackage(ResultSet results){
        try {
            while (results.next()) {
                System.out.printf("package details: \n\torder id: %d \n\tpackage id: %d \n\tpackage type: %s \n\tweight: %s \n\t" +
                                "delivery speed: %s \n\tcurrent status: %s \n\tsignature required: %s \n\tinsurance status: %s" +
                                "\n\thazardous: %s \n\tfragile: %s \n\tperishable: %s \n\tdescription: %s \n\t" +
                                "provided value: %s\n\n",
                        results.getInt(1),
                        results.getInt(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        results.getString(6),
                        results.getString(7),
                        results.getString(8),
                        results.getString(9),
                        results.getString(10),
                        results.getString(11),
                        results.getString(12),
                        results.getString(13));
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
            System.out.println("Press q to quit");
            char action = in.next().charAt(0);
            in.nextLine();

            switch (action) {
                case 'o':
                    this.searchOrder();
                    break;
                case 'p':
                    this.searchPackage();
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
