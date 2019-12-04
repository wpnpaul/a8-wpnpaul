package gameoflife;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Spot extends JPanel {
	
	private int _x;
	private int _y;
	
	public Spot(int x, int y) {
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		setBackground(Color.BLACK);
		_x = x;
		_y = y;
	}
	
	public int getSpotX() {
		return _x;
	}
	
	public int getSpotY() {
		return _y;
	}
}
