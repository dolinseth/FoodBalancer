public class FoodRequirements {
    public int totalPeopleInArea;

    /**
     * default constructor
     * @param totalPeopleInArea - the total people in the area of the food bank that live below the poverty line and thus will be expected to require food
     */
    public FoodRequirements(int totalPeopleInArea){
        this.totalPeopleInArea = totalPeopleInArea;
    }

    /*
    GETTERS AND SETTERS
    */

    public void setTotalPeopleInArea(int totalPeopleInArea){
        this.totalPeopleInArea = totalPeopleInArea;
    }

    public int getTotalPeopleInArea(){
        return totalPeopleInArea;
    }
}
