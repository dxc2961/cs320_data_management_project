package Views;

public class ShippingEmployeeView extends View {


    public ShippingEmployeeView(){
        super();
    }


    public void searchOrder(){
        //TODO query for the provided order number

        System.out.println("querying for order!");

    }


    public void searchPackage(){
        //TODO query for the provided package number

        System.out.println("querying for package!");

    }


    public void searchAddress(){
        //TODO query for the provided address deliveries

        System.out.println("querying for delivery address!");

    }

    @Override
    public void assist() {
        System.out.println("Hello " + this.username + "! What are you searching for?");

        System.out.println("Press o to search for an order");
        System.out.println("Press p to search for a package");
        System.out.println("Press a to search for an address");
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
            default:
                System.out.println("Invalid Command");
                break;
        }
    }

}
