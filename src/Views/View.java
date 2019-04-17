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


    public ResultSet runQuery(String input) throws SQLException{
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return statement.executeQuery(input);
    }


    public void runUpdate(String input) throws SQLException{
        Statement statement = conn.createStatement();
        statement.executeUpdate(input);
    }

    private boolean signIn(String email, String pass){
        this.email = email;
        ResultSet result;
        try {
            result = this.runQuery("SELECT display_name, password FROM customer WHERE email=\'" + this.email + "\'");
            result.first();
            this.username = result.getString(1);

            if (!pass.equals(result.getString(2))) {
                System.out.println("Incorrect password");
                return false;
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Incorrect Email");
            return false;

        }
        return true;
    }


    public void run(){

        this.createConnection("./packageMain/packageMain", "me", "password");


        in = new Scanner(System.in);


        System.out.println("Hello! Thank you for using this application. Please log in using your email.");
        String email = in.nextLine();

        System.out.println("Please enter your password");
        String password = in.nextLine();

        while(!this.signIn(email, password)) {
            System.out.println("Please enter your email");
            email = in.nextLine();
            System.out.println("Please enter your password");
            password = in.nextLine();
        }

        this.assist();

        this.closeConnection();

        System.out.println("Have a nice day!");
    }

    public void quit() {
        this.isActive = false;
    }


    public static boolean isNumeric(String in){
        try {
            for(int i = 0; i < in.length(); i++)
                Integer.parseInt(in.substring(i,i+1));
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static String printPhoneNumber(String pnum){
        return pnum.substring(0,3) + "-" + pnum.substring(3,6) + "-" + pnum.substring(6);
    }


    /**
     * Process the user depending on the application being used
     */
    public abstract void assist();

}
