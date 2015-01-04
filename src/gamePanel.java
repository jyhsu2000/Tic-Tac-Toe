import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class gamePanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private static gamePanel instance;
	int size = 500;
	int gridSize = size / 3;
	int left = 50;
	int top = 25;
	int padding = 20;
	int[][] loc = new int[2][2];

	public gamePanel() {
		//listener
		addMouseListener(this);
		//initialize
		initialize();
	}

	static public gamePanel getInstance() {
		if (instance == null) {
			instance = new gamePanel();
		}
		return instance;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		//border
		g2.drawRect(left, top, size, size);
		//horizontal line
		g2.drawLine(left + padding, top + gridSize, left + size - padding, top + gridSize);
		g2.drawLine(left + padding, top + gridSize * 2, left + size - padding, top + gridSize * 2);
		//vertical line
		g2.drawLine(left + gridSize, top + padding, left + gridSize, top + size - padding);
		g2.drawLine(left + gridSize * 2, top + padding, left + gridSize * 2, top + size - padding);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String status = findLocation(e.getX(), e.getY());
		statusPanel.setStatus(status);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private void initialize() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				loc[i][j] = 0;
			}
		}
	}

	private String findLocation(int x, int y) {
		String strX = "";
		String strY = "";
		//check if in area
		if (findLocX(x) == -1 || findLocY(y) == -1) {
			return "";
		}
		//horizontal location
		switch (findLocX(x)) {
		case 0:
			strX = "left";
			break;
		case 1:
			strX = "middle";
			break;
		case 2:
			strX = "right";
			break;
		}
		//vertical location
		switch (findLocY(y)) {
		case 0:
			strY = "top";
			break;
		case 1:
			strY = "middle";
			break;
		case 2:
			strY = "bottom";
			break;
		}
		return strY + " " + strX;
	}

	private int findLocX(int x) {
		int locX = -1;
		//check if in area
		if (x < left || x > left + size) {
			return -1;
		}
		//horizontal location
		if (x < left + gridSize) {
			locX = 0;
		} else if (x > left + gridSize * 2) {
			locX = 2;
		} else {
			locX = 1;
		}
		return locX;
	}

	private int findLocY(int y) {
		int locY = -1;
		//check if in area
		if (y < top || y > top + size) {
			return -1;
		}
		//vertical location
		if (y < top + gridSize) {
			locY = 0;
		} else if (y > top + gridSize * 2) {
			locY = 2;
		} else {
			locY = 1;
		}
		return locY;
	}
}
