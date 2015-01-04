import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class sidePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static sidePanel instance;

	public sidePanel() {
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.restart();
			}
		});
		add(restartButton);
	}

	static public sidePanel getInstance() {
		if (instance == null) {
			instance = new sidePanel();
		}
		return instance;
	}
}
