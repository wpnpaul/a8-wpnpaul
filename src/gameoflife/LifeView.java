package gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

import gameoflife.LifeController.DimListener;
import gameoflife.LifeController.PanelListener;

public class LifeView extends JFrame {

	private JButton _nextButton;
	private JButton _clearButton;
	private JButton _fillButton;
	private JSlider _resizeSlider;
	private JTextField dimensionField;
	
	private HashSet<Spot> _panels;
	private int _width;
	private int _height;
	Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
	Border yellowBorder = BorderFactory.createLineBorder(Color.YELLOW);
	
	private JPanel _boardPanel;
	
	public LifeView() {
		_boardPanel = new JPanel();
		_boardPanel.setSize(600, 600);
		JPanel controlPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 700);
		
		
		_nextButton = new JButton("Next");
		_clearButton = new JButton("Clear");
		_fillButton = new JButton("Fill");
		//_resizeSlider = new JSlider(10, 500, 10);
		dimensionField = new JTextField("10");
		_width = 10;
		_height = 10;
		_panels = new HashSet<>();
		
		controlPanel.add(_nextButton);
		controlPanel.add(_clearButton);
		controlPanel.add(_fillButton);
		controlPanel.add(dimensionField);
		
		initBoard();
		
		this.setLayout(new BorderLayout());
		this.add(_boardPanel, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public HashSet<Spot> getLifeBoard() {
		return _panels;
	}
	
	public void clearBoard() {
		for (JPanel p : _panels) {
			if (p.getBackground() == Color.WHITE) {
				p.setBackground(Color.BLACK);
			}
		}
	}
	
	public void togglePanel(Spot s) {
		if (s.getBackground() == Color.WHITE) {
			s.setBackground(Color.BLACK);
		} else {
			s.setBackground(Color.WHITE);
		}
	}
	
	public void setPanelOn(Spot s) {
		if (s.getBackground() == Color.BLACK) {
			s.setBackground(Color.WHITE);
		}
	}
	
	public void setPanelOff(Spot s) {
		if (s.getBackground() == Color.WHITE) {
			s.setBackground(Color.BLACK);
		}
	}
	
	public void highlightPanel(JPanel p) {
		p.setBorder(yellowBorder);
	}
	
	public void unhighlightPanel(JPanel p) {
		p.setBorder(blackBorder);
	}
	
	private void initBoard() {
		_boardPanel.setLayout(new GridLayout(_height, _width));
		for (int i = 0; i < _width; i++) {
			for (int j = 0; j < _height; j++) {
				Spot s = new Spot(i, j);
				s.setBackground(Color.BLACK);
				s.setBorder(blackBorder);
				_boardPanel.add(s);
				_panels.add(s);
			}
		}
	}
	
	public void resizeBoard(int dim) {
		this.remove(_boardPanel);
		JPanel temp = new JPanel();
		temp.setVisible(true);
		_boardPanel = temp;
		_width = dim;
		_height = dim;
		_panels.clear();
		initBoard();
		this.add(_boardPanel);
		
	}
	
	public int getResizeValue() {
		return _resizeSlider.getValue();
	}
	
	public int getDimValue() {
		return Integer.parseInt(dimensionField.getText());
	}

	public void addNextListener(ActionListener l) {
		_nextButton.addActionListener(l);
	}
	
	public void addClearListener(ActionListener l) {
		_clearButton.addActionListener(l);
	}
	
	public void addFillListener(ActionListener l) {
		_fillButton.addActionListener(l);
	}
	
	public void addResizeListener(ChangeListener l) {
		_resizeSlider.addChangeListener(l);
	}
	
	public void addPanelListener(PanelListener l) {
		for (JPanel p : _panels) {
			p.addMouseListener(l);
		}
	}

	public void addDimListener(DimListener l) {
		dimensionField.addActionListener(l);
	}

}
