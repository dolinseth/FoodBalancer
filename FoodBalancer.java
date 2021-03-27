import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class FoodBalancer extends Application{
    private Stage stage;

    public static void main(String[] args){
        launch(args);
        // //code to test out JSON saving
        // FoodBank fb1 = new FoodBank("Blue Ridge Area Food Bank");
        // FoodBank fb2 = new FoodBank("Loaves & Fishes Food Pantry");

        // fb1.setLocation(new Coordinate(38.039690, -78.480940));
        // fb2.setLocation(new Coordinate(38.077940, -78.500490));

        // FoodItem beefaroni = new FoodItem("Beefaroni");
        // beefaroni.setCaloriesPerServing(350);
        // beefaroni.setCarbsPerServing(48);
        // beefaroni.setCholesterolPerServing(20);
        // beefaroni.setFatPerServing(13);
        // beefaroni.setServingsPerContainer(1);
        // beefaroni.setQuantity(45);
        // beefaroni.addFoodType(FoodItem.FoodType.MEAT);
        // beefaroni.addFoodType(FoodItem.FoodType.GRAIN);

        // FoodItem broth = new FoodItem("Swanson Chicken Broth");
        // broth.setCaloriesPerServing(20);
        // broth.setCarbsPerServing(2);
        // broth.setCholesterolPerServing(0);
        // broth.setFatPerServing(0);
        // broth.setServingsPerContainer(1);
        // broth.setQuantity(28);
        // broth.addFoodType(FoodItem.FoodType.NONE);

        // fb1.addFoodItem(beefaroni);
        // fb2.addFoodItem(broth);

        // FoodBankList fbl = new FoodBankList();
        // fbl.addFoodBank(fb1);
        // fbl.addFoodBank(fb2);

        // JSONFileHandler.getJFH().saveToJSON(fbl.toJSON(), "FoodBanks.json");

        // FoodBankList importedfbl = new FoodBankList(JSONFileHandler.getJFH().getJSONFromFile("FoodBanks.json"));
        // ArrayList<FoodBank> foodBanks = importedfbl.getFoodBanks();
        // foodBanks.forEach(fb -> {
        //     System.out.println("Imported food bank with name: " + fb.getName());
        // });

        // // FoodBank fb3 = importedfbl.getFoodBank("Blue Ridge Area Food Bank");
        // // FoodItem fi1 = fb3.getFoodList().getFoodItems().get(0);
        // // System.out.println("fi1 name: " + fi1.getName());
    }


    public void start(Stage stage){
        //load FXML
        //each screen requires the following
            //a "Parent screenNameRoot" object to hold the controller for each screen
            //a "ScreenName screenName" object of the ScreenName class for each screen that exists
            //a "FXMLLoader screenNameLoader = new FXMLLoader(this.getClass().getResource("fxml/fxmlFileName.fxml"));" object to load the controller from FXML
            //an entry in the try/catch block that does the following (should catch IOException)
                //sets the screenNameRoot object to screenNameLoader.load()
                //sets the screenName object to screenNameLoader.getController()

        stage.setTitle("Potluck Main Menu");
    }
}