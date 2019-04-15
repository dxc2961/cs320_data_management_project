package Views;

/**
 * This view is for the database administrators only.
 *
 * We have the ability to run our self-created SQL scripts, and we can also start a new instance of
 * our application. In this new instance (almost like a virtual machine) you can pretend to be a customer,
 * service employee, or shipping employee. This would allow us to troubleshoot problems with the application.
 */

import java.sql.*;
import java.util.ArrayList;


public class AdminView extends View{

    ArrayList<String> previousScripts;

    public AdminView(){
        super();
    }


    private void sqlEditor(){
        //TODO allow user to write their own custom sql queries

        boolean done = false;
        String input;
        while(true) {
            System.out.println("Enter the script you would like to run (b to go back)");
            input = in.nextLine();
            if(input.equals("b"))
                break;
            String[] split = input.split(" ");

            try {
                if (split[0].equalsIgnoreCase("select")) {
                    ResultSet results = this.runQuery(input);
                    ResultSetMetaData metadata = results.getMetaData();

                    System.out.println("Results from query:");

                    int ccount = metadata.getColumnCount();

                    String[] columnHeaders = new String[ccount];

                    for(String s : columnHeaders){
                    }

                    for(int i = 1; i <= metadata.getColumnCount(); i++){


                    }

                    while(results.next()){

                        for(int i = 1; i <= metadata.getColumnCount(); i++){


                        }

                    }


                }
                else {
                    this.runUpdate(input);
                    System.out.println(split[0] + " completed successfully");
                }


            } catch (SQLException s){
                System.out.println(s.getMessage());
            }
        }
    }


    private void newInstance(){

        System.out.println("creating new view!");

        View newView = null;

        System.out.println("Press c to run a customer view");
        System.out.println("Press e to run a customer service view");
        System.out.println("Press s to run a shipping department view");
        System.out.println("Press b to go back");
        char action = this.in.next().charAt(0);
        in.nextLine();

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

        while(this.isActive()) {
            System.out.println("Press s to craft SQL scripts");
            System.out.println("Press n to start a new application instance");
            System.out.println("Press q to quit");
            char action = this.in.next().charAt(0);
            in.nextLine();

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
