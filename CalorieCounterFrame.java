import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

/**
 * This class creates the frame and components of the Calorie Counter app.
 * 
 */
public class CalorieCounterFrame extends JFrame
{
    //decided to use specific variables for buttons instead of array,
    //so that the code is more self-documented
    private JButton breakfastButton = new JButton("+"), lunchButton = new JButton("+"), 
        dinnerButton = new JButton("+"), snacksButton = new JButton("+"), 
        resetButton = new JButton("Reset");

    private final int FRAME_WIDTH = 300, FRAME_HEIGHT = 450, FIELD_WIDTH = 4;
    
    
    protected JTextField breakfastValue = new JTextField(Integer.toString(CalorieCounter.breakfast.getTotal()),FIELD_WIDTH);
    protected JTextField lunchValue = new JTextField(Integer.toString(CalorieCounter.lunch.getTotal()),FIELD_WIDTH);
    protected JTextField dinnerValue = new JTextField(Integer.toString(CalorieCounter.dinner.getTotal()),FIELD_WIDTH); 
    protected JTextField snacksValue = new JTextField(Integer.toString(CalorieCounter.snacks.getTotal()),FIELD_WIDTH);

    private JLabel breakfastLabel = new JLabel("Breakfast: ");
    private JLabel lunchLabel = new JLabel("Lunch: ");
    private JLabel dinnerLabel = new JLabel("Dinner: ");
    private JLabel snacksLabel = new JLabel("Snacks: ");
    protected JLabel totalLabel = new JLabel("Total: "+CalorieCounter.total);

    private ActionListener bfBtnListener, lcBtnListener, dnrBtnListener, 
        snBtnListener, resetListener;
        
    private FocusListener bfTxtListener, lcTxtListener, dnrTxtListener, snTxtListener;
    
    public CalorieCounterFrame(String title) {
        this.setTitle(title);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        JPanel panel = new JPanel();
        createActionListeners();
        addActionListeners();
        panel.add(breakfastLabel);
        panel.add(breakfastValue);
        panel.add(breakfastButton);
        
        
        
        panel.add(totalLabel);
        panel.add(resetButton);
        
        add(panel);
    }
    
    /**
     * Creates the action and focus listener objects.
     */
    public void createActionListeners() {
        bfBtnListener = new CalorieListener(CalorieCounter.breakfast,breakfastValue);
        bfTxtListener = new TextListener(breakfastValue);
        lcBtnListener = new CalorieListener(CalorieCounter.lunch,lunchValue);
        lcTxtListener = new TextListener(lunchValue);
        dnrBtnListener = new CalorieListener(CalorieCounter.dinner,dinnerValue);
        dnrTxtListener = new TextListener(dinnerValue);
        snBtnListener = new CalorieListener(CalorieCounter.snacks,snacksValue);
        snTxtListener = new TextListener(snacksValue);
        resetListener = new ResetListener();
    }
    
    /**
     * adds action and focus listener objects to the frame buttons.
     */
    public void addActionListeners() {
        breakfastButton.addActionListener(bfBtnListener);
        breakfastValue.addFocusListener(bfTxtListener);
        lunchButton.addActionListener(lcBtnListener);
        lunchValue.addFocusListener(lcTxtListener);
        dinnerButton.addActionListener(dnrBtnListener);
        dinnerValue.addFocusListener(dnrTxtListener);
        snacksButton.addActionListener(snBtnListener);
        snacksValue.addFocusListener(snTxtListener);
        resetButton.addActionListener(resetListener);
    }
    
    /**
     * event listener for the buttons used to add the meal values to the Food objects
     */
    class CalorieListener implements ActionListener {
        Food meal;
        JTextField text;
        
        public CalorieListener (Food meal, JTextField text) {
            this.meal = meal;
            this.text = text;
        }
        
        public void actionPerformed(ActionEvent e) {
            int value = Integer.parseInt(text.getText());
            meal.setTotal(value);
            CalorieCounter.total += value;
            text.setText(Integer.toString(meal.getTotal()));
            totalLabel.setText("Total: "+CalorieCounter.total);
        }
    }
    
    /**
     * focus listener to change state of text field
     */
    class TextListener implements FocusListener{
        JTextField text;
        
        public TextListener(JTextField text) {
            this.text = text;
        }
        
        public void focusGained(FocusEvent e) {
            text.setEditable(true);
            text.setText("");
        }
        
        public void focusLost(FocusEvent e) {
            text.setEditable(false);
        }
    }
    
    /**
     * action listener for reset button.
     */
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                CalorieCounter.resetValues();
            } catch (java.io.FileNotFoundException ex) {}
            resetTextFields();
        }
        
        public void resetTextFields() {
            createActionListeners();
            totalLabel.setText(Integer.toString(CalorieCounter.total));
            breakfastValue.setText("0");
            lunchValue.setText("0");
            dinnerValue.setText("0");
            snacksValue.setText("0");
            System.out.println(CalorieListener.this.bfBtnListener.meal.getTotal());
        }
    }
}
