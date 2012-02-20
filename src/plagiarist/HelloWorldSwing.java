package plagiarist;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class HelloWorldSwing implements ActionListener {
	// private static String labelText = "Number of button clicks: ";
	final static JLabel label = new JLabel();

	final static JEditorPane textArea = new JEditorPane();
	final static String LOOKANDFEEL = null;

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public Component createComponents() {

		JButton button = new JButton("Check for plagiarism");
		button.setMnemonic(KeyEvent.VK_1);
		button.addActionListener(this);
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		// textArea.setLineWrap(true);
		// textArea.setWrapStyleWord(true);
		textArea.setText("Enter suspected text here.");

		JPanel pane = new JPanel(new GridLayout(0, 1));
		pane.add(textArea);
		// pane.add(label);
		pane.add(button);
		pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		return pane;
	}

	private static void createAndShowGUI() {
		// Set the look and feel.
		initLookAndFeel();

		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		JFrame frame = new JFrame("Plagiarist");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HelloWorldSwing app = new HelloWorldSwing();
		Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Processing processor = new Processing();
		try {
			System.out.print(processor.parseResults(textArea.getText()));
		} catch (IOException e) {
			System.err.println("Input invalid.");
			e.printStackTrace();
		}
	}

	private static void initLookAndFeel() {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			System.err.println("Couldn't get specified look and feel ("
					+ lookAndFeel + "), for some reason.");
			System.err.println("Using the default look and feel.");
			e.printStackTrace();
		}
	}
}
