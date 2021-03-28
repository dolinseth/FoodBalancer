import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InventoryScreen extends Screen{
    //FXML defined fields
    @FXML
    private Button addItemToInventoryButton;
    @FXML
    private Button updateQuantitiesInInventoryButton;
    @FXML
    private Label totalCaloriesLabel;
    @FXML
    private Label numPeopleSupportableLabel;
    @FXML
    private Label foodGroupCaloriesLabel;

    //non-FXML fields
    AppController appController;


    @Override
    public void setup(){
        appController = AppController.getInstance();
        FoodBank fb = appController.getCurrentFoodBank();
        int totalCalories = fb.getFoodList().getTotalCalories();
        int totalCarbs = fb.getFoodList().getTotalCarbs();
        int totalFat = fb.getFoodList().getTotalFat();
        int numPeopleSupportable = totalCalories / 2000;

        String tcText = String.format("Total calories in your inventory: %d\nTotal carbs in your inventory %d\nTotal fat in your inventory: %d", totalCalories, totalCarbs, totalFat);
        totalCaloriesLabel.setText(tcText);
        numPeopleSupportableLabel.setText("Total number of people-days worth of calories available in your inventory: " + numPeopleSupportable);

        HashMap<String, Integer> fgc = fb.getFoodList().getFoodGroupCalories();
        String fgcText = String.format("Total calories in Fats, Oils, & Sweets: %d\nTotal calories in Dairy: %d\nTotal calories in Meat: %d\nTotal calories in Vegetables: %d\nTotal calories in Fruits: %d\nTotal calories in Grain: %d\nTotal uncategorized calories: %d", 
                                        fgc.get("FATS_OILS_SWEETS"), fgc.get("DAIRY"), fgc.get("MEAT"), fgc.get("VEGETABLE"), fgc.get("FRUIT"), fgc.get("GRAIN"), fgc.get("NONE"));
        foodGroupCaloriesLabel.setText(fgcText);
    }

    //FXML defined button handlers
    public void addItemHandler(){
        //load a new page where information about a food item can be input

    }

    public void updateQuantitiesHandler(){
        //load a new page that shows your inventory as a list and allows you to edit quantities

    }
}
