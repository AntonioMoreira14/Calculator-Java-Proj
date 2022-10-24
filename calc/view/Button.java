package calc.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {
	
	public Button(String text, Color color) {
		setText(text);
		setOpaque(true);
		setBackground(color);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setFont(new Font("courier", Font.PLAIN, 25));
	}
}
