package gameoflife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LifeController {
	
	private LifeView _view;
	private LifeModel _model;

	public LifeController(LifeView view, LifeModel model) {
		_view = view;
		_model = model;
		
		_view.addNextListener(new NextListener());
		_view.addClearListener(new ClearListener());
		_view.addFillListener(new FillListener());
		//_view.addResizeListener(new ResizeListener());
		_view.addPanelListener(new PanelListener());
		_view.addDimListener(new DimListener());
	}
	
	class PanelListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Spot s = (Spot) e.getSource();
			_view.togglePanel(s);
			_model.toggleSpot(s.getSpotX(), s.getSpotY());
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JPanel p = (JPanel) e.getSource();
			_view.highlightPanel(p);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JPanel p = (JPanel) e.getSource();
			_view.unhighlightPanel(p);
		}
		
	}
	
	class NextListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_model.nextGen();
			int[][] board = _model.getNextGen();
			for (Spot s : _view.getLifeBoard()) {
				if (board[s.getSpotX()][s.getSpotY()] == 1) {
					_view.setPanelOn(s);
				} else {
					_view.setPanelOff(s);
				}
			}
			_model.printBoard();
		}
		
	}
	
	class ClearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_model.clearBoard();
			_view.clearBoard();
			_model.printBoard();
		}
		
	}
	
	class FillListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_model.fillBoard();
			int[][] board = _model.getNextGen();
			for (Spot s : _view.getLifeBoard()) {
				if (board[s.getSpotX()][s.getSpotY()] == 1) {
					_view.togglePanel(s);
				}
			}
			_model.printBoard();
		}
		
	}
	
	class ResizeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			int dim = _view.getResizeValue();
			_model.resizeBoard(dim);
			System.out.println(_model.getNextGen().length + " x " +  _model.getNextGen()[0].length);

		}
		
	}
	
	class DimListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int dim = _view.getDimValue();
			_view.resizeBoard(dim);
			_model.resizeBoard(dim);
			System.out.println("True");
			_view.addPanelListener(new PanelListener());
		}


		
	}
}
