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

public class CalorieCounterFrame extends JFrame
{

	private final int FRAME_WIDTH = 350, FRAME_HEIGHT = 300, FIELD_WIDTH = 2;

	//decided to use specific variables for buttons instead of array,
	//so that the code is more self-documented
	private JButton breakfastButton = new JButton("+"); 
	private JButton lunchButton = new JButton("+");
	private JButton dinnerButton = new JButton("+");
	private JButton snacksButton = new JButton("+");
	private JButton resetButton = new JButton("Reset");
	
	protected MealMaker meals = new MealMaker();

	protected JTextField breakfastValue = new JTextField("0",FIELD_WIDTH);
	protected JTextField lunchValue = new JTextField("0",FIELD_WIDTH);
	protected JTextField dinnerValue = new JTextField("0",FIELD_WIDTH); 
	protected JTextField snacksValue = new JTextField("0",FIELD_WIDTH);

	private JLabel breakfastLabel = new JLabel("Breakfast: "+meals.getBreakfast());
	private JLabel lunchLabel = new JLabel("Lunch: "+meals.getLunch());
	private JLabel dinnerLabel = new JLabel("Dinner: "+meals.getDinner());
	private JLabel snacksLabel = new JLabel("Snacks: "+meals.getSnacks());
	private JLabel totalLabel = new JLabel("Total: "+meals.getTotal());

	private ActionListener resetListener = new ResetListener();
		
	private FocusListener bfTxtListener = new TextListener(breakfastValue); 
	private FocusListener lcTxtListener = new TextListener(lunchValue); 
	private FocusListener dnrTxtListener = new TextListener(dinnerValue); 
	private FocusListener snTxtListener = new TextListener(snacksValue);
	
	public String test = "hello";

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
		breakfastButton.addActionListener((ActionEvent e) -> {
			int value = Integer.parseInt(breakfastValue.getText());
			meals.addBreakfast(value);
			breakfastValue.setText("0");
			breakfastLabel.setText("Breakfast: "+meals.getBreakfast());
			totalLabel.setText("Total: "+meals.getTotal());
			
		});
		lunchButton.addActionListener((ActionEvent e) -> {
			int value = Integer.parseInt(lunchValue.getText());
			meals.addLunch(value);
			lunchValue.setText("0");
			lunchLabel.setText("Lunch: "+meals.getLunch());
			totalLabel.setText("Total: "+meals.getTotal());
		});
			dinnerButton.addActionListener((ActionEvent e) -> {
			int value = Integer.parseInt(dinnerValue.getText());
			meals.addDinner(value);
			dinnerValue.setText("0");
			dinnerLabel.setText("Dinner: "+meals.getDinner());
			totalLabel.setText("Total: "+meals.getTotal());
		});
			snacksButton.addActionListener((ActionEvent e) -> {
			int value = Integer.parseInt(snacksValue.getText());
			meals.addSnacks(value);
			snacksValue.setText("0");
			snacksLabel.setText("Snacks: "+meals.getSnacks());
			totalLabel.setText("Total: "+meals.getTotal());
		});
		
		breakfastValue.addFocusListener(bfTxtListener);
		breakfastValue.setEditable(false);
		lunchValue.addFocusListener(lcTxtListener);
		lunchValue.setEditable(false);
		dinnerValue.addFocusListener(dnrTxtListener);
		dinnerValue.setEditable(false);
		snacksValue.addFocusListener(snTxtListener);
		snacksValue.setEditable(false);
	   
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
			meals.resetValues();
			} catch (java.io.FileNotFoundException ex)  {
				
			}
		resetTextFields();
		}
		
		public void resetTextFields() {
			totalLabel.setText("Total: "+Integer.toString(meals.getTotal()));
			breakfastValue.setText("0");
			lunchValue.setText("0");
			dinnerValue.setText("0");
			snacksValue.setText("0");
		}
	}
}
