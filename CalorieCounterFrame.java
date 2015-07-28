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
 * to do:
 * Add this to Eclipse
 * Try to re-create the inner class, instead of using anonymous class for parameter passing on each ActionListener:
 *      - Initial trouble: I had initially an inner class that took a Food object as parameter, however this didn't
 *      work, due to Java passing parameters as value, not reference. It was causing the Reset button to fail, for
 *      some reason. A solution might be to implement a reset() method within the ActionListener implementing class,
 *      but how would I then make sure that each object would refer to its respective variable on the CalorieCounter
 *      class?
 * Add an option to save values by date;
 * Add an option to add food titles to each meal;
 * Add finishing to the layout;
 */
public class CalorieCounterFrame extends JFrame
{
    //decided to use specific variables for buttons instead of array,
    //so that the code is more self-documented
    private JButton breakfastButton = new JButton("+"), lunchButton = new JButton("+"), 
        dinnerButton = new JButton("+"), snacksButton = new JButton("+"), 
        resetButton = new JButton("Reset");

    private final int FRAME_WIDTH = 300, FRAME_HEIGHT = 300, FIELD_WIDTH = 4;
    
    
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
        snBtnListener, resetListener = new ResetListener();
        
    private FocusListener bfTxtListener = new TextListener(breakfastValue), lcTxtListener = new TextListener(lunchValue), 
    dnrTxtListener = new TextListener(dinnerValue), snTxtListener = new TextListener(snacksValue);
    
    public CalorieCounterFrame(String title) {
        this.setTitle(title);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        panel.setLayout(layout);
        
        addActionListeners();

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(breakfastLabel)
                    .addComponent(lunchLabel)
                    .addComponent(dinnerLabel)
                    .addComponent(snacksLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(breakfastValue)
                    .addComponent(lunchValue)
                    .addComponent(dinnerValue)
                    .addComponent(snacksValue)
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
                    .addComponent(breakfastValue)
                    .addComponent(breakfastButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lunchLabel)
                    .addComponent(lunchValue)
                    .addComponent(lunchButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dinnerLabel)
                    .addComponent(dinnerValue)
                    .addComponent(dinnerButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(snacksLabel)
                    .addComponent(snacksValue)
                    .addComponent(snacksButton))
                .addComponent(totalLabel)
                .addComponent(resetButton)
        );
        add(panel);
    }
    
    /**
     * adds action and focus listener objects to the frame buttons.
     */
    public void addActionListeners() {
        breakfastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(breakfastValue.getText());
                CalorieCounter.breakfast.setTotal(value);
                CalorieCounter.total += value;
                breakfastValue.setText(Integer.toString(CalorieCounter.breakfast.getTotal()));
                totalLabel.setText("Total: "+CalorieCounter.total);
            }
        });
        lunchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(lunchValue.getText());
                CalorieCounter.lunch.setTotal(value);
                CalorieCounter.total += value;
                lunchValue.setText(Integer.toString(CalorieCounter.lunch.getTotal()));
                totalLabel.setText("Total: "+CalorieCounter.total);
            }
        });
        dinnerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(dinnerValue.getText());
                CalorieCounter.dinner.setTotal(value);
                CalorieCounter.total += value;
                dinnerValue.setText(Integer.toString(CalorieCounter.dinner.getTotal()));
                totalLabel.setText("Total: "+CalorieCounter.total);
            }
        });
        snacksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(snacksValue.getText());
                CalorieCounter.snacks.setTotal(value);
                CalorieCounter.total += value;
                snacksValue.setText(Integer.toString(CalorieCounter.snacks.getTotal()));
                totalLabel.setText("Total: "+CalorieCounter.total);
            }
        });
        
        /* un-comment if you want text fields to show as un-editable when not selected.

        breakfastValue.addFocusListener(bfTxtListener);
        breakfastValue.setEditable(false);
        lunchValue.addFocusListener(lcTxtListener);
        lunchValue.setEditable(false);
        dinnerValue.addFocusListener(dnrTxtListener);
        dinnerValue.setEditable(false);
        snacksValue.addFocusListener(snTxtListener);
        snacksValue.setEditable(false);
        */
       
        resetButton.addActionListener(resetListener); 
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
            totalLabel.setText("Total: "+Integer.toString(CalorieCounter.total));
            breakfastValue.setText("0");
            lunchValue.setText("0");
            dinnerValue.setText("0");
            snacksValue.setText("0");
        }
    }
}
