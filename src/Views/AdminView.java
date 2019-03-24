package Views;

/**
 * This view is for the database administrators only.
 *
 * We have the ability to run our self-created SQL scripts, and we can also start a new instance of
 * our application. In this new instance (almost like a virtual machine) you can pretend to be a customer,
 * service employee, or shipping employee. This would allow us to troubleshoot problems with the application.
 */

public class AdminView extends View{


    public AdminView(){
        super();
    }


    public void sqlEditor(){
        //TODO allow user to write sql queries

        System.out.println("opening sql editor!");

    }


    public void newInstance(){

        System.out.println("creating new view!");

        View newView = null;

        System.out.println("Press c to run a customer view");
        System.out.println("Press e to run a customer service view");
        System.out.println("Press s to run a shipping department view");
        System.out.println("Press b to go back");
        char action = this.in.next().charAt(0);

        switch (action) {
            case 'c':
                newView = new CustomerView();
                break;
            case 'e':
                newView = new ServiceEmployeeView();
                break;
            case 's':
                newView = new ShippingEmployeeView();
                break;
        }
        if(newView != null) {
            newView.run();
            System.out.println("Welcome back, administrator");
            System.out.println();
        }

    }

    @Override
    public void assist() {

        System.out.println("Hello " + this.username + "! What would you like to do?");

        while(this.isActive) {
            System.out.println("Press s to craft SQL scripts");
            System.out.println("Press n to start a new application instance");
            System.out.println("Press q to quit");
            char action = this.in.next().charAt(0);

            switch (action) {
                case 's':
                    this.sqlEditor();
                    break;
                case 'n':
                    this.newInstance();
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
