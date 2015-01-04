import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class gamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static gamePanel instance;
	int size = 500;
	int left = 50;
	int top = 25;
	int padding = 20;

	public gamePanel() {
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
		g2.drawLine(left + padding, top + size / 3, left + size - padding, top + size / 3);
		g2.drawLine(left + padding, top + size / 3 * 2, left + size - padding, top + size / 3 * 2);
		//vertical line
		g2.drawLine(left + size / 3, top + padding, left + size / 3, top + size - padding);
		g2.drawLine(left + size / 3 * 2, top + padding, left + size / 3 * 2, top + size - padding);
	}
}
