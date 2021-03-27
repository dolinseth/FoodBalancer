import FoodItem.FoodType;

public class FoodBalancer{
    public static void main(String[] args){
        FoodBank fb1 = new FoodBank("Blue Ridge Area Food Bank");
        FoodBank fb2 = new FoodBank("Loaves & Fishes Food Pantry");

        fb1.setLocation(new Coordinate(38.039690, -78.480940));
        fb2.setLocation(new Coordinate(38.077940, -78.500490));

        FoodItem beefaroni = new FoodItem("Beefaroni");
        beefaroni.setCaloriesPerServing(350);
        beefaroni.setCarbsPerServing(48);
        beefaroni.setCholesterolPerServing(20);
        beefaroni.setFatPerServing(13);
        beefaroni.setServingsPerContainer(1);
        beefaroni.setQuantity(45);
        beefaroni.addFoodType(FoodItem.FoodType.MEAT);
        beefaroni.addFoodType(FoodItem.FoodType.GRAIN);

        FoodItem broth = new FoodItem("Swanson Chicken Broth");
        broth.setCaloriesPerServing(20);
        broth.setCarbsPerServing(2);
        broth.setCholesterolPerServing(0);
        broth.setFatPerServing(0);
        broth.setServingsPerContainer(1);
        broth.setQuantity(28);
        broth.addFoodType(FoodItem.FoodType.NONE);

        fb1.addFoodItem(beefaroni);
        fb2.addFoodItem(broth);

        FoodBankList fbl = new FoodBankList();
        fbl.addFoodBank(fb1);
        fbl.addFoodBank(fb2);

        JSONFileHandler.getJFH().saveToJSON(fbl.toJSON(), "FoodBanks.json");
    }
}