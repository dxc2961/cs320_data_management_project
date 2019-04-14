package Views;

/**
 * This class is just used to allow someone to access the application they choose. In a business scenario we
 * would deploy each application only where appropriate, so this class would be unnecessary.
 *
 * For now we can use this to test the different applications' functionality
 */

import java.util.Scanner;

public class RunApplication {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        View view;

        System.out.println("Hi there! What application will you use?");
        System.out.println("Press c for customer");
        System.out.println("Press e for customer service employees");
        System.out.println("Press s for shipping employees");
        System.out.println("Press a for database administrators");

        /*********FOR TESTING PURPOSES ONLY, COMMENTING OUT INPUTS**********/
        //char action = input.nextLine().charAt(0);
        //input.nextLine();
        char action = 'c';
        /****/

        switch (action) {
            case 'c':
                view = new CustomerView();
                break;
            case 'e':
                view = new ServiceEmployeeView();
                break;
            case 's':
                view = new ShippingEmployeeView();
                break;
            case 'a':
                view = new AdminView();
                break;
            default:
                view = new CustomerView();
                break;
        }

        view.run();
        //Display.run(appArray, view);

    }
}
