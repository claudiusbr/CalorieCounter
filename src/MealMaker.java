import java.lang.annotation.*;
import java.util.*;
import java.io.*;

/** This class is responsible for creating the relevant meal
 *  instances for the CalorieCounter
 */
public class MealMaker {
    private Food breakfast = new Food(); 
    private Food lunch = new Food();
    private Food dinner = new Food();
    private Food snacks = new Food();

    private PrintWriter food;

    private Scanner data;
    private int total;

    public MealMaker() {
        try {
            retrieveData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        total = breakfast.getTotal()
            +lunch.getTotal()
            +dinner.getTotal()
            +snacks.getTotal();
    }

    public int getBreakfast() {
        return breakfast.getTotal();
    }
    public void addBreakfast(int value) {
        breakfast.add(value);
        total+=value;
        try { saveToFile(); } catch (FileNotFoundException ex) {}
    }

    public int getLunch() {
        return lunch.getTotal();
    }
    public void addLunch(int value) {
        lunch.add(value);
        total+=value;
        try { saveToFile(); } catch (FileNotFoundException ex) {}
    }

    public int getDinner() {
        return dinner.getTotal();
    }
    public void addDinner(int value) {
        dinner.add(value);
        total+=value;
        try { saveToFile(); } catch (FileNotFoundException ex) {}
    }

    public int getSnacks() {
        return snacks.getTotal();
    }
    public void addSnacks(int value) {
        snacks.add(value);
        total+=value;
        try { saveToFile(); } catch (FileNotFoundException ex) {}
    }

    public int getTotal() {
        return total;
    }

    public void retrieveData() throws FileNotFoundException {
        int[] storage = new int[4];
        
        // if food.txt doesn't exist, create one
        if(!(new File("food.txt").exists())) {
            food = new PrintWriter("food.txt");
            food.print("0 0 0 0");
            food.close();
        }
        
        //import data from file into variable
        data = new Scanner(new File("food.txt"));
        
        //populate array with values from file
        for (int i = 0; data.hasNextInt() && i < storage.length; ++i) {
            storage[i] = data.nextInt();
        }
        data.close();
              
        //assign values to objects based on array
        breakfast.add(storage[0]);
        lunch.add(storage[1]);
        dinner.add(storage[2]);
        snacks.add(storage[3]);        
    }

    public void saveToFile() throws FileNotFoundException {
        if(!(new File("food.txt").exists())) {
            food = new PrintWriter("food.txt");
            food.print("0 0 0 0");
            food.close();
        }
        food = new PrintWriter("food.txt");
        food.print(breakfast.getTotal()+" "+lunch.getTotal()+" "+dinner.getTotal()+" "+snacks.getTotal());
        food.close();
    }
        
    public void resetValues() throws FileNotFoundException {
        breakfast = new Food();
        lunch = new Food();
        dinner = new Food();
        snacks = new Food();
        total = breakfast.getTotal()+lunch.getTotal()+dinner.getTotal()+snacks.getTotal();
        saveToFile();
    }
}
