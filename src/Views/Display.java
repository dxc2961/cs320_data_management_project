package Views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Display extends Application {

    private final static Font header  = new Font("Arial", 30);
    private final static Font text = new Font("Arial",12);
    private boolean isSignedIn = false;
    private char applicationType;
    private Label welcome;
    private Button signInButton;

    private static View view;


    /*public void init() {
        applicationType = this.getParameters().getRaw().get(0).charAt(0);
    }*/


    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();



        //welcome message at top
        welcome = new Label("Welcome");
        welcome.setFont(header);
        welcome.setAlignment(Pos.CENTER);
        mainPane.setTop(welcome);



        //query result area

        //control panel on right
        //buttons on top right
        signInButton = new Button("Sign In");
        Button homeButton = new Button("Home");
        Button quitButton = new Button("Quit");
        Button helpButton = new Button("Help");

        //set up button event handlers
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!isSignedIn)
                    signIn("frederic");
                else
                    signOut();
            }
        });
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
            }
        });
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        //add buttons to VBox
        VBox controlPanel = new VBox(signInButton, homeButton, quitButton, helpButton);
        controlPanel.setPrefHeight(2000);
        controlPanel.setMaxHeight(Double.MAX_VALUE);
        controlPanel.setAlignment(Pos.CENTER);
        //configure buttons
        for(Node b : controlPanel.getChildren()){
            ((Button) b).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            ((Button) b).setFont(text);
        }

        mainPane.setRight(controlPanel);



        //action panel on bottom



        //render view
        Scene view = new Scene(mainPane);

        primaryStage.setTitle( "Database" );
        primaryStage.setScene(view);
        primaryStage.show();
    }

    public void signIn(String username){
        this.isSignedIn = true;
        this.welcome.setText("Welcome " + username);
        signInButton.setText("Sign Out");
    }

    public void signOut(){
        this.isSignedIn = false;
        this.welcome.setText("Welcome");
        signInButton.setText("Sign In");
    }


    public static void run( String[] app, View thisView) {
        view = thisView;
        Application.launch(app[0]);
    }
}
