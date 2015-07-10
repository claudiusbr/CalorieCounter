import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Simple Calorie Counter
 * 07/06/15 - v1: linear calorie counter (deleted);
 * 13/06/15 - v2: still linear; included inheritance and specialisation;
 * 14/06/15 - v3: changed from linear to multiple choice; Reduced number of subclasses and scrapped subclasses;
 * 24/06/15 - v4:  
 *  .1 - changed 'option' to string: trying to control exceptions by limiting value of  to characters '1,2,3,4' contained in a String;
 *  .2 - modified way of clearing totals from object references: now done by using the no argument constructor of class Food;
 *  .3 - array 'storage' no longer to be used to store values before saving. Printing values from instances of Food directly to save files instead.
 * Next steps: 
 *  Learn how to develop a user interface;
 */

public class CalorieCounter {
    public static void main(String[]args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in), data;
        int[] storage = new int[4];
        String A;
        PrintWriter food;
        
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
        
        //construct objects for calories from Food.total:
        //1 = breakfast, 2 = lunch, 3 = dinner, 4 = snacks
        Food breakfast = new Food(), lunch = new Food(), dinner = new Food(), snacks = new Food();
        
        //assign values to objects based on array
        breakfast.setTotal(storage[0]);
        lunch.setTotal(storage[1]);
        dinner.setTotal(storage[2]);
        snacks.setTotal(storage[3]);
        
        //prompt user for values
        System.out.println("Please choose a meal to enter calories:\n(Please enter the number corresponding to the meal and press 'ENTER')");
        do {
            String option = "0";
            System.out.println("1 - Breakfast;\n2 - Lunch;\n3 - Dinner;\n4 - Snacks;\n");
            while(!"1234".contains(option)) {
                option = in.next();
            }
            
            switch (option) { 
                case "1": System.out.println("Please enter value for Breakfast:"); breakfast.setTotal(in.nextInt()); break;
                case "2": System.out.println("Please enter value for Lunch:"); lunch.setTotal(in.nextInt()); break;
                case "3": System.out.println("Please enter value for Dinner:"); dinner.setTotal(in.nextInt()); break;
                case "4": System.out.println("Please enter value for Snacks:"); snacks.setTotal(in.nextInt()); break;
                default: System.out.println("Invalid Option"); break;
            }
            
            do {
                System.out.println("Enter another meal?\nPlease answer Y/N:");
                A = in.next();
                A = A.toUpperCase();
            } while (!(A.equals("Y")||A.equals("N")));
            
        } while ((A.equals("Y")));
        
        System.out.print("\nTotals: \nBreakfast: "+breakfast.getTotal()+"\nLunch: "+lunch.getTotal()+"\nDinner: "+dinner.getTotal()+"\nSnacks: "+snacks.getTotal()+"\n");
        System.out.println("Total calories consumed: "+(breakfast.getTotal()+lunch.getTotal()+dinner.getTotal()+snacks.getTotal()));
        
        System.out.println("\nReset totals? ");
        do {
            System.out.print("(Please answer Y/N)\n");
            A = in.next();
            A = A.toUpperCase();
        } while (!((A.equals("Y")) || A.equals("N")));

        //v4.2clear totals from instance variables by setting them to 0;
        if (A.equals("Y")) {
            breakfast = new Food();
            lunch = new Food();
            dinner = new Food();
            snacks = new Food();
            System.out.println("Totals calories consumed: "+(breakfast.getTotal()+lunch.getTotal()+dinner.getTotal()+snacks.getTotal()));
        }
        
        //v4.3 save object field (attribute) values to saved files
        food = new PrintWriter("food.txt");
        food.print(breakfast.getTotal()+" "+lunch.getTotal()+" "+dinner.getTotal()+" "+snacks.getTotal());
        food.close();
    }
}

