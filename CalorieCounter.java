import java.lang.annotation.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@ClassHeader (
    author = "CBrasil",
    date = "21/05/2015",
    lastModified = "19/09/2015"
)
public class CalorieCounter {
    protected static PrintWriter food;
    protected static Food breakfast = new Food(), lunch = new Food(), dinner = new Food(), snacks = new Food();
    protected static Scanner data;
    protected static int total;
    
    public static void main(String[]args) throws FileNotFoundException {
        retrieveData();
        total = breakfast.getTotal()+lunch.getTotal()+dinner.getTotal()+snacks.getTotal();
        JFrame frame = new CalorieCounterFrame("Calorie Counter");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    saveToFile();
                }
                catch  (FileNotFoundException ex) {}
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void retrieveData() throws FileNotFoundException {
        int[] storage = new int[4];
        
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
       breakfast.setTotal(storage[0]);
       lunch.setTotal(storage[1]);
       dinner.setTotal(storage[2]);
       snacks.setTotal(storage[3]);            
    }
    
    public static void saveToFile() throws FileNotFoundException {

        if(!(new File("food.txt").exists())) {
            food = new PrintWriter("food.txt");
            food.print("0 0 0 0");
            food.close();
        }
        
        food = new PrintWriter("food.txt");
        food.print(breakfast.getTotal()+" "+lunch.getTotal()+" "+dinner.getTotal()+" "+snacks.getTotal());
        food.close();
    }
    
    public static void resetValues() throws FileNotFoundException {
        breakfast = new Food();
        lunch = new Food();
        dinner = new Food();
        snacks = new Food();
        total = breakfast.getTotal()+lunch.getTotal()+dinner.getTotal()+snacks.getTotal();
        saveToFile();
    }
}
