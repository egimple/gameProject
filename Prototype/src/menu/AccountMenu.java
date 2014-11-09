package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AccountMenu {

	private static JPanel panel = new JPanel();
	private static JFrame frame = new JFrame();

	public static void setFrameTitle(String title) {
		frame.setTitle(title);
	}

	public AccountMenu() {

		frame.setSize(325, 215);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(panel);
		new Login(panel);
		frame.setVisible(true);

	}
	

	public static void destroyFrame(){
		frame.setVisible(false);
		frame.dispose();
	}

	public static void main(String[] args) {
		new AccountMenu();
	}

	public class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "login button has been pressed");
		}
	}
	
	

}