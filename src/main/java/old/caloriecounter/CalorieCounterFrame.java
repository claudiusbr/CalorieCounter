package old.caloriecounter;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import java.util.regex.Pattern;

@Deprecated
public class CalorieCounterFrame extends JFrame
{

    private MealMaker meals = new MealMaker();

    private final int FRAME_WIDTH = 350, FRAME_HEIGHT = 300, FIELD_WIDTH = 2;

    //decided to use specific variables for buttons instead of array,
    //so that the code is more self-documented
    private JButton breakfastButton = new JButton("+"); 
    private JButton lunchButton = new JButton("+");
    private JButton dinnerButton = new JButton("+");
    private JButton snacksButton = new JButton("+");
    private JButton resetButton = new JButton("Reset");
    
    private JTextField breakfastText = new JTextField("0",FIELD_WIDTH);
    private JTextField lunchText = new JTextField("0",FIELD_WIDTH);
    private JTextField dinnerText = new JTextField("0",FIELD_WIDTH); 
    private JTextField snacksText = new JTextField("0",FIELD_WIDTH);

    private JLabel breakfastLabel = new JLabel("Breakfast: "+meals.getBreakfast());
    private JLabel lunchLabel = new JLabel("Lunch: "+meals.getLunch());
    private JLabel dinnerLabel = new JLabel("Dinner: "+meals.getDinner());
    private JLabel snacksLabel = new JLabel("Snacks: "+meals.getSnacks());
    private JLabel totalLabel = new JLabel("Total: "+meals.getTotal());

    private ActionListener resetListener = new ResetListener();
        
    private FocusListener bfTxtListener = new TextListener(breakfastText); 
    private FocusListener lcTxtListener = new TextListener(lunchText); 
    private FocusListener dnrTxtListener = new TextListener(dinnerText); 
    private FocusListener snTxtListener = new TextListener(snacksText);
    
    public CalorieCounterFrame(String title) {
        // create frame
        this.setTitle(title);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //create panel and layout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        panel.setLayout(layout);
        
        // add action listeners
        addActionListeners();

        // make sure labels are of the same size
        layout.linkSize(SwingConstants.VERTICAL,breakfastLabel,lunchLabel,dinnerLabel,snacksLabel);

        // set components (buttons, labels, textfields) in a grid format
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(breakfastLabel)
                    .addComponent(lunchLabel)
                    .addComponent(dinnerLabel)
                    .addComponent(snacksLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(breakfastText)
                    .addComponent(lunchText)
                    .addComponent(dinnerText)
                    .addComponent(snacksText)
                    .addComponent(totalLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(breakfastButton)
                    .addComponent(lunchButton)
                    .addComponent(dinnerButton)
                    .addComponent(snacksButton)
                    .addComponent(resetButton))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(breakfastLabel)
                    .addComponent(breakfastText)
                    .addComponent(breakfastButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lunchLabel)
                    .addComponent(lunchText)
                    .addComponent(lunchButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dinnerLabel)
                    .addComponent(dinnerText)
                    .addComponent(dinnerButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(snacksLabel)
                    .addComponent(snacksText)
                    .addComponent(snacksButton))
                .addComponent(totalLabel)
                .addComponent(resetButton)
        );

        // add the panel to CalorieCounterFrame
        add(panel);
    }
    
    /**
     * adds action and focus listener objects to the frame buttons.
     */
    public void addActionListeners() {
        breakfastButton.addActionListener((ActionEvent e) -> {
            if (isNumber(breakfastText.getText())) {
                int value = Integer.parseInt(breakfastText.getText());
                meals.addBreakfast(value);
                breakfastLabel.setText("Breakfast: "+meals.getBreakfast());
                totalLabel.setText("Total: "+meals.getTotal());
            }
            breakfastText.setText("0");
        });
        lunchButton.addActionListener((ActionEvent e) -> {
            if (isNumber(lunchText.getText())) {
                int value = Integer.parseInt(lunchText.getText());
                meals.addLunch(value);
                lunchLabel.setText("Lunch: "+meals.getLunch());
                totalLabel.setText("Total: "+meals.getTotal());
            }
            lunchText.setText("0");
        });
            dinnerButton.addActionListener((ActionEvent e) -> {
            if (isNumber(dinnerText.getText())) {
                int value = Integer.parseInt(dinnerText.getText());
                meals.addDinner(value);
                dinnerLabel.setText("Dinner: "+meals.getDinner());
                totalLabel.setText("Total: "+meals.getTotal());
            }
            dinnerText.setText("0");
        });
            snacksButton.addActionListener((ActionEvent e) -> {
            if (isNumber(snacksText.getText())) {
                int value = Integer.parseInt(snacksText.getText());
                meals.addSnacks(value);
                snacksLabel.setText("Snacks: "+meals.getSnacks());
                totalLabel.setText("Total: "+meals.getTotal());
            }
            snacksText.setText("0");
        });
        
        breakfastText.addFocusListener(bfTxtListener);
        breakfastText.setEditable(false);
        lunchText.addFocusListener(lcTxtListener);
        lunchText.setEditable(false);
        dinnerText.addFocusListener(dnrTxtListener);
        dinnerText.setEditable(false);
        snacksText.addFocusListener(snTxtListener);
        snacksText.setEditable(false);
       
        resetButton.addActionListener(resetListener); 
    }
    
    /** 
     * this method checks whether an input string is made up of
     * numbers only, and that it is made up of at least one digit
     */
    private boolean isNumber(String input) {
        String reg = "[0-9][0-9]*";
        Pattern p = Pattern.compile(reg);
        return (Pattern.matches(reg,input));
    }

    /**
     * focus listener to change state of text field
     */
    class TextListener implements FocusListener{
        JTextField text;
        String temp = "";
        public TextListener(JTextField text) {
            this.text = text;
        }
        
        public void focusGained(FocusEvent e) {
            text.setEditable(true);
            temp = text.getText();
            text.setText("");
        }
        
        public void focusLost(FocusEvent e) {
            if (isNumber(text.getText())) temp = text.getText();
            text.setEditable(false);
            text.setText(temp);
            temp = "";
        }
    }
    
    /**
     * action listener for reset button.
     */
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
            meals.resetValues();
            } catch (java.io.FileNotFoundException ex)  {
                
            }
        resetTextFields();
        }
        
        public void resetTextFields() {
            totalLabel.setText("Total: "+Integer.toString(meals.getTotal()));
            breakfastText.setText("0");
            breakfastLabel.setText("Breakfast: 0");
            lunchText.setText("0");
            lunchLabel.setText("Lunch: 0");
            dinnerText.setText("0");
            dinnerLabel.setText("Dinner: 0");
            snacksText.setText("0");
            snacksLabel.setText("Snacks: 0");
        }
    }
}
