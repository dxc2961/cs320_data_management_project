package Views;

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
        char app = input.next().charAt(0);

        switch (app) {
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



    }
}
