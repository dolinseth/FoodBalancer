import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WhoAmIScreen extends Screen{
    //FXML defined fields
    @FXML
    private Button iAmADonorButton;
    @FXML
    private Button iAmAManagerButton;
    @FXML
    private Button iAmAPantryButton;
    @FXML
    private Button iAmAClientButton;

    //non-FXML fields
    private AppController appController;

    @Override
    public void setup(){
        appController = AppController.getInstance();
    }

    //FXML defined button handlers
    public void donorHandler(){
        //load page with information about how to donate food, what food groups are lacking at your nearest food bank, etc

    }

    public void managerHandler(){
        //load inventory scene
        appController.setSceneToInventoryScreen();
    }

    public void pantryHandler(){
        //load inventory scene
        appController.setSceneToInventoryScreen();
    }

    public void clientHandler(){
        //load page with information about what is available at your nearest food bank

    }
}
