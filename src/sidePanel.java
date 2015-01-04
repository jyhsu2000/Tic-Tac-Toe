import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class sidePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static sidePanel instance;
	private JButton restartButton;
	private JButton toggleAIButton;
	private JButton toggleSmartAIButton;

	public sidePanel() {
		setLayout(new GridLayout(9, 1));
		restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.restart();
			}
		});
		add(restartButton);
		toggleAIButton = new JButton("AI(Off)");
		toggleAIButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean AIOn = gamePanel.toggleAI();
				if (AIOn) {
					toggleAIButton.setText("AI(On)");
				} else {
					toggleAIButton.setText("AI(Off)");
				}
				toggleSmartAIButton.setEnabled(AIOn);
			}
		});
		add(toggleAIButton);
		toggleSmartAIButton = new JButton("SmartAI(Off)");
		toggleSmartAIButton.setEnabled(false);
		toggleSmartAIButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean AIOn = gamePanel.toggleSmartAI();
				if (AIOn) {
					toggleSmartAIButton.setText("SmartAI(On)");
				} else {
					toggleSmartAIButton.setText("SmartAI(Off)");
				}
			}
		});
		add(toggleSmartAIButton);
	}

	static public sidePanel getInstance() {
		if (instance == null) {
			instance = new sidePanel();
		}
		return instance;
	}
}
