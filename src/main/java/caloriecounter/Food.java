package caloriecounter;

class Food {

    private int calories = 0;

    Food(int calories) {
       this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
