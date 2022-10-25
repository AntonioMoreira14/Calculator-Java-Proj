package calc.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import calc.model.Memory;

@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener {
	
	private final Color DARK_GRAY = new Color(92, 93, 93);
	private final Color ALMOST_BLACK = new Color(14, 8, 8);
	private final Color LIGHT_GRAY = new Color(144, 146, 149);
	
	public Keyboard() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		// FIRST LINE
		gbc.gridwidth = 2;
		addButton("AC", ALMOST_BLACK, gbc, 0 , 0);
		gbc.gridwidth = 1;
		addButton("±", DARK_GRAY, gbc, 2 , 0);
		addButton("÷", DARK_GRAY, gbc, 3 , 0);
		
		// SECOND LINE
		addButton("7", LIGHT_GRAY, gbc, 0 , 1);
		addButton("8", LIGHT_GRAY, gbc, 1 , 1);
		addButton("9", LIGHT_GRAY, gbc, 2 , 1);
		addButton("*", DARK_GRAY, gbc, 3 , 1);
		
		// THIRD LINE
		addButton("4", LIGHT_GRAY, gbc, 0 , 2);
		addButton("5", LIGHT_GRAY, gbc, 1 , 2);
		addButton("6", LIGHT_GRAY, gbc, 2 , 2);
		addButton("-", DARK_GRAY, gbc, 3 , 2);
		
		// FOURTH LINE
		addButton("1", LIGHT_GRAY, gbc, 0 , 3);
		addButton("2", LIGHT_GRAY, gbc, 1 , 3);
		addButton("3", LIGHT_GRAY, gbc, 2 , 3);
		addButton("+", DARK_GRAY, gbc, 3 , 3);
		
		// FIFTH LINE
		addButton("0", LIGHT_GRAY, gbc, 0 , 4);
		addButton("%", LIGHT_GRAY, gbc, 1 , 4);
		addButton(",", LIGHT_GRAY, gbc, 2 , 4);
		addButton("=", DARK_GRAY, gbc, 3 , 4);
	}

	private void addButton(String text, Color color, GridBagConstraints gbc, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		Button button = new Button(text, color);
		button.addActionListener(this);
		add(button, gbc);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			Memory.getInstance().processCommand(button.getText());
		}
	}
}
