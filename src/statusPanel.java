import javax.swing.JLabel;
import javax.swing.JPanel;

public class statusPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static statusPanel instance;
	private JLabel statusLabel;

	public statusPanel() {
		statusLabel = new JLabel();
		add(statusLabel);
	}

	static public statusPanel getInstance() {
		if (instance == null) {
			instance = new statusPanel();
		}
		return instance;
	}

	static public void setStatus(String status) {
		getInstance().statusLabel.setText(status);
	}
}
