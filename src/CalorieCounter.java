import java.lang.annotation.*;
import java.util.*;
import java.io.*;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** 
*    author = "CBrasil",
*    date = "21/05/2015",
*    lastModified = "19/09/2015"
*/
public class CalorieCounter {
    public static void main(String[]args) throws FileNotFoundException {
            
        JFrame frame = new CalorieCounterFrame("Calorie Counter");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
