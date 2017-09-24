package old.caloriecounter;

/**
 * @deprecated as of 24/Sep/2017 TO BE REWRITTEN
 * This class is used to create the objects and instance variables required by
 * the CalorieCounter class.
 */

@Deprecated
class Food {
    private int totalCalories = 0;
    
    /**
     * Constructor: no parameters.
     */
    protected Food() {
        this.totalCalories = 0;
    }
    
    /**
     * Mutator method: adds value to calories.
     * @param newValue the value to be added.
     */
    protected void add(int newValue) {
        this.totalCalories += newValue;
    }
    
    /**
     * Accessor method: retrieves current value of totalCalories for meal.
     * @return current total calories entered.
     */
    protected int getTotal() {
        return this.totalCalories;
    }
    
}
