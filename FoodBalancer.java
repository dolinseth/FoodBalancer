import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FoodBalancer extends Application{
    //app controller reference for easy access within this class
    private AppController appController;

    //main function, just calls launch(args) to start the JavaFX application
    public static void main(String[] args){
        launch(args);
        //this is some code that tests out the JSON saving and importing of data
        // FoodBank fb1 = new FoodBank("Blue Ridge Area Food Bank");
        // FoodBank fb2 = new FoodBank("Loaves & Fishes Food Pantry");

        // fb1.setLocation(new Coordinate(38.039690, -78.480940));
        // fb2.setLocation(new Coordinate(38.077940, -78.500490));

        // System.out.println("Distance between fb1 and fb2 is " + fb1.getLocation().getDistanceTo(fb2.getLocation()));

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
    }

    /**
     * start is the function that JavaFX calls to initialize the application, it is called by the hidden launch(args) method that is called by main
     */
    public void start(Stage stage){
        //set the class fields for the stage and app controller
        this.appController = AppController.getInstance();
        appController.setStage(stage);
        //load FXML
        //each screen requires the following
            //a "Parent screenNameRoot" object to hold the controller for each screen
            //a "ScreenName screenName" object of the ScreenName class for each screen that exists
            //a "FXMLLoader screenNameLoader = new FXMLLoader(this.getClass().getResource("fxml/fxmlFileName.fxml"));" object to load the controller from FXML
            //an entry in the try/catch block that does the following (should catch IOException)
                //sets the screenNameRoot object to screenNameLoader.load()
                //sets the screenName object to screenNameLoader.getController()
        Parent whoAmIScreenRoot = null;
        FXMLLoader whoAmIScreenLoader = new FXMLLoader(this.getClass().getResource("fxml/whoAreYouScene.fxml"));
        WhoAmIScreen whoAmIScreen = null;
        Parent inventoryScreenRoot = null;
        FXMLLoader inventoryScreenLoader = new FXMLLoader(this.getClass().getResource("fxml/inventoryScene.fxml"));
        InventoryScreen inventoryScreen = null;

        try{                
            whoAmIScreenRoot = whoAmIScreenLoader.load();
            whoAmIScreen = whoAmIScreenLoader.getController();
            inventoryScreenRoot = inventoryScreenLoader.load();
            inventoryScreen = inventoryScreenLoader.getController();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to load FXML");
            System.exit(1);
        }

        //set the title for the window (not super important since this application would be embedded in a web page where this wouldn't be visible, but done anyway for posterity)
        stage.setTitle("Potluck Main Menu");

        //create scenes for each screen
        Scene whoAmIScreenScene = new Scene(whoAmIScreenRoot);
        Scene inventoryScreenScene = new Scene(inventoryScreenRoot);


        //give AppController references to scenes and objects
        appController.setWhoAmIScreen(whoAmIScreen);
        appController.setWhoAmIScreenScene(whoAmIScreenScene);
        appController.setInventoryScreen(inventoryScreen);
        appController.setInventoryScreenScene(inventoryScreenScene);

        //set stage's current scene to the WhoAmIScreenScene cause that's the default
        stage.setScene(whoAmIScreenScene);

        //must be final call in the function, tells JavaFX to start the app
        stage.show();
    }

    /**
     * constructs a list of FoodTransportSuggestions based on the surpluses and deficits at food banks in the list
     * @param fbl - the food bank list to construct the suggestions for
     * @return - an ArrayList of FoodTransportSuggestions
     */
    public ArrayList<FoodTransportSuggestion> getFoodTransportSuggestions(FoodBankList fbl){
        ArrayList<FoodTransportSuggestion> suggestions = new ArrayList<FoodTransportSuggestion>();
        FoodBankList surplusBanks = new FoodBankList();
        FoodBankList deficitBanks = new FoodBankList();
        fbl.getFoodBanks().forEach(fb -> {
            if(fb.calculateSurplusCalories() > 0){
                surplusBanks.addFoodBank(fb);
            }
            else{
                deficitBanks.addFoodBank(fb);
            }
        });

        surplusBanks.getFoodBanks().forEach(fb -> {
            FoodBank nearest = fb.getNearestFoodBank(deficitBanks);
            int surplus = fb.calculateSurplusCalories();
            int deficit = nearest.calculateSurplusCalories();
            while(surplus > 0 && deficit < 0){
                surplus = fb.calculateSurplusCalories();
                deficit = nearest.calculateSurplusCalories();
                FoodTransportSuggestion fts = new FoodTransportSuggestion(fb, nearest);
                FoodItem mostAbundantAtSource = fb.getMostAbundantItem();
                int mostAbundantCalories = mostAbundantAtSource.getQuantity()*mostAbundantAtSource.getTotalCalories();
                if(mostAbundantCalories > surplus || mostAbundantCalories > (deficit * -1)){
                    int quantityAvailableInSurplus = (int)Math.floor(surplus / mostAbundantAtSource.getTotalCalories());
                    int quantityRequiredForDeficit = (int)Math.floor((deficit * -1) / mostAbundantAtSource.getTotalCalories());
                    mostAbundantAtSource.setQuantity(Math.min(quantityAvailableInSurplus, quantityRequiredForDeficit));
                    surplus -= mostAbundantAtSource.getTotalCalories() * mostAbundantAtSource.getQuantity();
                    deficit += mostAbundantAtSource.getTotalCalories() * mostAbundantAtSource.getQuantity();
                }
                fts.addFoodItem(mostAbundantAtSource);
                suggestions.add(fts);
                fbl.applyFoodTransportSuggestion(fts);
            }
        });

        return suggestions;
    }
}