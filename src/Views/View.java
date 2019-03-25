package Views;

/**
 * A generic view, meant for basically creating a dialogue between the user and the application
 *
 */

import java.util.Scanner;
import java.sql.*;

public abstract class View {

    public String email;
    public String username;

    public Scanner in;

    private boolean isActive;

    private Connection conn;


    public View(){
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    private void createConnection(String location, String user, String password) {
        try {

            //This needs to be on the front of your location
            String url = "jdbc:h2:" + location;

            //This tells it to use the h2 driver
            Class.forName("org.h2.Driver");

            //creates the connection
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            //You should handle this better
            e.printStackTrace();
        }
    }


    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet runQuery(String input){
        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(input);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public void run(){

        this.createConnection("./packageMain/packageMain", "me", "password");


        in = new Scanner(System.in);


        System.out.println("Hello! Thank you for using this application. Please log in using your username.");

        this.email = in.nextLine();
        //if email is in database
        this.username = email;

        this.assist();

        this.closeConnection();

        System.out.println("");
        System.out.println("Have a nice day!");
        System.out.println("");
    }

    public void quit() {
        this.isActive = false;
    }


    /**
     * Process the user depending on the application being used
     */
    public abstract void assist();

}
