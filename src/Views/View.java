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

    private boolean signIn(String username){
        this.username = username;
        ResultSet result;
        try {
            result = this.runQuery("SELECT email FROM customer WHERE display_name=\'" + username + "\'");
            result.first();
            this.email = result.getString(1);
        } catch (SQLException e){
            return false;
        }
        return true;
    }


    public void run(){

        this.createConnection("./packageMain/packageMain", "me", "password");


        in = new Scanner(System.in);


        System.out.println("Hello! Thank you for using this application. Please log in using your username.");

        /*********FOR TESTING PURPOSES ONLY, COMMENTING OUT INPUTS**********/
        //while(!this.signIn(in.nextLine()))
        //    System.out.println("Invalid username. Please try again.");
        this.signIn("frederic");
        /****/

        System.out.println("Your email is " + this.email);

        this.assist();

        this.closeConnection();

        System.out.println("");
        System.out.println("Have a nice day!");
        System.out.println("");
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
