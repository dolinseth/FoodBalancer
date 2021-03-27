import javafx.stage.Stage;

public class AppController {
    private static AppController appController;
    private Stage stage;
    //entries for each screen object type and their associated scenes would go here

    /**
     * default constructor, does nothing
     */
    private AppController(){}

    public static AppController getInstance(){
        if(appController == null){
            appController = new AppController();
        }

        return appController;
    }


}
