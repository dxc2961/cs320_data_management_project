package Views;

public class ServiceEmployeeView extends View {

    public String custUsername;


    public ServiceEmployeeView(){
        super();
    }


    public void findProfile(){
        //TODO query other relevant profile information for the customer

        System.out.println("querying profile data for " + this.custUsername + "!");

    }


    public void findOrders(){
        //TODO query other relevant order information for this user

        System.out.println("querying order data for " + this.custUsername + "!");

    }


    public void findAddresses(){
        //TODO query other relevant profile information for this user

        System.out.println("querying order data for " + this.custUsername + "!");

    }


    public void findPayments(){
        //TODO query other relevant profile information for this user

        System.out.println("querying order data for " + this.custUsername + "!");

    }

    @Override
    public void assist(){

        System.out.println("Hello " + this.username + "! What customer are you assisting?");

        this.custUsername = in.nextLine();
        //if custusername is in database

        System.out.println("What would you like to do?");

        while(this.isActive) {
            System.out.println("Press p to view a user's profile");
            System.out.println("Press o to view a user's orders");
            System.out.println("Press a to view a user's addresses");
            System.out.println("Press y to view a user's payment methods");
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
