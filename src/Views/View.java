package Views;

/**
 * A generic view, meant for basically creating a dialogue between the user and the application
 *
 */

import java.util.Scanner;

public abstract class View {

    public String email;
    public String username;

    public Scanner in;

    public boolean isActive;


    public View(){
        isActive = true;
    }




    public void run(){

        in = new Scanner(System.in);


        System.out.println("Hello! Thank you for using this application. Please log in using your email.");

        this.email = in.nextLine();
        //if email is in database
        this.username = "get username!";

        this.assist();



        System.out.println("");

        System.out.println("Have a nice day!");



        System.out.println("");


    }

    public void quit() {
        this.isActive = false;
    }


    /**
     * Process the user depending on the application being used
     * @return
     */
    public abstract void assist();

}
