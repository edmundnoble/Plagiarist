package plagiarist;

import java.awt.Label;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GFX {

	public static void Main(String[] args) throws Exception {
		JFrame mainFrame = new JFrame();
		Box box = new Box(BoxLayout.X_AXIS);
		Label label1 = new Label("Enter text here.");
		JTextField mainField = new JTextField();
		box.add(label1);
		box.add(mainField);
		mainFrame.add(box);


	}



}
