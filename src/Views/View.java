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

    public boolean isActive;

    private Connection conn;


    public View(){
        isActive = true;
    }


    public void createConnection(String location, String user, String password) {
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


    public void closeConnection() {
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


    public void printResults(ResultSet results){
        try {
            while (results.next()) {
                System.out.printf("\tAddress %d: %s %s %s %s %s %s\n",
                        results.getInt(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        results.getString(6),
                        results.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void run(){

        this.createConnection("./packageMain/packageMain", "me", "password");


        in = new Scanner(System.in);


        System.out.println("Hello! Thank you for using this application. Please log in using your email.");

        this.email = in.nextLine();
        //if email is in database
        this.username = "get username!";

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
