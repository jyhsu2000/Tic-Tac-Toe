import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class Launcher {
	int width = 800;
	int height = 600;

	private JFrame frame;

	/** Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher window = new Launcher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the application. */
	public Launcher() {
		initialize();
	}

	/** Initialize the contents of the frame. */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Tic Tac Toe");

		JPanel mainPanel = new gamePanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		JPanel sidePanel = new JPanel();
		frame.getContentPane().add(sidePanel, BorderLayout.EAST);

		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}

}
