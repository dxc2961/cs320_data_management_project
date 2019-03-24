package Views;

public class AdminView extends View{


    public AdminView(){
        super();
    }


    public void sqlEditor(){


    }


    public void newInstance(){


    }

    @Override
    public void assist() {

        System.out.println("Hello " + this.username + "! What would you like to do?");

        System.out.println("Press s to craft SQL scripts");
        System.out.println("Press n to start a new application instance");
        char action = in.next().charAt(0);

        switch (action) {
            case 's':
                this.sqlEditor();
                break;
            case 'n':
                this.newInstance();
                break;
            default:
                System.out.println("Invalid Command");
                break;
        }

    }
}
