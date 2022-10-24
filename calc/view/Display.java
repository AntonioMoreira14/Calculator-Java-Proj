package calc.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import calc.model.Memory;
import calc.model.MemoryObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoryObserver {
	
	private final JLabel label;
	
	public Display() {
		Memory.getInstance().addObserver(this);
		
		setBackground(new Color(46, 49, 50));
		label = new JLabel(Memory.getInstance().getPresentText());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 24));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		
		add(label);
	}
	
	public void valueChange(String newValue) {
		label.setText(newValue);
	}
}
