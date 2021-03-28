import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController {
    private static AppController appController;
    private Stage stage;
    private WhoAmIScreen whoAmIScreen;
    private Scene whoAmIScreenScene;
    private InventoryScreen InventoryScreen;
    private Scene inventoryScreenScene;
    private FoodBank currentFoodBank;
    //entries for each screen object type and their associated scenes would go here

    /**
     * default constructor, does nothing
     */
    private AppController(){}

    /**
     * get's the static instance of the AppController object and initializes it if it has not been initialized
     * @return - the singleton instance of the AppController class
     */
    public static AppController getInstance(){
        if(appController == null){
            appController = new AppController();
        }

        return appController;
    }

    /**
     * Sets the scene to the WhoAmIScreen
     */
    public void setSceneToWhoAmIScreen(){
        stage.setScene(whoAmIScreenScene);
    }

    /**
     * Sets the scene to the InventoryScreen
     */
    public void setSceneToInventoryScreen(){
        stage.setScene(inventoryScreenScene);
    }


    /*
    GETTERS AND SETTERS
    */

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public Stage getStage(){
        return stage;
    }

    public void setWhoAmIScreenScene(Scene s){
        whoAmIScreenScene = s;
    }

    public void setWhoAmIScreen(WhoAmIScreen wais){
        whoAmIScreen = wais;
    }

    public void setInventoryScreenScene(Scene s){
        inventoryScreenScene = s;
    }

    public void setInventoryScreen(InventoryScreen is){
        InventoryScreen = is;
    }

    public FoodBank getCurrentFoodBank(){
        return currentFoodBank;
    }
}
