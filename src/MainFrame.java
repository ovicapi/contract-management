import javax.swing.JFrame;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class MainFrame {
	public static void mainFrame() throws InvalidFormatException {
		JFrame frame = new CM_frame();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setAlwaysOnTop (true);
	}
}
