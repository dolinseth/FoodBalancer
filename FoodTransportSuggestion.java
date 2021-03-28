public class FoodTransportSuggestion{
    protected FoodBank source;
    protected FoodBank destination;
    protected FoodList foods;

    /**
     * default constructor, takes in source and destination food banks
     * @param source - the FoodBank that will supply the food to the destination bank
     * @param destination - the FoodBank that will receive the food
     */
    public FoodTransportSuggestion(FoodBank source, FoodBank destination){
        this.source = source;
        this.destination = destination;
        foods = new FoodList();
    }

    /*
    GETTERS AND SETTERS
    */

    public FoodBank getSource(){
        return source;
    }

    public FoodBank getDestination(){
        return destination;
    }

    public FoodList getFoods(){
        return foods;
    }

    public void setSource(FoodBank source){
        this.source = source;
    }

    public void setDestination(FoodBank destination){
        this.destination = destination;
    }

    public void addFoodItem(FoodItem fi){
        foods.addFoodItem(fi);
    }
}