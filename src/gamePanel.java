import java.awt.BasicStroke;
import java.awt.Color;
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
	int radius = 40;
	int normalLineWidth = 10;
	int boldLineWidth = 20;
	int[][] loc = new int[3][3];

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
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(getForeground());
		g2.setStroke(new BasicStroke(normalLineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		//border
		g2.drawRect(left, top, size, size);
		//horizontal line
		g2.drawLine(left + padding, top + gridSize, left + size - padding, top + gridSize);
		g2.drawLine(left + padding, top + gridSize * 2, left + size - padding, top + gridSize * 2);
		//vertical line
		g2.drawLine(left + gridSize, top + padding, left + gridSize, top + size - padding);
		g2.drawLine(left + gridSize * 2, top + padding, left + gridSize * 2, top + size - padding);
		//draw O and X
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (loc[i][j] == 1) {
					//draw O
					int startX = (int) (left + gridSize * (i + 0.5) - radius);
					int startY = (int) (top + gridSize * (j + 0.5) - radius);
					g2.setColor(Color.BLUE);
					g2.drawOval(startX, startY, radius * 2, radius * 2);
				} else if (loc[i][j] == 2) {
					//draw X
					int startX = (int) (left + gridSize * (i + 0.5) - radius);
					int startY = (int) (top + gridSize * (j + 0.5) - radius);
					g2.setColor(Color.RED);
					g2.drawLine(startX, startY, startX + radius * 2, startY + radius * 2);
					g2.drawLine(startX, startY + radius * 2, startX + radius * 2, startY);

				}
			}
		}
		//draw line cross symbol
		//horizontal line
		for (int i = 0; i < 3; i++) {
			int n = loc[i][0] * loc[i][1] * loc[i][2];
			if (n == 1 | n == 8) {
				g2.setStroke(new BasicStroke(boldLineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				if (n == 1) {
					g2.setColor(Color.BLUE);
				} else {
					g2.setColor(Color.RED);
				}
				int startX = (int) (left + gridSize * (i + 0.5));
				g2.drawLine(startX, top + padding, startX, top + size - padding);
			}
		}
		//vertical line
		for (int i = 0; i < 3; i++) {
			int n = loc[0][i] * loc[1][i] * loc[2][i];
			if (n == 1 | n == 8) {
				g2.setStroke(new BasicStroke(boldLineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				if (n == 1) {
					g2.setColor(Color.BLUE);
				} else {
					g2.setColor(Color.RED);
				}
				int startY = (int) (top + gridSize * (i + 0.5));
				g2.drawLine(left + padding, startY, left + size - padding, startY);
			}
		}
		//diagonal line
		//top-left to bottom-right
		int n1 = loc[0][0] * loc[1][1] * loc[2][2];
		if (n1 == 1 | n1 == 8) {
			g2.setStroke(new BasicStroke(boldLineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			if (n1 == 1) {
				g2.setColor(Color.BLUE);
			} else {
				g2.setColor(Color.RED);
			}
			g2.drawLine(left + padding, top + padding, left + size - padding, top + size - padding);
		}
		//top-right to bottom-left
		int n2 = loc[0][2] * loc[1][1] * loc[2][0];
		if (n2 == 1 | n2 == 8) {
			g2.setStroke(new BasicStroke(boldLineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			if (n2 == 1) {
				g2.setColor(Color.BLUE);
			} else {
				g2.setColor(Color.RED);
			}
			g2.drawLine(left + padding, top + size - padding, left + size - padding, top + padding);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String status = findLocation(e.getX(), e.getY());
		int x = findLocX(e.getX());
		int y = findLocY(e.getY());
		//check if in area
		if (x == -1 || y == -1) {
			return;
		}
		//FIXME change
		loc[x][y]++;
		if (loc[x][y] == 3) {
			loc[x][y] = 0;
		}
		//repaint
		repaint();
		//update status
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
